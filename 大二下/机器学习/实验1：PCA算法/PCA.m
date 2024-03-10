pictures=dir('C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\face10080\*.bmp');
sample=[];% 样本矩阵
for i=1:length(pictures)
    picture=imread("C:\Users\Yezi\Desktop\机器学习\实验1：PCA算法\face10080\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);% 单张图片拉成列向量
    sample=[sample,picture];
end
% PCA 主流程
meanFace=mean(sample); % 求样本均值
meanFace=ones(size(sample,1),1)*meanFace;% 矩阵化样本均值
sample=sample-meanFace; % 样本中心化：减去样本均值
covMatrix=sample*sample';% 求样本的协方差矩阵
[egienvectors,diagonalMatrix]=eig(covMatrix);% 协方差矩阵的特征值分解
egienvalues=diag(diagonalMatrix);% 取特征值
[egienvalues,order]=sort(egienvalues,'descend');% 特征值降序排序
egienvectors=egienvectors(:,order);% 将特征向量按特征值降序排序
