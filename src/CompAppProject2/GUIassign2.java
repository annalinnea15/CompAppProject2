package CompAppProject2;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridLayout;

import javax.swing.JMenuBar;


import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextPane;

import java.awt.Component;
import java.io.PrintStream;

import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;

public class GUIassign2 {

	private JFrame frame;
	private static JTextField Username_field;
	private static JTextField Password_field;
	private static JTextField active5;
	private static JTextField active7;
	private static JTextField active9;
	private static JTextField Reactive5;
	private static JTextField Reactive7;
	private static JTextField Reactive9;
	private static JTextField Active1;
	private static JTextField Active2;
	private static JTextField Active3;
	private static JTextField Reactive1;
	private static JTextField Reactive2;
	private static JTextField Reactive3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUIassign2 window = new GUIassign2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUIassign2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		//sets the application window size
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.window);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setBounds(200, 0, 1000, 730);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		//setting up the labels and text fields for every parameter
		
		Username_field = new JTextField();
		Username_field.setBounds(145, 201, 103, 20);
		frame.getContentPane().add(Username_field);
		Username_field.setColumns(10);
		Username_field.setText("root");
		
		Password_field = new JPasswordField();
		Password_field.setBounds(145, 232, 103, 20);
		frame.getContentPane().add(Password_field);
		Password_field.setColumns(10);
		Password_field.setText("root");
		
		JLabel lblServerLoginDetails = new JLabel("Server Login Details");
		lblServerLoginDetails.setForeground(Color.LIGHT_GRAY);
		lblServerLoginDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblServerLoginDetails.setBounds(123, 162, 145, 26);
		frame.getContentPane().add(lblServerLoginDetails);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblUsername.setBounds(79, 204, 87, 17);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPassword.setBounds(79, 235, 87, 17);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblEh = new JLabel("EH2745 Computer Application in Electric Power Systems");
		lblEh.setFont(new Font("Trajan Pro", Font.PLAIN, 24));
		lblEh.setBounds(79, 11, 804, 83);
		frame.getContentPane().add(lblEh);
		
		
		JLabel lblNewLabel = new JLabel("Load Bus Details");
		lblNewLabel.setForeground(Color.LIGHT_GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(141, 290, 138, 26);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblBusNo = new JLabel("Bus");
		lblBusNo.setBounds(79, 327, 46, 14);
		frame.getContentPane().add(lblBusNo);
		
		JLabel lblNewLabel_1 = new JLabel("Active Power (Pd) ");
		lblNewLabel_1.setBounds(123, 327, 125, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblReactivePowerqd = new JLabel("Reactive Power (Qd) ");
		lblReactivePowerqd.setBounds(234, 327, 127, 14);
		frame.getContentPane().add(lblReactivePowerqd);
		
		JLabel lblGeneratorBusDetails = new JLabel("Generator Bus Details");
		lblGeneratorBusDetails.setForeground(Color.LIGHT_GRAY);
		lblGeneratorBusDetails.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblGeneratorBusDetails.setBounds(123, 496, 172, 26);
		frame.getContentPane().add(lblGeneratorBusDetails);
		
		JLabel lblBus = new JLabel("Bus");
		lblBus.setBounds(79, 533, 46, 14);
		frame.getContentPane().add(lblBus);
		
		JLabel lblActivePowerpg = new JLabel("Active Power (Pg) ");
		lblActivePowerpg.setBounds(123, 533, 127, 14);
		frame.getContentPane().add(lblActivePowerpg);
		
		JLabel lblReactivePowerqg = new JLabel("Reactive Power (Qg) ");
		lblReactivePowerqg.setBounds(234, 533, 129, 14);
		frame.getContentPane().add(lblReactivePowerqg);
		
		JLabel label_1 = new JLabel("5");
		label_1.setBounds(79, 367, 46, 14);
		frame.getContentPane().add(label_1);
		
		JLabel label_2 = new JLabel("7");
		label_2.setBounds(79, 401, 46, 14);
		frame.getContentPane().add(label_2);
		
		JLabel label_3 = new JLabel("9");
		label_3.setBounds(79, 438, 46, 14);
		frame.getContentPane().add(label_3);
		
		JLabel label_4 = new JLabel("1");
		label_4.setBounds(79, 573, 46, 14);
		frame.getContentPane().add(label_4);
		
		JLabel label_5 = new JLabel("2");
		label_5.setBounds(79, 607, 46, 14);
		frame.getContentPane().add(label_5);
		
		JLabel label_6 = new JLabel("3");
		label_6.setBounds(79, 644, 46, 14);
		frame.getContentPane().add(label_6);
		
		active5 = new JTextField();
		active5.setBounds(123, 367, 86, 20);
		frame.getContentPane().add(active5);
		active5.setColumns(10);
		active5.setText("90");
		
		active7 = new JTextField();
		active7.setColumns(10);
		active7.setBounds(123, 401, 86, 20);
		frame.getContentPane().add(active7);
		active7.setText("100");
		
		active9 = new JTextField();
		active9.setColumns(10);
		active9.setBounds(123, 438, 86, 20);
		frame.getContentPane().add(active9);
		active9.setText("125");
		
		Reactive5 = new JTextField();
		Reactive5.setColumns(10);
		Reactive5.setBounds(235, 367, 86, 20);
		frame.getContentPane().add(Reactive5);
		Reactive5.setText("30");
		
		Reactive7 = new JTextField();
		Reactive7.setColumns(10);
		Reactive7.setBounds(235, 401, 86, 20);
		frame.getContentPane().add(Reactive7);
		Reactive7.setText("35");
		
		Reactive9 = new JTextField();
		Reactive9.setColumns(10);
		Reactive9.setBounds(234, 438, 86, 20);
		frame.getContentPane().add(Reactive9);
		Reactive9.setText("50");
		
		Active1 = new JTextField();
		Active1.setColumns(10);
		Active1.setBounds(123, 567, 86, 20);
		frame.getContentPane().add(Active1);
		Active1.setText("0");
		
		Active2 = new JTextField();
		Active2.setColumns(10);
		Active2.setBounds(123, 601, 86, 20);
		frame.getContentPane().add(Active2);
		Active2.setText("163");
		
		Active3 = new JTextField();
		Active3.setColumns(10);
		Active3.setBounds(123, 638, 86, 20);
		frame.getContentPane().add(Active3);
		Active3.setText("85");
		
		Reactive1 = new JTextField();
		Reactive1.setColumns(10);
		Reactive1.setBounds(234, 567, 86, 20);
		frame.getContentPane().add(Reactive1);
		Reactive1.setText("0");
		
		Reactive2 = new JTextField();
		Reactive2.setColumns(10);
		Reactive2.setBounds(234, 601, 86, 20);
		frame.getContentPane().add(Reactive2);
		Reactive2.setText("0");
		
		Reactive3 = new JTextField();
		Reactive3.setColumns(10);
		Reactive3.setBounds(234, 638, 86, 20);
		frame.getContentPane().add(Reactive3);
		Reactive3.setText("0");
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(383, 159, 480, 502);
		frame.getContentPane().add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
		textArea.setEditable(false);
		System.setOut(printStream);
		System.setErr(printStream);
		
		//starts program execution once the Start! button is pressed
		
		JButton btnNewButton = new JButton("Start!");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MainCalcs start = new MainCalcs();
				MainCalcs.main(null);
			}
		});
		btnNewButton.setBounds(258, 201, 87, 47);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblAssignment = new JLabel("Assignment 2 - Anna & Kaustubh");
		lblAssignment.setForeground(Color.GRAY);
		lblAssignment.setFont(new Font("Trajan Pro", Font.PLAIN, 20));
		lblAssignment.setBounds(307, 65, 804, 83);
		frame.getContentPane().add(lblAssignment);
		
		JLabel lblMw = new JLabel("MW");
		lblMw.setForeground(Color.LIGHT_GRAY);
		lblMw.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMw.setBounds(157, 342, 34, 14);
		frame.getContentPane().add(lblMw);
		
		JLabel label = new JLabel("MW");
		label.setForeground(Color.LIGHT_GRAY);
		label.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label.setBounds(157, 545, 34, 14);
		frame.getContentPane().add(label);
		
		JLabel lblMvar = new JLabel("MVAR");
		lblMvar.setForeground(Color.LIGHT_GRAY);
		lblMvar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblMvar.setBounds(258, 342, 34, 14);
		frame.getContentPane().add(lblMvar);
		
		JLabel label_7 = new JLabel("MVAR");
		label_7.setForeground(Color.LIGHT_GRAY);
		label_7.setFont(new Font("Tahoma", Font.PLAIN, 11));
		label_7.setBounds(258, 545, 34, 14);
		frame.getContentPane().add(label_7);
		
		
		
		
	}
	
	
	//function to access the GUI parameters into the program
	
	public static String GetUsername(){
		return Username_field.getText();
	}
	public static String GetPassword(){
		return Password_field.getText();
	}
	public static Double Getactive5(){
		return Double.parseDouble(active5.getText());
	}
	public static Double Getactive7(){
		return Double.parseDouble(active7.getText());
	}
	public static Double Getactive9(){
		return Double.parseDouble(active9.getText());
	}
	public static Double GetReactive5(){
		return Double.parseDouble(Reactive5.getText());
	}
	public static Double GetReactive7(){
		return Double.parseDouble(Reactive7.getText());
	}
	public static Double GetReactive9(){
		return Double.parseDouble(Reactive9.getText());
	}
	public static Double Getactive1(){
		return Double.parseDouble(Active1.getText());
	}
	public static Double Getactive2(){
		return Double.parseDouble(Active2.getText());
	}
	public static Double Getactive3(){
		return Double.parseDouble(Active3.getText());
	}
	public static Double GetReactive1(){
		return Double.parseDouble(Reactive1.getText());
	}
	public static Double GetReactive2(){
		return Double.parseDouble(Reactive2.getText());
	}
	public static Double GetReactive3(){
		return Double.parseDouble(Reactive3.getText());
	}
	
	
}
