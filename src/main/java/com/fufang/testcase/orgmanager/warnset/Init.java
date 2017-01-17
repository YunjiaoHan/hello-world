package com.fufang.testcase.orgmanager.warnset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class Init {

	@Test
	public void init() throws ClientProtocolException, URISyntaxException, IOException{

		String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/init";
		String userName= "hyjtest02emp";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray warnListArray = jsonObject.getJSONArray("warnList");
		System.out.println("warnListArray - "+ warnListArray);

		if(warnListArray==null||warnListArray.size()==0){
			Assert.assertFalse(warnListArray==null||warnListArray.size()==0, "返回数据为空");
		}

		String warnType= "";
		List<String> expectWarnTypeList = new ArrayList<String>();
		expectWarnTypeList.add("0");
		expectWarnTypeList.add("15");

		for(int i = 0; i<warnListArray.size(); i++){
			warnType = warnListArray.getJSONObject(i).getString("warnType");
			String expectWarnType = expectWarnTypeList.get(i);
			System.out.println(i+1 + ": warnType - " + expectWarnType);
			Assert.assertEquals(warnType, expectWarnType, "wrong warnType");
		}

		String dayNum = "";
		List<String> expectDayNumList = new ArrayList<String>();
		expectDayNumList.add("15");
		expectDayNumList.add("10");

		for(int i = 0; i<warnListArray.size(); i++){
			dayNum = warnListArray.getJSONObject(i).getString("dayNum");
			String expectDayNum = expectDayNumList.get(i);
			System.out.println(i+1 + ": dayNum - " + expectDayNum);
			Assert.assertEquals(dayNum, expectDayNum,"wrong dayNum");
		}

		String isOn = "";
		List<String> expectIsOnList = new ArrayList<String>();
		expectIsOnList.add("1");
		expectIsOnList.add("1");

		for(int i = 0; i<warnListArray.size(); i++){
			isOn = warnListArray.getJSONObject(i).getString("isOn");
			String expectIsOn = expectIsOnList.get(i);
			System.out.println(i+1 + ": isOn - " + expectIsOn);
			Assert.assertEquals(isOn, expectIsOn,"wrong isOn");
		}

		String maintainDay = "";
		List<String> expectmaintainDayList = new ArrayList<String>();
		expectmaintainDayList.add("180");
		expectmaintainDayList.add("90");

		for(int i = 0; i<warnListArray.size(); i++){
			maintainDay = warnListArray.getJSONObject(i).getString("maintainDay");
			String expectMaintainDay = expectmaintainDayList.get(i);
			System.out.println(i+1 + ": maintainDay - " + expectMaintainDay);
			Assert.assertEquals(maintainDay, expectMaintainDay,"wrong maintainDay");
		}

		String roleName = "";
		List<String> expectRoleNameList = new ArrayList<String>();
		expectRoleNameList.add("");
		expectRoleNameList.add("");

		for(int i = 0; i<warnListArray.size(); i++){
			roleName = warnListArray.getJSONObject(i).getString("roleNames");
			String expectRoleName = expectRoleNameList.get(i);
			System.out.println(i+1 + ": roleName - " + expectRoleName);
			Assert.assertEquals(roleName, expectRoleName,"wrong roleName");
		}

		String warnGspType = "";
		List<String> expectWarnGspTypeList = new ArrayList<String>();
		expectWarnGspTypeList.add("0");
		expectWarnGspTypeList.add("0");

		for(int i = 0; i<warnListArray.size(); i++){
			warnGspType = warnListArray.getJSONObject(i).getString("warnGspType");
			String expectWarnGspType = expectWarnGspTypeList.get(i);
			System.out.println(i+1 + ": warnGspType - " + expectWarnGspType);
			Assert.assertEquals(warnGspType, expectWarnGspType,"wrong warnGspType");
		}

		String licenseId = "";
		List<String> expectLicenseIdList = new ArrayList<String>();
		expectLicenseIdList.add("211");
		expectLicenseIdList.add("231");

		for(int i = 0; i<warnListArray.size(); i++){
			licenseId = warnListArray.getJSONObject(i).getString("licenseId");
			String expectLicenseId = expectLicenseIdList.get(i);
			System.out.println(i+1 + ": licenseId - " + expectLicenseId);
			Assert.assertEquals(licenseId, expectLicenseId,"wrong licenseId");
		}

		String warnContent = "";
		List<String> expectWarnContentList = new ArrayList<String>();
		expectWarnContentList.add("《组织机构代码证》");
		expectWarnContentList.add("随货通行单(票)样式");

		for(int i = 0; i<warnListArray.size(); i++){
			warnContent = warnListArray.getJSONObject(i).getString("warnContent");
			String expectWarnContent = expectWarnContentList.get(i);
			System.out.println(i+1 + ": warnContent - " + expectWarnContent);
			Assert.assertEquals(warnContent, expectWarnContent,"wrong warnContent");
		}
	}	
}

