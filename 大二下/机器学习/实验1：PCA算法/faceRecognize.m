pictures=dir('C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\face10080\*.bmp');
sample=[];
pictureNumber=length(pictures);
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\face10080\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end

meanFace=mean(sample);
meanFace=ones(size(sample,1),1)*meanFace;
sample=sample-meanFace;

covMatrix=sample*sample';

[egienvectors,diagonalMatrix]=eig(covMatrix);
egienvalues=diag(diagonalMatrix);

[egienvalues,order]=sort(egienvalues,'descend');
egienvectors=egienvectors(:,order);

trainNumber=5;
testNumber=6;
trainData=[];
testData=[];
for i=0:14
    trainData=[trainData,sample(:,i*11+1:i*11+trainNumber)];
end
for i=0:14
    testData=[testData,sample(:,i*11+trainNumber+1:i*11+11)];
end
result=[];
for knnK=1:8
    for dimension=10:10:160
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
end
X=10:10:160;
Y=1:8;
result=reshape(result,16,8);
result=result';
waterfall(X,Y,result);%不同k值不同维度的识别率
%plot(X,mean(result));%不同维度的平均识别率