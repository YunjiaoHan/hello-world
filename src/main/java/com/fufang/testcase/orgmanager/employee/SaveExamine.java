package com.fufang.testcase.orgmanager.employee;

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
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpPostLogin;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class SaveExamine {

	Connection con;
	String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";

	private int id;

	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	@BeforeTest
	public void saveExam() throws IOException, URISyntaxException{

		String userName = "hyjtest_update";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.87.189:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		String testUrl = "http://172.16.87.189:9999/organization-manager/employee/saveExamine";  
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("userStr", "{\"tuser\":{\"state\":3,\"id\":32815111,\"examinePersonnel\":\"examPerson1\",\"examineDate\":\"2016-12-0814:16:11\",\"examineOpinion\":\"同意\",\"examineResult\":1,\"reviewPersonnel\":\"复核员1\",\"reviewDate\":\"2016-12-0814:17:11\",\"reviewOpinion\":\"同意\",\"reviewResult\":0}}"));
		String result = HttpPostLogin.httpPost(testUrl, nvps, loginUrl, userName, pwd);
		System.out.println("response content - "+ result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String msg = jsonObject.getString("msg");
		String stateExamine = jsonObject.getString("stateExamine");

		Assert.assertEquals("success", msg);
		Assert.assertEquals("3", stateExamine);

		setId(jsonObject.getInt("id"));
	}

	@Test
	public void checkExam()throws SQLException{
		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);  

		int id =getId();

		try{
			Statement statement = con.createStatement();

			String querySqlU = "SELECT * FROM [dbo].[t_user] WHERE id ="+id;
			ResultSet resultSetU = statement.executeQuery(querySqlU);

			while(resultSetU.next()){
				String state = resultSetU.getString("state");
				System.out.println("state - " + state);

				Assert.assertEquals("3", state);
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
			String deleteSqlU = "UPDATE [dbo].[t_user] SET state = 1 WHERE id ="+id;
			System.out.println(deleteSqlU);
			PreparedStatement pStatementU = con.prepareStatement(deleteSqlU);
			pStatementU.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


