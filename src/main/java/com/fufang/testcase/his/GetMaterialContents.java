package com.fufang.testcase.his;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.SqlUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetMaterialContents {

	Connection Con;
	String dbUrl = "jdbc:mysql://222.128.34.242:3306/HISFC?userUnicode=true&characterEncoding=UTF-8";
	String dbUserName = "root";
	String dbPassword = "fufang";

	@BeforeTest
	public void insertSql() throws SQLException{

		SqlUtils c = new SqlUtils();
		Con = c.mySqlConnection(dbUrl, dbUserName, dbPassword);

		String sql = "INSERT INTO `HISFC`.`t_community_contents` (`communityId`, `hisCode`, `contentsId`, `createDate`, `updateDate`) VALUES ('200301', 'J3001', 'a31cec32-d07c-4497-8841-fc864033676a', '2016-12-20 11:43:55', NULL);";
		try{
			PreparedStatement pstmt = Con.prepareStatement(sql);	
			pstmt.executeUpdate(sql);
			sql = "INSERT INTO `HISFC`.`t_contents_item` (`contentsItemId`, `headId`, `headCode`, `ffId`, `name`, `materialType`, `commonName`, `licenseNum`, `manufName`, `unitName`, `spec`, `dosage`, `isDelete`, `createDate`, `updateDate`) VALUES ('031bbb2f-bda6-4c29-8e8a-c34e3cdca46d', 'a31cec32-d07c-4497-8841-fc864033676a', 'HYJ', '215911', '感冒止咳颗粒', 'Z', '感冒止咳颗粒', '国药准字Z20093536', '湖北襄阳隆中药业集团有限公司', '盒', '10克*12袋', '颗粒剂', NULL, '2016-09-19 16:32:06', NULL);";
			pstmt.executeUpdate(sql);
			sql = "INSERT INTO `HISFC`.`t_contents_item` (`contentsItemId`, `headId`, `headCode`, `ffId`, `name`, `materialType`, `commonName`, `licenseNum`, `manufName`, `unitName`, `spec`, `dosage`, `isDelete`, `createDate`, `updateDate`) VALUES ('03281538-cfc7-4917-a73f-e4cfe2dd9da6', 'a31cec32-d07c-4497-8841-fc864033676a', 'HYJ', '209247', '螺旋藻片', 'Z', '螺旋藻片', '国食健字G20090276', '广州联存医药科技有限公司', '瓶', '0.25克*300片', '片剂', NULL, '2016-09-19 16:29:39', NULL);";
			pstmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(Con!=null)
					Con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}

	@Test
	public void getContents() throws ClientProtocolException, IOException{

		String hiscode = "J3001";
		String url = "http://222.128.34.242:8090/ffcloud-hisFc/getMaterialContents/"+hiscode;

		String result = HttpUtils.httpGet(url);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");

		Assert.assertEquals(code, "000000");

		JSONArray dataArray = jsonObject.getJSONArray("data");
		System.out.println(dataArray);

		List<String> nameList = new ArrayList<String>();
		nameList.add("感冒止咳颗粒");
		nameList.add("螺旋藻片");
		
		List<String> licenseNumList = new ArrayList<String>();
		licenseNumList.add("国药准字Z20093536");
		licenseNumList.add("国食健字G20090276");
		
		
		for(int i = 0; i < dataArray.size(); i++){
			String name = dataArray.getJSONObject(i).getString("name");
			String headId = dataArray.getJSONObject(i).getString("headId");
			String licenseNum = dataArray.getJSONObject(i).getString("licenseNum");
			String expectName = nameList.get(i);
			String expectLicesenNum = licenseNumList.get(i);

			Assert.assertEquals(name, expectName);
			Assert.assertEquals(licenseNum, expectLicesenNum);
			Assert.assertEquals(headId, "a31cec32-d07c-4497-8841-fc864033676a");
		}
	}

	@AfterTest
	public void deleteSql() throws SQLException{

		SqlUtils c = new SqlUtils();
		Connection Conn = c.mySqlConnection(dbUrl, dbUserName, dbPassword);

		String sql = "DELETE FROM HISFC.t_community_contents WHERE communityId = '200301';";
		try{
			PreparedStatement pstmt = Conn.prepareStatement(sql);	
			pstmt.executeUpdate();
			sql = "DELETE FROM HISFC.t_contents_item WHERE contentsItemId in ('031bbb2f-bda6-4c29-8e8a-c34e3cdca46d','03281538-cfc7-4917-a73f-e4cfe2dd9da6')";
			pstmt.executeUpdate(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			try{
				if(Con!=null)
					Con.close();
			}catch(SQLException se){
				se.printStackTrace();
			}
		}
	}
}
