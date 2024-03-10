from ultralytics import YOLO
import matplotlib.pyplot as plt

# model = YOLO("yolov8n.yaml")  # build a new model from scratch
model=YOLO("yolov8n.pt")
model.train(data="data.yaml", epochs=50, device=0)  # train the model
model.val()