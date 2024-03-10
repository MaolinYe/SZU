import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class Downloader {
    public void Download(String url,String path) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
        InputStream input=connection.getInputStream();
        File file=new File(path);
        OutputStream output=new FileOutputStream(file);
        int one;
        while((one=input.read())!=-1){
            output.write(one);
        }
        output.close();
        input.close();
    }
}
