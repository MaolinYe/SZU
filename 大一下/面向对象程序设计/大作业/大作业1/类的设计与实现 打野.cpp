//����� 
class hero//����Ӣ���� 
{
	private:
		int hp;//����ֵ 
		int rank;//�ȼ� 
		int exp;//���� 
		int damage;//�˺� 
	public:
		void initial();//��ʼ�� 
		void Uprank();//���� 
		int Upexp();//�������� 
		int Hp(int damage);//��Ѫ 
		int Damage();//�����˺�ֵ		
};
class monster//����Ұ�� 
{
	private:
		int hp;//����ֵ 
		int damage;//�˺� 
	public:
		void initial();//��ʼ�� 
		int Hp(int damgage);//��Ѫ 
		int Damage();//�����˺�ֵ	
};
//�ඨ�� 
void hero::initial()//Ӣ�۳�ʼ�� 
{
	hp=1000;
	rank=1;
	exp=0;
	damage=100;	
}
int hero::Upexp()//���Ӿ��� 
{
	return exp+=10;
}
void hero::Uprank()//���� 
{
	rank++;//�ȼ����� 
	hp=hp+200;//����ֵ���� 
	damage=damage+50;//�˺�ǿ�� 
}
int hero::Hp(int damage)//��Ѫ 
{
	return hp=hp-damage;
}
int hero::Damage()//�����˺�ֵ 
{
	return damage;
}
void monster::initial()//Ұ�ֳ�ʼ�� 
{
	hp=500;
	damage=30;
}
int monster::Hp(int damage)//��Ѫ 
{
	return hp=hp-damage;
}
int monster::Damage()//�����˺�ֵ 
{
	return damage;
}
//������ 
int main()
{
	hero Jungle;//����Jungle���Ӣ�� 
	monster Boss;//����boss���Ұ�� 
	Jungle.initial();
	Boss.initial();
	while(Jungle.Hp(Boss.Damage())||Boss.Hp(Jungle.Damage()))//����ֱ������һ���ҵ� 
	{
		if(Jungle.Upexp()%100==0)//��һ�ξ�������������ÿ����100����һ�� 
		Jungle.Uprank();
	}		
} 
