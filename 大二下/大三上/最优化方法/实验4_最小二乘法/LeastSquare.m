clc,clear;
load Matrix_A_b.mat;
%% 正规方程QR分解求解
[Q,R]=qr(A);
exact=pinv(R)*Q'*b;
%% 梯度下降
iterations=1000;
f=zeros(1,iterations); %目标函数值
near=zeros(1,iterations); %相邻解的接近程度
mistake=zeros(1,iterations); %误差对比
x=zeros(40,1);
for k=1:iterations
%     f(1,k)=0.5*norm(A*x-b,2)^2; %目标函数计算
    p=A'*(A*x-b);
    a = norm(p,2)^2 / norm(A*p,2)^2; %最优步长计算
    y = x - a * p; %y为x（k+1）
%     near(1,k) = norm((x-y),2)/norm(x,2); %迭代解间的相对接近程度
    mistake(1,k) = norm((exact - x),2); %误差迭代
    x = y; %迭代
end
%% 画图
% figure(1);
% plot(f);
% title('目标函数值随迭代次数的变化');
% figure(2);
% plot(near);
% title('相邻迭代解的接近程度随迭代次数的变化');
figure(3);
plot(mistake);
title('准确解与近似解随迭代次数的变化');