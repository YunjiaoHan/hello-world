package com.fufang.testcase.orgmanager.orgm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpCookie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ToSavePage {
	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		System.out.println("--------before test---------");
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
	}

	@Test
	public void toSavePage() throws ClientProtocolException, IOException, URISyntaxException{
		System.out.println("------start run toSavePage------");

		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/toSavePage";

		HttpGet httpGet = new HttpGet(url);
		httpGet.addHeader("cookie", cookie);

		try{
			response = httpClient.execute(httpGet); 
			int status = response.getStatusLine().getStatusCode();
			Assert.assertEquals(status, 200);

			HttpEntity entity = response.getEntity(); 
			if(entity != null){  
				is = entity.getContent();  
				//转换为字节输入流  
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));  
				String result = null;  
				while((result=br.readLine()) != null){  
					System.out.println(result);  

					JSONObject jsonObject = JSONObject.fromObject(result);

					JSONArray cityArray = jsonObject.getJSONArray("cities");
					String city1 = cityArray.getJSONArray(0).toString();
					System.out.println("city1 - " + city1);
					Assert.assertEquals(city1, "[110100,\"市辖区\",110000]");

					JSONArray allRangesArray = jsonObject.getJSONArray("allRanges");
					String allRanges = allRangesArray.toString();
					System.out.println("allRanges - " + allRanges);
					Assert.assertEquals(allRanges, "[\"中药材\",\"中成药\",\"中药饮片\",\"化学原料药\",\"化学药制剂\",\"抗生素\",\"生化药品\",\"放射性药品\",\"生物制品\",\"血液制品\",\"诊断药品\",\"其他\",\"医疗器械\"]");

					JSONArray areaListArray = jsonObject.getJSONArray("areaList");

					for(int i = 0; i < areaListArray.size(); i++){
						//JSONArray childrenArray = areaListArray.getJSONObject(i).getJSONArray("children");
						String areaCode = areaListArray.getJSONObject(i).getString("areaCode");
						String areaName = areaListArray.getJSONObject(i).getString("areaName");
						System.out.println("areaList - " + areaCode + "-" + areaName);

						Assert.assertEquals(areaCode, "100");
						Assert.assertEquals(areaName, "中国");
					}

					JSONArray inputTaxArray = jsonObject.getJSONArray("inputTax");

					List<Integer> rateTypeList = new ArrayList<Integer>();
					rateTypeList.add(0);
					rateTypeList.add(0);

					List<Double> taxRateList = new ArrayList<Double>();
					taxRateList.add(0.77);
					taxRateList.add((double) 18);

					for(int i = 0; i < inputTaxArray.size(); i++){
						int rateType = inputTaxArray.getJSONObject(i).getInt("rateType");
						double taxRate = inputTaxArray.getJSONObject(i).getDouble("taxRate");
						System.out.println("rateType - " + rateType + "  taxRate - " + taxRate);
						int expectRateType = rateTypeList.get(i);
						double expectTaxRate = taxRateList.get(i);

						Assert.assertEquals(rateType, expectRateType);
						Assert.assertEquals(taxRate, expectTaxRate);
					}

					JSONArray provinceArray = jsonObject.getJSONArray("provinces");
					System.out.println("provinceArray - " + provinceArray);

					String province1 = provinceArray.getJSONArray(0).toString();
					String province2 = provinceArray.getJSONArray(1).toString();
					String province3 = provinceArray.getJSONArray(2).toString();

					Assert.assertEquals(province1, "[110000,\"北京市\",0]");
					Assert.assertEquals(province2, "[120000,\"天津市\",0]");
					Assert.assertEquals(province3, "[130000,\"河北省\",0]");
				}
			}        
		}finally{  
			//关闭输入流，释放资源  
			if(is != null){  
				try {  
					is.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
			//消耗实体内容  
			if(response != null){  
				try {  
					response.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
			//关闭相应 丢弃http连接  
			if(httpClient != null){  
				try {  
					httpClient.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}  
	}  
}
