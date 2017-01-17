package com.fufang.testcase.ep.elasticsearch;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpPostJs;

import net.sf.json.*;


public class SearchEPOld {
	
	@Test
	public void searchName(){
		
		System.out.println("开始执行SearchName");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"name\":\"安乃近\",\"page\":1,\"pageSize\":13}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
								
			JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
		
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			System.out.println("source = " + source);
			    
			String name;
		    JSONObject nameObject = JSONObject.fromObject(source);
		    name = nameObject.getString("name");
		    System.out.println("name = " + name);
			    
				
			boolean nameMatch = name.contains("安乃近");
			System.out.println("NameMatch = "+nameMatch);
			Assert.assertTrue(nameMatch);
		    //Assert.assertEquals(name,"铝镁司片");
			}
		
	}

	@Test
	public void searchLicense(){
		
		System.out.println("开始执行SearchLicense");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"license\":\"国\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String license;
		    JSONObject nameObject = JSONObject.fromObject(source);
		    license = nameObject.getString("license");
		    System.out.println("lisence = " + license);
			    
				
			boolean licenseMatch = license.contains("国");
			System.out.println("LicenseMatch = "+licenseMatch);
			Assert.assertTrue(licenseMatch);
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}

	@Test
	public void searchManuf(){
		
		System.out.println("开始执行SearchManuf");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"manuf\":\"北京\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String manuf;
		    JSONObject nameObject = JSONObject.fromObject(source);
		    manuf = nameObject.getString("manuf");
		    System.out.println("manuf = " + manuf);
			    
				
			boolean manufMatch = manuf.contains("北京");
			System.out.println("LicenseMatch = "+manufMatch);
			Assert.assertTrue(manufMatch);
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}

	
	@Test
	public void searchSupplier(){
		
		System.out.println("开始执行SearchSupplier");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"supplier\":\"北京\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);		
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String supplier;
		    JSONObject supplierObject = JSONObject.fromObject(source);
		    supplier = supplierObject.getString("supplier");
		    System.out.println("suppliere = " + supplier);
			    
				
			boolean supplierMatch = supplier.contains("北京");
			System.out.println("supplierMatch = "+supplierMatch);
			Assert.assertTrue(supplierMatch);
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}

	
	@Test
	public void searchNameSpec(){
		
		System.out.println("开始执行SearchNameSpec");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"name\":\"片\",\"spec\":\"片\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);		
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String spec;
		    JSONObject specObject = JSONObject.fromObject(source);
		    spec = specObject.getString("spec");
		    System.out.println("spec = " + spec);
			    
				
			boolean specMatch = spec.contains("片");
			System.out.println("specMatch = "+specMatch);
			Assert.assertTrue(specMatch, "结果不匹配  ");
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}
	
	@Test
	public void searchLicenseDosage(){
		
		System.out.println("开始执行SearchLicenseDosage");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"license\":\"国药准字\",\"dosage\":\"剂\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);		
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String dosage;
		    JSONObject dosageObject = JSONObject.fromObject(source);
		    dosage = dosageObject.getString("dosage");
		    System.out.println("dosage = " + dosage);
			    
				
			boolean dosageMatch = dosage.contains("剂");
			System.out.println("dosageMatch = "+dosageMatch);
			Assert.assertTrue(dosageMatch, "结果不匹配  ");
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}

	@Test
	public void searchNameManuf(){
		
		System.out.println("开始执行SearchNameManuf");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"name\":\"安乃近\",\"manuf\":\"北京\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);		
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String name;
		    JSONObject nameObject = JSONObject.fromObject(source);
		    name = nameObject.getString("name");
		    String manuf;
		    JSONObject manufObject = JSONObject.fromObject(source);
		    manuf = manufObject.getString("manuf");
		    		    
		    System.out.println( name + "   " + manuf);
		        	    
			boolean nameMatch = name.contains("安乃近");
			System.out.println("nameMatch = "+ nameMatch);
			boolean manufMatch = manuf.contains("北京");
			System.out.println("manufMatch ="+ manufMatch);
			Assert.assertTrue(nameMatch, "Name结果不匹配  ");
			Assert.assertTrue(manufMatch, "Manuf结果不匹配");		
		    //Assert.assertEquals(name,"铝镁司片");
			}
	}
	
	@Test
	public void searchLicenseManufDosage(){
		
		System.out.println("开始执行SearchLicenseManufDosage");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"license\":\"国药准字\",\"manuf\":\"北京\",\"dosage\":\"片\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
									
			JSONObject jsonObject = JSONObject.fromObject(result);		
			
			JSONArray hits;
			JSONObject hitsObject = jsonObject.getJSONObject("hits");
			hits = hitsObject.getJSONArray("hits");
			//System.out.println("hits = " + hits);
			
			if(hits==null||hits.size()==0){
				System.out.println("hits = " + hits);
			}
			Assert.assertFalse(hits==null||hits.size()==0, "查询无结果  ");
						
			String source;
			for (int i = 0; i < hits.size(); i++) {  
			JSONObject temp = (JSONObject) hits.get(i);  
			source = temp.getString("_source");  
			//System.out.println("source = " + source);
			    
			String license;
		    JSONObject nameObject = JSONObject.fromObject(source);
		    license = nameObject.getString("license");
		    String manuf;
		    JSONObject manufObject = JSONObject.fromObject(source);
		    manuf = manufObject.getString("manuf");
		    String dosage;
		    JSONObject dosageObject = JSONObject.fromObject(source);
		    dosage = dosageObject.getString("dosage");
		 
		    		    
		    System.out.println( license + "   " + manuf + "   " + dosage);
		        	    
			boolean licenseMatch = license.contains("国药准字");
			System.out.println("licenseMatch = "+ licenseMatch);
			boolean manufMatch = manuf.contains("北京");
			System.out.println("manufMatch ="+ manufMatch);
			boolean dosageMatch = dosage.contains("片");
			System.out.println("dosageMatch =" + dosageMatch);
			Assert.assertTrue(licenseMatch, "License结果不匹配  ");
			Assert.assertTrue(manufMatch, "Manuf结果不匹配");
			Assert.assertTrue(dosageMatch, "Dosage结果不匹配");
			//Assert.assertEquals(name,"铝镁司片");
			}
	}
	
	@Test
	public void duplicateVerify() {
		
		System.out.println("开始执行DuplicateVerify");
		
		List<String> list = new ArrayList<String>();
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"name\":\"安乃近\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		
		JSONObject jsonObject = JSONObject.fromObject(result);

		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");
		//System.out.println("hits = " + hits);
		String source;
		for (int i = 0; i < hits.size(); i++) {
			JSONObject temp = (JSONObject) hits.get(i);
			source = temp.getString("_source");
			//System.out.println(source);

			JSONObject nameObject = JSONObject.fromObject(source);
			
			String name = nameObject.getString("name");
			String manuf = nameObject.getString("manuf");
			String supplier = nameObject.getString("supplier");
			
			System.out.println(name + "-" + manuf + "-" + supplier);
			
			
			if(!list.contains(name+manuf+supplier)){
				
				list.add(name+manuf+supplier);
				//System.out.println(list);
			}else{
				Assert.assertFalse(true, "存在重复，重复的内容是：  "+name+"-"+manuf+"-"+supplier);
			}				
		}
	}
	
	@Test
	 public void pageSize(){
		
		System.out.println("开始执行PageSize");	
		
	 String URL = "http://172.16.86.60:8080/api.search/ep"; 
	 String params = "{\"name\":\"虫草\",\"page\":1,\"pageSize\":40}"; 

	 String result = HttpPostJs.post(URL, params);
 	
	 JSONObject jsonObject = JSONObject.fromObject(result);
	
	  JSONArray hits;
	  JSONObject hitsObject = jsonObject.getJSONObject("hits");
	  hits = hitsObject.getJSONArray("hits");
	  System.out.println("hits = " + hits);
	    
	
	  @SuppressWarnings("unused")
	String source;
	  for (int i = 0; i < hits.size(); i++) {  
	  JSONObject temp = (JSONObject) hits.get(i);  
	  source = temp.getString("_source");  
	  //System.out.println("source = "+ source);
	  }
	 System.out.println("结果 = " + hits.size() + " 条");
	   	 	
	   int count = hits.size();
	   int input = 40;
	   boolean compare = count <= input;
	   //System.out.println(compare);
	    	    
	  Assert.assertTrue(compare, "PageSize不正确");
	}
	
	@Test
	public void emptyParams(){
		
		System.out.println("开始执行EmptyParams");
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
								
		JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
			
		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");
			
		Assert.assertTrue(hits==null||hits.size()==0, "查询无结果  ");
		}
    
	@DataProvider(name = "inparams")
	public static Object[][] invalidInput(){
			return new Object[][] { 
			{ "http://172.16.86.60:8080/api.search/ep", "2","3","{\"name\":\"sdgsdfag\",\"page\":1,\"pageSize\":20}"}, 
//			{ "http://172.16.86.60:8080/api.search/ep", "{\"license\":\"感人肺腑的身份asdgs\",\"page\":1,\"pageSize\":20}"},
//			{ "http://172.16.86.60:8080/api.search/ep", "{\"manuf\":\"erjwrhwefge\",\"page\":1,\"pageSize\":20}"},
//			//{ "http://172.16.86.60:8080/api.search/ep", "{\"name\":\"虫草\",\"supplier\":\"dgdsgsfa萨芬发\",\"page\":1,\"pageSize\":20}"},
//			{ "http://172.16.86.60:8080/api.search/ep", "{\"license\":\"国\",\"manuf\":\"weyerhwfef\",\"page\":1,\"pageSize\":20}"},
			};
	   }
	@Test(dataProvider = "inparams")
	public void invalidParams(String URL,String key,String value, String params){
	
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();  
		nvps.add(new BasicNameValuePair(key,value));
		
		System.out.println("开始执行InvalidParams");
		String result = HttpPostJs.post(URL, params);
		
										
		JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
			
		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");
			
		Assert.assertTrue(hits==null||hits.size()==0, "查询无结果  ");
	}
	
	
	
}	




	
	






	
	