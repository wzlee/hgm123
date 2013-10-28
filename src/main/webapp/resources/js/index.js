/* 添加到收藏夹 */
function addFavorites(setUrl){
	var title="找好游戏，发好广告，做好GM，就来hgm123.com";
    var url="http://"+document.domain;
	if(setUrl!="" && setUrl!=null){
		url = setUrl;
	}
	
	try{
		window.external.addFavorite(url,title);
	}catch(e1){
		try{
			window.external.AddToFavoritesBar(url,title);
		}catch (e2){
			try{
				window.sidebar.addPanel(title,url);
			}catch (e3){
				alert("收藏失败，此操作被浏览器拒绝！\n请使用\"Ctrl+D\"进行收藏！");
			}
		}
	}
}
//function artDialogShow(content,time){
//	var artDialog = art.dialog.get('artDialog');
//	if(artDialog == undefined){
//		art.dialog({
//			id:'artDialog',
//			title:false,
//			content:content,
//			time:time
//		});
//	}else{
//		artDialog.content(content);
//		artDialog.time(time);
//	}
//}
var msg;
$._messengerDefaults = {
	extraClasses: 'messenger-fixed messenger-theme-future messenger-on-bottom messenger-on-right'
}

$(function(){
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
		if(version <= 7.0){
			msg = $.globalMessenger().post({
			  	message: '<strong>提示</strong>:<br>系统检测到您的浏览器版本为IE'+version+',在访问的过程中可能会出现样式兼容问题,为保证您有良好的浏览体验,建议您升级您的浏览器版本或点击 <a href="donwload" target="_blank">下载</a> 转入兼容浏览器下载页面.',
			  	type: 'error',
			  	hideAfter: 10,
		  		hideOnNavigate: true,
			  	showCloseButton: true
			});
		}
	}
	
	$(".input-filter").keyup(function(){
		// When value of the input is not blank
		if( $(this).val() != ""){
			// Show only matching TR, hide rest of them
			$(".datatable tbody > tr").hide();
			$(".datatable td:contains-ci('" + $(this).val() + "')").parent("tr").show();
		}else{
			// When there is no input or clean again, show everything back
			$(".datatable tbody > tr").show();
		}
	});
	
	$('.hgm-ad').carousel();
	
	$('.click-view a').click(function(){
		var $parent = $(this).parent();
		if(new Date().getTime() - $parent.data('stamp') > 10000){
			$.ajax({
				type:'POST',
				url:'public/count',
				data:{id:$(this).parent().data('id')},
				dataType:'json',
				timeOut:10000,
				success:function(data){
					if(data.success){
						$parent.next().html(parseInt($parent.next().html())+1);
						$parent.data('stamp',new Date().getTime());
					}
				},
				failure:function(){
				}
			});
		}
	});
    
	$('.load-toolbar button').on('click',function(){
		var load_btn = $(this);
		$.ajax({
			type:'POST',
			url:'public/load',
			dataType:'json',
			data:{querytime:load_btn.data('querytime')},
			beforeSend: function(){  
				load_btn.button('loading');
			},
			success:function(data){
				if(data.success){
					$('.datatable').dataTable().fnAddData(data.data);
					load_btn.unbind();
					load_btn.removeClass('btn-success');
					load_btn.attr('disabled',true);
				}
				load_btn.button('reset');
			},
			failure:function(){}
		});
	});
	
	$('.signin-form input[required]').blur(function(){
		var error_info = $('.signin-form .'+$(this).attr('name')+'-info');
		if($.trim($(this).val()) == ''){
			error_info.addClass('text-error').html('<i class="icon-hand-left"></i> '+'请输入'+error_info.data('label')+'!');
		}else{
			error_info.removeClass('text-error').html('');
		}
	});
	
	$('.refresh-code').click(function(){
		$('.refresh-code i').addClass('icon-spin');
		$('.validate-img').attr('src','public/validatecode?'+new Date().getTime());
		$('.validate-img').load(function(){
			$('.refresh-code i').removeClass('icon-spin');
		});
	});
	
	$('.keeplogin').hover(function(){
		$('.keeplogin-warning-tip').show();
	},function(){
		$('.keeplogin-warning-tip').hide();
	});
	
    $('.signin-modal').on('show', function () {
        if($.cookie('_upassid') == null || $.cookie('_upassid') == ''){
        	$('.signin-form .upassid').focus();
        }else{
        	$('.signin-form .upassid').val($.cookie('_upassid'));
        	$('.signin-form .password').focus();
        }
    });
	
    $('.btn-login').click(function(){
    	if($.trim($('.signin-form .upassid').val()) == ''){
    		$('.signin-form .upassid').focus();
			$('.signin-form .upassid-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入账号!');
		}else if($.trim($('.signin-form .password').val()) == ''){
			$('.signin-form .password').focus();
			$('.signin-form .password-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入密码!');
		}else if($.trim($('.signin-form .validatecode').val()) == ''){
			$('.signin-form .validatecode').focus();
			$('.signin-form .validatecode-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请填写验证码!');
		}else{
			$.ajax({
				type:'POST',
				url:'passport/login',
				dataType:'json',
				timeOut:10000,
				data:$('.signin-form').serialize(),
				beforeSend: function(){  
					$('.btn-login').button('loading');
				},
				success:function(data){
					if(data.success){
						$('.signin-modal').modal('hide');
						$('.ulogin,.ureg').addClass('hide');
						$('.nickname').html(data.errorfiled);
						$('.ucenter').removeClass('hide');
						$('.btn-login').button('reset');
						$.cookie('_upassid',$('.signin-form .upassid').val());
					}else{
						$('.'+data.errorfiled).focus().select();
						$('.'+data.errorfiled+'-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> '+data.message);
						$('.btn-login').button('reset');
					}
				},
				failure:function(){}
			});
		}
    });
    
    $('.digital-clock').clock({offset: '+8', type: 'digital'});
    
    $('.btn-refresh').click(function(){
    	console.log($('.userinfo-tab>li').find('active'));
//    	$('.userinfo-tab').find('active').show();
    });
    
	$('.signup-form input[name=upassid]').blur(function(){
		if($(this).val() == ''){
			$('.signup-form .upassid-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 帐号不允许为空!');
		}else{
			if(/^[a-zA-Z0-9_]{3,10}$/.test($(this).val())){
				$.ajax({
					type:'POST',
					url:'passport/check',
					dataType:'json',
					data:{upassid:$.trim($(this).val())},
					beforeSend: function(){  
						$('.signup-form .upassid-info').removeClass('text-error').removeClass('text-success').html('<i class="icon-spinner icon-spin"></i> 检测帐号中...');
					},
					success:function(data){
						if(data.success){
							$('.signup-form .upassid-info').addClass('text-success').html('<i class="icon-thumbs-up"></i> 帐号可用!');
						}else{
							$('.signup-form .upassid-info').addClass('text-error').html('<i class="icon-hand-left"></i> 帐号已存在!');
						}
					},
					failure:function(){}
				});
			}else{
				$('.signup-form .upassid-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 帐号格式不正确!');
			}
		}
	});
	
	$('.signup-form input[name=password]').blur(function(){
		if($(this).val() == ''){
			$('.signup-form .password-info').addClass('text-error').html('<i class="icon-hand-left"></i> 密码不能为空!');
		}else{
			$('.signup-form .password-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 密码输入正确!');
			$('.confirm').focus().select();
		}
	});
	
	$('.signup-form input[name=confirm]').blur(function(){
		if($(this).val() == ''){
			$('.confirm-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入确认密码!');
		}else{
			if($('.signup-form .password').val() == $(this).val()){
				$('.confirm-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 密码输入一致!');
			}else{
				$('.confirm-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 密码输入不一致!');
			}
		}
	});
	
	$('.signup-form input[name=nickname]').blur(function(){
		if($(this).val() == ''){
			$('.nickname-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入通行证昵称!');
		}else{
			$('.nickname-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 昵称输入正确!');
		}
	});
	
	$('.signup-form input[name=email]').blur(function(){
		if($(this).val() == ''){
			$('.email-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入邮箱地址!');
		}else{
			if(/^[_a-zA-Z\d\-\.]+@[_a-zA-Z\d\-]+(\.[_a-zA-Z\d\-]+)+$/.test($(this).val())){
				$('.email-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 邮箱输入正确!');
			}else{
				$('.email-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 邮箱格式不正确!');
			}
		}
	});
	
	$('.signup-form input[name=qq]').blur(function(){
		if($(this).val() != ''){
			if(/^\d{5,10}$/.test($(this).val())){
				$('.qq-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> QQ输入正确!');
			}else{
				$('.qq-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> QQ号码格式有误!');
			}
		}
	});

//	$(".chzn-select").chosen({disable_search_threshold: 10}).change(function(){
//		$('.group-info').html('<i class="icon-user"></i> '+$(this).find('selected').text());
//	});
	
	$('.signup-form .agreement').click(function() {
        if ($(this).is(':checked')) {
            $('.agreement-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 很好,明智的选择!');
        }else {
            $('.agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 还是从了贫道吧!');
        }
	});
	
 	$('.btn-reg').click(function(){
 		if($('.signup-form input[name=upassid]').val() == ''){
 			$('.signup-form .upassid').focus();
 			$('.signup-form .upassid-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 亲,登录需要帐号的!');
 		}else if($('.signup-form input[name=password]').val() == ''){
 			$('.signup-form .password').focus();
 			$('.signup-form .password-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 密码也是需要的!');
 		}else if($('.signup-form input[name=confirm]').val() == ''){
 			$('.signup-form .confirm').focus();
 			$('.confirm-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请再输一次密码!');
 		}else if($('.signup-form input[name=nickname]').val() == ''){
 			$('.signup-form .nickname').focus();
 			$('.nickname-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 您要做无名氏么?!');
 		}else if($('.signup-form input[name=email]').val() == ''){
 			$('.signup-form .email').focus();
 			$('.email-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 帐号都激活不了啊!');
 		}else if(!$('.signup-form input[name=agreement]').is(':checked')){
 			$('.signup-form .agreement').focus();
 			$('.agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 这都不同意?!');
 		}else{
 			if($('.signup-form .help-inline').hasClass('text-error')){
 				var errorTimeout = null;
 				var first_error = $('.signup-form .text-error').first();
 				clearTimeout(errorTimeout);
 				first_error.jrumble().trigger('startRumble');
 				errorTimeout = setTimeout(function(){first_error.trigger('stopRumble');}, 500);
 			}else{
				$.ajax({
					type:'POST',
					url:'passport/reg',
					dataType:'json',
					timeOut:10000,
					data:$('.signup-form').serialize(),
					beforeSend: function(){  
						$('.btn-reg').button('loading');
					},
					success:function(data){
						$('.btn-reg').button('reset');
						var mailboxs = {
											'qq.com': 'http://mail.qq.com',
											'gmail.com': 'http://mail.google.com',
											'sina.com': 'http://mail.sina.com.cn',
											'163.com': 'http://mail.163.com',
											'126.com': 'http://mail.126.com',
											'yeah.net': 'http://www.yeah.net/',
											'sohu.com': 'http://mail.sohu.com/',
											'tom.com': 'http://mail.tom.com/',
											'sogou.com': 'http://mail.sogou.com/',
											'139.com': 'http://mail.10086.cn/',
											'hotmail.com': 'http://www.hotmail.com',
											'live.com': 'http://login.live.com/',
											'live.cn': 'http://login.live.cn/',
											'live.com.cn': 'http://login.live.com.cn',
											'189.com': 'http://webmail16.189.cn/webmail/',
											'yahoo.com.cn': 'http://mail.cn.yahoo.com/',
											'yahoo.cn': 'http://mail.cn.yahoo.com/',
											'eyou.com': 'http://www.eyou.com/',
											'21cn.com': 'http://mail.21cn.com/',
											'188.com': 'http://www.188.com/',
											'foxmail.coom': 'http://www.foxmail.com'
										}; 
						if(data.success){
							$('.signup-form .help-inline').removeClass('text-success').removeClass('text-error').html('');
							$('.reg-info-modal .email').html($('.signup-form .email').val());
							$('.reg-info-modal .go-mailbox').attr('href',mailboxs[$('.signup-form .email').val().split('@')[1]]);
							$('.signup-form')[0].reset();
							$('.signup-modal').modal('hide');
							$('.reg-info-modal').modal('show');
						}else{
							$('.'+data.errorfiled).focus().select();
							$('.'+data.errorfiled+'-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> '+data.message);
						}
					},
					failure:function(){
						msg = $.globalMessenger().post({
						  	message: '<strong>提示</strong>:<br>服务器出现异常,请稍候再试.',
						  	type: 'error',
						  	hideAfter: 3,
					  		hideOnNavigate: true,
						  	showCloseButton: true
						});
					}
				});
 			}
		}
    });
	
	$('.btn-logout').click(function(){
		$.ajax({
			type:'POST',
			url:'passport/logout',
			dataType:'json',
			beforeSend: function(){  
				$('.logout-btn').button('loading');
			},
			success:function(data){
				if(data.success){
					$('.ucenter').addClass('hide');
					$('.ulogin,.ureg').removeClass('hide');
					$('.btn-logout').button('reset');
					$.cookie('_hgmu', null,{path:"/"});
					$.cookie('_hgmt', null,{path:"/"});
				}else{
					$('.btn-logout').button('reset');
					return;
				}
			},
			failure:function(){}
		});
	});
	
    $('.openTime').datetimepicker({
    	language:'zh-CN',
    	startView:1,
//    	minView:'minute',
//    	todayBtn:true,
//    	forceParse:true,
    	pickerPosition:'bottom-left',
    	minuteStep:30,
    	todayHighlight:true,
        format: 'm月/d日/hh点ii分开放',
        autoclose:true,
        startDate:(30).minutesAfter(new Date()),
        endDate:(2).daysAfter(new Date())
    }).on('hide', function(ev){
    		if($(ev.target).val() == ''){
    			$('.openTime-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 开放时间不能为空!');
    		}else{
    			$('.openTime-info').removeClass('text-error').addClass('text-success').html('<i class="icon-ok"></i> 输入正确!');
    		}
		});
	
    $('.publish-form input:not(.openTime,.gameUrl),.publish-form textarea').blur(function(){
    	var $helpInfo = $(this).parent().next();
    	if($(this).val() == ''){
    		$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> '+$helpInfo.data('label')+'不能为空!');
    	}else{
    		$helpInfo.removeClass('text-error').addClass('text-success').html('<i class="icon-ok"></i> 输入正确!');
    	}
    });
    
    $('.publish-form .gameUrl').blur(function(){
    	var $helpInfo = $(this).parent().next();
    	if($(this).val() == ''){
    		$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏网址不能为空!');
    	}else{ 
    		if($(this).val().match(/((http|ftp|https|file):\/\/([\w\-]+\.)+[\w\-]+(\/[\w\u4e00-\u9fa5\-\.\/?\@\%\!\&=\+\~\:\#\;\,]*)?)/ig)){
	    		$helpInfo.removeClass('text-error').addClass('text-success').html('<i class="icon-ok"></i> 输入正确!');
	    	}else{
	    		$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏网址格式有误!');
	    	}
    	}
    });
    
    $('.agreement').click(function() {
    	var $helpInfo = $(this).nextAll().filter('.agreement-info');
        if ($(this).is(':checked')) {
        	$helpInfo.removeClass('text-error').addClass('text-success').html('<i class="icon-exclamation-sign"></i> 请牢记协议内容!');
        }else {
        	$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请同意使用协议!');
        }
	});
    
    $('.publish-modal .publish-btn').click(function(){
    	if($('.publish-form .gameName').val() == ''){
    		$('.publish-form .gameName').focus();
    		$('.gameName-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏名称不能为空!');
    	}else if($('.publish-form .gameEdition').val() == ''){
    		$('.publish-form .gameEdition').focus();
 			$('.gameEdition-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏版本不能为空!');
 		}else if($('.publish-form .openTime').val() == ''){
 			$('.publish-form .openTime').focus();
 			$('.openTime-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 开放时间不能为空!');
 		}else if($('.publish-form .lineType').val() == ''){
 			$('.publish-form .lineType').focus();
 			$('.lineType-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏线路不能为空!');
 		}else if($('.publish-form .gameDescription').val() == ''){
 			$('.publish-form .gameDescription').focus();
 			$('.gameDescription-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 游戏描述不能为空!');
 		}else if($('.publish-form .gameUrl').val() == '' || $('.publish-form .gameUrl').val() == 'http://'){
 			$('.gameUrl-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入游戏网址!');
 		}else if(!$('.publish-form .agreement').is(':checked')){
 			$('.publish-form .agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请同意广告发布协议!');
 		}else{
 			if($('.publish-form .help-inline').hasClass('text-error')){
 				var errorTimeout = null;
 				var first_error = $('.publish-form .text-error').first();
 				clearTimeout(errorTimeout);
 				first_error.jrumble().trigger('startRumble');
 				errorTimeout = setTimeout(function(){first_error.trigger('stopRumble');}, 500);
 			}else{
 				$.ajax({
 					type:'POST',
 					url:'public/publish',
 					dataType:'json',
 					timeOut:10000,
 					data:$('.publish-form').serialize(),
 					beforeSend: function(){  
 						$('.publish_btn').button('loading');
 					},
 					success:function(data){
 						$('.publish_btn').button('reset');
 						if(data.success){
 							$('.publish-form')[0].reset();
 							$('.publish-form help-inline').html('');
 							$('.publish-modal').modal('hide');
// 							artDialogShow(data.message,1000);
 						}else{
 							$('.'+data.errorfiled).focus().select();
 							$('.'+data.errorfiled+'-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> '+data.message);
 						}
 					},
 					failure:function(){}
 				});
 			}
 		}
    });
    
    $('.thumbnails .select-btn').click(function(){
    	$('.use-template-form .templateCode').val($(this).data('code'));
    	$('.template-modal').modal('hide');
    });
    
    $('.use-template-form .sld,.upload-code-form .sld').blur(function(){
    	var $helpInfo = $(this).parent().next();
    	if($(this).val() == ''){
    		$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 域名不能为空!');
    	}else{ 
    		if(/^[a-zA-Z0-9_]{3,6}$/.test($(this).val())){
				$.ajax({
					type:'POST',
					url:'public/sld',
					dataType:'json',
					data:{sld:$.trim($(this).val())},
					beforeSend: function(){  
						$helpInfo.removeClass('text-error').removeClass('text-success').html('<i class="icon-spinner icon-spin"></i> 检测域名中...');
					},
					success:function(data){
						if(data.success){
							$helpInfo.addClass('text-success').html('<i class="icon-thumbs-up"></i> 个性域名可用!');
						}else{
							$helpInfo.addClass('text-error').html('<i class="icon-hand-left"></i> 个性域名已存在!');
						}
					},
					failure:function(){}
				});
			}else{
				$helpInfo.removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 域名格式不正确!');
			}
    	}
    });
    
    $('.use-template-btn').click(function(){
    	if($('.templateCode').val() == ''){
			var errorTimeout = null;
			clearTimeout(errorTimeout);
			$('.selecttemplate').jrumble().trigger('startRumble');
			errorTimeout = setTimeout(function(){$('.selecttemplate').trigger('stopRumble');}, 500);
//    		$('.templateCode-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 网站模板不能为空!');
    	}else if($('.use-template-form .sld').val() == ''){
    		$('.use-template-form .sld-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 网站域名不能为空!');
    	}else if($('.use-template-form .serverList').text() == ''){
    		var errorTimeout = null;
			clearTimeout(errorTimeout);
			$('.use-template-txt').jrumble().trigger('startRumble');
			errorTimeout = setTimeout(function(){$('.use-template-txt').trigger('stopRumble');}, 500);
    	}else if(!$('.use-template-form .agreement').is(':checked')){
    		$('.use-template-form .agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请同意使用协议!');
    	}else{
    		$('.use-template-form').ajaxSubmit({
    			url:'public/template',
    			type:'POST',
    			dataType:'json',
    			beforeSubmit:function(){
    				$('.use-template-btn').button('loading');
    			},
    			clearForm:true,
    			success:function(data){
    				$('.use-template-modal').modal('hide');
    				if(data.success){
    					$('.use-template-btn').button('reset');
    				}
//					artDialogShow(data.message,1000);
    			},
    			error:function(){}
    		});
    	}
    });
    
    $('.upload-code-btn').click(function(){
    	if($('.upload-code-form .sld').val() == ''){
    		$('.upload-code-form .sld').focus();
    		$('.upload-code-form .sld-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 域名不能为空!');
    	}else if($('.upload-code-form .serverList').text() == ''){
    		var errorTimeout = null;
			clearTimeout(errorTimeout);
			$('.upload-code-txt').jrumble().trigger('startRumble');
			errorTimeout = setTimeout(function(){$('.upload-code-txt').trigger('stopRumble');}, 500);
    	}else if($('.upload-code-form .codePackage').text() == ''){
    		var errorTimeout = null;
			clearTimeout(errorTimeout);
			$('.upload-code-zip').jrumble().trigger('startRumble');
			errorTimeout = setTimeout(function(){$('.upload-code-zip').trigger('stopRumble');}, 500);
    	}else if(!$('.upload-code-form .agreement').is(':checked')){
    		$('.upload-code-form .agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请同意使用协议!');
    	}else{
	    	$('.upload-code-form').ajaxSubmit({
    			url:'public/upload',
    			type:'POST',
    			dataType:'json',
    			beforeSubmit:function(){
    				$('.upload-code-btn').button('loading');
    			},
    			resetForm:true,
    			success:function(data){
    				$('.upload-code-btn').button('reset');
    				if(data.success){
    					$('.upload-code-modal .usage-tip').removeClass('text-error').addClass('text-success').html('<i class="text-ok"></i> '+data.message);
    				}else{
    					$('.upload-code-modal .usage-tip').removeClass('text-success').addClass('text-error').html('<i class="icon-warning-sign"></i> '+data.message);
    				}
    			},
    			error:function(){
    				$('.upload-code-btn').button('reset');
    				$('.upload-code-modal .usage-tip').removeClass('text-success').addClass('text-error').html('<i class="icon-warning-sign"></i> '+data.message);
    			}
	    	});
    	}
    });
    
	$('.go-bottom').click(function (e) {
			$('html, body').animate({scrollTop: $('.datatable').height()}, 500);
		}
	);
	
	$('.feedback-category').change(function(){
		switch($(this).val()){
			case 'ui':
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 界面效果存在视觉上的问题?');
				break;
			case 'ue':
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 操作使用过程存在影响体验问题?');
				break;
			case 'fn':
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 模块功能设计存在漏洞或缺失?');
				break;
			case 'css':
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 浏览器兼容导致页面布局和样式错乱?');
				break;
			case 'others':
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 想说些其他方面的建议或问题?');
				break;
			default:
				$('.category-info').html('<i class="icon-exclamation-sign"></i> 浏览器兼容导致页面布局和样式错乱?');
		}
	});
	
	$('.feedback_btn').click(function(){
 		if($('.feedback-content').val() == ''){
 			var errorTimeout = null;
			clearTimeout(errorTimeout);
			$('.feedback-content').jrumble().trigger('startRumble');
			errorTimeout = setTimeout(function(){$('.feedback-content').trigger('stopRumble');}, 500);
 		}else{
			$.ajax({
				type:'POST',
				url:'feedback',
				dataType:'json',
				timeOut:10000,
				data:$('.feedback-form').serialize(),
				beforeSend: function(){  
					$('.feedback_btn').button('loading');
				},
				success:function(data){
					$('.feedback_btn').button('reset');
					if(data.success){
						$('.feedback-form')[0].reset();
						$('.feedback-modal').modal('hide');
//						artDialogShow(data.message,2000);
						
					}else{
						$('.feedback-info').addClass('text-error').html('<i class="icon-warning-sign"></i> '+data.message);
					}
				},
				failure:function(){}
				});
		}
    });
	
	$('.go-top').click(function (e) {
			$('html, body').animate({scrollTop: '0px'}, 500);
		}
	);
});
$.extend($.expr[":"],{
    "contains-ci": function(elem, i, match, array){
		return (elem.textContent || elem.innerText || $(elem).text() || "").toLowerCase().indexOf((match[3] || "").toLowerCase()) >= 0;
	}
});