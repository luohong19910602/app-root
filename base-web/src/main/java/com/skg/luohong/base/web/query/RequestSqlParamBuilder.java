package com.skg.luohong.base.web.query;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import com.skg.luohong.base.core.page.DefaultPage;
import com.skg.luohong.base.core.page.IPage;
import com.skg.luohong.base.db.dao.ICondition;
import com.skg.luohong.base.db.dao.mybatis.Condition;
import com.skg.luohong.base.db.dao.mybatis.DefaultSqlParamBuilder;
import com.skg.luohong.base.web.utils.RequestUtils;

/**
 * 从request请求中获取查询条件
 * 这里面主要目的是：直接从request获取所有的查询条件，然后构建mybatis的查询语句，
 * 这里面包含where , order by, limit三部分的内容
 * 
 * 如何直接从request对象中构建SqlParam呢？
 * 1.取出所有的参数
 * 2.分别whereSql,orderSql, limitSql
 *
 * 如何构建whereSql
 * 1.取出所有的where查询条件
 * 2.获取查询操作：or，and
 * 3.根据查询的参数，参数类型，执行操作来构建sql
 * 4.得到所有的查询语句
 * 
 * 在查询中，由于涉及到关联表的查询，以及查询的多样性，所以需要加入一些特殊的字符串，用于解决该问题
 * 这里面约定使用如下格式来传入查询条件的参数名：Q__TYPE__OP__NAME
 * 
 * 其中Q：为前缀，代表查询
 * TYPE：为类型，代表该参数对应的数据库类型（主要有三种类型，NUMBER，STRING，DATE）
 * OP：为操作符，每种类型的操作符有所差异
 * NAME：对应数据库字段
 * 
 * TYPE为STRING的操作符：EQ,NQ,LIKE
 * TYPE为DATE的操作符：EQ,GT,LT,BW
 * TYPE为NUMBER的操作符：EQ,NQ,LT,GT
 * 
 * 下面给出几个基本的示例：
 * STRING
 * Q__STRING__LIKE__name_:骆宏  //name_包含骆宏
 * Q__STRING__EQ__account_:luohong   //account_等于luohong
 * Q__STRING__NQ__content_:luohong   //content_不等于luohong
 * 
 * DATE
 * Q__DATE__BW__create_time_:[2015-06-06,2016-09-09]  //create_time_在[2015-06-06,2016-09-09]内
 * Q__DATE_GT__create__time_:2015-06-06  //create_time_大于等于2015-06-06
 * Q__DATE__LT__create__time_:2015-06-06  //create_time_小于等于2015-06-06
 * 
 * NUMBER
 * Q__NUMBER__EQ__age_:10  //age_等于10
 * Q__NUMBER__NQ__age_:10  //age_不等于10
 * Q__NUMBER__GT__age_:10  //age_大于等于10
 * Q__NUMBER__LT__age_:10  //age_小于等于10
 * 
 * 具体的定义可以查看@see ICondition
 * NAME：为参数名，与数据库字段一一对应
 *
 * 如何构建orderSql
 * 1.取出所有的order参数
 * 2.根据order参数来构建sql
 * 这里面支持多个排序，每个排序格式如下
 * O__TYPE:NAME
 * 
 * O为排序前缀
 * TYPE为排序的类型，有ASC,DESC两种
 * NAME为数据库字段名
 *
 * 如何构建limitSql 
 * 1.取出翻页对象信息
 * 2.根据翻页对象信息来构建分页limitSql
 *
 * 
 * @author 骆宏 15013336884 846705189@qq.com
 * @date 2015-08-28 15:43
 * */
public class RequestSqlParamBuilder extends DefaultSqlParamBuilder {
	/**
	 * 查询前缀
	 * */
	public static final String QUERY_PREFIX = "Q_"; 
	/**
	 * 排序前缀
	 * */
	public static final String ORDER_PREFIX = "O_"; 
	
	/**
	 * 当前页码参数名
	 * */
	public static final String PAGE_PARAM_NAME = "page";   
	
	/**
	 * 显示记录数参数名
	 * */
	public static final String LIMIT_PARAM_NAME = "limit";
	
	private String pageParamName = PAGE_PARAM_NAME;
	private String limitParamName = LIMIT_PARAM_NAME;
	
	/**
	 * 设置当前页码的参数名字
	 * @param name
	 * */
	public void setPageParamName(String name){
		this.pageParamName = name;
	}
	
	/**
	 * 设置显示记录数参数名
	 * @param name
	 * */
	public void setLimitParamName(String name){
		this.limitParamName = name;
	}
	
	public String getPageParamName(){
		return pageParamName;
	}
	
	public String getLimitParamName(){
		return limitParamName;
	}
	
	public RequestSqlParamBuilder(){
        
	}
	
	/**
	 * @param req 当前请求对象
	 * */
	public RequestSqlParamBuilder(HttpServletRequest req){
        parse(req);
	}

	@SuppressWarnings("unchecked")
	private void parse(HttpServletRequest req) {
		Enumeration<String> params = req.getParameterNames();
		
		int limit = 0;
		int pageNum = 0;
		
		while(params.hasMoreElements()){
			String param = params.nextElement();
			Object value = req.getParameter(param);
			
			if(value == null) continue;
			
			if(param.startsWith(QUERY_PREFIX)){
				parseQuery(param, value);
			}else if(param.startsWith(ORDER_PREFIX)){
				parsetOrder(param, (String)value);
			}else if(param.equals(pageParamName)){
				pageNum = RequestUtils.getIntegerParameter(req, pageParamName);
			}else if(param.equals(limitParamName)){
				limit = RequestUtils.getIntegerParameter(req, limitParamName);
			}else{
				//ignore other parameters
			}
		}
		
		IPage page = new DefaultPage();
		if(limit != 0){
			page.setLimit(limit);
		}
		if(pageNum != 0){
			page.setPageNumber(pageNum);
		}
		super.setPage(page);
	}
    
	/**
	 * 处理一个排序
	 * */
	private void parsetOrder(String param, String value){
		
		String[] temp = param.split("__");
		
		
		String type = temp[1];  //类型
		String name = value;
		addOrder(name, type.toLowerCase());
	}
	
	/**
	 * 处理一个查询条件
	 * */
	private void parseQuery(String param, Object value) {
	    param = param.toLowerCase();
	    
		String[] temp = param.split("__");
		
		String type = temp[1];
		String op = temp[2];
		String name = temp[3];
		
		if(type.equalsIgnoreCase(ICondition.DATE_TYPE) && op.equalsIgnoreCase(ICondition.DateOpType.BW)){
			addCondition(new Condition(name, op, value, type));
		}else{
			addCondition(new Condition(name, op, value, type));
		}
	}
}
