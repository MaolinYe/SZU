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
			cout << "����״̬(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
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
			cout << "������״̬(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
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
	out << "������״̬(rank=" << me.rank << " hp=" << me.hp << " damage=" << me.damage << " exp=" << me.exp << " money=" << me.money << ')' << endl;
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
			cout << "Boss����״̬(rank=" << rank << " hp=" << hp << " damage=" << damage << " exp=" << exp << " money=" << money << ')' << endl;
		}
};
int main() {
	int ograd;
	cin >> ograd;
	Ultraman uobj(ograd);
	int t;
	cin >> t; //����Ҫ��Ĺ��޸�����
	while (t-- && uobj.getHp() > 10) { //ֻҪ������û��Ҳû���ܣ��ͼ�������޵�ս��
		cout << uobj; //��ʾ����������ǰ״̬
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
			} else cout << "�����������������" << endl;
		}
		p->display();	//��ʾ���޵�ǰ״̬
		int pick;
		cin >> pick;//�Ƿ�ʰȡħ����,��0ʰȡ��0��ʰȡ
		if (pick) {//���ʰȡħ����
			while (1) {
				cin >> c;//����Ҫʹ�õ�ħ����
				if (c == 'G') {
					cout << "****���³�˫****" << endl;
					++uobj;
					cout << "****�ɹ�����****" << endl;
					cout << uobj;
					break;
				} else if (c == 'B') {
					cout << "****��������****" << endl;
					--uobj;
					cout << "****���Ҽ���****" << endl;
					cout << uobj;
					break;
				} else if (c == 'X') {
					cout << "****���Ǵ�****" << endl;
					if (uobj < (*p)) cout << "****�󹦸��****" << endl;
					else cout << "****��δ�ɹ�****" << endl;
					cout << uobj;
					break;
				} else if (c == 'Y') {
					cout << "****��Ե���****" << endl;
					if (uobj == (*p))cout << "****��Ե�з�****" << endl;
					else cout << "****��Ե�޷�****" << endl;
					cout << uobj;
					break;
				} else cout << "����������ħ��������" << endl;
			}
		}
		//����ս����־flagΪtrue������ս��ѭ�����������£�
		int flag = 1;
		while (flag) { //��ս��δ�����ͼ���
			uobj.attack(*p);//����������1��
			if (p->getHp() > 0) { //����û��
				p->fightback(uobj);//���޷���1��
				if (uobj.getHp() > 10)		//����������ֵ���ڵ㣬������
					uobj.restore();//��������Ѫ���ڷ������ж��Ƿ���Ҫ��Ѫ
				else {	//���������ܣ������"lose"���س�
					uobj.escape();
					cout << "lose" << endl;
					uobj.display();//���������״̬
					flag = 0; //ֹͣս��
				}
			} else { //��������
				uobj.win(*p);	//������ʤ���������"win"���س�
				cout << "win" << endl;
				uobj.upgrade();//����������
				p->lose();//�������ս����Ϣ
				flag = 0; //ֹͣս��
			}
		}
	}
	return 0;
}
