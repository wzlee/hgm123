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
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/showLoading/css/showLoading.css">
	<link rel="stylesheet" type="text/css" href="myLib/jquery/plugins/jTable/themes/jqueryui/jtable_jqueryui.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/index.css?v=20130409">
  </head>
  
  <body>
	<header class="container">
			<h1>Conditional Demo</h1>
		</header>
		 <div class="load-toolbar">
	    	<div class="btn-group" data-toggle="buttons-radio">
				<button class='btn load-last' href="#myCarousel" data-slide-to="0"><i class="icon-backward"></i> 加载【<c:out value="${querytimes.last}"/>】数据...</button>
				<button class='btn load-now' href="#myCarousel" data-slide-to="1"><i class="icon-ok"></i> 加载【<c:out value="${querytimes.last}"/>】数据...</button>
				<button class='btn load-next' href="#myCarousel" data-slide-to="2"><i class="icon-forward"></i> 加载【<c:out value="${querytimes.next}"/>】数据...</button>
			</div>
    	</div>
	    <div id="myCarousel" class="carousel slide" data-interval=false>
		    <!-- Carousel items -->
		    <div class="carousel-inner">
		    	<div class="active item">
					<table class="table table-bordered table-hover table-condensed datatable">
						<caption>
			      			<div class="carousel slide">
							    <div class="carousel-inner">
							    	<div class="active item">
							    		<a target="_blank" href="./about">
							    			<img src="resources/images/banner/banner.png" alt="" />
						    			</a>
					    			</div>
							    	<div class="item">
							    		<a target="_blank" href="./about">
					  						<img src="resources/images/banner/banner.png" alt="" />
					 						</a>
									</div>
							    	<div class="item">
							    		<a target="_blank" href="./about">
					  						<img src="resources/images/banner/banner.png" alt="" />
					 						</a>
									</div>
							    </div>
					  		</div>
						</caption>
						<thead>
							<tr>
								<th width="12%"><i class="icon-flag"></i> 游戏版本</th>
								<th width="13%"><i class="icon-bookmark"></i> 游戏名称</th>
								<th width="18%"><i class="icon-calendar"></i> 开区时间</th>
								<th width="10%"><i class="icon-road"></i> 线路</th>
								<th width="30%"><i class="icon-exclamation-sign"></i> 游戏介绍</th>
								<th width="10%"><i class="icon-link"></i> 链接地址</th>
								<th width="7%"><i class="icon-star"></i>人气</th>
							</tr>
						</thead>
						<tbody role="alert" aria-live="polite" aria-relevant="all">
							<tr class="odd success">
								<td class=" ">１．８９黄金微变</td>
								<td class=" ">��散人一区��</td>
								<td class="  sorting_1">5月/6日/14点开放</td>
								<td class=" ">ＶＩＰ�双线</td>
								<td class=" ">进服送�麻痹�皓月�影刺�末日�黄金-推荐</td>
								<td class=" "><a target="_blank" href="http://hx.65hx.com">点击查看</a></td>
								<td class=" ">0</td>
							</tr>
							<tr class="even success">
								<td class=" ">��最火中变��</td>
								<td class=" ">新１１１１１１区</td>
								<td class="  sorting_1">5月/6日/14点开放</td>
								<td class=" ">�重金双线�</td>
								<td class=" ">此版本花钱不一定牛/会玩才牛内容丰富-推荐</td>
								<td class=" "><a target="_blank" href="http://zzz.jjxbbs.com">点击查看</a></td>
								<td class=" ">0</td>
							</tr>
							<tr class="odd success">
								<td class=" ">���玄仙���</td>
								<td class=" ">���１区���</td>
								<td class="  sorting_1">5月/6日/14点开放</td>
								<td class=" ">��双线��</td>
								<td class=" ">�����不是一区请骂Ｆ�����-推荐</td>
								<td class=" "><a target="_blank" href="http://cc.bp7777.com">点击查看</a></td>
								<td class=" ">0</td>
							</tr>
							<tr class="even success">
								<td class=" ">西游二季〓〓中变</td>
								<td class=" ">◆送99亿元宝</td>
								<td class="  sorting_1">5月/6日/14点开放</td>
								<td class=" ">冲级奖励防麻</td>
								<td class=" ">〖上线会员四钻�红名武器�终极狂暴〗-推荐</td>
								<td class=" "><a target="_blank" href="http://www.k6969.com">点击查看</a></td>
								<td class=" ">0</td>
							</tr>
							<tr class="odd success">
								<td class=" ">轻中变一区</td>
								<td class=" ">长久稳定</td>
								<td class="  sorting_1">5月/6日/14点开放</td>
								<td class=" ">�全国双线�</td>
								<td class=" ">����无限制轻变一切全靠打����-推荐</td>
								<td class=" "><a target="_blank" href="http://gg.xinooo.com/dddd.htm">点击查看</a></td>
								<td class=" ">0</td>
							</tr>
						</tbody>
					</table>
		    	</div>
		    	<div class="active item">
		    		<div class="passport-grid"></div>
		    	</div>
		    	<div class="active item">
		    		<div class="asfasdf"></div>
		    	</div>
		    </div>
	    </div> 	
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
    <script src="myLib/bootstrap/js/bootstrap-ajax-tabs.js"></script>
    <script src="myLib/jquery/jquery-ui.js"></script>
    <script src="myLib/jquery/plugins/jTable/jquery.jtable.min.js"></script>
 	<script src="resources/js/test.js?v=20120501"></script>
  </body>
</html>