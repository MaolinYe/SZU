pictures=dir('C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\ORL56_46\*.bmp');
sample=[];
for i=1:50
    picture=imread("C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\ORL56_46\"+pictures(i).name);
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

visualizeDataTemp=[];
%5个人
for i=0:4
    visualizeDataTemp=[visualizeDataTemp,sample(:,i*10+1:i*10+10)];
end

for dimension=2:3
    egienvector=egienvectors(:,1:dimension);
    visualizeData=egienvector'*visualizeDataTemp;
    colors=[];
    for i=1:50
        color=floor((i-1)/10+1)*20;
        colors=[colors,color];
    end
    if dimension==2
        scatter(visualizeData(1,:),visualizeData(2,:),[],colors);
    else
        scatter3(visualizeData(1,:),visualizeData(2,:),visualizeData(3,:),[],colors);
    end
end