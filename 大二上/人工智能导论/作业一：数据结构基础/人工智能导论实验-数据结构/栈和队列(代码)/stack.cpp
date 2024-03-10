#include <iostream>
#include <string>
using namespace std;

#define ERROR 0
#define OK 1

template<class ElemType>
struct  Stack_Node
{
	ElemType   data;		// ����
	Stack_Node  *next;		// ��һ���ڵ�
};

template<class ElemType>
class LinkStack
{
	Stack_Node<ElemType>* top;	// ͷ�ڵ� 
public:
	// ���캯��
	LinkStack()
	{
		top = new Stack_Node<ElemType>;
		top->next = NULL;
	}
	// ��������
	~LinkStack()
	{
		delete top;
	}
	// ��Ԫ��ѹ��ջ�� 
	int push(ElemType  e)
	{
		Stack_Node<ElemType>  *p;
		p = new Stack_Node<ElemType>;
		if (!p)
			return ERROR;
		p->data = e;
		p->next = top->next;
		top->next = p;
		return OK;
	}
	// ����ջ��Ԫ��
	int pop()
	{
		Stack_Node<ElemType>  *p;
		if (top->next == NULL)
			return ERROR;
		p = top->next;
		top->next = p->next;
		delete p;
		return OK;
	}
	// ����ջ��Ԫ��
	int getTop(ElemType &e)
	{
		Stack_Node<ElemType>  *p;
		if (top->next == NULL)
			return ERROR;
		p = top->next;
		e = p->data;
		return OK;
	}
	// �ж��Ƿ�Ϊ��
	bool empty()
	{
		if (top->next == NULL)
			return true;
		else
			return false;
	}
};


int main()
{
	LinkStack<string> s;
	int n;
	string str;
	cout << "Ҫ�����Ԫ�ظ�����";
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> str;
		s.push(str);
	}
	while (!s.empty())
	{
		s.getTop(str);
		s.pop();
		cout << str << " ";
	}
	cout << endl;
	return 0;
}
