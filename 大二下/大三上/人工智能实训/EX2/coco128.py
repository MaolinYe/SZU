from ultralytics import YOLO
import matplotlib.pyplot as plt

model = YOLO("yolov8n.yaml")  # build a new model from scratch
model.train(data="coco128.yaml", epochs=10, device='cpu')  # train the model
model.val()
results = model("bus.jpg")  # predict on an image
plt.imshow(results[0].plot())
plt.show()
