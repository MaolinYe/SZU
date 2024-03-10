import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Scanner;

public class programTwo{
    public static void main(String[] args) throws UnknownHostException {
        Scanner scanner=new Scanner(System.in);
        String DNS=scanner.nextLine();
        int IP=1;
        char[] test=DNS.toCharArray();
        for(int i=0;i<DNS.length();i++){
            if(Character.isAlphabetic(test[i])){
                IP=0;
                break;
            }
        }
        if(IP==0){
            InetAddress[] address=InetAddress.getAllByName(DNS);
            for (InetAddress inetAddress : address) {
                System.out.println(inetAddress.getHostAddress());
            }
        }else{
            InetAddress address=InetAddress.getByName(DNS);
            System.out.println(address.getHostName());
        }
    }
}