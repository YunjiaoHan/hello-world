package com.fufang.testcase.ep.supplierapi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class UpdatePriceAndStoreNum {

	/*String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";*/
	
	String dbUrl = "123.56.40.199";
	String dbName = "gsp";
	String dbUserName = "sa";
	String dbPassword = "1qaz@WSX";
	
	Connection con;
	String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/updatePriceAndStoreNum";

	@Test
	public void updatePriceAndStoreNum() throws ClientProtocolException, IOException, SQLException{

	    String sParams = "{\"key\":\"875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5\",\"data\":[{\"matcode\":\"ZP0001\",\"price\":11.1,\"storeNum\":111},{\"matcode\":\"ZP0002\",\"price\":22.2,\"storeNum\":222}]}";
		JsonUtils.printJson(sParams);

		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);

		JSONObject jsonObject = JSONObject.fromObject(resultContent);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String result = responseData.getString("result");
		String dataCode = responseData.getString("code");
		String item = responseData.getString("item");

		Assert.assertEquals("000000", code);
		Assert.assertEquals("更新单价、库存数目成功", result);
		Assert.assertEquals("2", item);
		Assert.assertEquals("SUCCESS", dataCode);

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement1 = con.createStatement();
			Statement statement2 = con.createStatement();
			String querySql1 = "SELECT * FROM [wit_selection].[zc_sumPriceAndNum] where matcode = 'ZP0001'";
			String querySql2 = "SELECT * FROM [wit_selection].[zc_sumPriceAndNum] where matcode = 'ZP0002'";
			ResultSet resultSet1 = statement1.executeQuery(querySql1);
			ResultSet resultSet2 = statement2.executeQuery(querySql2);
			
			while(resultSet1.next()){
				double price = resultSet1.getDouble("price");
				int storeNum = resultSet1.getInt("storeNum");
				System.out.println(price + " " + storeNum);		
				Assert.assertEquals(11.1, price);
				Assert.assertEquals(111, storeNum);
			}
			while(resultSet2.next()){
				double price = resultSet2.getDouble("price");
				int storeNum = resultSet2.getInt("storeNum");
				System.out.println(price + " " + storeNum);		
				Assert.assertEquals(22.2, price);
				Assert.assertEquals(222, storeNum);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void restorePriceAndStoreNum() throws ClientProtocolException, IOException, SQLException{

	    String sParams = "{\"key\":\"875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5\",\"data\":[{\"matcode\":\"ZP0001\",\"price\":10,\"storeNum\":100},{\"matcode\":\"ZP0002\",\"price\":10,\"storeNum\":100}]}";
		JsonUtils.printJson(sParams);

		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement1 = con.createStatement();
			Statement statement2 = con.createStatement();
			String querySql1 = "SELECT * FROM [wit_selection].[zc_sumPriceAndNum] where matcode = 'ZP0001'";
			String querySql2 = "SELECT * FROM [wit_selection].[zc_sumPriceAndNum] where matcode = 'ZP0002'";
			ResultSet resultSet1 = statement1.executeQuery(querySql1);
			ResultSet resultSet2 = statement2.executeQuery(querySql2);
			
			while(resultSet1.next()){
				double price = resultSet1.getDouble("price");
				int storeNum = resultSet1.getInt("storeNum");
				System.out.println(price + " " + storeNum);		
			}
			while(resultSet2.next()){
				double price = resultSet2.getDouble("price");
				int storeNum = resultSet2.getInt("storeNum");
				System.out.println(price + " " + storeNum);		
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
	
	
