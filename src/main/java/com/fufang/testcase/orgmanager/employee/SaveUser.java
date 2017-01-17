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

public class SaveUser {

	Connection con;
	String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";
	
	private int id;
	
	@BeforeTest
	public void saveUser() throws IOException, URISyntaxException{

		String userName = "hyjtest_update";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.87.189:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		String testUrl = "http://172.16.87.189:9999/organization-manager/employee/saveUser";  
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("userStr", "{\"tuser\":{\"userCode\":\"save1\",\"fullName\":\"save1\",\"pinyin\":\"save1\",\"pharmacyId\":200378,\"departmentIds\":0,\"positionIds\":0,\"roleIds\":\"850F7C34-E9DC-4528-8547-0016098C8501\",\"isSys\":0,\"userName\":\"hyjtest_update1\",\"loginAccount\":\"hyjtest_update1\",\"password\":\"1qaz2wsx\",\"confirmPassword\":\"1qaz2wsx\",\"normalMode\":0,\"chineseMedicineMode\":11,\"supplementSales\":\"\",\"prescriptionRegister\":\"\",\"limitSaRegister\":\"\",\"changePrice\":\"\",\"isLineOff\":\"\",\"isOrderOff\":\"\",\"isRound\":\"\",\"maxLineOff\":\"\",\"maxOrderOff\":\"\",\"maxOff\":\"\",\"maxRound\":\"\",\"leaveStatus\":\"\",\"leaveDate\":\"\",\"leaveReason\":\"\",\"remark\":\"\",\"sex\":\"女\",\"certNum\":\"\",\"birthday\":\"\",\"isMarried\":\"\",\"ethnic\":\"\",\"political\":\"\",\"motherLand\":\"\",\"address\":\"\",\"school\":\"\",\"graduationDate\":\"\",\"xueli\":\"\",\"profession\":\"\",\"workDate\":\"\",\"tel\":\"\",\"fax\":\"\",\"mobile\":\"\",\"email\":\"\",\"qqNum\":\"\",\"wxNum\":\"\",\"introduce\":\"\",\"employType\":\"\",\"assumption\":\"\",\"isTrained\":0,\"workerNum\":\"\"},\"userQualification\":[{\"gspType\":0,\"licenseId\":123,\"licenseType\":\"\",\"description\":\"save2\",\"titlesGrade\":\"\",\"titlesNum\":\"\",\"regCertNum\":\"\",\"area\":\"\",\"titlesType\":\"\",\"scope\":\"\",\"fileName\":\"\",\"fileId\":\"\"}]}")); 

		String result = HttpPostLogin.httpPost(testUrl, nvps, loginUrl, userName, pwd);
		System.out.println("response content - "+ result);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String msg = jsonObject.getString("msg");	
		Assert.assertEquals("success", msg);
		
		id = jsonObject.getInt("id");
	}
	
	@Test
	public void checkUser()throws SQLException{
		System.out.println("------beginRun------");

		SqlUtils c = new SqlUtils();   
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);  

		try{
			Statement statementU = con.createStatement();
			Statement statementQ = con.createStatement();
			
			String querySqlU = "SELECT * FROM [dbo].[t_user] WHERE id ="+id;
			String querySqlQ = "SELECT * FROM [dbo].[t_user_qualified] WHERE userId ="+id;
			ResultSet resultSetU = statementU.executeQuery(querySqlU);
		    ResultSet resultSetQ = statementQ.executeQuery(querySqlQ);
			

			while(resultSetU.next()){
				String userName = resultSetU.getString("userName");
				String fullName = resultSetU.getString("fullName");
				System.out.println(userName + " " + fullName);

				Assert.assertEquals("hyjtest_update1", userName);
				Assert.assertEquals("save1", fullName);
			}
			
			while(resultSetQ.next()){
				String description = resultSetQ.getString("description");
				
				Assert.assertEquals(description, "save2");
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
			String deleteSqlU = "DELETE FROM [dbo].[t_user] WHERE id ="+id;
			String deleteSqlQ = "DELETE FROM [dbo].[t_user_qualified] WHERE userId="+id;
			PreparedStatement pStatementU = con.prepareStatement(deleteSqlU);
			PreparedStatement pStatementQ = con.prepareStatement(deleteSqlQ);
		
			pStatementU.executeUpdate();
			pStatementQ.executeUpdate();
		
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}


