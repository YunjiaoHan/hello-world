package com.fufang.testcase.ep.elasticsearch;

import net.sf.json.*;

import org.testng.Assert;
import org.testng.annotations.*;

import com.fufang.httprequest.HttpPostJs;


public class SearchWX {
		
	@Test
	public void searchName(){
		
		String URL = "http://172.16.86.60:8080/api.search/wx"; 
		String params = "{\"keywords\":\"阿司匹林\",\"type\":\"name\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
		
		JSONObject jsonObject = JSONObject.fromObject(result);//解第一层大括号			
		
		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");
		//System.out.println("hits = " + hits);
	
		String source;
		for (int i = 0; i < hits.size(); i++) {  
		JSONObject temp = (JSONObject) hits.get(i);  
		source = temp.getString("_source");  
		//System.out.println("source = " + source);
		    
		String name;
	    JSONObject nameObject = JSONObject.fromObject(source);
	    name = nameObject.getString("name");
	    System.out.println("name = " + name);
		    
			
		boolean nameMatch = name.contains("阿司匹林");
		System.out.println("NameMatch = "+nameMatch);
		Assert.assertTrue(nameMatch);
	    //Assert.assertEquals(name,"铝镁司片");
		}
	}

		
	@Test
	public void searchNameManuf(){
		
		String URL = "http://172.16.86.60:8080/api.search/ep"; 
		String params = "{\"name\":\"虫草\",\"manuf\":\"上海\",\"page\":1,\"pageSize\":30}"; 
	
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
		        	    
			boolean nameMatch = name.contains("虫草");
			System.out.println("nameMatch = "+ nameMatch);
			boolean manufMatch = manuf.contains("上海");
			System.out.println("manufMatch ="+ manufMatch);
			Assert.assertTrue(nameMatch, "Name结果不匹配  ");
			Assert.assertTrue(manufMatch, "Manuf结果不匹配");	
		
			}	
	}
	
	@Test
	public void searchSymptoms(){
		
		String URL = "http://172.16.86.60:8080/api.search/wx"; 
		String params = "{\"keywords\":\"头疼\",\"type\":\"symptoms\",\"page\":1,\"pageSize\":30}"; 
	
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
		Assert.assertFalse(hits==null||hits.size()==0, "symptoms 查询无结果  ");
	
		String source;
		for (int i = 0; i < hits.size(); i++) {  
		JSONObject temp = (JSONObject) hits.get(i);  
		source = temp.getString("_source");  
		//System.out.println("source = " + source);
		    
		String symptoms;
	    JSONObject nameObject = JSONObject.fromObject(source);
	    symptoms = nameObject.getString("symptoms");
	    System.out.println("symptoms = " + symptoms);
		    
			
		boolean symptomsMatch = symptoms.contains("头疼");
		System.out.println("symptomsMatch = "+symptomsMatch);
		Assert.assertTrue(symptomsMatch);
	   
		}
	}
	
	@Test
	public void searchClass(){
		
		String URL = "http://172.16.86.60:8080/api.search/wx"; 
		String params = "{\"keywords\":\"中药\",\"type\":\"clazz\",\"page\":1,\"pageSize\":30}"; 
	
		String result = HttpPostJs.post(URL, params);
		//System.out.println(arg0);
		
		JSONObject jsonObject = JSONObject.fromObject(result);		
		
		JSONArray hits;
		JSONObject hitsObject = jsonObject.getJSONObject("hits");
		hits = hitsObject.getJSONArray("hits");
		//System.out.println("hits = " + hits);
	
		String source;
		for (int i = 0; i < hits.size(); i++) {  
		JSONObject temp = (JSONObject) hits.get(i);  
		source = temp.getString("_source");  
		//System.out.println("source = " + source);
		    
		String clazz;
	    JSONObject nameObject = JSONObject.fromObject(source);
	    clazz = nameObject.getString("clazz");
	    System.out.println("clazz = " + clazz);    
			
		boolean clazzMatch = clazz.contains("中药");
		System.out.println("clazzMatch = "+clazzMatch);
		Assert.assertTrue(clazzMatch);
	  	}
	}
}
	

