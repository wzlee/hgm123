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
<script type='text/javascript' src='/dwr/interface/__System.js'></script>
<script type='text/javascript' src='/dwr/engine.js'></script>
<script type='text/javascript' src='/dwr/util.js'></script>
<script type="text/javascript">
function sendMessage(){  
    var username = $("name").value;  
    alert(username);  
    Demo.sayHello(
	username, {
			//指定回调函数  
			callback : getMsg,
			//指定超时时长  
			timeout : 1000,
			//指定错误处理函数  
			errorHandler : function(message) {
				alert("错误提示: " + message);
			},
			//指定  
			warningHandler : function(message) {
				alert("Oops: " + message);
			},
			textHtmlHandler : function(message) {
				alert("Oops: " + message);
			},
			exceptionHandler : function(message) {
				alert("Oops: " + message);
			},
			//指定发送请求的方式  
			httpMethod : 'POST',
			async : true,
			//指定发送请求之前的勾子函数  
			preHook : function() {
				alert('远程调用之前...')
			},
			//指定发送请求之后的勾子函数  
			postHook : function() {
				alert('远程调用之后...')
			}
		});
	}
	//回调函数  
	function getMsg(data) {
		alert("s");
		document.getElementById("show").innerHTML = data;
	}
</script>
</head>
<body>
	<h3>  
            使用JSON格式回调  
        </h3>  
        请输入您的名字  
        <input id="name" name="name" type="text" />  
        <br />  
        <input type="button" value="使用JSON格式回调" onclick="sendMessage();" />  
        <hr />  
        下面是服务器的回应:  
        <br />  
        <div id="show"></div> 
</body>
</html>