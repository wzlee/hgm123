$(function(){
	
	$('input[type=text],input[type=password]').blur(function(){
		var help_info = $('.'+$(this).attr('name')+'-info');
		if($(this).attr('required') == 'required' && $.trim($(this).val()) == ''){
			help_info.html('<i class="icon-warning-sign"></i> '+help_info.data('label')+'不能为空!');
			help_info.parent().parent().addClass('error');
		}else{
			help_info.html('');
			help_info.parent().parent().removeClass('error');
		}
	});
	
	$('.keeplogin').hover(function(){
			$('.keeplogin-info').html('[<i class="icon-exclamation-sign"></i> 公共场合请慎用!]');
	},function(){
		$('.keeplogin-info').html('');
	});
	
    $('.login_btn').click(function(){
    	if($.trim($('.signin-form .upassid').val()) == ''){
    		$('.signin-form .upassid').focus();
			$('.upassid-info').html('<i class="icon-warning-sign"></i> 帐号不能为空!');
		}else if($.trim($('.signin-form .password').val()) == ''){
			$('.signin-form .password').focus();
			$('.password-info').html('<i class="icon-warning-sign"></i> 密码不能为空!');
		}else{
			$.ajax({
				type:'POST',
				url:'passport/login',
				dataType:'json',
//				data:$('.signin-form form').serialize(),
				data:{'upassid':$.trim($('.signin-form .upassid').val()),'password':$.md5($.trim($('.signin-form .password').val())),'keeplogin':$('.keeplogin').is(':checked')},
				beforeSend: function(){  
					$('.login_btn').button('loading');
				},
				success:function(data){
					if(data.success){
						$('#signin').modal('hide');
						$('.btn-signin,.btn-signup').hide();
//						$('.btn-ucenter a').html('<i class="icon-user"></i>'+data.nickname);
						$('.btn-ucenter').show();
						$('.login_btn').button('reset');
					}else{
						$('.'+data.errorfiled).focus().select();
						$('.'+data.errorfiled+'-error-info').html('<i class="icon-warning-sign"></i> '+data.message+'!');
						$('.login_btn').button('reset');
					}
				},
				failure:function(){}
			});
		}
    });
});