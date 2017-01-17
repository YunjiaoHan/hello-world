package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class CheckEmployee {

	@Test
	public void getAllRoleList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.87.189:9999/organization-manager/employee/checkEmptyee";
		String userName= "hyjtest";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String check = jsonObject.getString("check");
		
		Assert.assertEquals(check, "0");
	}
}
		