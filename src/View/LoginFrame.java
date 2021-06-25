package View;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.PointerInfo;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.MouseMotionListener;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import Control.Controller;

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {
	private Controller conntrol = new Controller();

	private AdminView admin;

	private static Point mouseDownScreenCoords = null;
	private static Point mouseDownCompCoords = null;
	PointerInfo a = MouseInfo.getPointerInfo();
	private JTextField usernameEntered;
	private JTextField passwordEntered;

	/**
	 * @wbp.parser.entryPoint
	 */

	public void createAndShowGUI() {
        final JFrame frmHospital = new JFrame();
		frmHospital.setResizable(false);
		frmHospital.setPreferredSize(new Dimension(600, 400));
		frmHospital.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

			}

			@Override
			public void mouseExited(MouseEvent arg0) {

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

		JLabel username = new JLabel("Email Address");
		username.setFont(new Font("Tahoma", Font.ITALIC, 16));
		username.setBounds(10, 106, 117, 25);
		frmHospital.getContentPane().add(username);

		JLabel password = new JLabel("Password");
		password.setFont(new Font("Tahoma", Font.ITALIC, 16));
		password.setBounds(10, 195, 102, 25);
		frmHospital.getContentPane().add(password);

		usernameEntered = new JTextField();
		usernameEntered.setBounds(10, 141, 250, 32);
		frmHospital.getContentPane().add(usernameEntered);
		usernameEntered.setColumns(10);

		passwordEntered = new JTextField();
		passwordEntered.setColumns(10);
		passwordEntered.setBounds(10, 228, 250, 32);
		frmHospital.getContentPane().add(passwordEntered);

		JButton signUpButton = new JButton("Sign Up");
		signUpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				SignUpFrame signUp = new SignUpFrame();
				signUp.startClass();
				frmHospital.setVisible(false);
				
			}
		});
		signUpButton.setBackground(new Color(0, 0, 0));
		signUpButton.setForeground(new Color(255, 255, 255));
		signUpButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		signUpButton.setBounds(10, 301, 102, 50);
		frmHospital.getContentPane().add(signUpButton);

		JButton loginButton = new JButton("Log In");
		loginButton.setMargin(new Insets(2, 6, 2, 14));
		loginButton.setHorizontalAlignment(SwingConstants.LEADING);
		loginButton.setIconTextGap(0);
		loginButton.setIcon(new ImageIcon("src/Images/Person_24.jpg"));
		loginButton.setForeground(Color.WHITE);
		loginButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		loginButton.setBackground(Color.BLACK);
		loginButton.setBounds(158, 301, 102, 50);
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String username = usernameEntered.getText();
				String password = passwordEntered.getText();
				
				if ((username.equals("admin")) && (password.equals("admin"))) {
					admin = new AdminView(conntrol);
					admin.startClass();
					frmHospital.setVisible(false);
				}
				else {
				try {
					if (conntrol.AccountValidation(username, password))
					{
						Branches branch = new Branches(conntrol);
						branch.startClass();
						frmHospital.setVisible(false);
					}
					else System.out.println("Account Invalid, Check Your Username And Password!!!");
				} catch (Exception e) {
					e.printStackTrace();
				}
				}
			}
		});
		frmHospital.getContentPane().add(loginButton);

		JLabel logo = new JLabel("");
		logo.setBackground(Color.WHITE);
		logo.setIcon(new ImageIcon("src/Images/hospital_logo_350.jpg"));
		logo.setBounds(270, 30, 330, 370);
		frmHospital.getContentPane().add(logo);
		
		JLabel loginLabel = new JLabel("LOGIN");
		loginLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		loginLabel.setBounds(92, 41, 127, 43);
		frmHospital.getContentPane().add(loginLabel);
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
	public void startClass()
	{

			new LoginFrame().createAndShowGUI();
		
	}
}