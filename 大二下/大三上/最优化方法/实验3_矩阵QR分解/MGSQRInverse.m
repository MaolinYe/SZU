clc,clear;
load MatrixB.mat;
[m,n]=size(B);
Q=zeros(m,n);
R=zeros(n,n);
%% 修正Gram-Schmidt QR分解
Q(:,1)=B(:,1)/norm(B(:,1)); %定义第一个向量基准
R(1,1)=norm(B(:,1));
for k=2:n
     for i=k:n
         B(:,i)=B(:,i)-B(:,i)'*Q(:,k-1)*Q(:,k-1); 
     end
     R(k,k)=norm(B(:,k)); %求出R（K,K）
     Q(:,k)=B(:,k)/R(k,k); %求出标准正交化向量qk
end
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