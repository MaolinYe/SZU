import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < 10; i++) { // 循环连续测试10次
            String input = scanner.nextLine();
            StringBuilder uppercase = new StringBuilder(); // 用于存储大写字母
            StringBuilder lowercase = new StringBuilder(); // 用于存储小写字母
            StringBuilder digits = new StringBuilder(); // 用于存储数字
            for (int j = 0; j < input.length(); j++) {
                char ch = input.charAt(j);
                if (Character.isUpperCase(ch)) {
                    uppercase.append(ch);
                } else if (Character.isLowerCase(ch)) {
                    lowercase.append(ch);
                } else if (Character.isDigit(ch)) {
                    digits.append(ch);
                }
            }
            System.out.println("大写字母：" + uppercase.toString() + " 小写字母：" + lowercase.toString() + " 数字：" + digits.toString());
        }
    }
}
