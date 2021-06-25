package Control;

import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;

import java.sql.Statement;
import java.util.ArrayList;


import Model.Doctor;
import Model.Patient;
import Model.Visits;
import View.Branches;

public class Controller {
	
	TXTController txtcon = new TXTController();
	XMLController xmlCon = new XMLController();
	ValidationLogIn log   = new ValidationLogIn();
	ValidationSignUp sign = new ValidationSignUp();
	SendEmail mail = new SendEmail();
	Patient pat = new Patient();
	Doctor doc  = new Doctor();
	Visits visit = new Visits();
	boolean PatientEmail = false;
	boolean Patientpassword = false;
	boolean DoctorEmail  = false;
	boolean Doctorpassword  = false;
	boolean isPatient = false;
	boolean isDoctor  = false;
	private String searchLetters = "";

	public boolean isPatient() {
		return isPatient;
	}

	public void setPatient(boolean isPatient) {
		this.isPatient = isPatient;
	}

	public boolean isDoctor() {
		return isDoctor;
	}

	public void setDoctor(boolean isDoctor) {
		this.isDoctor = isDoctor;
	}

	public void Conn() throws Exception 
	{
		Connection conn;
		DbConnection connect = new DbConnection("hospitaldb", "root", "");
		conn = connect.Connect();
	}

	public boolean AccountValidation(String Email, String password) throws Exception 
	{
		pat.setEmail(Email);
		pat.setPassword(password);
		doc.setEmail(Email);
		doc.setPassword(password);
		
		
		if(log.checkPatientLogInEmailExistsQuery(pat))
		{
			this.PatientEmail = true;
		}
		if(log.checkPatientLogInPasswordQuery(pat))
		{
			this.Patientpassword = true;
		}
		if(log.checkDoctorLogInEmailExistsQuery(doc))
		{
			this.DoctorEmail = true;
		}
		if(log.checkDoctorLogInPasswordQuery(doc))
		{
			this.Doctorpassword = true;
		}
		
		if(this.PatientEmail == true && this.Patientpassword == true)
		{
			this.isPatient = true;
		}
		if(this.DoctorEmail == true && this.Doctorpassword == true)
		{
			this.isDoctor = true;
		}
		
		if ((this.PatientEmail == true && this.Patientpassword == true) || (this.DoctorEmail == true && this.Doctorpassword == true))
		{
			this.PatientEmail = false;
			this.Patientpassword = false;
			this.DoctorEmail  = false;
			this.Doctorpassword  = false;
			return true;
		}
		else return false;
	}
	public void GetPatientID() throws Exception 
	{
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
		int result = 0;
	   	Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT Patient_id FROM Patient WHERE Email = '" + pat.getEmail() + "';");
    	while(rs.next()) {
    		result = rs.getInt(1);
    	}
    	pat.setId(result);
    	conn.close();
	}
	
	public void GetDoctorID() throws Exception 
	{
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
		int result = 0;
	   	Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT Doctor_id FROM Doctor WHERE Email = '" + doc.getEmail() + "';");
    	while(rs.next()) {
    		result = rs.getInt(1);
    	}
    	doc.setId(result);
    	conn.close();
	}
	
	public void AccountSignUp(String name, String Email, String password, String birthdate, String role) throws Exception 
	{
		doc.setName(name);
		doc.setEmail(Email);
		doc.setPassword(password);
		doc.setBirthdate(birthdate);
		
		pat.setName(name);
		pat.setEmail(Email);
		pat.setPassword(password);
		pat.setBirthdate(birthdate);
		
		if (role.equals("Doctor"))
		{
			sign.addDoctorQuery(doc);
			GetDoctorID();
			xmlCon.XMLWriteDoctor("src/Files/Doctor.xml" , doc);
			txtcon.TXTWriteDoctor("src/Files/Doctor.text" , doc);
			
		}
		if (role.equals("Patient"))
		{
			sign.addPatientQuery(pat);
			GetPatientID();
			xmlCon.XMLWritePatient("src/Files/Patient.xml" , pat);
			txtcon.TXTWritePatient("src/Files/Patient.text" , pat);
			
		}
	}
	
	public void updateDepartmentPatientTable(String Branch) throws Exception
	{
		pat.setDepartment(Branch);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
		
	   	Statement stmt = conn.createStatement();
    	int rs = stmt.executeUpdate("UPDATE Patient SET Department = '" +  Branch + "' WHERE Email = '" + pat.getEmail() + "';");
    //	setPatientIDInVisits(pat.getEmail());
    	conn.close();
	}
	
	public void updateDepartmentDoctorTable(String Branch) throws Exception
	{
		doc.setDepartment(Branch);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
		
	   	Statement stmt = conn.createStatement();
    	int rs = stmt.executeUpdate("UPDATE Doctor SET Department = '" +  Branch + "' WHERE Email = '" + doc.getEmail() + "';");
    	conn.close();
	}
	
	public void updateSpecializationPatientTable(String specia) throws Exception
	{
		pat.setSpecialization(specia);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	int rs = stmt.executeUpdate("UPDATE Patient SET Specialization = '" + specia + "' WHERE Email = '" + pat.getEmail() + "';");
    	conn.close();
	}
	public void updateSpecializationDoctorTable(String specia) throws Exception
	{
		doc.setSpecialization(specia);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	int rs = stmt.executeUpdate("UPDATE Doctor SET Specialization = '" + specia + "' WHERE Email = '" + doc.getEmail() + "';");
    	conn.close();
	}
	public String[] getDoctorsFromSpecialization(String specia) throws Exception
	{
		ArrayList<String> Drs = new ArrayList<String>();
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT Name FROM Doctor WHERE Specialization = '" + specia + "';"); 
    	while(rs.next()) {
    		String drString = rs.getString(1);
    		Drs.add(drString);
    	}
		String[] drsArray = new String[Drs.size()+1];
		
		drsArray[0] = "Please Choose Your Doctor!";
    	for (int i = 1;  i < Drs.size()+1;  i++) {
    		drsArray[i] = Drs.get(i-1);
    	}
    	conn.close();
    	return drsArray;
	}
	public String getDoctorsAppointments(String name) throws Exception
	{
		String drString = "";
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT WorkHour FROM Doctor WHERE Name = '" + name + "';"); 
    	while(rs.next()) {
    		drString = rs.getString(1);
    	}
    	doc.setWorkTime(drString);
    	conn.close();
    	return drString;
	}
	public String getDoctorSpecialization(String name) throws Exception
	{
		String drString= "";
		DbConnection dbCon = new DbConnection("hospitaldb", "root", "");
		Connection conn = dbCon.Connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Specialization FROM Doctor WHERE Name = '" + name + "';");
    	while(rs.next()) {
    		drString = rs.getString(1);
    	}
    	conn.close();
    	return drString;
	}
	public String getDoctorDepartment(String name) throws Exception
	{
		String drString= "";
		DbConnection dbCon = new DbConnection("hospitaldb", "root", "");
		Connection conn = dbCon.Connect();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Department FROM Doctor WHERE Name = '" + name + "';");
    	while(rs.next()) {
    		drString = rs.getString(1);
    	}
    	conn.close();
    	return drString;
	}
	public void updateDoctorWorkTime(String workTime) throws Exception
	{
		doc.setWorkTime(workTime);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	int rs = stmt.executeUpdate("UPDATE Doctor SET WorkHour = '" + workTime + "' WHERE Email = '" + doc.getEmail() + "';");
    	conn.close();
	}
	public void fillTableVisits(String workTime , String drName) throws Exception
	{
		String xmlPath = "src/Files/Visits.xml";
		String txtPath = "src/Files/Visits.text";
		GetPatientID();
		pat.setAppointmentTime(workTime);
		visit.setAppointmentDate(workTime);
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
		
		int drID = 0;
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT Doctor_id FROM Doctor WHERE Name = '" + drName + "';"); 
    	while(rs.next()) {
    		drID = rs.getInt(1);
    	}
	   	
	   	String query = ("INSERT INTO Visits(Patient_Patient_id, Doctor_Doctor_id, AppointmentDate)"
    				   + " VALUES(" + pat.getId() +", "+ drID+ " , '" + workTime +"');");

	   	Statement stmtm = conn.createStatement();
    	stmtm.executeUpdate(query);
    	conn.close();
    	String stringDrId = String.valueOf(drID);
    	String stringPatId = String.valueOf(pat.getId());
    	xmlCon.appointmentDate(xmlPath, stringPatId, stringDrId, workTime);
    	txtcon.TXTWriteVisits(stringPatId, stringDrId, workTime, txtPath);
	}
	
	public String[] doctorSearch(KeyEvent e) throws Exception {
		if (e.getKeyCode() == 8) {
			   if ((searchLetters != null) && (searchLetters.length() > 0)) {
				   searchLetters = searchLetters.substring(0, searchLetters.length() - 1);
				   }
		}
		else {
		char a = e.getKeyChar();
		searchLetters += Character.toString(a);
		}
		ArrayList<String> Drs = new ArrayList<String>();
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection conn = dbCon.Connect();
	   	Statement stmt = conn.createStatement();
    	ResultSet rs = stmt.executeQuery("SELECT Name FROM Doctor WHERE Name LIKE '" + searchLetters + "%';");
    	if(!(searchLetters.equals(""))){
    	while(rs.next()) {
    		String drString = rs.getString(1);
    		Drs.add(drString);
    	}}
		String[] drsArray = new String[Drs.size()];
    	for (int i = 0;  i < Drs.size();  i++) {
    		drsArray[i] = Drs.get(i);
    	}
    	conn.close();
    	return drsArray;
	}
	
	public String[] arrangeDep(String depp) {
		String[] allDep = new String[]{"Beirut", "Sidon", "Tyre", "Tripoli"};
		if (depp.equals(allDep[0])) {
			return allDep;
		}
		else {
			for (int i = 1 ; i< allDep.length;i++) {
				if (depp.equals(allDep[i])) {
					String temp = allDep[0];
					allDep[0] = allDep[i];
					allDep[i] = temp;
				}
			}
			return allDep;
		}
	}
	public String[] arrangeSpe(String spec) {
		String[] allSpec = new String[]{"Neurology", "Surgery", "Hematology", "Physical Therapy", "Anesthesiology", "Allergy And Immunology"};
		if (spec.equals(allSpec[0])) {
			return allSpec;
		}
		else {
			for (int i = 1 ; i< allSpec.length;i++) {
				if (spec.equals(allSpec[i])) {
					String temp = allSpec[0];
					allSpec[0] = allSpec[i];
					allSpec[i] = temp;
				}
			}
			return allSpec;
		}
	}
	public String arrangeTime(String app) {
		String count ="";
		String[] time = app.split(",");
		if(!(time[0].equals("-"))) {
			count+="1";
		}
		if(!(time[1].equals("-"))) {
			count+="2";
		}
		if(!(time[2].equals("-"))) {
			count+="3";
		}
		return count;
	}
	public void accValidationFromName(String name) throws Exception {
		
		DbConnection dbCon = new DbConnection("hospitaldb","root","");
		Connection con = dbCon.Connect();
    	String doctorEmail = "";
    	String doctorPassword = "";
    	
    	Statement stmt = con.createStatement();  
    	ResultSet rs = stmt.executeQuery("SELECT Email FROM Doctor WHERE Name = '" + name + "';");  
    	while(rs.next()) {
    		doctorEmail = rs.getString(1);
    	}
    	rs = stmt.executeQuery("SELECT Password FROM Doctor WHERE Name = '" + name + "';");  
    	while(rs.next()) {
    		doctorPassword = rs.getString(1);
    	}
    	
    	con.close();
    	AccountValidation(doctorEmail, doctorPassword);
	}
	public void sendmail(String s) {
		String b= doc.getEmail();
		mail.sendMail(s, b);
	}
	
}