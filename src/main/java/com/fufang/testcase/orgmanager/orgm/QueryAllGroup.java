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

public class QueryAllGroup {
	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest01&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
		System.out.println("--------before test---------");	
	}

	@Test
	public void queryAllGroup() throws ClientProtocolException, IOException, URISyntaxException{
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/queryAllGroup";
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
					//System.out.println(listArray);

					String pharmacyId = listArray.getJSONObject(0).getString("pharmacyId");
					String orgName = listArray.getJSONObject(0).getString("orgName");
					Assert.assertEquals(pharmacyId, "200362");
					Assert.assertEquals(orgName, "总部");
					
					String fOrgName = listArray.getJSONObject(1).getString("orgName");
					System.out.println(fOrgName);			
					Assert.assertEquals(fOrgName, "分店");
					
					JSONArray childrenArray = listArray.getJSONObject(1).getJSONArray("children");

					String childOrgName = "";
					List <String> childOrgNameList = new ArrayList<String>();
					childOrgNameList.add("自营");
					childOrgNameList.add("加盟");
					
					String orgCode = "";
					List<String> orgCodeList = new ArrayList<String>();
					orgCodeList.add("2001");
					orgCodeList.add("2002");
					
					for(int i = 0; i < 2; i++){
						childOrgName = childrenArray.getJSONObject(i).getString("orgName");
						orgCode = childrenArray.getJSONObject(i).getString("orgCode");
						String expectChildOrgName = childOrgNameList.get(i);
						String expectOrgCode = orgCodeList.get(i);
						
						Assert.assertEquals(childOrgName, expectChildOrgName, "wrong childOrgName");
						Assert.assertEquals(orgCode, expectOrgCode, "wrong childOrgCode");
					}
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
