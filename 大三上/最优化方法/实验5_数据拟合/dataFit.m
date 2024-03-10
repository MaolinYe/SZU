clc,clear;
load FittingData.mat;
scatter(x,y);
hold on;
f=@(a,x) a(1)+a(2)*x+a(3)*x.^2;
n=2;
for i = 0:n
    X(:, i + 1) = x.^i;
end
a=lsqlin(X,y',[],[],[1,1,1],4);
plot(x,f(a,x));
hold on;
plot(1,4,'bo','MarkerFaceColor', 'r');
legend('观测数据', '拟合曲线', '约束点');