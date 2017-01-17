package com.fufang.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpUtils {

	static CloseableHttpClient  httpClient = HttpClients.createDefault();
	static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();

	public static String httpGet(String url) throws ClientProtocolException, IOException{
		try {
			HttpGet httpGet = new HttpGet(url);
			httpGet.setConfig(requestConfig);

			System.out.println("Executing request " + httpGet.getRequestLine());
			// Create a custom response handler
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(
						final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responseBody = httpClient.execute(httpGet, responseHandler);
			System.out.println("ResponseBody - " + responseBody);
			return responseBody;
		} 
		finally {
			httpClient.close();
		}
	}


	public static String httpPostJson(String url, String params) throws UnsupportedEncodingException, ClientProtocolException, IOException{

		CloseableHttpClient  httpClient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();
		
		try {
			HttpPost httpPost = new HttpPost(url);
			System.out.println("正在执行 request " + httpPost.getRequestLine());			
			httpPost.setConfig(requestConfig);

			StringEntity entity = new StringEntity(params.toString(),"UTF-8");
			entity.setContentType("application/json");
			httpPost.setEntity(entity);

			ResponseHandler<String> responseHandler = new ResponseHandler<String>(){
				public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if(status >= 200 && status < 300){
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					}else{
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responseBody = httpClient.execute(httpPost, responseHandler);
			System.out.println("ResponseBody - " + responseBody);
			return responseBody;
		}finally{
			httpClient.close();
		}
	}

	public static String httpGetLogin(String TestUrl, String loginUrl, String userName, String pwd) throws ClientProtocolException, IOException, URISyntaxException{

		CloseableHttpClient  httpclient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();
		String cookie = CookieUtils.getCookie(loginUrl, userName, pwd);

		try {
			HttpGet httpget = new HttpGet(TestUrl);
			httpget.setConfig(requestConfig);
			httpget.addHeader("cookie",cookie);
			System.out.println("Executing request " + httpget.getRequestLine());
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responseBody = httpclient.execute(httpget, responseHandler);
			//System.out.println(responseBody);
			return responseBody;
		} 
		finally {
			httpclient.close();
		}
	}

	public static String httpPostLogin(String testUrl , List <NameValuePair> nvps,String loginUrl, String userName,String pwd) throws IOException, URISyntaxException{

		CloseableHttpClient  httpClient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();
		String cookie = CookieUtils.getCookie(loginUrl, userName, pwd);

		try {
			HttpPost httpPost = new HttpPost(testUrl);
			httpPost.setConfig(requestConfig);
			httpPost.addHeader("cookie", cookie);
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			System.out.println("Executing request " + httpPost.getRequestLine());
			ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
				public String handleResponse(final HttpResponse response) throws ClientProtocolException, IOException {
					int status = response.getStatusLine().getStatusCode();
					if (status >= 200 && status < 300) {
						HttpEntity entity = response.getEntity();
						return entity != null ? EntityUtils.toString(entity) : null;
					} else {
						throw new ClientProtocolException("Unexpected response status: " + status);
					}
				}
			};
			String responseBody = httpClient.execute(httpPost, responseHandler);
			//System.out.println(responseBody);
			return responseBody;
		}finally{
			httpClient.close();
		}
	}

	/*public static void main(String[] args) throws IOException, URISyntaxException{

		String userName = "hyjtest";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		String testUrl = "http://172.16.88.183:9999/organization-manager/orgManager/getPinyin.do";  
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("name", "顺丰速递")); 

		String result = HttpUtils.httpPostLogin(testUrl, nvps, loginUrl, userName, pwd);
		System.out.println("response content is "+ result);
		//System.out.println("content decode is "+ StringEscapeUtils.unescapeJava(content));
	}*/
}