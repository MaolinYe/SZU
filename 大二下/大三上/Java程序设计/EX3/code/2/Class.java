abstract class Human{
    String name;
    Human(String name){
        this.name=name;
    }
    abstract double sayHi();
}
class Chinese extends Human{

    Chinese(String name) {
        super(name);
    }

    @Override
    double sayHi() {
        System.out.println("你好");
        return 0;
    }
}
class Japanese extends Human{

    Japanese(String name) {
        super(name);
    }

    @Override
    double sayHi() {
        System.out.println("こんにちは");
        return 0;
    }
}
class English extends Human {

    English(String name) {
        super(name);
    }

    @Override
    double sayHi() {
        System.out.println("Hi");
        return 0;
    }
}
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}