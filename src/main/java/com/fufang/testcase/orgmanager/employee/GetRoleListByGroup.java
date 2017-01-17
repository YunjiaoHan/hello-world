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

public class GetRoleListByGroup {

	static String userName= "hyjtest02emp";
	static String pwd = "00000000";
	static String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

	@Test
	public void headRoleList() throws ClientProtocolException, URISyntaxException, IOException {  

		String testUrl = "http://172.16.88.195:9999/organization-manager/employee/getRoleListByGroup?pharmacyId=200369+&chainType=0&roleGroup=11";

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray rolesList = jsonObject.getJSONArray("roleList");

		//Assert.assertEquals(rolesList.size(), 61);

		String name ="";
		List<String> nameList = new ArrayList<String>();
		nameList.add("[总部]信息管理人员");
		nameList.add("[总部]企业负责人");
		nameList.add("[总部]企业法定代表人");
		nameList.add("[总部]质量负责人");

		String code ="";
		List<String> codeList = new ArrayList<String>();
		codeList.add("8101");
		codeList.add("0001");
		codeList.add("0001");
		codeList.add("0002");

		for(int i = 0; i < 4 /*rolesList.size()*/; i++){
			name = rolesList.getJSONObject(i).getString("name");
			code = rolesList.getJSONObject(i).getString("code");
			int roleGroup = rolesList.getJSONObject(i).getInt("roleGroup");
			String roleGroupName = rolesList.getJSONObject(i).getString("roleGoupName");

			String expectName = nameList.get(i);
			String expectCode = codeList.get(i);

			Assert.assertEquals(name, expectName);
			Assert.assertEquals(code, expectCode);
			Assert.assertEquals(roleGroup, 11);
			Assert.assertEquals(roleGroupName, "管理");
		}
	}

	@Test
	public void branchRoleList() throws ClientProtocolException, URISyntaxException, IOException {  

		String testUrl = "http://172.16.88.195:9999/organization-manager/employee/getRoleListByGroup?pharmacyId=200370&chainType=1&roleGroup=21";

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray rolesList = jsonObject.getJSONArray("roleList");

		//Assert.assertEquals(rolesList.size(), 61);

		String name ="";
		List<String> nameList = new ArrayList<String>();
		nameList.add("[分店]处方审核人员-成药");
		nameList.add("[分店]处方发药人员-成药");
		nameList.add("[分店]处方登记人员-中药饮片");
		nameList.add("[分店]处方审核人员-中药饮片");

		String code ="";
		List<String> codeList = new ArrayList<String>();
		codeList.add("1402");
		codeList.add("1405");
		codeList.add("1411");
		codeList.add("1412");

		for(int i = 0; i < 4 /*rolesList.size()*/; i++){
			name = rolesList.getJSONObject(i).getString("name");
			code = rolesList.getJSONObject(i).getString("code");
			int roleGroup = rolesList.getJSONObject(i).getInt("roleGroup");
			String roleGroupName = rolesList.getJSONObject(i).getString("roleGoupName");

			String expectName = nameList.get(i);
			String expectCode = codeList.get(i);

			Assert.assertEquals(name, expectName);
			Assert.assertEquals(code, expectCode);
			Assert.assertEquals(roleGroup, 21);
			Assert.assertEquals(roleGroupName, "质量");
		}
	}
}
