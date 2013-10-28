$(function(){
    $('.passport-grid').jtable({
        title: '通行证管理',
        actions: {
            listAction: 'passport/load',
            createAction: 'passport/add',
            updateAction: 'passport/update',
            deleteAction: 'passport/delete'
        },
//        jqueryuiTheme: true,
        sorting:true,
        defaultSorting:'status DESC',
        deleteConfirmation:function(data){
			data.deleteConfirmMessage = '你确定要删除['+data.record.nickname+']吗?';
//			data.cancel = true;
//			data.cancelMessage = '撤销删除['+data.record.nickname+']成功!';
        },
        selecting:true,
//        multiselect:true,
        fields: {
         	id: {
                key: true,
                list: false
            },
            nickname: {
                title: '昵称',
                width: 80
//			    input: function (data) {
//			        if (data.record) {
//			            return '<input type="text" name="Name" style="width:200px" value="' + data.record.Name + '" />';
//			        } else {
//			            return '<input type="text" name="Name" style="width:200px" placeholder="请输入昵称..." />';
//			        }
//			    }
            },
            upassid: {
                title: '通行证账号',
                width: 100
            },
            password: {
                title: '通行证密码',
//                type:'password',
                width: 120
            },
            email: {
                title: '邮箱',
                width: 100
            },
            qq: {
                title: 'qq',
                width: 80
            },
            groupname: {
                title: '身份',
                width: 50
            },
            status: {
                title: '状态',
                width: 60,
                options: { '0': '未激活', '1': '正常', '-1': '已锁定' }
            }
        }
    });
    $('.passport-grid').jtable('load');
})