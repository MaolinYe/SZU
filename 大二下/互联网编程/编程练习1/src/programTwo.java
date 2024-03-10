import java.io.DataInputStream;
import java.io.FileInputStream;

public class programTwo {
    public static void main(String[] args)throws Exception{
        DataInputStream input=new DataInputStream(new FileInputStream("C:\\Users\\Yezi\\Desktop\\互联网编程\\IOStream\\2021155015.txt"));
        int one=input.read();
        while(one!=-1){
            if(one!=' ')
                System.out.print((char)one);
            one=input.read();
        }
    }
}
