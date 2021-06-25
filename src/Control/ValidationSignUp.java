package Control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Model.Doctor;
import Model.Patient;

public class ValidationSignUp {

	
	public void checkPatientEmailValidation(Patient patient) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Patient_id FROM Patient WHERE Email = '" + patient.getEmail() + "';");  
    	while(rs.next())
    	{
    		result = rs.getInt(1);	
    	}
    	con.close();
    	if(result != 0) 
    	{
    		System.out.println("Email Taken Choose Another One");
    	}
    	else 
    	{
    		addPatientQuery(patient);
    	}
	}
	
	public void checkDoctorEmailValidation(Doctor doctor) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Doctor_id FROM Doctor WHERE Email = '" + doctor.getEmail() + "';");  
    	while(rs.next())
    	{
    		result = rs.getInt(1);	
    	}
    	con.close();
    	if(result != 0) 
    	{
    		System.out.println("Email Taken Choose Another One");
    	}
    	else
    	{
    		addDoctorQuery(doctor);
    	}
	}

	public void addPatientQuery(Patient patient) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();

    	String query = "INSERT INTO Patient(Name, Email, Password, Birthdate, Department)"
    								+ "	VALUES('" +  patient.getName() + "','" + patient.getEmail() + "',SHA('" +
    								patient.getPassword() + "'), '" + patient.getBirthdate() + "','" + patient.getDepartment() + "');";
    	System.out.println(query);
    	
    	Statement stmt = con.createStatement();    
    	stmt.executeUpdate(query);
    	
    	System.out.println("Added Doctor");
    	con.close();
	}
	public void addDoctorQuery(Doctor doctor) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	String query = "INSERT INTO Doctor(Name, Email, Password, Birthdate, Specialization, Department)"
    									+ " VALUES('" +  doctor.getName() + "','" + doctor.getEmail() + "',SHA('" + doctor.getPassword() +
    									"'), '" + doctor.getBirthdate() + "','" + doctor.getSpecialization() + "','" + doctor.getDepartment() + "');";
    	System.out.println(query);
    	
    	Statement stmt = con.createStatement();    
    	stmt.executeUpdate(query);
    	
    	System.out.println("Added Patient");
    	con.close();
	}
}