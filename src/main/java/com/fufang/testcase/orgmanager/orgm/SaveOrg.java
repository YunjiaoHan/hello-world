package com.fufang.testcase.orgmanager.orgm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpCookie;

import net.sf.json.JSONObject;

public class SaveOrg {

	String cookie;

	public String getCookie() {
		return cookie;
	}

	public void setCookie(String cookie) {
		this.cookie = cookie;
	}
	
	static Connection con;


	@BeforeTest
	public void LoginFirst() throws ClientProtocolException, URISyntaxException, IOException {
		System.out.println("--------before test---------");	
		String url = "http://172.16.88.183:9999/login_pharmacyLogin.do?user.userName=abc&user.password=1qaz2wsx";
		String cookie = HttpCookie.getCookie(url);
		setCookie(cookie);
	}

	@Test
	public void saveOrg() throws IOException{
		System.out.println("------start run saveOrg------");

		String cookie = getCookie();
		String url = "http://172.16.88.183:9999/organization-manager/orgManager/saveOrg.do";
		List<NameValuePair> nvps = new ArrayList <NameValuePair>();
		nvps.add(new BasicNameValuePair("orderStr","{\"tpharmacy\":{\"pharmacyCode\":\"101\",\"chainType\":1,\"name\":\"apitest\",\"currency\":1,\"economicType\":0,\"operationMode\":1,\"registeredCapital\":200,\"district\":\"sss\",\"provinceId\":1,\"cityId\":2,\"regionId\":3,\"postalcode\":22,\"registAddress\":\"sds\",\"pharmacyAddress\":\"dsds\",\"phone\":131,\"fax\":\"1313\",\"email\":\"sdfsdf\",\"webAddress\":\"sdfsd\",\"healthInsurancePharmacy\":1,\"representative\":\"ds\",\"businessPrincipal\":\"sds\",\"qualityPrincipal\":\"sds\",\"openBank\":\"sd\",\"openName\":\"sd\",\"accounts\":\"sdsd\",\"ranges\":\"sdfsdf\",\"status\":1,\"remark\":\"sdf\",\"managerDistrict\":\"sdf\",\"manager\":\"dsfd\",\"shopKeeper\":\"sdfds\",\"shopKeeperPhone\":133,\"addPoint\":20,\"distPriceType\":1,\"distPriceOrder\":22,\"priceOrderId\":11,\"settleType\":1,\"unity\":1,\"organizationId\":1,\"shareStorage\":1,\"remoteAudit\":1},\"pharmacyComplement\":{\"healthInsuranceCode\":\"sds\",\"busioption\":\"sd\",\"visible\":1,\"monthlyRent\":121,\"setupShopDate\":\"2016-01-01\",\"recentRelocationDate\":\"2016-01-01\",\"recentTransfDate\":\"2016-01-01\",\"storageArea\":\"12\",\"payProvision\":1,\"payPeriod\":1,\"payPeriodUnit\":1,\"payFrequency\":1,\"payTime\":1,\"pinyin\":\"sds\",\"pharmacyGroupId\":\"5718A378-CB35-452B-A75E-0DCF2DE06665\",\"pharmacyGroup\":\"sd\",\"managerDistrictId\":\"5718A378-CB35-452B-A75E-0DCF2DE05555\",\"ppPurchaserId\":1,\"distrManId\":2,\"ppCheckerId\":3,\"ppReceiverId\":4,\"ppStoremanId\":5,\"ppReCheckerId\":6,\"ppPurchaser\":\"45\",\"distrMan\":\"54\",\"ppChecker\":\"6\",\"ppReceiver\":\"4\",\"ppStoreman\":\"3\",\"ppReChecker\":\"2\",\"inputTax\":0.13,\"outputTax\":0.17},\"pharmacyQualification\":[{\"licenseId\":155,\"licenseCode\":\"assd\",\"validateTo\":\"2016-01-01\",\"fileId\":\"sad\",\"fileName\":\"sdff\"}]}"));

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url); 
		httpPost.addHeader("cookie", cookie);
		httpPost.setEntity(new UrlEncodedFormEntity(nvps)); 
		InputStream is = null;

		try {  
			HttpResponse httpResponse = httpClient.execute(httpPost);
			int status = httpResponse.getStatusLine().getStatusCode();	

			if(status == 200)  
			{  
				HttpEntity httpEntity = httpResponse.getEntity();  
				if(httpEntity != null){  
					is = httpEntity.getContent();  
					//转换为字节输入流  
					BufferedReader br = new BufferedReader(new InputStreamReader(is, Consts.UTF_8));  
					String result = null;  
					while((result=br.readLine()) != null){  
						System.out.println(result); 

						JSONObject jsonObject = JSONObject.fromObject(result);
						int state = jsonObject.getInt("state");
						String msg = jsonObject.getString("msg");

						Assert.assertEquals(state, 200, "unexpect code");
						Assert.assertEquals(msg, "success");
					}
				}
			}else{
				System.out.println("Unexpected code - " + status);
			}
		} catch (UnsupportedEncodingException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}  
		catch (ClientProtocolException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}  
		catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
			e.getMessage().toString();  
		}finally{
			httpClient.close();
		}
	}
	
	/*@AfterTest
	public void deleteData() throws SQLException{
		Conn c = new Conn();   //创建本类对象
		c.getConnection();     //调用连接数据库方法

		try{
			Statement statement = con.createStatement();
			String querySql = "SELECT * FROM [dbo].[t_pharmacy] WHERE id = apitest2";
			String deleteSql = "DELETE FROM t_pharnacy WHERE name = 'apitest2'";
			PreparedStatement pStatement = con.prepareStatement(deleteSql);
			
			ResultSet resultSet = statement.executeQuery(querySql);
			
			//pStatement.setInt(830, id);
			pStatement.executeUpdate();
			
			while(resultSet.next()){
				String id = resultSet.getString("id");
				System.out.println(id);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}*/
}