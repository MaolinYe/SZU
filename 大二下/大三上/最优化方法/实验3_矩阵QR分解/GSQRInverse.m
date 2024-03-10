clc,clear;
load MatrixB.mat;
[m,n]=size(B);
Q=zeros(m,n);
R=zeros(n,n);
%% Gram-Schmidt QR分解
for k=1:n
    R(1:k-1,k)=Q(:,1:k-1)'*B(:,k);  %求出R(1,K) - R(K-1,K)
    v=B(:,k)-Q(:,1:k-1)*R(1:k-1,k); %求出正交化向量q
    R(k,k)=norm(v);                 %求出R（K,K）
    Q(:,k)=v/R(k,k);                %单位化向量q
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