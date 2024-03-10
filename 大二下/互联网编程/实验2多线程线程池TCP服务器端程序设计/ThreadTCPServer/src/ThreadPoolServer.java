import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolServer {
    public static void main(String[] args) {
        ExecutorService executorService= Executors.newFixedThreadPool(200);
        try{
            ServerSocket serverSocket=new ServerSocket(9999,10000);
            while(true){
                Socket client=serverSocket.accept();
                executorService.execute(new TheadPoolTask(client));
                new Logger(client.getInetAddress().getHostAddress(),new Date(),"LogThreadPoolServer.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            executorService.shutdown();
        }
    }
}
class TheadPoolTask implements Runnable{
    private Socket socket=null;
    public TheadPoolTask(Socket socket){
        this.socket=socket;
    }
    public void run(){
        try{
            BufferedReader input=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message=null;
            while((message=input.readLine())!=null){
                System.out.println(message);
            }
            input.close();
            socket.close();

        }catch (IOException error){
            error.printStackTrace();
        }
    }
}