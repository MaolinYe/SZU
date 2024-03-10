import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import java.util.Vector;

public class hackAssembler {
    public static void main(String[] args) throws IOException {
        HashMap<String,Integer> Symbol=new HashMap<>(); //符号表
        Symbol.put("SP", 0);
        Symbol.put("LCL", 1);
        Symbol.put("ARG", 2);
        Symbol.put("THIS", 3);
        Symbol.put("THAT", 4);
        Symbol.put("SCREEN", 16384);
        Symbol.put("KBD", 24567);
        Symbol.put("R0", 0);
        Symbol.put("R1", 1);
        Symbol.put("R2", 2);
        Symbol.put("R3", 3);
        Symbol.put("R4", 4);
        Symbol.put("R5", 5);
        Symbol.put("R6", 6);
        Symbol.put("R7", 7);
        Symbol.put("R8", 8);
        Symbol.put("R9", 9);
        Symbol.put("R10", 10);
        Symbol.put("R11", 11);
        Symbol.put("R12", 12);
        Symbol.put("R13", 13);
        Symbol.put("R14", 14);
        Symbol.put("R15", 15);
        HashMap<String, String> Dest = new HashMap<>(); //目标位替换表
        Dest.put("null", "000");
        Dest.put("M", "001");
        Dest.put("D", "010");
        Dest.put("MD", "011");
        Dest.put("A", "100");
        Dest.put("AM", "101");
        Dest.put("AD", "110");
        Dest.put("AMD", "111");
        HashMap<String, String> Jmp = new HashMap<>(); //跳转位替换表
        Jmp.put("null", "000");
        Jmp.put("JGT", "001");
        Jmp.put("JEQ", "010");
        Jmp.put("JGE", "011");
        Jmp.put("JLT", "100");
        Jmp.put("JNE", "101");
        Jmp.put("JLE", "110");
        Jmp.put("JMP", "111");
        HashMap<String, String>Comp = new HashMap<>(); //计算位替换表
        // values in comp a=0
        Comp.put("0", "0101010");
        Comp.put("1", "0111111");
        Comp.put("-1", "0111010");
        Comp.put("D", "0001100");
        Comp.put("A", "0110000");
        Comp.put("!D", "0001111");
        Comp.put("!A", "0110001");
        Comp.put("-D", "0001111");
        Comp.put("D+1", "0011111");
        Comp.put("A+1", "0110111");
        Comp.put("D-1", "0001110");
        Comp.put("A-1", "0110010");
        Comp.put("D+A", "0000010");
        Comp.put("D-A", "0010011");
        Comp.put("A-D", "0000111");
        Comp.put("D&A", "0000000");
        Comp.put("D|A", "0010101");
        // values in comp a=1
        Comp.put("M", "1110000");
        Comp.put("!M", "1110001");
        Comp.put("-M", "1110011");
        Comp.put("M+1", "1110111");
        Comp.put("M-1", "1110010");
        Comp.put("D+M", "1000010");
        Comp.put("D-M", "1010011");
        Comp.put("M-D", "1000111");
        Comp.put("D&M", "1000000");
        Comp.put("D|M", "1010101");
        Scanner asm = new Scanner(new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\HW2\\nand2tetris\\projects\\06\\rect\\Rect.asm"));
        Vector<String>code=new Vector<>();
        while (asm.hasNextLine()) {
            String line = asm.nextLine();
            if (!Objects.equals(line, "") && line.charAt(0) != '/') { //去掉空白行和注释行
                String[] pure = line.split("/"); //去掉尾随代码的注释
                pure[0]=pure[0].replaceAll("\\s",""); //去掉空格
                if(pure[0].charAt(0)=='('){ //如果是标签符号，将之添加到符号表中
                    String label=pure[0].substring(1,pure[0].length()-1);
                    Symbol.put(label,code.size());
                }else{
                    code.add(pure[0]); //不是标签是代码
                }
            }
        }
        asm.close();
        FileWriter binary=new FileWriter(new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\HW2\\HackAssembler\\Rect.hack"));
        int start=16;
        for(String line:code){
            if(line.charAt(0)=='@'){ //A指令
                String address=line.substring(1); //取@后面的内容
                int number;
                if(Symbol.containsKey(address)){ // 出现过的符号，预定义符号、变量和标签
                    number=Symbol.get(address);
                }else if(address.matches("\\d+")){ //数字
                    number=Integer.parseInt(address); //取数字
                }else{ //首次出现的变量符号
                    number=start++;
                    Symbol.put(address,number);
                }
                String numberBinary=Integer.toBinaryString(number); //数字转二进制
                String zeros="0".repeat(16-numberBinary.length()); //补0齐16位
                binary.write(zeros+numberBinary+'\n');
            }else{ //C指令
                String dest="null";
                String jmp="null";
                String comp;
                String[] destCompJmp=line.split(";");
                if(destCompJmp[0].contains("=")){ // 有dest
                    String[] destComp=destCompJmp[0].split("=");
                    dest=destComp[0];
                    comp=destComp[1];
                }else{ //没有dest
                    comp=destCompJmp[0];
                }
                if(destCompJmp.length>1){ //有jmp
                    jmp=destCompJmp[1];
                }
                binary.write("111"+Comp.get(comp)+Dest.get(dest)+Jmp.get(jmp)+'\n');
            }
        }
        binary.close();
    }
}
