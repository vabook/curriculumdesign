package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.MediaName;

import top.vabook.domain.User;
import top.vabook.util.DBConnection;

public class UserDao {
	private static DBConnection dbConnection;
	
	private static Connection connection;
	
	private static List<User> users;
	
	private static String sql = "select * from table_user";
	
	
	private static PreparedStatement ppst;
	
	public UserDao() {
	}
	
	public static List<User> query(){
		dbConnection = new DBConnection();
		connection = dbConnection.connection;

		users = new ArrayList<User>();
		
		try {
			ppst = connection.prepareStatement(sql);
			ResultSet rs = ppst.executeQuery();
			while(rs.next()) {
				users.add(new User(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if (ppst != null) {
					
					ppst.close();
				}
				if (connection != null) {
					
					connection.close();
				}
				return users;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static Boolean update(String name,String password) {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
		
		String updateSql = "update table_user set password = '" + password + "' where name = '" + name + "'";
		
		try {
			ppst = connection.prepareStatement(updateSql);
			int i = ppst.executeUpdate();
			ppst.close();
			connection.close();
			return i==1 ? true:false;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
	}
	
}
