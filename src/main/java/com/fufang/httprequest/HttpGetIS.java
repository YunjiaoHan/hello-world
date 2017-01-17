package com.fufang.httprequest;

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

public class HttpGetIS{

	//final static String url = "http://172.16.86.60:8080/api.search/ep?q=名称";

	public static String httpGetIS(String url) throws URISyntaxException, ClientProtocolException, IOException{  
		CloseableHttpClient httpClient = HttpClients.createDefault();

		CloseableHttpResponse response = null;
		InputStream is = null;  
		
		try{
			HttpGet httpGet = new HttpGet(url);
			response = httpClient.execute(httpGet);   
			HttpEntity entity = response.getEntity(); 
			if(entity != null){  
				is = entity.getContent();  
				//转换为字节输入流  
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));  
				String result = null;  
				while((result = br.readLine()) != null){  
					System.out.println(result);  
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
		return null;
	} 
    
	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {  
		String url = "http://172.16.86.60:8080/api.search/ep?q=名称";
		httpGetIS(url);  
    }
}



