import matplotlib.pyplot as plt
import cv2
import numpy

img = cv2.imread("OIP.jpg")
img = cv2.cvtColor(img, cv2.COLOR_BGR2GRAY)
hist = cv2.calcHist([img], [0], None, [256], [0, 256])
cdf = hist.cumsum()
mapPixel = 255 * cdf / cdf[-1]
img = numpy.interp(img.ravel(), range(256), mapPixel).reshape(img.shape)
img = cv2.convertScaleAbs(img)
plt.hist(img.ravel(), bins=256)
plt.title('myEqualize')
plt.show()  # 均衡化直方图
img = cv2.cvtColor(img, cv2.COLOR_BGR2RGB)
plt.imshow(img)
plt.title('myEqualize')
plt.show()  # 均衡化灰度图
