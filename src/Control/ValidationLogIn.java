package Control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Model.Doctor;
import Model.Patient;

public class ValidationLogIn {
	private static int patientIdEmail;
	private static int doctorIdEmail;
	
	public Boolean checkPatientLogInEmailExistsQuery(Patient patient) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Patient_id FROM Patient WHERE Email = '" + patient.getEmail() + "';");  
    	while(rs.next()) {
    		result = rs.getInt(1);
    		patientIdEmail = result;	
    	}
    	con.close();
    	if(result != 0)
    	{
    		return true;
    	} 	
    	return false;
	}
	
	public Boolean checkPatientLogInPasswordQuery(Patient patient) throws Exception {

		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
		
    	int result = 0;
    	ArrayList<Integer> patientIdPassword = new ArrayList<Integer>();
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Patient_id FROM Patient WHERE Password = " + "SHA('" + patient.getPassword() + "');"); 
    	while(rs.next()) {
    		result = rs.getInt(1);
    		patientIdPassword.add(result);
    	}
    	con.close();
    	if(result != 0 && patientIdPassword.contains(patientIdEmail))
    	{
    		patientIdPassword.clear();
    		return true;
    	} 	
		patientIdPassword.clear();
    	return false;
	}
	public Boolean checkDoctorLogInEmailExistsQuery(Doctor doctor) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Doctor_id FROM Doctor WHERE Email = '" + doctor.getEmail() + "';");  
    	while(rs.next()) {
    		result = rs.getInt(1);
    		doctorIdEmail = result;
    	}
    	con.close();
    	if(result != 0)
    	{
    		return true;
    	} 	
    	return false;
	}
	
	public Boolean checkDoctorLogInPasswordQuery(Doctor doctor) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	int result = 0;
    	ArrayList<Integer> doctorIdPassword = new ArrayList<Integer>();
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Doctor_id FROM Doctor WHERE Password = " + "SHA('" + doctor.getPassword() + "');");  
    	while(rs.next()) {
    		result = rs.getInt(1);
    		doctorIdPassword.add(result);
    	}
    	con.close();
    	if(result != 0 && doctorIdPassword.contains(doctorIdEmail))
    	{
    		doctorIdPassword.clear();
    		return true;
    	} 	
		doctorIdPassword.clear();
    	return false;
	}
}