package seu.vczz.util;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

public class DBUtil {
	//擦，加了private之后用不了了，去掉
	static String driver="com.mysql.jdbc.Driver";
	static String url="jdbc:mysql://localhost:3306/bill_record?characterEncoding=UTF-8&useSSL=false";
	//private static String database="bill_record";
	static String username="root";
	static String password="CHINAchen";
	static int port = 3306;
	static String database = "bill_record";
	
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
