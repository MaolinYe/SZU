class BinaryNode:
    def __init__(self):
        self.data=None
        self.leftchild=None
        self.rightchild=None
class BinaryTree:
    def __init__(self,data):
        self.root=self.Creat(data)
    def Creat(self,data,i=0):
        if i>= len(data):
            return None
        if data[i]=='0':
            return None
        binarynode=BinaryNode()
        binarynode.data=data[i]
        binarynode.leftchild=self.Creat(data,2*i+1)
        binarynode.rightchild=self.Creat(data,2*i+2)
        return binarynode
    def PreOrderTraverse(self,binary=None):
        if binary:
            print(binary.data,end=' ')
            self.PreOrderTraverse(binary.leftchild)
            self.PreOrderTraverse(binary.rightchild)
    def InOrderTraverse(self,binary=None):
        if binary:
            self.InOrderTraverse(binary.leftchild)
            print(binary.data,end=' ')
            self.InOrderTraverse(binary.rightchild)
    def PostOrderTraverse(self,binary=None):
        if binary:
            self.PostOrderTraverse(binary.leftchild)
            self.PostOrderTraverse(binary.rightchild)
            print(binary.data,end=' ')
    def PreTraverse(self):
        self.PreOrderTraverse(self.root)
    def InTraverse(self):
        self.InOrderTraverse(self.root)
    def PostTraverse(self):
        self.PostOrderTraverse(self.root)
data="ABCDEF000F"
test=BinaryTree(data)
test.PreTraverse()
print()
test.InTraverse()
print()
test.PostTraverse()