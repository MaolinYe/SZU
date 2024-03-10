pictures=dir('C:\Users\Yezi\Desktop\机器学习\ORL56_46\*.bmp');
sample=[];
pictureNumber=length(pictures);
Dimension=56*46;
people=40;
personPictureNumber=10;
trainNumber=5;
testNumber=5;
trainData=[];
testData=[];
result=[];
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\ORL56_46\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end
for i=0:personPictureNumber-1
    trainData=[trainData,sample(:,i*personPictureNumber+1:i*personPictureNumber+trainNumber)];
end
for i=0:personPictureNumber-1
    testData=[testData,sample(:,i*personPictureNumber+trainNumber+1:(i+1)*personPictureNumber)];
end
meanFace=mean(sample);
meanFace=ones(size(sample,1),1)*meanFace;
sample=sample-meanFace;

% 计算紧密W
W = zeros(pictureNumber);
for i = 1:pictureNumber-1
    for j = i+1:pictureNumber
        if floor((i-1)/personPictureNumber)==floor((j-1)/personPictureNumber)
            W(i,j) = 1;
            W(j,i) = 1;
        end
    end
end

% 计算度矩阵D和拉普拉斯矩阵L
D = diag(sum(W));
L = D - W;


% 计算疏远WW
WW = ones(pictureNumber);
WW=WW-W-eye(pictureNumber);
% 计算度矩阵D和拉普拉斯矩阵L
DD = diag(sum(WW));
LL = DD - WW;

[egienvectors,diagonalMatrix]=eig(sample*L*sample',sample*LL*sample');
egienvalues=diag(diagonalMatrix);
[egienvalues,order]=sort(egienvalues,'ascend');
egienvectors=egienvectors(:,order);
%PCA
% covMatrix=sample*sample';
% [egienvectors,diagonalMatrix]=eig(covMatrix);
% egienvalues=diag(diagonalMatrix);
% [egienvalues,order]=sort(egienvalues,'descend');
% egienvectors=egienvectors(:,order);

knnK=1;
for dimension=10:10:100
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
    result=[result,rate];
end
X=10:10:100;
plot(X,result);