interface Human{
    double sayHi();
}
class Chinese implements Human {
    @Override
    public double sayHi() {
        System.out.println("你好");
        return 0;
    }
}
class Japanese implements Human {
    @Override
    public double sayHi() {
        System.out.println("こんにちは");
        return 0;
    }
}
class English implements Human {
    @Override
    public double sayHi() {
        System.out.println("Hi");
        return 0;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}