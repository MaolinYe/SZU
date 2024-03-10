clc,clear;
load MatrixA.mat;
[m,n]=size(A);
Q=zeros(m,n);
R=zeros(n,n);
%% 修正Gram-Schmidt QR分解
Q(:,1)=A(:,1)/norm(A(:,1)); %定义第一个向量基准
R(1,1)=norm(A(:,1));
for k=2:n
     for i=k:n
         A(:,i)=A(:,i)-A(:,i)'*Q(:,k-1)*Q(:,k-1); 
     end
     R(k,k)=norm(A(:,k)); %求出R（K,K）
     Q(:,k)=A(:,k)/R(k,k); %求出标准正交化向量qk
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