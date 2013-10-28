<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<meta http-equiv="description"
	content="国内第一个游戏GM服务平台,提供最新最热游戏版本,广告网站等游戏排行和资讯">

<link rel="shortcut icon" href="resources/images/favicon.ico" />
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type='text/javascript' src='/dwr/interface/dwrUtil.js'></script>
<script type='text/javascript' src='/dwr/interface/dwrSystem.js'></script>
<script type="text/javascript">
	function sayHello(){
		dwrUtil.sayHello(function(data){
			console.log(data);
		});
	}
	
	function grebData(){
		dwrSystem.loadData("http://9pk.118sh.com/?=ADTAG=stat");
		window.setInterval(function(){
			dwrSystem.handleProgress(function(data){
				console.log(data);
			});
		},100);
	}
	
</script>
</head>
<body>
	<input type="button" value="sayHello" onclick="sayHello();" />|<input type="button" value="grebData" onclick="grebData();" />
	<div id="showMessage"></div>  
</body>
</html>