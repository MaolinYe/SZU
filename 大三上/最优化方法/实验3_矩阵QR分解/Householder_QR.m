clc,clear;
load MatrixA.mat;
[m,n]=size(A);
Q=zeros(m,n);
R=zeros(n,n);
%% Householder QR分解
[Q,R]=qr(A);    % matlab库函数就是用的Householder
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