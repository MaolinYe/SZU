public class Test {
    public static void main(String[] args) {
        String s="Java"; // 创建字符串对象s并赋值为"Java"
        StringBuilder builder=new StringBuilder(s); // 创建StringBuilder对象builder，并以字符串s初始化

        change(s,builder); // 调用change方法，传递字符串s和StringBuilder对象builder作为参数

        System.out.println(s); // 输出字符串s
        System.out.println(builder); // 输出StringBuilder对象builder
    }

    private static void change(String s,StringBuilder builder){ // 定义change方法，接受一个字符串和一个StringBuilder对象作为参数
        s=s+" and HTML"; // 将原字符串s与" and HTML"拼接后赋给s，但这里的s是方法内的局部变量，不会影响main方法中的s
        builder.append(" and HTML"); // 在StringBuilder对象builder后追加" and HTML"
    }
}
