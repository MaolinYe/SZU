import cv2
import numpy as np

# 读取图像
image = cv2.imread('frame.jpg')

# lines = cv2.HoughLinesP(edge, 1, np.pi / 180, 100, minLineLength=50, maxLineGap=1)
# # 绘制检测到的直线
# if lines is not None:
#     for line in lines:
#         x1, y1, x2, y2 = line[0]
#         cv2.line(image, (x1, y1), (x2, y2), (0, 0, 255), 2)

# 将图像转换为灰度图
# image = cv2.cvtColor(image, cv2.COLOR_BGR2GRAY)
# image = cv2.equalizeHist(image)
# _, image = cv2.threshold(image, 75, 255, cv2.THRESH_BINARY)
# image = cv2.adaptiveThreshold(image, 255, cv2.ADAPTIVE_THRESH_MEAN_C, cv2.THRESH_BINARY, 11, 2)
image = cv2.GaussianBlur(image, (5, 5), 0)
image = cv2.Canny(image, 75, 175)
cv2.imwrite('Canny75-175Gauss.jpg', image)