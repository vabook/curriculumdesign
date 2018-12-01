package top.vabook.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.print.attribute.standard.Media;

import top.vabook.domain.StoreEm;
import top.vabook.util.DBConnection;

//从数据库中查找
public class StoreDao {

	private static DBConnection dbConnection;
	
	private Connection connection;
	
	private String sql = "select * from table_store";
	
	private String querySql = "select * from table_store where emname = ?";
	
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
	@SuppressWarnings("finally")
	public List<StoreEm> query() {
				
		storeEms = new ArrayList<StoreEm>();
		try {
			ppst = connection.prepareStatement(sql);
			ResultSet rs= ppst.executeQuery();
			
			while(rs.next()) {
				storeEm = new StoreEm(rs.getInt("emcount"),rs.getString("emno"),rs.getString("emname"),rs.getString("emstatus"));
				
				storeEms.add(new StoreEm(rs.getInt("emcount"),rs.getString("emno"),rs.getString("emname"),rs.getString("emstatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ppst != null) {
				try {
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return storeEms;
		}
		
	}
	//指定条件查询
	
	/*@SuppressWarnings("finally")
	public List<StoreEm> searchInName(String name){
		try {
			ppst = connection.prepareStatement(querySql);
			ppst.setString(3, name);

			ResultSet rs = ppst.executeQuery();
			
			while(rs.next()) {
				storeEms.add(new StoreEm(rs.getInt("emcount"),rs.getString("emno"),rs.getString("emname"),rs.getString("emstatus")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (ppst != null) {
				try {
					ppst.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return storeEms;
		}
		
		
		
	}*/
}
