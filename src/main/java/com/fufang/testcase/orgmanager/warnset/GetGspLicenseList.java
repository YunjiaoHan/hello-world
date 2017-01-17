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

public class GetGspLicenseList {

	String userName= "hyjtest";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

	@Test
	public void licenseTypeZero() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行gspType=0");

		String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getGspLicenseList?warnGspType=0";

		String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
		System.out.println("result - " + result);
		//解析出JSON
		JSONObject jsonObject = JSONObject.fromObject(result);	
		JSONArray gspLicenseListArray;	
		gspLicenseListArray = jsonObject.getJSONArray("gspLicenseList");
		System.out.println("gspLicenseList - " + gspLicenseListArray);

		//判断JSON是否为空
		if(gspLicenseListArray==null||gspLicenseListArray.size()==0){
			Assert.assertFalse(gspLicenseListArray==null||gspLicenseListArray.size()==0, "返回数据为空");
		}

		//创建expect List,添加期望结果
		{
			String licenseId = "";
			List<String> expectLicenseIdList = new ArrayList<String>();
			expectLicenseIdList.add("2014");
			expectLicenseIdList.add("1996");

			String gspType = "";
			List<String> expectGspTypeList = new ArrayList<String>();
			expectGspTypeList.add("0");
			expectGspTypeList.add("0");

			String licenseType = "";
			List<String> expectLicenseTypeList = new ArrayList<String>();
			expectLicenseTypeList.add("0");
			expectLicenseTypeList.add("0");

			String warnType = "";
			List<String> expectWarnTypeList = new ArrayList<String>();
			expectWarnTypeList.add("101");
			expectWarnTypeList.add("7");

			String warnContent = "";
			List<String> expectWarnContentList = new ArrayList<String>();
			expectWarnContentList.add("《税务登记证》");
			expectWarnContentList.add("《组织机构代码证》");

			String gspTypeName = "";
			List<String> expectGspTypeNameList = new ArrayList<String>();
			expectGspTypeNameList.add("企业资质");
			expectGspTypeNameList.add("企业资质");

			String licenseTypeName = "";
			List<String> expectLicenseTypeNameList = new ArrayList<String>();
			expectLicenseTypeNameList.add("企业资质");
			expectLicenseTypeNameList.add("企业资质");

			String warnTypeName = "";
			List<String> expectWarnTypeNameList = new ArrayList<String>();
			expectWarnTypeNameList.add("null");
			expectWarnTypeNameList.add("《组织机构代码证》预警");

			//循环取值，并在循环中断言
			for(int i = 0; i < 2/*gspLicenseListArray.size()*/; i++){
				licenseId = gspLicenseListArray.getJSONObject(i).getString("licenseId");
				String expectLicenseId = expectLicenseIdList.get(i);
				System.out.println(i+1 + ": liceseId = " + expectLicenseId);
				Assert.assertEquals(licenseId, expectLicenseId, "Wrong licenseId");		

				gspType = gspLicenseListArray.getJSONObject(i).getString("gspType");
				String expectGspType = expectGspTypeList.get(i);
				System.out.println(i+1 + ": gspType = " + gspType);
				Assert.assertEquals(gspType, expectGspType, "Wrong gspType");		

				licenseType = gspLicenseListArray.getJSONObject(i).getString("licenseType");
				String expectLicenseType = expectLicenseTypeList.get(i);
				System.out.println(i+1 + ": licenseType = " + licenseType);
				Assert.assertEquals(licenseType, expectLicenseType, "Wrong licenseType");		

				warnType = gspLicenseListArray.getJSONObject(i).getString("warnType");
				String expectWarnType = expectWarnTypeList.get(i);
				System.out.println(i+1 + ": warnType = " + warnType);
				Assert.assertEquals(warnType, expectWarnType, "Wrong warnType");		

				warnContent = gspLicenseListArray.getJSONObject(i).getString("warnContent");
				String expectWarnContent = expectWarnContentList.get(i);
				System.out.println(i+1 + ": warnContent = " + warnContent);
				Assert.assertEquals(warnContent, expectWarnContent, "Wrong warnContent");		

				gspTypeName = gspLicenseListArray.getJSONObject(i).getString("gspTypeName");
				String expectGspTypeName = expectGspTypeNameList.get(i);
				System.out.println(i+1 + ": gspTypeName = " + gspTypeName);
				Assert.assertEquals(gspTypeName, expectGspTypeName, "Wrong gspTypeName");		

				licenseTypeName = gspLicenseListArray.getJSONObject(i).getString("licenseTypeName");
				String expectLicenseTypeName = expectLicenseTypeNameList.get(i);
				System.out.println(i+1 + ": licenseTypeName = " + licenseTypeName);
				Assert.assertEquals(licenseTypeName, expectLicenseTypeName, "Wrong licenseTypeName");		

				warnTypeName = gspLicenseListArray.getJSONObject(i).getString("warnTypeName");
				String expectWarnTypeName = expectWarnTypeNameList.get(i);
				System.out.println(i+1 + ": warnTypeName = " + warnTypeName);
				Assert.assertEquals(warnTypeName, expectWarnTypeName, "Wrong warnTypeName");
			}
		}

	}

	@Test
	public void licenseTypeTwo() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行gspType=2");

		String testUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getGspLicenseList?warnGspType=2"; 
		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println(result);

		//解析出JSON
		JSONObject jsonObject = JSONObject.fromObject(result);	
		JSONArray gspLicenseListArray;
		gspLicenseListArray = jsonObject.getJSONArray("gspLicenseList");
		System.out.println("gspLicenseList - " + gspLicenseListArray);

		//判断JSON是否为空
		if(gspLicenseListArray==null||gspLicenseListArray.size()==0){
			Assert.assertFalse(gspLicenseListArray==null||gspLicenseListArray.size()==0, "返回数据为空");
		}

		{
			String licenseId = "";
			List<String> expectLicenseIdList = new ArrayList<String>();
			expectLicenseIdList.add("2018");
			expectLicenseIdList.add("2019");

			String gspType = "";
			List<String> expectGspTypeList = new ArrayList<String>();
			expectGspTypeList.add("1");
			expectGspTypeList.add("1");

			String licenseType = "";
			List<String> expectLicenseTypeList = new ArrayList<String>();
			expectLicenseTypeList.add("0");
			expectLicenseTypeList.add("0");

			String warnType = "";
			List<String> expectWarnTypeList = new ArrayList<String>();
			expectWarnTypeList.add("105");
			expectWarnTypeList.add("106");

			String warnContent = "";
			List<String> expectWarnContentList = new ArrayList<String>();
			expectWarnContentList.add("《生产企业法人营业执照》");
			expectWarnContentList.add("《药品生产许可证》");

			String gspTypeName = "";
			List<String> expectGspTypeNameList = new ArrayList<String>();
			expectGspTypeNameList.add("商品资质");
			expectGspTypeNameList.add("商品资质");

			String licenseTypeName = "";
			List<String> expectLicenseTypeNameList = new ArrayList<String>();
			expectLicenseTypeNameList.add("企业资质");
			expectLicenseTypeNameList.add("企业资质");

			String warnTypeName = "";
			List<String> expectWarnTypeNameList = new ArrayList<String>();
			expectWarnTypeNameList.add("null");
			expectWarnTypeNameList.add("null");

			//循环取值，并在循环中断言
			for(int i = 0; i < 2/*gspLicenseListArray.size()*/; i++){
				licenseId = gspLicenseListArray.getJSONObject(i).getString("licenseId");
				String expectLicenseId = expectLicenseIdList.get(i);
				System.out.println(i+1 + ": liceseId = " + expectLicenseId);
				Assert.assertEquals(licenseId, expectLicenseId, "Wrong licenseId");		

				gspType = gspLicenseListArray.getJSONObject(i).getString("gspType");
				String expectGspType = expectGspTypeList.get(i);
				System.out.println(i+1 + ": gspType = " + gspType);
				Assert.assertEquals(gspType, expectGspType, "Wrong gspType");		

				licenseType = gspLicenseListArray.getJSONObject(i).getString("licenseType");
				String expectLicenseType = expectLicenseTypeList.get(i);
				System.out.println(i+1 + ": licenseType = " + licenseType);
				Assert.assertEquals(licenseType, expectLicenseType, "Wrong licenseType");		

				warnType = gspLicenseListArray.getJSONObject(i).getString("warnType");
				String expectWarnType = expectWarnTypeList.get(i);
				System.out.println(i+1 + ": warnType = " + warnType);
				Assert.assertEquals(warnType, expectWarnType, "Wrong warnType");		

				warnContent = gspLicenseListArray.getJSONObject(i).getString("warnContent");
				String expectWarnContent = expectWarnContentList.get(i);
				System.out.println(i+1 + ": warnContent = " + warnContent);
				Assert.assertEquals(warnContent, expectWarnContent, "Wrong warnContent");		

				gspTypeName = gspLicenseListArray.getJSONObject(i).getString("gspTypeName");
				String expectGspTypeName = expectGspTypeNameList.get(i);
				System.out.println(i+1 + ": gspTypeName = " + gspTypeName);
				Assert.assertEquals(gspTypeName, expectGspTypeName, "Wrong gspTypeName");		

				licenseTypeName = gspLicenseListArray.getJSONObject(i).getString("licenseTypeName");
				String expectLicenseTypeName = expectLicenseTypeNameList.get(i);
				System.out.println(i+1 + ": licenseTypeName = " + licenseTypeName);
				Assert.assertEquals(licenseTypeName, expectLicenseTypeName, "Wrong licenseTypeName");		

				warnTypeName = gspLicenseListArray.getJSONObject(i).getString("warnTypeName");
				String expectWarnTypeName = expectWarnTypeNameList.get(i);
				System.out.println(i+1 + ": warnTypeName = " + warnTypeName);
				Assert.assertEquals(warnTypeName, expectWarnTypeName, "Wrong warnTypeName");
			}
		}
	}

	@Test
	public void licenseTypeOne() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行gspType=1");

		String testUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getGspLicenseList?warnGspType=1"; 
		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println(result);

		//解析出JSON
		JSONObject jsonObject = JSONObject.fromObject(result);	
		JSONArray gspLicenseListArray;
		gspLicenseListArray = jsonObject.getJSONArray("gspLicenseList");
		System.out.println("gspLicenseList - " + gspLicenseListArray);

		//判断JSON是否为空
		if(gspLicenseListArray==null||gspLicenseListArray.size()==0){
			Assert.assertFalse(gspLicenseListArray==null||gspLicenseListArray.size()==0, "返回数据为空");
		}

		{
			String licenseId = "";
			List<String> expectLicenseIdList = new ArrayList<String>();
			expectLicenseIdList.add("2014");
			expectLicenseIdList.add("1996");

			String gspType = "";
			List<String> expectGspTypeList = new ArrayList<String>();
			expectGspTypeList.add("0");
			expectGspTypeList.add("0");

			String licenseType = "";
			List<String> expectLicenseTypeList = new ArrayList<String>();
			expectLicenseTypeList.add("0");
			expectLicenseTypeList.add("0");

			String warnType = "";
			List<String> expectWarnTypeList = new ArrayList<String>();
			expectWarnTypeList.add("101");
			expectWarnTypeList.add("7");

			String warnContent = "";
			List<String> expectWarnContentList = new ArrayList<String>();
			expectWarnContentList.add("《税务登记证》");
			expectWarnContentList.add("《组织机构代码证》");

			String gspTypeName = "";
			List<String> expectGspTypeNameList = new ArrayList<String>();
			expectGspTypeNameList.add("企业资质");
			expectGspTypeNameList.add("企业资质");

			String licenseTypeName = "";
			List<String> expectLicenseTypeNameList = new ArrayList<String>();
			expectLicenseTypeNameList.add("企业资质");
			expectLicenseTypeNameList.add("企业资质");

			String warnTypeName = "";
			List<String> expectWarnTypeNameList = new ArrayList<String>();
			expectWarnTypeNameList.add("null");
			expectWarnTypeNameList.add("《组织机构代码证》预警");

			//循环取值，并在循环中断言
			for(int i = 0; i < 2/*gspLicenseListArray.size()*/; i++){
				licenseId = gspLicenseListArray.getJSONObject(i).getString("licenseId");
				String expectLicenseId = expectLicenseIdList.get(i);
				System.out.println(i+1 + ": liceseId = " + expectLicenseId);
				Assert.assertEquals(licenseId, expectLicenseId, "Wrong licenseId");		

				gspType = gspLicenseListArray.getJSONObject(i).getString("gspType");
				String expectGspType = expectGspTypeList.get(i);
				System.out.println(i+1 + ": gspType = " + gspType);
				Assert.assertEquals(gspType, expectGspType, "Wrong gspType");		

				licenseType = gspLicenseListArray.getJSONObject(i).getString("licenseType");
				String expectLicenseType = expectLicenseTypeList.get(i);
				System.out.println(i+1 + ": licenseType = " + licenseType);
				Assert.assertEquals(licenseType, expectLicenseType, "Wrong licenseType");		

				warnType = gspLicenseListArray.getJSONObject(i).getString("warnType");
				String expectWarnType = expectWarnTypeList.get(i);
				System.out.println(i+1 + ": warnType = " + warnType);
				Assert.assertEquals(warnType, expectWarnType, "Wrong warnType");		

				warnContent = gspLicenseListArray.getJSONObject(i).getString("warnContent");
				String expectWarnContent = expectWarnContentList.get(i);
				System.out.println(i+1 + ": warnContent = " + warnContent);
				Assert.assertEquals(warnContent, expectWarnContent, "Wrong warnContent");		

				gspTypeName = gspLicenseListArray.getJSONObject(i).getString("gspTypeName");
				String expectGspTypeName = expectGspTypeNameList.get(i);
				System.out.println(i+1 + ": gspTypeName = " + gspTypeName);
				Assert.assertEquals(gspTypeName, expectGspTypeName, "Wrong gspTypeName");		

				licenseTypeName = gspLicenseListArray.getJSONObject(i).getString("licenseTypeName");
				String expectLicenseTypeName = expectLicenseTypeNameList.get(i);
				System.out.println(i+1 + ": licenseTypeName = " + licenseTypeName);
				Assert.assertEquals(licenseTypeName, expectLicenseTypeName, "Wrong licenseTypeName");		

				warnTypeName = gspLicenseListArray.getJSONObject(i).getString("warnTypeName");
				String expectWarnTypeName = expectWarnTypeNameList.get(i);
				System.out.println(i+1 + ": warnTypeName = " + warnTypeName);
				Assert.assertEquals(warnTypeName, expectWarnTypeName, "Wrong warnTypeName");
			}
		}
	}

	@Test
	public void licenseTypeThree() throws ClientProtocolException, URISyntaxException, IOException{
		System.out.println("开始执行gspType=3");

		String testUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getGspLicenseList?warnGspType=3"; 
		String result = HttpGetLogin.httpGetLogin(testUrl, loginUrl, userName, pwd);
		System.out.println(result);

		//解析出JSON
		JSONObject jsonObject = JSONObject.fromObject(result);	
		JSONArray gspLicenseListArray;
		gspLicenseListArray = jsonObject.getJSONArray("gspLicenseList");
		System.out.println("gspLicenseList - " + gspLicenseListArray);

		//判断JSON是否为空
		if(gspLicenseListArray==null||gspLicenseListArray.size()==0){
			Assert.assertFalse(gspLicenseListArray==null||gspLicenseListArray.size()==0, "返回数据为空");
		}

		{
			String licenseId = "";
			List<String> expectLicenseIdList = new ArrayList<String>();
			expectLicenseIdList.add("2028");
			expectLicenseIdList.add("2029");

			String gspType = "";
			List<String> expectGspTypeList = new ArrayList<String>();
			expectGspTypeList.add("2");
			expectGspTypeList.add("2");

			String licenseType = "";
			List<String> expectLicenseTypeList = new ArrayList<String>();
			expectLicenseTypeList.add("0");
			expectLicenseTypeList.add("1");

			String warnType = "";
			List<String> expectWarnTypeList = new ArrayList<String>();
			expectWarnTypeList.add("115");
			expectWarnTypeList.add("116");

			String warnContent = "";
			List<String> expectWarnContentList = new ArrayList<String>();
			expectWarnContentList.add("《中药调剂员》");
			expectWarnContentList.add("《执业药师》");

			String gspTypeName = "";
			List<String> expectGspTypeNameList = new ArrayList<String>();
			expectGspTypeNameList.add("员工资质");
			expectGspTypeNameList.add("员工资质");

			String licenseTypeName = "";
			List<String> expectLicenseTypeNameList = new ArrayList<String>();
			expectLicenseTypeNameList.add("职称证");
			expectLicenseTypeNameList.add("资格证");

			String warnTypeName = "";
			List<String> expectWarnTypeNameList = new ArrayList<String>();
			expectWarnTypeNameList.add("null");
			expectWarnTypeNameList.add("null");

			//循环取值，并在循环中断言
			for(int i = 0; i < 2/*gspLicenseListArray.size()*/; i++){
				licenseId = gspLicenseListArray.getJSONObject(i).getString("licenseId");
				String expectLicenseId = expectLicenseIdList.get(i);
				System.out.println(i+1 + ": liceseId = " + expectLicenseId);
				Assert.assertEquals(licenseId, expectLicenseId, "Wrong licenseId");		

				gspType = gspLicenseListArray.getJSONObject(i).getString("gspType");
				String expectGspType = expectGspTypeList.get(i);
				System.out.println(i+1 + ": gspType = " + gspType);
				Assert.assertEquals(gspType, expectGspType, "Wrong gspType");		

				licenseType = gspLicenseListArray.getJSONObject(i).getString("licenseType");
				String expectLicenseType = expectLicenseTypeList.get(i);
				System.out.println(i+1 + ": licenseType = " + licenseType);
				Assert.assertEquals(licenseType, expectLicenseType, "Wrong licenseType");		

				warnType = gspLicenseListArray.getJSONObject(i).getString("warnType");
				String expectWarnType = expectWarnTypeList.get(i);
				System.out.println(i+1 + ": warnType = " + warnType);
				Assert.assertEquals(warnType, expectWarnType, "Wrong warnType");		

				warnContent = gspLicenseListArray.getJSONObject(i).getString("warnContent");
				String expectWarnContent = expectWarnContentList.get(i);
				System.out.println(i+1 + ": warnContent = " + warnContent);
				Assert.assertEquals(warnContent, expectWarnContent, "Wrong warnContent");		

				gspTypeName = gspLicenseListArray.getJSONObject(i).getString("gspTypeName");
				String expectGspTypeName = expectGspTypeNameList.get(i);
				System.out.println(i+1 + ": gspTypeName = " + gspTypeName);
				Assert.assertEquals(gspTypeName, expectGspTypeName, "Wrong gspTypeName");		

				licenseTypeName = gspLicenseListArray.getJSONObject(i).getString("licenseTypeName");
				String expectLicenseTypeName = expectLicenseTypeNameList.get(i);
				System.out.println(i+1 + ": licenseTypeName = " + licenseTypeName);
				Assert.assertEquals(licenseTypeName, expectLicenseTypeName, "Wrong licenseTypeName");		

				warnTypeName = gspLicenseListArray.getJSONObject(i).getString("warnTypeName");
				String expectWarnTypeName = expectWarnTypeNameList.get(i);
				System.out.println(i+1 + ": warnTypeName = " + warnTypeName);
				Assert.assertEquals(warnTypeName, expectWarnTypeName, "Wrong warnTypeName");
			}
		}
	}
}
