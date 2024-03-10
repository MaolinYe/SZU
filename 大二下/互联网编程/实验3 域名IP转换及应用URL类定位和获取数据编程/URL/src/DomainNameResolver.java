import java.net.InetAddress;
import java.net.UnknownHostException;

public class DomainNameResolver {
    public String [] DomainNameResolution(String domainName) throws UnknownHostException {
        InetAddress[] inetAddress=InetAddress.getAllByName(domainName);
        String [] IP=new String[inetAddress.length];
        for(int i=0;i<IP.length;i++){
            IP[i]=inetAddress[i].getHostAddress();
        }
        return IP;
    }
}
