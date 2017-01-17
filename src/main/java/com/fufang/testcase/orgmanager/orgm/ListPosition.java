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

public class ListPosition {

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
	public void listPosition() throws ClientProtocolException, IOException, URISyntaxException{
		System.out.println("------start lisPosition------");
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/listPosition.do";
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
					JSONArray rowsArray = jsonObject.getJSONArray("rows");
					System.out.println(rowsArray);
					int num = rowsArray.size();
					System.out.println(num);

					Assert.assertEquals(num, 18);

					{
						String id= "";
						List<String> expectIdList = new ArrayList<String>();
						expectIdList.add("15290");
						expectIdList.add("15291");
						expectIdList.add("15292");

						for(int i = 0; i<3; i++){
							id = rowsArray.getJSONObject(i).getString("id");
							String expectId = expectIdList.get(i);
							System.out.println(i+1 + ": id - " + id);
							Assert.assertEquals(id, expectId, "wrong id");
						}
					}

					{
						String name = "";
						List<String> expectNameList = new ArrayList<String>();
						expectNameList.add("企业负责人");
						expectNameList.add("质量负责人");
						expectNameList.add("质量管理部门负责人");

						for(int i = 0; i<3; i++){
							name = rowsArray.getJSONObject(i).getString("name");
							String expectName = expectNameList.get(i);
							System.out.println(i+1 + ": name - " + name);
							Assert.assertEquals(name, expectName, "wrong name");
						}
					}

					{
						String sys = "";
						List<String> expectSysList = new ArrayList<String>();
						expectSysList.add("1");
						expectSysList.add("1");
						expectSysList.add("1");

						for(int i = 0; i<3; i++){
							sys = rowsArray.getJSONObject(i).getString("sys");
							String expectSys = expectSysList.get(i);
							System.out.println(i+1 + ": sys - " + sys);
							Assert.assertEquals(sys, expectSys, "wrong sys");
						}
					}

					for(int i = 0 ; i < rowsArray.size(); i++){
						String pharmacyId = rowsArray.getJSONObject(i).getString("pharmacyId");
						//System.out.println(pharmacyId);
						Assert.assertEquals(pharmacyId, "200259");

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


