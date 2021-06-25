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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Control.Controller;

public class DoctorView extends JFrame {

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
		
		final JLabel label2 = new JLabel("Please Choose Specialization First!");
		label2.setForeground(Color.BLUE);
		label2.setFont(new Font("Arial", Font.ITALIC, 9));
		label2.setBounds(23, 200, 155, 25);
		frmHospital.getContentPane().add(label2);
		
		JLabel loginLabel = new JLabel("WELCOME!");
		loginLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 24));
		loginLabel.setBounds(236, 41, 127, 43);
		frmHospital.getContentPane().add(loginLabel);
		
		String[] specialization  = {"Specialization", "Neurology", "Surgery", "Hematology", "Physical Therapy", "Anesthesiology", "Allergy And Immunology"};
		final JComboBox specializationComboBox = new JComboBox(specialization);
		specializationComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				label2.setVisible(false);
				
				String selected = specializationComboBox.getSelectedItem().toString();
				if (selected.equals("Specialization"))
				{
					System.out.println("Please Choose A Right Specialization");
				} 
				else 
				{
					try {
						cont.updateSpecializationDoctorTable(selected);
						
						
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				
			}});

		specializationComboBox.setFont(new Font("Tahoma", Font.PLAIN, 12));
		specializationComboBox.setMaximumRowCount(5);
		specializationComboBox.setBounds(19, 110, 160, 22);
		frmHospital.getContentPane().add(specializationComboBox);
		
		String[] firstTime = new String[2];
		firstTime[0] = "-";
		firstTime[1] = "9 -> 12";
		
		final JComboBox workTimeComboBox1 = new JComboBox(firstTime);
		workTimeComboBox1.setMaximumRowCount(5);
		workTimeComboBox1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		workTimeComboBox1.setBounds(20, 180, 90, 22);
		frmHospital.getContentPane().add(workTimeComboBox1);
		
		String[] secondTime = new String[2];
		secondTime[0] = "-";
		secondTime[1] = "12 -> 3";
		
		final JComboBox workTimeComboBox2 = new JComboBox(secondTime);
		workTimeComboBox2.setMaximumRowCount(5);
		workTimeComboBox2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		workTimeComboBox2.setBounds(120, 180, 90, 22);
		frmHospital.getContentPane().add(workTimeComboBox2);
		
		String[] thirdTime = new String[2];
		thirdTime[0] = "-";
		thirdTime[1] = "3 -> 6";
		
		final JComboBox workTimeComboBox3 = new JComboBox(thirdTime);
		workTimeComboBox3.setMaximumRowCount(5);
		workTimeComboBox3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		workTimeComboBox3.setBounds(220, 180, 90, 22);
		frmHospital.getContentPane().add(workTimeComboBox3);
		
		JLabel logo = new JLabel("");
		logo.setBackground(Color.WHITE);
		logo.setIcon(new ImageIcon("src/Images/hospital_logo_350.jpg"));
		logo.setBounds(278, 80, 322, 320);
		frmHospital.getContentPane().add(logo);
		
		JLabel workLabel = new JLabel("Choose Your Work Hours Below !");
		workLabel.setBounds(20, 155, 169, 14);
		frmHospital.getContentPane().add(workLabel);
		
		JButton applyForWorkButton = new JButton("Apply For Work");		
		applyForWorkButton.setBackground(new Color(0, 0, 0));
		applyForWorkButton.setForeground(new Color(255, 255, 255));
		applyForWorkButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		applyForWorkButton.setBounds(23, 271, 155, 50);
		frmHospital.getContentPane().add(applyForWorkButton);
		specializationComboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				label2.setVisible(false);
					try {
						cont.updateSpecializationDoctorTable(specializationComboBox.getSelectedItem().toString());
						
					} catch (Exception e) {
						e.printStackTrace();
					}}});
		applyForWorkButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
					String selected1 = workTimeComboBox1.getSelectedItem().toString(); 
					String selected2 = workTimeComboBox2.getSelectedItem().toString(); 
					String selected3 = workTimeComboBox3.getSelectedItem().toString(); 
					
					String workTime = selected1 +","+ selected2 +","+ selected3;
					cont.updateDoctorWorkTime(workTime);
					System.exit(0);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
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
			new DoctorView(cont).createAndShowGUI();
	}
	public DoctorView(Controller cont) throws HeadlessException {
		super();
		this.cont = cont;
	}
}