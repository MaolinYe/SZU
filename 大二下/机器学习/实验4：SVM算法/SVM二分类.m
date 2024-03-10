% 生成随机数据
X = [randn(20,2)-2; randn(20,2)+2]; % 生成20个标签为-1的点和20个标签为1的点，分别在(-4,-4)和(4,4)的区域内
Y = [-1*ones(20,1); ones(20,1)]; % 将标签存入列向量y中

% 拟合SVM模型
svmModel = fitcsvm(X,Y); % 拟合线性SVM模型

% 绘制决策边界
w = svmModel.Beta; % 获取模型的权重向量w
b = svmModel.Bias; % 获取模型的偏置项b
a = -w(1)/w(2); % 计算决策边界的斜率
xx = linspace(-5,5); % 生成x坐标
yy = a*xx - b/w(2); % 计算决策边界的y坐标
margin = 1/sqrt(sum(w.^2)); % 计算间隔边界的宽度
yy_down = yy - sqrt(1+a^2)*margin; % 计算下界的y坐标
yy_up = yy + sqrt(1+a^2)*margin; % 计算上界的y坐标

% 绘制数据点和支持向量
figure; % 创建新的图形窗口
gscatter(X(:,1),X(:,2),Y,'br','x+'); % 绘制数据点，标签为-1的点用蓝色表示，标签为1的点用红色表示
hold on; % 将图形保持在当前状态，以便绘制其他图形
plot(xx,yy,'k-'); % 绘制决策边界
plot(xx,yy_down,'k--'); % 绘制间隔边界下界
plot(xx,yy_up,'k--'); % 绘制间隔边界上界
plot(svmModel.SupportVectors(:,1),svmModel.SupportVectors(:,2),'go','MarkerSize',10); % 绘制支持向量，用黑色圆点表示
axis([-5 5 -5 5]); % 设置坐标轴范围
legend('Class A','Class B','Decision boundary','Margin','Margin','Support vectors'); % 设置图例
hold off; % 关闭当前图形的保持状态，以便绘制其他图形
