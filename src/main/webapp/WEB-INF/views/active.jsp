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
	<link rel="stylesheet" type="text/css" href="resources/css/active.css?v=20130429">
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
    	<div class="content">
    		<div class="active">
	    		<div class="active-result"><img class="img-polaroid" alt="" src="resources/images/result/${result}.png"></div>
		    		<c:choose>
		    			<c:when test="${result == 'success'}">
		    				<div class="alert alert-block alert-success fade in active-message">${message }<br>
		    			</c:when>
		    			<c:when test="${result == 'noexist'}">
		    				<div class="alert alert-block alert-error fade in active-message">${message }<br>
		    			</c:when>
		    			<c:when test="${result == 'expiry'}">
		    				<div class="alert alert-block alert-warning fade in active-message">${message }<br>
		    			</c:when>
		    			<c:when test="${result == 'actived'}">
		    				<div class="alert alert-block alert-info fade in active-message">${message }<br>
		    			</c:when>
		    			<c:otherwise>
		    				<div class="alert alert-block alert-info fade in active-message">激活过程出现未知异常,请联系Hgm123管理员!<br>
		    			</c:otherwise>
		    		</c:choose>
	    			<hr>
    				<p>
    					浏览器将在<span class="count">3</span>秒后跳转至Hgm123首页
    				</p>
   					<a class="btn go-index" href="./"><i class="icon-home"></i> 立即跳转</a>
   				</div>
   			</div>
   		</div>
   	</div>
	<div class="footer">
		<hr>
  		&copy;2013 Hgm123&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">好GM必读</a>&nbsp;&nbsp;&nbsp;|<a href="./help">关于hgm123</a>&nbsp;&nbsp;&nbsp;|<a href="mailto:214508914@qq.com"><i class="icon-envelope"></i> Me</a>&nbsp;&nbsp;&nbsp;
  		<div><script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script></div>
	</div>
    
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
 	<script src="myLib/jquery/jquery.cookie.js"></script>
 	<script src="resources/js/active.js"></script>
  </body>
</html>