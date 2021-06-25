package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.HeadlessException;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.Controller;

import javax.swing.JComboBox;
import java.awt.SystemColor;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

public class PatientView extends JFrame {

	Controller cont;
	private static Point mouseDownScreenCoords = null;
	private static Point mouseDownCompCoords = null;
	PointerInfo a = MouseInfo.getPointerInfo();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	String selectedDoctorString;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() {

		final JFrame frmHospital = new JFrame();
		frmHospital.setResizable(false);
		frmHospital.setPreferredSize(new Dimension(600, 400));
		frmHospital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmHospital.setExtendedState(JFrame.MAXIMIZED_BOTH);
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
		
		final JPanel panel3 = new JPanel();
		panel3.setBackground(SystemColor.menu);
		panel3.setBounds(201, 191, 91, 100);
		frmHospital.getContentPane().add(panel3);
		
		final JPanel panel12 = new JPanel();
		panel12.setBackground(SystemColor.menu);
		panel12.setBounds(109, 191, 91, 100);
		frmHospital.getContentPane().add(panel12);
		
		final JPanel panel9 = new JPanel();
		panel9.setBackground(SystemColor.menu);
		panel9.setBounds(15, 191, 91, 100);
		frmHospital.getContentPane().add(panel9);
		
		JLabel clock9Label_1 = new JLabel("");
		clock9Label_1.setIcon(new ImageIcon("src/Images/clock12_90.jpg"));
		clock9Label_1.setBounds(109, 198, 112, 96);
		frmHospital.getContentPane().add(clock9Label_1);
		
		JLabel clock9Label_1_1 = new JLabel("");
		clock9Label_1_1.setIcon(new ImageIcon("src/Images/clock3_90.jpg"));
		clock9Label_1_1.setBounds(203, 198, 112, 96);
		frmHospital.getContentPane().add(clock9Label_1_1);
		
		JLabel clock9Label = new JLabel("");
		clock9Label.setIcon(new ImageIcon("src/Images/clock9_90.jpg"));
		clock9Label.setBounds(15, 198, 112, 96);
		frmHospital.getContentPane().add(clock9Label);
		frmHospital.getContentPane().add(xButton);
		frmHospital.getContentPane().add(frameTitle);
		frmHospital.getContentPane().add(titleBar);

		final JLabel label1 = new JLabel("Please Choose Specialization First!");
		label1.setForeground(new Color(0, 0, 255));
		label1.setFont(new Font("Arial", Font.ITALIC, 9));
		label1.setBounds(21, 162, 155, 25);
		frmHospital.getContentPane().add(label1);

		JLabel logo = new JLabel("");
		logo.setBackground(Color.WHITE);
		logo.setIcon(new ImageIcon("src/Images/hospital_logo_350.jpg"));
		logo.setBounds(266, 80, 334, 320);
		frmHospital.getContentPane().add(logo);
		
		JLabel loginLabel = new JLabel("WELCOME!");
		loginLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		loginLabel.setBounds(236, 41, 127, 43);
		frmHospital.getContentPane().add(loginLabel);
		
		final JComboBox doctorsComboBox = new JComboBox(new Object[]{});
		doctorsComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});	
		doctorsComboBox.setMaximumRowCount(5);
		doctorsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		doctorsComboBox.setBounds(19, 140, 170, 22);
		frmHospital.getContentPane().add(doctorsComboBox);
		
		
		final JRadioButton radioButton9 = new JRadioButton("");
		buttonGroup.add(radioButton9);
		radioButton9.setBounds(50, 295, 85, 23);

		final JRadioButton radioButton12 = new JRadioButton("");
		buttonGroup.add(radioButton12);
		radioButton12.setBounds(145, 295, 85, 23);
		
		final JRadioButton radioButton3 = new JRadioButton("");
		buttonGroup.add(radioButton3);
		radioButton3.setBounds(238, 295, 109, 23);
		
		
		String[] specialization  = {"Specialization", "Neurology", "Surgery", "Hematology", "Physical Therapy", "Anesthesiology", "Allergy And Immunology"};
		final JComboBox specializationComboBox = new JComboBox(specialization);
		specializationComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				label1.setVisible(false);
				
				String selected = specializationComboBox.getSelectedItem().toString();
				if (selected.equals("Specialization"))
				{
					System.out.println("Please Choose A Right Specialization");
				} else

					try {
						JComboBox appointmentsComboBox1 = new JComboBox();
						cont.updateSpecializationPatientTable(selected);
						doctorsComboBox.setVisible(false);
						if (selected.equals("Neurology")) 
						{
							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										} catch (Exception e) {
											e.printStackTrace();
										}
									}
									
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});
						}
						if (selected.equals("Surgery")) 
						{
							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										}
											catch (Exception e) {
											e.printStackTrace();
										}
									}
									
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});

						}
						if (selected.equals("Hematology")) 
						{

							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										}
										catch (Exception e) {
											e.printStackTrace();
										}
									}
									
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});
						}
						if (selected.equals("Physical Therapy")) 
						{

							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										}
										catch (Exception e) {
											e.printStackTrace();
										}
									}
									
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});
						}
						if (selected.equals("Anesthesiology")) 
						{
							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										}
										catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});
						}
						if (selected.equals("Allergy And Immunology")) 
						{
							final JComboBox doctorsComboBox2 = new JComboBox(cont.getDoctorsFromSpecialization(selected));
							doctorsComboBox2.setMaximumRowCount(5);
							doctorsComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 11));
							doctorsComboBox2.setBounds(19, 140, 170, 22);
							frmHospital.getContentPane().add(doctorsComboBox2);
							doctorsComboBox2.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									String drSelected = doctorsComboBox2.getSelectedItem().toString();
									selectedDoctorString = drSelected;
									if (drSelected.equals("Please Choose Your Doctor!"))
									{
										System.out.println("Please Choose Your Doctor First");
									} else 
									{
										try {
											String time =cont.getDoctorsAppointments(drSelected);
											String[] hours = time.split(",");
											if(!(hours[0].equals("-")))
											{
												panel9.setVisible(false);
											}
											else 
											{
												panel9.setVisible(true);
											}
											
											if(!(hours[1].equals("-")))
											{
												panel12.setVisible(false);
											}
											else 
											{
												panel12.setVisible(true);
											}
											if(!(hours[2].equals("-")))
											{
												panel3.setVisible(false);
											}
											else 
											{
												panel3.setVisible(true);
											}

										}
										catch (Exception e) {
											e.printStackTrace();
										}
									}
								}
							});
							specializationComboBox.addItemListener(new ItemListener() {
								public void itemStateChanged(ItemEvent arg0) {
									doctorsComboBox2.setVisible(false);
								}});
						}
						
					} catch (Exception e) {
						e.printStackTrace();
					}
			}
		});
		
		frmHospital.getContentPane().add(radioButton9);
		frmHospital.getContentPane().add(radioButton12);
		frmHospital.getContentPane().add(radioButton3);
		specializationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 11));
		specializationComboBox.setMaximumRowCount(5);
		specializationComboBox.setBounds(19, 100, 170, 22);
		frmHospital.getContentPane().add(specializationComboBox);

		JButton makeAppointmentButton = new JButton("Make Appointment");
		makeAppointmentButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(radioButton9.isSelected())
				{
					String appDate = "9 -> 12";
					try {
						cont.fillTableVisits(appDate , selectedDoctorString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(radioButton12.isSelected())
				{
					String appDate = "12 -> 3";
					try {
						cont.fillTableVisits(appDate , selectedDoctorString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				if(radioButton3.isSelected())
				{
					String appDate = "3 -> 6";
					try {
						cont.fillTableVisits(appDate , selectedDoctorString);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
                                System.exit(0);			
			}
		});
		makeAppointmentButton.setBackground(new Color(0, 0, 0));
		makeAppointmentButton.setForeground(new Color(255, 255, 255));
		makeAppointmentButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		makeAppointmentButton.setBounds(21, 335, 155, 50);
		frmHospital.getContentPane().add(makeAppointmentButton);
		
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

			new PatientView(cont).createAndShowGUI();
	
	}

	public PatientView(Controller cont) throws HeadlessException {
		super();
		this.cont = cont;
	}
}
