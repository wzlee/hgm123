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
    
    <title>好哥们,专业游戏GM服务平台</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="私服,私服广告,私服GM,家族">
	<meta http-equiv="description" content="国内第一个游戏GM服务平台,提供最新最热游戏版本,广告网站等游戏排行和资讯">
	
    <link rel="shortcut icon" href="resources/images/favicon.ico" /> 
    <link rel="stylesheet" type="text/css" href="myLib/jquery/jqueryui/jquery-ui.css">
    <link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/jTable/themes/lightcolor/gray/jtable.css">
  </head>
  
  <body>
  	<div class="passport-grid"></div>
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/jquery/jqueryui/jquery-ui.js"></script>
    <script src="myLib/jquery/plugins/jTable/jquery.jtable.min.js"></script>
    <script type="text/javascript" src="myLib/jquery/plugins/jTable/localization/jquery.jtable.zh-CN.js"></script>
 	<script src="myLib/jquery/plugins/jTable/index.js?v=20120501"></script>
  </body>
</html>