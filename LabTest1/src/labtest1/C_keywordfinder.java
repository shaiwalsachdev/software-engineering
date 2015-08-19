
package labtest1;

import java.io.File;
import java.io.PrintWriter;
import java.util.*;


public class C_keywordfinder {
    String search[]={"auto","break","case","char","const","continue","default","do","double","else","enum","extern",
                        "float","for","goto","if","int","long","register","return","short","signed","sizeof","static","struct",
                        "switch","typedef","union","unsigned","void","volatile","while"};
    int freq[]= new int[32];
    void disfreq(){
        int i;
        for(i=0;i<32;i++){
            if(freq[i]!=0){
                System.out.println(search[i]+" "+"Freq= "+freq[i]);
            }
        }
    }
    int checking(String a){
       
        int i;
        for(i=0;i<32;i++){
            if(a.compareTo(search[i])==0){
                freq[i]++;
                return 1;
            }
        }
        return 0;
    }
    
    
    public static void main(String[] args) {
        C_keywordfinder ob = new C_keywordfinder();
    try{
        
        File file = new File("lab.c");
        Scanner sc = new Scanner(file);
       PrintWriter pw = new PrintWriter("out1.txt");
       int line = 0;
        while(sc.hasNext()){
            String ab=sc.nextLine(); 
            line++;
            if(ab.contains("//"))
                  continue;
            if(ab.contains("/*")){
                
               while(true){
                   if(ab.contains("*/")){
                       line++;
                       ab = sc.nextLine();
                        break;
                   }
                    ab = sc.nextLine();
                    line++;
                }
            }
            
            String check[]=ab.split(" ");
            
            for(int i=0;i<check.length;i++){
                if(ob.checking(check[i])==1)
                    pw.println(check[i]+" "+line);
            }
        }
        pw.close();
        sc.close();
     }
    catch(Exception e){}
        ob.disfreq();
    }
}
