import torch
import torch.quantization
import numpy as np
import ort

class M(torch.nn.Module):
    def __init__(self):
        super(M, self).__init__()
        self.quant = torch.quantization.QuantStub()  # 静态量化时量化桩用于量化数据
        self.conv = torch.nn.Conv2d(1, 1, 1)
        self.relu = torch.nn.ReLU()
        self.dequant = torch.quantization.DeQuantStub()  # 取消量化桩

    def forward(self, x):
        x = self.quant(x)  # 量化数据，从fp32->uint8
        x = self.conv(x)  # 量化后conv
        x = self.relu(x)  # 量化后relu
        x = self.dequant(x)  # 恢复量化变量为fp32
        return x


# create a model instance
model_fp32 = M()  # 创建模型
model_fp32.eval()  # 推理模式
model_fp32.qconfig = torch.quantization.get_default_qconfig('fbgemm')  # 设置量化配置
model_fp32_fused = torch.quantization.fuse_modules(model_fp32, [['conv', 'relu']])  # 量化算子并融合
model_fp32_prepared = torch.quantization.prepare(model_fp32_fused)  # 准备
input_fp32 = torch.randn(4, 1, 4, 4)  # 产生伪数据用于测试模型
model_fp32_prepared(input_fp32)  # 数据量化操作，准备范围，刻度等
model_int8 = torch.quantization.convert(model_fp32_prepared)  # 量化数据

output_x = model_int8(input_fp32)  # 量化后推理
traced = torch.jit.trace(model_int8, (input_fp32,))  # 用于演示trace方法
traced_script = torch.jit.script(model_int8, (input_fp32,))  # 用于验证script方法

torch.onnx.export(model_int8,  # model being run
                  input_fp32,  # model input (or a tuple for multiple inputs)
                  './model_int8.onnx',  # where to save the model (can be a file or file-like object)
                  export_params=True,  # store the trained parameter weights inside the model file
                  opset_version=13,  # the ONNX version to export the model to
                  # do_constant_folding=True,  # whether to execute constant folding for optimization
                  input_names=['input'],  # the model's input names
                  # output_names = ['output'], # the model's output names
                  # example_outputs=traced(input_fp32)
                  )

torch.onnx.export(traced,  # model being run
                  input_fp32,  # model input (or a tuple for multiple inputs)
                  './model_int8_trace.onnx',  # where to save the model (can be a file or file-like object)
                  export_params=True,  # store the trained parameter weights inside the model file
                  opset_version=13,  # the ONNX version to export the model to
                  do_constant_folding=True,  # whether to execute constant folding for optimization
                  input_names=['input'],  # the model's input names
                  output_names=['output'],  # the model's output names
                  #  example_outputs=traced(input_fp32)
                  )

onnx_pth = './model_int8.onnx'

oxx_m = ort.InferenceSession(onnx_pth)
onnx_blob = input_fp32.data.numpy()
onnx_out = oxx_m.run(None, {'input': onnx_blob})[0]

print('mean diff of int8 onnx= ', np.mean(onnx_out - torch_out.data.numpy()))

onnx_pth = './model_int8_trace.onnx'
oxx_m = ort.InferenceSession(onnx_pth)
onnx_out2 = oxx_m.run(None, {'input': onnx_blob})[0]

print('mean diff of traced int8 onnx= ', np.mean(onnx_out2 - torch_out.data.numpy()))

# for traced

traced_out = traced(input_fp32)

print('mean diff of traced torch= ', np.mean(traced_out.data.numpy() - torch_out.data.numpy()))
# for script
script_out = traced_script(input_fp32)

print('mean diff of script torch= ', np.mean(script_out.data.numpy() - torch_out.data.numpy()))
# 保存模型，可以用于pnnx转换ncnn
torch.jit.save(traced, "./jit_trace.pth")
torch.jit.save(traced_script, "jit_script.pth")