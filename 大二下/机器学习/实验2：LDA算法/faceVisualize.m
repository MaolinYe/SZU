pictures=dir('C:\Users\Yezi\Desktop\机器学习\FERET_80\*.tif');
people=3;
personPictureNumber=7;
Dimension=80*80;
sample=[];
pictureNumber=people*personPictureNumber;
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\FERET_80\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end

% meanPerson=[];
% meanTotal=mean(sample,2);
% Sb=zeros(Dimension,Dimension);
% for i=0:people-1
%     temp=sample(:,i*personPictureNumber+1:(i+1)*personPictureNumber);
%     one=mean(temp,2)-meanTotal;
%     Sb=Sb+one*one';
%     meanPerson=[meanPerson,mean(temp,2)]; 
% end
% Sb=Sb/people;
% Sw=zeros(Dimension,Dimension);
% for i=1:pictureNumber
%     one=sample(:,i)-meanPerson(floor((i-1)/personPictureNumber)+1);
%     Sw=Sw+one*one';
% end
% Sw=Sw/pictureNumber;

% 经典
% covMatrix=pinv(Sw)*Sb;
% 正则
% Sb=Sb+eye(Dimension)*10^-6;
% covMatrix=pinv(Sw)*Sb;
% 减法
% covMatrix=Sb-Sw;
% pca
meanFace=mean(sample);
meanFace=ones(size(sample,1),1)*meanFace;
sample=sample-meanFace;
covMatrix=sample*sample';

[egienvectors,diagonalMatrix]=eig(covMatrix);
egienvalues=diag(diagonalMatrix);
[egienvalues,order]=sort(egienvalues,'descend');
egienvectors=egienvectors(:,order);

visualizeDataTemp=[];
%3个人
for i=0:2
    visualizeDataTemp=[visualizeDataTemp,sample(:,i*personPictureNumber+1:i*personPictureNumber+personPictureNumber)];
end

for dimension=2:3
    egienvector=egienvectors(:,1:dimension);
    visualizeData=egienvector'*visualizeDataTemp;
    colors=[];
    for i=1:21
        color=floor((i-1)/personPictureNumber+1)*50;
        colors=[colors,color];
    end
    if dimension==2
        scatter(visualizeData(1,:),visualizeData(2,:),[],colors,'filled');
    else
        scatter3(visualizeData(1,:),visualizeData(2,:),visualizeData(3,:),[],colors,'filled');
    end
end