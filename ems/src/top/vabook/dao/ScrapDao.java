package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import top.vabook.util.DBConnection;

public class ScrapDao {

	private static Connection connection;

	private static DBConnection dbConnection;

	private static String sql = "insert into table_scrap(date,emno,emname,people) values(?,?,?,?)";

	private static PreparedStatement ppst;
	
	private static int flag ;

	public ScrapDao() {
	}

	@SuppressWarnings("finally")
	public static Boolean insert(String date, String emNo, String emName, String people) {
		try {
			dbConnection = new DBConnection();
			connection = dbConnection.connection;
			ppst = connection.prepareStatement(sql);
			ppst.setString(1, date);
			ppst.setString(2, emNo);
			ppst.setString(3, emName);
			ppst.setString(4, people);

			flag = ppst.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ppst != null && connection != null) {
					ppst.close();
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return flag == 1?true:false;
		}
	}
}
