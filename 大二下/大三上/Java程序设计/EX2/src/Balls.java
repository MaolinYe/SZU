abstract class Ball{
    private String name; //球类运动名称
    private String venue; //球类运动场地
    public Ball(String name,String venue){
        this.name=name;
        this.venue=venue;
    }
    protected void display(){
        System.out.println("name: "+name+" venue: "+venue);
    }
    public abstract void showKind();
}
class PingPong extends Ball{
    public PingPong(){
        super("乒乓球","乒乓球场");
    }

    @Override
    public void showKind() {
        System.out.println("PingPong: ");
        display();
    }
}
class Volleyball extends Ball{

    public Volleyball() {
        super("排球","排球场");
    }

    @Override
    public void showKind() {
        System.out.println("Volleyball: ");
        display();
    }
}
class Badminton extends Ball{

    public Badminton() {
        super("羽毛球", "羽毛球场");
    }

    @Override
    public void showKind() {
        System.out.println("Badminton: ");
        display();
    }
}
public class Balls {
    public static void main(String[] args) {
        PingPong pingPong = new PingPong();
        pingPong.showKind();
        Volleyball volleyball=new Volleyball();
        volleyball.showKind();
        Badminton badminton=new Badminton();
        badminton.showKind();
    }
}
