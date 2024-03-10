from keras.models import Sequential
from keras.layers import Dense, Flatten, Conv2D, MaxPooling2D
import os
from PIL import Image
import numpy

# 导入人脸数据并进行处理
# picture_path = r"C:\Users\Yezi\Desktop\机器学习\数据集\ORL56_46"
# picture_path = r"C:\Users\Yezi\Desktop\机器学习\数据集\FERET_80"
picture_path = r"C:\Users\Yezi\Desktop\机器学习\数据集\Yale_face10080"
suffix = ".bmp"
num_people = 15
num_train = 6
num_picture_single = 11
dimension = [80, 100]
x_train = []
y_train = []
x_test = []
y_test = []
picture_file = [file for file in os.listdir(picture_path) if file.endswith(suffix)]  # 获取图片文件名列表
num_picture = len(picture_file)
for i in range(num_picture):  # 逐张读取图片数据
    picture = list(Image.open(picture_path + '\\' + picture_file[i]).getdata())
    y = [0] * num_people  # 创建数据标签
    y[i // num_picture_single] = 1
    if i % num_picture_single < num_train:
        x_train.append(picture)
        y_train.append(y)
    else:
        x_test.append(picture)
        y_test.append(y)
x_train, x_test, y_train, y_test = numpy.array(x_train), numpy.array(x_test), numpy.array(y_train), numpy.array(y_test)

# 将数据变为四维张量并归一化
x_train = x_train.reshape(x_train.shape[0], dimension[0], dimension[1], 1).astype('float32') / 255
x_test = x_test.reshape(x_test.shape[0], dimension[0], dimension[1], 1).astype('float32') / 255

# 构建模型
model = Sequential()  # 定义一个序贯模型
# 卷积层
model.add(Conv2D(filters=4, kernel_size=(3, 3), activation='relu', input_shape=(dimension[0], dimension[1], 1)))
# 池化层
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Flatten())  # 一维化数据
# 全连接层
model.add(Dense(num_people, activation='softmax'))  # 添加一个全连接层，输出大小为人数，激活函数为softmax
# 编译模型
model.compile(loss='categorical_crossentropy',  # 使用交叉熵作为损失函数
              optimizer='adam',  # 使用adam优化器进行训练
              metrics=['accuracy'])  # 在训练过程中监测模型的精度
# 训练模型
history = model.fit(x_train, y_train,  # 输入训练数据和标签
                    batch_size=128, epochs=20,  # 分批次训练，每个批次包含128个样本
                    verbose=1, validation_data=(x_test, y_test))  # 显示训练过程
# 使用测试集进行验证
# 评估模型
score = model.evaluate(x_test, y_test, verbose=0)  # 对模型进行测试并返回测试误差和测试准确率
# 输出测试准确率
print('Test accuracy:', score[1])
