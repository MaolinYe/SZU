import java.io.DataInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;

public class CSS {
    public static void main(String[] args) throws IOException {
        URL url = new URL("https://www.szu.edu.cn/css/style.css");
        URLConnection urlConnection = url.openConnection();
        DataInputStream inputStream = new DataInputStream(urlConnection.getInputStream());
        int one;
        String css = "";
        while ((one = inputStream.read()) != -1) {
            css += (char) one;
        }
//        System.out.println(css);
        String pattern = "url\\(['\"]?(.*?)['\"]?\\)";
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(css);
        while (m.find()) {
            String imageUrl = m.group(1);
            imageUrl=imageUrl.substring(imageUrl.indexOf('/'));
            imageUrl="https://www.szu.edu.cn"+imageUrl;
            System.out.println(imageUrl);
            Download(new URL(imageUrl));
        }
    }
    public static void Download(URL url)  {
        try{
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
        } catch (FileNotFoundException e) {
            System.out.println(e);
        } catch (IOException e) {
            System.out.println(e);
        }

    }
}
