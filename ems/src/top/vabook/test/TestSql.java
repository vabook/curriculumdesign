package top.vabook.test;

import java.util.jar.Attributes.Name;

public class TestSql {

	public static String name = "admin";
	public static String password = "root";
	public static String sql1 ;
	public static String sql2 ;
	public static void main(String[] args) {
		//必须有''号,且后面的空格不能忘
		sql1 = "update table_user set password = '" + password + "' where name = '" + name + "'";
		System.out.println(sql1);
		
		sql2 = "update table_user set password = " + password + "where name = " + name;
		System.out.println(sql2);
		
	}
}
