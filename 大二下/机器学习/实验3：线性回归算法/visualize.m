% pictures=dir('C:\Users\Yezi\Desktop\机器学习\AR_Gray_50by40\*.tif');
people=10;
personPictureNumber=100;
sample=[];
pictureNumber=people*personPictureNumber;
testData=[];
trainData=[];
testNumber=50;
trainNumber=personPictureNumber-testNumber;
testDataNumber=testNumber*people;
trainDataNumber=trainNumber*people;
dimension=16*16;
for i=1:pictureNumber
%     picture=imread("C:\Users\Yezi\Desktop\机器学习\AR_Gray_50by40\"+pictures(i).name);
%     picture=double(picture);
%     picture=picture(:);
    picture=usps1000(:,i);
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

% W=pinv(trainData*trainData')*trainData*Y';

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
a=0.000001;
WTemp=W-2*a*trainData*(trainData'*W-Y');
for i=1:1000
    W=WTemp;
    WTemp=W-2*a*trainData*(trainData'*W-Y');
end
W=WTemp;

visualizeDataTemp=[];
%3个人
for i=0:2
    visualizeDataTemp=[visualizeDataTemp,sample(:,i*personPictureNumber+1:i*personPictureNumber+personPictureNumber)];
end
egienvector=W(:,1:2);
visualizeData=egienvector'*visualizeDataTemp;
colors=[];
for i=1:3*personPictureNumber
    color=floor((i-1)/personPictureNumber+1)*50;
    colors=[colors,color];
end
scatter(visualizeData(1,:),visualizeData(2,:),[],colors,'filled');