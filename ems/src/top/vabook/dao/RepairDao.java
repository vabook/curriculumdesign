package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.colorchooser.ColorChooserComponentFactory;

import top.vabook.util.DBConnection;

public class RepairDao {
	private Connection connection;
	
	private DBConnection dbConnection;

	private static PreparedStatement ppst;
	
	private static String sql = "insert into table_repair(date,people,cost,emno,emname) values(?,?,?,?,?)";
	
	public RepairDao() {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	public void insert(String date, String people, int cost, String emNO, String emName) {
		try {
			ppst = connection.prepareStatement(sql);
			
			ppst.setString(1, date);
			ppst.setString(2, people);
			ppst.setInt(3, cost);
			ppst.setString(4, emNO);
			ppst.setString(5, emName);
			
			ppst.execute();
			
			ppst.close();
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
