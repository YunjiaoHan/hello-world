
package com.fufang.testcase.orgmanager.orgm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpCookie;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetGspLicense {
	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}

	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=hyjtest&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
		System.out.println("--------before test---------");	
	}

	@Test
	public void getGspLicense() throws ClientProtocolException, IOException, URISyntaxException{
		String cookie = getCookie();
		CloseableHttpClient httpClient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		InputStream is = null;  
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/getGspLicense";
		try{
			HttpGet httpGet = new HttpGet(url);
			httpGet.addHeader("cookie", cookie);
			response = httpClient.execute(httpGet);  
			HttpEntity entity = response.getEntity(); 
			if(entity != null){  
				is = entity.getContent();  
				//转换为字节输入流  
				BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));  
				String result = null;  
				while((result=br.readLine()) != null){  
					System.out.println(result);  

					JSONObject jsonObject = JSONObject.fromObject(result);
					
					JSONArray rowsArray = jsonObject.getJSONArray("rows");		
					System.out.println(rowsArray);
					
					int gspType;
					List<Integer> gspTypeList = new ArrayList<Integer>();
					gspTypeList.add(0);
					gspTypeList.add(0);
					gspTypeList.add(0);
					gspTypeList.add(0);
					
					int licenseType;
					List<Integer> licenseTypeList = new ArrayList<Integer>();
					licenseTypeList.add(0);
					licenseTypeList.add(0);
					licenseTypeList.add(0);
					licenseTypeList.add(1);
					
					String description;
					List<String> descriptionList = new ArrayList<String>();
					descriptionList.add("《税务登记证》");
					descriptionList.add("《组织机构代码证》");
					descriptionList.add("《企业法人营业执照》");
					descriptionList.add("年检证明文件");
					
					String gspTypeName;
					List<String> gspTypeNameList = new ArrayList<String>();
					gspTypeNameList.add("企业资质");
					gspTypeNameList.add("企业资质");
					gspTypeNameList.add("企业资质");
					gspTypeNameList.add("企业资质");
							
					String licenseTypeName;
					List<String> licenseTypeNameList = new ArrayList<String>();
					licenseTypeNameList.add("企业资质");
					licenseTypeNameList.add("企业资质");
					licenseTypeNameList.add("企业资质");
					licenseTypeNameList.add("相关文件");
					
					
					for(int i = 0; i < 4; i++){
						gspType = rowsArray.getJSONObject(i).getInt("gspType");
						licenseType = rowsArray.getJSONObject(i).getInt("licenseType");
						description = rowsArray.getJSONObject(i).getString("description");
						gspTypeName = rowsArray.getJSONObject(i).getString("gspTypeName");
						licenseTypeName = rowsArray.getJSONObject(i).getString("licenseTypeName");
						
						int expectgspType = gspTypeList.get(i);
						int expectLicenseType = licenseTypeList.get(i);
						String expectDescription = descriptionList.get(i);
						String expectgspTypeName = gspTypeNameList.get(i);
						String expcetLicenseTypeName = licenseTypeNameList.get(i);
						
						Assert.assertEquals(gspType, expectgspType, "wrong gspType");
						Assert.assertEquals(licenseType, expectLicenseType, "wrong licenseType");
						Assert.assertEquals(description, expectDescription, "wrong description");
						Assert.assertEquals(gspTypeName, expectgspTypeName, "wrong gspTypeName");
						Assert.assertEquals(licenseTypeName, expcetLicenseTypeName, "wrong licenseTypeName");
						
					}

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
	}  
}
