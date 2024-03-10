

pictures=dir('C:\Users\Yezi\Desktop\机器学习\face10080\*.bmp');
people=15;
personPictureNumber=11;
Dimension=100*80;
pictureNumber=people*personPictureNumber;
sample=[];
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\face10080\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end
% PCA
% meanFace=mean(sample);
% meanFace=ones(size(sample,1),1)*meanFace;
% sample=sample-meanFace;
% covMatrix=sample*sample';

% LDA
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

[egienvectors,diagonalMatrix]=eig(covMatrix);
egienvalues=diag(diagonalMatrix);
[egienvalues,order]=sort(egienvalues,'descend');
egienvectors=egienvectors(:,order);

oneFace=sample(:,1);
for dimension=1:1:8
    egienvector=egienvectors(:,1:dimension);
    rebuildFace=egienvector*(egienvector'*oneFace);
    rebuildFace=reshape(rebuildFace,100,80);
    subplot(2,4,dimension);
    imshow(mat2gray(rebuildFace));
    xlabel(sprintf("dimension=%d",dimension));
end