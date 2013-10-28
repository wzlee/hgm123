package com.wzlee.hgm123.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.lingala.zip4j.exception.ZipException;

import org.apache.commons.beanutils.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.wzlee.hgm123.domain.Advertise;
import com.wzlee.hgm123.domain.Gamedata;
import com.wzlee.hgm123.domain.IpData;
import com.wzlee.hgm123.domain.Passport;
import com.wzlee.hgm123.domain.Website;
import com.wzlee.hgm123.repositories.AdvertiseRepository;
import com.wzlee.hgm123.repositories.GamedataRepository;
import com.wzlee.hgm123.repositories.IpDataRepository;
import com.wzlee.hgm123.repositories.WebsiteRepository;
import com.wzlee.hgm123.utils.CompressUtil;
import com.wzlee.hgm123.utils.DateHelper;
import com.wzlee.hgm123.utils.ImageUtil;
import com.wzlee.hgm123.utils.IpHelper;
import com.wzlee.hgm123.view.ListData;
import com.wzlee.hgm123.view.MessageResult;

@Controller
@RequestMapping(value = "/public")
@SessionAttributes("passport")
public class PublicController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(PublicController.class);
	@Autowired
	GridFsOperations operations;
	@Autowired
	private AdvertiseRepository advertiseRepository;
	@Autowired
	private WebsiteRepository websiteRepository;
	@Autowired
	private IpDataRepository ipDataRepository;
	@Autowired
	private GamedataRepository gamedataRepository;
	
	@RequestMapping(value = "/session", method = RequestMethod.GET)
	public void checkSession(@Value(value = "passport") @ModelAttribute("passport") Passport passport,HttpServletRequest request,HttpServletResponse response,BindingResult result) {
		if(result.hasErrors()){
			this.outJson(response, new MessageResult(false,"登陆验证失败!"));
		}else{
			this.outJson(response, new MessageResult(true,"登陆验证成功!"));
		}
//		if(passport == null){
//			this.outJson(response, new MessageResult(false,"登陆验证失败!"));
//		}else{
//			this.outJson(response, new MessageResult(true,"登陆验证成功!"));
//		}
	}
	
	@RequestMapping(value = "/validatecode", method = RequestMethod.GET)
	public void validateCode(HttpServletRequest request,HttpServletResponse response) throws IOException {
		ImageUtil.randCaptcha(request, response);
	}

	@RequestMapping(value = "/passport", method = RequestMethod.POST)
	public void loadPassport(HttpServletRequest request,HttpServletResponse response){
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport==null){
			this.outHTML(response, "<p>会话session已过期,请重新登录!</p>");
		}else{
			String html = "<div class='base-info pull-left'>" +
						   		"<ul>" +
									"<li><span class='info-label'>账号</span>:<span class='info-text'>"+passport.getUpassid()+"</span></li>" +
									"<li><span class='info-label'>昵称</span>:<span class='info-text'>"+passport.getNickname()+"</span></li>" +
									"<li><span class='info-label'>角色</span>:<span class='info-text'>"+passport.getGroupname()+"</span></li>" +
									"<li><span class='info-label'>状态</span>:<span class='info-text'>"+passport.getStatus()+"</span></li>" +
									"<li><span class='info-label'>邮箱</span>:<span class='info-text'>"+passport.getEmail()+"</span></li>" +
									"<li><span class='info-label'>QQ</span>:<span class='info-text'>"+passport.getQq()+"</span></li>" +
								"</ul>" +
							"</div>" +
							"<div class='log-info pull-right'>" +
								"<ul>" +
									"<li><span class='info-label'>注册时间</span>:<span class='info-text'>"+JSON.toJSONStringWithDateFormat(passport.getRegisterdate(), "yyyy-MM-dd HH:mm:ss")+"</span></li>" +
									"<li><span class='info-label'>激活时间</span>:<span class='info-text'>"+JSON.toJSONStringWithDateFormat(passport.getActivedate(), "yyyy-MM-dd HH:mm:ss")+"</span></li>" +
									"<li><span class='info-label'>本次登录时间</span>:<span class='info-text'>"+JSON.toJSONStringWithDateFormat(passport.getLastlogindate(), "yyyy-MM-dd HH:mm:ss")+"</span></li>" +
									"<li><span class='info-label'>上次登录时间</span>:<span class='info-text'>"+JSON.toJSONStringWithDateFormat(passport.getThislogindate(), "yyyy-MM-dd HH:mm:ss")+"</span></li>" +
									"<li><span class='info-label'>本次登录IP</span>:<span class='info-text'>"+passport.getLastloginip()+"</span></li>" +
									"<li><span class='info-label'>上次登录IP</span>:<span class='info-text'>"+passport.getThisloginip()+"</span></li>" +
								"</ul>" +
							"</div>";
			this.outHTML(response, html);
		}
	}
	
	@RequestMapping(value = "/advertise", method = RequestMethod.POST)
	public void loadAdvertise(HttpServletRequest request,HttpServletResponse response){
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport==null){
			this.outHTML(response, "<p>会话session已过期,请重新登录!</p>");
		}else{
			String html = "<table class='table table-bordered'>" +
							"<thead>" +
								"<tr>" +
									"<th width='150'>发布日期</th>" +
									"<th width='160'>游戏名称</th>" +
									"<th width='160'>开区时间</th>" +
									"<th width='60'>人气</th>" +
									"<th width='120'>分享</th>" +
								"</tr>" +
							"</thead>" +
							"<tbody>";
			List<Advertise> advertises = advertiseRepository.findAdvertiseBySource(passport.getUpassid());
			if(advertises.size() == 0){
				html += "<tr>" +
						"<td colspan='5'>对不起,暂无游戏资讯数据...</td>" +
					"</tr>" +
					"</tbody>" +
					"</table>";
 			}else{
 				for(Advertise advertise:advertises){
 					html += "<tr>" +
 							"<td>"+advertise.getDateTime()+"</td>" +
 							"<td><a href='"+advertise.getGameUrl()+"' target='_blank'>"+advertise.getGameName()+"</a></td>" +
 							"<td>"+advertise.getOpenTime()+"</td>" +
 							"<td>"+advertise.getGamePopularity()+"</td>" +
 							"<td><a><img alt='分享QQ空间' src='resources/images/qzone.png'></a> | <a><img alt='分享腾讯微博' src='resources/images/tencent-weibo.png'></a> | <a><img alt='分享新浪微博' src='resources/images/sina-weibo.png'></a></td>" +
 							"</tr>";
 				}
 				html += "</tbody></table>";
			}
			this.outHTML(response, html);
		}
	}
	
	@RequestMapping(value = "/website", method = RequestMethod.POST)
	public void loadWebsite(HttpServletRequest request,HttpServletResponse response){
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport==null){
			this.outHTML(response, "<p>会话session已过期,请重新登录!</p>");
		}else{
			String html = "<div class='item'>" +
							"<table class='table table-bordered'>" +
								"<thead>" +
								"<tr>" +
									"<th width='160'>创建日期</th>" +
									"<th width='100'>创建类型</th>" +
									"<th width='100'>个性域名</th>" +
									"<th width='80'>点击率</th>" +
									"<th width='140'>分享</th>" +
								"</tr>" +
							"</thead>" +
							"<tbody>";
			List<Website> websites = websiteRepository.findWebsiteByCreator(passport.getUpassid());
			if(websites.size() == 0){
				html += "<tr>" +
							"<td colspan='5'>对不起,暂无游戏网站数据...</td>" +
						"</tr>" +
						"</tbody>" +
						"</table>";
				this.outHTML(response, html);
 			}else{
 				for(Website website:websites){
 					html += "<tr>" +
	 							"<td>"+website.getCreatedate()+"</td>" +
	 							"<td>"+website.getType()+"</td>" +
	 							"<td><a href='http://"+website.getSld()+".hgm8.com'>"+website.getSld()+".hgm8.com"+"</a></td>" +
	 							"<td>"+website.getVisitors()+"</td>" +
	 							"<td><a><img alt='分享QQ空间' src='resources/images/qzone.png'></a> | <a><img alt='分享腾讯微博' src='resources/images/tencent-weibo.png'></a> | <a><img alt='分享新浪微博' src='resources/images/sina-weibo.png'></a></td>" +
 							"</tr>";
 				}
 				html += "</tbody></table>";
 				this.outHTML(response, html);
			}
		}
	}
	
	@RequestMapping(value = "/bindinfo", method = RequestMethod.POST)
	public void loadBindinfo(HttpServletRequest request,HttpServletResponse response){
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport==null){
			this.outHTML(response, "<p>会话session已过期,请重新登录!</p>");
		}else{
			String BIND_QQ = "";
			String BIND_TW = "";
			String BIND_SW = "";
			if(passport.getQqOpenid() == null){
				BIND_QQ = "<a href='qqbind'><img class='img-polaroid' src='resources/images/qq_l.png'></a>";
			}else{
				BIND_QQ = "<img class='img-polaroid' src='"+passport.getQqAvatar()+"' alt='"+passport.getQqNick()+"'>";
			}
			if(passport.getTwOpenid() == null){
				BIND_TW = "<a href='twbind'><img class='img-polaroid' src='resources/images/tencent-weibo_l.png'></a>";
			}else{
				BIND_TW = "<img class='img-polaroid' src='"+passport.getTwAvatar()+"' alt='"+passport.getTwNick()+"'>";
			}
			if(passport.getSwOpenid() == null){
				BIND_SW = "<a href='swbind'><img class='img-polaroid' src='resources/images/sina-weibo_l.png'></a>";
			}else{
				BIND_SW = "<img class='img-polaroid' src='"+passport.getSwAvatar()+"' alt='"+passport.getSwNick()+"'>";
			}
			String html = "<div class='pull-left third-bind l-25'>" +
								"<div class='pull-left qq-bind'>" + 
									BIND_QQ +
								"</div>" +
								"<div class='pull-left tw-bind'>" +
									BIND_TW +
								"</div>" +
								"<div class='pull-left sw-bind'>" +
									BIND_SW +
								"</div>" +
							"</div>";
			this.outHTML(response, html);
		}
	}
	
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	public void loadData(@RequestParam("querytime")String querytime,HttpServletRequest request,HttpServletResponse response) {
		logger.info("request from "+request.getRemoteHost()+" loading the ["+querytime+"] advertise data from mongo begin...");
		List<Advertise> advertises = advertiseRepository.findAdvertiseByOpenTime(querytime);
		this.outJson(response, new ListData<Advertise>(true, advertises));
	}
	
	@RequestMapping(value = "/tongji", method = RequestMethod.GET)
	public String tongJi(HttpServletRequest request,HttpServletResponse response) {
		return "statistic";
	}
	
	@RequestMapping(value = "/ipdata", method = RequestMethod.POST)
	public void ipData(HttpServletRequest request,HttpServletResponse response) {
		List<IpData> ipdatas = ipDataRepository.findAll();
		this.outJson(response, new ListData<IpData>(true, ipdatas));
	}
	
	@RequestMapping(value = "/count", method = RequestMethod.POST)
	public void countLink(@RequestParam("id")String id,HttpServletRequest request,HttpServletResponse response) {
		logger.info("player from "+request.getRemoteAddr()+" click the advertise's url");
		try {
			Advertise advertise = advertiseRepository.findOne(id);
			advertise.setGamePopularity(advertise.getGamePopularity() + 1);
			advertiseRepository.save(advertise);
			IpData ipdata = IpHelper.queryIpData(request.getRemoteAddr());
			ipdata.setPasspord_id(request.getSession().getAttribute("passport")==null?request.getSession().getId():((Passport)request.getSession().getAttribute("passport")).getId());
			ipdata.setAdvertise_id(advertise.getId());
			ipdata.setRecord_date(DateHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss"));
			ipDataRepository.save(ipdata);
			this.outJson(response, new MessageResult(true, "ip记录成功!"));
		} catch (Exception e) {
			this.outJson(response, new MessageResult(false, e.getLocalizedMessage()));
		}
	}
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public void publishAdvertise(HttpServletRequest request,HttpServletResponse response) {
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport == null){
			this.outJson(response, new MessageResult(false,"session未创建或已过期,请重新登录!"));
		}else{
			Advertise advertise = new Advertise();
			try {
				BeanUtils.populate(advertise,request.getParameterMap());
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			} catch (InvocationTargetException e1) {
				e1.printStackTrace();
			}
			advertise.setSource(passport.getUpassid());
			advertiseRepository.save(advertise);
			gamedataRepository.save(new Gamedata(null,passport.getUpassid(),advertise.getId(),"发布资讯",advertise.getGameName(),DateHelper.date2String("yyyy-MM-dd HH:mm:ss")));
			this.outJson(response, new MessageResult(true,"发布成功!"));
		}
	}
	
	@RequestMapping(value = "/sld", method = RequestMethod.POST)
	public void checkDomain(@RequestParam("sld")String sld,HttpServletRequest request,HttpServletResponse response) {
		List<Website> websites = websiteRepository.findWebsiteBySld(sld);
		if(websites.size() == 0){
			this.outJson(response, new MessageResult(true,"个性域名可用!"));
		}else{
			this.outJson(response, new MessageResult(false,"域名已被使用!"));
		}
	}
	
	@RequestMapping(value = "/template", method = RequestMethod.POST)
	public void userTemplate(@RequestParam("sld")String sld,@RequestParam("type")String type,@RequestParam("title")String title,@RequestParam("templateCode")String templateCode,@RequestParam MultipartFile serverList,HttpServletRequest request,HttpServletResponse response) {
		String md5_serverList = "";
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport == null){
			this.outJson(response, new MessageResult(false,"session未创建或已过期,请重新登录!"));
		}else{
			try {
				md5_serverList = operations.store(serverList.getInputStream(),sld+".txt").getMD5();
			} catch (IOException e) {
				logger.info(e.getLocalizedMessage());
			}
			Website website = websiteRepository.save(new Website(null,type,sld,null,templateCode,title,md5_serverList,passport.getUpassid(),DateHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss")));
			gamedataRepository.save(new Gamedata(null,passport.getUpassid(),website.getId(),"发布资讯",website.getSld()+".hgm8.com",DateHelper.date2String(new Date(), "yyyy-MM-dd HH:mm:ss")));
			logger.info("["+sld+".hgm8.com]创建成功!");
			this.outJson(response, new MessageResult(true,"网站创建成功!"));
		}
	}
	
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public void uploadCode(@RequestParam("sld")String sld,@RequestParam("type")String type,@RequestParam MultipartFile serverList,@RequestParam MultipartFile codePackage,HttpServletRequest request,HttpServletResponse response) {
		String md5_serverList = "";
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport == null){
			this.outJson(response, new MessageResult(false,"session未创建或已过期,请重新登录!"));
		}else{
			try {
				md5_serverList = operations.store(serverList.getInputStream(),sld+".txt").getMD5();
			} catch (IOException e) {
				logger.info(e.getLocalizedMessage());
			}
			String uploadPath = "/WEB-INF/views/website/code/"+sld+"/";
			String contextPath = request.getSession().getServletContext().getRealPath(uploadPath);
			File filepath = new File(contextPath);
			if (!filepath.exists()) {
				filepath.mkdir();
				logger.info("["+filepath.getAbsolutePath()+"]创建成功!");
			}
			File _codePackage = new File(contextPath,codePackage.getOriginalFilename());
			try {
				codePackage.transferTo(_codePackage);
			} catch (IllegalStateException e1) {
				logger.info("IllegalStateException异常:"+e1.getLocalizedMessage());
			} catch (IOException e1) {
				logger.info("IOException异常:"+e1.getLocalizedMessage());
			}
			try {
				System.out.println(_codePackage.getAbsolutePath());
				File[] files = CompressUtil.unzip(_codePackage.getAbsolutePath(),"abc");
				for (int i = 0; i < files.length; i++) {
					logger.info("正在解压:"+files[i]);
				}
			} catch (ZipException e) {
				this.outJson(response, new MessageResult(false,e.getLocalizedMessage()));
			}
			Website website = websiteRepository.save(new Website(null,type,sld,filepath.getAbsolutePath(),null,null,md5_serverList,passport.getUpassid(),DateHelper.date2String("yyyy-MM-dd HH:mm:ss")));
			gamedataRepository.save(new Gamedata(null,passport.getUpassid(),website.getId(),"发布资讯",website.getSld()+".hgm8.com",DateHelper.date2String("yyyy-MM-dd HH:mm:ss")));
			logger.info("["+sld+".hgm123.com]代码上传成功!");
			this.outJson(response, new MessageResult(true,"网站上传成功!"));
		}
	}
}
