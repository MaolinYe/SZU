import java.rmi.Naming;

public class Client {
    public static void main(String[] args) {
        try {
            // 使用Naming类使用存根产生一个远程对象的代理
            RemoteHttpService remoteHttpService = (RemoteHttpService) Naming.lookup("RemoteHttpService");
            String url = "https://szu.edu.cn";    // 指定要下载HTML的网站
            String htmlContent = remoteHttpService.downloadHtml(url); // 调用远程方法下载HTML
            System.out.println(htmlContent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
