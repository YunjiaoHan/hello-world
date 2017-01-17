package com.fufang.testcase.his;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetFitPharmacy {
	
	final static String url = "http://ct.fu-fang.com:8001/ffcloud-hisFc/getFitPharmacy";
	
	@Test
	public void getPharmacy() throws UnsupportedEncodingException, ClientProtocolException, IOException{
		
		Map<String, Integer> materials = new HashMap<String, Integer>();
		materials.put("amount", 1);
		materials.put("ffId", 215934);
		JSONArray materialsArray = JSONArray.fromObject(materials);	
		
		JSONObject paramsObject = new JSONObject();
		paramsObject.put("diagnosisId", "123456");
		paramsObject.put("hisCode", "mtg201");
		paramsObject.put("materials", materialsArray);
		
		String params = paramsObject.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String data = jsonObject.getString("data");
		
		Assert.assertEquals(code, "000000");
		Assert.assertEquals(data, "{\"materials\":[{\"pharmacyId\":\"200410\",\"matInfo\":[{\"amount\":2,\"price\":5,\"ffId\":215934,\"contentsItemId\":\"1683364d-a973-4eb2-94fa-40028665e39e\"}],\"pharmacyName\":\"001\"}],\"maxPrescriptionCode\":\"1101090031612230001\"}");			
	}
	
	@Test
	public void invalidParams() throws UnsupportedEncodingException, ClientProtocolException, IOException{
		
		Map<String, Integer> materials = new HashMap<String, Integer>();
		materials.put("amount", 1);
		materials.put("ffId", 21412);
		JSONArray materialsArray = JSONArray.fromObject(materials);	
		
		JSONObject paramsObject = new JSONObject();
		paramsObject.put("diagnosisId", "123456");
		paramsObject.put("hisCode", "mtg201");
		paramsObject.put("materials", materialsArray);
		
		String params = paramsObject.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);
		
		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String message = jsonObject.getString("message");
		
		Assert.assertEquals(code, "100000");
	    boolean messageMatch = message.contains("查询药店实时库存异常,错误信息");
	    Assert.assertEquals(messageMatch, true);
	}
}
