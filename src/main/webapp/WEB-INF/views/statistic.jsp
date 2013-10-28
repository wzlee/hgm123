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
    
    <title>好哥们游戏资讯服务平台|数据统计中心</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="私服,私服广告,私服GM,家族">
	<meta http-equiv="description" content="国内第一个游戏GM服务平台,提供最新最热游戏版本,广告网站等游戏排行和资讯">
	
    <link rel="shortcut icon" href="resources/images/favicon.ico" /> 
	<link rel="stylesheet" type="text/css" href="myLib/extjs/resources/css/ext-all.css">
  </head>
  
  <body>
  	<div id="loading-mask" style=""></div>
   	<div id="loading">
       <div class="loading-indicator">
           <img src="myLib/extjs/resources/themes/images/loading.gif" width="32" height="32" style="margin-right:8px;float:left;vertical-align:top;"/><a href="http://www.hgm123.com" target="_blank">www.Hgm123.com</a>
           <br /><span id="loading-msg">Loading style...</span>
       </div>
    </div>
    <script type="text/javascript">document.getElementById('loading-msg').innerHTML = 'Loading JS Core...';</script>
	<script type="text/javascript" src="myLib/extjs/ext-all.js"></script>
	<script type="text/javascript" src="myLib/extjs/ext-lang-zh_CN.js"></script>
	<script type="text/javascript">document.getElementById('loading-msg').innerHTML = 'Initializing...';</script>
 	<script type="text/javascript"src="app/app.js"></script>
  </body>
</html>