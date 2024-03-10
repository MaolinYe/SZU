pictures=dir('C:\Users\Yezi\Desktop\机器学习\ORL56_46\*.bmp');
people=40;
personPictureNumber=10;
Dimension=56*46;
sample=[];
pictureNumber=length(pictures);
trainNumber=5;
testNumber=personPictureNumber-trainNumber;
trainData=[];
testData=[];
SVMresult=[];
KNNresult=[];
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\ORL56_46\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
    if mod(i,personPictureNumber)<trainNumber+1 && mod(i,personPictureNumber)~=0
        trainData=[trainData,picture];
    else
        testData=[testData,picture];
    end
end

% 计算权重矩阵W
distances=zeros(pictureNumber);

for i = 1:pictureNumber-1
    for j = i+1:pictureNumber
        distances(i,j)=norm(sample(:,i)-sample(:,j));
        distances(j,i)=distances(i,j);
    end
end
W = zeros(pictureNumber);
for i = 1:pictureNumber
    [~,index]=sort(distances(:,i));
    index=index(1:personPictureNumber);
    for j=1:personPictureNumber
        W(i,index(j))=1;
        W(index(j),i)=1;
    end
end

WW = eye(pictureNumber);
for i = 1:pictureNumber-1
    for j = i+1:pictureNumber
        if floor((i-1)/personPictureNumber)==floor((j-1)/personPictureNumber)
            WW(i,j) = 1;
            WW(j,i) = 1;
        end
    end
end
W=W.*WW;

% 计算度矩阵D和拉普拉斯矩阵L
D = diag(sum(W));
L = D - W;

[egienvectors,diagonalMatrix]=eig(sample*L*sample',sample*D*sample');
egienvalues=diag(diagonalMatrix);
[egienvalues,order]=sort(egienvalues,'ascend');
egienvectors=egienvectors(:,order);

halfDataNumber=pictureNumber/2;
Y=zeros(halfDataNumber,1);
for i=1:halfDataNumber
    Y(i)=floor((i-1)/testNumber)+1;
end

for dimension=5:5:160
    egienvector=egienvectors(:,1:dimension);
    trainDataTemp=egienvector'*trainData;
    testDataTemp=egienvector'*testData;
    right=0;
    model=fitcecoc(trainDataTemp',Y);
    YPredicted=predict(model,testDataTemp');
    for i=1:halfDataNumber
        if Y(i)==YPredicted(i)
            right=right+1;
        end
    end
    rate=right/halfDataNumber;
    SVMresult=[SVMresult,rate];
end

X=5:5:160;
plot(X,SVMresult);
writematrix(SVMresult,'LPP.xlsx');
knnK=1;
for dimension=5:5:160
    egienvector=egienvectors(:,1:dimension);
    trainDataTemp=egienvector'*trainData;
    testDataTemp=egienvector'*testData;
    error=0;
    testDataNumber=size(testDataTemp,2);
    trainDataNumber=size(trainDataTemp,2);
    for i=1:testDataNumber
        distances=[];
        for j=1:trainDataNumber
            distance=0;
            for k=1:dimension
                distance=distance+(testDataTemp(k,i)-trainDataTemp(k,j))^2;
            end
            distances=[distances,distance];
        end
        [distances,index]=sort(distances);
        rightIndex=floor((i-1)/testNumber)+1;
        testIndex=0;
        knn=[];
        for k=1:knnK
            knn=[knn,floor((index(k)-1)/trainNumber)+1];
        end
        [modeIndex,times]=mode(knn);
        if times==1
            testIndex=knn(1);
        else
            testIndex=modeIndex;
        end
        if testIndex~=rightIndex
            error=error+1;
        end
    end
    rate=(testDataNumber-error)/testDataNumber;
    KNNresult=[KNNresult,rate];
end
plot(X,KNNresult);
writematrix(KNNresult,'LPP.xlsx','Range','A2');