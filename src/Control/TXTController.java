package Control;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import Model.Doctor;
import Model.Patient;

public class TXTController {

	
	public void TXTWritePatient(String path, Patient patient) {
		
		FileWriter out;
		
		try {
            out = new FileWriter(path , true);
            out.write(patient.getId()+ "," +patient.getName()+ "," +patient.getEmail()+ "," +patient.getPassword()+ "," +patient.getBirthdate()+"\n");
            out.close();   
        } 
        catch(IOException e) {
            System.out.println("Error appending to file" );
        }
	}
	
	public void TXTWriteDoctor(String path, Doctor doctor) {
		
		FileWriter out;
		
		try {
            out = new FileWriter(path , true);
            out.write(doctor.getId()+ "," +doctor.getName()+ "," +doctor.getEmail()+ "," +doctor.getPassword()+ "," +doctor.getBirthdate()+"\n");
            out.close();   
        } 
        catch(IOException e) {
            System.out.println("Error appending to file" );
        }
	}
	
	public void TXTWriteVisits(String patientID, String doctorID, String appointmentDate, String path) {
		
		FileWriter out;
		
		try {
            out = new FileWriter(path , true);
            out.write(patientID+ "," +doctorID+ "," +appointmentDate+ "\n");
            out.close();   
        } 
        catch(IOException e) {
            System.out.println("Error appending to file" );
        }
	}
	
	public void TXTReadPatient(String path) {
		
		try {
            FileInputStream fStream = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
            
            String line = in.readLine();
            
            while (in.ready()) {
                line = in.readLine();
                String[] splittedArray = line.split("\n");

            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("File Reading Went Wrong");
        }
	}
	
	public void TXTReadDoctor(String path) {
		
		try {
            FileInputStream fStream = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
            
            String line = in.readLine();
            
            while (in.ready()) {
                line = in.readLine();
                String[] splittedArray = line.split("\n");

            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("File Reading Went Wrong");
        }
	}
	
	public void TXTReadVisits(String path) {
		
		try {
            FileInputStream fStream = new FileInputStream(path);
            BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
            
            String line = in.readLine();
            
            while (in.ready()) {
                line = in.readLine();
                String[] splittedArray = line.split("\n");

            }
            in.close();
        }
        catch (IOException e) {
            System.out.println("File Reading Went Wrong");
        }
	}

}
