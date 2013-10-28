package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.io.InputStream;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

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
		PostMethod post = new PostMethod("https://openapi.youku.com/v2/oauth2/token");
        NameValuePair[] data = {
          new NameValuePair("client_id", "bf928cbbd9d41aeb"),
          new NameValuePair("client_secret", "4345289e536292a6dba7e4ed540eb7f5"),
          new NameValuePair("grant_type", "authorization_code"),
          new NameValuePair("code", "a54f6fc6183e5f97a8480f67014e9f4f"),
          new NameValuePair("redirect_uri", "http://localhost/yk/oauth")
        };
        post.setRequestBody(data);
        httpClient.executeMethod(post);
        InputStream in = post.getResponseBodyAsStream();
        String responseString = InputStreamUtil.InputStreamTOString(in, "UTF-8");
		logger.info(responseString);
	}
	
}
