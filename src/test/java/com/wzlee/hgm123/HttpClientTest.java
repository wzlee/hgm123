package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
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
	
	@SuppressWarnings({ "resource", "deprecation" })
	@Test
	public void youkuTest() throws Exception{
		HttpClient httpClient = new DefaultHttpClient();
		String uri = "https://openapi.youku.com/v2/oauth2/token";
		HttpPost httpPost = new HttpPost(uri);
		List<NameValuePair> params=new ArrayList<NameValuePair>(); 
		params.add(new BasicNameValuePair("client_id","bf928cbbd9d41aeb"));
		params.add(new BasicNameValuePair("client_secret","4345289e536292a6dba7e4ed540eb7f5"));
		params.add(new BasicNameValuePair("grant_type","authorization_code"));
		params.add(new BasicNameValuePair("code","714e59266da7be960e8c2b24f8ee549d"));
		params.add(new BasicNameValuePair("redirect_uri","http://localhost/yk/oauth"));
		httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8")); 
		try {
			HttpResponse response = httpClient.execute(httpPost); 
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				logger.info("请求失败,错误信息:"+response.getStatusLine().getStatusCode());
			}else{
				InputStream responseBody = response.getEntity().getContent();
				String responseString = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
				logger.info(responseString);
				AccessToken accessToken = JSON.parseObject(responseString, AccessToken.class);
				logger.info("获取优酷TOKEN:"+accessToken.toString()+"成功!");
			}
		} catch (HttpException e) {
			logger.info("捕获HTTP异常,错误信息:"+e.getLocalizedMessage());
		} catch (IOException e) {
			logger.info("捕获IO异常,错误信息:"+e.getLocalizedMessage());
		} finally {
			httpPost.releaseConnection();
		}
	}
	
}
