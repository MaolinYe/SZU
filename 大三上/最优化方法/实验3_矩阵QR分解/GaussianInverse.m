clc,clear;
load MatrixB.mat;
b=eye(50);
B_b=[B,b];
[n,m]=size(B_b);
for i=1:n
    for j=m:-1:i
        B_b(i,j)=B_b(i,j)/B_b(i,i);
    end
    for j=i+1:n
        for k=m:-1:i
            B_b(j,k)=B_b(j,k)-B_b(j,i)*B_b(i,k);
        end
    end
%     fprintf('第%d次消元\n',i);
%     disp(rats(A_b));
end
for i=n-1:-1:1
    for j=i:-1:1
        for k=m:-1:n+1
            B_b(j,k)=B_b(j,k)-B_b(j,i+1)*B_b(i+1,k);
        end
        B_b(j,i+1)=0;
    end
%     fprintf('第%d次回代\n',n-i);
%     disp(rats(A_b));
end
gaussInverse=B_b(:,end-49:end);
inverse=inv(B);
%% 画图比较
for i=0:n-1
    for j=1:n
        scatter(i*n+j,inverse(i+1,j),'red');
        hold on;
        scatter(i*n+j,gaussInverse(i+1,j),'green','.');
        hold on;
    end
end