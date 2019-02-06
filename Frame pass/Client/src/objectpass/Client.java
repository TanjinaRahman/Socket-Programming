package objectpass;

import java.io.*;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException {

        Socket s = new Socket("127.0.0.1", 9999);
        OutputStream dis = s.getOutputStream();
        String d="hello",a="hi",t="bye";
        Frame frame;
        frame = new Frame(1, d, a, t);
        ObjectOutputStream obj = new ObjectOutputStream(dis);
        obj.writeObject(frame);

     DataInputStream des = new DataInputStream(s.getInputStream());
    String msg=des.readUTF();
    System.out.println(msg);

    s.close();
    dis.close();
    obj.close();
    des.close();
    }

    
}
