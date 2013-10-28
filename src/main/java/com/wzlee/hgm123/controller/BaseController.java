package com.wzlee.hgm123.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.wzlee.hgm123.exception.BusinessException;
import com.wzlee.hgm123.exception.ParameterException;

import com.alibaba.fastjson.JSON;

public class BaseController {
	/** 基于@ExceptionHandler异常处理 */
	//(value = { BusinessException.class, ParameterException.class, Exception.class})
	@ExceptionHandler
	public String exp(HttpServletRequest request, Exception ex) {
		request.setAttribute("ex", ex);
		if(ex instanceof BusinessException) {
			return "error/business";
		}else if(ex instanceof ParameterException) {
			return "error/parameter";
		} else {
			return "error/error";
		}
	}
	
	public void outHTML(HttpServletResponse response,String html) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(html);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
    public void outHTMLP(HttpServletResponse response,String callback,String html) {
		try {
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback+"("+html+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	public void outJson(HttpServletResponse response,Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(json);
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 输出JSONP(跨域响应)
	 * @param object
	 */
	public void outJsonP(HttpServletResponse response,String callback,Object object) {
		try {
			String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss");
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(callback+"("+json+")");
			response.getWriter().flush();
			response.getWriter().close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
