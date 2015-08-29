package com.skg.luohong.base.web.utils;

import java.math.BigDecimal;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 * HttpServletRequest 工具类
 * 
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-28 19:14
 * */
public final class RequestUtils {
	
	/**
	 * Convenience method to get the user ip address
	 * @author 骆宏
	 * */
	public static String getIP(HttpServletRequest req){
		String ip = req.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = req.getRemoteAddr();
		}
		return ip;
	}
	
	/**
	 * Convenience method to get the application's URL based on request
	 * variables.
	 */
	public static String getAppURL(HttpServletRequest request) {
		StringBuffer url = new StringBuffer();
		int port = request.getServerPort();
		if (port < 0) {
			port = 80; // Work around java.net.URL bug
		}
		String scheme = request.getScheme();
		url.append(scheme);
		url.append("://");
		url.append(request.getServerName());
		if ((scheme.equals("http") && (port != 80))
				|| (scheme.equals("https") && (port != 443))) {
			url.append(':');
			url.append(port);
		}
		url.append(request.getContextPath());
		return url.toString();
	}

	/**
	 * Convenience method to get a cookie by name
	 * 
	 * @param request
	 *            the current request
	 * @param name
	 *            the name of the cookie to find
	 * 
	 * @return the cookie (if found), null if not found
	 */
	public static Cookie getCookie(HttpServletRequest request, String name) {
		Cookie[] cookies = request.getCookies();
		Cookie returnCookie = null;

		if (cookies == null) {
			return returnCookie;
		}

		for (int i = 0; i < cookies.length; i++) {
			Cookie thisCookie = cookies[i];
			if (thisCookie.getName().equals(name)) {
				// cookies with no value do me no good!
				if (!thisCookie.getValue().equals("")) {
					returnCookie = thisCookie;

					break;
				}
			}
		}

		return returnCookie;
	}
	
	public static final String getFullRequestUrl(HttpServletRequest req) {
		return (req.getQueryString() == null ? req.getRequestURL() : req
				.getRequestURL().append("?").append(req.getQueryString()))
				.toString();
	}
	
	public static Short getShortParameter(HttpServletRequest request, String paramName) {
		String id = request.getParameter(paramName);
		if (id != null && !id.equals("")) {
			try {
				return new Short(id);
			} catch (Exception e) {
				return null;
			}
		}
		return null;
	}
	
	public static Integer getIntegerParameter(HttpServletRequest request, String paramName) {
		String id = request.getParameter(paramName);
		if (id != null && !id.equals("")) {
			try {
				return new Integer(id);
			} catch (Exception e) {
				return null;
			} 
		}
		return null;
	}
	
	public static BigDecimal getBigDecimalParameter(HttpServletRequest request, String paramName) {
		String id = request.getParameter(paramName);
		if (id != null && !id.equals("")) {
			try {
				return new BigDecimal(id);
			} catch (Exception e) {
				return null;
			} 
		}
		return null;
	}
	
	public static List<Integer> getIntegerParametersToList(HttpServletRequest request,
			String paramName) {
		List<Integer> iIds = new ArrayList<Integer>();
		String[] strIds = request.getParameterValues(paramName);
		if (strIds != null && strIds.length > 0) {			
			for (int i = 0; i < strIds.length; i++) {
				iIds.add(new Integer(strIds[i]));
			}
		}
		return iIds;
	}
	
	
	
	public static String[] getParametersToArray(HttpServletRequest request,
			String paramName) {
		String[] strValues = request.getParameterValues(paramName);
		if(strValues==null){
			strValues=new String[]{};
		}
		return strValues;  
	}

	@SuppressWarnings("unchecked")
	public static Map<String,String[]> getParameterMapByPrefix(HttpServletRequest request,String namePrefix){
		Map<String,String[]> result = new HashMap<String,String[]>();
		Map<String,String[]> map = request.getParameterMap();
		for(Iterator<String> it = map.keySet().iterator(); it.hasNext();){
			String key = it.next();
			if(key.startsWith(namePrefix)){
				String[] value = map.get(key);
				result.put(key, value);
			}						
		}
		return result;
	}
	
	public static List<String> getParametersToList(HttpServletRequest request,
			String paramName) {
		List<String> list = new ArrayList<String>();
		String[] strs = request.getParameterValues(paramName);
		if(strs!=null && strs.length>0){
			for(String id:strs){
				list.add(id);
			}
		}
		return list;  
	}
	
	public static List<Integer> getParametersToIntegerList(HttpServletRequest request,
			String paramName) {
		List<Integer> list = new ArrayList<Integer>();
		String[] strInts = request.getParameterValues(paramName);
		if(strInts!=null && strInts.length>0){
			for(String strInt:strInts){
				list.add(Integer.valueOf(strInt));
			}
		}
		return list;  
	}
	
	public static String getParameterNullSafe(HttpServletRequest request,
			String paramName) {
		try{
			String ret = request.getParameter(paramName);
			if (ret == null) {
				ret = "";
			}
			return ret;	
		}catch(Exception e){
			return "";
		}
	}
	
	/**
	 * 解码中文乱码
	 * 用于http get URL传中文参数（eg：js发送get请求）
	 * js需要对中文参数两次加密
	 * eg:："http://......?name="+encodeURI(encodeURI(name))
	 * @author chenxiaohua
	 * @param request
	 * @param paramName
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String getDecodeParameter(HttpServletRequest request,
			String paramName) {
		try{
			String ret = request.getParameter(paramName);
			if (ret == null) {
				ret = "";
			}else{
				ret = URLDecoder.decode(URLDecoder.decode(ret));
			}
			return ret;	
		}catch(Exception e){
			return "";
		}
	}
	
	
	@SuppressWarnings("unchecked")
	public static Map<String,Object> getRequestMap(final HttpServletRequest request) {
		Map<String, Object> params = new HashMap<String, Object>();		
		
		for(Iterator<String> it = request.getParameterMap().keySet().iterator(); it.hasNext();){
			String key = it.next();
			String[] values = request.getParameterValues(key);
			if (values != null) {  //如果传过来的参数如下：[111111111111100,111111111111111001]，那么将它分割，因为开发中，between时报错
				if (values.length == 1) {
					if(!values[0].contains(",")){
						params.put(key, values[0]);
					}else{						
						params.put(key, values[0].split(","));
					}
				} else {
					params.put(key, values);
				}
			}
		}

		return Collections.unmodifiableMap(params);
	}

	/**
	 * 判断当前的uri是否选择器
	 * @param req
	 * @return
	 */
	public static boolean isSelectorUri(final HttpServletRequest req)
	{
	    return req.getRequestURI().startsWith(req.getContextPath()+"/selector/");
	}

	private RequestUtils() {
	}
}

