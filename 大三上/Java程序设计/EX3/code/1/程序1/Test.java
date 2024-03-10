public class Test {
    public static void main(String[] args) {
        // 判断字符串是否匹配正则表达式"ABC "
        System.out.println("Hi, ABC, good".matches("ABC "));
        // 判断字符串中是否包含"ABC"
        System.out.println("Hi, ABC, good".matches(".*ABC.*"));
        // 将字符串中的",;"替换为"#"
        System.out.println("A,B;C".replaceAll(",;", "#"));
        // 将字符串中的","或";"替换为"#"
        System.out.println("A,B;C".replaceAll("[,;]", "#"));
        // 使用正则表达式"[,;]"将字符串分割成多个部分
        String[] tokens = "A,B;C".split("[,;]");
        for (int i = 0; i < tokens.length; i++)
            System.out.println(tokens[i] + " ");
    }
}
