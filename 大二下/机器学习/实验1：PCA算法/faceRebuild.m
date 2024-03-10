pictures=dir('C:\Users\Yezi\Desktop\机器学习\face10080\*.bmp');
sample=[];
for i=1:length(pictures)
    picture=imread("C:\Users\Yezi\Desktop\机器学习\face10080\"+pictures(i).name);
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

oneFace=sample(:,1);
for dimension=20:20:160
    egienvector=egienvectors(:,1:dimension);
    rebuildFace=egienvector*(egienvector'*oneFace);
    rebuildFace=reshape(rebuildFace,100,80);
    index=dimension/20;
    subplot(2,4,index);
    imshow(mat2gray(rebuildFace));
    xlabel(sprintf("dimension=%d",dimension));
end