import java.sql.ResultSet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

public class Manager_account extends JPanel {
	private JTextField tfsearch;
	private JTable table_account;
	private JTextField tfusername;
	private JPasswordField passwordField;
	
	
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

	
	public Manager_account () {
		Connect connect = new Connect();
		
		setLayout(null);
		
		
		JComboBox comboBox1 = new JComboBox();
		comboBox1.setModel(new DefaultComboBoxModel(new String[] {"Boss", "Manager", "Accountant"}));
		comboBox1.setBounds(605, 289, 96, 18);
		add(comboBox1);
		
		
		JButton btnadd = new JButton("ADD");
		btnadd.addActionListener(new ActionListener() {
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
				int record = connect.executeDB("insert into Account values ('"+tfusername.getText()+"','"+passwordField.getText()+"','"+comboBox1.getSelectedItem().toString()+"')");
				if(record>0) JOptionPane.showMessageDialog(null, "Add successfully");
				load_Account () ;
					}
			}
		});
		btnadd.setBounds(77, 403, 101, 29);
		add(btnadd);
		
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
		add(btnUpdate);
		
		JButton btndelete = new JButton("DELETE");
		btndelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int record = connect.executeDB("delete from Account where username = '"+tfusername.getText()+"'");
				JOptionPane.showMessageDialog(null, "Deleted successfully");
				load_Account ();
				
			}
		});
		btndelete.setBounds(453, 403, 101, 29);
		add(btndelete);
		
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
		tfsearch.setBounds(408, 496, 113, 20);
		add(tfsearch);
		tfsearch.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(45, 92, 364, 297);
		add(scrollPane);
		
		table_account = new JTable();
		scrollPane.setViewportView(table_account);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setForeground(new Color(128, 0, 0));
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_1.setBounds(469, 128, 62, 14);
		add(lblNewLabel_1);
		
		tfusername = new JTextField();
		tfusername.setBounds(605, 125, 96, 20);
		add(tfusername);
		tfusername.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setForeground(new Color(128, 0, 0));
		lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_2.setBounds(472, 212, 59, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Acess right");
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(469, 293, 68, 14);
		add(lblNewLabel_3);
		
		
		
		passwordField = new JPasswordField();
		passwordField.setBounds(605, 209, 96, 17);
		add(passwordField);
		
		JLabel lblNewLabel_4 = new JLabel("SEARCH USERNAME");
		lblNewLabel_4.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_4.setBounds(243, 492, 155, 29);
		add(lblNewLabel_4);
		
		JLabel lblshowpass = new JLabel("");
		
		
		lblshowpass.setBounds(605, 237, 96, 18);
		add(lblshowpass);
		
		
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
		add(btnSeePass);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\\\DOWNLOAD\\\\4370051.jpg"));
		lblNewLabel.setBounds(0, 0, 859, 563);
		add(lblNewLabel);
		
		
		load_Account () ; 
		
		
		
	}
	
	
}
