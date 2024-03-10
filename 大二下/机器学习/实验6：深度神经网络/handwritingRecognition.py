from keras.datasets import mnist
from keras.models import Sequential
from keras.layers import Dense, Flatten, Conv2D, MaxPooling2D
from keras.utils import np_utils

# 加载手写体数据集，将数据集分为训练集和测试集
(x_train, y_train), (x_test, y_test) = mnist.load_data()
# 将数据变为四维张量并归一化
x_train = x_train.reshape(x_train.shape[0], 28, 28, 1).astype('float32') / 255
x_test = x_test.reshape(x_test.shape[0], 28, 28, 1).astype('float32') / 255
# 将类别数据转换为 one-hot 编码
y_train = np_utils.to_categorical(y_train, 10)
y_test = np_utils.to_categorical(y_test, 10)
# 构建模型
model = Sequential()  # 定义一个序贯模型
# 卷积层
model.add(Conv2D(filters=1, kernel_size=(3, 3), activation='relu', input_shape=(28, 28, 1)))
# 池化层
model.add(MaxPooling2D(pool_size=(2, 2)))
model.add(Flatten())  # 一维化数据
# 全连接层
model.add(Dense(10, activation='softmax'))  # 添加一个全连接层，输出大小为10，激活函数为softmax
# 编译模型
model.compile(loss='categorical_crossentropy',  # 使用交叉熵作为损失函数
              optimizer='adam',  # 使用adam优化器进行训练
              metrics=['accuracy'])  # 在训练过程中监测模型的精度
# 训练模型
history = model.fit(x_train, y_train,  # 输入训练数据和标签
                    batch_size=128, epochs=5,  # 分批次训练，每个批次包含128个样本
                    verbose=1, validation_data=(x_test, y_test))  # 显示训练过程
# 使用测试集进行验证
# 评估模型
score = model.evaluate(x_test, y_test, verbose=0)  # 对模型进行测试并返回测试误差和测试准确率
# 输出测试准确率
print('Test accuracy:', score[1])
