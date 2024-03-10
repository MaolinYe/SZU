#include<iostream>
using namespace std;
#define Max_Node_Num 10     		//输入的数组大小 
struct BiNode {
	char data;								//可根据需要存储的数据类型进行修改
	BiNode *lchild, *rchild;
};
class BiTree {
	private:
		BiNode *root;						//root存储整棵树的根节点
		BiNode *Create(char *c, int i) {	//据顺序结构存储建立树,构造函数可根据树的表达形式进行修改
			if (i >= Max_Node_Num)
				return NULL;
			if (c[i] == '0')
				return NULL;
			BiNode* T = new BiNode;
			T->data = c[i];
			T->lchild = Create(c, 2 * i + 1);
			T->rchild = Create(c, 2 * i + 2);
			return T;
		}
		void PreOrderTraverse(BiNode* T) {	//先序遍历
			if (T) {
				cout << T->data << " ";
				PreOrderTraverse(T->lchild);
				PreOrderTraverse(T->rchild);
			}
		}
		void InOrderTraverse(BiNode* T) {	//中序遍历
			if (T) {
				InOrderTraverse(T->lchild);
				cout << T->data << " ";
				InOrderTraverse(T->rchild);
			}
		}
		void PostOrderTraverse(BiNode* T) {	//后序遍历
			if (T) {
				PostOrderTraverse(T->lchild);
				PostOrderTraverse(T->rchild);
				cout << T->data << " ";
			}
		}
	public:
		BiTree() {
			root = NULL;
		}
		BiTree(char *c) {								//根据顺序结构存储建立树,构造函数可根据树的表达形式进行修改
			root = Create(c, 0);
		}
		BiNode *Getroot() {							//返回根节点
			return root;
		}
		void PreTraverse() {							//先序遍历
			PreOrderTraverse(root);
		}
		void InTraverse () {							//中序遍历
			InOrderTraverse(root);
		}
		void PostTraverse() {							//后序遍历
			PostOrderTraverse(root);
		}
		BiNode* FindBiNode(BiNode* Node, char _data) {	//查找值为_data的节点，若查找失败返回NULL
			BiNode* p = NULL;
			if (Node != NULL) {
				if (Node->data == _data)
					p = Node;
				else {
					if (p == NULL)
						p = FindBiNode(Node->lchild, _data);
					if (p == NULL)
						p = FindBiNode(Node->rchild, _data);
				}
			}
			return p;
		}
		int Insert_leftchild(BiNode* p, char child_data) {	//某个节点中插入他的左孩子，插入失败返回-1，成功返回1
			if (p->lchild != NULL)
				return -1;
			BiNode* T = new BiNode;
			T->data = child_data;
			T->lchild = NULL;
			T->rchild = NULL;
			p->lchild = T;
			return 1;
		}
		int Insert_rightchild(BiNode* p, char child_data) { //某个节点中插入他的右孩子，插入失败返回-1，成功返回1
			if (p->rchild != NULL)
				return -1;
			BiNode* T = new BiNode;
			T->data = child_data;
			T->lchild = NULL;
			T->rchild = NULL;
			p->rchild = T;
			return 1;
		}
};
int main() {
	char a[Max_Node_Num];
	cin >> a;												//输入树的顺序存储，0表示空节点,可以用ABCDEF000G这个例子验证正确性
	BiTree p(a);
	cout << "先序遍历结果："; 							//ABCDEF000G先序遍历结果为ABDEGCF
	p.PreTraverse();
	cout << endl;
	cout << "中序遍历结果："; 							//ABCDEF000G中序遍历结果为DBGEAFC
	p.InTraverse();
	cout << endl;
	cout << "后序遍历结果："; 							//ABCDEF000G后序遍历结果为DGEBFCA
	p.PostTraverse();
	cout << endl;
	BiNode* D_BiNode = p.FindBiNode(p.Getroot(), 'D');
	p.Insert_leftchild(D_BiNode, 'Q');
	BiNode* C_BiNode = p.FindBiNode(p.Getroot(), 'C');
	p.Insert_rightchild(C_BiNode, 'H');
	cout << "在D节点插入左孩子Q，C节点插入右孩子H后，先序遍历结果为:" << endl;
	p.PreTraverse();									//插入后先序遍历结果为:ABDQEGCFH
}
