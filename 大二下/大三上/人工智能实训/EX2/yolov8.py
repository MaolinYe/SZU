from ultralytics import YOLO
import matplotlib.pyplot as plt

# model = YOLO("yolov8n.yaml")  # build a new model from scratch
model=YOLO("yolov8n.pt")
model.train(data="data.yaml", epochs=30, device='cpu')  # train the model
model.val(data="data.yaml")
results = model(r"C:\Users\Yezi\Desktop\人工智能实训\HW2\data\images\00909.jpg")  # predict on an image
plt.imshow(results[0].plot())
plt.show()
results = model(r"C:\Users\Yezi\Desktop\人工智能实训\HW2\data\images\100318.jpg")  # predict on an image
plt.imshow(results[0].plot())
plt.show()
