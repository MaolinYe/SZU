function [Y]=pca(X)
testdata=testdata-ones(size(testdata,1),1)*mean(testdata);
c=testdata*testdata'/size(testdata,2);
[e,d]=eig(c);
[dummy,order]=sort(diag(-d));
e=e(:,order);
testdata=e'*testdata;
