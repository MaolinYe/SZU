clc,clear;
load MatrixB.mat;
[m,n]=size(B);
Q=zeros(m,n);
R=zeros(n,n);
%% Householder QR分解
[Q,R]=qr(B);    % matlab库函数就是用的Householder
%% 求逆
inverseQR=R\Q';
inverse=inv(B);
%% 画图比较
for i=0:n-1
    for j=1:n
        scatter(i*n+j,inverse(i+1,j),'red');
        hold on;
        scatter(i*n+j,inverseQR(i+1,j),'green','.');
        hold on;
    end
end