#include <iostream>
#include <string>
using namespace std;

#define ERROR 0
#define OK 1

template<class ElemType>
struct QNode
{
	ElemType data;		// 数据
	QNode *next;		// 下一个节点
};

template<class ElemType>
class LinkQueue {
	QNode<ElemType>* front;		// 队头指针，始终指向队列头元素
	QNode<ElemType>* rear;		// 队尾指针，始终指向队列尾元素 
public:
	// 构造函数
	LinkQueue() 
	{
		front = new QNode<ElemType>;
		if (!front) 
			exit(ERROR);
		rear = front;
		front->next = NULL;
	}
	// 析构函数
	~LinkQueue() 
	{
		while (front)
		{
			rear = front->next;
			delete front;
			front = rear;
		}
	}
	// 在队列末尾插入一个元素
	int push(ElemType e)
	{
		if (!front) 
			return ERROR;
		QNode<ElemType> *p;
		p = new QNode<ElemType>;
		if (!p) 
			return ERROR;
		p->data = e;
		p->next = NULL;
		rear->next = p;
		rear = p;
		return OK;
	}
	// 访问队列的第一个元素
	int getFront(ElemType &e)
	{
		if (front == rear)
			return ERROR;
		e = front->next->data;
		return OK;
	}
	// 删除队列的第一个元素
	int pop()
	{
		if (front == rear)   
			return ERROR;
		QNode<ElemType> *p;
		p = front->next;
		front->next = p->next;
		if (rear == p) 	
			rear = front;
		else	
			delete p;
		return OK;
	}
	// 判断是否为空
	bool empty()
	{
		if (front == rear)
			return true;
		else
			return false;
	}
};


int main() {
	LinkQueue<string> q;
	int n;
	string str;
	cout << "要插入的元素个数：";
	cin >> n;
	for (int i = 0; i < n; i++)
	{
		cin >> str;
		q.push(str);
	}
	while (!q.empty())
	{
		q.getFront(str);
		q.pop();
		cout << str << " ";
	}
	cout << endl;
	return 0;
}

