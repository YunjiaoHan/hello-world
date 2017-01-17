package com.fufang.testcase.orgmanager.maindata;

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

public class ListRate{

	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		String url = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
		System.out.println("--------before test---------");	
	}

	@Test
	public void listRate() throws ClientProtocolException, IOException, URISyntaxException{
		System.out.println("------start run listRate------");
		
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.195:9999/organization-manager/mainData/listRate.do";
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
					//System.out.println(result);  

					JSONObject jsonObject = JSONObject.fromObject(result);
					JSONArray dataArray = jsonObject.getJSONArray("data");
					//System.out.println(dataArray);
					
					String rate = dataArray.getJSONObject(0).toString();
					System.out.println(rate);
					
					Assert.assertEquals(rate, "{\"updateUserName\":null,\"id\":849,\"updateUserId\":null,\"rateType\":0,\"createUserName\":\"hyjtest\",\"taxRate\":0.0077,\"status\":1,\"pharmacyId\":200259,\"createDate\":1480917757697,\"updateDate\":null,\"createUserId\":32814962}");

				/*	for(int i = 0; i < dataArray.size(); i++){
						String createUserName = dataArray.getJSONObject(i).getString("createUserName");
						int rateType = dataArray.getJSONObject(i).getInt("rateType");
						double taxRate = dataArray.getJSONObject(i).getDouble("taxRate");
						System.out.println(taxRate);
						int status = dataArray.getJSONObject(i).getInt("status");
						
						Assert.assertEquals(createUserName, "hyjtest","wrong user");
						Assert.assertEquals(rateType, 0, "wrong rateType");
						Assert.assertEquals(taxRate, 0.0077, "wrong taxRate");
						Assert.assertEquals(status, 1, "wrong status");
					}*/
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
