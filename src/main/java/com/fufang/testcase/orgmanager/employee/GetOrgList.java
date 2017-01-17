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

public class GetOrgList {
	
    @Test
	public void getOrgList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/getOrgList";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray orgList = jsonObject.getJSONArray("orgList");

		int pharmacyId;
		List<Integer> pharmacyIdList = new ArrayList<Integer>();
		pharmacyIdList.add(200369);
		pharmacyIdList.add(200370);

		String pharmacyName;
		List<String> pharmacyNameList = new ArrayList<String>();
		pharmacyNameList.add("hyj的emp测试药店总店");
		pharmacyNameList.add("hyj的emp测试药店分店1");

		String pharmacyCode = "";
		List<String> pharmacyCodeList = new ArrayList<String>();
		pharmacyCodeList.add("0002");
		pharmacyCodeList.add("000201");

		for(int i = 0; i < orgList.size(); i++){
			pharmacyId = orgList.getJSONObject(i).getInt("pharmacyId");
			pharmacyName = orgList.getJSONObject(i).getString("pharmacyName");
			pharmacyCode = orgList.getJSONObject(i).getString("pharmacyCode");

			int expectPharmacyId = pharmacyIdList.get(i);
			String expectPharmacyName = pharmacyNameList.get(i);
			String expectPharmacyCode = pharmacyCodeList.get(i);

			Assert.assertEquals(pharmacyId, expectPharmacyId);
			Assert.assertEquals(pharmacyName, expectPharmacyName);
			Assert.assertEquals(pharmacyCode, expectPharmacyCode);
		}
	}
}
