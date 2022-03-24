import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JRadioButton;

import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Sales extends JPanel {
	private JTable table;
	private JTextField tfName_pro;
	private JTextField tfNumber;
	private JTextField tfSearch;
	private JTable table_Number;
	private JTable table_Sales;

	/**
	 * Create the panel.
	 */
	public void load_table_Sales_8() {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product");
			table.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load_table_Sales_9() {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product");
			table.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load_table_Total_Sales8 () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select sum (Product.price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product");
			table_Sales.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load_table_Total_Sales9 () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select sum (Product.price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product");
			table_Sales.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load_table_Total_Number9 () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select sum (Number) from Sale_in_september");
			table_Number.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void load_table_Total_Number8 () {
		Connect connect = new Connect();
		connect.connect();
		try {
			ResultSet rst = connect.SelectDB("select sum (Number) from Sale_in_august");
			table_Number.setModel(DbUtils.resultSetToTableModel(rst));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Sales() {
		Connect connect = new Connect();
		setLayout(null);
		
		JComboBox comboBox_Month = new JComboBox();
		comboBox_Month.setModel(new DefaultComboBoxModel(new String[] {"August", "September"}));
		comboBox_Month.setBounds(550, 48, 111, 28);
		add(comboBox_Month);
		
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 459, 299);
		add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JLabel lblTotalNumber = new JLabel("Total Number");
		lblTotalNumber.setBackground(new Color(184, 134, 11));
		lblTotalNumber.setForeground(new Color(128, 0, 0));
		lblTotalNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalNumber.setBounds(128, 332, 89, 23);
		add(lblTotalNumber);
		
		JLabel lblTotalSales = new JLabel("Total Sales");
		lblTotalSales.setForeground(new Color(128, 0, 0));
		lblTotalSales.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblTotalSales.setBounds(128, 366, 89, 23);
		add(lblTotalSales);
		
		JLabel lblNewLabel_3 = new JLabel("Name product");
		lblNewLabel_3.setForeground(new Color(128, 0, 0));
		lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNewLabel_3.setBounds(496, 143, 96, 14);
		add(lblNewLabel_3);
		
		JLabel lblNumber = new JLabel("Number");
		lblNumber.setForeground(new Color(128, 0, 0));
		lblNumber.setFont(new Font("Times New Roman", Font.BOLD, 14));
		lblNumber.setBounds(496, 225, 69, 14);
		add(lblNumber);
		
		tfName_pro = new JTextField();
		tfName_pro.setBounds(630, 140, 96, 20);
		add(tfName_pro);
		tfName_pro.setColumns(10);
		
		tfNumber = new JTextField();
		tfNumber.setBounds(630, 222, 96, 20);
		add(tfNumber);
		tfNumber.setColumns(10);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = comboBox_Month.getSelectedItem().toString();
				Boolean b = tfName_pro.getText().matches("([A-Z]|[a-z]|\\s)+");
				Boolean c = tfNumber.getText().matches("([0-9])+") ;
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Only name in name product !");
				}
				if(c==false) {
					JOptionPane.showMessageDialog(null, "Only Number in quantily !");
				}
				  if(a.equals("August") && b==true && c==true) {
					int record = connect.executeDB("insert into Sale_in_august values ('"+tfName_pro.getText()+"','"+tfNumber.getText()+"')");
					if(record>0) JOptionPane.showMessageDialog(null, "Add Sucessfully");
					load_table_Sales_8();
					load_table_Total_Sales8 () ;
					load_table_Total_Number8 ();
				  }else if(a.equals("September") && b==true && c==true) {
					  int record = connect.executeDB("insert into Sale_in_september values ('"+tfName_pro.getText()+"','"+tfNumber.getText()+"')");
					if(record>0) JOptionPane.showMessageDialog(null, "Add Sucessfully");
					load_table_Sales_9();
					load_table_Total_Sales9 () ;
					load_table_Total_Number9 ();
				  }
				 
			}
		});
		btnAdd.setBounds(54, 436, 89, 23);
		add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = comboBox_Month.getSelectedItem().toString();
				Boolean b = tfName_pro.getText().matches("([A-Z]|[a-z]|\\s)+");
				Boolean c = tfNumber.getText().matches("([0-9])+") ;
				if(b==false) {
					JOptionPane.showMessageDialog(null, "Only name in name product !");
				}
				if(c==false) {
					JOptionPane.showMessageDialog(null, "Only Number in quantily !");
				}
				  if(a.equals("August") && b==true && c==true) {
					  int record = connect.executeDB("update Sale_in_august set Number = '"+tfNumber.getText()+"'where Name_product = '"+tfName_pro.getText()+"'");
						JOptionPane.showMessageDialog(null, "Updated sucessfully");
						load_table_Sales_8();
						load_table_Total_Sales8 () ;
						load_table_Total_Number8 ();
				  }else if(a.equals("September") && b==true && c==true) {
					  int record = connect.executeDB("update Sale_in_september set Number = '"+tfNumber.getText()+"'where Name_product = '"+tfName_pro.getText()+"'");
						JOptionPane.showMessageDialog(null, "Updated sucessfully");
						load_table_Sales_9();
						load_table_Total_Sales9 () ;
						load_table_Total_Number9 ();
				  }
				
				
			}
		});
		btnUpdate.setBounds(194, 436, 89, 23);
		add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = comboBox_Month.getSelectedItem().toString();
				if(a.equals("August")) {
					int record = connect.executeDB("Delete from Sale_in_august where Name_product = '"+tfName_pro.getText()+"'");
					JOptionPane.showMessageDialog(null, "Deleted Sucessfully");
					load_table_Sales_8();
					load_table_Total_Sales8 () ;
					load_table_Total_Number8 ();
				}else if(a.equals("September")) {
					int record = connect.executeDB("Delete from Sale_in_september where Name_product = '"+tfName_pro.getText()+"'");
					JOptionPane.showMessageDialog(null, "Deleted Sucessfully");
					load_table_Sales_9();
					load_table_Total_Sales9 () ;
					load_table_Total_Number9 ();
				}
				
			}
		});
		btnDelete.setBounds(366, 436, 89, 23);
		add(btnDelete);
		
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connect.connect();
				String n = comboBox_Month.getSelectedItem().toString();
				if(n.equals("August")) {
					try {
						ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product and Sale_in_august.Name_product='"+tfSearch.getText()+"'");
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}else if(n.equals("September")) {
					try {
						ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product and Sale_in_september.Name_product= '"+tfSearch.getText()+"'");
						table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		btnSearch.setBounds(194, 497, 89, 23);
		add(btnSearch);
		
		tfSearch = new JTextField();
		tfSearch.setBounds(319, 498, 96, 20);
		add(tfSearch);
		tfSearch.setColumns(10);
		
		
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Number", "Sale"}));
		comboBox.setBounds(676, 436, 104, 23);
		add(comboBox);
		
		
		JRadioButton rdbtnAsc = new JRadioButton("Ascending");
		rdbtnAsc.setBounds(615, 486, 111, 23);
		add(rdbtnAsc);
		
		JRadioButton rdbtndes = new JRadioButton("Descending");
		rdbtndes.setBounds(615, 539, 111, 23);
		add(rdbtndes);
		
		ButtonGroup b = new ButtonGroup();
		b.add(rdbtnAsc);
		b.add(rdbtndes);
		
		
		JButton btnSort = new JButton("Sort");
		btnSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String a = comboBox_Month.getSelectedItem().toString();
				String n = comboBox.getSelectedItem().toString();
				if(n.equals("Number") && rdbtnAsc.isSelected() && a.equals("August") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product order by Sale_in_august.Number ASC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}else if(n.equals("Number") && rdbtndes.isSelected() && a.equals("August") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product order by Sale_in_august.Number DESC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
										} catch (Exception e2) {
											e2.printStackTrace();
										}
				}else if(n.equals("Sale") && rdbtnAsc.isSelected() && a.equals("August") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product order by (Product.Price*Sale_in_august.Number) ASC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
										} catch (Exception e2) {
											e2.printStackTrace();
										}
				}else if(n.equals("Sale") && rdbtndes.isSelected() && a.equals("August") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_august.ID_sale_in_august,Sale_in_august.Name_product,Product.Price,Sale_in_august.Number,(Product.Price*Sale_in_august.Number) as \"ToTal Price \" from Sale_in_august,Product where Sale_in_august.Name_product=Product.Name_product order by (Product.Price*Sale_in_august.Number) DESC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
															} catch (Exception e2) {
																e2.printStackTrace();
															}
				}
				if(n.equals("Number") && rdbtnAsc.isSelected() && a.equals("September") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product order by Sale_in_september.Number ASC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}else if(n.equals("Number") && rdbtndes.isSelected() && a.equals("September") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product order by Sale_in_september.Number DESC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
										} catch (Exception e2) {
											e2.printStackTrace();
										}
				}else if(n.equals("Sale") && rdbtnAsc.isSelected() && a.equals("September") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product order by (Product.Price*Sale_in_september.Number) ASC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
										} catch (Exception e2) {
											e2.printStackTrace();
										}
				}else if(n.equals("Sale") && rdbtndes.isSelected() && a.equals("September") ) {
					try {
	ResultSet rst = connect.SelectDB("select Sale_in_september.ID_sale_in_september,Sale_in_september.Name_product,Product.Price,Sale_in_september.Number,(Product.Price*Sale_in_september.Number) as \"ToTal Price \" from Sale_in_september,Product where Sale_in_september.Name_product=Product.Name_product order by (Product.Price*Sale_in_september.Number) DESC");
	table.setModel(DbUtils.resultSetToTableModel(rst));
															} catch (Exception e2) {
																e2.printStackTrace();
															}
				}
			}
		});
		btnSort.setBounds(529, 436, 89, 23);
		add(btnSort);
		
		
		
		JButton btn = new JButton("View");
		btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String n = comboBox_Month.getSelectedItem().toString();
				if(n.equals("August")) {
					load_table_Sales_8();
					load_table_Total_Sales8 () ;
					load_table_Total_Number8 ();
				}else if(n.equals("September")) {
					load_table_Sales_9();
					load_table_Total_Sales9 () ;
					load_table_Total_Number9 ();
				}
			}
		});
		btn.setBounds(692, 51, 75, 23);
		add(btn);
		
		table_Number = new JTable();
		table_Number.setBounds(227, 337, 75, 19);
		add(table_Number);
		
		table_Sales = new JTable();
		table_Sales.setBounds(227, 370, 75, 19);
		add(table_Sales);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("D:\\DOWNLOAD\\4370051.jpg"));
		lblNewLabel.setBounds(0, 0, 811, 579);
		add(lblNewLabel);
		
	}
}
