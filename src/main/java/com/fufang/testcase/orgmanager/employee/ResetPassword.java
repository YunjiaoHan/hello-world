package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;

public class ResetPassword{
	Connection con;
	String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";
	
	@BeforeTest
	public void resetPassword() throws ClientProtocolException, URISyntaxException, IOException {  
		System.out.println("------beforeTest------");

		String TestUrl = "http://172.16.88.183:9999/organization-manager/employee/resetPassword?uId=32814773";
		String userName= "abc";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);
	}

	@Test
	public void checkPassword()throws SQLException{
		System.out.println("------beginRun------");

		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);  

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [dbo].[t_user] WHERE id = 32814773;";
			ResultSet resultSet = statement.executeQuery(querySql);

			while(resultSet.next()){
				String password = resultSet.getString("password");
				System.out.println("reseted password - " + password);

				Assert.assertEquals(password, "3Ush6e9x4SkRg6RrkTrm8g==");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterTest
	public void changePassword()throws SQLException{
		System.out.println("------aftertest------");
		
		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);   

		try{
			String updateSql = "UPDATE [dbo].[t_user] SET password = 'HGMSmunbnGDD6KqU0+AElQ==' WHERE id = 32814773;";
			PreparedStatement pStatement = con.prepareStatement(updateSql);
			pStatement.executeUpdate();

			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [dbo].[t_user] WHERE id = 32814773;";
			ResultSet resultSet = statement.executeQuery(querySql);

			while(resultSet.next()){
				String password = resultSet.getString("password");
				System.out.println("orginal password - " + password);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
