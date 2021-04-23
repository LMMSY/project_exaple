//切换图标显示隐藏
$(".moduleTitle .tip").click(function(){
	if($(this).hasClass('down')){
		$(this).hide();
		$(this).siblings('.right').show();
		$(this).parents('.module').find('.allIcon').hide();
	}else{
		$(this).hide();
		$(this).siblings('.down').show();
		$(this).parents('.module').find('.allIcon').show();
	}
})

//点击事件
$(".titleBody").click(function(){
	let url=$(this).attr('data-url');
	window.location.href='approve.html?url='+url;
})

//获取各个流程待办数量
getData();
function getData(){
	commonAjax('get','flow/nums',{},function(res){
		if(res.code==0){
			$.each(res.data,function(i,item){
				if(item>0){
					$('.'+i).show();
					if(item>10){
						$('.'+i).addClass('omit');
						$('.'+i).html('...');
					}else{
						$('.'+i).html(item);
					}
				}
			})
			$("#username").html(res.data.user.userName);
		}
	})
}