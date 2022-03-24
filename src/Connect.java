import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Connect {
	Connection conn;
	Statement stmt;
	public void connect() {
		try {
		//load driver
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//start connecting EmployeeDB database
		conn = DriverManager.getConnection("jdbc:sqlserver://localhost\\SQLEXPRESS;databaseName=Sales_management;user=sa;password=123456");
		System.out.println("Connected");
		}
	catch (Exception e){
		e.printStackTrace();
	}
		}
//excute table
	public int executeDB(String sql) {
		int record = 0;
		try {
			connect();
			stmt=conn.createStatement();
			record = stmt.executeUpdate(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return record;
	}
	
	public ResultSet SelectDB(String sql) {
		ResultSet rs=null;
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return rs;
		
	}
}
