import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Documented;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws IOException {
        URL url=new URL("https://www.szu.edu.cn/");
        URLConnection urlConnection=url.openConnection();
        DataInputStream inputStream=new DataInputStream(urlConnection.getInputStream());
        FileOutputStream fileOutputStream=new FileOutputStream("HTML.html");
        int one;
        String html="";
        while((one=inputStream.read())!=-1){
            fileOutputStream.write(one);
            html+=(char)one;
        }
        Pattern imgPattern = Pattern.compile("<img.*?>");
        Matcher imgMatcher = imgPattern.matcher(html);
        while (imgMatcher.find()) {
            Pattern srcPattern = Pattern.compile("src=\"(.*?)\"");
            Matcher srcMatcher = srcPattern.matcher(imgMatcher.group());
            if (srcMatcher.find()) {
                String imageUrl = srcMatcher.group(1);
                System.out.println(imageUrl);
                URL URL = new URL("https://www.szu.edu.cn/"+imageUrl);
                if(imageUrl!="")
                Download(URL);
            }
        }
    }
    public static void Download(URL url) throws IOException {
        String imageUrl=url.getPath();
        URLConnection urlConnection=url.openConnection();
        DataInputStream inputStream=new DataInputStream(urlConnection.getInputStream());
        imageUrl=imageUrl.substring(imageUrl.lastIndexOf('/')+1);
        System.out.println(imageUrl);
        FileOutputStream fileOutputStream=new FileOutputStream("image/"+imageUrl);
        int one;
        while((one=inputStream.read())!=-1){
            fileOutputStream.write(one);
        }
    }
}