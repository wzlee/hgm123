package com.wzlee.hgm123.controller;

import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.HtmlEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wzlee.hgm123.domain.Passport;
import com.wzlee.hgm123.repositories.PassportRepository;
import com.wzlee.hgm123.view.MessageResult;
import com.wzlee.hgm123.view.VerifyResult;

@Controller
@RequestMapping(value = "/passport")
public class PassportController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(PassportController.class);
	
	@Autowired
	private PassportRepository passportRepository;
	
	@RequestMapping(value = "/load", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject loadPassport(HttpServletRequest request,HttpServletResponse response){
		JSONObject jo = new JSONObject();
		try {
			jo.put("Result","OK");
			jo.put("Records", passportRepository.findAll());
		} catch (Exception e) {
			jo.put("Result","ERROR");
			jo.put("Message", e.getLocalizedMessage());
		}
		return jo;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addPassport(@ModelAttribute Passport passport, BindingResult result) {
		JSONObject jo = new JSONObject();
	    if (result.hasErrors()) {
			jo.put("Result","ERROR");
			jo.put("Message", "BindingResult:result has errors!");
	    }
	    try {
	    	jo.put("Result","OK");
			jo.put("Record",passportRepository.save(passport));
	    } catch (Exception e) {
	    	jo.put("Result","ERROR");
			jo.put("Message", e.getLocalizedMessage());
	    }
	    return jo;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject updatePassport(@ModelAttribute Passport passport, BindingResult result) {
		JSONObject jo = new JSONObject();
	    if (result.hasErrors()) {
			jo.put("Result","ERROR");
			jo.put("Message", "BindingResult:result has errors!");
	    }
	    try {
	    	passportRepository.save(passport);
	    	jo.put("Result","OK");
	    } catch (Exception e) {
	    	jo.put("Result","ERROR");
			jo.put("Message", e.getLocalizedMessage());
	    }
	    return jo;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deletePassport(@RequestParam String id) {
		JSONObject jo = new JSONObject();
	    try {
	    	passportRepository.delete(id);
	    	jo.put("Result","OK");
	    } catch (Exception e) {
	    	jo.put("Result","ERROR");
			jo.put("Message", e.getLocalizedMessage());
	    }
	    return jo;
	}
	
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public void checkPassport(@RequestParam("upassid")String upassid,HttpServletRequest request,HttpServletResponse response) {
		List<Passport> passports = passportRepository.findPassportByUpassid(upassid);
		if(passports.size() == 0){
			this.outJson(response, new MessageResult(true, "恭喜,帐号可用!"));
		}else{
			this.outJson(response, new MessageResult(false, "帐号已存在!"));
		}
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.POST)
	public void regPassport(HttpServletRequest request,HttpServletResponse response) {
		Passport passport = new Passport();
		try {
			BeanUtils.populate(passport,request.getParameterMap());
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
		try {
			passportRepository.save(passport);
			this.outJson(response, new MessageResult(true,"注册成功!"));
			
			Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.HOUR, 1);
			Date expiry = calendar.getTime();
			
			HtmlEmail hgm123_mail = new HtmlEmail();
//			hgm123_mail.setDebug(true);
			hgm123_mail.setCharset("utf-8");
			hgm123_mail.setHostName("smtp.exmail.qq.com");
			hgm123_mail.setSSLOnConnect(true);
			hgm123_mail.setAuthenticator(new DefaultAuthenticator("admin@hgm123.com", "loveu1314"));
			hgm123_mail.addTo(passport.getEmail());
			hgm123_mail.setFrom("admin@hgm123.com","hgm123管理员");
			hgm123_mail.setSubject("Hgm123通行证激活邮件<系统自动发出,请勿回复>");
			
			URL logo_url = new URL("http://www.hgm123.com/resources/images/hgm123_logo_r.png");
			String logo_src = hgm123_mail.embed(logo_url, "Hgm123-logo");
			String htmlEmailTemplate = (
				"<html>" +
					"<div style='border-bottom:1px solid #ccc;'>" +
						"<a href='http://www.hgm123.com'>" +
							"<img src='cid:"+logo_src+"'>" +
						"</a>" +
					"</div>"
					+ "<div style='padding:10px 0;'>"
					+ "<p>"
					+ "最专业,最权威的游戏资讯发布平台:<a href='http://hgm123.com'>hgm123.com</a><br>"
					+ "</p>"
					+ "<p>注册成功!点击 <a href='http://www.hgm123.com/passport/active?id="
					+ passport.getId()
					+ "&code="
					+ passport.getLoginSequence()
					+ "&expiry="
					+ expiry.getTime()
					+ "'>立即激活</a> 完成账号激活!</p>"
					+ "</div>"
					+ "<div style='font-size:12px;border-top:1px solid #ccc;'>"
					+ "<p style='margin:0;padding:0;'>网址:<a href='http://www.hgm123.com' target='_blank'>http://www.hgm123.com</a></p>"
					+ "<p style='margin:0;padding:0;'>邮箱:admin@hgm123.com</p>"
					+ "</div>" +
				"</html>");
			hgm123_mail.setHtmlMsg(htmlEmailTemplate);
			hgm123_mail.setTextMsg("您的邮箱不支持HTML格式的邮件!");
			hgm123_mail.send();
		} catch (Exception e) {
			this.outJson(response, new MessageResult(false,e.getLocalizedMessage()));
		}
	}
	
	@RequestMapping(value = "/active", method = RequestMethod.GET)
	public String activePassport(@RequestParam("id")String id,@RequestParam("code")String code,@RequestParam("expiry")Long expiry,HttpServletRequest request,HttpServletResponse response, Model model) {
		Passport passport = passportRepository.findOne(id);
		String result = "";
		String message = "";
		if(passport == null){
			result = "noexist";
			message = "您确定您注册了Hgm123的通行证吗?如果没有请在 <a href='http://hgm123.com'>Hgm123首页</a> 注册!";
		}else{
			if(passport.getLoginSequence().equals(code)){
				if(passport.getStatus().equals("未激活")){
					if(expiry < new Date().getTime()){
						result = "expiry";
						message = "您的激活邮件已经失效,请点击  <a href='http://hgm123.com/passport/reactive?id="+id+"&code="+code+"'>重新发送</a> 通行证激活邮件!";
					}else{
						passport.setStatus("已激活");
						passport.setActivedate(new Date());
						passport.setOnline(true);
						passport.setLastlogindate(passport.getThislogindate());
						passport.setThislogindate(new Date());
						passport.setLastloginip(passport.getThisloginip());
						passport.setThisloginip(request.getRemoteAddr());
						passport.setLoginTimestamp(new Date().getTime());
						passportRepository.save(passport);
						request.getSession().setAttribute("passport", passport);
						result = "success";
						message = passport.getNickname()+",恭喜您,您的Hgm123通行证:"+passport.getUpassid()+"激活成功!";
					}
				}else{
					result = "actived";
					message = "您的Hgm123通行证:"+passport.getUpassid()+"在"+JSON.toJSONStringWithDateFormat(passport.getActivedate(), "yyyy-MM-dd HH:mm:ss")+"已经成功激活!";
				}
			}
		}
		model.addAttribute("result", result);
		model.addAttribute("message", message);
		return "active";
	}
	
	@RequestMapping(value = "/reactive", method = RequestMethod.POST)
	public void reactivePassport(HttpServletRequest request,HttpServletResponse response) {
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport == null){
			this.outJson(response, new MessageResult(true,"通行证登录已过期!"));
		}else{
			request.getSession().removeAttribute("passport");
			this.outJson(response, new MessageResult(true,"通行证退出登录成功!"));
		}
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPassport(@RequestParam("upassid")String upassid,@RequestParam("password")String password,@RequestParam("validatecode")String validatecode,HttpServletRequest request,HttpServletResponse response) {
		if(request.getSession().getAttribute("validatecode").toString().equals(validatecode)){
			List<Passport> passports = passportRepository.findPassportByUpassid(upassid);
			if(passports.isEmpty()){
				this.outJson(response, new VerifyResult(false, "upassid", "帐号不存在!"));
			}else{
				Passport passport = passports.get(0);
				if(passport.getPassword().equals(password)){
					if(passport.getStatus().equals("已激活")){
						passport.setOnline(true);
						passport.setLastlogindate(passport.getThislogindate());
						passport.setThislogindate(new Date());
						passport.setLastloginip(passport.getThisloginip());
						passport.setThisloginip(request.getRemoteAddr());
						passport.setLoginTimestamp(new Date().getTime());
						passportRepository.save(passport);
						request.getSession().setAttribute("passport", passport);
						logger.info(passport+"在["+request.getRemoteHost()+"]登陆成功!");
						this.outJson(response, new VerifyResult(true,passport.getNickname(),"登陆成功!"));
					}else{
						this.outJson(response, new VerifyResult(false,"upassid","帐号"+passport.getStatus()+"!"));
					}
				}else{
					this.outJson(response, new VerifyResult(false, "password", "密码有误!"));
				}
			}
		}else{
			this.outJson(response, new VerifyResult(false, "validatecode", "验证码有误!"));
		}
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public void logoutPassport(HttpServletRequest request,HttpServletResponse response) {
		Passport passport = (Passport) request.getSession().getAttribute("passport");
		if(passport==null){
			this.outJson(response, new MessageResult(false, "Session未创建或已过期!"));
		}else{
			request.getSession().removeAttribute("passport");
			this.outJson(response, new MessageResult(true, "Session登录信息清除成功!"));
		}
	}
	
}
