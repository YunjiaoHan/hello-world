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

public class GetAllRoleList {

	@Test
	public void getAllRoleList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/getAllRoleList?pharmacyId=200369";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray rolesList = jsonObject.getJSONArray("roleList");

		Assert.assertEquals(rolesList.size(), 63);

		String name ="";
		List<String> nameList = new ArrayList<String>();
		nameList.add("[总部]采购人员");
		nameList.add("[总部]采购人员");
		nameList.add("[总部]储存人员");
		nameList.add("[总部]销售人员");
		//nameList.add("[总部]配送人员");

		String code ="";
		List<String> codeList = new ArrayList<String>();
		codeList.add("20036901");
		codeList.add("2101");
		codeList.add("3101");
		codeList.add("4101");
		//codeList.add("5101");

		int roleGroup;
		List<Integer> roleGroupList = new ArrayList<Integer>();
		roleGroupList.add(31);
		roleGroupList.add(31);
		roleGroupList.add(41);
		roleGroupList.add(61);
		//roleGroupList.add(51);

		String roleGroupName ="";
		List<String> roleGroupNameList = new ArrayList<String>();
		roleGroupNameList.add("采购");
		roleGroupNameList.add("采购");
		roleGroupNameList.add("存储");
		roleGroupNameList.add("销售");
		//roleGroupNameList.add("配送");

		for(int i = 0; i < 4 /*rolesList.size()*/; i++){
			name = rolesList.getJSONObject(i).getString("name");
			code = rolesList.getJSONObject(i).getString("code");
			roleGroup = rolesList.getJSONObject(i).getInt("roleGroup");
			roleGroupName = rolesList.getJSONObject(i).getString("roleGoupName");

			String expectName = nameList.get(i);
			String expectCode = codeList.get(i);
			int expectRoleGroup = roleGroupList.get(i);
			String expectRoleGroupName = roleGroupNameList.get(i);

			Assert.assertEquals(name, expectName);
			Assert.assertEquals(code, expectCode);
			Assert.assertEquals(roleGroup, expectRoleGroup);
			Assert.assertEquals(roleGroupName, expectRoleGroupName);
		}
	}
}
