import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Accountant_function extends JFrame{

	private JFrame frame;
	private View_product vp ;
	private JTable table;
	private JTable table_1;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table_2;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		  EventQueue.invokeLater(new Runnable() { 
			  public void run() { 
				 try {
					 new Accountant_function();
		 
		  } catch (Exception e) {
			  e.printStackTrace(); 
			  }
		  } });
		 
		
	}

	/**
	 * Create the application.
	 */
	public Accountant_function() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DOWNLOAD\\coffee-icon (1).png"));
		frame.setBounds(100, 100, 811, 699);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		JLabel lblNewLabel = new JLabel("WELCOME ACCOUNTANT");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(248, 11, 447, 57);
		frame.getContentPane().add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 56, 853, 610);
		frame.getContentPane().add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("View Product", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 848, 582);
		panel.add(scrollPane);
		
		table_2 = new JTable();
		scrollPane.setViewportView(table_2);
		
		Connect connect =  new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select * from Product");
			table_2.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		Sales s = new Sales();
		
		tabbedPane.addTab("Sales", null, s, null);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("D:\\DOWNLOAD\\4370051.jpg"));
		lblNewLabel_1.setBounds(0, 0, 797, 662);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
	}
}
