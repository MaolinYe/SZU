import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client {
    public static void main(String[] args)throws Exception {
        Socket socket=new Socket(InetAddress.getLocalHost(),6666);
        DataInputStream data=new DataInputStream(socket.getInputStream());
        int fileNumber=data.readInt();
        String[] fileName=new String[fileNumber];
        long[] fileLength=new long[fileNumber];
        for(int i=0;i<fileNumber;i++){
            fileName[i]=data.readUTF();
            fileLength[i]=data.readLong();
        }
        BufferedInputStream input=new BufferedInputStream(socket.getInputStream());
        for(int i=0;i<fileNumber;i++){
            BufferedOutputStream output=new BufferedOutputStream(new FileOutputStream("C:\\Users\\Yezi\\Desktop\\互联网编程\\实验1\\Client\\"+fileName[i]));
            for(long j=0;j<fileLength[i];j++){
                int one=input.read();
                output.write(one);
            }
            output.close();
        }
        socket.shutdownOutput();
        socket.close();
    }
}
