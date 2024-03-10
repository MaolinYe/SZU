import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\nand2tetris\\projects\\08\\FunctionCalls\\StaticsTest");
        ArrayList<File> vm = new ArrayList<>();
        CodeWriter codeWriter = new CodeWriter(new File("C:\\Users\\Yezi\\Desktop\\Java程序设计\\nand2tetris\\projects\\08\\FunctionCalls\\StaticsTest\\StaticsTest.asm"));
        codeWriter.writeInit();
        if (file.isFile()) {
            vm.add(file);
        } else {
            for (File one : Objects.requireNonNull(file.listFiles())) {
                if (one.getName().endsWith(".vm")) {
                    vm.add(one);
                }
            }
        }
        for (File one : vm) {
            Parser parser = new Parser(one);
            codeWriter.setFileName(one.getName());
            while (parser.hasMoreCommands()) {
                parser.advance();
                if (Objects.equals(parser.commandType(), "C_ARITHMETIC")) {
                    codeWriter.writeArithmetic(parser.arg1());
                } else if (Objects.equals(parser.commandType(), "C_LABEL")) {
                    codeWriter.writeLabel(parser.arg1());
                } else if (Objects.equals(parser.commandType(), "C_GOTO")) {
                    codeWriter.writeGoto(parser.arg1());
                } else if (Objects.equals(parser.commandType(), "C_IF")) {
                    codeWriter.writeIf(parser.arg1());
                } else if (Objects.equals(parser.commandType(), "C_FUNCTION")) {
                    codeWriter.writeFunction(parser.arg1(), parser.arg2());
                } else if (Objects.equals(parser.commandType(), "C_RETURN")) {
                    codeWriter.writeReturn();
                } else if (Objects.equals(parser.commandType(), "C_CALL")) {
                    codeWriter.writeCall(parser.arg1(), parser.arg2());
                } else {
                    codeWriter.writePushPop(parser.commandType(), parser.arg1(), parser.arg2());
                }
            }
            parser.close();
        }
        codeWriter.close();
    }
}