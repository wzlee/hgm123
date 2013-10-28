 function dwz_jump(count,url) { 
    window.setTimeout(function(){ 
        count--; 
        if(count > 0) { 
        	$('.count').html(count); 
        	dwz_jump(count,url); 
        } else { 
            window.location.href = url; 
        } 
    }, 1000); 
}
$(function() {
	var userAgent = navigator.userAgent.toLowerCase();
	var core,name,version,src,html,dwz="";
	jQuery.browser = {
		version : (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
		safari : /webkit/.test(userAgent),
		chrome:/chrome/.test(userAgent) && !/(applewebkit|safari)/.test(userAgent),
		opera : /opera/.test(userAgent),
		msie : /msie/.test(userAgent) && !/opera/.test(userAgent),
		mozilla : /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
	};
	version = jQuery.browser.version;
	if(jQuery.browser.msie){
		core = 'mise';
		name = 'IE';
		if(jQuery.browser.version < 9.0){
			src = 'resources/icons/browser/bsie.png';
			dwz = 'dwz/bsie.index';
		}else{
			src = 'resources/icons/browser/iexplore.png';
			dwz = 'dwz/common.index';
		}
	}else if(jQuery.browser.mozilla){
		core = 'mozilla';
		name = 'FireFox';
		src = 'resources/icons/browser/firefox.png';
		dwz = 'index.jsp';
	}else if(jQuery.browser.safari){
		core = 'safari';
		name = 'Safari';
		src = 'resources/icons/browser/safari.png';
		dwz = 'dwz/common.index';
	}else if(jQuery.browser.chrome){
		core = 'chrome';
		name = 'Chrome';
		src = 'resources/icons/browser/chrome.png';
		dwz = 'dwz/common.index';
	}else if(jQuery.browser.opera){
		core = 'opera';
		name = 'Opera';
		src = 'resources/icons/browser/opera.png';
		dwz = 'dwz/common.index';
	}else{
		core = 'unknown';
		name = 'Unknown';
		src = 'resources/icons/browser/unknown.png';
		dwz = 'dwz/unknown.index';
	}
	$('.dwz-info img').attr('src',src);
	$('.dwz-info p').html('检测浏览器信息:<br>名称：<span class="name">'+name+'</span>,内核：<span class="core">'+core+'</span>,版本：<span class="version">'+version+'</span><br>将在<span class="count">3</span>秒后跳转至本浏览器兼容页面！');
	dwz_jump(3,dwz);
});