package com.fufang.testcase.ep.supplierapi;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.RedisUtils;
import com.fufang.utils.SqlUtils;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class UpdateMaterial {

	/*String dbUrl = "172.16.86.44";
	String dbName = "gspdev";
	String dbUserName = "gspadmin";
	String dbPassword = "gspadmin01";*/
	
	String dbUrl = "123.56.40.199";
	String dbName = "gsp";
	String dbUserName = "sa";
	String dbPassword = "1qaz@WSX";
	
	Connection con;
	String matCode = "YP8888";

	//首先调保存药品接口，保存药品信息
	@BeforeTest
	public void saveMed() throws ClientProtocolException, IOException, SQLException{
		
		//String url = "http://172.16.87.183:7777/ffcloud-epscm/pullMaterial/saveOrUpdateAll.do";
		String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/saveOrUpdateAll.do";

		Map<String, String> data = new HashMap<String, String>();
		data.put("barcode","药品条码004");
		data.put("matcode", matCode);
		data.put("batchNum","批号004");
		data.put("name","名称004");
		data.put("commonName","通用名004");
		data.put( "spec","规格004");
		data.put("unit","单位004");
		data.put("dosage","剂型004");
		data.put("licenseNum","国药准字004");
		data.put("manufName","厂家004");
		data.put("prodPlace","厂址004");
		data.put("productDate","2012-12-12");
		data.put("validDate","2013-12-13");
		data.put("retailPrice","14.4");
		data.put("zhongbao","4");
		data.put("createPerson","操作员004");
		data.put( "sellState","y");
		data.put("manufNameAbbr","北京");
		data.put("is_kcbxs","是");
		data.put("price","15.5");
		data.put("storeNum","100");

		JSONArray dataArray = JSONArray.fromObject(data);
		JSONObject params = new JSONObject();
		params.put("key","875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5");
		params.put("data",dataArray);
		String sParams = params.toString();	
		JsonUtils.printJson(sParams);

		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);

		JSONObject jsonObject = JSONObject.fromObject(resultContent);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String result = responseData.getString("result");
		String dataCode = responseData.getString("code");

		Assert.assertEquals("000000", code);
		Assert.assertEquals("保存或更新药品及价格库存信息成功", result);
		Assert.assertEquals("SUCCESS", dataCode);

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [wit_selection].[zc_sumMaterial] where matcode = '"+matCode+"'";
			ResultSet resultSet = statement.executeQuery(querySql);
			//resultSet.absolute(1);

			while(resultSet.next()){
				String barcode = resultSet.getString("barcode");
				System.out.println(barcode);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//调更新药品接口
	@Test
	public void updateMed() throws ClientProtocolException, IOException, SQLException{
		
		String url = "http://123.56.242.250/ffcloud-epscm/pullMaterial/updateMaterial";

		Map<String, String> data = new HashMap<String, String>();
		data.put("barcode","药品条码005");
		data.put("matcode", matCode);
		data.put("batchNum","批号005");
		data.put("name","名称005");
		data.put("commonName","通用名005");
		data.put( "spec","规格005");
		data.put("unit","单位005");
		data.put("dosage","剂型005");
		data.put("licenseNum","国药准字005");
		data.put("manufName","厂家005");
		data.put("prodPlace","厂址005");
		data.put("productDate","2012-12-15");
		data.put("validDate","2013-12-15");
		data.put("retailPrice","15.5");
		data.put("zhongbao","5");
		data.put("createPerson","操作员005");
		data.put( "sellState","n");
		data.put("manufNameAbbr","东京");
		data.put("is_kcbxs","否");

		JSONArray dataArray = JSONArray.fromObject(data);
		JSONObject params = new JSONObject();
		params.put("key","875FC66B-0422-4E0A-B0CE-F1C47BF3E1B5");
		params.put("data",dataArray);
		String sParams = params.toString();	
		JsonUtils.printJson(sParams);

		String resultContent = HttpUtils.httpPostJson(url, sParams);
		System.out.println("请求结果 - " + resultContent);

		JSONObject jsonObject = JSONObject.fromObject(resultContent);
		String code = jsonObject.getString("code");
		JSONObject responseData = jsonObject.getJSONObject("data");
		String result = responseData.getString("result");
		String dataCode = responseData.getString("code");

		Assert.assertEquals("000000", code);
		Assert.assertEquals("更新全部传入药品信息成功", result);
		Assert.assertEquals("SUCCESS", dataCode);

		SqlUtils c = new SqlUtils();
		con = c.sqlSConnection(dbUrl, dbName, dbUserName, dbPassword);

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [wit_selection].[zc_sumMaterial] where matcode = '"+matCode+"'";
			ResultSet resultSet = statement.executeQuery(querySql);
			//resultSet.absolute(1);

			while(resultSet.next()){
				String barcode = resultSet.getString("barcode");
				//String actualMacCode = resultSet.getString("barcode");
				System.out.println(barcode);

				Assert.assertEquals(barcode, "药品条码005");
				//需要更多校验

			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	//测试后删除数据库和redis
	@AfterTest
	public void deleteMed() throws SQLException{
		try{
			String deleteSql = "DELETE FROM [wit_selection].[zc_sumMaterial] where matcode = '"+ matCode +"'";
			String deleteSqlP = "DELETE FROM [wit_selection].[zc_sumPriceAndNum] where matcode = '"+ matCode +"'";
			PreparedStatement pStatement = con.prepareStatement(deleteSql + deleteSqlP);
			pStatement.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}

		RedisUtils rc = new RedisUtils();
		Jedis jedis = rc.redisConn();

		try{
			jedis.hdel("MATERIAL_MATCODE_SUPPLIERID", "YP8888|66");
			jedis.hdel("PRISTOR_MATCODE_SUPPLIERID", "YP8888|66");	
			if(jedis.hget("MATERIAL_MATCODE_SUPPLIERID", "YP8888|66") == null && jedis.hget("PRISTOR_MATCODE_SUPPLIERID", "YP8888|66") == null){
				System.out.println("delete redis success");
			}else{
				System.out.println("delete redis fail");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
