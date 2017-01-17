package com.fufang.httprequest;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class HttpPostBuild {  

	public static String postBuildJson(String url, String params) throws UnsupportedEncodingException, ClientProtocolException, IOException{

		CloseableHttpClient httpClient = HttpClients.createDefault();
		String postResult = null;

		HttpPost httpPost = new HttpPost(url);
		System.out.println("正在执行 request " + httpPost.getRequestLine());
		
		RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(10000).setConnectTimeout(10000).build();
		httpPost.setConfig(requestConfig);
		StringEntity entity = new StringEntity(params.toString(),"UTF-8");
		entity.setContentType("application/json");
		httpPost.setEntity(entity);
	
		try {
			HttpResponse response = httpClient.execute(httpPost);
			int	status = response.getStatusLine().getStatusCode();
			if(status >= 200 && status < 300) {
				HttpEntity responseEntity = response.getEntity();
				postResult = EntityUtils.toString(responseEntity);
			}else{
				System.out.println("unexpected response status - " + status);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return postResult;
	}

	/*public static void main(String[] args) throws ClientProtocolException, IOException{
		String url = "http://172.16.87.192:7777/ffcloud-epscm/pullMaterial/saveOrUpdateAll.do";

		Map<String, String> data = new HashMap<String, String>();
		data.put("barcode","药品条码abc");
		data.put("matcode","药品编码abc");
		data.put("batchNum","批号qbc");
		data.put("name","名称");
		data.put("commonName","通用名");
		data.put( "spec","规格");
		data.put("unit","单位");
		data.put("dosage","剂型");
		data.put("licenseNum","国药准字");
		data.put("manufName","厂家");
		data.put("prodPlace","厂址");
		data.put("productDate","2012-12-12");
		data.put("validDate","2013-12-13");
		data.put("retailPrice","12.5");
		data.put("zhongbao","10");
		data.put("createPerson","立龙");
		data.put( "sellState","y");
		data.put("manufNameAbbr","北京");
		data.put("is_kcbxs","是");
		data.put("price","12");
		data.put("storeNum","100");

		JSONArray dataArray = JSONArray.fromObject(data);
		JSONObject params = new JSONObject();
		params.put("key","61300");
		params.put("data",dataArray);
		System.out.println("参数 - " + params);

		HttpPostEP.postJson(url, params);
	}*/
}