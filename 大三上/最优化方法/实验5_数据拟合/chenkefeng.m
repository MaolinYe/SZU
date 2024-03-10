clear;
% 载入数据
Data = load("FittingData.mat");
x_data = Data.x;
y_data = Data.y;
% 约束点
x_constraint = 1;
y_constraint = 4;
% 设置多项式阶数
n = 2;
% 构建矩阵X
X = zeros(length(x_data), n + 1);
for i = 0:n
    X(:, i + 1) = x_data.^i;
end
% 构建矩阵Y
Y = y_data(:);
% 约束点的额外约束
c = zeros(1, n + 1);
for i = 0:n
    c(i + 1) = x_constraint^i;
end
% 利用最小二乘法求解系数，带有约束条件
coefficients = lsqlin(X, Y, [], [], c, y_constraint);
% 显示多项式系数
disp('拟合多项式的系数：');
disp(coefficients);
% 计算拟合多项式的值
x_fit = linspace(min(x_data), max(x_data), 100);
y_fit = zeros(size(x_fit));
for i = 0:n
    y_fit = y_fit + coefficients(i + 1) * x_fit.^i;
end
% 绘制结果
figure;
plot(x_data, y_data, 'o', x_fit, y_fit, '-')
hold on;
plot(x_constraint, y_constraint, 's', 'MarkerSize', 10, 'MarkerFaceColor', 'r') % 约束点
xlabel('x');
ylabel('y');
legend('观测数据', '拟合曲线', '约束点');
