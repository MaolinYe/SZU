import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Objects;

public class CodeWriter {
    private final FileWriter asm;
    private String asmCommand;
    private final HashMap<String, String> vmToAsm = new HashMap<>();
    private int jump = 0;

    public CodeWriter(File file) throws IOException {
        asm = new FileWriter(file);
        String fetch = "@SP\nM=M-1\nA=M\nD=M\nA=A-1\n";
        vmToAsm.put("add", fetch + "M=M+D\n");
        vmToAsm.put("sub", fetch + "M=M-D\n");
        vmToAsm.put("and", fetch + "M=M&D\n");
        vmToAsm.put("or", fetch + "M=M|D\n");
        vmToAsm.put("gt", fetch + "D=M-D\n@TRUE\nD;JGT\n@SP\nA=M-1\nM=0\n@END\n0;JMP\n(TRUE)\n@SP\nA=M-1\nM=-1\n(END)\n");
        vmToAsm.put("eq", fetch + "D=M-D\n@TRUE\nD;JEQ\n@SP\nA=M-1\nM=0\n@END\n0;JMP\n(TRUE)\n@SP\nA=M-1\nM=-1\n(END)\n");
        vmToAsm.put("lt", fetch + "D=M-D\n@TRUE\nD;JLT\n@SP\nA=M-1\nM=0\n@END\n0;JMP\n(TRUE)\n@SP\nA=M-1\nM=-1\n(END)\n");
        vmToAsm.put("neg", "D=0\n@SP\nA=M-1\nM=D-M\n");
        vmToAsm.put("not", "@SP\nA=M-1\nM=!M\n");
    }

    public void writeArithmetic(String vmCommand) throws IOException {
        asmCommand = vmToAsm.get(vmCommand);
        if (Objects.equals(vmCommand, "gt") || Objects.equals(vmCommand, "eq") || Objects.equals(vmCommand, "lt")) {
            asmCommand = asmCommand.replaceAll("TRUE", "TRUE" + jump);
            asmCommand = asmCommand.replaceAll("END", "END" + jump);
            jump++;
        }
        asm.write(asmCommand);
    }

    public void writePushPop(String cmd, String segment, int index) throws IOException {
        if (Objects.equals(cmd, "C_PUSH")) {
            if (Objects.equals(segment, "constant")) {
                asmCommand = "@" + index + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "local")) {
                asmCommand = "@LCL\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "argument")) {
                asmCommand = "@ARG\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "this")) {
                asmCommand = "@THIS\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "that")) {
                asmCommand = "@THAT\nD=M\n@" + index + "\nA=D+A\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "temp")) {
                asmCommand = "@" + (5 + index) + "\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            } else if (Objects.equals(segment, "pointer")) {
                if (index == 0) {
                    asmCommand = "@THIS\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                } else {
                    asmCommand = "@THAT\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
                }
            } else if (Objects.equals(segment, "static")) {
                asmCommand = "@" + (16 + index) + "\nD=M\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
            }
        } else {
            if (Objects.equals(segment, "local")) {
                asmCommand = "@LCL\nD=M\n@" + index + "\nD=D+A\n@255\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@255\nA=M\nM=D\n";
            } else if (Objects.equals(segment, "argument")) {
                asmCommand = "@ARG\nD=M\n@" + index + "\nD=D+A\n@255\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@255\nA=M\nM=D\n";
            } else if (Objects.equals(segment, "this")) {
                asmCommand = "@THIS\nD=M\n@" + index + "\nD=D+A\n@255\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@255\nA=M\nM=D\n";
            } else if (Objects.equals(segment, "that")) {
                asmCommand = "@THAT\nD=M\n@" + index + "\nD=D+A\n@255\nM=D\n@SP\nM=M-1\nA=M\nD=M\n@255\nA=M\nM=D\n";
            } else if (Objects.equals(segment, "temp")) {
                asmCommand = "@SP\nM=M-1\nA=M\nD=M\n@" + (5 + index) + "\nM=D\n";
            } else if (Objects.equals(segment, "pointer")) {
                if (index == 0) {
                    asmCommand = "@SP\nM=M-1\nA=M\nD=M\n@THIS\nM=D\n";
                } else {
                    asmCommand = "@SP\nM=M-1\nA=M\nD=M\n@THAT\nM=D\n";
                }
            } else if (Objects.equals(segment, "static")) {
                asmCommand = "@SP\nM=M-1\nA=M\nD=M\n@" + (16 + index) + "\nM=D\n";
            }
        }
        asm.write(asmCommand);
    }

    public void close() throws IOException {
        asm.close();
    }
}
