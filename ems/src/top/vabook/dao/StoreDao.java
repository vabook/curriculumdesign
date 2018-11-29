package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import top.vabook.domain.StoreEm;
import top.vabook.util.DBConnection;

//从数据库中查找
public class StoreDao {

	private static DBConnection dbConnection;
	
	private Connection connection;
	
	private String sql = "select * from table_store";
	
	private PreparedStatement ppst;
	
	private StoreEm storeEm;
	
	private List<StoreEm> storeEms;
	
	public StoreDao() {
		
		dbConnection = new DBConnection();
		
		connection = dbConnection.connection;
	}
	/**
	 * 
	 * @return 返回设备集合
	 */
	public List<StoreEm> query() {
				
		storeEms = new ArrayList<StoreEm>();
		try {
			ppst = connection.prepareStatement(sql);
			ResultSet rs= ppst.executeQuery();
			
			while(rs.next()) {
				storeEm = new StoreEm(rs.getInt("emcount"),rs.getString("emno"),rs.getString("emname"),rs.getString("emstatus"));
				
				storeEms.add(new StoreEm(rs.getInt("emcount"),rs.getString("emno"),rs.getString("emname"),rs.getString("emstatus")));
			}
			return storeEms;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return null;
		
	}
}
