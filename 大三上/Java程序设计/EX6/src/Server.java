import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(8888); // 创建服务器端套接字
            System.out.println("服务器已启动...");
            while (true) {
                byte[] receiveData = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket); // 接收客户端消息
                String clientMessage = new String(receivePacket.getData()).trim(); // 解析接收到的消息
                System.out.println("[收到客户端消息]" + clientMessage);
                byte[] responseData = clientMessage.getBytes();
                int sendPort;
                if (clientMessage.startsWith("A"))
                    sendPort = 9999;
                else sendPort = 6666;
                DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length,
                        receivePacket.getAddress(), sendPort);
                serverSocket.send(responsePacket); // 发送响应消息给客户端
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
