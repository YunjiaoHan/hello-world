package com.fufang.testcase.his;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;

import com.fufang.utils.HttpUtils;
import com.fufang.utils.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Upload {

//	final static String FITURL = "http://ct.fu-fang.com:8001/ffcloud-hisFc/getFitPharmacy";
//	final static String UPLOADURL = "http://ct.fu-fang.com:8001/ffcloud-hisFc/uploadPrescription";
	
	final static String FITURL = "http://222.128.34.242:8090/getFitPharmacy";
	final static String UPLOADURL = "http://222.128.34.242:8090/uploadPrescription";
	

	public static void main(String[] args) throws UnsupportedEncodingException, ClientProtocolException, IOException{

		for(int i = 33; i < 34; i++){
			String dianosisId = "诊疗" + i;
			String ffId = "fufang" + i;
			String clientName = "复方" + i;

			JSONObject paramsObject = new JSONObject();

			JSONObject diagnosisObject = new JSONObject();
			diagnosisObject.put("CLINC_NO","I2017");
			diagnosisObject.put( "DIAG_NAME","骨折");
			diagnosisObject.put("NOTE","");
			diagnosisObject.put("SOAP_SEQID",dianosisId);
			JSONArray diagnosisArray = new JSONArray();
			diagnosisArray.add(0, diagnosisObject);

			JSONObject itemObject = new JSONObject();
			itemObject.put("CHARGE_CODE", "550122220015");
			itemObject.put("CHARGE_OPR_ID", "李伟");
			itemObject.put("CLINC_NO", "20170113");
			itemObject.put("FREQUENCY_NAME", "一天2片");
			itemObject.put("IS_EXTERNAL", "1");
			itemObject.put("LEAST_UNIT", "g");
			itemObject.put("MED_GNAME", "阿托伐他汀钙片");
			itemObject.put("MED_PRICE", "12.5");
			itemObject.put("MED_SEQID", "134177");
			itemObject.put("MED_SPEC", "6克*12袋");
			itemObject.put("NUM_PER", "2");
			itemObject.put("REAL_AMOUNT", "37.5");
			itemObject.put("SEND_NUM", "3");
			itemObject.put( "SF_TIME", "2016-12-21 00:00:00");
			itemObject.put("SOAP_MEDID", "374586SM");
			itemObject.put("SOAP_SEQID", dianosisId);
			itemObject.put("UNIT_PER", "g");
			itemObject.put("USAGE_NAME", "口服");
			itemObject.put("VALID", "1");
			JSONArray itemArray = new JSONArray();
			itemArray.add(0, itemObject);

			JSONObject prescriptionObject = new JSONObject();
			prescriptionObject.put("PHARMACY_ID", "200282");
			prescriptionObject.put("AGE","83");
			prescriptionObject.put("CASE_GROUP","1");
			prescriptionObject.put("CARDNO","12345678901S");
			prescriptionObject.put("CLIENT_NAME", clientName);
			prescriptionObject.put("CLINC_NO", "20170113");
			prescriptionObject.put("CLINIC_DATE", "2016-12-19 00:00:00");
			prescriptionObject.put("DEPART_CODE", "J2735");
			prescriptionObject.put("DEPART_NAME", "东华门街道多福巷社区卫生服务站");
			prescriptionObject.put("DEPART_UPCODE", "J2700");
			prescriptionObject.put("DOC_NAME", "医生13");
			prescriptionObject.put("EHR_NEWNO", "06000948-A");
			prescriptionObject.put("ID_CARD","110101193212290013");
			prescriptionObject.put("MEDICARE", "8");
			prescriptionObject.put("MED_TYPE", "02");
			prescriptionObject.put("MOBILE_PHONE", "13522671461");
			prescriptionObject.put("NOW_ADDRESS", "景山街道隆福寺社区美术馆东街2");
			prescriptionObject.put("PRESCRI_TYPE", "3");
			prescriptionObject.put("SEX", "男");
			prescriptionObject.put("SOAP_SEQID", dianosisId);
			prescriptionObject.put("UPCODE_NAME", "东城区社区卫生服务管理中心");
			prescriptionObject.put("VALID", "1");
			prescriptionObject.put("FFPRESCRIPTIONCODE", ffId);
			prescriptionObject.put("prescriptionItems", itemArray);
			JSONArray prescriptionArray = new JSONArray();
			prescriptionArray.add(0, prescriptionObject);

			paramsObject.put("diagnosis", diagnosisArray);
			paramsObject.put( "hisCode","J2735");
			paramsObject.put("prescriptions", prescriptionArray);

			String params = paramsObject.toString();

			JSONObject materialsObject = new JSONObject();
			materialsObject.put("ffId", 134177);
			materialsObject.put("amount", 1);
			JSONArray materialsArray = new JSONArray();
			materialsArray.add(0, materialsObject);

			JSONObject paramsObject2 = new JSONObject();
			paramsObject2.put("hisCode", "J2735");
			paramsObject2.put("diagnosisId", dianosisId);
			paramsObject2.put("materials", materialsArray);

			String params2 = paramsObject2.toString();

			JsonUtils.printJson(params2);
			JsonUtils.printJson(params);
			HttpUtils.httpPostJson(FITURL, params2);
			HttpUtils.httpPostJson(UPLOADURL, params);
		}
	}
}
