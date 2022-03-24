import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Product extends JPanel {
	private JTable table;
	private JTextField tfName_pro;
	private JTextField tfUnit;
	private JTextField tfPrice;
	private JTextField tfSearch;
	
	
	
	
	/**
	 * Create the panel.
	 */
	public void load_table_Product () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select * from Product ");
			table.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Product() {
		Connect connect = new Connect();
		
		setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 479, 361);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblName = new JLabel("Name product");
		lblName.setForeground(new Color(128, 0, 0));
		lblName.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblName.setBounds(532, 102, 89, 34);
		add(lblName);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblUnit.setForeground(new Color(128, 0, 0));
		lblUnit.setBounds(532, 199, 89, 24);
		add(lblUnit);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setForeground(new Color(128, 0, 0));
		lblPrice.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblPrice.setBounds(532, 293, 89, 24);
		add(lblPrice);
		
		tfName_pro = new JTextField();
		tfName_pro.setBounds(675, 109, 96, 20);
		add(tfName_pro);
		tfName_pro.setColumns(10);
		
		tfUnit = new JTextField();
		tfUnit.setBounds(675, 201, 96, 20);
		add(tfUnit);
		tfUnit.setColumns(10);
		
		tfPrice = new JTextField();
		tfPrice.setBounds(675, 297, 96, 20);
		add(tfPrice);
		tfPrice.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean a = tfName_pro.getText().matches("([A-Z]|[a-z]|\\s)+") ;
				Boolean c = tfUnit.getText().matches("([A-Z]|[a-z]|\\s)+") ;
				Boolean b = tfPrice.getText().matches("([0-9])+") ;
				if(a==false) {
					JOptionPane.showMessageDialog(null, "Only word in name product !");
					
				}
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Only Number in price !");
					
				}
				if(c==false) {
					JOptionPane.showMessageDialog(null, "Only word in Unit !");
				}
				if(a==true && b==true && c==true)
				{
					int record = connect.executeDB("insert into Product values ('"+tfName_pro.getText()+"','"+tfUnit.getText()+"','"+tfPrice.getText()+"')");
					if(record>0) {
						JOptionPane.showMessageDialog(null, "Add sucessfully");
					}
					load_table_Product () ; 
				}
				
			}
		});
		btnAdd.setBounds(40, 461, 89, 23);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean a = tfName_pro.getText().matches("([A-Z]|[a-z]|\\s)+") ;
				Boolean c = tfUnit.getText().matches("([A-Z]|[a-z]|\\s)+") ;
				Boolean b = tfPrice.getText().matches("([0-9])+") ;
				if(a==false) {
					JOptionPane.showMessageDialog(null, "Only word in name product !");
					
				}
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Only Number in price !");
					
				}
				if(c==false) {
					JOptionPane.showMessageDialog(null, "Only word in Unit !");
				}
				if(a==true && b==true && c==true)
				{
					int record = connect.executeDB("update Product set Unit = '"+tfUnit.getText()+"', Price ='"+tfPrice.getText()+"'where Name_product = '"+tfName_pro.getText()+"'");
					JOptionPane.showMessageDialog(null, "Update sucessfilly");
					load_table_Product () ;
				}
				
			}
		});
		btnUpdate.setBounds(180, 461, 89, 23);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Boolean a = tfName_pro.getText().matches("([A-Z]|[a-z]|\\s)+") ;
				if(a==false) {
					JOptionPane.showMessageDialog(null, "Only word in name product !");
				}
				if(a==true) {
					int record = connect.executeDB("Delete from Product where Name_product = '"+tfName_pro.getText()+"'");
					JOptionPane.showMessageDialog(null, "Delete sucessfully");
					load_table_Product () ;
				}
				
			}
		});
		btnDelete.setBounds(334, 461, 89, 23);
		add(btnDelete);
		
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Boolean a = tfSearch.getText().matches("([A-Z]|[a-z]|\\s)+") ;
					if(a==false) {
						JOptionPane.showMessageDialog(null, "Input Name of product to search !");
					}
					if(a==true) {
						ResultSet rst = connect.SelectDB("select * from Product where Name_product = '"+tfSearch.getText()+"'");
						table.setModel(DbUtils.resultSetToTableModel(rst));
					}
					
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnSearch.setBounds(240, 520, 89, 23);
		add(btnSearch);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(360, 521, 96, 20);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		JRadioButton rdbtnASC = new JRadioButton("Ascending");
		rdbtnASC.setBounds(629, 435, 111, 23);
		add(rdbtnASC);
		
		JRadioButton rdbtnDESC = new JRadioButton("Descending");
		rdbtnDESC.setBounds(629, 497, 111, 23);
		add(rdbtnDESC);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnASC);
		buttonGroup.add(rdbtnDESC);
		
		

		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnASC.isSelected()) {
					try {
						ResultSet rst = connect.SelectDB("select * from Product ORDER BY Price ASC");
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
					
				}else if (rdbtnDESC.isSelected()) {
					try {
						ResultSet rst = connect.SelectDB("select * from Product  ORDER BY Price DESC");
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
				
			}
		});
		btnSort.setBounds(485, 461, 89, 23);
		add(btnSort);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\DOWNLOAD\\4370051.jpg"));
		lblNewLabel.setBounds(0, 0, 859, 563);
		add(lblNewLabel);
		
		JButton btnFind = new JButton("Search Price");
		btnFind.setBounds(240, 403, 89, 23);
		add(btnFind);
		
		load_table_Product () ;
		
	}
}
