import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Logger {
    private final String log;
    private final String filePath;
    public Logger(String IP, Date date,String filePath){
        log=IP+" login at "+date.toString()+'\n';
        this.filePath=filePath;
        keep();
    }
    private synchronized void keep(){
        try {
            BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(filePath,true));
            bufferedWriter.write(log);
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
