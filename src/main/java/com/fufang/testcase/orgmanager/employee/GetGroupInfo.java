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

public class GetGroupInfo {

	String userName= "hyjtest02emp";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

	@Test
	public void getGroupByDepart() throws ClientProtocolException, URISyntaxException, IOException {  
		String testUrl = "http://172.16.88.195:9999/organization-manager/employee/getGroupInfo?group=1";

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
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

	@Test
	public void getGroupByPosition() throws ClientProtocolException, URISyntaxException, IOException {  
		String testUrl = "http://172.16.88.195:9999/organization-manager/employee/getGroupInfo?group=2";

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject empGroup = jsonObject.getJSONObject("empGroup");
		JSONArray zPositionList = empGroup.getJSONArray("zbPositionList");

		int positionId;
		List<Integer> positionIdList = new ArrayList<Integer>();
		positionIdList.add(16025);
		positionIdList.add(16026);

		String positionName;
		List<String> positionNameList = new ArrayList<String>();
		positionNameList.add("企业负责人");
		positionNameList.add("质量负责人");

		for(int i = 0; i < 2; i++){
			positionId = zPositionList.getJSONObject(i).getInt("positionId");
			positionName = zPositionList.getJSONObject(i).getString("positionName");
			int expectPositionId = positionIdList.get(i);
			String expectPositionName = positionNameList.get(i);
			String pharmacyName = zPositionList.getJSONObject(i).getString("pharmacyName");

			Assert.assertEquals(positionId, expectPositionId);
			Assert.assertEquals(positionName, expectPositionName);
			Assert.assertEquals(pharmacyName, "hyj的emp测试药店总店");
		}
	}

	@Test
	public void getGroupByRole() throws ClientProtocolException, URISyntaxException, IOException {  
		String testUrl = "http://172.16.88.195:9999/organization-manager/employee/getGroupInfo?group=3";

		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONObject empGroup = jsonObject.getJSONObject("empGroup");
		JSONObject zd = empGroup.getJSONObject("zd");

		JSONObject zroleMap = zd.getJSONObject("roleMap");
		JSONArray buyList = zroleMap.getJSONArray("采购");

		String zRoleName;
		List<String> zRoleNameList = new ArrayList<String>();
		zRoleNameList.add("采购人员");

		String zRoleCode;
		List<String> zRoleCodeList = new ArrayList<String>();
		zRoleCodeList.add("20036901");

		for(int i = 0; i < 1/*roleMap.size()*/; i++){
			zRoleName = buyList.getJSONObject(i).getString("roleName");
			zRoleCode = buyList.getJSONObject(i).getString("roleCode");
			String pharmacyName = buyList.getJSONObject(i).getString("pharmacyName");
			String expectZRoleName = zRoleNameList.get(i);
			String expectZRoleCode = zRoleCodeList.get(i);

			Assert.assertEquals(zRoleName, expectZRoleName);
			Assert.assertEquals(zRoleCode, expectZRoleCode);
			Assert.assertEquals(pharmacyName, "hyj的emp测试药店总店");
		}

		JSONArray pharmacyList = empGroup.getJSONArray("pharmacyList");
		JSONObject froleMap = pharmacyList.getJSONObject(0).getJSONObject("roleMap");
		JSONArray infoList = froleMap.getJSONArray("信息");

		String fRoleName;
		List<String> fRoleNameList = new ArrayList<String>();
		fRoleNameList.add("财务人员");

		String fRoleCode;
		List<String> fRoleCodeList = new ArrayList<String>();
		fRoleCodeList.add("20037001");

		for(int i = 0; i < 1/*roleMap.size()*/; i++){
			fRoleName = infoList.getJSONObject(i).getString("roleName");
			fRoleCode = infoList.getJSONObject(i).getString("roleCode");
			String pharmacyName = infoList.getJSONObject(i).getString("pharmacyName");
			String expectFRoleName = fRoleNameList.get(i);
			String expectFRoleCode = fRoleCodeList.get(i);

			Assert.assertEquals(fRoleName, expectFRoleName);
			Assert.assertEquals(fRoleCode, expectFRoleCode);
			Assert.assertEquals(pharmacyName, "hyj的emp测试药店分店1");
		}
	}
}
