class Account{
    private int balance=10000;
    public synchronized void take(int money){
        if(money>balance){
            System.out.println("Your balance is insufficient");
        }else {
            balance=balance-money;
        }
        System.out.println("The balance is "+balance);
    }
}
class Take implements Runnable{
    private Account account;
    private int money;
    public Take(Account account,int money){
        this.account=account;
        this.money=money;
    }
    @Override
    public void run() {
        account.take(money);
    }
}
public class Bank {
    public static void main(String[] args) {
        Account account=new Account();
        for(int i=0;i<10;i++){
            new Thread(new Take(account,100)).start();
        }
    }
}
