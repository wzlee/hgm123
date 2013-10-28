package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSON;
import com.qq.connect.javabeans.AccessToken;
import com.wzlee.hgm123.utils.InputStreamUtil;

public class HttpClientTest {
	
	private static final Logger logger = LoggerFactory.getLogger(HttpClientTest.class);
	
	@Autowired
//	private static RabbitTemplate rabbitTemplate;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void youkuTest() throws Exception{
		HttpClient httpClient = new HttpClient();
		String uri = "https://openapi.youku.com/v2/oauth2/token";
		PostMethod postMethod = new PostMethod(uri);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setContentCharset("UTF-8");
//		Part[] parts = {
//				new StringPart("client_id", "bf928cbbd9d41aeb"), 
//				new StringPart("client_secret", "4345289e536292a6dba7e4ed540eb7f5"),
//				new StringPart("grant_type", "authorization_code"),
//				new StringPart("code", "958924e2e4547aafbcf8cd484790f1db"),
//				new StringPart("redirect_uri", "http://hgm123.com/yk")
//			};
//		postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
		try {
			int _statusCode = httpClient.executeMethod(postMethod);
			if (_statusCode != HttpStatus.SC_OK) {
				logger.info("请求失败,错误信息:"+postMethod.getStatusLine());
			}else{
				InputStream responseBody = postMethod.getResponseBodyAsStream();
				String responseString = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
				AccessToken accessToken = JSON.parseObject(responseString, AccessToken.class);
				logger.info("获取优酷TOKEN:"+accessToken.toString()+"成功!");
			}
		} catch (HttpException e) {
			logger.info("捕获HTTP异常,错误信息:"+e.getLocalizedMessage());
		} catch (IOException e) {
			logger.info("捕获IO异常,错误信息:"+e.getLocalizedMessage());
		} finally {
			postMethod.releaseConnection();
		}
	}
	
}
