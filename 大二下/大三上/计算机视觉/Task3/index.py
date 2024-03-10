import gradio as gr
import FaceDetect


def face_detect(image, model):
    if model == "Haar":
        return FaceDetect.Haar(image)
    elif model == "肤色特征":
        return FaceDetect.SkinFeature(image)
    else:
        return FaceDetect.DeepLearning(image)


models = ["Haar", "肤色特征", "深度学习"]
interface = gr.Interface(fn=face_detect, inputs=[gr.Image(), gr.Dropdown(models)],
                         outputs=gr.Image())
interface.launch()
