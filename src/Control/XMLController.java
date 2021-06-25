package Control;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;


import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import Model.Doctor;
import Model.Patient;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class XMLController {

	
	public void XMLWritePatient(String path ,Patient patient) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
		
		Element root = document.getDocumentElement();
		Element newPatient = document.createElement("Patient");
		root.appendChild(newPatient);
		
		Element newPatientId = document.createElement("Patient_id");
		newPatientId.appendChild(document.createTextNode(String.valueOf(patient.getId())));
		newPatient.appendChild(newPatientId);
		
		Element newPatientName = document.createElement("Name");
		newPatientName.appendChild(document.createTextNode(patient.getName()));
		newPatient.appendChild(newPatientName);

		Element newPatientEmail = document.createElement("Email");
		newPatientEmail.appendChild(document.createTextNode(patient.getEmail()));
		newPatient.appendChild(newPatientEmail);
		
		Element newPatientPassword = document.createElement("Password");
		newPatientPassword.appendChild(document.createTextNode(patient.getPassword()));
		newPatient.appendChild(newPatientPassword);
		
		Element newPatientBirthdate = document.createElement("Birthdate");
		newPatientBirthdate.appendChild(document.createTextNode(patient.getBirthdate()));
		newPatient.appendChild(newPatientBirthdate);
		
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);
		StreamResult file = new StreamResult(path);
		transformer.transform(source, file);
		
	}

	public void XMLRead(String path) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
		
		NodeList nodelist = document.getElementsByTagName("Patient");
		
		for(int i = 0; i < nodelist.getLength(); i++) {
			Node patientNode = nodelist.item(i);
			
			if(patientNode.getNodeType() == Node.ELEMENT_NODE) {
				Element patientElement = (Element) patientNode;
				NodeList patientElementList = patientElement.getChildNodes();
				
				Patient patient = new Patient();
				System.out.println("Patient " + (i+1));
				for(int j = 0; j < patientElementList.getLength(); j++) {
					Node patientElementListNode = patientElementList.item(j);
					
					if(patientElementListNode.getNodeType() == Node.ELEMENT_NODE) {
						Node patientElementListElement = patientElementListNode;
						
						System.out.println(patientElementListElement.getNodeName() +": "+
						patientElementListElement.getTextContent());
					}}}}}
	
	public void XMLWriteDoctor(String path ,Doctor doctor) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
		
		Element root = document.getDocumentElement();
		Element newPatient = document.createElement("Doctor");
		root.appendChild(newPatient);
		
		Element newDoctorId = document.createElement("Doctor_id");
		newDoctorId.appendChild(document.createTextNode(String.valueOf(doctor.getId())));
		newPatient.appendChild(newDoctorId);
		
		Element newDoctorName = document.createElement("Name");
		newDoctorName.appendChild(document.createTextNode(doctor.getName()));
		newPatient.appendChild(newDoctorName);

		Element newDoctorEmail = document.createElement("Email");
		newDoctorEmail.appendChild(document.createTextNode(doctor.getEmail()));
		newPatient.appendChild(newDoctorEmail);
		
		Element newDoctorPassword = document.createElement("Password");
		newDoctorPassword.appendChild(document.createTextNode(doctor.getPassword()));
		newPatient.appendChild(newDoctorPassword);
		
		Element newDoctorBirthdate = document.createElement("Birthdate");
		newDoctorBirthdate.appendChild(document.createTextNode(doctor.getBirthdate()));
		newPatient.appendChild(newDoctorBirthdate);
	
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);
		StreamResult file = new StreamResult(path);
		transformer.transform(source, file);
		
	}
	
	public void appointmentDate( String path, String patientID, String doctorID, String appointmentDate) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
		
		Element root = document.getDocumentElement();
		Element newPatient = document.createElement("Visit");
		root.appendChild(newPatient);
		
		Element newPatientId = document.createElement("Patient_id");
		newPatientId.appendChild(document.createTextNode(patientID));
		newPatient.appendChild(newPatientId);
		
		Element newDoctorId = document.createElement("Doctor_id");
		newDoctorId.appendChild(document.createTextNode(doctorID));
		newPatient.appendChild(newDoctorId);

		Element newAppointmentDate = document.createElement("AppointmentDate");
		newAppointmentDate.appendChild(document.createTextNode(appointmentDate));
		newPatient.appendChild(newAppointmentDate);
	
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		DOMSource source = new DOMSource(document);
		StreamResult file = new StreamResult(path);
		transformer.transform(source, file);
		
	}
}
