k=3;
Data_name=dir('C:\FERET_80\*.tif');
A=imread(Data_name(1).name);
A=double(A);
A=A(:);
[m,~]=size(A);
traindata=zeros(m,0);
trainlabel=zeros(1,0);
trainnumber=4;
testnumber=5;
for j=1:7:274
    for i=j:j+trainnumber-1
        A=imread(Data_name(i).name);
        A=double(A);
        A=A(:);
        traindata=[traindata,A];
        trainlabel=[trainlabel;j];
    end
end
for j=1:7:280
    for i=j+testnumber-1:j+5
        A=imread(Data_name(i).name);
        A=double(A);
        A=A(:);
        testdata=[testdata,A];
        testlabel=[testlabel;j];
    end
end
[~,Z]=size(testdata);
error=0;
for j=1:Z
    [N,A]=size(traindata);
    dist=zeros(A,1);
    for i=1:A
        Dist=0;
        for k=1:N
            Dist=Dist+(testdata(k,j)-traindata(k,i))^2;
        end
        dist(i,1)=Dist;
    end
    [~,B]=sort(dist);
    C=[];
    for i=1:K
        Q=floor((B(i,1)-1)/trainnumber)*7+1;
        C=[C,Q];
    end
    [idx,M]=mode(C);
    if M==1
        idx=C(1,1);
    end
    if idx~=testlabel(j,1)
        error=error+1;
    end
end
right=(Z-error)/Z;
fprintf('%.2f%%\n',right*100);
    
