A=[1,1,2,1;1,2,0,1;1,4,2,1;1,8,2,4];
b=[2;0;2;3];
A_b=[A,b];
[n,m]=size(A_b);
for i=1:n
    for j=m:-1:i
        A_b(i,j)=A_b(i,j)/A_b(i,i);
    end
    for j=i+1:n
        for k=m:-1:i
            A_b(j,k)=A_b(j,k)-A_b(j,i)*A_b(i,k);
        end
    end
    fprintf('第%d次消元\n',i);
    disp(rats(A_b));
end
for i=n-1:-1:1
    for j=i:-1:1
        A_b(j,m)=A_b(j,m)-A_b(j,i+1)*A_b(i+1,m);
        A_b(j,i+1)=0;
    end
    fprintf('第%d次回代\n',n-i);
    disp(rats(A_b));
end
