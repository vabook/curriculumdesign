package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.text.StyledEditorKit.BoldAction;

import top.vabook.domain.BuyEm;
import top.vabook.util.DBConnection;

public class BuyDao {

	private static BuyEm buyEm;

	private static Connection connection;
	
	private DBConnection dbConnection;

	static String sql = "insert into table_buy(count,date,cost,peoplename,emno,emname) values(?,?,?,?,?,?)";
//	先获取到数据库的连接
	public BuyDao() {
		dbConnection = new DBConnection();
		connection = dbConnection.connection;
	}
	
	public  void insert(int count, String date, int cost, String peopleName, String emNo, String emName) {

		// 暂时写在这里
		buyEm = new BuyEm(count, date, cost, peopleName, emNo, emName);
		System.out.println(count + "  " + cost + peopleName + emName + emNo + date);
		System.out.println("成功........................................");
		
		try {
			PreparedStatement ppst = connection.prepareStatement(sql);
			ppst.setInt(1, buyEm.count);
			ppst.setString(2, buyEm.date);
			ppst.setInt(3, buyEm.cost);
			ppst.setString(4, buyEm.peopleName);
			ppst.setString(5,buyEm.emNo);
			ppst.setString(6, buyEm.emName);

			ppst.execute();
			System.out.println("插入成功...");
			ppst.close();
			connection.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
