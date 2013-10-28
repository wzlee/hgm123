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
    
    <title>好哥们,专业游戏GM服务平台</title>
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
	<!-- <link rel="stylesheet" type="text/css" href="myLib/datatable/css/jquery.dataTables.css"> -->
	<link rel="stylesheet" type="text/css" href="resources/css/test.css?v=20130409">
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
           			<li class="divider-vertical"></li>
           			<li class="dropdown">
                  		<a data-toggle="dropdown" class="dropdown-toggle" href="#"><i class="icon-globe"></i> 网站地图 <b class="caret"></b></a>
                   		<ul class="dropdown-menu">
                     		<li><a href="index.html" target="_blank">baidu样式首页</a></li>
	                     	<li><a href="#">人气热服</a></li>
                    		<li><a href="#">今日上榜</a></li>
	                     	<li><a href="#">Hgm论坛</a></li>
	                     	<li><a href="html/about.html" role="button" data-toggle="modal" data-target="#about"><i class="icon-exclamation-sign"></i> 关于Hgm123</a></li>
	                     	<li class="divider"></li>
	                     	<li><a href="jsLib/bootstrap-master/docs/index.html" target="_blank">Bootstrap Docs</a></li>
	                     	<li><a href="jsLib/FortAwesome/docs/index.html" target="_blank">FortAwesome</a></li>
	                     	<li><a href="jsLib/data-tables/examples/index.html" target="_blank">DataTables</a></li>
	                   	</ul>
                	</li>
              	</ul>
        	</div><!-- container -->
      	</div><!-- navbar-inner -->
    </div><!-- navbar -->	
    
    <div class="container">
	    <ul class="breadcrumb">
		  	<li><i class="icon-home"></i> <a href="./">首页</a> <span class="divider">/</span></li>
		  	<li class="active"><i class="icon-credit-card"></i> 通行证登陆</li>
		</ul>
		<div class="modal-body">
			<div class="hgm-logo pull-left">
			  	<img src="resources/images/hgm123_logo_s.png">
			</div>
			<form class="signin-form pull-left">
				<div class="control-group">
			    	<div class="controls">
						<div class="input-prepend">
							<span class="add-on">账号:</span>
							<input class="span2 upassid" name="upassid" type="text" placeholder="输入通行证账号..." data-required>
						</div>
						<span class="help-inline upassid-error-info" data-label="帐号"></span>
					</div>
				</div>
				<div class="control-group">
					<div class="controls">
						<div class="input-prepend">
							<span class="add-on">密码:</span>
							<input class="span2 password" name="password" type="password" placeholder="输入通行证密码..." data-required>
						</div>
						<span class="help-inline password-error-info" data-label="密码"></span>
					</div>
		    	</div>
		    	<div class="control-group">
		    		<div class="controls">
	    				<input class="span keeplogin" name="keeplogin" type="checkbox">
	    				<span class="help-inline">保持登陆</span>
	    				<span class="help-inline keeplogin-warning-tip"><i class="icon-exclamation-sign"></i> 公共场合请慎用!</span>
		    		</div>
		    	</div>
		   	</form>
		    <span class="login-tip pull-left"><p><i class="icon-tag"></i> 请妥善保存账号和密码,如果忘记密码,点击 <a href="jsp/find-pd.jsp" target="_blank">找回密码</a> .</p></span>
			<button class="btn login_btn" data-loading-text="<i class='icon-spinner icon-spin'></i> 正在登陆"><i class="icon-signin"></i> 立即登陆</button>
		</div>
    </div>
	<div class="modal-footer">
  		&copy;2013 Hgm123&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">好GM必读</a>&nbsp;&nbsp;&nbsp;|<a href="./help">关于hgm123</a>&nbsp;&nbsp;&nbsp;|<a href="mailto:214508914@qq.com"><i class="icon-envelope"></i> Me</a>&nbsp;&nbsp;&nbsp;
  		<script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script>
	</div>
    
	
   <%--  <div class="footer">
  		<hr size="3">
  		&copy;2013 Hgm123&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">好GM必读</a>&nbsp;&nbsp;&nbsp;|<a href="./help">关于hgm123</a>&nbsp;&nbsp;&nbsp;|<a href="mailto:214508914@qq.com"><i class="icon-envelope"></i> Me</a>&nbsp;&nbsp;&nbsp;
  		<script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script>
 	</div><!-- /footer --> --%>
 	
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
 	<script src="myLib/jquery/jquery.cookie.js"></script>
    <script src="myLib/jquery/plugins/validate/jquery-validate.js"></script>
    <script src="myLib/jquery/jquery.md5.js"></script>
 	<script src="resources/js/login.js"></script>
  </body>
</html>