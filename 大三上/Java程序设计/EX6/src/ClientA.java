import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClientA {
    public static void main(String[] args) throws IOException {
        DatagramSocket clientSocket = new DatagramSocket(6666); // 创建客户端套接字
        Scanner scanner = new Scanner(System.in);
        Thread receive = new Thread(() -> {
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                try {
                    clientSocket.receive(receivePacket); // 接收服务器响应
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String serverResponse = new String(receivePacket.getData()).trim(); // 解析服务器响应
                System.out.println("[收到消息]" + serverResponse);
            }
        });
        receive.start();
        while (true) {
            String message = "A: " + scanner.nextLine();
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getLocalHost(), 8888);
            clientSocket.send(sendPacket); // 发送消息给服务器
        }
    }
}
