/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Aspire
 */
public class Sender {
    
    public static String bitincrease (String bit){
        String zerosToAdd = "";
         if(bit.length() <7){
                for(int j=0;j<(7-bit.length());j++){
                    zerosToAdd +="0";
                }
            }
         return zerosToAdd+bit;
    }
    
    
    public static String[] send(char[] messchar,int i, int j){
        String l, m = null,bin="",b="",data="",zerosToAdd="",zerosAdd="",mm="";
         int len = 0,a = 0,count=0;
         char c = 0,res;
        
       while( i<j+2){
           
      if(i<messchar.length){
        data +=Integer.toBinaryString(messchar[i])+" ";
      }
      else{ //padding
          data +="0000000";
          break;
      }
        bin=Integer.toBinaryString(messchar[i]);
        
            a = bin.length();
            char d = messchar[i];
            c=(char) (c+d);
            b=Integer.toBinaryString(c);
           
           
            //b=bitincrease(b);
            len=b.length();
            
            //System.out.println(Integer.toBinaryString(c));
            if(len>a){
                char y=1;
                String x= b.substring(1,len);
                char e = (char) Integer.parseInt(x,2);
                res=(char) (e+y);
                //System.out.println("inside if"+x+" "+e);
            }
            else{
                res=c;
                //System.out.println("inside else");
            }
            c=res;
           i++;
        //System.out.println(i+" "+j);
       
     }
      
       
       m = Integer.toBinaryString(c);
           
           m=bitincrease(m);
            //System.out.println("Checksum : "+m);
            //complement
            BigInteger twoToLength=new BigInteger("2").pow(m.length());
             mm = twoToLength.add(new BigInteger(m,2).not()).toString(2);
      
       String mx=bitincrease(mm);
       
       
       
        String[] St = new String[2];
        St[0] = mx;
        St[1] =data;
        return St;
        
      
    }
   

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String l,m = null,bin="",b="",data="",zerosToAdd="",zerosAdd="",mm="",mx="",msg="";
         int len = 0,a = 0,i=0,j=0,sq=0,k=0,mslen=0,ack=0;
         char c = 0,res;
          
        //connection
        Socket s = new Socket("localhost",2019);
        //OutputStream dis = s.getOutputStream();
        

        
        System.out.println("Message : "); 
        OutputStream dis = s.getOutputStream();
        Scanner sc = new Scanner(System.in);//input from user
        l=sc.nextLine(); //string from user
       
       char[] messchar = l.toCharArray();
       
       if(messchar.length%2==0){
           mslen=messchar.length/2;
          
       }
       else{
           
           mslen=messchar.length/2+1;
          
                   }
      
       while(k<mslen){
       
     
         if (msg!=""){
            
            sq++;
            i=i+2;
            j=j+2;
            String[] St = send(messchar,i,j);
             mx = St[0];
             data=St[1];
             //System.out.println(i+" "+j);
        }
        else{
            i=i;
            j=i;
            String[] St = send(messchar,i,j);
             mx = St[0];
             data=St[1];
              
        }
      
        
      
   //    System.out.println("Checksum : "+mx);
       
       System.out.println("Data : "+data); 
       
     
        
        Frame frame;
        
      
        frame = new Frame(sq, data,ack, mx);
        ObjectOutputStream obj = new ObjectOutputStream(dis);
        obj.writeObject(frame);
        DataInputStream des = new DataInputStream(s.getInputStream());
          msg=des.readUTF();
           System.out.println("\n");
      System.out.println(msg);
     
          k++;
       }
       
    }
    
}
