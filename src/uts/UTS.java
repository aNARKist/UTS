/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NARK
 */
public class UTS {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Socket Listen=new Socket("10.151.34.155",6666);
            
            Scanner scan=new Scanner(System.in);
            String username;
            
            OutputStream os=Listen.getOutputStream();
            InputStream is=Listen.getInputStream();
            
            byte[] buffing=new byte[150];
            is.read(buffing);
            String wawa=new String(buffing);
            int mamam=0;
            mamam=wawa.lastIndexOf("\n");
            
            System.out.print(wawa.trim());
            username=scan.nextLine();
            
            username="Username:"+username+"\n";
            os.write(username.getBytes());
            os.flush();
            
            System.out.println(username);
            
            is=Listen.getInputStream();
            
            byte[] lalm=new byte[500];
            is.read(lalm);
            String lalam=new String(lalm);
            lalam=lalam.trim();
            System.out.println(lalam);
            while(true){
                is=Listen.getInputStream();
                
                String out="";
                String in="";
                
                byte[] buf=new byte[100];
                int k=is.read(buf);
                in=new String(buf);
                System.out.println(k);
                System.out.println("after getting");
                int ha=in.indexOf("\n");
                String ma=in.substring(0,ha);
                System.out.println(ha);
                System.out.println("before Clossing");
                
                //is.close();
                
                System.out.println("after Clossing");
                System.out.println(ma);
                
                if(ma=="Hash"){
                    String dama=in.substring(ha+1);
                    int ada=dama.indexOf("\n");
                    int lala= Integer.parseInt(in.substring(ha+1,ada));
                    out="Hash:"+dama.substring(ada+1)+"\n";
                }
                else if(ma=="200 State: Logged On\n"){
                    ma="";
                }
                else if(ma=="201 True\n"){
                    ma="";
                }
                else if(ma=="666\n"){
                    ma="";
                }
                
                else{
                    String ka=in;
                    int total=0;
                    int aas=0;
                    int operator=-2;
                    while(true){
                        int o=0;
                        int kakah=0;
                        o=ka.indexOf(" ");
                        String lla=ka.substring(0,o);
                        if(kakah==3){
                            break;
                        }
                        else if("+".equals(lla)){
                            operator=1;
                            ka=ka.substring(o+1);
                            kakah=kakah+1;
                        }
                        else if("-".equals(lla)){
                            operator=2;
                            ka=ka.substring(o+1);
                            kakah=kakah+1;
                        }
                        else if("x".equals(lla)){
                            operator=3;
                            ka=ka.substring(o+1);
                            kakah=kakah+1;
                        }
                        else if("mod".equals(lla)){
                            operator=4;
                            ka=ka.substring(o+1);
                            kakah=kakah+1;
                        }
                        else{
                            System.out.println("LLA: "+lla);
                            aas=Integer.parseInt(lla);
                            if(operator==-2){
                                total=aas;
                            }
                            ka=ka.substring(o+1);
                            kakah=kakah+1;
                            if(operator==1){
                                total=total+aas;
                                operator=-1;
                            }
                            else if(operator==2){
                                total=total-aas;
                                operator=-1;
                            }
                            else if(operator==3){
                                total=total*aas;
                                operator=-1;
                            }
                            else if(operator==4){
                                total=total%aas;
                                operator=-1;
                            }
                        }
                        System.out.println("LOOPING");
                    }
                    out="Result:"+total+"\n";
                }
                System.out.println("Send");
                os.write(out.getBytes());
                os.flush();
            }
            
            is.close();
            os.close();
            Listen.close();
            
        } catch (IOException ex) {
            Logger.getLogger(UTS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
}
