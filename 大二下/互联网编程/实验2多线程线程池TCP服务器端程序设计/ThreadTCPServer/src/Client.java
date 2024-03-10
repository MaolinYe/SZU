import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Client {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        int scale = 1000;
        for (int i = 0; i < scale; i++) {
            executorService.execute(new Task(9999));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
        executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < scale; i++) {
            executorService.execute(new Task(8888));
        }
        executorService.shutdown();
        while (!executorService.isTerminated()) {
        }
    }
}

class Task implements Runnable {
    private final int port;

    public Task(int port) {
        this.port = port;
    }

    @Override
    public void run() {
        try {
            Socket socket = new Socket(InetAddress.getLocalHost(), port);
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            for (int i = 0; i < 1000; i++) {
                String message = "叶茂林的简单测试中……";
                output.write(message);
            }
            output.flush();
            output.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }
}
