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

public class Init {
	
	@Test
	public void init() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/init";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		
		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject empGroup = jsonObject.getJSONObject("empGroup");
		
		JSONArray pharmacyList = empGroup.getJSONArray("pharmacyList");
		
		String pharmacyId;
		List<String> pharmacyIdList = new ArrayList<String>();
		pharmacyIdList.add("200370");
		
		String pharmacyName;
		List<String> pharmacyNameList = new ArrayList<String>();
		pharmacyNameList.add("hyj的emp测试药店分店1");
		
		for(int i = 0; i < pharmacyList.size(); i++){
			pharmacyId = pharmacyList.getJSONObject(i).getString("pharmacyId");
			pharmacyName = pharmacyList.getJSONObject(i).getString("pharmacyName");
			String expectPharnacyId = pharmacyIdList.get(i);
			String expectPharmacyName = pharmacyNameList.get(i);
			
			Assert.assertEquals(pharmacyId, expectPharnacyId);
			Assert.assertEquals(pharmacyName, expectPharmacyName);
		}
		
		JSONArray fDeptList = pharmacyList.getJSONObject(0).getJSONArray("deptList");
		System.out.println(fDeptList);
		
		int fDepartId;
		List<Integer> fDepartIdList = new ArrayList<Integer>();
		fDepartIdList.add(23669);
		fDepartIdList.add(23670);
		
		String fDepartName;
		List<String> fDepartNameList = new ArrayList<String>();
		fDepartNameList.add("采购部门");
		fDepartNameList.add("储存部门");
		
		for(int i = 0; i < 2; i++){
			fDepartId = fDeptList.getJSONObject(i).getInt("deptId");
			fDepartName = fDeptList.getJSONObject(i).getString("deptName");
			String fpharmacyName = fDeptList.getJSONObject(i).getString("pharmacyName");
			int expectFDepartId = fDepartIdList.get(i);
			String expectFDepartName = fDepartNameList.get(i);
			
			Assert.assertEquals(fDepartId, expectFDepartId);
			Assert.assertEquals(fDepartName, expectFDepartName);
			Assert.assertEquals(fpharmacyName, "hyj的emp测试药店分店1");
		}
		
		JSONObject zd = empGroup.getJSONObject("zd");
		JSONArray zDepartList = zd.getJSONArray("deptList");
		System.out.println(zDepartList);
		
		int zDeptId;
		List<Integer> zDeptIdList = new ArrayList<Integer>();
		zDeptIdList.add(23661);
		zDeptIdList.add(23662);
		
		String zDeptName;
		List<String> zDeptNameList = new ArrayList<String>();
		zDeptNameList.add("质量管理");
		zDeptNameList.add("采购");
		
		for(int i = 0; i < 2; i++){
			zDeptId = zDepartList.getJSONObject(i).getInt("deptId");
			zDeptName = zDepartList.getJSONObject(i).getString("deptName");
			String zPharmacyName = zDepartList.getJSONObject(i).getString("pharmacyName");
			int expectZDeptId = zDeptIdList.get(i);
			String expectZDeptName = zDeptNameList.get(i);
			
			Assert.assertEquals(zDeptId, expectZDeptId);
			Assert.assertEquals(zDeptName, expectZDeptName);
			Assert.assertEquals(zPharmacyName, "hyj的emp测试药店总店");
		}
	}
}
