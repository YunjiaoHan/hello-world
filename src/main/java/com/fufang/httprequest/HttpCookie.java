package com.fufang.httprequest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class HttpCookie {

	//final static String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";

	public static String getCookie(String url) throws URISyntaxException, ClientProtocolException, IOException{  
		//url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = "";
		BasicCookieStore cookieStore = new BasicCookieStore();	
		CloseableHttpClient httpClient = HttpClients.custom().setDefaultCookieStore(cookieStore).build();
		CloseableHttpResponse response = null;

		InputStream is = null;  

		try{
			HttpGet httpGet = new HttpGet(url);
			RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();//设置请求和传输超时时间
			httpGet.setConfig(requestConfig);
			
			response = httpClient.execute(httpGet); 

			List<Cookie> cookies = cookieStore.getCookies();
			if (cookies.isEmpty()) {
				System.out.println("None");
			} else {

				for (int i = 0; i < cookies.size(); i++) {
					//System.out.println("- " + cookies.get(i).toString());
					String result = cookies.get(i).toString();
					//System.out.println(result);
					if(i==0){
						cookie = cookie+"JSESSIONID="+result.split(":")[3].split("]")[0]+";";
					}else if(i==1){
						cookie = cookie+"user="+result.split(":")[3].split("]")[0]+";";
					}
				}
				System.out.println(cookie);
				return cookie;
			}

		}finally{   
			if(is != null){  
				try {  
					is.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}   
			if(response != null){   
				try {  
					response.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}   
			if(httpClient != null){    
				try {  
					httpClient.close();  
				} catch (IOException e) {  
					e.printStackTrace();  
				}  
			}  
		}
		return null; 
	}

	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {  
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest01&user.password=1qaz2wsx";
		HttpCookie.getCookie(url);
	}
}

















