import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<String> A = new HashSet<>();
        A.add("张三");
        A.add("李四");
        Set<String> B = new HashSet<>();
        B.add("李四");
        B.add("王五");
        Set<String> AB = new HashSet<>(A);
        AB.retainAll(B);
        System.out.println("A社团的人：" + A);
        System.out.println("B社团的人：" + B);
        System.out.println("同时是A和B社团的人：" + AB);
    }
}
