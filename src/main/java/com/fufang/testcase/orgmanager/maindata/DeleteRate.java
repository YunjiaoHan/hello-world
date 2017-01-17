package com.fufang.testcase.orgmanager.maindata;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpCookie;

import net.sf.json.JSONObject;

public class DeleteRate{

	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		System.out.println("------before test------");	
		String url = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
	}

	@Test
	public void deleteRate() throws IOException, URISyntaxException{
		System.out.println("------start deleteRate------");
		int id;
		String cookie = getCookie();
		String url = "http://172.16.88.195:9999/organization-manager/mainData/postRate.do";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("rateStr","{\"rate\":{\"rateType\":0,\"taxRate\":234,\"status\":1}}"));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("cookie", cookie);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps)); 

		try {  
			HttpResponse postResponse = httpClient.execute(httpPost);

			if(postResponse.getStatusLine().getStatusCode() == 200){  
				HttpEntity postEntity = postResponse.getEntity();  
				String postResult = EntityUtils.toString(postEntity);
				System.out.println(postResult); 

				JSONObject jsonObject = JSONObject.fromObject(postResult);
				id = jsonObject.getInt("id");
				System.out.println("新增id = " + id);

				String deleteUrl = "http://172.16.88.195:9999/organization-manager/mainData/deleteRate.do?id="+id;
				System.out.println("删除接口地址 = " + deleteUrl);

				HttpGet httpGet = new HttpGet(deleteUrl);
				httpGet.addHeader("cookie", cookie);
				try{
					HttpResponse getResponse = httpClient.execute(httpGet);
					int status = getResponse.getStatusLine().getStatusCode();
					Assert.assertEquals(status, 200);
					
					String getResult = EntityUtils.toString(getResponse.getEntity());
					System.out.println("删除接口返回 = " + getResult);

					JSONObject deleteObject = JSONObject.fromObject(getResult);
					String msg = deleteObject.getString("msg");
					
					Assert.assertEquals(msg, "success");

				}catch (ClientProtocolException e) {  
					// TODO Auto-generated catch block  
					e.printStackTrace();  
					e.getMessage().toString();  
				}catch (IOException e) {  
					// TODO Auto-generated catch block  
					e.printStackTrace();  
					e.getMessage().toString(); 
				}
			}
			else{
				System.out.println("status code = " + postResponse.getStatusLine().getStatusCode());
			}
		} catch (UnsupportedEncodingException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}  
		catch (ClientProtocolException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}  
		catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}finally{
			httpClient.close();
		}
	}
}