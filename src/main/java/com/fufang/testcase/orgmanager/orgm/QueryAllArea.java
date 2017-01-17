package com.fufang.testcase.orgmanager.orgm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;

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

public class QueryAllArea {

	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
		System.out.println("--------before test---------");	
	}

	@Test
	public void queryAllArea() throws ClientProtocolException, IOException, URISyntaxException{
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/queryAllArea";
		try{
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("cookie", cookie);
			response = httpClient.execute(httpGet);  
			HttpEntity entity = response.getEntity(); 
			if(entity != null){  
				is = entity.getContent();  
				//转换为字节输入流  
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));  
				String result = null;  
				while((result=br.readLine()) != null){  
					System.out.println(result); 

					JSONObject jsonObject = JSONObject.fromObject(result);
					JSONArray listArray = jsonObject.getJSONArray("list");
					System.out.println(listArray);

					JSONObject jsonList1 = listArray.getJSONObject(0);

					String areaCode = jsonList1.getString("areaCode");
					String areaName = jsonList1.getString("areaName");
					String parentCode = jsonList1.getString("parentCode");
					//String parentName = jsonList1.getString("parentName");

					Assert.assertEquals(areaCode, "100");
					Assert.assertEquals(areaName, "中国");
					Assert.assertEquals(parentCode, "100");
					//Assert.assertEquals(parentName, "");
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
