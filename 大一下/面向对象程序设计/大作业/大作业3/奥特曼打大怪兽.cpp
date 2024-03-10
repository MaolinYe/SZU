#include <iostream>
using namespace std;
class Ultraman;
class Monster {
	protected:
		int rank, hp, damage, exp, money;
	public:
		Monster() {}
		Monster(int rank) : rank(rank), hp(20 * rank), damage(2 * rank), money(10 * rank), exp(10 * rank) {}
		void attacked(int hurt) {
			hp -= hurt;
		}
		virtual void fightback(Ultraman& me);
		virtual void lose() {
			if (hp <= 0)cout << "Boss!Help!" << endl;
		}
		virtual void display() {
			cout << "怪兽状态(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
		}
		int getrank() {
			return rank;
		}
		int getexp() {
			return exp;
		}
		int getHp() {
			return hp;
		}
		int getdamage() {
			return damage;
		}
		int getmoney() {
			return money;
		}
};
class Ultraman {
	protected:
		int rank, exp, hp, damage, money;
	public:
		Ultraman() {}
		Ultraman(int rank) : rank(rank), hp(10 * rank), damage(3 * rank), money(10 * rank), exp(0) {}
		int getrank() {
			return rank;
		}
		int getexp() {
			return exp;
		}
		int getHp() {
			return hp;
		}
		int getdamage() {
			return damage;
		}
		int getmoney() {
			return money;
		}
		void escape() {
			money = 0;
		}
		void attack(Monster& trash) {
			trash.attacked(damage);
		}
		void attacked(int hurt) {
			hp -= hurt / 2;
		}
		void restore() {
			if (hp > 10 && hp < 5 * rank)
				while (money >= 10 && hp < 10 * rank) {
					money -= 10;
					hp += 1;
				}
		}
		void win(Monster& trash) {
			money += trash.getmoney();
			exp += trash.getexp();
		}
		void upgrade() {
			while (exp >= 10 * rank) {
				exp -= 10 * rank;
				rank++;
				hp = 10 * rank;
				damage = 3 * rank;
			}
		}
		void display() {
			cout << "奥特曼状态(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
		}
		friend ostream& operator<<(ostream& out, Ultraman& me);
		Ultraman& operator++() {
			rank *= 2;
			hp = 10 * rank;
			damage = 3 * rank;
			exp *= 2;
			money *= 2;
			return *this;
		}
		Ultraman& operator--() {
			hp /= 2;
			damage /= 2;
			exp /= 2;
			money /= 2;
			return *this;
		}
		bool operator<(Monster& trash) {
			if (rank > trash.getrank()) {
				hp += trash.getHp() / 2;
				if (hp > rank * 10)
					hp = rank * 10;
				damage += trash.getdamage() / 2;
				exp += trash.getexp() / 2;
				money += trash.getmoney() / 2;
				return 1;
			}
			return 0;
		}
		bool operator==(Monster& trash) {
			if (rank == trash.getrank()) {
				hp += trash.getHp();
				if (hp > rank * 10)
					hp = rank * 10;
				damage += trash.getdamage();
				exp += trash.getexp();
				money += trash.getmoney();
				return 1;
			}
			return 0;
		}
};
ostream& operator<<(ostream& out, Ultraman& me) {
	out << "奥特曼状态(rank=" << me.rank << " hp=" << me.hp << " damage=" << me.damage << " exp=" << me.exp << " money=" << me.money << ')' << endl;
	return out;
}
void Monster::fightback(Ultraman& me) {
		me.attacked(damage);
}
class Boss : public Monster {
		int count = 0;
	public:
		Boss() : Monster(10) {
			hp = 300;
			exp = 1000;
			damage = 50;
			money = 1001;
		}
		void XP(Ultraman& me) {
			for (int i = 3; i > 0; i--)
				fightback(me);
		}
		virtual void fightback(Ultraman& me) {
			if (++count == 5) {
				count = 0;
				XP(me);
			} else
				me.attacked(damage);
		}
		virtual void lose() {
			if (hp <= 0)cout << "I'll be back!" << endl;
		}
		virtual void display() {
			cout << "Boss怪兽状态(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
		}
};
int main() {
	int ograd;
	cin >> ograd;
	Ultraman uobj(ograd);
	int t;
	cin >> t; //输入要打的怪兽个数数
	while (t-- && uobj.getHp() > 10) { //只要奥特曼没死也没逃跑，就继续与怪兽的战斗
		cout << uobj; //显示奥特曼对象当前状态
		Monster* p;
		char c = 0;
		while (1) {
			cin >> c;
			if (c == 'B') {
				p = new Boss();
				break;
			} else if (c == 'M') {
				int r;
				cin >> r;
				p = new Monster(r);
				break;
			} else cout << "请重新输入怪兽类型" << endl;
		}
		p->display();	//显示怪兽当前状态
		int pick;
		cin >> pick;//是否拾取魔法袋,非0拾取，0不拾取
		if (pick) {//如果拾取魔法袋
			while (1) {
				cin >> c;//输入要使用的魔法袋
				if (c == 'G') {
					cout << "****好事成双****" << endl;
					++uobj;
					cout << "****成功翻倍****" << endl;
					cout << uobj;
					break;
				} else if (c == 'B') {
					cout << "****祸不单行****" << endl;
					--uobj;
					cout << "****不幸减半****" << endl;
					cout << uobj;
					break;
				} else if (c == 'X') {
					cout << "****吸星大法****" << endl;
					if (uobj < (*p)) cout << "****大功告成****" << endl;
					else cout << "****尚未成功****" << endl;
					cout << uobj;
					break;
				} else if (c == 'Y') {
					cout << "****有缘相会****" << endl;
					if (uobj == (*p))cout << "****有缘有分****" << endl;
					else cout << "****无缘无分****" << endl;
					cout << uobj;
					break;
				} else cout << "请重新输入魔法袋类型" << endl;
			}
		}
		//设置战斗标志flag为true，启动战斗循环，具体如下：
		int flag = 1;
		while (flag) { //当战斗未结束就继续
			uobj.attack(*p);//奥特曼攻击1次
			if (p->getHp() > 0) { //怪兽没死
				p->fightback(uobj);//怪兽反击1次
				if (uobj.getHp() > 10)		//奥特曼生命值大于点，不逃跑
					uobj.restore();//奥特曼回血，在方法中判断是否需要加血
				else {	//奥特曼逃跑，并输出"lose"并回车
					uobj.escape();
					cout << "lose" << endl;
					uobj.display();//输出奥特曼状态
					flag = 0; //停止战斗
				}
			} else { //怪兽死了
				uobj.win(*p);	//奥特曼胜利，并输出"win"并回车
				cout << "win" << endl;
				uobj.upgrade();//奥特曼升级
				p->lose();//输出怪兽战败信息
				flag = 0; //停止战斗
			}
		}
	}
	return 0;
}
