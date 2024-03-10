import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(200);
        try {
            ServerSocket serverSocket = new ServerSocket(8888, 10000);
            while (true) {
                Socket client = serverSocket.accept();
                executorService.execute(new TheadPoolTask(client));
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}

class TheadPoolTask implements Runnable {
    private Socket socket = null;
    private final String cookie = "Set-Cookie: " + new Date() + "\r\n";

    private static final String html = "<!DOCTYPE html>\n" +
            "<html>\n" +
            "<head>\n" +
            "\t<title>叶茂林的网站</title>\n" +
            "\t<style>\n" +
            "\t.block{\n" +
            "\tdisplay:inline-block;\n" +
            "\t}\n" +
            "\t</style>\n" +
            "</head>\n" +
            "<body>\n" +
            "\t<h1>叶茂林的互联网编程实验4专用测试网站</h1>\n" +
            "\t<div class=\"block\">\n" +
            "\t<p>这是一张用来测试的图片。</p>\n" +
            "\t<img src=\"C:\\Users\\Yezi\\Desktop\\互联网编程\\实验4传输协议与套接字应用编程\\web\\go.jpg\" text=\"\">\n" +
            "\t</div>\n" +
            "\t<div class=\"block\">\n" +
            "\t<p>这是一个本测试网站的HTML文件。</p>\n" +
            "\t<object data=\"C:\\Users\\Yezi\\Desktop\\互联网编程\\实验4传输协议与套接字应用编程\\web\\index.html\" width=\"300\" height=\"300\"></object>\n" +
            "\t</div>\n" +
            "</body>\n" +
            "</html>";

    public TheadPoolTask(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            while (!socket.isClosed()) {
                BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String request = input.readLine();
                System.out.println(request);
                String method = request.split(" ")[0];
                String path = request.split(" ")[1];
                while (!Objects.equals(request = input.readLine(), "Done!!!")) {
                    System.out.println(request);
                }
                if (method.equals("GET")) {
                    GET(path); // 处理GET请求
                } else if (method.equals("HEAD")) {
                    HEAD();  // 处理HEAD请求
                } else {
                    POST();  // 处理POST请求
                }
            }
            socket.close();
        } catch (IOException error) {
            error.printStackTrace();
        }
    }

    private void GET(String path) throws IOException {
        //发送响应
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html";
        writer.println(response);
        writer.println(cookie);
        writer.println("Done!!!");
        //发送文件
        String mainPath = "C:\\Users\\Yezi\\Desktop\\互联网编程\\实验4传输协议与套接字应用编程\\web\\";
        File file = new File(mainPath + path.substring(path.lastIndexOf('/') + 1));
        InputStream input = new FileInputStream(file);
        OutputStream output = socket.getOutputStream();
        new DataOutputStream(output).writeLong(file.length());
        int one;
        while ((one = input.read()) != -1) {
            output.write(one);
        }
    }

    private void HEAD() throws IOException {
        //发送响应
        String response = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: text/html";
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(response);
        writer.println(cookie);
        writer.println("Done!!!");
    }

    private void POST() throws IOException {
        String response = "HTTP/1.1 200 OK";
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(response);
        writer.println(cookie);
        writer.println("Done!!!");
    }
}