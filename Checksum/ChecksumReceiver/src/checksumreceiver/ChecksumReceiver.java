/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package checksumreceiver;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


/**
 *
 * @author Aspire
 */
public class ChecksumReceiver {
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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String strng = "",l = null,m = null,bin="",b="",data="",zerosToAdd="";
        char c = 0,res;
        int len = 0,a = 0;
        
        ServerSocket s1 = new ServerSocket(2019);
        Socket ss = s1.accept();
        
    
       // ObjectInputStream objIn= new ObjectInputStream(dos);
        Scanner sc = new Scanner (ss.getInputStream());
        String receiveddata = sc.nextLine();
        System.out.println("Received Data : "+receiveddata);
        
        StringBuilder sb=new StringBuilder();
        StringBuilder sbb=new StringBuilder();
        char[] arr= receiveddata.replaceAll("\\s", "").toCharArray();
        /*for(int j=0; j<arr.length;j+=7){
            int idx=0;
            int sum=0;
            for(int i=6;i>=0;i--){
                if(arr[i+j]=='1'){
                    sum +=1<<idx;
                }
                idx++;
            }
          // System.out.println(sum);
           l = sb.append(Character.toChars(sum)).toString();
        }*/
        l=stringbuild(arr);
        
        char[] messchar = l.toCharArray();
     
       for(int i=0;i<messchar.length;i++){
        data +=Integer.toBinaryString(messchar[i])+" ";
        bin=Integer.toBinaryString(messchar[i]);
        
            a = bin.length();
            char d = messchar[i];
            c=(char) (c+d);
            b=Integer.toBinaryString(c);
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
       }
       
      
      //System.out.println(Integer.toBinaryString(c));
      String finalsum=Integer.toBinaryString(c);
      System.out.println("Checksum : "+finalsum);
      
      if(finalsum.equals("1111111")){
        //System.out.println("okay");
       /* int len1=0;
        int len2=l.length();
       len1=(arr.length/7)-1;
        
        String r=l.substring(len1,len2);
        System.out.println(r);*/
        // System.out.println(l);
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
              System.out.println("Error!!!");
              }
          
        
    //  System.out.println(l);
      
    }
   
    
}
