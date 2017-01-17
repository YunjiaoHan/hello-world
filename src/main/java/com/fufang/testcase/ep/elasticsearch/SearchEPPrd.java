package com.fufang.testcase.ep.elasticsearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetUtils;

import net.sf.json.*;


public class SearchEPPrd{

	@Test
	public void searchName() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchName------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=大活络丸&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			JSONObject nameObject = JSONObject.fromObject(source);
			String name = nameObject.getString("name");
			System.out.println("name - " + name);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean nameMatch = name.contains("活络");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(nameMatch, "name不匹配");
		}
	}

	@Test
	public void searchLicense() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchLicense------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=国药准字H&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String license = JSONObject.fromObject(source).getString("license");
			System.out.println("license - " + license);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean match = license.contains("国药准字H");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(match, "license不匹配");
		}
	}

	@Test
	public void searchManuf() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchManuf------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=北京华&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String manuf = JSONObject.fromObject(source).getString("manuf");
			System.out.println("manuf - " + manuf);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean match = manuf.contains("北京华");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(match, "manuf不匹配");
		}
	}


	@Test
	public void searchSupplier() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchSupplier------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=凯宏鑫&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String supplier = JSONObject.fromObject(source).getString("supplier");
			System.out.println("supplier - " + supplier);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean match = supplier.contains("凯宏鑫");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(match, "supplier不匹配");
		}
	}


	@Test
	public void searchNameDosage() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchNameSpec------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=头孢%20片&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String name = JSONObject.fromObject(source).getString("name");
			String dosage = JSONObject.fromObject(source).getString("dosage");
			System.out.println("name/dosage - " + name + "/" + dosage);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean matchName = name.contains("头孢");
			boolean matchDosage = dosage.contains("片");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(matchName, "name不匹配");
			Assert.assertTrue(matchDosage, "dosage不匹配");
		}
	}

	@Test
	public void searchLicenseDosage() throws ClientProtocolException, IOException{

		String url = "http://101.201.114.175:8080/api.search/ep?q=国药%20丸&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String license = JSONObject.fromObject(source).getString("license");
			String dosage = JSONObject.fromObject(source).getString("dosage");
			System.out.println("license/dosage - " + license + "/" + dosage);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean matchLicense = license.contains("国药");
			boolean matchDosage = dosage.contains("丸");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(matchLicense, "license不匹配");
			Assert.assertTrue(matchDosage, "dosage不匹配");
		}
	}

	@Test
	public void searchNameManuf() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchNameManuf------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=片%20山东&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String name = JSONObject.fromObject(source).getString("name");
			String manuf = JSONObject.fromObject(source).getString("manuf");
			System.out.println("name/manuf - " + name +"/" + manuf);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean nameMatch = name.contains("片");
			boolean manufMatch = manuf.contains("山东");		
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(nameMatch, "name不匹配");
			Assert.assertTrue(manufMatch, "manuf不匹配");
		}
	}

	@Test
	public void searchLicenseManufDosage() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchLicenseManufDosage------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=国药%20山东%20片&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			String index = hitsArray.getJSONObject(i).getString("_index");
			//System.out.println("index - " + index);

			double score = hitsArray.getJSONObject(i).getDouble("_score");
			//System.out.println("score - " + score);

			String source = hitsArray.getJSONObject(i).getString("_source");
			//System.out.println("source = " + source);
			String license = JSONObject.fromObject(source).getString("license");
			String manuf = JSONObject.fromObject(source).getString("manuf");
			String dosage = JSONObject.fromObject(source).getString("dosage");
			System.out.println("license/manuf/dosage - " + license +"/" + manuf + "/" + dosage);

			//校验数据索引
			Assert.assertEquals(index, "epmats","数据索引不匹配"); 

			//校验最低分			
			boolean scoreCheck = score >= 1;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");

			//校验名称匹配
			boolean licenseMatch = license.contains("国药");
			boolean manufMatch = manuf.contains("山东");	
			boolean dosageMatch = dosage.contains("片");
			//System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(licenseMatch, "license不匹配");
			Assert.assertTrue(manufMatch, "manuf不匹配");
			Assert.assertTrue(dosageMatch, "dosage不匹配");
		}
	}

	@Test
	public void duplicateVerify() throws ClientProtocolException, IOException {
		System.out.println("------开始执行DuplicateVerify------");

		List<String> list = new ArrayList<String>();
		//String url = "http://172.16.86.60:8080/api.search/ep?q=胃灵颗粒&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1";
		String url = "http://101.201.114.175:8080/api.search/ep?q=胃";
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {
			JSONObject temp = (JSONObject) hitsArray.get(i);
			String source = temp.getString("_source");
			//System.out.println(source);

			JSONObject nameObject = JSONObject.fromObject(source);

			String name = nameObject.getString("name");
			String manuf = nameObject.getString("manuf");
			String supplier = nameObject.getString("supplier");
			int provinceId = nameObject.getInt("provinceId");
			int cityId = nameObject.getInt("cityId");
			

			System.out.println(name + "-" + manuf + "-" + supplier + "-" + provinceId + "-" + cityId);


			if(!list.contains(name+manuf+supplier+provinceId+cityId)){

				list.add(name+manuf+supplier+provinceId+cityId);
				//System.out.println(list);
			}else{
				Assert.assertFalse(true, "存在重复，重复的内容是：  "+name+"-"+manuf+"-"+supplier+"-"+provinceId+"-"+ cityId);
			}				
		}
	}

	@Test
	public void pageSize() throws ClientProtocolException, IOException{
		System.out.println("------开始执行PageSize------");	

		String url = "http://101.201.114.175:8080/api.search/ep?q=复方&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  
			JSONObject temp = (JSONObject) hitsArray.get(i);  
			String source = temp.getString("_source");  
			System.out.println("source = "+ source);
		}
		System.out.println("结果 = " + hitsArray.size() + " 条");

		int count = hitsArray.size();
		int input = 10;
		boolean compare = count <= input;
		//System.out.println(compare);

		Assert.assertTrue(compare, "PageSize不正确");
	}

	/* @Test
	public void emptyParams() throws ClientProtocolException, IOException{
		System.out.println("------开始执行EmptyParams------");

		String url = "http://101.201.114.175:8080/api.search/ep?"; 
		String result = HttpGetUtils.httpGetUtils(url);
		//System.out.println(arg0);

		JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			

		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");

		Assert.assertTrue(hits==null||hits.size()==0, "查询无结果  ");
	}*/

	/*@DataProvider(name = "inparams")
	public static Object[][] invalidInput(){
		return new Object[][] { 
			{ "http://101.201.114.175:8080/api.search/ep?q=睇&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1"}, 
            { "http://101.201.114.175:8080/api.search/ep", "{\"license\":\"感人肺腑的身份asdgs\",\"page\":1,\"pageSize\":20}"},

	}*/
	
	@Test   //(dataProvider = "inparams")
	public void invalidParams() throws ClientProtocolException, IOException{
		System.out.println("------开始执行InvalidParams------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=尕&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=1";
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);			

		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		Assert.assertTrue(hitsArray==null||hitsArray.size()==0, "查询无结果  ");
	}

	@Test
	public void minScore() throws ClientProtocolException, IOException{
		System.out.println("------开始执行SearchName------");

		String url = "http://101.201.114.175:8080/api.search/ep?q=复方&page=1&pageSize=10&provinceId=110000&cityId=110100&minScore=3.4"; 
		String result = HttpGetUtils.httpGetUtils(url);

		JSONObject jsonObject = JSONObject.fromObject(result);		
		JSONArray hitsArray;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hitsArray = hitsObject.getJSONArray("hits");
		System.out.println("hits = " + hitsArray);

		if(hitsArray==null||hitsArray.size()==0){
			System.out.println("查询结果为空");
		}
		Assert.assertFalse(hitsArray==null||hitsArray.size()==0, "查询无结果  ");

		for (int i = 0; i < hitsArray.size(); i++) {  			
			double score = hitsArray.getJSONObject(i).getDouble("_score");
	
			//校验最低分			
			boolean scoreCheck = score >= 3.5;
			//System.out.println(scoreCheck);
			Assert.assertTrue(scoreCheck, "分数低于minScore");
		}
	}
}	













