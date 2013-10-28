<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh">
	<head>
    <base href="<%=basePath%>">
    
    <title>HGM123——注册通行证</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="私服,私服广告,私服GM,家族">
	<meta http-equiv="description" content="国内第一个游戏GM服务平台,提供最新最热游戏版本,广告网站等游戏排行和资讯">
	
    <link rel="shortcut icon" href="resources/images/favicon.ico" /> 
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/chosen/chosen.css">
	<link rel="stylesheet" type="text/css" href="resources/css/register.css?v=20130409">
  </head>
  
  <body>
	<div class="navbar navbar-static-top">
    	<div class="navbar-inner">
      		<div class="container">
          		<ul class="nav pull-left">
       				<li class="index"><a href="./">首页</a></li>
           			<li class="divider-vertical"></li>
            		<li><a href="#"><i class="icon-home"></i>设为首页</a></li>
               	</ul>
               	<ul class="nav pull-right">
               		<li><a href="http://user.qzone.qq.com/214508914" target="_blank"><img alt="关注QQ空间" src="resources/images/qzone.png"> QQ空间</a></li>
		    		<li><a href="http://t.qq.com/v_hgm123" target="_blank"><img alt="关注腾讯微博" src="resources/images/tencent-weibo.png"> 腾讯微博</a></li>
		    		<li><a href="http://weibo.com/hgm8" target="_blank"><img alt="关注新浪微博" src="resources/images/sina-weibo.png"> 新浪微博</a></li>
           			<li class="divider-vertical"></li>
           			<li class="dropdown">
                  		<a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-globe"></i> 网站地图 <b class="caret"></b></a>
                   		<ul class="dropdown-menu">
	                     	<li><a href="#"><i class="icon-share"></i> 游戏资讯发布</a></li>
                    		<li><a href="#"><i class="icon-desktop"></i> 创建游戏网站</a></li>
                    		<li><a href="#"><i class="icon-bar-chart"></i> 游戏数据统计</a></li>
	                     	<li><a href="#"><i class="icon-group"></i> 好GM联盟</a></li>
	                     	<li class="divider"></li>
	                     	<li><a href="#" role="button" data-toggle="modal" data-target=".about-modal"><i class="icon-exclamation-sign"></i> 关于与帮助</a></li>
	                   	</ul>
                	</li>
              	</ul>
        	</div><!-- container -->
      	</div><!-- navbar-inner -->
    </div><!-- navbar -->	
    
    <!-- 关于hgm123窗口 -->
    <div class="modal hide fade about-modal" role="dialog" aria-labelledby="aboutModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
			<i class="icon-exclamation-sign"></i> 关于Hgm123
		</div>
		<div class="modal-body">
			<p><a href='./' target="_blank">Hgm123.com</a>是最专业最权威的游戏服务平台,友好的操作界面,快捷的操作流程,先进的设计理念,目前开放如下功能:</p>
			<div class="row-fluid">
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
		<div class="fade in signup-modal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-header">
				<h5 id="myModalLabel"><i class="icon-credit-card"></i> 注册通行证</h5>
			</div>
			<div class="modal-body">
				<div class="hgm-logo pull-left">
				  	<img src="${thirdBean.avatar }" alt="${thirdBean.nick }" onerror="this.src='resources/images/hgm123_logo_s.png;this.onerror=null'">
				</div>
				<form class="signup-form pull-left">
					<input type="hidden" name="${thirdBean.code }Openid" value="${thirdBean.openid }">
					<input type="hidden" name="${thirdBean.code }Nick" value="${thirdBean.nick }">
					<input type="hidden" name="${thirdBean.code }Avatar" value="${thirdBean.avatar }">
					<div class="control-group">
							<div class="input-prepend">
								<span class="add-on">昵称:</span>
								<input class="span2 nickname" name="nickname" value="${thirdBean.nick }" type="text" minlength="3" maxlength="10" placeholder="通行证显示的昵称..." required>
							</div>
							<span class="help-inline nickname-info" data-label="昵称"></span>
					</div>
					<div class="control-group">
				    	<div class="controls">
							<div class="input-prepend">
								<span class="add-on">账号:</span>
								<input class="span2 upassid" name="upassid" value="${thirdBean.name }" type="text" minlength="3" maxlength="10" placeholder="3-10位字母/数字组合..." required>
							</div>
							<span class="help-inline upassid-info" data-label="帐号"></span>
						</div>
					</div>
					<div class="control-group">
						<div class="controls">
							<div class="input-prepend">
								<span class="add-on">密码:</span>
								<input class="span2 password" name="password" type="password" minlength="6" maxlength="20" placeholder="6-20位密码组合..." required>
							</div>
							<span class="help-inline password-info" data-label="密码"></span>
						</div>
				   	</div>
				   	<div class="control-group">
						<div class="input-prepend">
							<span class="add-on">确认:</span>
							<input class="span2 confirm" name="confirm" type="password" minlength="6" maxlength="20" placeholder="再次输入密码确认..." required>
						</div>
						<span class="help-inline confirm-info" data-label="确认"></span>
				   	</div>
					<div class="control-group">
							<div class="input-prepend">
								<span class="add-on">邮箱:</span>
								<input class="span2 email" name="email" type="email" placeholder="接收通行证激活邮件..." required>
							</div>
							<span class="help-inline email-info" data-label="邮箱"></span>
					</div>
					<div class="control-group">
							<div class="input-prepend">
								<span class="add-on">Q Q:</span>
								<input class="span2 qq" name="qq" type="number" minlength="5" maxlength="10" placeholder="加入官方群验证号码...">
							</div>
							<span class="help-inline qq-info" data-label="QQ"></span>
					</div>
					<div class="control-group">
						<div class="input-prepend">
							<span class="add-on">身份:</span>
							<select class="chzn-select groupname" style="width:170px" name="groupname" data-placeholder="--请选择身份--" required>
							  	<option value="player">游戏玩家</option>
							  	<option value="gm">游戏GM</option>
							</select>
						</div>
					</div>
				   	<div class="control-group">
		  				<input class="agreement" name="agreement" type="checkbox">
		  				<span class="help-inline">同意使用协议: </span>
		  				<span class="help-inline">
		  					<a class="ag_target" href="html/agreement.html" role="button" data-toggle="modal" data-target="#agreement" title="点击阅读使用协议"><i class="icon-list-alt"></i></a>
	 					</span>
			   			<span class="help-inline agreement-info" data-label="同意协议"></span>
			   		</div>
				</form>
			</div>
			<div class="modal-footer">
				<span class="reg-tip pull-left"><i class="icon-tag"></i> 强烈建议邮箱设置为常用的邮箱,用于收取通行证激活邮件!</span>
				<button type="button" class="btn reg_btn" data-loading-text="<i class='icon-spinner icon-spin'></i>提交中..."><i class="icon-ok-circle"></i> 提交</button>
			</div>
		</div>
		
		<!-- 注册提示窗口 -->
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
				<span class="reg-tip pull-left"><i class="icon-tag"></i> 如果找不到Hgm123发送的激活邮件,请尝试在邮箱垃圾邮件中查找!</span>
				<a class="btn pull-right go-mailbox" href="#"><i class="icon-envelope"></i> 立即激活</a>
			</div>
		</div>
	</div>
	
  	<div class="footer">
  		&copy;2013 Hgm123&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">投诉建议</a>&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">商务洽谈</a>&nbsp;&nbsp;&nbsp;|<a href="./help">广告合作</a>&nbsp;&nbsp;&nbsp;|<a href="mailto:214508914@qq.com"><i class="icon-envelope"></i> 联系站长</a>&nbsp;&nbsp;&nbsp;
  		<div><script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script></div>
 	</div><!-- /footer -->
	
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
    <script src="myLib/jquery/plugins/chosen/chosen.jquery.js"></script>
    <script src="myLib/jquery/plugins/jrumble/jquery.jrumble.1.3.js"></script>
 	<script src="resources/js/register.js"></script>
  </body>
</html>