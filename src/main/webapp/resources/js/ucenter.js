$(function(){
//	$('.user-info-tab nav li:FIRST-CHILD').ajaxTab('show');
//	$('.user-info-tab a').on('shown', function(e){
//		var el = $(e.target).attr('href');
////		$(el).showLoading();
////		var name = el.split('#')[1];
////		$(el).load('html/'+name+'.html',function(){
////			$('.user-info-tab ul').tab();
////			$(el).hideLoading();
////		})
//		$.ajax({
//			url:'system/ucenter',
//			dataType:'JSON',
//			beforeSend:function(){
//				$(el).showLoading();
//			},
//			success:function(data){
//				console.log(data);
//				$(el).hideLoading();
//			},
//			failure:function(response){
//				console.log(response);
//			}
//		});
//	});
	
//	T.init({                                                                                 
//        appkey: 801124054,                                                                  
//        pingback: false                                                                    
//    });                                                                                    
//                                                                                           
//    if(T.loginStatus()) {                                                                   
//        T.task(                                                                             
//            T.api('friends/check',{names: 'api_weibo', flag: 1}),                           
//            T.api('user/other_info',{name: 'api_weibo'})                                    
//          )                                                                                 
//         .success(function (ret0,ret1) {                                                    
//            var stat = T.find("#status")[0];                                                
//            var isfollow = ret0[0].data.api_weibo;                                          
//            var fansnum = ret1[0].data.fansnum;                                              
//            stat.innerHTML = (isfollow ? "已收听" : "未收听") + '，听众' + fansnum + '人'; 
//            if(isfollow)stat.className = 'green';                                           
//       });                                                                                   
//    }                                                                                       
//                                                                                              
//    function follow() {                                                                                     
//        if (!T.loginStatus()) {                                                              
//            T.login(follow);                                                               
//            return;                                                                        
//        }                                                                                           
//        T.api("friends/add",{name: 'api_weibo'},'json','post')                
//        
//        .success(function () {                                                                               
//            alert('收听成功');                                                               
//        })                                                                                                  
//        .error(function (code,message) {                                                                  
//            alert('收听失败' + message);                                                    
//        });                                                                                                 
//        return false;                                                                       
//    }          
});
