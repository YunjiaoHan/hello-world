package com.fufang.httprequest;


import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpGetLogin{
	
	static CloseableHttpClient  httpclient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();

	public static String httpGetLogin(String TestUrl, String loginUrl, String userName, String pwd) throws ClientProtocolException, IOException, URISyntaxException{
		
		String cookie = HttpCookie2.httpGetCookie(loginUrl, userName, pwd);
		HttpGet httpget = new HttpGet(TestUrl);
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
		httpget.setConfig(requestConfig);
		httpget.addHeader("cookie",cookie);
		
		try {
			System.out.println("Executing request " + httpget.getRequestLine());
			// Create a custom response handler	
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
	
	/*public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {  
		String TestUrl = "http://172.16.88.195:9999/organization-manager/employee/init";
		String userName= "hyjtest";
		String pwd = "1qaz2wsx";
		String loginUrl = "http://172.16.88.195:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;
		
		HttpUtilsLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);  
	}*/

}











