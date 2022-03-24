import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Account {

	private JFrame frame;
	private JTextField tfusername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Account window = new Account();
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
	public Account() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect connect = new Connect();
		connect.connect();
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DOWNLOAD\\coffee-icon (1).png"));
		frame.setBounds(100, 100, 539, 380);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lbluser = new JLabel("Username");
		lbluser.setForeground(new Color(128, 0, 0));
		lbluser.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lbluser.setBounds(92, 114, 64, 27);
		frame.getContentPane().add(lbluser);
		
		JLabel lblPassword = new JLabel("Password\r\n");
		lblPassword.setForeground(new Color(128, 0, 0));
		lblPassword.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPassword.setBounds(92, 173, 64, 27);
		frame.getContentPane().add(lblPassword);
		
		tfusername = new JTextField();
		tfusername.setBounds(177, 118, 137, 20);
		frame.getContentPane().add(tfusername);
		tfusername.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Boss", "Manager", "Accountant"}));
		comboBox.setBounds(338, 146, 126, 22);
		frame.getContentPane().add(comboBox);
		
		JLabel lblNewLabel_2 = new JLabel("Access right");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(368, 121, 84, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean a = tfusername.getText().equals("");
				boolean b = passwordField.getText().equals("");
				Boolean c = passwordField.getText().matches("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{3,}$");
			    Boolean d = tfusername.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,}$");
				if(a==true || b==true) {
					  JOptionPane.showMessageDialog(null, "Can not be null !");
					  }
				  
				 
					
				ResultSet rs= connect.SelectDB("select * from Account where username = '"+tfusername.getText()+"'and password = '"+passwordField.getText()+"'and acess_right = '"+comboBox.getSelectedItem().toString()+"'");
				String n = comboBox.getSelectedItem().toString();
				try {
					if(rs.next() && a==false && b==false ) {
		
						if(n.equals("Boss")) {
							
							  Boss_function bf = new Boss_function();
							  bf.setVisible(true);
							  	
						}else if(n.equals("Manager")) {
							Manager_function window = new Manager_function(); 
							  window.setVisible(true);
						}else if(n.equals("Accountant")) {
							Accountant_function af = new Accountant_function();
							af.setVisible(true);
						}
					}
					else{
						JOptionPane.showMessageDialog(null, "Invalid Username or Password !");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		btnNewButton.setBounds(230, 248, 84, 37);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("WELCOME");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 32));
		lblNewLabel_1.setBounds(177, 11, 190, 57);
		frame.getContentPane().add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(177, 177, 137, 20);
		frame.getContentPane().add(passwordField);
		
		
		JLabel lblseepass = new JLabel("");
		lblseepass.setBounds(230, 197, 137, 14);
		frame.getContentPane().add(lblseepass);
		
		JButton btnNewButton_1 = new JButton("");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblseepass.setText(passwordField.getText());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblseepass.setText("");
			}
		});
		btnNewButton_1.setIcon(new ImageIcon("D:\\DOWNLOAD\\eye-icon.png"));
		btnNewButton_1.setBounds(348, 177, 39, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("D:\\DOWNLOAD\\coffee background.png"));
		lblNewLabel.setBounds(0, 0, 525, 343);
		frame.getContentPane().add(lblNewLabel);
		
	 
	}
}
