import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Service {
    public static void main(String[] args)throws Exception {
        ServerSocket serverSocket=new ServerSocket(6666);
        Socket socket=serverSocket.accept();
        File file=new File("C:\\Users\\Yezi\\Desktop\\互联网编程\\实验1\\Service");
        File[] files=file.listFiles();
        DataOutputStream data=new DataOutputStream(socket.getOutputStream());
        data.writeInt(files.length);
        for(int i=0;i<files.length;i++){
            data.writeUTF(files[i].getName());
            data.writeLong(files[i].length());
        }
        BufferedOutputStream output=new BufferedOutputStream(socket.getOutputStream());
        for(int i=0;i<files.length;i++){
            BufferedInputStream input=new BufferedInputStream(new FileInputStream("C:\\Users\\Yezi\\Desktop\\互联网编程\\实验1\\Service\\"+files[i].getName()));
            int one=input.read();
            while(one!=-1){
                output.write(one);
                one=input.read();
            }
        }
        output.close();
        socket.close();
        serverSocket.close();
    }
}