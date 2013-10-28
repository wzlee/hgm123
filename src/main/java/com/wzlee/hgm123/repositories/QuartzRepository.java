package com.wzlee.hgm123.repositories;

import java.io.IOException;
import java.io.InputStream;
import java.util.ResourceBundle;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzlee.hgm123.utils.InputStreamUtil;

public class QuartzRepository implements Job {
	private static final Logger logger = LoggerFactory.getLogger(QuartzRepository.class);
	private static String domain = ResourceBundle.getBundle("config").getString("app.domain");
	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		String jobName = jec.getJobDetail().getName();  
		logger.info("QuartzName:" + jobName + "执行中...");
		//构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		//创建GET方法的实例
		GetMethod getMethod = new GetMethod("http://"+domain+"/system/greb");
		//使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,new DefaultHttpMethodRetryHandler());
		getMethod.getParams().setContentCharset("UTF-8");
		try {
			//执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("Http请求[http://"+domain+"/system/greb]请求失败,信息为:"+ getMethod.getStatusLine());
			}
			//读取内容
			InputStream responseBody = getMethod.getResponseBodyAsStream();
			String responseText;
			try {
				responseText = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
				JSONObject jo = JSON.parseObject(responseText);
				if(jo.getBooleanValue("success") == true){
					logger.info("QuartzName:" + jobName + "执行成功...");
				}else{
					logger.info("QuartzName:" + jobName + "执行失败...");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (HttpException e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			logger.info("Http请求[http://"+domain+"/system/greb]发生致命的异常，可能是协议不对或者返回的内容有问题");
			e.printStackTrace();
		} catch (IOException e) {
			//发生网络异常
			e.printStackTrace();
		} finally {
			//释放连接
			getMethod.releaseConnection();
		}
	}

}
