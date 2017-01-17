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

public class GetDepartList {

	@Test
	public void getDepartList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/getDeptList?pharmacyId=200369";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray deptList = jsonObject.getJSONArray("deptList");
		Assert.assertEquals(deptList.size(), 8);

		String deptName = "";
		List<String> deptNameList = new ArrayList<String>();
		deptNameList.add("质量管理");
		deptNameList.add("采购");
		deptNameList.add("储存");
		deptNameList.add("销售");

		/*		int pharmacyId;
		List<Integer> pharmacyIdList = new ArrayList<Integer>();
		pharmacyIdList.add(200369);
		pharmacyIdList.add(200369);
		pharmacyIdList.add(200369);
		pharmacyIdList.add(200369);

		String pharmacyName ="";
		List<String> pharmacyNameList = new ArrayList<String>();
		pharmacyNameList.add("hyj的emp测试药店总店");
		pharmacyNameList.add("hyj的emp测试药店总店");
		pharmacyNameList.add("hyj的emp测试药店总店");
		pharmacyNameList.add("hyj的emp测试药店总店");*/

		for(int i = 0; i < 4 /*rolesList.size()*/; i++){
			deptName = deptList.getJSONObject(i).getString("deptName");
			int pharmacyId = deptList.getJSONObject(i).getInt("pharmacyId");
			String pharmacyName = deptList.getJSONObject(i).getString("pharmacyName");

			String expectDeptName = deptNameList.get(i);

			Assert.assertEquals(deptName, expectDeptName);
			Assert.assertEquals(pharmacyId, 200369);
			Assert.assertEquals(pharmacyName, "hyj的emp测试药店总店");
		}
	}
}
