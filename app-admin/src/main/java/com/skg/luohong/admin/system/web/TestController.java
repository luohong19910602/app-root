package com.skg.luohong.admin.system.web;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.skg.luohong.base.web.query.RequestSqlParamBuilder;
import com.skg.luohong.base.web.utils.HttpClientUtil;

/**
 * 添加测试
 * 
 * @author 骆宏 846705189@qq.com
 * @date 2015-08-28 21:29
 * */
@Controller
public class TestController {
    
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(HttpServletRequest req){
    	try {
			req.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new RequestSqlParamBuilder(req).buildSqlParam().toString();
    }
	
	/**
	 * 查询构造器的测试
	 * */
	public static void main(String[] args) throws Exception {
		Map<String, String> paramMap = new HashMap<String, String>();
		
		//query string
		paramMap.put("Q__STRING__LIKE__account_", "account");
		paramMap.put("Q__STRING__EQ__title_", "title");
		paramMap.put("Q__STRING__NQ__content_", "content");
		
		//query date
		paramMap.put("Q__DATE__EQ__create_time_", "2015-06-06 12:12:12");
		paramMap.put("Q__DATE__GT__update_time_", "2015-06-06 12:12:12");
		paramMap.put("Q__DATE__LT__delete_time_", "2015-06-06 12:12:12");
		paramMap.put("Q__DATE__BW__haha_time_", "[2015-06-06 12:12:12,2015-06-07 12:12:12]");
		
		//query int
		paramMap.put("Q__NUMBER__EQ__age_", "25");
		paramMap.put("Q__NUMBER__NQ__day_", "28");
		paramMap.put("Q__NUMBER__GT__month_", "12");
		paramMap.put("Q__NUMBER__LT__year_", "2015");
		
		
		//multiple table
		paramMap.put("Q__STRING__LIKE__table.hello_", "hello");
		
		//page
		paramMap.put(RequestSqlParamBuilder.LIMIT_PARAM_NAME, "20");
		paramMap.put(RequestSqlParamBuilder.PAGE_PARAM_NAME, "5");
		
		//order
		paramMap.put("O__ASC", "name_");
		paramMap.put("O__DESC", "age_");
		
		
		HttpEntity entity = HttpClientUtil.sendGetRequest("http://127.0.0.1:8080/app-admin/list.do", paramMap);
		InputStream inStream = entity.getContent();
		String result = "";
		byte[] b = new byte[2048];
		
		while(inStream.read(b) != -1){
			result += new String(b);
		}
		System.out.println(result);
	}
}
