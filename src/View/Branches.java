package View;

import java.awt.BorderLayout;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Control.Controller;

import java.awt.SystemColor;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class Branches extends JFrame {
	Controller cont;
	private static Point mouseDownScreenCoords = null;
	private static Point mouseDownCompCoords = null;
	PointerInfo a = MouseInfo.getPointerInfo();

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
		frmHospital.getContentPane().add(xButton);
		frmHospital.getContentPane().add(frameTitle);
		frmHospital.getContentPane().add(titleBar);
		
		JLabel branch = new JLabel("BRANCHES");
		branch.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		branch.setBounds(236, 41, 127, 43);
		frmHospital.getContentPane().add(branch);
		
		final JRadioButton beirutButton = new JRadioButton("");
		beirutButton.setBounds(69, 256, 109, 23);
		frmHospital.getContentPane().add(beirutButton);
		
		final JRadioButton tripoliButton = new JRadioButton("");
		tripoliButton.setBounds(210, 256, 109, 23);
		frmHospital.getContentPane().add(tripoliButton);
		
		final JRadioButton tyreButton = new JRadioButton("");
		tyreButton.setBounds(370, 256, 109, 23);
		frmHospital.getContentPane().add(tyreButton);
		
		final JRadioButton sidonButton = new JRadioButton("");
		sidonButton.setBounds(511, 256, 109, 23);
		frmHospital.getContentPane().add(sidonButton);
		frmHospital.setVisible(true);
		
		String[] combo = { "Please Select Your Role!", "Doctor", "Patient" };
		
		String[] day = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		String[] year = { "", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"};
		
		String[] month = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		
		JButton chooseBranchButton = new JButton("Choose Branch");
		chooseBranchButton.setForeground(Color.WHITE);
		chooseBranchButton.setFont(new Font("Tahoma", Font.BOLD, 12));
		chooseBranchButton.setBackground(Color.BLACK);
		chooseBranchButton.setBounds(235, 305, 130, 50);
		chooseBranchButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				String branch = null;
				if (beirutButton.isSelected())
				{
					branch = "Beirut";
				}
				else if (tripoliButton.isSelected())
				{
					branch = "Tripoli";
				}
				else if (tyreButton.isSelected())
				{
					branch = "Tyre";
				}
				else if (sidonButton.isSelected())
				{
					branch = "Sidon";
				}
				else 
				{
					System.out.println("Please Select a Branch!");
				}
				if (branch != null)
				{
					try 
					{
						if(cont.isPatient())
						{
						cont.updateDepartmentPatientTable(branch);
						PatientView patientview = new PatientView(cont);
						patientview.startClass();
						}
						if(cont.isDoctor())
						{
							cont.updateDepartmentDoctorTable(branch);
							DoctorView doctorview = new DoctorView(cont);
							doctorview.startClass();
						}

					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
				frmHospital.setVisible(false);
			}
		});
		frmHospital.getContentPane().add(chooseBranchButton);
		
		JLabel beirutBranch = new JLabel("");
		beirutBranch.setHorizontalAlignment(SwingConstants.CENTER);
		beirutBranch.setBackground(Color.WHITE);
		beirutBranch.setIcon(new ImageIcon("src/Images/hospital_100.jpg"));
		beirutBranch.setBounds(10, 114, 140, 140);
		frmHospital.getContentPane().add(beirutBranch);
		
		JLabel tripoliBranch = new JLabel("");
		tripoliBranch.setIcon(new ImageIcon("src/Images/hospital_100.jpg"));
		tripoliBranch.setHorizontalAlignment(SwingConstants.CENTER);
		tripoliBranch.setBackground(Color.WHITE);
		tripoliBranch.setBounds(150, 114, 140, 140);
		frmHospital.getContentPane().add(tripoliBranch);
		
		JLabel sidonBranch = new JLabel("");
		sidonBranch.setIcon(new ImageIcon("src/Images/hospital_100.jpg"));
		sidonBranch.setHorizontalAlignment(SwingConstants.CENTER);
		sidonBranch.setBackground(Color.WHITE);
		sidonBranch.setBounds(450, 114, 140, 140);
		frmHospital.getContentPane().add(sidonBranch);
		
		JLabel tyreBranch = new JLabel("");
		tyreBranch.setIcon(new ImageIcon("src/Images/hospital_100.jpg"));
		tyreBranch.setHorizontalAlignment(SwingConstants.CENTER);
		tyreBranch.setBackground(Color.WHITE);
		tyreBranch.setBounds(311, 114, 140, 140);
		frmHospital.getContentPane().add(tyreBranch);
		
		JLabel beirutLabel = new JLabel("Beirut");
		beirutLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		beirutLabel.setHorizontalAlignment(SwingConstants.CENTER);
		beirutLabel.setBounds(31, 235, 100, 20);
		frmHospital.getContentPane().add(beirutLabel);
		
		JLabel tripoliLabel = new JLabel("Tripoli");
		tripoliLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tripoliLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		tripoliLabel.setBounds(170, 234, 100, 20);
		frmHospital.getContentPane().add(tripoliLabel);
		
		JLabel tyreLabel = new JLabel("Tyre");
		tyreLabel.setHorizontalAlignment(SwingConstants.CENTER);
		tyreLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		tyreLabel.setBounds(330, 234, 100, 20);
		frmHospital.getContentPane().add(tyreLabel);
		
		JLabel sidonLabel = new JLabel("Sidon");
		sidonLabel.setHorizontalAlignment(SwingConstants.CENTER);
		sidonLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		sidonLabel.setBounds(471, 234, 100, 20);
		frmHospital.getContentPane().add(sidonLabel);
		
		ButtonGroup group = new ButtonGroup();
		group.add(beirutButton);
		group.add(tripoliButton);
		group.add(tyreButton);
		group.add(sidonButton);
		
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

	public Controller getCon() {
		return cont;
	}

	public void setCon(Controller conn) {
		this.cont = conn;
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

	public void startClass() {

			new Branches(cont).createAndShowGUI();
		
	}

	public Branches(Controller cont) throws HeadlessException {
		super();
		this.cont = cont;
	}
}