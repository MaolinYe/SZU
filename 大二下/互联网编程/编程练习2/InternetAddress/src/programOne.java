import java.net.InetAddress;
import java.net.UnknownHostException;

public class programOne {
    public static void main(String[] args) throws UnknownHostException {
        InetAddress ip=InetAddress.getLocalHost();
        InetAddress[] address=InetAddress.getAllByName(ip.getHostName());
        for (InetAddress inetAddress : address) {
            System.out.println(inetAddress.getHostAddress());
        }
        System.out.println(ip);
        InetAddress szuIP=InetAddress.getByName("www.szu.edu.cn");
        System.out.println(szuIP);
        System.out.println(szuIP.getHostName());

    }
}
