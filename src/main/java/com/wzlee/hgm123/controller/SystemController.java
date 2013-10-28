package com.wzlee.hgm123.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.TimeZone;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import sun.org.mozilla.javascript.internal.NativeArray;

import com.wzlee.hgm123.domain.Advertise;
import com.wzlee.hgm123.repositories.AdvertiseRepository;
import com.wzlee.hgm123.utils.DateHelper;
import com.wzlee.hgm123.view.MessageResult;

@Controller
@RequestMapping(value = "/system")
public class SystemController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(SystemController.class);
	
	@Autowired
	private AdvertiseRepository advertiseRepository;
	
	/**
	 * Hack页面
	 * @return
	 */
	@RequestMapping(value = "/hack", method = RequestMethod.GET)
	public String hackTool() {
		logger.info("User the system hack tool...");
		return "hack";
	}
	
	/**
	 * Hack页面
	 * @return
	 */
	@RequestMapping(value = "/dwrtest", method = RequestMethod.GET)
	public String dwrTest() {
		logger.info("The DWR testing...");
		return "dwrtest";
	}
	
	/**
	 * 从网站抓取数据存入数据库
	 * @param response
	 * @throws ScriptException 
	 * @throws ParseException 
	 */
	@SuppressWarnings("restriction")
	@RequestMapping(value = "/greb", method = RequestMethod.GET)
	public void loadData(@RequestParam(value="url",defaultValue="http://9pk.118sh.com/?=ADTAG=stat")String url,HttpServletRequest request,HttpServletResponse response) throws ScriptException, ParseException {
		logger.info("greb data begin...");
		long startMili=System.currentTimeMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		StringBuffer html = new StringBuffer("<html><table><tbody>");
		int i = 0;
		try {
			Connection conn = Jsoup.connect(url).timeout(100000).maxBodySize(1000000000);
			Response _response = conn.execute();
			logger.info("Jsoup与["+url+"]建立连接成功!服务器时间:"+DateHelper.date2String(sdf.parse(_response.header("Date")),"yyyy-MM-dd HH:mm:ss"));
			logger.info("Jsoup成功获取连接的Document!");
			Document doc = Jsoup.parse(new String(_response.bodyAsBytes(),"gbk"));
			ScriptEngineManager semr = new ScriptEngineManager();
			ScriptEngine se = semr.getEngineByName("JavaScript");
			se.eval(doc.select("script").get(2).data().split("theAds2='';")[0]);
			NativeArray theAds = (NativeArray) se.get("theAds");
			for(int j=0;j<theAds.getLength();j++){
				html.append(theAds.get(j, theAds).toString());
			}
			html.append("</tbody></table></html>");
			doc.html(html.toString());
			for(Element tr:doc.select("tr")){
				Advertise advertise = new Advertise();
				advertise.setGameName(tr.child(0).text());
				advertise.setGameEdition(tr.child(1).text());
				advertise.setOpenTime(tr.child(2).text());
				advertise.setLineType(tr.child(3).text());
				advertise.setGameDescription(tr.child(4).text());
				advertise.setGameUrl(tr.child(6).html());
				advertise.setSource(url);
				advertiseRepository.save(advertise);
				i++;
//				logger.info("插入第["+i+"]条数据到MongoDB...");
			}
			long endMili=System.currentTimeMillis();
			logger.info("Jsoup成功抓取["+i+"]条数据并插入mongodb,总共耗时：["+(endMili-startMili)/1000+"]秒");
			this.outJson(response, new MessageResult(true, ""));
		} catch (IOException e) {
			logger.info("Jsoup与["+url+"]建立连接失败!错误信息:"+e.getLocalizedMessage());
			this.outJson(response, new MessageResult(false, ""));
		} finally{
			logger.info("greb data end...");
		}
//		return "redirect:/";
	}
	
	/**
	 * 从网站抓取数据存入数据库
	 * @param response
	 * @throws ScriptException 
	 * @throws ParseException 
	 */
	@RequestMapping(value = "/mail", method = RequestMethod.GET)
	public void grebMail(@RequestParam("qun_no")Long qun_no,HttpServletRequest request,HttpServletResponse response) throws ScriptException, ParseException {
		logger.info("greb email begin...");
		long startMili=System.currentTimeMillis();
		String url = "http://qun.qzone.qq.com/group#!/"+qun_no+"/member";
		SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss 'GMT'", Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		int i = 0;
		try {
			Connection conn = Jsoup.connect(url).timeout(100000).maxBodySize(1000000000);
			Response _response = conn.execute();
			logger.info("Jsoup与["+url+"]建立连接成功!服务器时间:"+DateHelper.date2String(sdf.parse(_response.header("Date")),"yyyy-MM-dd HH:mm:ss"));
			logger.info("Jsoup成功获取连接的Document!");
			Document doc = conn.get();
			Elements texts = doc.select(".members_text");
			for(Element text:texts){
				System.out.println("群名片:"+text.select("a").first().text());
				System.out.println("群角色:"+text.select("p").first().text());
				System.out.println("QQ号码:"+text.select("span").first().text());
			}
			long endMili=System.currentTimeMillis();
			logger.info("Jsoup成功抓取["+i+"]条数据并插入mongodb,总共耗时：["+(endMili-startMili)/1000+"]秒");
			this.outJson(response, new MessageResult(true, ""));
		} catch (IOException e) {
			logger.info("Jsoup与["+url+"]建立连接失败!错误信息:"+e.getLocalizedMessage());
			this.outJson(response, new MessageResult(false, ""));
		} finally{
			logger.info("greb data end...");
		}
//		return "redirect:/";
	}
	
	public String handleProgress(HttpServletRequest request,HttpServletResponse response){
		return request.getSession().getAttribute("process").toString();
	}
	
}
