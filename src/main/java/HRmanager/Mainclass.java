
package HRmanager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;




public class Mainclass{
    
private int Eid;
private String Ename;
private String EDepartment;
private String purpose;
private String EDesignation;
private int DesignationID;
private int Hraid;
private String Hrassname;
protected String Hrusername;
protected String Hrpassword;
protected String username;
protected String password;


public Mainclass(int Eid,String Ename,String EDepartment,String purpose,String EDesignation, int DesignationID){
this.Eid = Eid;
this.Ename = Ename;
this.EDepartment = EDepartment;
this.purpose = purpose;
this.DesignationID = DesignationID;
this.EDesignation = EDesignation;

}

public Mainclass(int Hraid, String Hrassname,String Hrusername, String Hrpassword) {
        this.Hraid = Hraid;
        this.Hrassname = Hrassname;
        this.Hrusername = Hrusername;
        this.Hrpassword = Hrpassword;
    }



public int getEid(){
return Eid;
}


public String getEname(){
return Ename;
}


public String getEDepartment(){
return EDepartment;
}


public String getpurpose(){
return purpose;
}


public int getDesignationID(){
return DesignationID;
}


public String getEDesignation(){
return EDesignation;
}


public String getHrassname(){
return Hrassname;
}


public int getHraid(){
return Hraid;
}


public void SavetoDepartment(){ 

 try (BufferedWriter writer = new BufferedWriter(new FileWriter("Department_details", true))) {
            writer.write("Department Details");
            writer.newLine();
            writer.write("-----------------------------");
            writer.newLine();
            try {
            
            LocalDateTime now = LocalDateTime.now();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
            writer.write("Department Name : "+EDepartment+"  Purpose : "+purpose);
            writer.newLine(); 
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
}
public void SavetoDesignation(){

     try (BufferedWriter writer = new BufferedWriter(new FileWriter("Designation_details", true))) {
            writer.write("Designation Details");
            writer.newLine();
            writer.write("-----------------------------");
            writer.newLine();
            try {
            
            LocalDateTime now = LocalDateTime.now();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
            writer.write("Designation  Name : "+EDesignation+"  Designation ID : "+DesignationID);
            writer.newLine();
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }

} 

public void SavetoHRassistant(){

     try (BufferedWriter writer = new BufferedWriter(new FileWriter("HR Assistant_details", true))) {
            writer.write("HR Assistant Details");
            writer.newLine();
            writer.write("-----------------------------");
            writer.newLine();
            try {
            
            LocalDateTime now = LocalDateTime.now();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
            writer.write("HR Assistant  Name : "+Hrassname+"  HR Assistant ID : "+Hraid+" Username : "+Hrusername+" Password : "+Hrpassword);
            writer.newLine(); 
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }      

}
public void saveEmployeeDetails(Mainclass employee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Employee_details", true))) {
            writer.write("Employee Details");
            writer.newLine();
            writer.write("-----------------------------");
            writer.newLine();
            try {
            
            LocalDateTime now = LocalDateTime.now();
            
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            writer.write(formattedDateTime);
            writer.newLine();
        } catch (Exception e) {
            e.printStackTrace();
        }
            writer.write("Employee ID : "+Eid+", Employee Name : "+Ename+" , Department : "+EDepartment+" , Designation : "+EDesignation);
            writer.newLine(); 
            writer.newLine();
            writer.close();
        } catch (Exception e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }      
}
    public static void main(String[] args) {
        Show msg = new Show();
        msg.Message();
        
        }
}
