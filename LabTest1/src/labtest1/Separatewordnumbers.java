/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labtest1;

import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;
import java.lang.*;
/**
 *
 * @author iiita
 */
public class Separatewordnumbers {
    public static void main(String[] args) {
        try {
            File file = new File("input.txt");
            Scanner sc = new Scanner(file);
          //  PrintWriter pw = new PrintWriter("output.txt");
            PrintWriter pw1 = new PrintWriter("words.txt");
            PrintWriter pw2 = new PrintWriter("numbers.txt");
            PrintWriter pw3 = new PrintWriter("punctuations.txt");
            String mod,mod1;
            while(sc.hasNext()){
                mod1="";
                mod = sc.next();
               // System.out.println(mod);
                int len = mod.length();
                char c = mod.charAt(0);
           //     System.out.println(c);
                char check =  mod.charAt(len-1);
                
                if(check == '.'|| check==','||check=='*'){
                    mod1 = mod.substring(0,len-1);
           
                    pw3.println(mod.charAt(len-1));
                //    System.out.println(check);
                }
                else 
                    mod1=mod;
                
                if(c>='0' && c<='9'){
                    
                    pw2.println(mod1);
                }
                else {
                    pw1.println(mod1);
                }
                
                
                
            }
            sc.close();
       //     pw.close();
           pw1.close();
           pw2.close();
            pw3.close();
        }
        catch(Exception e){
            System.out.println("Heyy error!!");
        }
        
    }
}