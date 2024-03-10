#include <iostream>
#include <string>
using namespace std;

#define ERROR 0
#define OK 1

template<class ElemType>
struct  Stack_Node
{
	ElemType   data;		// 数据
	Stack_Node  *next;		// 下一个节点
};

template<class ElemType>
class LinkStack
{
	Stack_Node<ElemType>* top;	// 头节点 
public:
	// 构造函数
	LinkStack()
	{
		top = new Stack_Node<ElemType>;
		top->next = NULL;
	}
	// 析构函数
	~LinkStack()
	{
		delete top;
	}
	// 将元素压入栈顶 
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
	// 弹出栈顶元素
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
	// 访问栈顶元素
	int getTop(ElemType &e)
	{
		Stack_Node<ElemType>  *p;
		if (top->next == NULL)
			return ERROR;
		p = top->next;
		e = p->data;
		return OK;
	}
	// 判断是否为空
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
	cout << "要插入的元素个数：";
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
