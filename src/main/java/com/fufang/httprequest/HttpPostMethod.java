package com.fufang.httprequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpPostMethod {

	static CloseableHttpClient httpClient = HttpClients.custom().setConnectionManager(null).setConnectionManagerShared(true).build();
	
	public static String httpPost(String url , List <NameValuePair> nvps) throws IOException{

		HttpPost httpPost = new HttpPost(url);
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

	public static void main(String[] args) throws IOException{

		String url = "http://172.16.88.183:9999/organization-manager/orgManager/postPosition.do";  
		List <NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair("positionStr", "{\"position\":{\"name\":\"fsdada\",\"status\":0,\"code\":\"fsafdasd\",\"remark\":\"测试备注\"}}")); 

		try {
			String result = HttpPostMethod.httpPost(url, nvps);
			System.out.println("response content is "+ result);
			//System.out.println("content decode is "+ StringEscapeUtils.unescapeJava(content));
		} catch (UnsupportedEncodingException e) {
			//TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

