import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    private String command = null;
    private final Scanner scanner;
    private String cmd0 = null;
    private String cmd1 = null;
    private int cmd2;

    public Parser(File file) throws FileNotFoundException {
        scanner = new Scanner(new FileReader(file));
    }

    public boolean hasMoreCommands() {
        boolean hasMore = false;
        while (scanner.hasNextLine()) {
            command = scanner.nextLine();
            command = command.replaceAll("\\s+", " "); //将连续的空格替换成单个空格
            if (!Objects.equals(command, "") && command.charAt(0) != '/') { //去掉空白行和注释
                String[] pure = command.split("/");
                command = pure[0];
                hasMore = true;
                break;
            }
        }
        return hasMore;
    }

    public void advance() {
        String[] cmd = command.split(" ");
        cmd0 = cmd[0];
        if (cmd.length > 1) {
            cmd1 = cmd[1];
            if (cmd.length > 2) {
                cmd2 = Integer.parseInt(cmd[2]);
            }
        }
    }

    public String commandType() {
        if (Objects.equals(cmd0, "push")) {
            return "C_PUSH";
        } else if (Objects.equals(cmd0, "pop")) {
            return "C_POP";
        } else if (Objects.equals(cmd0, "label")) {
            return "C_LABEL";
        } else if (Objects.equals(cmd0, "goto")) {
            return "C_GOTO";
        } else if (Objects.equals(cmd0, "if-goto")) {
            return "C_IF";
        } else if (Objects.equals(cmd0, "call")) {
            return "C_CALL";
        } else if (Objects.equals(cmd0, "function")) {
            return "C_FUNCTION";
        } else if (Objects.equals(cmd0, "return")) {
            return "C_RETURN";
        } else {
            cmd1 = cmd0;
            return "C_ARITHMETIC";
        }
    }

    public String arg1() {
        return cmd1;
    }

    public int arg2() {
        return cmd2;
    }

    public void close() {
        scanner.close();
    }

}
