$(function(){
	
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
				$('.qq-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请输入正确的QQ号码!');
			}
		}
	});

	$(".chzn-select").chosen({disable_search_threshold: 10}).change(function(){
		$('.group-info').html('<i class="icon-user"></i> '+$(this).find('selected').text());
	});
	
	$('.signup-form .agreement').click(function() {
        if ($(this).is(':checked')) {
            $('.agreement-info').removeClass('text-error').addClass('text-success').html('<i class="icon-thumbs-up"></i> 很好,明智的选择!');
        }else {
            $('.agreement-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 还是从了贫道吧!');
        }
	});
	
 	$('.reg_btn').click(function(){
 		if($('.signup-form input[name=upassid]').val() == ''){
 			$('.signup-form .upassid-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 亲,登录需要帐号的!');
 		}else if($('.signup-form input[name=password]').val() == ''){
 			$('.signup-form .password-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 密码也是需要的!');
 		}else if($('.signup-form input[name=confirm]').val() == ''){
 			$('.confirm-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 请再输一次密码!');
 		}else if($('.signup-form input[name=nickname]').val() == ''){
 			$('.nickname-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 您要做无名氏么?!');
 		}else if($('.signup-form input[name=email]').val() == ''){
 			$('.email-info').removeClass('text-success').addClass('text-error').html('<i class="icon-hand-left"></i> 帐号都激活不了啊!');
 		}else if(!$('.signup-form input[name=agreement]').is(':checked')){
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
						$('.reg_btn').button('loading');
					},
					success:function(data){
						$('.reg_btn').button('reset');
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
							$('.reg-info-modal').modal('show');
						}else{
							$('.signup .'+data.errorfield).data('title',data.message).tooltip('show');
						}
					},
					failure:function(){}
				});
 			}
		}
    });
 	
});