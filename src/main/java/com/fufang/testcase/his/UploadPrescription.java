package com.fufang.testcase.his;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;
import com.fufang.utils.SqlUtils;

import net.sf.json.JSONObject;

public class UploadPrescription {

	String dbUrl = "jdbc:mysql://172.16.88.112:8066/HISFC?userUnicode=true&characterEncoding=UTF-8";
	String dbUserName = "root";
	String dbPassword = "root";
	String id;

	public String getId() {
		return id;
	}

	private void setId(String id) {
		this.id = id;
	}

	@Test
	public void uploadPrescription() throws UnsupportedEncodingException, ClientProtocolException, IOException, SQLException{

		String url = "http://ct.fu-fang.com:8001/ffcloud-hisFc/uploadPrescription";
		String params = "{\"diagnosis\":[{\"CLINC_NO\":\"d00\",\"DIAG_NAME\":\"测试诊疗\",\"NOTE\":\"\",\"SOAP_SEQID\":\"000000\"}],\"hisCode\":\"mtg201\",\"prescriptions\":[{\"AGE\":\"83\",\"CARDNO\":\"12345678901S\",\"CASE_GROUP\":\"1\",\"CLIENT_NAME\":\"测试患者\",\"CLINC_NO\":\"0000CL\",\"CLINIC_DATE\":\"2016-12-1900:00:00\",\"DEPART_CODE\":\"mtg201\",\"DEPART_NAME\":\"门头沟服务站\",\"DEPART_UPCODE\":\"mtg101\",\"DOC_NAME\":\"测试医生\",\"EHR_NEWNO\":\"06000948-A\",\"ID_CARD\":\"110101193212290013\",\"MEDICARE\":\"8\",\"MED_TYPE\":\"02\",\"MOBILE_PHONE\":\"\",\"NOW_ADDRESS\":\"景山街道隆福寺社区美术馆东街2\",\"PRESCRI_TYPE\":\"3\",\"SEX\":\"男\",\"SOAP_SEQID\":\"000000\",\"UPCODE_NAME\":\"门头沟服务中心\",\"VALID\":\"1\",\"FFPRESCRIPTIONCODE\":\"11010100816122000FF\",\"prescriptionItems\":[{\"CHARGE_CODE\":\"550122220016\",\"CHARGE_OPR_ID\":\"测试收款员1\",\"CLINC_NO\":\"0000CL\",\"FREQUENCY_NAME\":\"Bid\",\"IS_EXTERNAL\":\"1\",\"LEAST_UNIT\":\"g\",\"MED_GNAME\":\"三七伤药片\",\"MED_PRICE\":\"5\",\"MED_SEQID\":\"215934\",\"MED_SPEC\":\"18片*2板\",\"NUM_PER\":\"5\",\"REAL_AMOUNT\":\"5\",\"SEND_NUM\":\"2\",\"SF_TIME\":\"2016-12-2100:00:00\",\"SOAP_MEDID\":\"0000SM\",\"SOAP_SEQID\":\"000000\",\"UNIT_PER\":\"g\",\"USAGE_NAME\":\"口服\",\"VALID\":\"1\"}]}]}";
		JsonUtils.printJson(params);
		String result = HttpUtils.httpPostJson(url, params);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String data = jsonObject.getString("data");
		Assert.assertEquals(code, "000000");
		Assert.assertEquals(data, "上传成功！");

		SqlUtils c = new SqlUtils();
		Connection con = c.mySqlConnection(dbUrl, dbUserName, dbPassword);		
		String sql = "SELECT * FROM `t_prescriptionnew` WHERE patientName = '测试患者';";
		try{
			Statement stmt = con.createStatement();
			ResultSet rspn = stmt.executeQuery(sql);
			while(rspn.next()){
				setId(rspn.getString("id"));
				String address = rspn.getString("address");
				String prescriptionCode = rspn.getString("prescriptionCode");			
				Assert.assertEquals(address, "景山街道隆福寺社区美术馆东街2");
				Assert.assertEquals(prescriptionCode, "11010100816122000FF");
			}
			sql = "SELECT * FROM t_prescription_attachinfo WHERE id = '" +id+ "'";
			ResultSet rspa = stmt.executeQuery(sql);
			while(rspa.next()){
				String diagnosisId = rspa.getString("diagnosisId");
				Assert.assertEquals(diagnosisId, "000000");
			}
			sql = "SELECT * FROM t_diagnosis WHERE diagnosisId = '000000'";
			ResultSet rsd = stmt.executeQuery(sql);
			while(rsd.next()){
				String diagnosisName = rsd.getString("diagnosisName");
				Assert.assertEquals(diagnosisName, "测试诊疗");
			}
			sql = "SELECT * FROM `t_prescription_itemnew` WHERE prescriptionCode = '110101008161220000FF'";
			ResultSet rspin = stmt.executeQuery(sql);
			while(rspin.next()){
				String matName = rspin.getString("matName");
				Assert.assertEquals(matName, "三七伤药片");
			}
			sql = "SELECT * FROM `t_prescriptionitem_attachinfo` WHERE hisPrescriptionCode = '0000CL'";
			ResultSet rspia = stmt.executeQuery(sql);
			while(rspia.next()){
				String ffId = rspia.getString("ffId");
				Assert.assertEquals(ffId, "215934");
			}

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(con!=null){
					con.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
		System.out.println(getId());
	}

	@AfterTest
	public void deleteSql() throws SQLException{

		SqlUtils c = new SqlUtils();
		Connection con = c.mySqlConnection(dbUrl, dbUserName, dbPassword);

		String sql = "DELETE FROM t_prescriptionnew WHERE id = '" 
				+id+ "';DELETE FROM t_prescription_attachinfo WHERE id = '"
				+id+ "';DELETE FROM t_diagnosis WHERE diagnosisCode = 'd00';"+
				"DELETE FROM t_prescription_itemnew where prescriptionCode = '11010100816122000FF';"+
				"DELETE FROM t_prescriptionitem_attachinfo WHERE diagnosisId = '000000'";
		try{
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();

		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(con!=null){
					con.close();
				}
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
}
