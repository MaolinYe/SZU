import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;

interface RemoteHttpService extends Remote {
    //定义远程方法，用于下载指定 URL 的 HTML 内容
    String downloadHtml(String url) throws RemoteException;
}

class RemoteHttpServiceImpl extends UnicastRemoteObject implements RemoteHttpService {
    public RemoteHttpServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public String downloadHtml(String url) throws RemoteException {
        StringBuilder content = new StringBuilder();
        try {
            // 使用 HttpURLConnection 发起 HTTP GET 请求
            URL urlObject = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();
            // 读取响应内容
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            reader.close();
            connection.disconnect();
            return content.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "Error downloading HTML from " + url;
        }
    }
}

public class HttpServer {
    public static void main(String[] args) {
        try {
            // 创建远程服务对象，启动RMI注册表，并将远程服务对象绑定到注册表
            RemoteHttpService remoteHttpService = new RemoteHttpServiceImpl();
            Naming.rebind("RemoteHttpService", remoteHttpService);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
