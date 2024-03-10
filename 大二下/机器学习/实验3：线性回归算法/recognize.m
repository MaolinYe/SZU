clc,clear;
pictures=dir('C:\Users\Yezi\Desktop\机器学习\AR_Gray_50by40\*.tif');
people=120;
personPictureNumber=26;
sample=[];
pictureNumber=people*personPictureNumber;
testData=[];
trainData=[];
testNumber=13;
trainNumber=personPictureNumber-testNumber;
testDataNumber=testNumber*people;
trainDataNumber=trainNumber*people;
dimension=50*40;
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\AR_Gray_50by40\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
%     picture=usps1000(:,i);
    sample=[sample,picture];
    if mod(i,personPictureNumber)<trainNumber+1 && mod(i,personPictureNumber)~=0
        trainData=[trainData,picture];
    else
        testData=[testData,picture];
    end
end
Y=zeros(people,trainDataNumber);
for i=1:trainDataNumber
    Y(floor((i-1)/trainNumber)+1,i)=1;
end
% 岭回归
% W=(trainData*trainData'+eye(dimension)*4500000)^-1*trainData*Y';

% 经典线性回归
% W=pinv(trainData*trainData')*trainData*Y';

% 梯度下降法
% for i=1:trainDataNumber
%     meanmean=mean(trainData(:,i));
%     stdstd=std(trainData(:,i));
%     for j=1:dimension
%         trainData(j,i)=(trainData(j,i)-meanmean)/stdstd;
%     end
% end
% for i=1:testDataNumber
%     meanmean=mean(testData(:,i));
%     stdstd=std(testData(:,i));
%     for j=1:dimension
%         testData(j,i)=(testData(j,i)-meanmean)/stdstd;
%     end
% end
% W=zeros(dimension,people);
% a=0.000001;
% WTemp=W-2*a*trainData*(trainData'*W-Y');
% for i=1:1000
%     W=WTemp;
%     WTemp=W-2*a*trainData*(trainData'*W-Y');
% end
% W=WTemp;

% 套索回归
for i=1:trainDataNumber
    meanmean=mean(trainData(:,i));
    stdstd=std(trainData(:,i));
    for j=1:dimension
        trainData(j,i)=(trainData(j,i)-meanmean)/stdstd;
    end
end
for i=1:testDataNumber
    meanmean=mean(testData(:,i));
    stdstd=std(testData(:,i));
    for j=1:dimension
        testData(j,i)=(testData(j,i)-meanmean)/stdstd;
    end
end
W=zeros(dimension,people);
for i=1:people
    w=lasso(trainData',Y(i,:));
    W(:,i)=sum(w,2);
end

% 局部加权线性回归
% w=zeros(trainDataNumber);
% for i=1:trainDataNumber
%     x=mod(i-1,trainNumber);
%     w(i,i)=i/sqrt(2*pi)*exp(-x*x/2);
% end
% W=pinv(trainData*w*trainData')*trainData*w*Y';

% 我的操作
% w=zeros(trainDataNumber);
% for i=1:trainDataNumber
%     x=mod(i-1,trainNumber);
%     w(i,i)=i/sqrt(2*pi)*exp(-x*x/2);
% end
% W=(trainData*w*trainData'+eye(dimension)*10^-3)^-1*trainData*w*Y';

Predict=W'*testData;
[~,Index]=sort(Predict,'descend');
right=0;
for i=1:testDataNumber
    if Index(1,i)==floor((i-1)/testNumber)+1
        right=right+1;
    end
end
result=right/(testDataNumber);