import java.util.ArrayList;
import java.util.List;

class SingleChoice {
    public String question, optionA, optionB, optionC, optionD, correctAnswer;

    public SingleChoice(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
}

class MultipleChoice {
    public String question, optionA, optionB, optionC, optionD, correctAnswer;

    public MultipleChoice(String question, String optionA, String optionB, String optionC, String optionD, String correctAnswer) {
        this.question = question;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.correctAnswer = correctAnswer;
    }
}

class TrueOrFalse {
    public String question;
    public boolean correctAnswer;

    public TrueOrFalse(String question, boolean correctAnswer) {
        this.question = question;
        this.correctAnswer = correctAnswer;
    }
}

public class Questions {

    public List<SingleChoice> singleChoices = new ArrayList<>();
    public List<MultipleChoice> multipleChoices = new ArrayList<>();
    public List<TrueOrFalse> trueOrFalse = new ArrayList<>();

    public Questions() {
        // 添加单选题
        singleChoices.add(new SingleChoice("Java中，下列哪个关键字用于声明一个类？", "A. int", "B. float", "C. class", "D. void", "C"));
        singleChoices.add(new SingleChoice("什么是面向对象编程的基本特征之一？", "A. 继承", "B. 多态", "C. 封装", "D. 以上皆是", "D"));
        singleChoices.add(new SingleChoice("在Java中，如何通过继承实现类之间的关系？", "A. extends关键字", "B. implements关键字", "C. super关键字", "D. this关键字", "A"));
        singleChoices.add(new SingleChoice("以下哪个关键字用于在Java中创建接口？", "A. interface", "B. class", "C. abstract", "D. extends", "A"));
        singleChoices.add(new SingleChoice("在Java中，下列哪个关键字用于控制程序的执行流程？", "A. if", "B. else", "C. switch", "D. for", "C"));
        singleChoices.add(new SingleChoice("在Java中，下列哪个关键字用于声明一个常量？", "A. const", "B. final", "C. static", "D. var", "B"));
        singleChoices.add(new SingleChoice("以下哪个不是Java的基本数据类型？", "A. int", "B. boolean", "C. double", "D. string", "D"));
        singleChoices.add(new SingleChoice("在Java中，哪个关键字用于创建一个新的对象？", "A. new", "B. create", "C. make", "D. build", "A"));
        singleChoices.add(new SingleChoice("以下哪个选项可以用于创建多线程？", "A. 哈希", "B. Thread类", "C. Main方法", "D. ArrayList类", "B"));
        singleChoices.add(new SingleChoice("以下哪个关键字可以用于定义抽象类？", "A. abstract", "B. interface", "C. static", "D. final", "A"));
        // 添加多选题
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java的基本数据类型？", "A. boolean", "B. string", "C. integer", "D. double", "AD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的流控制语句？", "A. if-else", "B. for", "C. while", "D. function", "ABC"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的包装类？", "A. Integer", "B. Float", "C. Long", "D. Char", "ABC"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的集合类？", "A. ArrayList", "B. LinkedList", "C. HashSet", "D. Map", "ABCD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的线程状态？", "A. NEW", "B. RUNNING", "C. WAITING", "D. DEAD", "ABCD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的线程同步方法？", "A. wait()", "B. notify()", "C. synchronized()", "D. interrupt()", "ABC"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的异常类型？", "A. Checked Exception", "B. Unchecked Exception", "C. Error", "D. Throwable", "ABCD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的注解类型？", "A. @Override", "B. @Deprecated", "C. @SuppressWarnings", "D. @Inject", "ABCD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的IO流类型？", "A. FileInputStream", "B. InputStreamReader", "C. OutputStream", "D. Socket", "ABCD"));
        multipleChoices.add(new MultipleChoice("下列哪些选项是Java中的网络通信协议？", "A. TCP", "B. UDP", "C. FTP", "D. HTTP", "ABCD"));
        //添加判断题
        trueOrFalse.add(new TrueOrFalse("Java是一种面向对象的编程语言", true));
        trueOrFalse.add(new TrueOrFalse("Java中的final关键字用于标识一个类不可被继承", true));
        trueOrFalse.add(new TrueOrFalse("Java中的String是基本数据类型", false));
        trueOrFalse.add(new TrueOrFalse("Java中的数组长度可以动态改变", false));
        trueOrFalse.add(new TrueOrFalse("Java中的interface可以实现多重继承", true));
        trueOrFalse.add(new TrueOrFalse("Java中的double类型比float类型精度更高", true));
        trueOrFalse.add(new TrueOrFalse("Java中的HashMap是线程安全的", false));
        trueOrFalse.add(new TrueOrFalse("Java中的try块必须和catch或finally块配合使用", true));
        trueOrFalse.add(new TrueOrFalse("Java中的抽象类可以有构造方法", true));
        trueOrFalse.add(new TrueOrFalse("Java中的静态方法可以被子类重写", false));
    }
}