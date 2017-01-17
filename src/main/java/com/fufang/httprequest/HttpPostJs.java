package com.fufang.httprequest;

import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStreamWriter;  
import java.net.HttpURLConnection;  
import java.net.URL;
  
public class HttpPostJs {  
  
    final static String url = "http://172.16.87.194:9999/organization-manager/warnSet/saveOrUpdate";  
    final static String params = "{\"warnBeanList\":[{\"id\":\"348\",\"warnType\":\"32\",\"warnGspType\":0,\"licenseId\":\"66666666-37C7-4E68-B7D0-43A39BA44C8B\",\"isOn\":1,\"dayNum\":10,\"maintainDay\":10,\"roles\":\"ABCD1234-067E-4CAA-ACF0-12DFCDF7B8D3,B6E58291-0A5B-49A3-B379-19DAC845123F\"}]}";

    /** 
     * 发送HttpPost请求
     *  
     *  
     * @param strURL 
     *            服务地址 
     * @param params 
     *            json�字符串,例如: "{ \"id\":\"12345\" }" ;属性名需要带双引号<br/> 
     * @return 成功:返回json字符串<br/> 
     */  
    public static String post(String testUrl, String params) {  
        System.out.println("接口地址："+ testUrl);  
        System.out.println("入参："+ params);  
        try {  
            URL url = new URL(testUrl);// 创建链接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();  
            connection.setDoOutput(true);  
            connection.setDoInput(true);  
            connection.setUseCaches(false);  
            connection.setInstanceFollowRedirects(true);  
            connection.setRequestMethod("POST"); // 设置请求方法 
            connection.setRequestProperty("Accept", "application/json;charset=utf8"); // 接收数据格式 
            connection.setRequestProperty("Content-Type", "application/json"); // 发送数据格式
            connection.connect();  
            OutputStreamWriter out = new OutputStreamWriter(  
                    connection.getOutputStream(), "UTF-8"); // utf-8编码 
            out.append(params);  
            out.flush();  
            out.close();  
            // 读取相应
            int length = (int) connection.getContentLength();// 获取长度
            InputStream is = connection.getInputStream();  
            if (length != -1) {  
                byte[] data = new byte[length];  
                byte[] temp = new byte[512];  
                int readLen = 0;  
                int destPos = 0;  
                while ((readLen = is.read(temp)) > 0) {  
                    System.arraycopy(temp, 0, data, destPos, readLen);  
                    destPos += readLen;  
                }  
                String result = new String(data, "UTF-8"); // utf-8编码
                System.out.println("返回值：" + result);  
                return result;  
            } 
              } catch (IOException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }  
        return "error"; // 自定义错误信息 
    }  
  
    public static void main(String[] args) {  
        post(url, params);  
    }  
}
  


/*
httpUrlConnection.setDoOutput(true);以后就可以使用conn.getOutputStream().write() 
httpUrlConnection.setDoInput(true);以后就可以使用conn.getInputStream().read(); 

get请求用不到conn.getOutputStream()，因为参数直接追加在地址后面，因此默认是false。 
post请求（比如：文件上传）需要往服务区传输大量的数据，这些数据是放在http的body里面的，因此需要在建立连接以后，往服务端写数据。 

因为总是使用conn.getInputStream()获取服务端的响应，因此默认值是true。
*/

 