package com.fufang.testcase.orgmanager.warnset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpPostLogin;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class SaveOrUpdate {

	Connection con;
	String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";

	String userName = "hyjtest01";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
	String testUrl = "http://172.16.87.194:9999/organization-manager/warnSet/saveOrUpdate";

	private int id;

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	@Test
	public void saveWarn() throws IOException, URISyntaxException, SQLException{

		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("jsonStr","{\"warnBeanList\": [{\"warnType\": \"32\",\"warnGspType\": 3,\"licenseId\":\"77\",\"isOn\":1,\"dayNum\":30,\"maintainDay\": 90,\"roles\":\"B6E58291-0A5B-49A3-B379-19DAC845789F\"}]}"));

		String result = HttpPostLogin.httpPost(testUrl, nvps, loginUrl, userName, pwd);

		JSONObject jsonObject = JSONObject.fromObject(result);
		int state = jsonObject.getInt("state");
		String msg = jsonObject.getString("msg");
		System.out.println(state + " - " + msg);

		Assert.assertEquals(200, state);
		Assert.assertEquals("success",msg);

		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);


		try{
			Statement statement = con.createStatement();

			String querySqlU = "SELECT * FROM [dbo].[t_warn] WHERE roles ='B6E58291-0A5B-49A3-B379-19DAC845789F'";
			ResultSet resultSetU = statement.executeQuery(querySqlU);

			while(resultSetU.next()){
				setId(resultSetU.getInt("id"));
				String warnType = resultSetU.getString("warnType");
				Assert.assertEquals("32", warnType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}


	@Test
	public void updateWarn() throws IOException, URISyntaxException, SQLException{

		int id = getId();
		System.out.println("id = " + id);

		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("jsonStr","{\"warnBeanList\":[{\"id\":"+id+",\"warnType\":\"36\",\"warnGspType\":3,\"licenseId\":\"77\",\"isOn\":1,\"dayNum\":30,\"maintainDay\":90,\"roles\":\"ABCD1234-067E-4CAA-ACF0-12DFCDF7B8D3\"}]}"));

		String result = HttpPostLogin.httpPost(testUrl, nvps, loginUrl, userName, pwd);

		JSONObject jsonObject = JSONObject.fromObject(result);
		int state = jsonObject.getInt("state");
		String msg = jsonObject.getString("msg");
		System.out.println(state + " - " + msg);

		Assert.assertEquals(200, state);
		Assert.assertEquals("success",msg);

		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySqlU = "SELECT * FROM [dbo].[t_warn] WHERE id ="+id;
			ResultSet resultSetU = statement.executeQuery(querySqlU);

			while(resultSetU.next()){
				String warnType = resultSetU.getString("warnType");
				System.out.println("updated warnType - " + warnType);

				Assert.assertEquals("36", warnType);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterTest
	public void deleteUser()throws SQLException{
		System.out.println("------aftertest------");
		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);  

		try{
			String deleteSql = "DELETE FROM [dbo].[t_warn] WHERE id ="+id;
			PreparedStatement pStatement = con.prepareStatement(deleteSql);
			pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			Statement statement = con.createStatement();
			String querySqlU = "SELECT * FROM [dbo].[t_warn] WHERE id ="+id;
			ResultSet resultSetU = statement.executeQuery(querySqlU);
			while(resultSetU.next()){
				int deleteId = resultSetU.getInt("id");
				System.out.println(deleteId);

				Assert.assertEquals("123"
						+ "", deleteId);
			}

			/*if(resultSetU == null){
				System.out.println("delete success");
			}else{
				System.out.println("delete fail");
			}*/
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


