P=0:2:20;
out1=[];
out2=[];
out3=[];
T=3;
for p=0:2:20
    pw=10^(p/10);
    count1=0;
    count2=0;
    count3=0;
    for i=1:100000
        h1=randn+sqrt(-1)*randn;
        h2=randn(3,1)+sqrt(-1)*randn(3,1);
        h3=randn(3,3)+sqrt(-1)*randn(3,3);
        snr1=pw*abs(h1)^2;
        snr2=pw*norm(h2)^2;
        
        m=h3'*h3;
        [egienvectors,diagonalMatrix]=eig(m);
        egienvalues=diag(diagonalMatrix);
        [egienvalues,order]=sort(egienvalues,'descend');
        egienvectors=egienvectors(:,order);
        v=egienvectors(:,1);
        snr3=pw*norm(h3*v)^2;
        if snr1<T
            count1=count1+1;
        end
        if snr2<T
            count2=count2+1;
        end
        if snr3<T
            count3=count3+1;
        end
    end
    out1=[out1,count1/100000];
    out2=[out2,count2/100000];
    out3=[out3,count3/100000];
end
semilogy(P,out1);
grid on;
hold on;
semilogy(P,out2);
grid on;
hold on;
semilogy(P,out3);
grid on;
hold on;
legend('1','2','3');