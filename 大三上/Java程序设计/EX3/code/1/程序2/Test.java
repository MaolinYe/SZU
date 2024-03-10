public class Test {
    public static void main(String[] args) {
        String s = "Hi, Good Morning";
        System.out.println(m(s)); // 调用 m 方法并打印输出结果
    }

    public static int m(String s) {
        int count = 0; // 初始化计数器为0

        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) // 判断字符是否为大写字母
                count++; // 若是大写字母，则计数器加1
        }

        return count; // 返回计数结果
    }
}
