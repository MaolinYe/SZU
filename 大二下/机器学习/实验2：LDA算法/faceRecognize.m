pictures=dir('C:\Users\Yezi\Desktop\机器学习\FERET_80\*.tif');
people=50;
personPictureNumber=7;
Dimension=80*80;
sample=[];
pictureNumber=length(pictures)/4;
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\FERET_80\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end

meanPerson=[];
meanTotal=mean(sample,2);
Sb=zeros(Dimension,Dimension);
for i=0:people-1
    temp=sample(:,i*personPictureNumber+1:(i+1)*personPictureNumber);
    one=mean(temp,2)-meanTotal;
    Sb=Sb+one*one';
    meanPerson=[meanPerson,mean(temp,2)]; 
end
Sb=Sb/people;
Sw=zeros(Dimension,Dimension);
for i=1:pictureNumber
    one=sample(:,i)-meanPerson(floor((i-1)/personPictureNumber)+1);
    Sw=Sw+one*one';
end
Sw=Sw/pictureNumber;

% 经典
% covMatrix=pinv(Sw)*Sb;
% 正则
Sb=Sb+eye(Dimension)*10^-6;
covMatrix=pinv(Sw)*Sb;
% 减法
% covMatrix=Sb-Sw;
% pca
% meanFace=mean(sample);
% meanFace=ones(size(sample,1),1)*meanFace;
% sample=sample-meanFace;
% covMatrix=sample*sample';


[egienvectors,diagonalMatrix]=eig(covMatrix);
egienvalues=diag(diagonalMatrix);

[egienvalues,order]=sort(egienvalues,'descend');
egienvectors=egienvectors(:,order);

trainNumber=4;
testNumber=3;
trainData=[];
testData=[];
for i=0:people-1
    trainData=[trainData,sample(:,i*personPictureNumber+1:i*personPictureNumber+trainNumber)];
end
for i=0:people-1
    testData=[testData,sample(:,i*personPictureNumber+trainNumber+1:i*personPictureNumber+personPictureNumber)];
end
result=[];
for knnK=1:1
    for dimension=1:1:10
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
X=1:1:10;
plot(X,result);