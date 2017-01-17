package com.fufang.testcase.orgmanager.employee;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.junit.Assert;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetGspLicenseList {
	
	@Test
	public void getGspLicenseList() throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/getGspLicenseList?pharmacyId=200259";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		
		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray  gspLicenseList = jsonObject.getJSONArray("gspLicenseList");
		
		int gspType;
		List<Integer> gspTypeList = new ArrayList<Integer>();
		gspTypeList.add(2);
		gspTypeList.add(2);
		gspTypeList.add(2);
		gspTypeList.add(2);
		
		String gspTypeName;
		List<String> gspTypeNameList = new ArrayList<String>();
		gspTypeNameList.add("员工资质");
		gspTypeNameList.add("员工资质");
		gspTypeNameList.add("员工资质");
		gspTypeNameList.add("员工资质");
		
		int licenseType;
		List<Integer> licenseTypeList = new ArrayList<Integer>();
		licenseTypeList.add(0);
		licenseTypeList.add(1);
		licenseTypeList.add(1);
		licenseTypeList.add(1);
		
		String licenseTypeName;
		List<String> licenseTypeNameList = new ArrayList<String>();
		licenseTypeNameList.add("职称证");
		licenseTypeNameList.add("资格证");
		licenseTypeNameList.add("资格证");
		licenseTypeNameList.add("资格证");
		
		String description;
		List<String> descriptionList = new ArrayList<String>();
		descriptionList.add("《中药调剂员》");
		descriptionList.add("《执业药师》");
		descriptionList.add("《从业药师》");
		descriptionList.add("《药剂师》");
		
		for(int i = 0; i < 4; i++){
			gspType = gspLicenseList.getJSONObject(i).getInt("gspType");
			gspTypeName = gspLicenseList.getJSONObject(i).getString("gspTypeName");
			licenseType = gspLicenseList.getJSONObject(i).getInt("licenseType");
			licenseTypeName = gspLicenseList.getJSONObject(i).getString("licenseTypeName");
			description = gspLicenseList.getJSONObject(i).getString("description");
			
			int expectGspType = gspTypeList.get(i);
			String expectGspTypeName = gspTypeNameList.get(i);
			int expectLicenseType = licenseTypeList.get(i);
			String expectLicenseTypeName = licenseTypeNameList.get(i);
			String expectDescription = descriptionList.get(i);
			
			Assert.assertEquals(gspType, expectGspType);
			Assert.assertEquals(gspTypeName, expectGspTypeName);
			Assert.assertEquals(licenseType, expectLicenseType);
			Assert.assertEquals(licenseTypeName, expectLicenseTypeName);
			Assert.assertEquals(description, expectDescription);
			
		}
		
		
		
	}
}
