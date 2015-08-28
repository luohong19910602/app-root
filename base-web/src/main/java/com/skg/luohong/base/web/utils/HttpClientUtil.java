package com.skg.luohong.base.web.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * 注意：HttpPost请求不能处理重定向转发事件。需要手动的去做。
 * 如果要模拟登陆后的操作，则在请求中加入JSESSIONID就可以了
 * httpGet.setHeader("Cookie" , "JSESSIONID=432423432907427987EHLJ42"); 就可以了
 * @author Easin
 * @date   2014年9月25日
 */
public class HttpClientUtil {  
    
    /** 
     * 发送GET请求，携带参数 
     * @param url 
     * @param params 
     * @return   
     * @throws Exception 
     */  
    @SuppressWarnings({ "deprecation", "resource" })
	public static HttpEntity sendGetRequest(String url , Map<String , String> params ) throws Exception{  
        final StringBuffer tmp  = new StringBuffer(url); ;  
        if(params != null){  
            Set<Entry<String, String>> paramset = params.entrySet();  
            if(paramset.size() > 0){  
                tmp.append("?");  
                int totalLen = paramset.size() , index = 0 ;  
                for (Entry<String, String> entry : paramset) {
                    String val=URLEncoder.encode(StringUtils.isNotBlank(entry.getValue())?entry.getValue().toString():"","utf-8");
                    tmp.append(entry.getKey() + "=" +val);
                    if(++index <  totalLen){
                        tmp.append("&");   
                    }  
                }  
            }  
        }  
        HttpClient httpClient = new DefaultHttpClient();  
        HttpGet httpGet = new HttpGet(tmp.toString());  
        System.err.println(tmp.toString());
        HttpResponse response = httpClient.execute(httpGet);  
        return response.getEntity();  
    }  
  
  

    /**
     * 发送POST请求
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "deprecation", "resource" })
	public static HttpEntity sendPostRequest(String url , Map<String , String> params) throws Exception{  
        HttpClient httpClient = new DefaultHttpClient();  
        List<NameValuePair> listParams = new ArrayList<NameValuePair>();  
        if(params != null){  
            Set<Map.Entry<String, String>> set = params.entrySet();  
            for (Entry<String, String> entry : set)
            {
//                String val=URLEncoder.encode(ObjectUtils.isNotBlank(entry.getValue())?entry.getValue().toString():"","utf-8");
                listParams.add( new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }  
        }  
        HttpPost httpPost = new HttpPost(url);    
        httpPost.setEntity(new UrlEncodedFormEntity(listParams , "utf-8"));  
        HttpResponse reponse = httpClient.execute(httpPost) ;       
          
        return  reponse.getEntity();  
    }  
  
    /** 
     * 下载文件 
     * @param path  文件URL 
     * @param savePath  保存路径 
     * @param fileName  文件名 
     */  
    @SuppressWarnings({ "deprecation", "resource" })
	public static void downloadFile(String path , String savePath , String fileName) throws Exception{  
        HttpClient httpClient = new DefaultHttpClient();  
        HttpGet httpGet = new HttpGet(path);  
        HttpResponse response = httpClient.execute(httpGet);  
        File dir = new File(savePath);  
        if(!dir.exists())dir.mkdirs();  
        File saveFile = new File(dir , fileName);  
        BufferedInputStream  bis = new BufferedInputStream(response.getEntity().getContent());  
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(saveFile));  
        byte tmp[] = new byte[1024];  
        int len = 0 ;  
        while((len = bis.read(tmp)) != -1){   
            bos.write(tmp, 0 , len);  
        }  
        bis.close();  
        bos.flush() ; bos.close();  
    }  
      
  
    /** 
     * 利用jdk提供的HttpUrlConnect发起Post请求 
     * @return   
     */  
    public static InputStream sendPostRequestByUrlConnect(String path , Map<String , String> params)throws Exception{  
        URL url = new URL(path);  
        HttpURLConnection hul = (HttpURLConnection) url.openConnection();  
        hul.setRequestMethod("POST");  
        hul.setDoOutput(true);   //post 方式     
        hul.setDoInput(true);     
        if(params != null){  
            Set<Entry<String, String>> paramSet = params.entrySet() ;           
            //加入参数  
            StringBuilder paramsStr = new StringBuilder();  
            String paramss = null;  
            for (Entry<String, String> entry : paramSet) {       
                paramsStr.append(entry.getKey()).append("=").append(entry.getValue()).append("&");  
            }      
            if(paramsStr.length() > 0)  
                paramss = paramsStr.substring(0, paramsStr.length() - 1);    
            hul.getOutputStream().write(paramss.getBytes());     
        }  
        hul.getOutputStream().flush();  
        hul.getOutputStream().close();  
        return hul.getInputStream();  
    }  
  
    /** 
     * 将输入流转换为字符串 
     * @param is 
     * @return 
     * @throws Exception 
     */  
    public static String inputToString(InputStream is)throws Exception{  
        StringBuffer sb = new StringBuffer("");  
        String line = null;  
        BufferedReader br = new BufferedReader(new InputStreamReader(is));  
        while((line = br.readLine()) != null){  
            sb.append(line + "\r\n");  
        }  
        return sb.toString();  
    }  
  
    /** 
     * 将HttpEntity对象转换为字符串 
     * @param entity 
     * @return 
     * @throws Exception 
     */  
    public static String entityToString(HttpEntity entity) throws Exception{  
        return entity == null ?   
                null:EntityUtils.toString(entity , "utf-8");   
    }  
    public static String translate(String text,String from,String to){  
    	String result=""; 
    	String url="http://translate.google.cn/translate_a/single?client=t&sl=zh-CN&tl=en&hl=zh-CN&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&source=btn&ssel=3&tsel=3&kc=0&tk=520406150839&q="+text;
    	
     	 //去掉了竖线才是ok的，因为特殊字符会报错
     	 try
         {
     		 //url=URLEncoder.encode(url,"UTF-8");
     		 HttpEntity entity = HttpClientUtil.sendGetRequest(url,null);
 	         result = HttpClientUtil.entityToString(entity);
         } catch (Exception e)
         {
 	        e.printStackTrace();
         } 
     	 return result;
    }  
  
    public static void main(String[] args)
    {
    	
//    	QaAySA65YCzKsReiCZuInuG5

//    	http://openapi.baidu.com/public/2.0/bmt/translate?client_id=QaAySA65YCzKsReiCZuInuG5&q=today&from=auto&to=auto
    	 String url="http://openapi.baidu.com/public/2.0/bmt/translate?client_id=QaAySA65YCzKsReiCZuInuG5&q=today&from=en&to=zh";
//		try
//        {
//			url="http://translate.google.cn/translate_a/single?client=t&sl=en&tl=zh-CN&hl=zh-CN&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&otf=1&ssel=0&tsel=0&kc=1&tk=520406|150839&q=There%20are%20%20items%20in%20your%20cart.";
//	     	
//	        HttpEntity entity = HttpClientUtils.sendPostRequest(url, null);
//	         String result = HttpClientUtils.entityToString(entity);
//	     	System.err.println(HttpClientUtils.entityToString(entity));
////	         System.err.println(result);
////	         System.err.println(((List)JSONObject.parseObject(result).get("trans_result")).get(0)); ;
//        } catch (Exception e)
//        {
//	        e.printStackTrace();
//        }
		
    	 url="http://translate.google.cn/translate_a/single?client=t&sl=zh-CN&tl=en&hl=zh-CN&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&dt=at&ie=UTF-8&oe=UTF-8&source=btn&ssel=3&tsel=3&kc=0&tk=520406150839&q=%E6%98%AF%E7%9A%84";
    	//去掉了竖线才是ok的，因为特殊字符会报错
    	 try
        {
	        HttpEntity entity = HttpClientUtil.sendGetRequest(url,null);
	         String result = HttpClientUtil.entityToString(entity);
	         System.err.println(result);
        } catch (Exception e)
        {
	        e.printStackTrace();
        }
    }
    
    
  
}    
