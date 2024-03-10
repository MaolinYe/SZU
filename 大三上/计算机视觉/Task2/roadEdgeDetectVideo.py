import cv2
import numpy as np


def detect(image):
    gauss = cv2.GaussianBlur(image, (5, 5), 0)
    edge = cv2.Canny(gauss, 75, 175)
    # 进行Hough直线变换
    lines = cv2.HoughLines(edge, 1, np.pi / 180, 220)
    # 绘制检测到的直线
    if lines is not None:
        for rho, theta in lines[:, 0, :]:
            if theta < 2:
                continue
            a = np.cos(theta)
            b = np.sin(theta)
            x0 = a * rho
            y0 = b * rho
            x1 = int(x0 - 350 * (-b))
            y1 = int(y0 - 350 * a)
            x2 = int(x0 - 700 * (-b))
            y2 = int(y0 - 700 * a)
            cv2.line(image, (x1, y1), (x2, y2), (0, 255, 0), 4)
    return image


video = cv2.VideoCapture('Task2/03.avi')  # 打开视频文件
# 获取视频的宽度和高度以及帧率信息
width = int(video.get(cv2.CAP_PROP_FRAME_WIDTH))
height = int(video.get(cv2.CAP_PROP_FRAME_HEIGHT))
fps = video.get(cv2.CAP_PROP_FPS)
# 创建VideoWriter对象，用于保存处理后的视频
videoWriter = cv2.VideoWriter('Task2/video3.mp4', cv2.VideoWriter_fourcc(*'mp4v'), fps, (width, height))
while True:
    have, frame = video.read()  # 读取当前帧
    if have:
        frame = detect(frame)  # 在这里对每一帧进行处理
        videoWriter.write(frame)  # 将处理后的帧写入输出视频文件
    else:
        break
video.release()  # 释放资源
videoWriter.release()
