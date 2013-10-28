package com.wzlee.hgm123;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.Connection.Response;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.qq.connect.javabeans.AccessToken;
import com.wzlee.hgm123.controller.SystemController;
import com.wzlee.hgm123.domain.Advertise;
import com.wzlee.hgm123.repositories.AdvertiseRepository;
import com.wzlee.hgm123.utils.DateHelper;
import com.wzlee.hgm123.utils.InputStreamUtil;
import com.wzlee.hgm123.view.MessageResult;

@RunWith(SpringJUnit4ClassRunner.class) 
@ContextConfiguration(locations={
        "file:src/main/webapp/WEB-INF/spring/*.xml", 
        "file:src/main/webapp/WEB-INF/spring/appServlet/*.xml"})
public class JsoupTest {
	
	private static final Logger logger = LoggerFactory.getLogger(JsoupTest.class);
	
	@Autowired
	private AdvertiseRepository advertiseRepository;
	
	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void JsoupData() throws ParseException{
		logger.info("greb data begin...");
		long startMili=System.currentTimeMillis();
		String url = "http://9pk.118sh.com/?=ADTAG=stat";
		int i = 0;
		try {
			Connection conn = Jsoup.connect(url).timeout(100000).maxBodySize(10000000);
			logger.info("Jsoup与["+url+"]建立连接成功!");
//			Document doc = conn.timeout(100000).execute().charset().parse();
			String html = new String(conn.execute().bodyAsBytes(),"gbk");
			Document doc = Jsoup.parse(html);
			logger.info("Jsoup成功获取连接的Document!");
//			Elements scripts = doc.select("script");
//			for(Element script:scripts){
//				System.out.println(script.html());
//			}
//			System.out.println(doc.html());
			Elements tables = doc.select(".tableBorder1");
			for(Element table:tables){
				Elements tbodys = table.select("tbody");
				for(Element tbody:tbodys){
					for(Element tr:tbody.select("tr")){
						if(tr.childNodeSize() == 7){
							Advertise advertise = new Advertise();
							advertise.setGameName(tr.child(0).text().replaceAll(" ", ""));
							advertise.setGameEdition(tr.child(1).text().replaceAll(" ", ""));
							advertise.setOpenTime(tr.child(2).text().replaceAll(" ", ""));
							advertise.setLineType(tr.child(3).text().replaceAll(" ", ""));
							advertise.setGameDescription(tr.child(4).text().replaceAll(" ", ""));
							advertise.setGameUrl(tr.child(6).html());
							advertise.setSource(url);
							advertiseRepository.save(advertise);
//							advertiseRepository.save(new Advertise(null, tr.child(0).text(), tr.child(1).text(), tr.child(2).text(), tr.child(3).text(), tr.child(4).text(), tr.child(6).html(), 0, url));
							i++;
							logger.info("插入第["+i+"]条数据到MongoDB...");
						}
					}
				}
			}
			long endMili=System.currentTimeMillis();
			logger.info("Jsoup成功抓取["+i+"]条数据并插入mongodb,总共耗时：["+(endMili-startMili)/1000+"]秒");
		} catch (IOException e) {
			logger.info("Jsoup与建立连接失败!错误信息:"+e.getLocalizedMessage());
		}
		logger.info("greb data end...");
	}
	
	@Test
	public void grebMail() throws ParseException{
		logger.info("greb email begin...");
		long startMili=System.currentTimeMillis();
		String url = "http://qun.qzone.qq.com/group#!/200052013/member";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		int i = 0;
		try {
			Connection conn = Jsoup.connect(url).timeout(100000).maxBodySize(1000000000);
			Response _response = conn.execute();
			logger.info("Jsoup与["+url+"]建立连接成功!服务器时间:"+DateHelper.date2String(sdf.parse(_response.header("Date")),"yyyy-MM-dd HH:mm:ss"));
			logger.info("Jsoup成功获取连接的Document!");
			Document doc = conn.get();
			logger.info(doc.html());
			Elements texts = doc.select(".members_text");
			for(Element text:texts){
				System.out.println("群名片:"+text.select("a").first().text());
				System.out.println("群角色:"+text.select("p").first().text());
				System.out.println("QQ号码:"+text.select("span").first().text());
			}
			long endMili=System.currentTimeMillis();
			logger.info("Jsoup成功抓取["+i+"]条数据并插入mongodb,总共耗时：["+(endMili-startMili)/1000+"]秒");
		} catch (IOException e) {
			logger.info("Jsoup与["+url+"]建立连接失败!错误信息:"+e.getLocalizedMessage());
		} finally{
			logger.info("greb data end...");
		}
	}
	
	@Test
	public void youkuTest() throws Exception{
		HttpClient httpClient = new HttpClient();
		String uri = "https://openapi.youku.com/v2/oauth2/token";
		PostMethod postMethod = new PostMethod(uri);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setContentCharset("UTF-8");
		Part[] parts = {
				new StringPart("client_id", "bf928cbbd9d41aeb"), 
				new StringPart("client_secret", "4345289e536292a6dba7e4ed540eb7f5"),
				new StringPart("grant_type", "authorization_code"),
				new StringPart("code", "958924e2e4547aafbcf8cd484790f1db"),
				new StringPart("redirect_uri", "http://hgm123.com/yk/oauth")
			};
		postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
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
