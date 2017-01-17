package com.fufang.testcase.his;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;

import net.sf.json.JSONObject;

public class AgreeReturnMat {
	
	String url = "http://ct.fu-fang.com:8001/ffcloud-hisFc/agreeReturnMat";	

	@Test
	public void returenMat() throws UnsupportedEncodingException, ClientProtocolException, IOException{

		JSONObject paramsObject = new JSONObject();
		paramsObject.put("departCode", "J3000");
		paramsObject.put("diagnosisId", "64748598");
		paramsObject.put("prescriptionCode", "CF2555548");

		String params = paramsObject.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String data = jsonObject.getString("data");

		Assert.assertEquals(code, "000000");
		Assert.assertEquals(data, "退药申请上传成功！");
	}

	@Test
	public void invalidParams() throws UnsupportedEncodingException, ClientProtocolException, IOException{

		JSONObject paramsObject = new JSONObject();
		paramsObject.put("departCode", "");
		paramsObject.put("diagnosisId", "64748598");
		paramsObject.put("prescriptionCode", "CF2555548");

		String params = paramsObject.toString();
		JsonUtils.printJson(params);

		String result = HttpUtils.httpPostJson(url, params);

		JSONObject jsonObject = JSONObject.fromObject(result);
		String code = jsonObject.getString("code");
		String message = jsonObject.getString("message");

		Assert.assertEquals(code, "100000");
		boolean messageMatch = message.contains("退药申请上传失败,输入参数不完整");
		Assert.assertTrue(messageMatch, "message不匹配");
	}
}
