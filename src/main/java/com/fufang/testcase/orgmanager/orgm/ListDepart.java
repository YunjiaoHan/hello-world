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

public class ListDepart {

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
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest01&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
	}

	@Test
	public void listDepart() throws ClientProtocolException, IOException, URISyntaxException{
		System.out.println("------start lisPosition------");
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/listDepart.do";
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
					JSONArray rowsArray = jsonObject.getJSONArray("rows");

					int pid = rowsArray.getJSONObject(0).getInt("pid");
					String name = rowsArray.getJSONObject(0).getString("name");

					Assert.assertEquals(pid, 200362);
					Assert.assertEquals(name, "hyj测试总店01");

					JSONObject rows1 = rowsArray.getJSONObject(0); 
					JSONArray ownerArray = rows1.getJSONArray("owner");

					String ownerName = "";
					List<String> expectNameList = new ArrayList<String>();
					expectNameList.add("质量管理");
					expectNameList.add("采购");
					expectNameList.add("储存");

					int id;
					List<Integer> expectIdList = new ArrayList<Integer>();
					expectIdList.add(23605);
					expectIdList.add(23606);
					expectIdList.add(23607);

					for(int i = 0; i < 3; i++){
						id = ownerArray.getJSONObject(i).getInt("id");
						System.out.println(id);
						int expectId = expectIdList.get(i);
						Assert.assertEquals(id, expectId, "wrongId");

						ownerName = ownerArray.getJSONObject(i).getString("name");
						System.out.println(ownerName);
						String expectOwnerName = expectNameList.get(i);
						Assert.assertEquals(ownerName, expectOwnerName, "wrong ownerName");
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


