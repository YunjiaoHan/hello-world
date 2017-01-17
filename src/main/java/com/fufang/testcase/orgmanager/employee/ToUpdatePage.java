package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import junit.framework.Assert;
import net.sf.json.JSONObject;

public class ToUpdatePage{

	@Test
	public void init() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.183:9999/organization-manager/employee/toUpdatePage?uId=32815057";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject oneUser = jsonObject.getJSONObject("oneUser");
		
		String pharmacyName = oneUser.getString("pharmacyName");
		String email = oneUser.getString("email");
		String mobile = oneUser.getString("mobile");
		
		Assert.assertEquals(pharmacyName, "hyj的emp测试药店总店");
		Assert.assertEquals(email, "hyjtest02emp@fu-fang.com");
		Assert.assertEquals(mobile, "13810866410");

	}
}