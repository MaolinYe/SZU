class Bridge{
    public synchronized void cross(String person){
        System.out.print(person+',');
    }
}
class Person implements Runnable{
    Bridge bridge;
    String name;
    public Person(Bridge bridge,String name){
        this.bridge=bridge;
        this.name=name;
    }
    @Override
    public void run() {
        bridge.cross(name);
    }
}
public class CrossTheBridge {
    public static void main(String[] args) {
        Bridge bridge=new Bridge();
        for(int i=1;i<=20;i++){
            new Thread(new Person(bridge,"E"+i)).start();
            new Thread(new Person(bridge,"W"+i)).start();
        }
    }
}
