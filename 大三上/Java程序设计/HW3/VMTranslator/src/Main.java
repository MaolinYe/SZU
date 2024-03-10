import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        Parser parser=new Parser(new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\nand2tetris\\projects\\07\\MemoryAccess\\StaticTest\\StaticTest.vm"));
        CodeWriter codeWriter=new CodeWriter(new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\nand2tetris\\projects\\07\\MemoryAccess\\StaticTest\\StaticTest.asm"));
        while(parser.hasMoreCommands()){
            parser.advance();
            if(Objects.equals(parser.commandType(), "C_ARITHMETIC")){
                codeWriter.writeArithmetic(parser.arg1());
            }else{
                codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
            }
        }
        codeWriter.close();
        parser.close();
    }
}