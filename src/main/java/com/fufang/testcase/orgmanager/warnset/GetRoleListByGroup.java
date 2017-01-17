package com.fufang.testcase.orgmanager.warnset;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetRoleListByGroup {

	@DataProvider(name = "testParams")
	public static Object[][] testUrl(){
		return new Object[][]{
			{"param1","http://172.16.87.194:9999/organization-manager/warnSet/getRoleListByGroup?chainType=0&roleGroup=31",0,31},
			{"param2","http://172.16.87.194:9999/organization-manager/warnSet/getRoleListByGroup?chainType=0&roleGroup=22",0,22},
			{"param3","http://172.16.87.194:9999/organization-manager/warnSet/getRoleListByGroup?chainType=1&roleGroup=41",1,41}

		};
	}

	@Test(dataProvider = "testParams")
	public void getRoleListByGroup(String comments, String testUrl, int expectChianType, int expectRoleGroup) throws ClientProtocolException, IOException, URISyntaxException{
		System.out.println("------开始执行getRoleListByGroup------");

		//String testUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getAllRoleList";
		String userName= "hls1";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonobject = JSONObject.fromObject(result);
		JSONArray roleListArray = jsonobject.getJSONArray("roleList");
		
		for (int i = 0; i < roleListArray.size(); i++){
			int chainType = roleListArray.getJSONObject(i).getInt("chaintype"); 
			int roleGroup = roleListArray.getJSONObject(i).getInt("roleGroup");

			Assert.assertEquals(chainType, expectChianType);
			Assert.assertEquals(roleGroup, expectRoleGroup);
		}
	}
}
