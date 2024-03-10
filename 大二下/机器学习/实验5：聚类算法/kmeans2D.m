mu=[0 0];
sigma=[1 1];
class1=mvnrnd(mu,sigma,10);
mu=[5 5];
class2=mvnrnd(mu,sigma,10);
mu=[10 10];
class3=mvnrnd(mu,sigma,10);
sample=[class1;class2;class3];

k=3;
sampleNumberAll=size(sample,1);
classSampleNumber=100;
color=zeros(sampleNumberAll,3);
classColor=[[255 0 0];[0 255 0];[0 0 255]];
class=zeros(1,k);
classCenter=[2 8;3 9;4 10];
figure(1);
hold on;
scatter(classCenter(:,1),classCenter(:,2),[],classColor,'filled');
scatter(sample(:,1),sample(:,2),'m');
for iterator=1:3
    nextCenter=[0 0;0 0;0 0];
    classNumber=[0 0 0];
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
    for i=1:k
        if classNumber(i)~=0
            classCenter(i,:)=nextCenter(i,:)/classNumber(i);
        end
    end
    figure(iterator+1);
    scatter(classCenter(:,1),classCenter(:,2),[],classColor,'filled');
    hold on;
    for i=1:sampleNumberAll
        if class(i)==1
            scatter(sample(i,1),sample(i,2),'r','s');
        elseif class(i)==2
            scatter(sample(i,1),sample(i,2),'g','d');
        else
            scatter(sample(i,1),sample(i,2),'b','h');
        end
    end
end
