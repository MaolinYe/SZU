#include <iostream>
#include <string>
using namespace std;

#define ERROR 0
#define OK 1

template<class ElemType>
struct QNode
{
	ElemType data;		// ����
	QNode *next;		// ��һ���ڵ�
};

template<class ElemType>
class LinkQueue {
	QNode<ElemType>* front;		// ��ͷָ�룬ʼ��ָ�����ͷԪ��
	QNode<ElemType>* rear;		// ��βָ�룬ʼ��ָ�����βԪ�� 
public:
	// ���캯��
	LinkQueue() 
	{
		front = new QNode<ElemType>;
		if (!front) 
			exit(ERROR);
		rear = front;
		front->next = NULL;
	}
	// ��������
	~LinkQueue() 
	{
		while (front)
		{
			rear = front->next;
			delete front;
			front = rear;
		}
	}
	// �ڶ���ĩβ����һ��Ԫ��
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
	// ���ʶ��еĵ�һ��Ԫ��
	int getFront(ElemType &e)
	{
		if (front == rear)
			return ERROR;
		e = front->next->data;
		return OK;
	}
	// ɾ�����еĵ�һ��Ԫ��
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
	// �ж��Ƿ�Ϊ��
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
	cout << "Ҫ�����Ԫ�ظ�����";
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

