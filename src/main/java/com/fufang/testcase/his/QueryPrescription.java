package com.fufang.testcase.his;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.SqlUtils;

import net.sf.json.JSONObject;

public class QueryPrescription {

	String dbUrl = "jdbc:mysql://172.16.88.112:8066/HISFC?userUnicode=true&characterEncoding=UTF-8";
	String dbUserName = "root";
	String dbPassword = "root";

	@BeforeTest
	public void insertSql() throws SQLException{

		SqlUtils c = new SqlUtils();
		Connection con = c.mySqlConnection(dbUrl, dbUserName, dbPassword);

		String sql = "INSERT INTO t_prescriptionnew (id,prescriptionCode ) VALUES ('30f94ee5-53e7-4cdc-b815-38ed9e40770a','1234')";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);	
			pstmt.executeUpdate(sql);
			sql = "INSERT INTO t_prescription_attachinfo (`id`, `valid`, `getMode`, `pharmacyId`, `pharmacyName`, `isGet`, `salesCode`, `salesTime`, `salesMoney`, `cashierName`, `returnCode`, `returnTime`, `returnMoney`, `returnCashName`, `createDate`, `updateDate`, `diagnosisId`, `hisPrescriptionCode`, `departCode`, `departName`, `upDepartName`, `upDepartCode`, `prescriptionGroup`, `communityId`) VALUES ('30f94ee5-53e7-4cdc-b815-38ed9e40770a', '0', '0', '3581', '药店名称', '1', 'XS20160706-0001', '2013-08-26 12:50:52', '108.55', '销售员', 'XT20160706-0001', '2013-08-26 12:50:52', '108.55', '退货员', '2016-08-30 16:35:47', '2016-12-21 16:38:07', '30000', '30001', 'J3000', '龙潭服务中心', '社卫中心', 'J2700', 'CF-052644', '200184')";			
			pstmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(con!=null)
					con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}

	@Test
	public void queryPrescription() throws UnsupportedEncodingException, ClientProtocolException, IOException{

		String url = "http://ct.fu-fang.com:8001/ffcloud-hisFc/queryPrescription";

		JSONObject paramsJson = new JSONObject();
		paramsJson.put("departCode", "J3000");
		paramsJson.put("diagnosisId", "30000");
		paramsJson.put("prescriptionCode","1234");
//		paramsJson.put("hisPrescriptionCode","30001");

		String params = paramsJson.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String data = jsonObject.getString("data");

		Assert.assertEquals(code, "000000");
		Assert.assertEquals(data, "{\"returnCode\":\"XT20160706-0001\",\"isGet\":1}");
	}
	
	@Test
	public void invalidParams() throws UnsupportedEncodingException, ClientProtocolException, IOException{

		String url = "http://ct.fu-fang.com:8001/ffcloud-hisFc/queryPrescription";

		JSONObject paramsJson = new JSONObject();
		paramsJson.put("departCode", "");
		paramsJson.put("diagnosisId", "30000");
		paramsJson.put("prescriptionCode","1234");
//		paramsJson.put("hisPrescriptionCode","30001");

		String params = paramsJson.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String message = jsonObject.getString("message");

		Assert.assertEquals(code, "100000");
		boolean messageMatch = message.contains("查询处方信息失败,错误信息:输入参数不完整");
		Assert.assertEquals(messageMatch, true);
	}

	@AfterTest
	public void deleteSql() throws SQLException{

		SqlUtils c = new SqlUtils();
		Connection conn = c.mySqlConnection(dbUrl, dbUserName, dbPassword);

		String sql = "DELETE FROM t_prescriptionnew WHERE id = '30f94ee5-53e7-4cdc-b815-38ed9e40770a'";
		try{
			PreparedStatement pstmt = conn.prepareStatement(sql);	
			pstmt.executeUpdate();
			sql = "DELETE FROM t_prescription_attachinfo WHERE id = '30f94ee5-53e7-4cdc-b815-38ed9e40770a'";
			pstmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(conn!=null)
					conn.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
}
