/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checksumsender;

import java.io.IOException;
import java.io.PrintStream;
import java.math.BigInteger;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Aspire
 */
public class ChecksumSender {

     public static String bitincrease (String bit){
        String zerosToAdd = "";
         if(bit.length() <7){
                for(int j=0;j<(7-bit.length());j++){
                    zerosToAdd +="0";
                }
            }
         return zerosToAdd+bit;
    }
    /**
     * @param args the command line arguments
     */
     public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String l,m = null,bin="",b="",data="",zerosToAdd="",zerosAdd="";
        int len = 0,a = 0;
        //connection
        Socket s = new Socket("localhost",2019);
        //OutputStream dis = s.getOutputStream();
        

        
        System.out.println("Message : "); 
        Scanner sc = new Scanner(System.in);//input from user
        l=sc.nextLine(); //string from user
        //to pass the number to the server
        PrintStream p=new PrintStream(s.getOutputStream());//printstream object p. 
        //p.println(n);
       char[] messchar = l.toCharArray();
       char c = 0,res;
       for(int i=0;i<messchar.length;i++){
        data +=Integer.toBinaryString(messchar[i])+" ";
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
               // System.out.println("inside if"+x+" "+e);
            }
            else{
                res=c;
                //System.out.println("inside else");
            }
            c=res;
            
            
         //System.out.println(c); 
         //System.out.println(b);
         // System.out.println(a); 
       //System.out.println(len); 
       }
       m = Integer.toBinaryString(c);
            //System.out.println(m.length());
           /* if(m.length() <7){
                for(int j=0;j<(7-m.length());j++){
                    zerosToAdd +="0";
                }
            }
            m =zerosToAdd+m;*/
           m=bitincrease(m);
            //System.out.println("Checksum : "+m);
            //complement
            BigInteger twoToLength=new BigInteger("2").pow(m.length());
            String mm = twoToLength.add(new BigInteger(m,2).not()).toString(2);
       /* if(mm.length() <7){
        for(int j=0;j<(7-mm.length());j++){
        zerosAdd +="0";
        }
        }
        mm =zerosAdd+mm;*/
       String mx=bitincrease(mm);
        
      
      //System.out.println(data); 
       System.out.println("Checksum : "+mx);
       data = data+mx;
       System.out.println(data); 
       
      p.println(data);
       
       

    }
    
}
