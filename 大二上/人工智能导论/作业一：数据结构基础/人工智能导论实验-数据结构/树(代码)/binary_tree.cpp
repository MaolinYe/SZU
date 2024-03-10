#include<iostream>
using namespace std;
#define Max_Node_Num 10     		//����������С 
struct BiNode {
	char data;								//�ɸ�����Ҫ�洢���������ͽ����޸�
	BiNode *lchild, *rchild;
};
class BiTree {
	private:
		BiNode *root;						//root�洢�������ĸ��ڵ�
		BiNode *Create(char *c, int i) {	//��˳��ṹ�洢������,���캯���ɸ������ı����ʽ�����޸�
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
		void PreOrderTraverse(BiNode* T) {	//�������
			if (T) {
				cout << T->data << " ";
				PreOrderTraverse(T->lchild);
				PreOrderTraverse(T->rchild);
			}
		}
		void InOrderTraverse(BiNode* T) {	//�������
			if (T) {
				InOrderTraverse(T->lchild);
				cout << T->data << " ";
				InOrderTraverse(T->rchild);
			}
		}
		void PostOrderTraverse(BiNode* T) {	//�������
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
		BiTree(char *c) {								//����˳��ṹ�洢������,���캯���ɸ������ı����ʽ�����޸�
			root = Create(c, 0);
		}
		BiNode *Getroot() {							//���ظ��ڵ�
			return root;
		}
		void PreTraverse() {							//�������
			PreOrderTraverse(root);
		}
		void InTraverse () {							//�������
			InOrderTraverse(root);
		}
		void PostTraverse() {							//�������
			PostOrderTraverse(root);
		}
		BiNode* FindBiNode(BiNode* Node, char _data) {	//����ֵΪ_data�Ľڵ㣬������ʧ�ܷ���NULL
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
		int Insert_leftchild(BiNode* p, char child_data) {	//ĳ���ڵ��в����������ӣ�����ʧ�ܷ���-1���ɹ�����1
			if (p->lchild != NULL)
				return -1;
			BiNode* T = new BiNode;
			T->data = child_data;
			T->lchild = NULL;
			T->rchild = NULL;
			p->lchild = T;
			return 1;
		}
		int Insert_rightchild(BiNode* p, char child_data) { //ĳ���ڵ��в��������Һ��ӣ�����ʧ�ܷ���-1���ɹ�����1
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
	cin >> a;												//��������˳��洢��0��ʾ�սڵ�,������ABCDEF000G���������֤��ȷ��
	BiTree p(a);
	cout << "������������"; 							//ABCDEF000G����������ΪABDEGCF
	p.PreTraverse();
	cout << endl;
	cout << "������������"; 							//ABCDEF000G����������ΪDBGEAFC
	p.InTraverse();
	cout << endl;
	cout << "������������"; 							//ABCDEF000G����������ΪDGEBFCA
	p.PostTraverse();
	cout << endl;
	BiNode* D_BiNode = p.FindBiNode(p.Getroot(), 'D');
	p.Insert_leftchild(D_BiNode, 'Q');
	BiNode* C_BiNode = p.FindBiNode(p.Getroot(), 'C');
	p.Insert_rightchild(C_BiNode, 'H');
	cout << "��D�ڵ��������Q��C�ڵ�����Һ���H������������Ϊ:" << endl;
	p.PreTraverse();									//���������������Ϊ:ABDQEGCFH
}
