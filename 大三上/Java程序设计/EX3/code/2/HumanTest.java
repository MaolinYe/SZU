public class HumanTest {
    public static void main(String[] args) {
        Human[] humans={new Chinese(),new Japanese(),new English()};
        for(Human human:humans){
            human.sayHi();
        }
    }
}
