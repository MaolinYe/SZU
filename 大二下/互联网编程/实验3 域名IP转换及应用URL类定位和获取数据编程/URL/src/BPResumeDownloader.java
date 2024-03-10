import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class BPResumeDownloader {
    public void Download(String url,String path) throws IOException {
        HttpURLConnection connection=(HttpURLConnection) new URL(url).openConnection();
        long startBytes=0;
        File file=new File(path);
        OutputStream output;
        if(file.exists()){
            startBytes=file.length();
            output=new FileOutputStream(file,true);
        }else{
            output=new FileOutputStream(file);
        }
        connection.setRequestProperty("Range","bytes="+startBytes+"-");
        InputStream input=connection.getInputStream();
        int one;
        while((one=input.read())!=-1){
            output.write(one);
        }
        output.close();
        input.close();
    }
}
