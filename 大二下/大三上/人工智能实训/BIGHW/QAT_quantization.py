# QAT quantization  QAT量化
import onnx
from onnxruntime.quantization import quantize_qat, QuantType

model_fp32 = "yolov8n.onnx"
model_quant = "yolov8n_int8.onnx"
quantized_model = quantize_qat(model_fp32, model_quant)
