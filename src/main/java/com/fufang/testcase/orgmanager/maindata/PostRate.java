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

public class PostRate{

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
	public void postRate() throws IOException{
		System.out.println("--------start run postRate------");
		
		int id;
		String cookie = getCookie();
		String url = "http://172.16.88.195:9999/organization-manager/mainData/postRate.do";
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("rateStr","{\"rate\":{\"rateType\":0,\"taxRate\":244,\"status\":1}}"));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("cookie", cookie);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps)); 

		try {  
			HttpResponse postResponse = httpClient.execute(httpPost);

			if(postResponse.getStatusLine().getStatusCode() == 200){  
				HttpEntity postEntity = postResponse.getEntity();  
				String postResult = EntityUtils.toString(postEntity);
				System.out.println("postRate - " + postResult); 

				JSONObject jsonObject = JSONObject.fromObject(postResult);
				id = jsonObject.getInt("id");

				String deleteUrl = "http://172.16.88.195:9999/organization-manager/mainData/deleteRate.do?id="+id;

				HttpGet httpGet = new HttpGet(deleteUrl);
				httpGet.addHeader("cookie", cookie);
				try{
					HttpResponse getResponse = httpClient.execute(httpGet);
					int status = getResponse.getStatusLine().getStatusCode();
					Assert.assertEquals(status, 200);
					
					String getResult = EntityUtils.toString(getResponse.getEntity());
					System.out.println("deleteRate - " + getResult);

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

	@Test
	public void postRateRepeat() throws IOException{
		System.out.println("------start run postRateRepeat------");

		String cookie = getCookie();
		String url = "http://172.16.88.195:9999/organization-manager/mainData/postRate.do";  
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("rateStr","{\"rate\":{\"rateType\":0,\"taxRate\":0.77,\"status\":1}}"));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		httpPost.addHeader("cookie", cookie);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps)); 

		try {  
			HttpResponse httpResponse = httpClient.execute(httpPost); 
			int status = httpResponse.getStatusLine().getStatusCode();
			Assert.assertEquals(status, 200);
			if(httpResponse.getStatusLine().getStatusCode() == 200)  
			{  
				HttpEntity httpEntity = httpResponse.getEntity();  
				String result = EntityUtils.toString(httpEntity);
				System.out.println("postRateRepeat - " + result); 

				JSONObject jsonObject = JSONObject.fromObject(result);
				String msg = jsonObject.getString("msg");

				Assert.assertEquals(msg, "repeat");
			}
			else{
				System.out.println("status code = " + httpResponse.getStatusLine().getStatusCode());
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
