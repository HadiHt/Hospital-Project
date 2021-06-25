package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
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

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Control.Controller;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

public class SignUpFrame extends JFrame 
{
	private static Point mouseDownScreenCoords = null;
	private static Point mouseDownCompCoords = null;
	PointerInfo a = MouseInfo.getPointerInfo();
	private JTextField usernameEntered;
	private JTextField passwordEntered;
	private JTextField firstTextField;
	private JTextField lastTextField;

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createAndShowGUI() 
	{
		final JFrame frmHospital = new JFrame();
		frmHospital.setResizable(false);
		frmHospital.setPreferredSize(new Dimension(600, 600));
		frmHospital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frmHospital.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frmHospital.setUndecorated(true);
		frmHospital.addMouseListener(new MouseListener() 
		{
			public void mouseReleased(MouseEvent e) 
			{
				mouseDownScreenCoords = null;
				mouseDownCompCoords = null;
			}

			public void mousePressed(MouseEvent e) 
			{
				mouseDownScreenCoords = e.getLocationOnScreen();
				mouseDownCompCoords = e.getPoint();
			}

			public void mouseExited(MouseEvent e) 
			{
			}

			public void mouseEntered(MouseEvent e) 
			{
			}

			public void mouseClicked(MouseEvent e) 
			{
			}
		});

		frmHospital.addMouseMotionListener(new MouseMotionListener() 
		{
			public void mouseMoved(MouseEvent arg0) 
			{
			}

			public void mouseDragged(MouseEvent e) 
			{
				Point currCoords = e.getLocationOnScreen();
				frmHospital.setLocation(
						mouseDownScreenCoords.x + (currCoords.x - mouseDownScreenCoords.x) - mouseDownCompCoords.x,
						mouseDownScreenCoords.y + (currCoords.y - mouseDownScreenCoords.y) - mouseDownCompCoords.y);
			}

		});
		frmHospital.addComponentListener(new ComponentAdapter() 
		{
			@Override
			public void componentResized(ComponentEvent e) 
			{
				titleAlign(frmHospital);
			}

		});

		frmHospital.pack();
		frmHospital.setLocationRelativeTo(null);

		JLabel xButton = new JLabel("");
		xButton.setBounds(560, 0, 30, 30);
		xButton.setIcon(new ImageIcon("src/Images/X-icon-34.jpg"));
		xButton.addMouseListener(new MouseListener() 
		{
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

		JLabel username = new JLabel("Email Address:");
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setBounds(10, 290, 117, 25);
		frmHospital.getContentPane().add(username);

		JLabel password = new JLabel("Password:");
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setBounds(10, 371, 102, 25);
		frmHospital.getContentPane().add(password);

		usernameEntered = new JTextField();
		usernameEntered.setBounds(10, 325, 250, 32);
		frmHospital.getContentPane().add(usernameEntered);
		usernameEntered.setColumns(10);

		passwordEntered = new JTextField();
		passwordEntered.setColumns(10);
		passwordEntered.setBounds(10, 404, 250, 32);
		frmHospital.getContentPane().add(passwordEntered);

		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBackground(new Color(0, 0, 0));
		cancelButton.setForeground(new Color(255, 255, 255));
		cancelButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		cancelButton.setBounds(10, 528, 102, 50);
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame signIn = new LoginFrame();
				signIn.startClass();
				frmHospital.setVisible(false);
			}
		});
		frmHospital.getContentPane().add(cancelButton);

		JLabel logo = new JLabel("");
		logo.setBackground(Color.WHITE);
		logo.setIcon(new ImageIcon("src/Images/hospital_logo_350.jpg"));
		logo.setBounds(270, 30, 330, 535);
		frmHospital.getContentPane().add(logo);
		
		JLabel signUpLabel = new JLabel("REGISTER");
		signUpLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		signUpLabel.setBounds(80, 41, 127, 43);
		frmHospital.getContentPane().add(signUpLabel);
		
		String[] combo = { "Please Select Your Role!", "Doctor", "Patient" };
		final JComboBox roleComboBox = new JComboBox(combo);
		roleComboBox.setMaximumRowCount(3);
		roleComboBox.setBounds(10, 93, 250, 30);
		frmHospital.getContentPane().add(roleComboBox);
		
		JLabel firstName = new JLabel("First Name:");
		firstName.setFont(new Font("Tahoma", Font.ITALIC, 16));
		firstName.setBounds(10, 134, 102, 25);
		frmHospital.getContentPane().add(firstName);
		
		firstTextField = new JTextField();
		firstTextField.setColumns(10);
		firstTextField.setBounds(10, 169, 250, 32);
		frmHospital.getContentPane().add(firstTextField);
		
		JLabel lastName = new JLabel("Last Name:");
		lastName.setFont(new Font("Tahoma", Font.ITALIC, 16));
		lastName.setBounds(10, 212, 102, 25);
		frmHospital.getContentPane().add(lastName);
		
		lastTextField = new JTextField();
		lastTextField.setColumns(10);
		lastTextField.setBounds(10, 247, 250, 32);
		frmHospital.getContentPane().add(lastTextField);
		
		String[] day = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", 
				          "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"};
		
		final JComboBox dayComboBox = new JComboBox(day);
		dayComboBox.setMaximumRowCount(13);
		dayComboBox.setBounds(10, 482, 70, 22);
		frmHospital.getContentPane().add(dayComboBox);
		
		String[] year = { "", "1970", "1971", "1972", "1973", "1974", "1975", "1976", "1977", "1978", "1979","1980" , "1981", "1982", "1983", 
				          "1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992", "1993", "1994", "1995", "1996", "1997", "1998", 
				          "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006", "2007", "2008", "2009", "2010", "2011", "2012", "2013", 
				          "2014", "2015", "2016", "2017", "2018", "2019", "2020", "2021"};
		
		final JComboBox yearComboBox = new JComboBox(year);
		yearComboBox.setMaximumRowCount(13);
		yearComboBox.setBounds(190, 482, 70, 22);
		frmHospital.getContentPane().add(yearComboBox);
		
		String[] month = { "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
		final JComboBox monthComboBox = new JComboBox(month);
		monthComboBox.setMaximumRowCount(13);
		monthComboBox.setBounds(100, 482, 70, 22);
		frmHospital.getContentPane().add(monthComboBox);
		
		JLabel dateOfBirth = new JLabel("Date Of Birth:");
		dateOfBirth.setFont(new Font("Tahoma", Font.ITALIC, 16));
		dateOfBirth.setBounds(10, 447, 102, 25);
		frmHospital.getContentPane().add(dateOfBirth);
		
		JButton signUpButton = new JButton("Sign Up");
		signUpButton.setForeground(Color.WHITE);
		signUpButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		signUpButton.setBackground(Color.BLACK);
		signUpButton.setBounds(158, 528, 102, 50);
		signUpButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0)
			{
				String role = roleComboBox.getSelectedItem().toString();
				int indexRole = roleComboBox.getSelectedIndex();
				String firstName = firstTextField.getText();
				String lastName = lastTextField.getText();
				String name = firstName + " " + lastName;
				String username = usernameEntered.getText();
				String password = passwordEntered.getText();
				String day = dayComboBox.getSelectedItem().toString();
				int indexDay = dayComboBox.getSelectedIndex();
				String month = monthComboBox.getSelectedItem().toString();
				int indexMonth = monthComboBox.getSelectedIndex();
				String year = yearComboBox.getSelectedItem().toString();
				int indexYear = yearComboBox.getSelectedIndex();
				String birthdate = day+"/"+month+"/"+year;

				if ((indexRole == 0) || (indexDay == 0) || (indexMonth == 0) || (indexYear == 0) || (firstName.equals(""))
						|| lastName.equals("") || username.equals("") || password.equals(""))
				{
					System.out.println("Please Fill All Variables!");
				}
				else 
				{
					Controller con = new Controller();
					try {
						con.AccountSignUp(name, username, password, birthdate, role);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					LoginFrame signIn = new LoginFrame();
					signIn.startClass();
					frmHospital.setVisible(false);
				}
			}
		});
		frmHospital.getContentPane().add(signUpButton);
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
	public void startClass() {

			new SignUpFrame().createAndShowGUI();
		
	}

	private void mouseClicked(MouseEvent e) {
		String s = "X-Corrdinate = " + e.getX() + " y-Coordinate = " + e.getY();
		System.out.println(s);
		System.exit(0);
	}
}
