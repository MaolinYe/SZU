import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class HttpClient {
    private static final int port = 8888;
    private static Socket socket = null;

    private static void GET(String path) throws IOException {
        //发送GET请求
        String request = "GET /index.html/" + path + " HTTP/1.1\r\n" +
                "Host: localhost:8888\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Connection: keep-alive\r\n";
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(request);
        writer.println("Done!!!");
        //接受服务器响应，并打印在控制台
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while (!Objects.equals(line = reader.readLine(), "Done!!!")) {
            System.out.println(line);
        }
        //接受文件，并保存在本地
        InputStream input = socket.getInputStream();
        long fileLength=new DataInputStream(input).readLong();
        String savePath = "C:\\Users\\Yezi\\Desktop\\互联网编程\\实验4传输协议与套接字应用编程\\";
        OutputStream output = new FileOutputStream(new File(savePath + path));
        for(long i=0;i<fileLength;i++){
            output.write(input.read());
        }
        output.close();
    }

    private static void HEAD() throws IOException {
        //发送HEAD请求
        String request = "HEAD /index.html HTTP/1.1\r\n" +
                "Host: localhost:8888\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/91.0.4472.77 Safari/537.36\r\n" +
                "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9\r\n" +
                "Accept-Encoding: gzip, deflate, br\r\n" +
                "Connection: keep-alive\r\n";
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(request);
        writer.println("Done!!!");
        //接受服务器响应，并打印在控制台
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while (!Objects.equals(line = reader.readLine(), "Done!!!")) {
            System.out.println(line);
        }
    }

    private static void POST() throws IOException {
        //发送POST请求
        String request = "POST /index.html HTTP/1.1\r\n" +
                "Host: localhost:8888\r\n" +
                "Content-Type: application/json\r\n" +
                "{\n" +
                "  \"name\": \"YeMaolin\",\n" +
                "  \"email\": \"2021155015@email.szu.edu.cn\",\n" +
                "  \"age\": 20\n" +
                "}\r\n";
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(request);
        writer.println("Done!!!");
        //接受服务器响应，并打印在控制台
        BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String line = null;
        while (!Objects.equals(line = reader.readLine(), "Done!!!")) {
            System.out.println(line);
        }
    }

    public static void main(String[] args) throws IOException {
        socket = new Socket(InetAddress.getLocalHost(), port);
        Scanner scanner = new Scanner(System.in);
        String method;
        while (!Objects.equals(method = scanner.nextLine(), "quit")) {
            if (Objects.equals(method, "HEAD")) {
                HEAD();
            } else if (Objects.equals(method, "POST")) {
                POST();
            } else {
                GET(method.split(" ")[1]);
            }
        }
        socket.close();
    }
}
