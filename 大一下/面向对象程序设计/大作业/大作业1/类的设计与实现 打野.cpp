//类界面 
class hero//定义英雄类 
{
	private:
		int hp;//生命值 
		int rank;//等级 
		int exp;//经验 
		int damage;//伤害 
	public:
		void initial();//初始化 
		void Uprank();//升级 
		int Upexp();//经验增加 
		int Hp(int damage);//掉血 
		int Damage();//返回伤害值		
};
class monster//定义野怪 
{
	private:
		int hp;//生命值 
		int damage;//伤害 
	public:
		void initial();//初始化 
		int Hp(int damgage);//掉血 
		int Damage();//返回伤害值	
};
//类定义 
void hero::initial()//英雄初始化 
{
	hp=1000;
	rank=1;
	exp=0;
	damage=100;	
}
int hero::Upexp()//增加经验 
{
	return exp+=10;
}
void hero::Uprank()//升级 
{
	rank++;//等级提升 
	hp=hp+200;//生命值增加 
	damage=damage+50;//伤害强化 
}
int hero::Hp(int damage)//掉血 
{
	return hp=hp-damage;
}
int hero::Damage()//返回伤害值 
{
	return damage;
}
void monster::initial()//野怪初始化 
{
	hp=500;
	damage=30;
}
int monster::Hp(int damage)//掉血 
{
	return hp=hp-damage;
}
int monster::Damage()//返回伤害值 
{
	return damage;
}
//主函数 
int main()
{
	hero Jungle;//定义Jungle这个英雄 
	monster Boss;//定义boss这个野怪 
	Jungle.initial();
	Boss.initial();
	while(Jungle.Hp(Boss.Damage())||Boss.Hp(Jungle.Damage()))//开打直到其中一方挂掉 
	{
		if(Jungle.Upexp()%100==0)//打一次经验提升，经验每提升100就升一级 
		Jungle.Uprank();
	}		
} 
