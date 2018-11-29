package top.vabook.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	public Connection connection;
	private static String url = "jdbc:mysql://127.0.0.1:3306/ems";
	private static String user = "root";
	private static String password = "root";

	public DBConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
