import java.io.*;
import java.util.Scanner;

public class programOne {
    public static void main(String[] args)throws Exception {
        BufferedWriter output=new BufferedWriter(new FileWriter("C:\\Users\\Yezi\\Desktop\\互联网编程\\IOStream\\2021155015.txt"));
        System.out.println("请输入字符串的行数：");
        Scanner scanner=new Scanner(System.in);
        int lines= scanner.nextInt();
        int count=0;
        while(lines-->=0) {
            String string = scanner.nextLine();
            count=count+string.length();
            output.write(string);
        }
        output.write("This document contains "+count+" bytes in total.");
        output.flush();
        output.close();
    }
}
