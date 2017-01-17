package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;
import com.fufang.utils.SqlUtils;

public class UpdateUser{

	String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";
	String userName= "hyjtest_update2";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;	

	@Test
	public void updateUser() throws ClientProtocolException, URISyntaxException, IOException, SQLException {  

		String TestUrl = "http://172.16.88.183:9999/organization-manager/employee/updateUser?userStr=%7B%20%20%20%20%20%22tuser%22%3A%20%7B%20%20%20%20%20%20%20%20%20%22id%22%3A%20%2232815110%22%2C%20%20%20%20%20%20%20%20%20%22userCode%22%3A%20%2200002%22%2C%20%20%20%20%20%20%20%20%20%22fullName%22%3A%20%22%E5%B0%8FB%22%2C%20%20%20%20%20%20%20%20%20%22pinyin%22%3A%20%22XB%22%2C%20%20%20%20%20%20%20%20%20%22pharmacyId%22%3A%20%22200378%22%2C%20%20%20%20%20%20%20%20%20%22departmentId%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22positionId%22%3A%20%2214%22%2C%20%20%20%20%20%20%20%20%20%22roleIds%22%3A%20%2216156633-F936-46BF-8750-C905DDC8A4F8%22%2C%20%20%20%20%20%20%20%20%20%22isSys%22%3A%20%22on%22%2C%20%20%20%20%20%20%20%20%20%22userName%22%3A%20%22hyjtest_update2%22%2C%20%20%20%20%20%20%20%20%20%22password%22%3A%20%22HGMSmunbnGDD6KqU0%2BAElQ%3D%3D%22%2C%20%20%20%20%20%20%20%20%20%22confirmPassword%22%3A%20%22HGMSmunbnGDD6KqU0%2BAElQ%3D%3D%22%2C%20%20%20%20%20%20%20%20%20%22maxLineOff%22%3A%20%2212%22%2C%20%20%20%20%20%20%20%20%20%22maxOrderOff%22%3A%20%2212%22%2C%20%20%20%20%20%20%20%20%20%22maxOff%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22maxRound%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22leaveStatus%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22leaveReason%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22remark%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22certNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22ethnic%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22political%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22motherLand%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22address%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22school%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22xueli%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22profession%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22tel%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22fax%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22mobile%22%3A%20%2213269281059%22%2C%20%20%20%20%20%20%20%20%20%22email%22%3A%20%22asdfa%40163.com%22%2C%20%20%20%20%20%20%20%20%20%22qqNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22wxNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22introduce%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22employType%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22workerNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22birthday%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22graduationDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22workDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22assumption%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22dutyDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22normalMode%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22chineseMedicineMode%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22supplementSales%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22prescriptionRegister%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22limitSaRegister%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22changePrice%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isLineOff%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isOrderOff%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isRound%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22sex%22%3A%20%22%E5%A5%B3%22%2C%20%20%20%20%20%20%20%20%20%22isMarried%22%3A%20true%2C%20%20%20%20%20%20%20%20%20%22departmentNames%22%3A%20%22%E5%82%A8%E5%AD%98%E9%83%A8%E9%97%A8%2C%E9%87%87%E8%B4%AD%E9%83%A8%E9%97%A8%22%2C%20%20%20%20%20%20%20%20%20%22pharmacyName%22%3A%20%22%E8%BF%9E%E9%94%81%E6%B5%8B%E8%AF%95%E6%80%BB%E9%83%A8%22%2C%20%20%20%20%20%20%20%20%20%22positionNames%22%3A%20%22%E9%87%87%E8%B4%AD%E4%BA%BA%E5%91%98%2C%E9%87%87%E8%B4%AD%E9%83%A8%E9%97%A8%E8%B4%9F%E8%B4%A3%E4%BA%BA%22%2C%20%20%20%20%20%20%20%20%20%22roleNames%22%3A%20%22%E5%A4%84%E6%96%B9%E5%8F%91%E8%8D%AF%E4%BA%BA%E5%91%98-%E4%B8%AD%E8%8D%AF%E9%A5%AE%E7%89%87%2C%E5%A4%84%E6%96%B9%E6%A0%B8%E5%AF%B9%E4%BA%BA%E5%91%98-%E4%B8%AD%E8%8D%AF%E9%A5%AE%E7%89%87%22%2C%20%20%20%20%20%20%20%20%20%22state%22%3A%20%221%22%20%20%20%20%20%7D%2C%20%20%20%20%20%22userQualification%22%3A%20%5B%5D%2C%20%20%20%20%20%22userNote%22%3A%20%7B%7D%20%7D";		
		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println(result);

		SqlUtils c = new SqlUtils();   
		Connection con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);  
		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [dbo].[t_user] where id = 32815110;";

			ResultSet resultSet = statement.executeQuery(querySql);	
			while(resultSet.next()){
				String fullName = resultSet.getString(5);
				int departmentId = resultSet.getInt(6);
				int positionId = resultSet.getInt(7);
				System.out.println(fullName + departmentId +positionId);

				Assert.assertEquals(fullName, "小B");
				Assert.assertEquals(departmentId, 1);
				Assert.assertEquals(positionId, 14);			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@Test
	public void restoreUser() throws ClientProtocolException, URISyntaxException, IOException, SQLException {  

		String TestUrl = "http://172.16.88.183:9999/organization-manager/employee/updateUser?userStr=%7B%20%20%20%20%20%22tuser%22%3A%20%7B%20%20%20%20%20%20%20%20%20%22id%22%3A%20%2232815110%22%2C%20%20%20%20%20%20%20%20%20%22userCode%22%3A%20%2200002%22%2C%20%20%20%20%20%20%20%20%20%22fullName%22%3A%20%22%E5%B0%8FC%22%2C%20%20%20%20%20%20%20%20%20%22pinyin%22%3A%20%22XB%22%2C%20%20%20%20%20%20%20%20%20%22pharmacyId%22%3A%20%22200378%22%2C%20%20%20%20%20%20%20%20%20%22departmentId%22%3A%2015%2C%20%20%20%20%20%20%20%20%20%22positionId%22%3A%20134%2C%20%20%20%20%20%20%20%20%20%22roleIds%22%3A%20%2216156633-F936-46BF-8750-C905DDC8A4F8%22%2C%20%20%20%20%20%20%20%20%20%22isSys%22%3A%20%22on%22%2C%20%20%20%20%20%20%20%20%20%22userName%22%3A%20%22hyjtest_update2%22%2C%20%20%20%20%20%20%20%20%20%22password%22%3A%20%22HGMSmunbnGDD6KqU0%2BAElQ%3D%3D%22%2C%20%20%20%20%20%20%20%20%20%22confirmPassword%22%3A%20%22HGMSmunbnGDD6KqU0%2BAElQ%3D%3D%22%2C%20%20%20%20%20%20%20%20%20%22maxLineOff%22%3A%20%2212%22%2C%20%20%20%20%20%20%20%20%20%22maxOrderOff%22%3A%20%2212%22%2C%20%20%20%20%20%20%20%20%20%22maxOff%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22maxRound%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22leaveStatus%22%3A%20%221%22%2C%20%20%20%20%20%20%20%20%20%22leaveReason%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22remark%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22certNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22ethnic%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22political%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22motherLand%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22address%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22school%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22xueli%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22profession%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22tel%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22fax%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22mobile%22%3A%20%2213269281059%22%2C%20%20%20%20%20%20%20%20%20%22email%22%3A%20%22asdfa%40163.com%22%2C%20%20%20%20%20%20%20%20%20%22qqNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22wxNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22introduce%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22employType%22%3A%20%220%22%2C%20%20%20%20%20%20%20%20%20%22workerNum%22%3A%20%22%22%2C%20%20%20%20%20%20%20%20%20%22birthday%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22graduationDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22workDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22assumption%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22dutyDate%22%3A%20%222016-12-05%22%2C%20%20%20%20%20%20%20%20%20%22normalMode%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22chineseMedicineMode%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22supplementSales%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22prescriptionRegister%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22limitSaRegister%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22changePrice%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isLineOff%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isOrderOff%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22isRound%22%3A%200%2C%20%20%20%20%20%20%20%20%20%22sex%22%3A%20%22%E5%A5%B3%22%2C%20%20%20%20%20%20%20%20%20%22isMarried%22%3A%20true%2C%20%20%20%20%20%20%20%20%20%22departmentNames%22%3A%20%22%E9%87%87%E8%B4%AD%E9%83%A8%E9%97%A8%22%2C%20%20%20%20%20%20%20%20%20%22pharmacyName%22%3A%20%22%E8%BF%9E%E9%94%81%E6%B5%8B%E8%AF%95%E5%88%86%E9%83%A8%22%2C%20%20%20%20%20%20%20%20%20%22positionNames%22%3A%20%22%E9%87%87%E8%B4%AD%E9%83%A8%E9%97%A8%E8%B4%9F%E8%B4%A3%E4%BA%BA%22%2C%20%20%20%20%20%20%20%20%20%22roleNames%22%3A%20%22%E5%A4%84%E6%96%B9%E6%A0%B8%E5%AF%B9%E4%BA%BA%E5%91%98-%E4%B8%AD%E8%8D%AF%E9%A5%AE%E7%89%87%22%2C%20%20%20%20%20%20%20%20%20%22state%22%3A%20%221%22%20%20%20%20%20%7D%2C%20%20%20%20%20%22userQualification%22%3A%20%5B%5D%2C%20%20%20%20%20%22userNote%22%3A%20%7B%7D%20%7D";
        String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println(result);

		SqlUtils c = new SqlUtils();   
		Connection con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword); 
		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [dbo].[t_user] where id = 32815110;";

			ResultSet resultSet = statement.executeQuery(querySql);	
			while(resultSet.next()){
				String fullName = resultSet.getString(5);
				int departmentId = resultSet.getInt(6);
				int positionId = resultSet.getInt(7);
				System.out.println(fullName + departmentId +positionId);

				Assert.assertEquals(fullName, "小C");
				Assert.assertEquals(departmentId, 15);
				Assert.assertEquals(positionId, 134);			
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}






