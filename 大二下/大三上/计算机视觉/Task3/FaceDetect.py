import cv2
import numpy as np


def Haar(image):
    # 加载人脸检测的Haar级联分类器
    face_cascade = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')
    # 将图像转换为灰度图像
    gray = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
    # 使用Haar级联分类器进行人脸检测
    faces = face_cascade.detectMultiScale(gray, scaleFactor=1.1, minNeighbors=5, minSize=(30, 30))
    # 在检测到的人脸周围绘制矩形框
    for (x, y, w, h) in faces:
        cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
    return image


def SkinFeature(image):
    # # 将图片从 BGR 转换到 HSV 色彩空间
    # hsv_image = cv2.cvtColor(image, cv2.COLOR_BGR2HSV)
    # 将图片从 BGR 转换到 YcrCb 色彩空间
    ycrcb_image = cv2.cvtColor(image, cv2.COLOR_BGR2YCrCb)
    # 定义色彩空间中肤色的范围
    lower_skin = np.array([0, 48, 80], dtype=np.uint8)
    upper_skin = np.array([20, 255, 255], dtype=np.uint8)
    # 根据肤色范围创建掩码
    skin_mask = cv2.inRange(ycrcb_image, lower_skin, upper_skin)
    # # 定义色彩空间中肤色的范围
    # lower_skin = np.array([0, 48, 80], dtype=np.uint8)
    # upper_skin = np.array([20, 255, 255], dtype=np.uint8)
    # skin_mask_hsv = cv2.inRange(hsv_image, lower_skin, upper_skin)
    # skin_mask = cv2.bitwise_and(skin_mask_ycrcb, skin_mask_hsv)
    # 对掩码进行高斯模糊
    skin_mask = cv2.GaussianBlur(skin_mask, (9, 9), 0)
    # 形态学操作：膨胀和腐蚀
    kernel = cv2.getStructuringElement(cv2.MORPH_ELLIPSE, (11, 11))
    skin_mask = cv2.dilate(skin_mask, kernel, iterations=2)
    skin_mask = cv2.erode(skin_mask, kernel, iterations=2)
    # 查找肤色区域的轮廓
    contours, _ = cv2.findContours(skin_mask, cv2.RETR_TREE, cv2.CHAIN_APPROX_SIMPLE)
    # 根据轮廓大小和形状筛选可能的人脸区域
    for contour in contours:
        area = cv2.contourArea(contour)
        if area > 1000:  # 可调整的面积阈值
            # 计算轮廓的边界框
            x, y, w, h = cv2.boundingRect(contour)
            # 绘制边界框
            cv2.rectangle(image, (x, y), (x + w, y + h), (0, 255, 0), 2)
    return image


def DeepLearning(image):
    # 加载预训练的人脸检测模型
    net = cv2.dnn.readNetFromCaffe('deploy.prototxt', 'res10_300x300_ssd_iter_140000_fp16.caffemodel')
    # 创建一个blob（二进制大对象）从图像中提取特征
    blob = cv2.dnn.blobFromImage(cv2.resize(image, (300, 300)), 1.0, (300, 300), (104.0, 177.0, 123.0))
    # 将blob输入到神经网络中进行前向传播
    net.setInput(blob)
    detections = net.forward()
    # 在检测到的人脸周围绘制矩形框
    for i in range(detections.shape[2]):
        confidence = detections[0, 0, i, 2]
        # 设置置信度阈值
        if confidence > 0.5:
            box = detections[0, 0, i, 3:7] * np.array([image.shape[1], image.shape[0], image.shape[1], image.shape[0]])
            (startX, startY, endX, endY) = box.astype(int)
            cv2.rectangle(image, (startX, startY), (endX, endY), (0, 255, 0), 2)
    return image
