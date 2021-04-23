//ajax调用
function commonAjax(type,url,param,callback){
	$.ajax({
        type:type,
        url: url,
        data:param,
        dataType:"json",
        success:function(res){
			callback(res)
        }
    });
}

//获取url参数
function getQueryVariable(variable)
{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}