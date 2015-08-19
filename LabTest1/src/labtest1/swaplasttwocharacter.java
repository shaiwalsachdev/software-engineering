package labtest1;

//importing packages
import java.io.*;
import java.util.Scanner;
//Classname is Lab1
public class swaplasttwocharacter {
    
    public static void main(String[] args) {
     
        try{
            // creating file object
            File file = new File("input.txt");
            // Making Scanner Object
            Scanner sc = new Scanner(file);
            //PrintWriter is used to output to file
            PrintWriter pw = new PrintWriter("output.txt");
            // While loop reading data until the end of file 
            while(sc.hasNext()){
                //Reading the filen using Scanner
                String read = sc.next();
                
                int len = read.length();
                char a,b;
                String mod="";
                // Checking for fullstop and comma at the end of word
                if(read.charAt(len-1)=='.' || read.charAt(len-1)==','){
                    // Finding the string leaving the last two characters 
                    mod = read.substring(0,len-3);
                    // finding the last two characters
                    a = read.charAt(len-2); 
                    b = read.charAt(len-3);
                    if(read.charAt(len-1)=='.')
                        mod = mod + a + b + ".";
                    else 
                        mod = mod + a + b + ",";
                }
                else {
                    // Finding the string leaving the last two characters 
                    mod = read.substring(0,len-2);
                    // finding the last two characters
                    a = read.charAt(len-1);
                    b = read.charAt(len-2);
                    mod = mod + a + b;
                    }
                   //Printing to File
                    pw.print(mod+" ");
            }
            // Closing the scanner and printwriter object
            sc.close();
            pw.close();
        }
        catch(Exception e){
            System.out.println("Heyy Error!!!!!!!!");
        }
    }
}
