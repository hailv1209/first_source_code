import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;
import java.awt.Toolkit;

public class Manager_function extends JFrame {

	private JFrame frame;
	private JTextField tfsearch;
	private JTable table_account;
	private JTextField tfusername;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new Manager_function();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Manager_function() {
		initialize();
		load_Account ();
	}
	public void load_Account () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select * from Account ");
			table_account.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Connect connect = new Connect();
		connect.connect();
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DOWNLOAD\\coffee-icon (1).png"));
		frame.setBounds(100, 100, 834, 595);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("WELCOME MANAGER");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(272, 0, 313, 62);
		frame.getContentPane().add(lblNewLabel);
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Boss", "Manager", "Accountant"}));
		comboBox1.setBounds(605, 289, 96, 18);
		frame.getContentPane().add(comboBox1);
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			   Boolean a = passwordField.getText().matches("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{3,10}$");
			   Boolean b = tfusername.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,20}$");
			 
				if(a==false) {
					JOptionPane.showMessageDialog(null, "Password include number and word (no more than 10 characters) !");
				}
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Username must include number and word (up case in the first and no more than 20 characters)  !" );
				}
				
				if(a==true && b==true ) {
					int record = connect.executeDB("insert into Account values ('"+tfusername.getText()+"','"+passwordField.getText()+"','"+comboBox1.getSelectedItem().toString()+"')");
					if(record>0) JOptionPane.showMessageDialog(null, "Add successfully");
					load_Account () ;
				}
				
			}
		});
		btnadd.setBounds(77, 403, 101, 29);
		frame.getContentPane().add(btnadd);
		
		JButton btnUpdate = new JButton("UPDATE");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Boolean a = passwordField.getText().matches("^(?=.*[a-z])(?=.*\\d)[a-z\\d]{3,}$");
				   Boolean b = tfusername.getText().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{3,}$");
				 
					if(a==false) {
						JOptionPane.showMessageDialog(null, "Password include number and word !");
					}
					if(b==false) {
						JOptionPane.showMessageDialog(null, "Username must include number and word (up case in the first)  !" );
					}
				
				
					if(a==true && b==true ) {
				int record = connect.executeDB("update Account set  password = '"+passwordField.getText()+"', acess_right = '"+comboBox1.getSelectedItem().toString()+"' where username = '"+tfusername.getText()+"'");
				JOptionPane.showMessageDialog(null, "Updated successfully");
				load_Account ();
					}
			}
		});
		btnUpdate.setBounds(262, 403, 101, 29);
		frame.getContentPane().add(btnUpdate);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int record = connect.executeDB("delete from Account where username = '"+tfusername.getText()+"'");
				JOptionPane.showMessageDialog(null, "Deleted successfully");
				load_Account ();
				
			}
		});
		btndelete.setBounds(453, 403, 101, 29);
		frame.getContentPane().add(btndelete);
		
		tfsearch = new JTextField();
		tfsearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
					String username = tfsearch.getText();
					ResultSet rst = connect.SelectDB("Select password,acess_right from Account where username = '"+username+"'");
					if(rst.next()==true)
					{
						String pass = rst.getString(1);
						String acess = rst.getString(2);
						tfusername.setText(username);
						passwordField.setText(pass);
						
							
								comboBox1.getModel().setSelectedItem(acess);
							
						
						
					}else {
						tfusername.setText("");
						passwordField.setText("");
						comboBox1.setSelectedItem("");
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		tfsearch.setBounds(400, 496, 113, 20);
		frame.getContentPane().add(tfsearch);
		tfsearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 92, 364, 297);
		frame.getContentPane().add(scrollPane);
		
		table_account = new JTable();
		scrollPane.setViewportView(table_account);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setForeground(new Color(220, 20, 60));
		lblNewLabel_1.setBounds(469, 128, 62, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		tfusername = new JTextField();
		tfusername.setBounds(605, 125, 96, 20);
		frame.getContentPane().add(tfusername);
		tfusername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setBounds(472, 212, 59, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Acess right");
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setBounds(469, 293, 68, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(605, 209, 96, 17);
		frame.getContentPane().add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("SEARCH USERNAME");
		lblNewLabel_4.setForeground(new Color(128, 0, 0));
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(254, 491, 155, 29);
		frame.getContentPane().add(lblNewLabel_4);
		
		JLabel lblshowpass = new JLabel("");
		
		
		lblshowpass.setBounds(605, 237, 96, 18);
		frame.getContentPane().add(lblshowpass);
		
		
		JButton btnSeePass = new JButton("");
		btnSeePass.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblshowpass.setText(passwordField.getText());
			}
			@Override
			public void mouseExited(MouseEvent e) {
				lblshowpass.setText("");
			}
		});
		btnSeePass.setIcon(new ImageIcon("D:\\DOWNLOAD\\eye-icon.png"));
		btnSeePass.setBounds(722, 203, 26, 23);
		frame.getContentPane().add(btnSeePass);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon("D:\\DOWNLOAD\\4370051.jpg"));
		lblNewLabel_5.setBounds(0, 0, 820, 558);
		frame.getContentPane().add(lblNewLabel_5);
		
		frame.setVisible(true);
	}
}
