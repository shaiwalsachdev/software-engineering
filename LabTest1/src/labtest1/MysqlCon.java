package labtest1;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;  
import java.lang.*;
import java.util.*;

class MysqlCon{  
    Connection con =null;
    void connect(String url,String username,String password) throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");  
           con=DriverManager.getConnection(url,username,password);
        
    }
    String[] search(String search) throws ClassNotFoundException, SQLException{
        String re[] =  new String[10];
        int p=0;
        try{
           connecttofile();
            Search sc =new Search();
        PreparedStatement stmt = con.prepareStatement("SELECT rollno from student where name=?");
                stmt.setString(1,search);
                ResultSet rs=stmt.executeQuery();
                 HashMap hm = new HashMap();
                 hm.put("She",new Integer(100));
                  
           while(rs.next()) {
                      int roll=rs.getInt(1);
                      PreparedStatement stmt1 = con.prepareStatement("SELECT marks from marksheet where rollno=?");
                      String r1=String.valueOf(roll);
                      stmt1.setString(1, r1);
                      
                      ResultSet rs1 =stmt1.executeQuery();
                      
                      
                      if(rs1.next()){
                         
                        //  System.out.println(search+" "+roll+" "+rs1.getInt(1));
                       re[p] = "Name :"+ search + "\n" +"RollNO: "+roll+"\n" + "Marks: " + rs1.getInt(1) +"\n"; 
                       //   sc.getresult(search, roll, rs1.getInt(1));
                     //   System.out.println(re[0]);
                       // System.out.println(re[1]);
                       p++;
                        //return re;
                      }
                }
                 
             //    con.close();   
           
        }
        catch(Exception e){ System.out.println(e);}
        return re;
    }
    
   void insert(String name,int rollno,int marks) throws SQLException
   {  
       connecttofile();
       PreparedStatement stmt = con.prepareStatement("insert into student values(?,?)");
       stmt.setInt(1, rollno);
       stmt.setString(2, name);
        stmt.execute();
       
       PreparedStatement stmt1 = con.prepareStatement("insert into marksheet values(?,?)");
       stmt1.setInt(1, rollno);
       stmt1.setInt(2, marks);
        stmt1.execute();
       
    }
    void creatingfile(String username , String password) throws IOException{
        try {
             
	     Properties properties = new Properties();
	     properties.setProperty("url", "jdbc:mysql://localhost:3306/labse");
      
             
	     properties.setProperty("username", username);
	     properties.setProperty("password", password);
          //   System.out.println("Heyy i got your username password");
                File file = new File("mysql.properties");
		FileOutputStream fileOut = new FileOutputStream(file);
		properties.store(fileOut, "MYSQL PROPERTIES FILE @@@@@Copyright@@@@");
		fileOut.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        
    }
     void connecttofile(){
         
         String url="",username="",password="";
            try{  
                        File file = new File("mysql.properties");
			InputStream fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
                         url=properties.getProperty("url").toString();
                         username=properties.getProperty("username").toString();
                         password=properties.getProperty("password").toString();
                         connect(url,username,password);
            }
            catch(Exception e){}
     }
     
     
   
     
}  