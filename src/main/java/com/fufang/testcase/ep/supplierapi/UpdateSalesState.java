package com.fufang.testcase.ep.supplierapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class UpdateSalesState {

	/*String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";
	Connection con;
	String url = "http://172.16.87.183:7777/ffcloud-epscm/pullMaterial/updateSalesState";*/
	
	String dbUrl = "123.56.40.199";
	String dbName = "gsp";
	String dbUserName = "sa";
	String dbPassword = "1qaz@WSX";
	Connection con;
	String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/updateSalesState";
	
	@Test
	public void updateSalesState() throws UnsupportedEncodingException, ClientProtocolException, IOException, SQLException{
		
		int state = 2;
		List<String> data = new ArrayList<String>();
		data.add("F2016120900072");
		data.add("F2016120900071"); 
		JSONArray dataArray = JSONArray.fromObject(data);
		
		JSONObject params = new JSONObject();
		params.put("key","875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5");
		params.put("state",state);
		params.put("ddbh",dataArray);
		String sParams = params.toString();	
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
		Assert.assertEquals("更新订单状态成功", result);
		Assert.assertEquals("SUCCESS", dataCode);
		Assert.assertEquals("2", item);
		
		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT zcState FROM wit_selection.zc_sales WHERE (otherddbh = 'F2016120900071' OR otherddbh = 'F2016120900072')";
			ResultSet resultSet = statement.executeQuery(querySql);
			while(resultSet.next()){
				int zcState = resultSet.getInt("zcState");
				Assert.assertEquals(state, zcState);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	@AfterTest
	public void restoreSalesState() throws UnsupportedEncodingException, ClientProtocolException, IOException, SQLException{
		
		int state = 3;
		List<String> data = new ArrayList<String>();
		data.add("F2016120900072");
		data.add("F2016120900071"); 
		JSONArray dataArray = JSONArray.fromObject(data);
		
		JSONObject params = new JSONObject();
		params.put("key","875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5");
		params.put("state",state);
		params.put("ddbh",dataArray);
		String sParams = params.toString();	
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
		Assert.assertEquals("更新订单状态成功", result);
		Assert.assertEquals("SUCCESS", dataCode);
		Assert.assertEquals("2", item);
		
		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT zcState FROM wit_selection.zc_sales WHERE (otherddbh = 'F2016120900071' OR otherddbh = 'F2016120900072')";
			ResultSet resultSet = statement.executeQuery(querySql);
			while(resultSet.next()){
				int zcState = resultSet.getInt("zcState");
				Assert.assertEquals(state, zcState);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
