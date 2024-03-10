import java.io.IOException;
import java.net.*;
import java.util.concurrent.atomic.AtomicLong;

public class TestServerClient {
    public static void main(String[] args) {
        for (int power = 1; power <= 10; power++) {
            int scale = 2000*power ;
            for (int i = 0; i < 2000; i++) {
                if(i%2==0)
                new TestTask(9999).run();
                else new TestTask(8888).run();
            }
            System.out.printf("线程池：规模为%d消耗时间%d毫秒\n", scale/2, TestTask.timePool.get());
            System.out.printf("多线程：规模为%d消耗时间%d毫秒\n", scale/2, TestTask.timeMulti.get());
        }
    }
}

class TestTask{
    private final int port;
    public static AtomicLong timePool =new AtomicLong();
    public static AtomicLong timeMulti=new AtomicLong();

    public TestTask(int port) {
        this.port = port;
    }

    public void run() {
        try {
            Socket socket = new Socket();
            SocketAddress socketAddress=new InetSocketAddress(InetAddress.getLocalHost(), port);
            long start=System.currentTimeMillis();
            socket.connect(socketAddress);
            while(!socket.isConnected()){}
            socket.close();
            long end=System.currentTimeMillis();
            if(port==9999)
            timePool.addAndGet(end-start);
            else timeMulti.addAndGet(end-start);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
