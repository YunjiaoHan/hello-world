package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import junit.framework.Assert;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetPositionList {

	@Test
	public void getPositionList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/getPositionList";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_positionLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray positionList = jsonObject.getJSONArray("positionList");

		int positionId;
		List<Integer> positionIdList = new ArrayList<Integer>();
		positionIdList.add(16025);
		positionIdList.add(16026);

		String positionName;
		List<String> positionNameList = new ArrayList<String>();
		positionNameList.add("企业负责人");
		positionNameList.add("质量负责人");

		String pharmacyName;
		List<String> pharmacyNameList = new ArrayList<String>();
		pharmacyNameList.add("hyj的emp测试药店总店");
		pharmacyNameList.add("hyj的emp测试药店总店");

		for(int i = 0; i < 2 /*positionList.size()*/; i++){
			positionId = positionList.getJSONObject(i).getInt("positionId");
			positionName = positionList.getJSONObject(i).getString("positionName");
			pharmacyName = positionList.getJSONObject(i).getString("pharmacyName");

			int expectpositionId = positionIdList.get(i);
			String expectpositionName = positionNameList.get(i);
			String expectPharmacyName = pharmacyNameList.get(i);

			Assert.assertEquals(positionId, expectpositionId);
			Assert.assertEquals(positionName, expectpositionName);
			Assert.assertEquals(pharmacyName, expectPharmacyName);
		}
	}
}
