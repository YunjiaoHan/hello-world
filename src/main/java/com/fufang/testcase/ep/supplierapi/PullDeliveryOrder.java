package com.fufang.testcase.ep.supplierapi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.RedisUtils;
import com.fufang.utils.SqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class PullDeliveryOrder {

	/*String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";*/
	
	String dbUrl = "123.56.40.199";
	String dbName = "gsp";
	String dbUserName = "sa";
	String dbPassword = "1qaz@WSX";
	
	Connection con;
	String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/pullDeliveryorder";

	@Test
	public void pullDeliveryOrder() throws UnsupportedEncodingException, ClientProtocolException, IOException, SQLException{

		String detailsA = "{\"matcode\":\"M001\",\"ddbh\":\"F20161216000771\",\"num\":4,\"price\":16.81,\"batchNum\":\"Batch01\",\"productDate\":\"2016-01-01 01:02:03\",\"validDate\":\"2020-01-02 01:02:03\"}";
		String detailsB = "{\"matcode\":\"M002\",\"ddbh\":\"F20161216000772\",\"num\":10,\"price\":13.16,\"batchNum\":\"Batch02\",\"productDate\":\"2016-01-01 01:02:03\",\"validDate\":\"2020-01-02 01:02:03\"}";      
		JSONObject AObject = JSONObject.fromObject(detailsA);
		JSONObject BObject = JSONObject.fromObject(detailsB);	
		JSONArray detail = new JSONArray();
		detail.add(0, AObject);
		detail.add(1, BObject);

		JSONObject head = new JSONObject();
		head.put("deliveryOrder", "出库单号H074");
		head.put("ddbh", "F20161216000771,F20161216000772");
		head.put("totalPrice",198.84);
		head.put("deliveryTime", "2016-12-15 01:02:03");

		
		JSONObject dataObject = new JSONObject();
		dataObject.put("head", head);
		dataObject.put("detail", detail);	
		JSONArray data = new JSONArray();
		data.add(dataObject);

		JSONObject paramsObject = new JSONObject();
		paramsObject.put("key", "875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5");
		paramsObject.put("data", data);

		String sparams = paramsObject.toString();     
		JsonUtils.printJson(sparams);
		//String result = HttpPostBuild.postBuildJson(url, sparams);
		String result = HttpUtils.httpPostJson(url, sparams);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String dataResult = responseData.getString("result");
		String dataCode = responseData.getString("code");

		Assert.assertEquals(code, "000000");
		Assert.assertEquals(dataCode, "SUCCESS");
		Assert.assertEquals(dataResult, "保存出库单成功。出库单抬头表[1]明细表[2]映射表[2]");

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();

			String querySql1 = "SELECT * FROM wit_selection.zc_delivery WHERE deliveryOrder = '出库单号H074'";
			ResultSet resultSet1 = statement.executeQuery(querySql1);
			while(resultSet1.next()){
				String totalPrice = resultSet1.getString("totalPrice");
				Assert.assertEquals(totalPrice, "198.840");
			}

			String querySql2 = "SELECT * FROM wit_selection.zc_delivery_item WHERE deliveryOrder = '出库单号H074'";
			ResultSet resultSet2 = statement.executeQuery(querySql2);

			List<String> expectDdbhList = new ArrayList<String>();
			expectDdbhList.add("F20161216000771");
			expectDdbhList.add("F20161216000772");

			while(resultSet2.next()){
				String ddbh = resultSet2.getString("ddbh");	
				System.out.println(ddbh);
			}

			String querySql3 = "SELECT * FROM wit_selection.zc_deliveryDdbhMapping WHERE deliveryOrder = '出库单号H074'";
			ResultSet resultSet3 = statement.executeQuery(querySql3);
			while(resultSet3.next()){
				String ddbh = resultSet3.getString("ddbh");	
				System.out.println(ddbh);;
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	@AfterTest
	public void deleteDeliveryOrder(){
		try{
			String deleteSql = "DELETE FROM wit_selection.zc_delivery WHERE deliveryOrder = '出库单号H074';"
					+ "DELETE FROM wit_selection.zc_delivery_item WHERE deliveryOrder = '出库单号H074';"
					+ "DELETE FROM wit_selection.zc_deliveryDdbhMapping WHERE deliveryOrder = '出库单号H074';";
			PreparedStatement pStatement = con.prepareStatement(deleteSql);
			pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}

		RedisUtils rc = new RedisUtils();
		Jedis jedis = rc.redisConn();
		try{
			jedis.srem("DELIVERY_CKDH_SUPPLIERID","出库单号H074|66");

			if(jedis.sismember("DELIVERY_CKDH_SUPPLIERID","出库单号H074|66") == false){
				System.out.println("delete redis success");
			}else{
				System.out.println("delete redis fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}




