package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import top.vabook.util.DBConnection;

public class LendDao {
	private static Connection connection;
	private DBConnection dbConnection;
	
	private static PreparedStatement ppst;
	private static String sql = "insert into table_lend(emno,emname,peoplename,lenddate,returndate) values(?,?,?,?,?)";
	public LendDao() {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	public  void insert(String emNo, String emName, String peopleName, String lendDate, String returnDate) {
		try {
			ppst = connection.prepareStatement(sql);
			ppst.setString(1, emNo);
			ppst.setString(2, emName);
			ppst.setString(3, peopleName);
			ppst.setString(4, lendDate);
			ppst.setString(5, returnDate);
			
			ppst.execute();
			
			ppst.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void main(String[] args) {
		new LendDao();
	}
}
