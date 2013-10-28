<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> --%>
<!DOCTYPE html>
<html lang="zh">
	<head>
    <%-- <base href="<%=basePath%>"> --%>
    
    <title>好哥们,专业游戏GM服务平台</title>
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="私服,私服广告,私服GM,家族">
	<meta http-equiv="description" content="国内第一个游戏GM服务平台,提供最新最热游戏版本,广告网站等游戏排行和资讯">
	
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/bootstrap-responsive.css">
	<link rel="stylesheet" type="text/css" href="myLib/bootstrap/css/font-awesome.min.css">
	<link rel="stylesheet" type="text/css" href="resources/css/index.css?v=20130409">
  </head>
  
  <body data-target=".side-bar" data-spy="scroll">
	<div class="navbar navbar-fixed-top">
    	<div class="navbar-inner">
      		<div class="container">
          		<ul class="nav pull-left">
       				<li class="index"><a href="./">首页</a></li>
           			<li class="divider-vertical"></li>
            		<li><a href="#"><i class="icon-home"></i>设为首页</a></li>
            		<li>
				        <form class="navbar-search pull-left">
				        	<input class="search-query" type="text" placeholder="输入查询关键字(多个用空格隔开)..."><span class="help-inline">热门搜索: <a href="#">神龙</a> | <a href="#">真彩</a></span>
					    </form>
			    	</li>
               	</ul>
               	<ul class="nav pull-right">
                	<li class="btn-signin">
			    		<a href="html/signin.html" role="button" data-toggle="modal" data-target="#signin"><i class="icon-signin"></i> 登陆</a>
             		</li>
             		<li class="btn-signup">
             			<a href="html/signup.html" role="button" data-toggle="modal" data-target="#signup"><i class="icon-user"></i> 注册</a>
             		</li>
             		<li class="btn-ucenter">
             			<a calss="userinfo" href="#ucenter" role="button" data-toggle="modal"></a>
             		</li>
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
	    <div class="row">
	   		<div class="side-bar span2">
	        	<ul class="nav nav-list side-nav affix">
	          		<li class="active"><a href="#show-now"><i class="icon-fire-left"></i> 本时段热服<i class="icon-chevron-right"></i></a></li>
		          	<li><a href="#show-last"><i class="icon-chevron-right"></i> 上一时段热服</a></li>
		          	<li><a href="#show-next"><i class="icon-chevron-right"></i> 下一时段热服</a></li>
		          	<li><a href="#today-rank"><i class="icon-chevron-right"></i> 今日热服排行</a></li>
		          	<li><a href="#week-rank"><i class="icon-chevron-right"></i> 本周热服排行</a></li>
		          	<li><a href="#month-rank"><i class="icon-chevron-right"></i> 本月热服排行</a></li>
		          	<li class="divider"></li>
		          	<li>
		          		<a class="hot-news">
			                <marquee behavior="scroll" width="150" direction="up" scrollDelay=80 scrollamount=2 align=middle onmouseover=this.stop() onmouseout=this.start()>
			                	<a href="./">台湾6.1级地震威力如0.7颗原子弹 已致1死19伤</a>
								<hr>
			                	<a href="./">金砖基金中国拟出410亿美元 巴俄印各出180亿</a>
			                	<hr>
			                	<a href="./">多地市民排队集中抢购天然气 官方辟谣涨价传言</a>
			                	<hr>
			                	<a href="./">新疆20嫌犯因煽动分裂国家策划暗杀干警等获刑</a>
			                	<hr>
			                </marquee>
		          		</a>
		          	</li>
	       		</ul>
	      	</div>
	      	<div class="span10">
	      		<section id="show-now">
		          	<%@include file="html/now.html" %>
		        </section>
		        
		        <section id="show-last">
		        	<table class="table table-bordered table-hover table-condensed">
		        		<caption>
		          			<div id="carousel2" class="carousel slide">
						    	<!-- Carousel items -->
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
								    <!-- Carousel nav -->
								    <%--<a class="carousel-control left" href="#carousel" data-slide="prev">&lsaquo;</a>
								    <a class="carousel-control right" href="#carousel" data-slide="next">&rsaquo;</a>--%>
					  		</div>
		          		</caption>
						<thead>
							<tr>
								<th width="12%">游戏版本</th>
								<th width="13%">游戏名称</th>
								<th width="18%">开区时间</th>
								<th width="10%">线路</th>
								<th width="30%">游戏介绍</th>
								<th width="10%">链接地址</th>
								<th width="7%">人气</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="lastAdvertise" items="${lastAdvertises}">
								<tr>
									<td>${lastAdvertise.gameName}</td>
									<td>${lastAdvertise.gameEdition}</td>
									<td>${lastAdvertise.openTime}</td>
									<td>${lastAdvertise.lineType}</td>
									<td>${lastAdvertise.gameDescription}</td>
									<td><a href="${lastAdvertise.gameUrl}" target="_blank">进入查看</a></td>
									<td>${lastAdvertise.gamePopularity}</td>
								</tr>
        					</c:forEach>
						</tbody>
					</table>
		        </section>
		        
		        <section id="show-next">
		        	<table class="table table-bordered table-hover table-condensed">
		        		<caption>
		          			<div id="carousel3" class="carousel slide">
						    	<!-- Carousel items -->
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
								    <!-- Carousel nav -->
								    <%--<a class="carousel-control left" href="#carousel" data-slide="prev">&lsaquo;</a>
								    <a class="carousel-control right" href="#carousel" data-slide="next">&rsaquo;</a>--%>
					  		</div>
		          		</caption>
						<thead>
							<tr>
								<th width="12%">游戏版本</th>
								<th width="13%">游戏名称</th>
								<th width="18%">开区时间</th>
								<th width="10%">线路</th>
								<th width="30%">游戏介绍</th>
								<th width="10%">链接地址</th>
								<th width="7%">人气</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="nextAdvertise" items="${nextAdvertises}">
								<tr>
									<td>${nextAdvertise.gameName}</td>
									<td>${nextAdvertise.gameEdition}</td>
									<td>${nextAdvertise.openTime}</td>
									<td>${nextAdvertise.lineType}</td>
									<td>${nextAdvertise.gameDescription}</td>
									<td><a href="${nextAdvertise.gameUrl}" target="_blank">进入查看</a></td>
									<td>${nextAdvertise.gamePopularity}</td>
								</tr>
        					</c:forEach>
						</tbody>
					</table>
		        </section>
		        
		        <section id="today-rank">
		        	<table class="table table-bordered table-hover table-condensed">
		        		<caption>
		          			<div id="carousel4" class="carousel slide">
						    	<!-- Carousel items -->
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
								    <!-- Carousel nav -->
								    <%--<a class="carousel-control left" href="#carousel" data-slide="prev">&lsaquo;</a>
								    <a class="carousel-control right" href="#carousel" data-slide="next">&rsaquo;</a>--%>
					  		</div>
		          		</caption>
						<thead>
							<tr>
								<th width="12%">游戏版本</th>
								<th width="13%">游戏名称</th>
								<th width="18%">开区时间</th>
								<th width="10%">线路</th>
								<th width="30%">游戏介绍</th>
								<th width="10%">链接地址</th>
								<th width="7%">人气</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		        </section>
		        <section id="week-rank">
		        	<table class="table table-bordered table-hover table-condensed">
		        		<caption>
		          			<div id="carousel5" class="carousel slide">
						    	<!-- Carousel items -->
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
								    <!-- Carousel nav -->
								    <%--<a class="carousel-control left" href="#carousel" data-slide="prev">&lsaquo;</a>
								    <a class="carousel-control right" href="#carousel" data-slide="next">&rsaquo;</a>--%>
					  		</div>
		          		</caption>
						<thead>
							<tr>
								<th width="12%">游戏版本</th>
								<th width="13%">游戏名称</th>
								<th width="18%">开区时间</th>
								<th width="10%">线路</th>
								<th width="30%">游戏介绍</th>
								<th width="10%">链接地址</th>
								<th width="7%">人气</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		        </section>
		        
		        <section id="month-rank">
		        	<table class="table table-bordered table-hover table-condensed">
		        		<caption>
		          			<div id="carousel6" class="carousel slide">
						    	<!-- Carousel items -->
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
								    <!-- Carousel nav -->
								    <%--<a class="carousel-control left" href="#carousel" data-slide="prev">&lsaquo;</a>
								    <a class="carousel-control right" href="#carousel" data-slide="next">&rsaquo;</a>--%>
					  		</div>
		          		</caption>
						<thead>
							<tr>
								<th width="12%">游戏版本</th>
								<th width="13%">游戏名称</th>
								<th width="18%">开区时间</th>
								<th width="10%">线路</th>
								<th width="30%">游戏介绍</th>
								<th width="10%">链接地址</th>
								<th width="7%">人气</th>
							</tr>
						</thead>
						<tbody>
						</tbody>
					</table>
		        </section>
	      	</div>
	    </div>
	    
	    <div class="footer">
	  		<hr size="3">
	  		&copy;2013 Hgm123&nbsp;&nbsp;&nbsp;|<a href="http://www.hgm123.com/duty/index.html" target="_blank">好GM必读</a>&nbsp;&nbsp;&nbsp;|<a href="./help">关于hgm123</a>&nbsp;&nbsp;&nbsp;|<a href="mailto:214508914@qq.com"><i class="icon-envelope"></i> Me</a>&nbsp;&nbsp;&nbsp;
	  		<%--<script src="http://s17.cnzz.com/stat.php?id=4945862&amp;web_id=4945862&amp;show=pic" type="text/javascript"></script>--%>
	 	</div><!-- /footer -->
    </div>
 	
 	<script src="myLib/jquery/jquery-1.9.1.min.js"></script>
    <script src="myLib/bootstrap/js/bootstrap.js"></script>
 	<script src="myLib/jquery/jquery.cookie.js"></script>
    <script src="myLib/datatable/js/jquery.dataTables.js"></script>
 	<script src="resources/js/index.js"></script>
  </body>
</html>