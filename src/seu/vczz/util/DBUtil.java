package seu.vczz.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBUtil {

	private static String driver="com.mysql.jdbc.Driver";
	private static String url="jdbc:mysql://localhost:3306/bill_record?characterEncoding=UTF-8&useSSL=false";
	//private static String database="bill_record";
	private static String username="root";
	private static String password="CHINAchen";
	
	static{
		try {
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(url, username, password);	
		return con;
	}
}
