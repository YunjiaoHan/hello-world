package com.fufang.testcase.orgmanager.warnset;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fufang.httprequest.HttpGetLogin;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class GetAllRoleList {
	
	@Test
	public void getAllRoleList() throws ClientProtocolException, IOException, URISyntaxException{

	String TestUrl = "http://172.16.87.194:9999/organization-manager/warnSet/getAllRoleList";
	String userName= "hyjtest02emp";
	String pwd = "1qaz2wsx";
	String loginUrl = "http://172.16.87.194:9999/login_pharmacyLogin.do?user.userName="+userName+"&user.password="+pwd;

	String result = HttpGetLogin.httpGetLogin(TestUrl, loginUrl, userName, pwd);
	System.out.println("result - " + result);

		JSONObject jsonObject = JSONObject.fromObject(result);
		JSONArray roleListArray = jsonObject.getJSONArray("roleList");
		System.out.println("roleListArray - "+ roleListArray);

		{
			String id= "";
			List<String> expectIdList = new ArrayList<String>();
			expectIdList.add("D114CDC3-587A-4F23-ADD5-70462AED32C6");
			expectIdList.add("D114CDC3-587A-4F23-ADD5-70462AED32C7");
			expectIdList.add("9DDB6000-3506-401A-B0D2-4DB9053F6391");
			expectIdList.add("407D479C-C294-4DE4-ADAA-AEC8DC5D4D92");
			expectIdList.add("CAE31E80-09E7-4241-AA4C-1E51EFE19D14");
			expectIdList.add("2B277015-7BC3-4B87-87D0-167EC1955342");
			expectIdList.add("927E5716-3535-4968-BE87-172A250AFC68");
			expectIdList.add("9908BEAF-0FB8-4675-8E15-184FF7A6B13F");
			expectIdList.add("B6E58291-0A5B-49A3-B379-19DAC845E59F");
			expectIdList.add("CAE31E80-09E7-4241-AA4C-1E51EFE19D14");
			expectIdList.add("F7D7258C-0C10-465E-827D-1EB41EFE03B2");
			
			String pharmacyId= "";
			List<String> expectPharmacyIdList = new ArrayList<String>();
			expectPharmacyIdList.add("200369");
			expectPharmacyIdList.add("200370");
			expectPharmacyIdList.add("2");
			expectPharmacyIdList.add("2");
			expectPharmacyIdList.add("2");
			expectPharmacyIdList.add("3");
			expectPharmacyIdList.add("3");
			expectPharmacyIdList.add("3");
			expectPharmacyIdList.add("2");
			expectPharmacyIdList.add("2");
			
			String name= "";
			List<String> expectNameList = new ArrayList<String>();
			expectNameList.add("[总部]采购人员");
			expectNameList.add("[分店]财务人员");
			expectNameList.add("[总部]储存人员");
			expectNameList.add("[总部]销售人员");
			expectNameList.add("[总部]配送人员");
			expectNameList.add("养护人员-成药");
			expectNameList.add("电子监管码管理人员");
			expectNameList.add("处方调剂人员-中药饮片");
			expectNameList.add("质量管理人员-成药");
			expectNameList.add("配送人员");
			
			String code= "";
			List<String> expectCodeList = new ArrayList<String>();
			expectCodeList.add("20036901");
			expectCodeList.add("20037001");
			expectCodeList.add("3101");
			expectCodeList.add("4101");
			expectCodeList.add("5101");
			expectCodeList.add("1301");
			expectCodeList.add("9201");
			expectCodeList.add("1413");
			expectCodeList.add("1101");
			expectCodeList.add("5101");
			
			String isSys= "";
			List<String> expectIsSysList = new ArrayList<String>();
			expectIsSysList.add("0");
			expectIsSysList.add("0");
			expectIsSysList.add("28");
			expectIsSysList.add("30");
			expectIsSysList.add("32");
			expectIsSysList.add("20");
			expectIsSysList.add("20");
			expectIsSysList.add("20");
			expectIsSysList.add("14");
			expectIsSysList.add("32");
			
			String chainType= "";
			List<String> expectChainTypeList = new ArrayList<String>();
			expectChainTypeList.add("0");
			expectChainTypeList.add("1");
			expectChainTypeList.add("0");
			expectChainTypeList.add("0");
			expectChainTypeList.add("0");
			expectChainTypeList.add("1");
			expectChainTypeList.add("1");
			expectChainTypeList.add("1");
			expectChainTypeList.add("0");
			expectChainTypeList.add("0");
			
			String roleGroup= "";
			List<String> expectRoleGroupList = new ArrayList<String>();
			expectRoleGroupList.add("31");
			expectRoleGroupList.add("81");
			expectRoleGroupList.add("41");
			expectRoleGroupList.add("61");
			expectRoleGroupList.add("51");
			expectRoleGroupList.add("23");
			expectRoleGroupList.add("71");
			expectRoleGroupList.add("21");
			expectRoleGroupList.add("21");
			expectRoleGroupList.add("51");
			
			String remark= "";
			List<String> expectRemarkList = new ArrayList<String>();
			expectRemarkList.add("总店角色");
			expectRemarkList.add("分店角色");
			expectRemarkList.add("总部角色");
			expectRemarkList.add("总部角色");
			expectRemarkList.add("总部角色");
			expectRoleGroupList.add("71");
			expectRoleGroupList.add("21");
			expectRoleGroupList.add("21");
			expectRoleGroupList.add("51");
			expectRoleGroupList.add("22");
			
			String roleGroupName= "";
			List<String> expectRoleGroupNameList = new ArrayList<String>();
			expectRoleGroupNameList.add("采购");
			expectRoleGroupNameList.add("财务");
			expectRoleGroupNameList.add("存储");
			expectRoleGroupNameList.add("销售");
			expectRoleGroupNameList.add("配送");
			expectRoleGroupNameList.add("养护");
			expectRoleGroupNameList.add("信息");
			expectRoleGroupNameList.add("质量");
			expectRoleGroupNameList.add("质量");
			expectRoleGroupNameList.add("配送");

			for(int i = 0; i<2; i++){
				id = roleListArray.getJSONObject(i).getString("id");
				String expectId = expectIdList.get(i);
				System.out.println(i+1 + ": id - " + id);
				Assert.assertEquals(id, expectId, "wrong id");
	
				pharmacyId = roleListArray.getJSONObject(i).getString("pharmacyId");
				String expectPharmacyId = expectPharmacyIdList.get(i);
				System.out.println(i+1 + ": pharmacyId - " + pharmacyId);
				Assert.assertEquals(pharmacyId, expectPharmacyId, "wrong PharmacyId");
		
				name = roleListArray.getJSONObject(i).getString("name");
				String expectName = expectNameList.get(i);
				System.out.println(i+1 + ": name - " + name);
				Assert.assertEquals(name, expectName, "wrong name");

				code = roleListArray.getJSONObject(i).getString("code");
				String expectCode = expectCodeList.get(i);
				System.out.println(i+1 + ": code - " + code);
				Assert.assertEquals(code, expectCode, "wrong code");
	
				isSys = roleListArray.getJSONObject(i).getString("isSys");
				String expectIsSys = expectIsSysList.get(i);
				System.out.println(i+1 + ": isSys - " + isSys);
				Assert.assertEquals(isSys, expectIsSys, "wrong isSys");

				chainType = roleListArray.getJSONObject(i).getString("chaintype");
				String expectChainType = expectChainTypeList.get(i);
				System.out.println(i+1 + ": chainType - " + chainType);
				Assert.assertEquals(chainType, expectChainType, "wrong chainType");

				roleGroup = roleListArray.getJSONObject(i).getString("roleGroup");
				String expectRoleGroup = expectRoleGroupList.get(i);
				System.out.println(i+1 + ": roleGroup - " + roleGroup);
				Assert.assertEquals(roleGroup, expectRoleGroup, "wrong roleGroup");

				remark = roleListArray.getJSONObject(i).getString("remark");
				String expectRemark = expectRemarkList.get(i);
				System.out.println(i+1 + ": roleGroup - " + remark);
				Assert.assertEquals(remark, expectRemark, "wrong remark");

				roleGroupName = roleListArray.getJSONObject(i).getString("roleGoupName");
				String expectRoleGroupName = expectRoleGroupNameList.get(i);
				System.out.println(i+1 + ": roleGroupName - " + roleGroupName);
				Assert.assertEquals(roleGroupName, expectRoleGroupName, "wrong roleGroupName");
			}
		}
	}	
}

