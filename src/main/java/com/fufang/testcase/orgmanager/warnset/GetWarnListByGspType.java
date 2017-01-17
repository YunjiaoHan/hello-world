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


public class GetWarnListByGspType {
	
	String userName= "hyjtest01";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

	@Test
	public void warnGspTypeOne() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行warnGspType=1");
		
		String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getWarnListByGspType?warnGspType=1";

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray warnListArray = jsonObject.getJSONArray("warnList");

		String warnType= "";
		List<String> expectWarnTypeList = new ArrayList<String>();
		expectWarnTypeList.add("1");
		expectWarnTypeList.add("32");
		expectWarnTypeList.add("57");
		expectWarnTypeList.add("56");	
		expectWarnTypeList.add("58");
		expectWarnTypeList.add("59");
		expectWarnTypeList.add("60");
		expectWarnTypeList.add("61");	
		expectWarnTypeList.add("62");
		expectWarnTypeList.add("64");
		expectWarnTypeList.add("65");
		
		String dayNum = "";
		List<String> expectDayNumList = new ArrayList<String>();
		expectDayNumList.add("14");
		expectDayNumList.add("15");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		expectDayNumList.add("0");
		
		String isOn = "";
		List<String> expectIsOnList = new ArrayList<String>();
		expectIsOnList.add("1");
		expectIsOnList.add("1");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		
		String maintainDay = "";
		List<String> expectmaintainDayList = new ArrayList<String>();
		expectmaintainDayList.add("30");
		expectmaintainDayList.add("100");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		expectmaintainDayList.add("0");
		
		String roleName = "";
		List<String> expectRoleNameList = new ArrayList<String>();
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		
		String warnGspType = "";
		List<String> expectWarnGspTypeList = new ArrayList<String>();
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		expectWarnGspTypeList.add("1");
		
		String licenseId = "";
		List<String> expectLicenseIdList = new ArrayList<String>();
		expectLicenseIdList.add("123");
		expectLicenseIdList.add("1");
		expectLicenseIdList.add("4D2DBF45-3A15-4C86-9B39-334AF7E296F9");
		expectLicenseIdList.add("45E1D27E-5679-4252-84A8-E99B1E11AD71");
		expectLicenseIdList.add("0FB02CFE-2BD4-4876-9BCB-049D2BEFDDDB");
		expectLicenseIdList.add("3AA61A5F-2B05-482B-BBEB-03328156002A");
		expectLicenseIdList.add("6AC53C7B-577C-4143-8E5B-2FC7A5F50D15");
		expectLicenseIdList.add("31B811B0-C683-4C61-88B9-F43F9D3ADB3E");
		expectLicenseIdList.add("1C7660DD-675B-4C9F-8C06-939EDA5C75E1");
		expectLicenseIdList.add("F53FB1CC-1491-431A-A1C5-8EB7F8292F05");
		expectLicenseIdList.add("89D3A7EB-1006-4336-BB65-0896A08E8BA1");
		
		String warnContent = "";
		List<String> expectWarnContentList = new ArrayList<String>();
		expectWarnContentList.add("《化妆品经营许可证》");
		expectWarnContentList.add("近效期商品");
		expectWarnContentList.add("《组织机构代码证》");
		expectWarnContentList.add("《企业法人营业执照》");
		expectWarnContentList.add("《药品经营许可证》");
		expectWarnContentList.add("《药品经营质量管理规范认证证书》");
		expectWarnContentList.add("《医疗器械经营企业许可证》");
		expectWarnContentList.add("《食品经营许可证》");
		expectWarnContentList.add("《食品卫生许可证》");
		expectWarnContentList.add("《化妆品卫生许可证》");
		expectWarnContentList.add("《化妆品经营许可证》");
		
		for(int i = 0; i<warnListArray.size(); i++){
			warnType = warnListArray.getJSONObject(i).getString("warnType");
			String expectWarnType = expectWarnTypeList.get(i);
			System.out.println(i+1 + ": warnType - " + expectWarnType);
			Assert.assertEquals(warnType, expectWarnType, "wrong warnType");

			dayNum = warnListArray.getJSONObject(i).getString("dayNum");
			String expectDayNum = expectDayNumList.get(i);
			System.out.println(i+1 + ": dayNum - " + expectDayNum);
			Assert.assertEquals(dayNum, expectDayNum,"wrong dayNum");

			isOn = warnListArray.getJSONObject(i).getString("isOn");
			String expectIsOn = expectIsOnList.get(i);
			System.out.println(i+1 + ": isOn - " + expectIsOn);
			Assert.assertEquals(isOn, expectIsOn,"wrong isOn");

			maintainDay = warnListArray.getJSONObject(i).getString("maintainDay");
			String expectMaintainDay = expectmaintainDayList.get(i);
			System.out.println(i+1 + ": maintainDay - " + expectMaintainDay);
			Assert.assertEquals(maintainDay, expectMaintainDay,"wrong maintainDay");

			roleName = warnListArray.getJSONObject(i).getString("roleNames");
			String expectRoleName = expectRoleNameList.get(i);
			System.out.println(i+1 + ": roleName - " + expectRoleName);
			Assert.assertEquals(roleName, expectRoleName,"wrong roleName");

			warnGspType = warnListArray.getJSONObject(i).getString("warnGspType");
			String expectWarnGspType = expectWarnGspTypeList.get(i);
			System.out.println(i+1 + ": warnGspType - " + expectWarnGspType);
			Assert.assertEquals(warnGspType, expectWarnGspType,"wrong warnGspType");

			licenseId = warnListArray.getJSONObject(i).getString("licenseId");
			String expectLicenseId = expectLicenseIdList.get(i);
			System.out.println(i+1 + ": licenseId - " + expectLicenseId);
			Assert.assertEquals(licenseId, expectLicenseId,"wrong licenseId");

			warnContent = warnListArray.getJSONObject(i).getString("warnContent");
			String expectWarnContent = expectWarnContentList.get(i);
			System.out.println(i+1 + ": warnContent - " + expectWarnContent);
			Assert.assertEquals(warnContent, expectWarnContent,"wrong warnContent");
		}
	}
	
	@Test
	public void warnGspTypeTwo() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行warnGspType=2");
		
		String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getWarnListByGspType?warnGspType=2";

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);			
		JSONArray warnListArray = jsonObject.getJSONArray("warnList");
		
		String warnType = "";
		List<String> expectwarnTypeList = new ArrayList<String>();
		expectwarnTypeList.add("21");
		expectwarnTypeList.add("56");
		expectwarnTypeList.add("4");
		expectwarnTypeList.add("5");
		expectwarnTypeList.add("6");
		expectwarnTypeList.add("55");
		
		String isOn = "";
		List<String> expectIsOnList = new ArrayList<String>();
		expectIsOnList.add("1");
		expectIsOnList.add("1");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		
		String roleName = "";
		List<String> expectRoleNameList = new ArrayList<String>();
        expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		
		String warnGspType = "";
		List<String> expectWarnGspTypeList = new ArrayList<String>();
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		
		String warnContent = "";
		List<String> expectWarnContentList = new ArrayList<String>();
		expectWarnContentList.add("积货和缺货商品");
		expectWarnContentList.add("滞销商品");
		expectWarnContentList.add("常规商品养护和检查");
		expectWarnContentList.add("重点商品养护和检查");
		expectWarnContentList.add("批准文号/注册证号有效期至");
		expectWarnContentList.add("过期商品");
		
		for(int i = 0; i<warnListArray.size(); i++){
			warnType = warnListArray.getJSONObject(i).getString("warnType");
			String expectWarnType = expectwarnTypeList.get(i);
			System.out.println(i+1 + ": warnType - " + expectWarnType);
			Assert.assertEquals(warnType, expectWarnType,"wrong warnType");

			isOn = warnListArray.getJSONObject(i).getString("isOn");
			String expectIsOn = expectIsOnList.get(i);
			System.out.println(i+1 + ": isOn - " + expectIsOn);
			Assert.assertEquals(isOn, expectIsOn,"wrong isOn");

			roleName = warnListArray.getJSONObject(i).getString("roleNames");
			String expectRoleName = expectRoleNameList.get(i);
			System.out.println(i+1 + ": roleName - " + expectRoleName);
			Assert.assertEquals(roleName, expectRoleName,"wrong roleName");

			warnGspType = warnListArray.getJSONObject(i).getString("warnGspType");
			String expectWarnGspType = expectWarnGspTypeList.get(i);
			System.out.println(i+1 + ": warnGspType - " + expectWarnGspType);
			Assert.assertEquals(warnGspType, expectWarnGspType,"wrong warnGspType");
	
			warnContent = warnListArray.getJSONObject(i).getString("warnContent");
			String expectWarnContent = expectWarnContentList.get(i);
			System.out.println(i+1 + ": warnContent - " + expectWarnContent);
			Assert.assertEquals(warnContent, expectWarnContent,"wrong warnContent");
		}
	}
	
	@Test
	public void warnGspTypeFour() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行warnGspType=4");
		
		String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getWarnListByGspType?warnGspType=4";

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);			
		JSONArray warnListArray = jsonObject.getJSONArray("warnList");
		
		String warnType = "";
		List<String> expectwarnTypeList = new ArrayList<String>();
		expectwarnTypeList.add("12");
		expectwarnTypeList.add("16");
		expectwarnTypeList.add("4");
		expectwarnTypeList.add("5");
		expectwarnTypeList.add("6");
		expectwarnTypeList.add("55");
		
		String isOn = "";
		List<String> expectIsOnList = new ArrayList<String>();
		expectIsOnList.add("0");
		expectIsOnList.add("1");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		expectIsOnList.add("0");
		
		String roleName = "";
		List<String> expectRoleNameList = new ArrayList<String>();
        expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		expectRoleNameList.add("");
		
		String warnGspType = "";
		List<String> expectWarnGspTypeList = new ArrayList<String>();
		expectWarnGspTypeList.add("4");
		expectWarnGspTypeList.add("4");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		expectWarnGspTypeList.add("2");
		
		String warnContent = "";
		List<String> expectWarnContentList = new ArrayList<String>();
		expectWarnContentList.add("常规商品养护和检查");
		expectWarnContentList.add("常规商品养护和检查");
		expectWarnContentList.add("常规商品养护和检查");
		expectWarnContentList.add("重点商品养护和检查");
		expectWarnContentList.add("批准文号/注册证号有效期至");
		expectWarnContentList.add("过期商品");
		
		for(int i = 0; i<warnListArray.size(); i++){
			warnType = warnListArray.getJSONObject(i).getString("warnType");
			String expectWarnType = expectwarnTypeList.get(i);
			System.out.println(i+1 + ": warnType - " + expectWarnType);
			Assert.assertEquals(warnType, expectWarnType,"wrong warnType");

			isOn = warnListArray.getJSONObject(i).getString("isOn");
			String expectIsOn = expectIsOnList.get(i);
			System.out.println(i+1 + ": isOn - " + expectIsOn);
			Assert.assertEquals(isOn, expectIsOn,"wrong isOn");

			roleName = warnListArray.getJSONObject(i).getString("roleNames");
			String expectRoleName = expectRoleNameList.get(i);
			System.out.println(i+1 + ": roleName - " + expectRoleName);
			Assert.assertEquals(roleName, expectRoleName,"wrong roleName");

			warnGspType = warnListArray.getJSONObject(i).getString("warnGspType");
			String expectWarnGspType = expectWarnGspTypeList.get(i);
			System.out.println(i+1 + ": warnGspType - " + expectWarnGspType);
			Assert.assertEquals(warnGspType, expectWarnGspType,"wrong warnGspType");
	
			warnContent = warnListArray.getJSONObject(i).getString("warnContent");
			String expectWarnContent = expectWarnContentList.get(i);
			System.out.println(i+1 + ": warnContent - " + expectWarnContent);
			Assert.assertEquals(warnContent, expectWarnContent,"wrong warnContent");
		}
	}
}