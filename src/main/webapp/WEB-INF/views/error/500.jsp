<%@ page contentType="text/html;charset=UTF-8" isErrorPage="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="org.slf4j.Logger,org.slf4j.LoggerFactory" %>
<%response.setStatus(200);%>

<%
	Throwable ex = null;
	if (exception != null)
		ex = exception;
	if (request.getAttribute("javax.servlet.error.exception") != null)
		ex = (Throwable) request.getAttribute("javax.servlet.error.exception");

	//记录日志
	Logger logger = LoggerFactory.getLogger("500.jsp");
	logger.error(ex.getMessage(), ex);
%>

<!DOCTYPE html>
<html>
<head>
	<title>500 - 系统内部错误</title>
	<style type="text/css">
		.error-context{
			width:500px;
			margin:50px auto;
		}
	</style>
</head>

<body>
	<div class="error-context">
		错误代码:<span>500</span><br>
		错误描述:<span>系统发生内部错误.</span><br>
		错误信息:<span>${message }</span><br>
		<p><a href="<c:url value="/"/>">返回首页</a></p>
	</div>
</body>
</html>