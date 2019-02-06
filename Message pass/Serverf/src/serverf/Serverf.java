/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverf;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Aspire
 */
public class Serverf {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        
       ServerSocket s1 = new ServerSocket(2019);
       Socket ss = s1.accept();
       
       DataInputStream si = new DataInputStream(ss.getInputStream()); 
       DataOutputStream so = new DataOutputStream(ss.getOutputStream());
       
       so.writeUTF("Serever has received message");
       System.out.println(si.readUTF());
    }
    
}
