clc,clear;
load MatrixA.mat;
[m,n]=size(A);
Q=zeros(m,n);
R=zeros(n,n);
%% Gram-Schmidt QR分解
for k=1:n
    R(1:k-1,k)=Q(:,1:k-1)'*A(:,k);  %求出R(1,K) - R(K-1,K)
    v=A(:,k)-Q(:,1:k-1)*R(1:k-1,k); %求出正交化向量q
    R(k,k)=norm(v);                 %求出R（K,K）
    Q(:,k)=v/R(k,k);                %单位化向量q
end
%% 正交性偏差
figure(1);
E = zeros(1,n);
for k=2:n
    max = 0;
    for i=1:k-1
        temp = abs(Q(:,i)' *  Q(:,k));
        if temp > max
            max = temp;
        end
    end
    E(1,k)=max;
end    
plot(E)
%% 比较QTQ和I
QTQ=Q'*Q;
figure(2);
for i=1:n
    for j=1:n
        scatter3(i,j,QTQ(i,j),'red');
        hold on;
    end
end
zlim([0,1]);
I=eye(n);
figure(3);
for i=1:n
    for j=1:n
        scatter3(i,j,I(i,j),'red');
        hold on;
    end
end
zlim([0,1]);