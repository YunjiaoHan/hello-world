package com.fufang.httprequest;


import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpGetUtils{
	
	static CloseableHttpClient  httpclient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();

	public static String httpGetUtils(String url) throws ClientProtocolException, IOException{
		try {
			HttpGet httpget = new HttpGet(url);
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
			System.out.println(responseBody);
			return responseBody;
		} 
		finally {
		   httpclient.close();
		}
		
	}
	public static void main(String[] args) throws ClientProtocolException, URISyntaxException, IOException {  
		String url = "http://172.16.86.60:8080/api.search/ep?q=名称";
		HttpGetUtils.httpGetUtils(url);  
	}

}











