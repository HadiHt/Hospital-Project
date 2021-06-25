package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.HeadlessException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Control.Controller;
import Control.DbConnection;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.UIManager;
import java.awt.event.MouseAdapter;
import javax.swing.JRadioButton;

public class AdminView extends JFrame {
	Controller cont;
	private static Point mouseDownScreenCoords = null;
	private static Point mouseDownCompCoords = null;
	PointerInfo a = MouseInfo.getPointerInfo();
	private JTextField doctorSearch;
	private JTextField search1;
	private JTextField search2;
	private JTextField search3;
	private JTextField search4;
	private JTextField search5;
	private JTextField search6;
	private JTextField search7;
	private String[] Drs;
	private String Specialization;
	private String Department;
	private String Appointments;
	private String[] depp;
	JFrame frmHospital = new JFrame();
	private JComboBox Dep;
	private JComboBox spe;
	private JLabel clock9Label_1;
	private JLabel clock9Label_2;
	/**
	 * @wbp.parser.entryPoint
	 */
	
	
	

	public void createAndShowGUI() {

		frmHospital.setResizable(false);
		frmHospital.setPreferredSize(new Dimension(600, 400));
		frmHospital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmHospital.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmHospital.setUndecorated(true);
		frmHospital.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				mouseDownScreenCoords = null;
				mouseDownCompCoords = null;
			}

			public void mousePressed(MouseEvent e) {
				mouseDownScreenCoords = e.getLocationOnScreen();
				mouseDownCompCoords = e.getPoint();
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});

		
		
		frmHospital.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frmHospital.setLocation(
						mouseDownScreenCoords.x + (currCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
						mouseDownScreenCoords.y + (currCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
			}
		});
		frmHospital.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				titleAlign(frmHospital);
			}
		});

		frmHospital.pack();
		frmHospital.setLocationRelativeTo(null);
		
		
		JRadioButton radiobut9 = new JRadioButton("");
		radiobut9.setBounds(265, 220, 109, 23);
		frmHospital.getContentPane().add(radiobut9);
		
		JRadioButton radiobut12 = new JRadioButton("");
		radiobut12.setBounds(377, 220, 109, 23);
		frmHospital.getContentPane().add(radiobut12);
		
		JRadioButton radiobut3 = new JRadioButton("");
		radiobut3.setBounds(487, 220, 109, 23);
		frmHospital.getContentPane().add(radiobut3);

		JLabel xButton = new JLabel("");
		xButton.setBounds(560, 0, 30, 30);
		xButton.setIcon(new ImageIcon("src/Images/X-icon-34.jpg"));
		xButton.addMouseListener(new MouseListener() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				mouseDownCompCoords = arg0.getPoint();

			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				mouseDownCompCoords = null;

			}

			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
			}
		});

		JLabel frameTitle = new JLabel("  H. HOSPITAL");
		frameTitle.setBounds(0, 0, 127, 30);
		frameTitle.setFont(new Font("Segoe UI Black", Font.BOLD, 16));
		frameTitle.setForeground(Color.WHITE);

		JPanel titleBar = new JPanel();
		titleBar.setBounds(0, 0, 600, 30);
		titleBar.setBackground(Color.BLACK);
		titleBar.setLayout(null);
		frmHospital.getContentPane().setLayout(null);
		frmHospital.getContentPane().add(xButton);
		frmHospital.getContentPane().add(frameTitle);
		frmHospital.getContentPane().add(titleBar);
		
		String[] specialization  = {"Specialization", "Neurology", "Surgery", "Hematology", "Physical Therapy", "Anesthesiology", "Allergy And Immunology"};
		
		String[] firstTime = new String[2];
		firstTime[0] = "-";
		firstTime[1] = "9 -> 12";
		
		String[] secondTime = new String[2];
		secondTime[0] = "-";
		secondTime[1] = "12 -> 3";
		
		String[] thirdTime = new String[2];
		thirdTime[0] = "-";
		thirdTime[1] = "3 -> 6";
		
		doctorSearch = new JTextField();
		doctorSearch.setBounds(20, 70, 190, 25);
		frmHospital.getContentPane().add(doctorSearch);
		doctorSearch.setColumns(10);
		
		search1 = new JTextField();
		search1.addMouseListener(new MouseAdapter() {      //search1
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search1.getText());
					Department = cont.getDoctorDepartment(search1.getText());
					Appointments = cont.getDoctorsAppointments(search1.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doctorSearch.setText(search1.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			    

			    
			}
		});
		search1.setBackground(Color.WHITE);
		search1.setEditable(false);
		search1.setColumns(10);
		search1.setBounds(20, 95, 190, 26);
		search1.setVisible(false);
		frmHospital.getContentPane().add(search1);
		
		search2 = new JTextField();
		search2.addMouseListener(new MouseAdapter() {              //search2
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search2.getText());
					Department = cont.getDoctorDepartment(search2.getText());
			     	Appointments = cont.getDoctorsAppointments(search2.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				
				doctorSearch.setText(search2.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search2.setBackground(Color.WHITE);
		search2.setEditable(false);
		search2.setColumns(10);
		search2.setBounds(20, 120, 190, 26);
		search2.setVisible(false);
		frmHospital.getContentPane().add(search2);
		
		search3 = new JTextField();
		search3.addMouseListener(new MouseAdapter() {        //search3
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search3.getText());
					Department = cont.getDoctorDepartment(search3.getText());
			     	Appointments = cont.getDoctorsAppointments(search3.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doctorSearch.setText(search3.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search3.setBackground(Color.WHITE);
		search3.setEditable(false);
		search3.setColumns(10);
		search3.setBounds(20, 145, 190, 26);
		search3.setVisible(false);
		frmHospital.getContentPane().add(search3);
		
		search4 = new JTextField();
		search4.addMouseListener(new MouseAdapter() {           //search4
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search4.getText());
					Department = cont.getDoctorDepartment(search4.getText());
			     	Appointments = cont.getDoctorsAppointments(search4.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doctorSearch.setText(search4.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search4.setBackground(Color.WHITE);
		search4.setEditable(false);
		search4.setColumns(10);
		search4.setBounds(20, 170, 190, 26);
		search4.setVisible(false);
		frmHospital.getContentPane().add(search4);
		
		search5 = new JTextField();
		search5.addMouseListener(new MouseAdapter() {        //search5
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search5.getText());
					Department = cont.getDoctorDepartment(search5.getText());
			     	Appointments = cont.getDoctorsAppointments(search5.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				doctorSearch.setText(search5.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search5.setBackground(Color.WHITE);
		search5.setEditable(false);
		search5.setColumns(10);
		search5.setBounds(20, 195, 190, 26);
		search5.setVisible(false);
		frmHospital.getContentPane().add(search5);
		
		search6 = new JTextField();
		search6.addMouseListener(new MouseAdapter() {          //search6
			@Override
			public void mouseClicked(MouseEvent e) {			
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search6.getText());
					Department = cont.getDoctorDepartment(search6.getText());
			     	Appointments = cont.getDoctorsAppointments(search6.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doctorSearch.setText(search6.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search6.setBackground(Color.WHITE);
		search6.setEditable(false);
		search6.setColumns(10);
		search6.setBounds(20, 220, 190, 26);
		search6.setVisible(false);
		frmHospital.getContentPane().add(search6);
		
		search7 = new JTextField();
		search7.addMouseListener(new MouseAdapter() {            //search7
			@Override
			public void mouseClicked(MouseEvent e) {
				search1.setVisible(false);
				search2.setVisible(false);	
				search3.setVisible(false);
				search4.setVisible(false);
				search5.setVisible(false);
				search6.setVisible(false);
				search7.setVisible(false);
				
				try {
					Specialization = cont.getDoctorSpecialization(search7.getText());
					Department = cont.getDoctorDepartment(search7.getText());
			     	Appointments = cont.getDoctorsAppointments(search7.getText());
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				doctorSearch.setText(search7.getText());
				Dep.removeAllItems();
			    String[] temp11 =cont.arrangeDep(Department);
			    for(int i = 0; i< temp11.length; i++) {
			    	Dep.addItem(temp11[i]);
			    }
			    spe.removeAllItems();
			    String[] temp12 = cont.arrangeSpe(Specialization);
			    for(int i = 0; i< temp12.length; i++) {
			    	spe.addItem(temp12[i]);
			    }
			    radiobut9.setSelected(false);
			    radiobut12.setSelected(false);
			    radiobut3.setSelected(false);
			    String temp13 = cont.arrangeTime(Appointments);
			    if(temp13.contains("1")) {
			    	radiobut9.setSelected(true);
			    }
			    if(temp13.contains("2")) {
			    	radiobut12.setSelected(true);
			    }
			    if(temp13.contains("3")) {
			    	radiobut3.setSelected(true);
			    }
			}
		});
		search7.setBackground(Color.WHITE);
		search7.setEditable(false);
		search7.setColumns(10);
		search7.setBounds(20, 245, 190, 26);
		search7.setVisible(false);
		frmHospital.getContentPane().add(search7);
		
		doctorSearch.addKeyListener(new KeyAdapter() {           //Searching Doctors
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					Drs = cont.doctorSearch(e);
					int num= Drs.length;
					switch(num) {
					
					case 0:
						search0();
						break;
					case 1:
						search1();
						break;
					case 2:
						search2();
						break;
					case 3:
						search3();
						break;
					case 4:
						search4();
						break;
					case 5:
						search5();
						break;
					case 6:
						search6();
						break;
					case 7:
						search7();
						break;
						
					}
					
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		
		
		JLabel pickDoctorTag = new JLabel("Seach a Doctor!");
		pickDoctorTag.setBounds(20, 41, 107, 25);
		frmHospital.getContentPane().add(pickDoctorTag);
		
		JButton sendMailButton = new JButton("Send Mail");		
		sendMailButton.setBounds(23, 271, 155, 50);
		sendMailButton.setBackground(new Color(0, 0, 0));
		sendMailButton.setForeground(new Color(255, 255, 255));
		sendMailButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		frmHospital.getContentPane().add(sendMailButton);
		
		Dep = new JComboBox();
		Dep.setMaximumRowCount(5);
		Dep.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Dep.setBounds(20, 160, 140, 22);
		frmHospital.getContentPane().add(Dep);
		
	//	dep = new JComboBox();
	//	dep.setBounds(20, 160, 90, 22);
	//	dep.setMaximumRowCount(5);
	//	dep.setFont(new Font("Tahoma", Font.PLAIN, 12));
	//	frmHospital.getContentPane().add(dep);
		
		spe = new JComboBox();
		spe.setBounds(20, 200, 140, 22);
		spe.setMaximumRowCount(5);
		spe.setFont(new Font("Tahoma", Font.PLAIN, 12));
		frmHospital.getContentPane().add(spe);
		
		JLabel clock9Label = new JLabel("");
		clock9Label.setBounds(230, 120, 112, 96);
		clock9Label.setIcon(new ImageIcon("src/Images/clock9_90.jpg"));
		frmHospital.getContentPane().add(clock9Label);
		
		clock9Label_1 = new JLabel("");
		clock9Label_1.setBounds(341, 120, 112, 96);
		clock9Label_1.setIcon(new ImageIcon("src/Images/clock12_90.jpg"));
		frmHospital.getContentPane().add(clock9Label_1);
		
		clock9Label_2 = new JLabel("");
		clock9Label_2.setIcon(new ImageIcon("src/Images/clock3_90.jpg"));
		clock9Label_2.setBounds(452, 120, 112, 96);
		frmHospital.getContentPane().add(clock9Label_2);

		
		sendMailButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String depp = Dep.getSelectedItem().toString(); 
					String specia = spe.getSelectedItem().toString(); 
					String workt = ""; 
					if(radiobut9.isSelected()) {
						workt +="9 -> 12,";
					}
					else workt +="-,";

					if(radiobut12.isSelected()) {
						workt +="12 -> 3,";
					}
					else workt +="-,";
					
					if(radiobut3.isSelected()) {
						workt +="3 -> 6";
					}
					else workt +="-";
					cont.accValidationFromName(doctorSearch.getText());
					cont.updateDoctorWorkTime(workt);
					cont.updateDepartmentDoctorTable(depp);
					cont.updateSpecializationDoctorTable(specia);
					
					Department = depp;
					Specialization = specia;
					Appointments = workt;
					

				} catch (Exception e) {
					e.printStackTrace();
				}
				String mailToSend = "Dear "+doctorSearch.getText()+ "\nYour Department has been changed to: " 
				                  + Department+".\nYour Specialization has been changed to: "
						          + Specialization+ ".\nYour Work Hours has been changed to: "+ Appointments;
				cont.sendmail(mailToSend);
				System.exit(ABORT);

			}
		});
		
						
		
		frmHospital.setVisible(true);
		titleBar.addMouseListener(new MouseListener() {
			public void mouseReleased(MouseEvent e) {
				mouseDownScreenCoords = null;
				mouseDownCompCoords = null;
			}

			public void mousePressed(MouseEvent e) {
				mouseDownScreenCoords = e.getLocationOnScreen();
				mouseDownCompCoords = e.getPoint();
			}

			public void mouseExited(MouseEvent e) {
			}

			public void mouseEntered(MouseEvent e) {
			}

			public void mouseClicked(MouseEvent e) {
			}
		});

		titleBar.addMouseMotionListener(new MouseMotionListener() {
			public void mouseMoved(MouseEvent e) {
			}

			public void mouseDragged(MouseEvent e) {
				Point currCoords = e.getLocationOnScreen();
				frmHospital.setLocation(
						mouseDownScreenCoords.x + (currCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
						mouseDownScreenCoords.y + (currCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
			}
		});
	}


	
	
	public void search0() {
		search1.setVisible(false);
		search2.setVisible(false);	
		search3.setVisible(false);
		search4.setVisible(false);
		search5.setVisible(false);
		search6.setVisible(false);
		search7.setVisible(false);
	}
	public void search1() {
		search1.setVisible(true);
		search2.setVisible(false);	
		search3.setVisible(false);
		search4.setVisible(false);
		search5.setVisible(false);
		search6.setVisible(false);
		search7.setVisible(false);
		search1.setText(Drs[0]);
	}
	public void search2() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(false);
		search4.setVisible(false);
		search5.setVisible(false);
		search6.setVisible(false);
		search7.setVisible(false);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
	}
	public void search3() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(true);
		search4.setVisible(false);
		search5.setVisible(false);
		search6.setVisible(false);
		search7.setVisible(false);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
		search3.setText(Drs[2]);
	}
	public void search4() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(true);
		search4.setVisible(true);
		search5.setVisible(false);
		search6.setVisible(false);
		search7.setVisible(false);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
		search3.setText(Drs[2]);
		search4.setText(Drs[3]);
	}
	public void search5() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(true);
		search4.setVisible(true);
		search5.setVisible(true);
		search6.setVisible(false);
		search7.setVisible(false);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
		search3.setText(Drs[2]);
		search4.setText(Drs[3]);
		search5.setText(Drs[4]);
	}
	public void search6() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(true);
		search4.setVisible(true);
		search5.setVisible(true);
		search6.setVisible(true);
		search7.setVisible(false);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
		search3.setText(Drs[2]);
		search4.setText(Drs[3]);
		search5.setText(Drs[4]);
		search6.setText(Drs[5]);
	}
	public void search7() {
		search1.setVisible(true);
		search2.setVisible(true);	
		search3.setVisible(true);
		search4.setVisible(true);
		search5.setVisible(true);
		search6.setVisible(true);
		search7.setVisible(true);
		search1.setText(Drs[0]);
		search2.setText(Drs[1]);
		search3.setText(Drs[2]);
		search4.setText(Drs[3]);
		search5.setText(Drs[4]);
		search6.setText(Drs[5]);
		search7.setText(Drs[6]);
	}
	
	public JTextField getSearch1() {
		return search1;
	}

	public void setSearch1(JTextField search1) {
		this.search1 = search1;
	}

	public JTextField getSearch2() {
		return search2;
	}

	public void setSearch2(JTextField search2) {
		this.search2 = search2;
	}

	public JTextField getSearch3() {
		return search3;
	}

	public void setSearch3(JTextField search3) {
		this.search3 = search3;
	}

	public JTextField getSearch4() {
		return search4;
	}

	public void setSearch4(JTextField search4) {
		this.search4 = search4;
	}

	public JTextField getSearch5() {
		return search5;
	}

	public void setSearch5(JTextField search5) {
		this.search5 = search5;
	}

	public JTextField getSearch6() {
		return search6;
	}

	public void setSearch6(JTextField search6) {
		this.search6 = search6;
	}

	public JTextField getSearch7() {
		return search7;
	}

	public void setSearch7(JTextField search7) {
		this.search7 = search7;
	}

	private void titleAlign(JFrame frame) {

		Font font = frame.getFont();

		String currentTitle = frame.getTitle().trim();
		FontMetrics fm = frame.getFontMetrics(font);
		int frameWidth = frame.getWidth();
		int titleWidth = fm.stringWidth(currentTitle);
		int spaceWidth = fm.stringWidth(" ");
		int centerPos = (frameWidth / 2) - (titleWidth / 2);
		int spaceCount = centerPos / spaceWidth;
		String pad = "";
		pad = String.format("%" + (spaceCount - 14) + "s", pad);
		frame.setTitle(pad + currentTitle);

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void startClass(){

		EventQueue.invokeLater(() -> {
			new AdminView(cont).createAndShowGUI();
		});
	}
	public AdminView(Controller cont) throws HeadlessException {
		super();
		this.cont = cont;
	}
}
