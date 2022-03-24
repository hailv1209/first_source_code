import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class Boss_function extends JFrame  {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					 new Boss_function();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Boss_function() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\DOWNLOAD\\coffee-icon (1).png"));
		frame.setBounds(100, 100, 813, 737);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME BOSS");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 27));
		lblNewLabel.setBounds(274, 11, 283, 69);
		frame.getContentPane().add(lblNewLabel);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 69, 940, 608);
		frame.getContentPane().add(tabbedPane);
		
		Product product = new Product();
		tabbedPane.addTab("Product", null, product, null);
		
		Sales s = new Sales();
		tabbedPane.addTab("Sale", null, s, null);
		
		
		  Manager_account manager_function = new Manager_account();
		  tabbedPane.addTab("Account", null, manager_function, null);
		  
		  JLabel lblNewLabel_1 = new JLabel("");
		  lblNewLabel_1.setIcon(new ImageIcon("D:\\DOWNLOAD\\4370051.jpg"));
		  lblNewLabel_1.setBounds(0, 0, 799, 700);
		  frame.getContentPane().add(lblNewLabel_1);
		 
		frame.setVisible(true);
	}
}
