import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class StressTest {
    public static void main(String[] args) {
        int count=0;
        while(true){
            try{
                new Socket(InetAddress.getLocalHost(),8888);
                count++;
            } catch (IOException e) {
                System.out.println("Maximum capacity: "+count);
                System.exit(0);
                throw new RuntimeException(e);
            }
        }
    }
}
