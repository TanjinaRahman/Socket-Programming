package objectpass;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    public static void main(String[] args) throws IOException {

      ServerSocket server = new ServerSocket(9999);
        Socket s = server.accept();
        InputStream dos = s.getInputStream(); 
   ObjectInputStream objIn= new ObjectInputStream(dos);

        try {
            Frame frame = (Frame) objIn.readObject();
            System.out.println("Received Frame is:");
            System.out.println("Sequence: " + frame.getSeq());
            System.out.println("Data: " + frame.getData());
            System.out.println("Acknowledgement: " + frame.getAck());
            System.out.println("Tailer: " + frame.getTailer());

            
            DataOutputStream des = new DataOutputStream(s.getOutputStream());
                des.writeUTF("Server acknowledged that the frame is received");

            
        } catch (ClassNotFoundException e) {
         
        }

      server.close();
      s.close();
      dos.close();
      objIn.close();
      
    }
}
