package com.fufang.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlUtils {

	public Connection sqlSConnection(String dbUrl, String dbName, String dbUserName, String dbPassword) throws SQLException{ 
		Connection con = null;

		try {              
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{                
			con = DriverManager.getConnection("jdbc:sqlserver://" +dbUrl+":1433;DatabaseName="+dbName,dbUserName,dbPassword);
			System.out.println("数据库连接成功");

		} catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}

	public Connection mySqlConnection(String dbUrl, String dbUserName, String dbPassword) throws SQLException{ 
		Connection con = null;

		try {              
			Class.forName("com.mysql.jdbc.Driver");
			//System.out.println("数据库驱动加载成功");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{                
			con = DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
			System.out.println("数据库连接成功");

		} catch(SQLException e){
			e.printStackTrace();
		}
		return con;
	}

	public static void main(String[] args) throws SQLException{

	/*	{
			String dbUrl = "172.16.86.44";
			String dbName = "gspdev";
			String dbUserName = "gspadmin";
			String dbPassword = "gspadmin01";
			SqlUtils c = new SqlUtils();
			c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);
		}*/
	/*	{
			String dbUrl = "jdbc:mysql://172.16.88.112:8066/HISFC?userUnicode=true&amp;characterEncoding=UTF-8";
			String dbUserName = "root";
			String dbPassword = "root";
			SqlUtils c = new SqlUtils();
			c.mySqlConnection(dbUrl, dbUserName, dbPassword);
		}*/
	}
}