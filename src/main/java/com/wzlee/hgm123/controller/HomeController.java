package com.wzlee.hgm123.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.UriUtils;

import weibo4j.Account;
import weibo4j.Timeline;
import weibo4j.Users;
import weibo4j.model.Status;
import weibo4j.model.User;
import weibo4j.model.WeiboException;

import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.api.qzone.Topic;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.javabeans.GeneralResultBean;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.qq.connect.oauth.Oauth;
//import com.tencent.weibo.api.TAPI;
//import com.tencent.weibo.api.UserAPI;
//import com.tencent.weibo.oauthv2.OAuthV2;
//import com.tencent.weibo.oauthv2.OAuthV2Client;
//import com.tencent.weibo.utils.QHttpClient;
import com.wzlee.hgm123.domain.Advertise;
import com.wzlee.hgm123.domain.Feedback;
import com.wzlee.hgm123.domain.Passport;
import com.wzlee.hgm123.domain.Website;
import com.wzlee.hgm123.domain.Youkuer;
import com.wzlee.hgm123.repositories.AdvertiseRepository;
import com.wzlee.hgm123.repositories.FeedbackRepository;
import com.wzlee.hgm123.repositories.PassportRepository;
import com.wzlee.hgm123.repositories.WebsiteRepository;
import com.wzlee.hgm123.repositories.YoukuerRepository;
import com.wzlee.hgm123.utils.DateHelper;
import com.wzlee.hgm123.utils.InputStreamUtil;
import com.wzlee.hgm123.view.MessageResult;
import com.wzlee.hgm123.view.ThirdBean;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController extends BaseController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//	private static OAuthV2 t_oauth = new OAuthV2();
//	private static QHttpClient qHttpClient=new QHttpClient(2, 2, 5000, 5000, null, null);
	private static weibo4j.Oauth s_oauth = new weibo4j.Oauth();
	private static String domain = ResourceBundle.getBundle("config").getString("app.domain");
	private static String token = "mt17kN6yb6ddAA25l4NGguMJ8fcIhD";
	@Autowired
	private PassportRepository passportRepository;
	@Autowired 
	private AdvertiseRepository advertiseRepository;
	@Autowired 
	private WebsiteRepository websiteRepository;
	@Autowired
	private FeedbackRepository feedbackRepository;
	@Autowired
	private YoukuerRepository youkuerRepository;
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) {
		logger.info("Welcome index! The client locale is:"+locale+",ip is:"+request.getRemoteAddr()+",userAgent is:"+request.getHeader("user-agent"));
		String serverName = request.getServerName();
		int index = serverName.indexOf(domain);
		if(index <= 0){
			List<Advertise> advertises = advertiseRepository.findAdvertiseByOpenTime(DateHelper.getQueryString(0));
//			if(advertises.size() == 0){
//				advertises = advertiseRepository.findAdvertiseByOpenTime(DateHelper.getQueryString(-1));
//			}
			model.addAttribute("advertises",advertises);
			return "index";
		}else if(index >0 && index <=2){
			return "error/404";
		}else{
			String sld = serverName.substring(0, index-1);
			if(sld.equals("www")){
				List<Advertise> advertises = advertiseRepository.findAdvertiseByOpenTime(DateHelper.getQueryString(0));
				model.addAttribute("advertises",advertises);
				return "index";
			}else{
				List<Website> websites = websiteRepository.findWebsiteBySld(sld);
				if(websites.size() == 0){
					return "error/404";
				}else{
					Website website = websites.get(0);
					model.addAttribute("sld", sld);
					if(website.getType().equals("template")){
						return "website/template/"+sld+"/index";
					}else if(website.getType().equals("upload")){
						return "website/code/"+sld+"/index";
					}else{
						return "error/404";
					}
				}
			}
		}
	}
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String index(HttpServletRequest request,Locale locale, Model model) {
//		model.addAttribute("userAgent", request.getHeader("user-agent"));
//		System.out.println(request.getHeader("user-agent"));
//		return "home";
//	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String test(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) {
		return "test";
	}
	
	@RequestMapping(value = "/reg", method = RequestMethod.GET)
	public String register(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) {
		return "register";
	}
	
	@RequestMapping(value = "/yklogin", method = RequestMethod.GET)
	public void youkuLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		String uri = "https://openapi.youku.com/v2/oauth2/authorize?client_id=bf928cbbd9d41aeb&response_type=code&redirect_uri=http://localhost/yk";
		response.sendRedirect(uri);
	}
	
	@RequestMapping(value = "/qqlogin", method = RequestMethod.GET)
	public void qqLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.sendRedirect(new Oauth().getAuthorizeURL(request));
	}
	
//	@RequestMapping(value = "/twlogin", method = RequestMethod.GET)
//	public void twLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
//		t_oauth.setClientId("801352427");
//		t_oauth.setClientSecret("4a33326718a1ac34a1429a172f36f241");
//		t_oauth.setRedirectUri("http://www.hgm123.com/tw");
//		// t_oauth.setAccessToken("02204e5e59c2d22b73fb9b688e9f0c02");
//		// t_oauth.setOpenid("42A2ACA51C28782E854A50C5FA14160A");
//		// t_oauth.setOpenkey("ECBBC3B17F4190C8824DB652436E5DFA");
//		// t_oauth.setExpiresIn("604800");
//		OAuthV2Client.setQHttpClient(qHttpClient);
//		response.sendRedirect(OAuthV2Client.generateAuthorizationURL(t_oauth));
//	}
	
	@RequestMapping(value = "/swlogin", method = RequestMethod.GET)
	public void swLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.sendRedirect(s_oauth.authorize("code","2513338801","5f51ee1630355ac40e7d40869ad80dcf"));
	}
	
	@RequestMapping(value = "/qq", method = RequestMethod.GET)
	public String qQ(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) throws QQConnectException {
		AccessToken accessTokenObj = (new Oauth()).getAccessTokenByRequest(request);
        if (accessTokenObj.getAccessToken().equals("")) {
            logger.info("回调地址没有获取到响应参数...");
            return "error";
        } else {
            String accessToken = accessTokenObj.getAccessToken();
            OpenID openIDObj =  new OpenID(accessToken);
            String QQ_OPENID = openIDObj.getUserOpenID();

            UserInfo qzoneUserInfo = new UserInfo(accessToken, QQ_OPENID);
            UserInfoBean userInfoBean = qzoneUserInfo.getUserInfo();
            List<Passport> passports = passportRepository.findPassportByQqOpenid(QQ_OPENID);
            if(passports.size()<=0){
            	ThirdBean thirdBean = new ThirdBean("qq",QQ_OPENID,"",userInfoBean.getNickname(),userInfoBean.getAvatar().getAvatarURL100());
            	model.addAttribute("thirdBean", thirdBean);
            	return "register";
            }else{
            	Passport passport = passports.get(0);
            	passport.setOnline(true);
				passport.setLastlogindate(passport.getThislogindate());
				passport.setThislogindate(new Date());
				passport.setLastloginip(passport.getThisloginip());
				passport.setThisloginip(request.getRemoteAddr());
				passport.setLoginTimestamp(new Date().getTime());
				passportRepository.save(passport);
            	request.getSession().setAttribute("passport", passport);
            	Topic topic = new Topic(accessToken, QQ_OPENID);
	            GeneralResultBean grb = topic.addTopic("我刚刚登陆了最专业最权威的游戏资讯发布网站:http://hgm123.com,最新最热的游戏资讯等着你哦!");
                if (grb.getRet() == 0) {
				    logger.info("["+passport.getUpassid()+"]使用QQ登陆成功,并成功发布了说说!");
				} else {
					logger.info("["+passport.getUpassid()+"]使用QQ登陆成功,但发表说说失败！原因： " + grb.getMsg());
				}
            	return "redirect:/";
            }
        }
	}
	
	
//	@RequestMapping(value = "/tw", method = RequestMethod.GET)
//	public String tencentWeibo(HttpServletRequest request,Model model) throws Exception {
//		if(OAuthV2Client.parseAuthorization(request.getQueryString(), t_oauth)){
//            System.out.println("Parse Authorization Information Successfully");
//        }else{
//            System.out.println("Fail to Parse Authorization Information");
//            return "error";
//        }
//		//检查是否正确取得授权码
//		if (t_oauth.getStatus() == 2) {
//			System.out.println("Get Authorization Code failed!");
//			return "error";
//		}
//       
//		//换取access token
//		t_oauth.setGrantType("authorize_code");
//		try {
//			OAuthV2Client.accessToken(t_oauth);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//       
//		//检查是否正确取得access token
//		if (t_oauth.getStatus() == 3) {
//            System.out.println("Get Access Token failed!");
//            return "error";
//        }
//		String TW_OPENID = t_oauth.getOpenid();
//		List<Passport> passports = passportRepository.findPassportByTwOpenid(TW_OPENID);
//        if(passports.size() <= 0){
//        	UserAPI userAPI = new UserAPI(t_oauth.getOauthVersion(),qHttpClient);
//        	JSONObject userBean = JSON.parseObject(userAPI.info(t_oauth, "json"));
//        	ThirdBean thirdBean = new ThirdBean("tw",TW_OPENID,userBean.getJSONObject("data").getString("name"),userBean.getJSONObject("data").getString("nick"),userBean.getJSONObject("data").getString("head"));
//        	model.addAttribute("thirdBean", thirdBean);
//        	return "register";
//        }else{
//        	Passport passport = passports.get(0);
//        	passport.setOnline(true);
//			passport.setLastlogindate(passport.getThislogindate());
//			passport.setThislogindate(new Date());
//			passport.setLastloginip(passport.getThisloginip());
//			passport.setThisloginip(request.getRemoteAddr());
//			passport.setLoginTimestamp(new Date().getTime());
//			passportRepository.save(passport);
//        	request.getSession().setAttribute("passport", passport);
//        	TAPI tAPI=new TAPI(t_oauth.getOauthVersion());
//        	JSONObject response = JSON.parseObject(tAPI.add(t_oauth, "json", "我刚刚登陆了最专业最权威的游戏资讯发布网站:http://www.hgm123.com,最新最热的游戏资讯等着你哦!", request.getRemoteAddr(), "", "", ""));
//            if (response.getIntValue("errcode") == 0) {
//			    logger.info("["+passport.getUpassid()+"]使用腾讯微博登陆成功,并成功发布了一条微博!");
//			} else {
//				logger.info("["+passport.getUpassid()+"]使用QQ登陆成功,但发表微博失败！原因： " + response.getString("msg"));
//			}
//            return "redirect:/";
//        }
//	}
	
	@RequestMapping(value = "/sw", method = RequestMethod.GET)
	public String sinaWeibo(@RequestParam("code")String code,HttpServletRequest request,Model model) throws Exception {
        try{
        	String accsestoken = s_oauth.getAccessTokenByCode(code).getAccessToken();
        	Account am = new Account();
        	am.client.setToken(accsestoken);
        	Users um = new Users();
        	um.client.setToken(accsestoken);
        	String SW_OPENID = am.getUid().getString("uid");
    		List<Passport> passports = passportRepository.findPassportBySwOpenid(SW_OPENID);
            if(passports.size() <= 0){
            	User user = um.showUserById(SW_OPENID);
            	ThirdBean thirdBean = new ThirdBean("sw",SW_OPENID,user.getName(),user.getScreenName(),user.getAvatarLarge());
            	model.addAttribute("thirdBean", thirdBean);
            	return "register";
            }else{
            	Passport passport = passports.get(0);
            	passport.setOnline(true);
    			passport.setLastlogindate(passport.getThislogindate());
    			passport.setThislogindate(new Date());
    			passport.setLastloginip(passport.getThisloginip());
    			passport.setThisloginip(request.getRemoteAddr());
    			passport.setLoginTimestamp(new Date().getTime());
    			passportRepository.save(passport);
            	request.getSession().setAttribute("passport", passport);
            	Timeline tl = new Timeline();
				tl.client.setToken(accsestoken);// access_token
				Status status = tl.UpdateStatus("我刚刚登陆了最专业最权威的游戏资讯发布网站:http://www.hgm123.com,最新最热的游戏资讯等着你哦!");
			    logger.info("["+passport.getUpassid()+"]使用新浪微博登陆成功,并成功发布了一条微博:"+status.toString());
                return "redirect:/";
            }
        } catch (WeiboException e) {
            if(401 == e.getStatusCode()){
                logger.info("Unable to get the access token.");
            }else{
                e.printStackTrace();
            }
            return "error";
        }
	}
	
	@RequestMapping(value = "/yk", method = RequestMethod.GET)
	public void youku(@RequestParam(required=false)String code,HttpServletRequest request,HttpServletResponse response) throws Exception {
    	HttpClient httpClient = new HttpClient();
    	String uri = "https://openapi.youku.com/v2/oauth2/token";
    	PostMethod postMethod = new PostMethod(uri);
    	postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
    	postMethod.getParams().setContentCharset("UTF-8");
    	Part[] parts = {
    			new StringPart("client_id", "bf928cbbd9d41aeb"), 
    			new StringPart("client_secret", "4345289e536292a6dba7e4ed540eb7f5"),
    			new StringPart("grant_type", "authorization_code"),
    			new StringPart("code", code),
    			new StringPart("redirect_uri", "http://localhost/yk/oauth")
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
    			request.getSession().setAttribute("YOUKU_TOKEN", accessToken);
    			logger.info("获取优酷TOKEN["+accessToken.getAccessToken()+"]成功!");
    		}
    	} catch (HttpException e) {
    		logger.info("捕获HTTP异常,错误信息:"+e.getLocalizedMessage());
    	} catch (IOException e) {
    		logger.info("捕获IO异常,错误信息:"+e.getLocalizedMessage());
    	} finally {
    		postMethod.releaseConnection();
    	}
	}
	
	@RequestMapping(value = "/yk/oauth", method = RequestMethod.GET)
	public String youkuOauth(HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		HttpClient httpClient = new HttpClient();
		String uri = "https://openapi.youku.com/v2/users/myinfo.json";
		PostMethod postMethod = new PostMethod(uri);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler());
		postMethod.getParams().setContentCharset("UTF-8");
		AccessToken accessToken = (AccessToken) request.getSession().getAttribute("YOUKU_TOKEN");
		if(accessToken == null){
			model.addAttribute("message", "YOUKU_TOKEN未创建或已过期!");
			return "error/500";
		}else{
			Part[] parts = {
					new StringPart("client_id", "bf928cbbd9d41aeb"), 
					new StringPart("access_token", accessToken.getAccessToken())
			};
			postMethod.setRequestEntity(new MultipartRequestEntity(parts, postMethod.getParams()));
			try {
				int _statusCode = httpClient.executeMethod(postMethod);
				if (_statusCode != HttpStatus.SC_OK) {
					model.addAttribute("message",postMethod.getStatusLine());
					return "error/500";
				}else{
					InputStream responseBody = postMethod.getResponseBodyAsStream();
					String responseString = InputStreamUtil.InputStreamTOString(responseBody, "UTF-8");
					Youkuer youkuer = JSON.parseObject(responseString,Youkuer.class);
					Youkuer _youkuer = youkuerRepository.findOne(youkuer.getId());
					if(_youkuer == null){
						youkuerRepository.save(youkuer);
						model.addAttribute("youkuer",youkuer);
					}else{
						model.addAttribute("youkuer",_youkuer);
					}
				}
			} catch (HttpException e) {
				model.addAttribute("message", e.getLocalizedMessage());
				return "error/500";
			} catch (IOException e) {
				model.addAttribute("message", e.getLocalizedMessage());
				return "error/500";
			} finally {
				postMethod.releaseConnection();
			}
			return "youku/user";
		}
	}
	
	@RequestMapping(value = "/wx", method = RequestMethod.GET)
	public void weixin(@RequestParam("signature")String signature,@RequestParam("timestamp")String timestamp,@RequestParam("nonce")String nonce,@RequestParam("echostr")String echostr,HttpServletRequest request,HttpServletResponse response,Model model) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add(token);
		list.add(timestamp);
		list.add(nonce);
		Collections.sort(list);
		String string = list.toArray().toString();
		logger.info("组合后的字符串为:"+string);
		DigestUtils.sha(string);
		logger.info("加密后的字符串为:"+string);
		this.outHTML(response, echostr);
	}
	
	@RequestMapping(value = "/yy", method = RequestMethod.GET)
	public String yyOpen(@RequestParam("code")String code,HttpServletRequest request,Model model) throws Exception {
		//TODO YY开放平台接入
		return "yy";
	}
	
	@RequestMapping(value = "/feedback", method = RequestMethod.POST)
	public void saveFeedback(HttpServletRequest request,HttpServletResponse response){
		Feedback feedback = new Feedback();
		try {
			BeanUtils.populate(feedback,request.getParameterMap());
			feedbackRepository.save(feedback);
			this.outJson(response, new MessageResult(true,"Hgm123系统已经接收到您的反馈,我们会尽快处理!"));
		} catch (IllegalAccessException e1) {
			this.outJson(response, new MessageResult(false,"异常消息:"+e1.getLocalizedMessage()));
		} catch (InvocationTargetException e1) {
			this.outJson(response, new MessageResult(false,"异常消息:"+e1.getLocalizedMessage()));
		}
	}
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	public String resourceDownload(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model) {
		return "download";
	}
	
	public static void main(String[] args) throws UnsupportedEncodingException { 
		System.out.println(UriUtils.decode("The+provided+authorization+grant+%28e.g.+authorization+code%2C+resource+owner+credentials%29+is+invalid%2C+expired%2C+revoked%2C+does+not+match+the+redirection+URI+used+in+the+authorization+request%2C+or+was+issued+to+another+client", "UTF-8"));
//		Properties pps=new Properties();   
//	    try {  
//	        pps.load(new FileInputStream("src/main/resources/config.ini"));
//	        System.out.println(pps.getProperty("domain"));
////	        Enumeration<?> enum1 = pps.propertyNames();  
////	        while (enum1.hasMoreElements()) {  
////	        	String strKey = (String) enum1.nextElement();  
////	       		String strValue = pps.getProperty(strKey);  
////	       		System.out.println(strKey + "=" + strValue);  
////	        }  
//	    } catch (Exception e) {  
//	        e.printStackTrace();  
//	    }  
    }  
	
}