/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Aspire
 */
public class Clientf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
         Socket s = new Socket("localhost",2019);
         
         DataInputStream ci = new DataInputStream(s.getInputStream()); 
        DataOutputStream co = new DataOutputStream(s.getOutputStream());
       co.writeUTF("Client has sent message");
       System.out.println(ci.readUTF());
    }
    
}
