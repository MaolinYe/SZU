pictures=dir('C:\Users\Yezi\Desktop\机器学习\数据集\coil\*.png');
k=2;
dimension=2;
personPictureNumber=17;
Dimension=128*128;
sample=[];
pictureNumber=k*personPictureNumber;
for i=1:pictureNumber
    picture=imread("C:\Users\Yezi\Desktop\机器学习\数据集\coil\"+pictures(i).name);
    picture=double(picture);
    picture=picture(:);
    sample=[sample,picture];
end
pictures=sample;
% meanPerson=[];
% meanTotal=mean(sample,2);
% Sb=zeros(Dimension,Dimension);
% for i=0:k-1
%     temp=sample(:,i*personPictureNumber+1:(i+1)*personPictureNumber);
%     one=mean(temp,2)-meanTotal;
%     Sb=Sb+one*one';
%     meanPerson=[meanPerson,mean(temp,2)]; 
% end
% Sb=Sb/k;
% Sw=zeros(Dimension,Dimension);
% for i=1:pictureNumber
%     one=sample(:,i)-meanPerson(floor((i-1)/personPictureNumber)+1);
%     Sw=Sw+one*one';
% end
% Sw=Sw/pictureNumber;
% covMatrix=pinv(Sw)*Sb;
% PCA
meanFace=mean(sample);
meanFace=ones(size(sample,1),1)*meanFace;
sample=sample-meanFace;
covMatrix=sample*sample';
[egienvectors,diagonalMatrix]=eig(covMatrix);
egienvalues=diag(diagonalMatrix);
[egienvalues,order]=sort(egienvalues,'descend');
egienvectors=egienvectors(:,order);
egienvector=egienvectors(:,1:dimension);
sample=egienvector'*sample;
sample=sample';

sampleNumberAll=size(sample,1);
color=zeros(sampleNumberAll,k);
classColor=[[255 0 0];[0 255 0]];
class=zeros(1,k);
classCenter=[-12000 4000;-9000 2000];
figure(1);
scatter(classCenter(:,1),classCenter(:,2),[],classColor,'filled');
hold on;
scatter(sample(:,1),sample(:,2),'m');
iterator=0;
while(true)
    iterator=iterator+1;
    nextCenter=[0 0;0 0];
    classNumber=zeros(1,k);
    for i=1:sampleNumberAll
        distances=zeros(1,k);
        for j=1:k
            distances(j)=pdist2(sample(i,:),classCenter(j,:));
        end
        [~,index]=sort(distances);
        class(i)=index(1);
        classNumber(class(i))=classNumber(class(i))+1;
        nextCenter(class(i),:)=nextCenter(class(i),:)+sample(i,:);
    end
    temp=classCenter;
    for i=1:k
        if classNumber(i)~=0
            classCenter(i,:)=nextCenter(i,:)/classNumber(i);
        end
    end
    if temp==classCenter
        break
    end
end
figure(2);
scatter(classCenter(:,1),classCenter(:,2),[],classColor,'filled');
hold on;
for i=1:sampleNumberAll
    if class(i)==1
        scatter(sample(i,1),sample(i,2),'r','s','filled');
    elseif class(i)==2
        scatter(sample(i,1),sample(i,2),'g','d','filled');
    end
    hold on;
    picture=pictures(:,i);
    picture=reshape(picture,128,128);
    picture=imrotate(picture,180);
    colormap(gray(256));
    image('CData',picture,'XData',[sample(i,1)-150 sample(i,1)+150],'YData',[sample(i,2)-500+700 sample(i,2)+500+700]);
end