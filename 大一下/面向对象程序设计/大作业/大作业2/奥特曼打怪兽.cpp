#include"iostream"
using namespace std;
class Angel
{
	protected:
		int rank,damage,exp,money;
	public:
		Angel(int rank,int exp,int money):rank(rank),damage(rank/2),money(money),exp(exp){}
		int getexp(){return exp;}
		int getmoney(){return money;}
		int getdamage(){return damage;}
		void upgrade(int rank,int money,int exp){this->rank=rank;damage=rank/2;this->money=money;this->exp=exp;}
};
class Ultraman
{
	protected:
		int rank,exp,hp,damage,money;
		Angel angel;
	public:
		int getrank(){return rank;}
		int getexp(){return exp;}
		int gethp(){return hp;}
		int getdamage(){return damage;}
		int getmoney(){return money;}
		Ultraman(int rank):rank(rank),hp(10*rank),damage(3*rank),money(10*rank),exp(0),angel(rank,0,0){}
		void escape(){money=0;}
		void attack(){}
		void attacked(int hurt){hp-=hurt/2;}
		void restore()
		{
			if(hp>10&&hp<5*rank)
			while(money>=10&&hp<10*rank)
			{
				money-=10;
				hp+=1;
			}
		}
		void win(int monster_money,int monster_exp)
		{
			money+=monster_money;
			exp+=monster_exp;
		}
		void upgrade()
		{
			while(exp>=10*rank)
			{
				exp-=10*rank;
				rank++;
				angel.upgrade(rank,0,0);
				hp=10*rank;
				damage=3*rank;			
			}
		}
		void display(){cout<<"rank="<<rank<<" hp="<<hp<<" damage="<<damage<<" exp="<<exp<<" mongey="<<money<<endl;}
};
class Monster
{
	protected:
		int rank,hp,damage,exp,money;
	public:
		int getrank(){return rank;}
		int getexp(){return exp;}
		int gethp(){return hp;}
		int getdamage(){return damage;}
		int getmoney(){return money;}
		Monster(int rank):rank(rank),hp(20*rank),damage(2*rank),money(10*rank),exp(10*rank){}
		void attacked(int hurt){hp-=hurt;}	
		void fightback(){}
		void display(){cout<<"rank="<<rank<<" hp="<<hp<<" damage="<<damage<<" exp="<<exp<<" mongey="<<money<<endl;}		
};
class NMonster:public Monster
{
	public:
		NMonster(int rank):Monster(rank){hp=10*rank;}
};
class AMonster:public Monster
{
	protected:
		Angel angel;
	public:
		AMonster(int rank):Monster(rank),angel(rank,5*rank,5*rank){hp=5*rank;damage=4*rank;}
		int angel_exp(){return angel.getexp();}
		int angel_money(){return angel.getmoney();}
		int angel_damage(){return angel.getdamage();}
};
class DMonster:public Monster
{
	protected:
		Angel angel;	
	public:
		DMonster(int rank):Monster(rank),angel(rank,5*rank,5*rank){damage=rank;}
		int angel_exp(){return angel.getexp();}
		int angel_money(){return angel.getmoney();}		
};
class SMonster:public Monster
{
	public:
		SMonster(int rank):Monster(rank){hp=10*rank;damage=rank;money=20*rank;exp=20*rank;}
};
int main()
{
	int t,rank,i,mgrad[4];
	cin>>t;
	while(t--)
	{
		cin>>rank;
		Ultraman Outman(rank);
		Outman.display();
		for(i=0;i<4;i++)
		cin>>mgrad[i];
		NMonster normal(mgrad[0]);
		normal.display();
		SMonster snack(mgrad[1]);
		snack.display();
		DMonster defense(mgrad[2]);
		defense.display();
		AMonster attacker(mgrad[3]);
		attacker.display();
		bool flag=true,winflag1=0,winflag2=0,winflag3=0,winflag4=0,eflag;
		while(flag)
		{
			normal.attacked(Outman.getdamage());
			snack.attacked(Outman.getdamage());
			defense.attacked((Outman.getdamage()+2)/2);
			attacker.attacked(Outman.getdamage()+1);
			if(normal.gethp()>0)
			{
				Outman.attacked(normal.getdamage());
				if(Outman.gethp()>10)
				Outman.restore();
				else
				{
					eflag=true;
					cout<<"lose"<<endl;
					Outman.escape();
					Outman.display();
					flag=false;
					break;
				}
			}
			else if(winflag1==0)
			{
				cout<<"win"<<endl;
				Outman.win(normal.getmoney(),normal.getexp());
				Outman.upgrade();
				Outman.display();
				winflag1=true;
			}
			if(snack.gethp()>0)
			{
				Outman.attacked(snack.getdamage());
				if(Outman.gethp()>10)
				Outman.restore();
				else
				{
					eflag=true;
					cout<<"lose"<<endl;
					Outman.escape();
					Outman.display();
					flag=false;
					break;
				}
			}
			else if(winflag2==0)
			{
				cout<<"win"<<endl;
				Outman.win(snack.getmoney()*2,snack.getexp()*2);
				Outman.upgrade();
				Outman.display();
				winflag2=true;
			}			
			if(defense.gethp()>0)
			{
				Outman.attacked(defense.getdamage());
				if(Outman.gethp()>10)
				Outman.restore();
				else
				{
					eflag=true;
					cout<<"lose"<<endl;
					Outman.escape();
					Outman.display();
					flag=false;
					break;
				}
			}
			else if(winflag3==0)
			{
				cout<<"win"<<endl;
				Outman.win(defense.getmoney()+defense.angel_money(),defense.getexp()+defense.angel_exp());
				Outman.upgrade();
				Outman.display();
				winflag3=true;
			}			
			if(attacker.gethp()>0)
			{
				Outman.attacked(attacker.getdamage()+attacker.angel_damage());
				if(Outman.gethp()>10)
				Outman.restore();
				else
				{
					eflag=true;
					cout<<"lose"<<endl;
					Outman.escape();
					Outman.display();
					flag=false;
					break;
				}
			}
			else if(winflag4==0)
			{
				cout<<"win"<<endl;
				Outman.win(attacker.getmoney()+attacker.angel_money(),attacker.getexp()+attacker.angel_exp());
				Outman.upgrade();
				Outman.display();
				winflag4=true;
			}			
			if(winflag1&&winflag2&&winflag3&&winflag4)
			{
				flag=false;
				cout<<"win all"<<endl;
			}			
		}
	}
}
