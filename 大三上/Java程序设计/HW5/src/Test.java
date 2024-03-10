public class Test {
    public static void function(Bird bird){
        bird.flying();
        bird.nesting();
        bird.eating();
        bird.singing();
    }
    public static void main(String[] args) {
        function(new Eagle());
        function(new Dove());
        function(new Sparrow());
    }
}
