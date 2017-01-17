package com.fufang.httprequest;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpPostLogin {

	static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();

	public static String httpPost(String testUrl , List <NameValuePair> nvps,String loginUrl, String userName,String pwd) throws IOException, URISyntaxException{
		String cookie = HttpCookie2.httpGetCookie(loginUrl, userName, pwd);
		HttpPost httpPost = new HttpPost(testUrl);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
		httpPost.setConfig(requestConfig);
		httpPost.addHeader("cookie", cookie);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps));

		try {
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity = response.getEntity();
			int	code = response.getStatusLine().getStatusCode();
			return 200==code?EntityUtils.toString(entity):"Error:"+code;
			//return 200==code?response.getStatusLine().toString():"Error:"+code;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			httpClient.close();
		}
		return null;
	}

	/*public static void main(String[] args) throws IOException, URISyntaxException{

		String userName = "hyjtest";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		String testUrl = "http://172.16.88.183:9999/organization-manager/orgManager/getPinyin.do";  
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("name", "顺丰速递")); 

		String result = HttpPostLogin.httpPost(testUrl, nvps, loginUrl, userName, pwd);
		System.out.println("response content is "+ result);
		//System.out.println("content decode is "+ StringEscapeUtils.unescapeJava(content));
	}*/
}

