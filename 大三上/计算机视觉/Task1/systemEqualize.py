import matplotlib.pyplot as plt
import cv2

img = cv2.imread("OIP.jpg")
img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
plt.hist(img.ravel(), bins=256)
plt.title('origin')
plt.show()  # 原始直方图
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.title('origin')
plt.imshow(img)
plt.show()  # 原始灰度图

img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
img = cv2.equalizeHist(img)
plt.hist(img.ravel(), bins=256)
plt.title('systemEqualize')
plt.show()  # 均衡化直方图
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.imshow(img)
plt.title('systemEqualize')
plt.show()  # 均衡化灰度图