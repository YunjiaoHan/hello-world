package com.fufang.testcase.ep.supplierapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.RedisUtils;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class PullPharmacy {

	/*String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";*/
	
	String dbUrl = "123.56.40.199";
	String dbName = "gsp";
	String dbUserName = "sa";
	String dbPassword = "1qaz@WSX";
	
	Connection con;
	String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/pullPharmacy";
	
	//String dwbh = "dwbh001";
	//String key = "875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5";

	@Test
	public void savePharmacy() throws ClientProtocolException, IOException, SQLException{

	/*	//构造参数请求失败，原因待检查
		Map<String, String> data = new HashMap<String, String>();
		data.put("dwbh", dwbh);
		data.put("dwmch", "药店名称001");
		data.put("isKg","是");//是否管控
		data.put("adress","知春路1号");
		data.put("phContact","联系人001");
		data.put("mobilePhone","联系电话001");

		JSONArray dataArray = JSONArray.fromObject(data);
		JSONObject params = new JSONObject();
		params.put("key",key);
		params.put("data",dataArray);
		String sParams = params.toString();	
		FormatJson.printJson(sParams);*/
		
		String sParams = "{\"key\":\"875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5\",\"data\":[{\"dwbh\":\"dwbh009\",\"dwmch\":\"药店名称001\",\"iskg\":\"是\",\"address\":\"知春路1号\",\"phContact\":\"联系人001\",\"mobilePhone\":\"13000000001\"}]}";
		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);
		
		JSONObject jsonObject = JSONObject.fromObject(resultContent);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String result = responseData.getString("result");
		String dataCode = responseData.getString("code");

		Assert.assertEquals("000000", code);
		Assert.assertEquals("添加或修改药店成功。添加[1]修改[0]", result);
		Assert.assertEquals("SUCCESS", dataCode);

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [wit_selection].[middle_temp_pharmacy] where dwbh = 'dwbh009'";
			ResultSet resultSet = statement.executeQuery(querySql);
			while(resultSet.next()){
				String address = resultSet.getString("address");
				System.out.println(address);
				Assert.assertEquals("知春路1号", address);
				//需要更多验证
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void updatePharmacy() throws SQLException, UnsupportedEncodingException, ClientProtocolException, IOException{
		String sParams = "{\"key\":\"875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5\",\"data\":[{\"dwbh\":\"dwbh009\",\"dwmch\":\"药店名称002\",\"iskg\":\"否\",\"address\":\"知春路2号\",\"phContact\":\"联系人002\",\"mobilePhone\":\"13000000002\"}]}";
		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);
		
		JSONObject jsonObject = JSONObject.fromObject(resultContent);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String result = responseData.getString("result");
		String dataCode = responseData.getString("code");

		Assert.assertEquals("000000", code);
		Assert.assertEquals("添加或修改药店成功。添加[0]修改[1]", result);
		Assert.assertEquals("SUCCESS", dataCode);

		try{
			Statement statement = con.createStatement();
			String querySqlM = "SELECT * FROM [wit_selection].[middle_temp_pharmacy] where dwbh = 'dwbh009'";
			ResultSet resultSetM = statement.executeQuery(querySqlM);
			while(resultSetM.next()){
				String adress = resultSetM.getString("address");
				System.out.println(adress);
				Assert.assertEquals("知春路2号", adress);
				//需要更多验证
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void deletePharmacy() throws SQLException{
		try{
			String deleteSql = "DELETE FROM [wit_selection].[middle_temp_pharmacy] where dwbh = 'dwbh009'";
			PreparedStatement pStatement = con.prepareStatement(deleteSql);
			pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}

		RedisUtils rc = new RedisUtils();
		Jedis jedis = rc.redisConn();

		try{
			jedis.srem("PHARMACY_DWBH_SUPPLIERID","dwbh009|66");
						
			if(jedis.sismember("PHARMACY_DWBH_SUPPLIERID", "dwbh009|66") == false){
				System.out.println("delete redis success");
			}else{
				System.out.println("delete redis fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
