import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class MultithreadingServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888, 10000);
            while (true) {
                Socket client = serverSocket.accept();
                new MultiThread(client).start();
                new Logger(client.getInetAddress().getHostAddress(), new Date(), "LogMultithreadingServer.txt");
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}

class MultiThread extends Thread {
    private Socket socket = null;

    public MultiThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message = null;
            while ((message = input.readLine()) != null) {
                System.out.println(message);
            }
            input.close();
            socket.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }
}