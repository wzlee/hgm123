<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
    <base href="<%=basePath%>">
    
    <title>专业游戏GM服务平台 | Hgm123.com</title>
    <meta property="qc:admins" content="250527663760751236375" />
    <meta property="wb:webmaster" content="105dd5e33ff935d9" />
    <meta name="baidu-site-verification" content="c6b8b7c08d56425a55c76a491fa43fd3"/>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="keywords" content="">
	<meta http-equiv="description" content="">
	
    <link rel="shortcut icon" href="resources/images/favicon.ico" /> 
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap-fileupload.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/datetimepicker/css/datetimepicker.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/showLoading/css/showLoading.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/artDialog-5.0.3/skins/default.css">
	<link rel="stylesheet" type="text/css" href="myLib/messenger/css/messenger.css">
	<link rel="stylesheet" type="text/css" href="resources/css/index.css?v=20130501">
  </head>
  
  <body>
	<div class="navbar navbar-fixed-top hgm-header">
    	<div class="navbar-inner">
      		<div class="container">
          		<ul class="nav pull-left">
       				<li class="index"><a href="./">首页</a></li>
       				<li class="divider-vertical"></li>
       				<li><a href="#"><i class="icon-home"></i>设为首页</a></li>
   					<li class="divider-vertical"></li>
            		<li>
				        <div class="navbar-form pull-left">
				        	<input class="span3 input-filter" type="text" placeholder="输入搜索关键字(如:'一区','精品','1.76')..." required>
					  	</div>
			    	</li>
			    	<li class="divider-vertical"></li>
               	</ul>
               	<ul class="nav pull-right">
              	<c:choose>  
					<c:when test="${sessionScope.passport == null}">  
						<li class="ulogin">
				    		<a href="#" role="button" data-toggle="modal" data-target=".signin-modal"><i class="icon-signin"></i> 登录</a>
	             		</li>
	             		<li class="ureg">
	             			<a href="#" role="button" data-toggle="modal" data-target=".signup-modal"><i class="icon-user"></i> 注册</a>
	             		</li>
	             		<li class="dropdown ucenter hide">
		               		<div class="btn-group pull-left">
						 			<a class="btn nickname" href="#" role="button" data-toggle="modal" data-target=".ucenter-modal"></a>
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#" role="button" data-toggle="modal" data-target=".publish-modal"><i class="icon-share"></i> 游戏资讯发布</a></li>
			                   	 	<li class="dropdown-submenu" role="menu" aria-labelledby="dLabel">
										<a href="#"><i class="icon-desktop"></i> 创建游戏网站</a>
										<ul class="dropdown-menu">
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".use-template-modal"><i class="icon-magic"></i> 创建模板网站</a></li>
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".upload-code-modal"><i class="icon-upload-alt"></i> 上传网站代码</a></li>
										</ul>
									</li>
			                  		<li><a href="#"><i class="icon-bar-chart"></i> 游戏数据统计</a></li>
			                  		<li class="dropdown-submenu" role="menu" aria-labelledby="dLabel">
										<a href="#"><i class="icon-share"></i> HGM联盟</a>
										<ul class="dropdown-menu">
				                    		<li><a href="http://hgm8.com" target="_blank"><i class="icon-home"></i> Hgm8.com主站</a></li>
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".qq-qun-modal"><i class="icon-group"></i> HGM官方QQ群</a></li>
										</ul>
									</li>
			                    	<li class="divider"></li>
			                    	<li><a class="btn-logout text-error" href="#"><i class="icon-off"></i> 退出登录</a></li>
								</ul>
							</div>
		               	</li>
				    </c:when>  
				   	<c:otherwise>
				   		<li class="ulogin hide">
				    		<a href="#" role="button" data-toggle="modal" data-target=".signin-modal"><i class="icon-signin"></i> 登录</a>
	             		</li>
	             		<li class="ureg hide">
	             			<a href="#" role="button" data-toggle="modal" data-target=".signup-modal"><i class="icon-credit-card"></i> 注册</a>
	             		</li>
		               	<li class="dropdown ucenter">
		               		<div class="btn-group pull-left">
						 			<a class="btn nickname" href="#" role="button" data-toggle="modal" data-target=".ucenter-modal">${sessionScope.passport.nickname}</a>
								<button class="btn dropdown-toggle" data-toggle="dropdown">
									<span class="caret"></span>
								</button>
								<ul class="dropdown-menu">
									<li><a href="#" role="button" data-toggle="modal" data-target=".publish-modal"><i class="icon-share"></i> 游戏资讯发布</a></li>
			                   	 	<li class="dropdown-submenu" role="menu" aria-labelledby="dLabel">
										<a href="#"><i class="icon-desktop"></i> 创建游戏网站</a>
										<ul class="dropdown-menu">
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".use-template-modal"><i class="icon-magic"></i> 创建模板网站</a></li>
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".upload-code-modal"><i class="icon-upload-alt"></i> 上传网站代码</a></li>
										</ul>
									</li>
			                  		<li><a href="#"><i class="icon-bar-chart"></i> 游戏数据统计</a></li>
			                  		<li class="dropdown-submenu" role="menu" aria-labelledby="dLabel">
										<a href="#"><i class="icon-share"></i> HGM联盟</a>
										<ul class="dropdown-menu">
				                    		<li><a href="http://hgm8.com" target="_blank"><i class="icon-home"></i> Hgm8.com主站</a></li>
				                    		<li><a href="#" role="button" data-toggle="modal" data-target=".qq-qun-modal"><i class="icon-group"></i> HGM官方QQ群</a></li>
										</ul>
									</li>
			                    	<li class="divider"></li>
			                    	<li><a class="btn-logout text-error" href="#"><i class="icon-off"></i> 退出登录</a></li>
								</ul>
							</div>
		               	</li>
           		   	</c:otherwise>  
				</c:choose>
	           		<li class="divider-vertical"></li>
              		<li class="dropdown">
						<a class="dropdown-toggle" data-toggle="dropdown" href="#"><i class="icon-globe"></i> 网站导航 <b class="caret"></b></a>
						<ul class="dropdown-menu" role="menu">
	                   		<li><a href="http://hgm8.com" target="_blank"><i class="icon-home"></i> Hgm8.com主站</a></li>
	                   		<li><a href="#" role="button" data-toggle="modal" data-target=".qq-qun-modal"><i class="icon-group"></i> HGM官方QQ群</a></li>
	                     	<li><a href="#" role="button" data-toggle="modal" data-target=".about-modal"><i class="icon-exclamation-sign"></i> 关于与帮助</a></li>
						</ul>
					</li>
           		</ul>
        	</div><%-- container --%>
      	</div><%-- navbar-inner --%>
    </div><%-- navbar --%>	
    
    <%-- 登录窗口 --%>
    <div class="modal hide fade signin-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-credit-card"></i> 通行证登录
		</div>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="signin-form pull-left">
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend">
							<span class="add-on">账号</span>
							<input class="span2 upassid" name="upassid" type="text" minlength="3" maxlength="10" placeholder="输入通行证账号..." required>
						</div>
						<span class="help-inline upassid-info" data-label="帐号"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">密码</span>
							<input class="span2 password" name="password" type="password" minlength="6" maxlength="20" placeholder="输入通行证密码..." required>
						</div>
						<span class="help-inline password-info" data-label="密码"></span>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="input-prepend input-append">
							<span class="add-on">验证码</span>
							<input class="span1 validatecode" name="validatecode" type="text" minlength="4" maxlength="4" placeholder="验证码..." required>
							<span class="add-on"><img class="validate-img" alt="点击刷新" src="public/validatecode"></span>
							<button class="btn refresh-code" type="button"><i class="icon-refresh"></i></button>
						</div>
						<span class="help-inline validatecode-info" data-label="验证码"></span>
					</div>
		    	</div>
		    	<div class="control-group">
		    		<div class="controls">
	    				<input class="span keeplogin" name="keeplogin" type="checkbox">
	    				<span class="help-inline">保持登录</span>
	    				<span class="help-inline keeplogin-warning-tip"><i class="icon-exclamation-sign"></i> 公共场合请慎用!</span>
	    				<span class="help-inline"><a href="jsp/find-pd.jsp" target="_blank">找回密码</a></span>
		    		</div>
		    	</div>
		   	</form>
		</div>
		<div class="modal-footer">
			<div class="third-label pull-left">使用合作帐号登录：</div>
			<div class="btn-group pull-left" data-toggle="buttons-radio">
				<a class="btn third-qq" href="yklogin"><img alt="youku账号登录" src="resources/images/youku.JPG"></a>
			    <a class="btn third-qq" href="qqlogin"><img alt="QQ账号登录" src="resources/images/qq.png"></a>
			    <a class="btn" href="twlogin"><img alt="腾讯微博登录" src="resources/images/tencent-weibo.png"></a>
			    <a class="btn" href="swlogin"><img alt="新浪微博登录" src="resources/images/sina-weibo.png"></a>
		    </div>
			<button class="btn btn-login" data-loading-text="<i class='icon-spinner icon-spin'></i> 正在登录"><i class="icon-signin"></i> 立即登录</button>
		</div>
	</div>
	
	<%-- 注册窗口 --%>
    <div class="modal hide fade signup-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h5 id="myModalLabel"><i class="icon-credit-card"></i> 注册通行证</h5>
		</div>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="signup-form pull-left">
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend">
							<span class="add-on">账号</span>
							<input class="span2 upassid" name="upassid" type="text" minlength="3" maxlength="10" placeholder="3-10位字母/数字组合..." required>
						</div>
						<span class="help-inline upassid-info" data-label="帐号"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">密码</span>
							<input class="span2 password" name="password" type="password" minlength="6" maxlength="20" placeholder="6-20位密码组合..." required>
						</div>
						<span class="help-inline password-info" data-label="密码"></span>
					</div>
			   	</div>
			   	<div class="control-group">
					<div class="input-prepend">
						<span class="add-on">确认</span>
						<input class="span2 confirm" name="confirm" type="password" minlength="6" maxlength="20" placeholder="再次输入密码确认..." required>
					</div>
					<span class="help-inline confirm-info" data-label="确认"></span>
			   	</div>
				<div class="control-group">
						<div class="input-prepend">
							<span class="add-on">昵称</span>
							<input class="span2 nickname" name="nickname" type="text" minlength="3" maxlength="10" placeholder="通行证显示的昵称..." required>
						</div>
						<span class="help-inline nickname-info" data-label="昵称"></span>
				</div>
				<div class="control-group">
						<div class="input-prepend">
							<span class="add-on">邮箱</span>
							<input class="span2 email" name="email" type="email" placeholder="接收通行证激活邮件..." required>
						</div>
						<span class="help-inline email-info" data-label="邮箱"></span>
				</div>
				<div class="control-group">
						<div class="input-prepend">
							<span class="add-on">Q Q</span>
							<input class="span2 qq" name="qq" type="number" minlength="5" maxlength="10" placeholder="加入官方群验证号码...">
						</div>
						<span class="help-inline qq-info" data-label="QQ"></span>
				</div>
				<div class="control-group">
					<div class="input-prepend">
						<span class="add-on">身份:</span>
						<select class="groupname" name="groupname" data-placeholder="--请选择身份--" required>
						  	<option value="player">游戏玩家</option>
						  	<option value="gm">游戏GM</option>
						</select>
					</div>
				</div>
			   	<div class="control-group">
	  				<input class="agreement" name="agreement" type="checkbox">
	  				<span class="help-inline">同意使用协议 </span>
	  				<span class="help-inline">
	  					<a class="ag_target" href="html/agreement.html" role="button" data-toggle="modal" data-target=".agreement-modal" title="点击阅读使用协议"><i class="icon-list-alt"></i></a>
 					</span>
		   			<span class="help-inline agreement-info" data-label="同意协议"></span>
		   		</div>
			</form>
		</div>
		<div class="modal-footer">
			<span class="reg-tip pull-left"><i class="icon-tag"></i> 强烈建议邮箱设置为常用的邮箱,用于收取通行证激活邮件!</span>
			<button type="button" class="btn btn-reg" data-loading-text="<i class='icon-spinner icon-spin'></i>提交中..."><i class="icon-ok-circle"></i> 提交</button>
		</div>
	</div>
	
	<%-- 注册提示窗口 --%>
	<div class="modal hide fade reg-info-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h5 id="myModalLabel"><i class="icon-info-sign"></i> 注册提示</h5>
		</div>
		<div class="modal-body">
			<div class="signupInfo-msg">
				<i class="icon-flag"></i>
				注册成功!系统将已经向邮箱:<span class="email"></span>发送了一封激活邮件,请尽快打开邮件完成账号激活!(激活邮件1小时内有效)
			</div>
		</div>
		<div class="modal-footer">
			<a class="btn btn-primary pull-left go-mailbox" href="#" target="_blank"><i class="icon-envelope"></i> 立即激活</a>
			<a class="btn pull-right" data-dismiss="modal" aria-hidden="true"><i class="icon-ok"></i> 完成激活</a>
		</div>
	</div>
	
	<%-- 用户信息窗口 --%>
	<div class="modal hide fade ucenter-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<h5><i class="icon-user"></i> 用户中心</h5>
		</div>
		<div class="modal-body">
			<ul class="nav nav-tabs userinfo-tab" data-tab-content=".userinfo-tab-content">
				<li class="active"><a href="#" data-toggle="tab" data-target=".hgm-welcome"><i class="icon-credit-card"></i> Hgm123</a></li>
				<li><a href="public/passport" data-toggle="ajax-tab" data-target="#passport"><i class="icon-credit-card"></i> 通行证资料</a></li>
				<li><a href="public/advertise" data-toggle="ajax-tab" data-target="#advertise"><i class="icon-hdd"></i> 游戏资讯</a></li>
				<li><a href="public/website" data-toggle="ajax-tab" data-target="#website"><i class="icon-hdd"></i> 游戏网站</a></li>
				<li><a href="public/bindinfo" data-toggle="ajax-tab" data-target="#bindinfo"><i class="icon-sitemap"></i> 关联信息</a></li>
			</ul> 
			<div class="tab-content userinfo-tab-content">
				<div class="tab-pane active hgm-welcome">
					<p>
						<blockquote><span class="nickname lead">${sessionScope.passport.nickname}</span> ,欢迎登录Hgm123游戏资讯平台</blockquote><br>
						<blockquote>
							<div class="inline digital-clock">当前时间: 
								<span class="hour lead"></span>：<span class="min lead"></span>：
								<span class="sec"></span> <span class="meridiem"></span>
							</div>
						</blockquote>
	                	<%-- <blockquote>
	                		<marquee behavior="scroll" height="30" direction="left" scrollDelay=80 scrollamount=2 align=middle onmouseover=this.stop() onmouseout=this.start()>
			                	<a href="./">台湾6.1级地震威力如0.7颗原子弹 已致1死19伤</a>
			                	<a href="./">金砖基金中国拟出410亿美元 巴俄印各出180亿</a>
			                	<a href="./">多地市民排队集中抢购天然气 官方辟谣涨价传言</a>
			                	<a href="./">新疆20嫌犯因煽动分裂国家策划暗杀干警等获刑</a>
			                </marquee>
		                </blockquote> --%>
					</p>
				</div>
				<div class="tab-pane passport" id="passport">
				</div>
				<div class="tab-pane advertise" id="advertise">
				</div>
				<div class="tab-pane website" id="website">
				</div>
				<div class="tab-pane bindinfo" id="bindinfo">
				</div>
			</div>
		</div>
		<div class="modal-footer">
			<span class="reg-tip pull-left"><i class="icon-tag"></i> Hgm123开放通行证关联第三方账号功能,关联有惊喜哦!</span>
			<button class="btn btn-refresh"><i class="icon-refresh"></i> 刷新</button>
			<button class="btn" data-dismiss="modal" aria-hidden="true"><i class="icon-remove-sign"></i> 关闭</button>
		</div>
	</div>
	
	<%-- 发布广告  --%>
    <div class="modal hide fade publish-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-credit-card"></i> 发布游戏资讯
		</div>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="publish-form pull-left">
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend">
							<span class="add-on">游戏名称</span>
							<input class="span2 gameName" name="gameName" type="text" minlength="3" maxlength="10" placeholder="输入游戏名称..." required>
						</div>
						<span class="help-inline gameName-info" data-label="游戏名称"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">游戏版本</span>
							<input class="span2 gameEdition" name="gameEdition" type="text" minlength="6" maxlength="20" placeholder="输入游戏版本..." required>
						</div>
						<span class="help-inline gameEdition-info" data-label="游戏版本"></span>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">开放时间</span>
							<input class="span2 openTime" name="openTime" type="text" placeholder="选择开区日期和时间..." required>
						</div>
						<span class="help-inline openTime-info" data-label="开放时间"></span>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">游戏线路</span>
							<input class="span2 lineType" name="lineType" type="text" minlength="6" maxlength="20" placeholder="输入游戏线路..." required>
						</div>
						<span class="help-inline lineType-info" data-label="游戏线路"></span>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">游戏描述</span>
							<textarea class="span2 gameDescription" rows="2" maxlength="20" name="gameDescription" type="textarea" placeholder="输入游戏描述..." data-placement="right" required></textarea>
						</div>
						<span class="help-inline gameDescription-info" data-label="游戏描述"></span>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">游戏网站</span>
							<input class="span2 gameUrl" name="gameUrl" value="http://" type="url" minlength="6" maxlength="20" placeholder="输入游戏网站地址..." required>
						</div>
						<span class="help-inline gameUrl-info" data-label="游戏网站"></span>
					</div>
		    	</div>
		    	<div class="control-group">
		    		<div class="controls">
	    				<input class="span agreement" name="agreement" type="checkbox">
	    				<span class="help-inline">同意Hgm123游戏资讯发布协议</span>
	    				<span class="help-inline">
		  					<a class="ag_target" href="html/agreement.html" role="button" data-toggle="modal" data-target=".agreement-modal" title="请仔细阅读协议"><i class="icon-list-alt"></i></a>
	 					</span>
			   			<span class="help-inline agreement-info" data-label="同意协议"></span>
		    		</div>
		    	</div>
		   	</form>
		</div>
		<div class="modal-footer">
			<ul class="inline pull-left">
				<li><strong>同步分享至：</strong></li>
	    		<li><input class="span" type="checkbox" checked="checked"> <img alt="QQ账号登录" src="resources/images/qq.png"></li>
	    		<li><input class="span" type="checkbox" checked="checked"> <img alt="腾讯微博登录" src="resources/images/tencent-weibo.png"></li>
	    		<li><input class="span" type="checkbox" checked="checked"> <img alt="新浪微博登录" src="resources/images/sina-weibo.png"></li>
	   		</ul>
			<button class="btn publish-btn" data-loading-text="<i class='icon-spinner icon-spin'></i> 发布中"><i class="icon-signin"></i> 立即发布</button>
		</div>
	</div>
	
	<%-- 创建模板网站  --%>
    <div class="modal hide fade use-template-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-magic"></i> 创建模板网站
		</div>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="use-template-form pull-left">
				<input type="hidden" name="type" value="template"/>
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend input-append selecttemplate">
							<span class="add-on">网站模板</span>
							<input class="span2 templateCode" readonly="readonly" name="templateCode" type="text" minlength="3" maxlength="10" placeholder="选择网站模板..." required>
							<a class="btn select-template" href="#" role="button" data-toggle="modal" data-target=".template-modal"><i class="icon-book"></i> 选择</a>
						</div>
						<span class="help-inline templateCode-info" data-label="网站模板"></span>
					</div>
				</div>
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend input-append">
							<span class="add-on">个性域名</span>
							<input class="span1 sld" name="sld" type="text" align="right" minlength="3" maxlength="6" placeholder="个性域名..." required>
							<span class="add-on">.hgm8.com</span>
						</div>
						<span class="help-inline sld-info" data-label="网站域名"></span>
					</div>
				</div>
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend">
							<span class="add-on">网站标题</span>
							<input class="span3 title" name="title" type="text" minlength="3" maxlength="20" placeholder="输入网站标题..." required>
						</div>
						<span class="help-inline title" data-label="网站名称"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="fileupload fileupload-new use-template-txt" data-provides="fileupload">
							<div class="input-prepend input-append">
								<span class="add-on">服务器列表</span>
								<div class="uneditable-input span2">
									<i class="icon-file fileupload-exists"></i> <span class="fileupload-preview serverList"></span>
								</div>
								<span class="btn btn-file">
									<span class="fileupload-new"><i class="icon-folder-open"></i> 浏览</span>
									<span class="fileupload-exists"><i class="icon-refresh"></i> 修改</span>
									<input class="span" name="serverList" type="file" data-uploadtype="file" data-name="serverList"/>
								</span>
								<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><i class="icon-trash"></i> 清除</a>
							</div>
						</div>
					</div>
		    	</div>
		    	<div class="control-group">
		    		<div class="controls">
	    				<input class="span agreement" name="agreement" type="checkbox">
	    				<span class="help-inline">同意Hgm123游戏网站协议</span>
	    				<span class="help-inline">
  							<a class="ag_target" href="html/agreement.html" role="button" data-toggle="modal" data-target=".agreement-modal" title="请仔细阅读协议"><i class="icon-list-alt"></i></a>
	 					</span>
			   			<span class="help-inline agreement-info" data-label="同意协议"></span>
		    		</div>
		    	</div>
		   	</form>
		</div>
		<div class="modal-footer">
			<div class="usage-tip pull-left"><i class="icon-tag"></i> 使用网站模板建站收费标准为: <span class="text-success"> 免费</span></div>
			<button class="btn use-template-btn" data-loading-text="<i class='icon-spinner icon-spin'></i> 创建中..."><i class="icon-signin"></i> 立即创建</button>
		</div>
	</div>
	
	<%-- 网站模板  --%>
    <div class="modal hide fade in template-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-credit-card"></i> 网站模板
		</div>
		<div class="modal-body">
			<div class="row-fluid">
				<ul class="thumbnails">
					<li class="span4">
						<a class="thumbnail" href="#"> 
							<img alt="1.76版本" data-templateCode="default1.76" src="resources/images/templates/1.76.jpg">
							<div class="caption">
								<div class="btn-group">
									<button class="btn pull-left select-btn" data-code="default1.76"><i class="icon-check"></i> 选中</button>
									<button class="btn pull-right preview-btn" data-url="default1.76"><i class="icon-eye-open"></i> 预览</button>
								</div>
		                  	</div>
						</a>
					</li>
					<li class="span4">
						<a class="thumbnail" href="#"> 
							<img alt="1.85版本" data-templateCode="default1.85" src="resources/images/templates/1.85.jpg">
							<div class="caption">
								<div class="btn-group">
									<button class="btn pull-left select-btn" data-code="default1.85"><i class="icon-check"></i> 选中</button>
									<button class="btn pull-right preview-btn" data-url="default1.85"><i class="icon-eye-open"></i> 预览</button>
								</div>
		                  	</div>
						</a>
					</li>
					<li class="span4">
						<a class="thumbnail" href="#"> 
							<img alt="1.89版本" data-templateCode="default1.89" src="resources/images/templates/1.89.jpg">
							<div class="caption">
								<div class="btn-group">
									<button class="btn pull-left select-btn" data-code="default1.89"><i class="icon-check"></i> 选中</button>
									<button class="btn pull-right preview-btn" data-url="default1.89"><i class="icon-eye-open"></i> 预览</button>
								</div>
		                  	</div>
						</a>
					</li>
				</ul>
				<div class="template-tip"><p><i class="icon-tag"></i> 模板所有内容(包括但不限于文字、内容、图片以及样式)为本站原创,未经本站同意不得复制和修改用于其它任何用途!</p></div>
			</div>
		</div>
		<div class="modal-footer">
		</div>
	</div>
	
	<%-- 上传网站代码  --%>
    <div class="modal hide fade upload-code-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-upload-alt"></i> 上传网站代码
		</div>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="upload-code-form pull-left">
				<input type="hidden" name="type" value="upload"/>
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend input-append">
							<span class="add-on">个性域名</span>
							<input class="span1 sld" name="sld" type="text" minlength="3" maxlength="10" placeholder="个性域名..." required>
							<span class="add-on">.hgm8.com</span>
						</div>
						<span class="help-inline sld-info" data-label="网站域名"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="fileupload fileupload-new upload-code-txt" data-provides="fileupload">
							<div class="input-prepend input-append">
								<span class="add-on">服务器列表</span>
								<div class="uneditable-input span2">
									<i class="icon-file fileupload-exists"></i> <span class="fileupload-placeholder">请选择服务列表文件(.txt)...</span><span class="fileupload-preview serverList"></span>
								</div>
								<span class="btn btn-file">
									<span class="fileupload-new"><i class="icon-folder-open"></i> 浏览</span>
									<span class="fileupload-exists"><i class="icon-refresh"></i> 修改</span>
									<input class="span" name="serverList" type="file" data-uploadtype="file" placeholder="请选择服务列表文件(.txt)..." data-name="serverList"/>
								</span>
								<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><i class="icon-trash"></i> 清除</a>
							</div>
						</div>
					</div>
		    	</div>
		    	<div class="control-group">
					<div class="controls">
						<div class="fileupload fileupload-new upload-code-zip" data-provides="fileupload">
							<div class="input-prepend input-append">
								<span class="add-on">代码压缩包</span>
								<div class="uneditable-input span2">
									<i class="icon-file fileupload-exists"></i> <span class="fileupload-placeholder">请选择代码压缩包(.zip)...</span><span class="fileupload-preview codePackage"></span>
								</div>
								<span class="btn btn-file">
									<span class="fileupload-new"><i class="icon-folder-open"></i> 浏览</span>
									<span class="fileupload-exists"><i class="icon-refresh"></i> 修改</span>
									<input class="span" name="codePackage" type="file" placeholder="请选择代码压缩包(.zip)..." data-uploadtype="file" data-name="codePackage"/>
								</span>
								<a href="#" class="btn fileupload-exists" data-dismiss="fileupload"><i class="icon-trash"></i> 清除</a>
							</div>
						</div>
					</div>
		    	</div>
		    	<div class="control-group">
		    		<div class="controls">
	    				<input class="span agreement" name="agreement" type="checkbox">
	    				<span class="help-inline">同意Hgm123游戏资讯发布协议</span>
	    				<span class="help-inline">
		  					<a class="ag_target" href="html/agreement.html" role="button" data-toggle="modal" data-target=".agreement-modal" title="请仔细阅读协议"><i class="icon-list-alt"></i></a>
	 					</span>
			   			<span class="help-inline agreement-info" data-label="同意协议"></span>
		    		</div>
		    	</div>
		   	</form>
		</div>
		<div class="modal-footer">
			<div class="usage-tip pull-left"><i class="icon-tag"></i> 上传网站代码建站收费标准为:10元/天(3天起)</div>
			<button class="btn upload-code-btn" data-loading-text="<i class='icon-spinner icon-spin'></i> 上传中..."><i class="icon-upload-alt"></i> 立即上传</button>
		</div>
	</div>
	
	<%-- 关于hgm123窗口 --%>
    <div class="modal hide fade about-modal" role="dialog" aria-labelledby="aboutModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-exclamation-sign"></i> 关于Hgm123
		</div>
		<div class="modal-body">
			<p><a href='./' target="_blank">Hgm123.com</a>是最专业最权威的游戏资讯服务平台,友好的操作界面,快捷的操作流程,先进的设计理念,目前开放如下功能:</p>
			<div class="row-fluid roadmap">
				<div class="span4">
					<blockquote>
					    <dl>
					    	<dt>游戏网站</dt>
					    	<dd>一键建站</dd>
					    	<dd>整站托管</dd>
					    	<dd>...</dd>
					    </dl>
				    </blockquote>
			    </div>
			    <div class="span4">
				    <blockquote>
					    <dl>
					    	<dt>游戏资讯</dt>
					    	<dd>GM发布</dd>
					    	<dd>玩家分享</dd>
					    	<dd>点击统计</dd>
					    	<dd>...</dd>
					    </dl>
				    </blockquote>
			    </div>
			    <div class="span4">
				    <blockquote>
					    <dl>
					    	<dt>统计数据</dt>
					    	<dd>游戏版本</dd>
					    	<dd>在线时段</dd>
					    	<dd>区域分布</dd>
					    	<dd>...</dd>
					    </dl>
				    </blockquote>
			    </div>
		    </div>
			<div>
				<hr>
				<ul class="inline">
					<li><strong>联系方式:</strong></li>
		    		<li><i class="icon-envelope"></i><a href="mailto:214508914@qq.com"> 电子邮件</a></li>
		    		<li><a href="http://user.qzone.qq.com/214508914" target="_blank"><img alt="关注QQ空间" src="resources/images/qzone.png"> QQ空间</a></li>
		    		<li><a href="http://t.qq.com/v_hgm123" target="_blank"><img alt="关注腾讯微博" src="resources/images/tencent-weibo.png"> 腾讯微博</a></li>
		    		<li><a href="http://weibo.com/hgm8" target="_blank"><img alt="关注新浪微博" src="resources/images/sina-weibo.png"> 新浪微博</a></li>
		   		</ul>
			</div>
    	</div>
    	<div class="modal-footer">
			<a class="btn pull-right" data-dismiss="modal" aria-hidden="true"><i class="icon-remove-circle"></i> 关闭</a>
		</div>
	</div>
	
    <div class="container">
    	<div class="data-table">
    		<table class="table table-bordered table-hover table-condensed datatable">
				<caption class="container hgm-ad">
	      			<div class="carousel slide">
					    <div class="carousel-inner">
					    	<div class="active item">
					    		<a target="_blank" href="./about">
					    			<!-- <img src="resources/images/banner/jd.jpg" alt="" /> -->
				    			</a>
			    			</div>
					    	<div class="item">
					    		<a target="_blank" href="./about">
			  						<!-- <img src="resources/images/banner/taobao.png" alt="" /> -->
		 						</a>
							</div>
					    </div>
			  		</div>
				</caption>
				<thead>
					<tr>
						<th width="12%"><i class="icon-flag"></i> 游戏版本</th>
						<th width="13%"><i class="icon-bookmark"></i> 游戏名称</th>
						<th width="18%" filter-type='ddl'><i class="icon-calendar"></i> 开区时间</th>
						<th width="10%"><i class="icon-road"></i> 线路</th>
						<th width="30%"><i class="icon-exclamation-sign"></i> 游戏介绍</th>
						<th width="10%"><i class="icon-link"></i> 链接地址</th>
						<th width="7%"><i class="icon-star"></i>人气</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>  
						<c:when test="${advertises.size() == 0}">
							<tr>
								<td colspan="7"><i class="icon-coffee"></i> 暂无数据 , 稍候为您呈现最新最热的游戏...</td>
							</tr>
							<tr>
								<td colspan="7">
									爱 <span class="lead">PK</span> , 爱 <span class="lead">赌博</span> ,不爱 <span class="lead">打装备 </span>和 <span class="lead">升级</span> .<br>
									爱 <span class="lead">极品</span> ,<br>
									最爱 <span class="lead">暴</span> 好装备de超级大 <span class="lead">BOSS</span> .<br>
									和你一样 , 我是 <span class="lead">骨灰玩家</span> .<br>
									<br>
									爱 <span class="lead">兄弟</span> , 爱 <span class="lead">朋友</span> ,不爱 <span class="lead">人妖</span> 和 <span class="lead">骗子</span>.<br>
									爱 <span class="lead">激情</span> ,<br>
									最爱别人不敢出 <span class="lead">安全区</span> .<br>
									我们不是在 <span class="lead">搞基</span> .<br>
									我们是 <span class="lead">好哥们,123</span> .<br>
						              	<img alt="" src="resources/images/hgm123_logo_s.png">
						              	<small>- 最 <span class="lead">专业</span> , 最 <span class="lead">权威</span> 的游戏资讯服务平台 -</small>
						              	<img width="100" height="100" alt="关注微信,分享游戏!" src="resources/images/wx_hgm123.jpg">
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="advertise" items="${advertises}">
								<tr>
									<td>${advertise.gameName}</td>
									<td>${advertise.gameEdition}</td>
									<td>${advertise.openTime}</td>
									<td>${advertise.lineType}</td>
									<td>${advertise.gameDescription}</td>
									<td class="click-view" data-id="${advertise.id}" data-stamp="0000000">${advertise.gameUrl}</td>
									<td class="game-popularity">${advertise.gamePopularity}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
    	</div>
    	<%-- <div class="load-toolbar">
	    	<div class="btn-group">
				<button class='btn btn-success load-last' data-querytime="<c:out value="${querytimes.last}"/>" data-loading-text="<i class='icon-spinner icon-spin icon-spin'></i> 加载【<c:out value="${querytimes.last}"/>】数据..."><i class="icon-backward"></i> 加载【<c:out value="${querytimes.last}"/>】数据...</button>
				<button class='btn btn-success load-next' data-querytime="<c:out value="${querytimes.next}"/>" data-loading-text="<i class='icon-spinner icon-spin icon-spin'></i> 加载【<c:out value="${querytimes.next}"/>】数据..."><i class="icon-forward"></i> 加载【<c:out value="${querytimes.next}"/>】数据...</button>
			</div>
    	</div> --%>
			
    </div>
	<div class="btn-group btn-group-vertical fix-box-middle">
	    <a title="回到顶部" class="btn go-top"><i class="icon-angle-up"></i></a>
	    <a title="反馈意见" class="btn" data-toggle="modal" data-target=".feedback-modal"><i class="icon-comment-alt"></i></a>
	    <a title="到达底部" class="btn go-bottom"><i class="icon-angle-down"></i></a>
	</div>
	
	<div class="modal hide fade feedback-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-comment-alt"></i> 意见反馈
		</div>
		<div class="modal-body">
			<form class="form feedback-form">
				<div class="control-group">
					<div class="input-prepend">
						<span class="add-on">联系邮箱</span>
						<input class="span2 feedback-email"  tabindex="2" name="email" type="email" placeholder="输入联系邮箱..." data-placement="right" required>
					</div>
					<div class="input-prepend">
						<span class="add-on">Hgm账号</span>
						<input class="span2 feedback-upassid"  tabindex="2" name="upassid" type="text" placeholder="输入通行证账号..." data-placement="right" required>
					</div>
				</div>
				<div class="control-group">
					<div class="input-prepend">
						<span class="add-on">反馈类别</span>
						<select class="feedback-category" name="category">
							<option value="css"><i class="icon-magnet"></i>样式兼容</option>
							<option value="ui"><i class="icon-eye-open"></i>界面美观</option>
							<option value="ue"><i class="icon-thumbs-down"></i>用户体验</option>
							<option value="fn"><i class="icon-sitemap"></i>模块功能</option>
							<option value="others"><i class="icon-lemon"></i>其他问题</option>
						</select>
					</div>
					<span class="help-inline category-info text-info"><i class="icon-exclamation-sign"></i> 浏览器兼容导致页面布局和样式错乱</span>
				</div>
				<div class="control-group">
					<div class="input-prepend">
						<span class="add-on">反馈内容</span>
						<textarea class="span4 feedback-content" rows="5" tabindex="2" name="content" type="textarea" placeholder="输入意见/反馈内容..." data-placement="right" required></textarea>
					</div>
				</div>
			</form>
		</div>
		<div class="modal-footer">
			<span class="pull-left feedback-info"><i class="icon-tag"></i> 请输入联系邮箱和通行证账号,便于工作人员及时与您联系!</span>
			<button type="button" class="btn feedback_btn" data-loading-text="<i class='icon-spinner icon-spin'></i>提交中..."><i class="icon-ok-circle"></i> 提交</button>
		</div>
	</div>
	
	<%-- 协议内容 --%>
    <div class="modal hide fade agreement-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<i class="icon-print"></i> 用户协议
		</div>
		<div class="modal-body">
		</div>
		<div class="modal-footer">
			<div class="third-label pull-left"><i class="icon-tag"></i> 请仔细阅读协议内容,有任何疑问可以与网站工作人员联系!</div>
			<button class="btn check-btn" data-dismiss="modal" aria-hidden="true"><i class="icon-check"></i> 已阅读</button>
		</div>
	</div>
	
	<%-- 免责声明 --%>
    <div class="modal hide fade disclaimer-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-header">
			<i class="icon-legal"></i> <strong>Hgm123.com 免责声明</strong>
		</div>
		<div class="modal-body disclaimer-text">
			<p>访问者在接受本网站服务之前,请务必仔细阅读本声明.访问者访问本网站的行为以及通过各类方式利用本网站的行为,都将被视作是对本声明全部内容的无异议的认可.</p>
			<p>第一条 访问者在从事与本网站相关的所有行为(包括但不限于访问浏览,利用,转载,宣传介绍)时,必须以善意且谨慎的态度行事;访问者不得故意或者过失的损害本网站的各类合法权益,不得利用本网站以任何方式直接或者间接的从事违反中华人民共和国法律,国际公约以及社会公德的行为.</p>
			<p>第二条 本网站充分尊重转载文章之作者的著作权以及客户的商标权等知识产权.本网站合理信赖转载文章之原登载网站已经征得著作权人的同意并与著作权人就相关问题作出了妥善处理,同时本网站合理信赖客户对其在本网站登载的企业商标享有知识产权,并许可本网站合理使用.本网站对于有关文章的转载以及客户商标的使用属于合理使用,因此与之有关的知识产权纠纷本网站不承担任何责任.本网站郑重提醒访问者:请在转载有关文章或者使用有关企业商标时务必尊重其知识产权,否则与之有关的知识产权纠纷本网站免责.
				同时,对本网站原创文章以及本网站标识,本网站享有自主知识产权.侵犯本网站之知识产权的,本网站有权追究其法律责任.</p>
			<p>第三条 本网站刊载的各类文章,广告,访问者在本网站发表的观点以及以链接形式推荐的其他网站内容,仅为提供更多信息以参考使用或者学习交流,并不代表本网站观点,也不构成任何投资建议.本网站不保证其内容,产品,服务或其他材料的真实性和合法性.对于访问者根据本网站提供的信息所做出的一切行为,本网站不承担任何责任. </p>
			<p>第四条 以下情形导致的个人信息泄露,本网站免责:</p>
				<p>(一)政府部门,司法机关等依照法定程序要求本网站披露个人资料时,本网站将根据执法单位之要求或为公共安全之目的提供个人资料;</p>
				<p>(二)由于用户将个人密码告知他人或与他人共享注册账户,由此导致的任何个人资料泄露; </p>
				<p>(三)任何由于计算机问题,黑客攻击,计算机病毒侵入或发作,因政府管制而造成的暂时性关闭等影响网络正常经营的不可抗力而造成的个人资料泄露,丢失,被盗用或被窜改等; </p>
				<p>(四)由于与本网站链接的其他网站所造成之个人资料泄露; </p>
			<p>第五条 本网站若因线路及本网站控制范围外的硬件故障或其它不可抗力而导致暂停服务,于暂停服务期间给用户造成的一切损失,本网站不承担任何法律责任. </p>
			<p>第六条 除本网站注明之服务条款外,其他一切因使用本网站而引致之任何意外,疏忽,诽谤,版权或知识产权侵犯及其所造成的损失(包括因下载而感染电脑病毒),本网站不承担任何法律责任.</p>
			<p>第七条 若因本网站产生任何诉诸于诉讼程序的法律争议,均以本网站所有者东莞市金地网络科技有限公司所在地的法院,即东莞市人民法院为管辖法院.</p>
			<p>第八条 本声明之订立,修改,更新及最终解释权东莞市金地网络科技有限公司所有.</p> 
			<p>第九条 以上声明于即日公布并生效</p>
		</div>
		<div class="modal-footer">
			<div class="third-label pull-left"><i class="icon-envelope"></i> 联系本站： <a href="mailto:admin@hgm123.com">admin@hgm123.com</a></div>
			<button class="btn print-btn"><i class="icon-print"></i> 打印</button>
			<button class="btn check-btn" data-dismiss="modal" aria-hidden="true"><i class="icon-check"></i> 已阅读</button>
		</div>
	</div>
	
    <div class="footer">
  		&copy;2013 HGM123.COM&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">广告合作</a>&nbsp;&nbsp;&nbsp;|<a href="#" data-toggle="modal" data-target=".disclaimer-modal">免责声明</a>&nbsp;&nbsp;&nbsp;|<!-- <script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script> -->
 	</div><%-- /footer --%>
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
 	<script src="myLib/jquery/jquery.form.js"></script>
 	<script src="resources/js/sugar-1.3.9.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
    <script src="myLib/bootstrap/js/bootstrap-ajax-tabs.js"></script>
    <script src="myLib/bootstrap/js/bootstrap-fileupload.js"></script>
 	<script src="myLib/jquery/jquery.cookie.js"></script>
    <script src="myLib/jquery/jquery.md5.js"></script>
    <script src="myLib/jquery/plugins/jrumble/jquery.jrumble.1.3.js"></script>
    <script src="myLib/jquery/plugins/scrolltb/scroll-startstop.events.jquery.js"></script>
    <script src="myLib/jquery/plugins/showLoading/js/jquery.showLoading.js"></script>
    <script src="myLib/jquery/plugins/artDialog-5.0.3/artDialog.min.js"></script>
    <script src="myLib/messenger/js/messenger.min.js"></script>
    <script src="myLib/messenger/js/messenger-theme-future.js"></script>
    <%-- <script src="myLib/jquery/plugins/tableFilter/table.filter.min.js"></script> --%>
    <script src="myLib/jquery/plugins/textSearch/jquery.textSearch-1.0.js"></script>
    <script src="myLib/jquery/plugins/chosen/chosen.jquery.js"></script>
    <script src="myLib/jquery/plugins/clock/js/jquery.clock.js"></script>
 	<script src="myLib/jquery/plugins/datetimepicker/js/bootstrap-datetimepicker.js"></script>
    <script src="myLib/jquery/plugins/datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
 	<script src="resources/js/index.js?v=20130501"></script>
  </body>
</html>