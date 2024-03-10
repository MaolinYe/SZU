abstract class Bird {
    abstract void flying();
    abstract void nesting();
    abstract void eating();
    abstract void singing();
}
class Eagle extends Bird{
    @Override
    void flying() {
        System.out.println("Eagle is flying");
    }
    @Override
    void nesting() {
        System.out.println("Eagle is nesting");
    }
    @Override
    void eating() {
        System.out.println("Eagle is eating");
    }
    @Override
    void singing() {
        System.out.println("Eagle is singing");
    }
}
class Dove extends Bird{
    @Override
    void flying() {
        System.out.println("Dove is flying");
    }
    @Override
    void nesting() {
        System.out.println("Dove is nesting");
    }
    @Override
    void eating() {
        System.out.println("Dove is eating");
    }
    @Override
    void singing() {
        System.out.println("Dove is singing");
    }
}
class Sparrow extends Bird{
    @Override
    void flying() {
        System.out.println("Sparrow is flying");
    }
    @Override
    void nesting() {
        System.out.println("Sparrow is nesting");
    }
    @Override
    void eating() {
        System.out.println("Sparrow is eating");
    }
    @Override
    void singing() {
        System.out.println("Sparrow is singing");
    }
}