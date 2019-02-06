/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import static java.lang.Integer.sum;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aspire
 */
public class Receiver {
     public static String stringbuild(char[] array){
         StringBuilder sb=new StringBuilder();
          for(int j=0; j<array.length;j+=7){
            int idx=0;
            int sum=0;
            for(int i=6;i>=0;i--){
                if(array[i+j]=='1'){
                    sum +=1<<idx;
                }
                idx++;
            }
            sb.append(Character.toChars(sum));
    }
        
          return sb.toString();
    }
     
      public static int checksum(String receiveddata,String tailer){
         String strng = "",l = null,m = null,bin="",b="",data="",zerosToAdd="";
        char c = 0,res;
        int len = 0,a = 0,ret=0,ml=0;
          
        StringBuilder sbb=new StringBuilder();
        receiveddata=receiveddata+tailer;
        char[] arr= receiveddata.replaceAll("\\s", "").toCharArray();
  
        l=stringbuild(arr);
        
        char[] messchar = l.toCharArray();
       ml=messchar.length;
       for(int i=0;i<messchar.length;i++){
        data +=Integer.toBinaryString(messchar[i])+" ";
        bin=Integer.toBinaryString(messchar[0]);
        
            a = bin.length();
            char d = messchar[i];
            c=(char) (c+d);
            b=Integer.toBinaryString(c);
            len=b.length();
         
            if(len>a){
                char y=1;
                String x= b.substring(1,len);
                char e = (char) Integer.parseInt(x,2);
                res=(char) (e+y);
               // System.out.println("inside if"+x+" "+e);
            }
            else{
                res=c;
                //System.out.println("inside else");
            }
            c=res;
             //System.out.println(Integer.toBinaryString(c));
       }
       
      
     
      String finalsum=Integer.toBinaryString(c);
      //System.out.println("Checksum : "+finalsum);
      
      if(finalsum.equals("1111111")){
      
         ret=1;
         
          for(int j=0; j<(arr.length-7);j+=7){
            int idx=0;
            int sum=0;
            for(int i=6;i>=0;i--){
                if(arr[i+j]=='1'){
                    sum +=1<<idx;
                }
                idx++;
            }
          //
            sbb.append(Character.toChars(sum)).toString();
        }
           System.out.println("Real Data : "+sbb);
          // System.out.println(l);
      }
         else{
              ret=0;
              System.out.println("Error!!!");
              }
 
    //  System.out.println(l);
      
         return ret;
         
     }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        // TODO code application logic here
        
        
       Socket ss = null;
       ServerSocket s1;
       try{
       s1 = new ServerSocket(2019);
       ss = s1.accept();
       }catch(IOException e){
         e.printStackTrace();
       }
       InputStream dos=null;
       ObjectInputStream objIn = null;
       
        String received="",tail="";
        int sq=0,i=0,ack=0;
        while(i!=1){
            
            try{
                
            dos = ss.getInputStream(); 
            objIn = new ObjectInputStream(dos);
            
           
            
            Frame frame = (Frame) objIn.readObject();
            System.out.println("Received Frame is:");
            sq=frame.getSeq();
            System.out.println("Sequence: " + sq);
            received = frame.getData();
            System.out.println("Data: " + received);
            ack = frame.getAck();
           
            System.out.println("Acknowledgement: " + ack);
            tail=frame.getTailer();
            System.out.println("Tailer: " + tail);
             ack=sq;

            //checksum(received,tail);
             int ret = checksum(received,tail);
            DataOutputStream des = new DataOutputStream(ss.getOutputStream());
            
             if(ret==1){
                des.writeUTF("Server acknowledged that the frame "+ ack +" is received");
            }
           else{
            
                des.writeUTF("");
           }
         
           System.out.println("\n");
        
             
        } catch(IOException e){
                try {
                    dos.close();
                    objIn.close();
                    System.out.println("Data has been received.");
                    break;
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
        }catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
        
        
      }
    
   
   
}
