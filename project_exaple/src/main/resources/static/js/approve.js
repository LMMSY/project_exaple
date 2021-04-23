$(".back").click(function(){
	window.location.href='index.html';
})

var url=getQueryVariable('url');
//获取各个流程待办数量
getData();
function getData(){
	commonAjax('get','flow/'+url+'List',{},function(res){
		if(res.code==0){
			let html='';
			$(".noData").show();
			if(res.data.length>0){
				$.each(res.data,function(i,item){
					let purchase_type=item.TYPE?item.TYPE:'';
					html+=`<div class="approveDetail">
						<div class="showBody">
							<div class="showName">${item.userName.substring(item.userName.length-2)}</div>
							<div class="showCont">
								<div class="contract">${item.userName+'提交的'+item.flowType}</div>
								<div class="depart">${item.depName}</div>
							</div>
							<div class="unsolve" u=${item.u} s=${item.s} instance_id=${item.instance_id} wf_code=${item.wf_code} wf_node=${item.wf_node} wf_version=${item.wf_version} workitem_id=${item.workitem_id} purchase_type=${purchase_type}>${item.status}</div>
						</div>
						<div class="line"></div>
						<div class="time">${item.startTime}</div>
					</div>`
				})
				$(".noData").hide();
				$(".approveBody").html(html);
			}
		}
	})
}

// 待审批进入详情
$(".approveBody").on('click','.approveDetail',function(){
	let __WF_WORKITEM_ID=$(this).find('.unsolve').attr('workitem_id');
	let __WF_INSTANCE_ID=$(this).find('.unsolve').attr('instance_id');
	let __WF_CODE=$(this).find('.unsolve').attr('wf_code');
	let __WF_VERSION=$(this).find('.unsolve').attr('wf_version');
	let __WF_NODE=$(this).find('.unsolve').attr('wf_node');
	let u=$(this).find('.unsolve').attr('u');
	let s=$(this).find('.unsolve').attr('s');
	let purchase_type=$(this).find('.unsolve').attr('purchase_type');
	if(purchase_type){
		let type;
		if(purchase_type=='3'){
			type=1;
		}else if(purchase_type=='1'||purchase_type=='4'){
			type=2;
		}else if(purchase_type=='2'){
			type=3;
		}
		window.location.href=`http://www.fhxynet.com:8081/test/core/shell/login.jsp?actionName=ssoLogin&u=${u}&s=${s}&target=/weixin/${url}Mobile${type}.jsp&__WF_WORKITEM_ID=${__WF_WORKITEM_ID}&__WF_INSTANCE_ID=${__WF_INSTANCE_ID}&__WF_CODE=${__WF_CODE}&__WF_VERSION=${__WF_VERSION}&__WF_NODE=${__WF_NODE}&url=${url}`;
	}else{
		window.location.href=`http://www.fhxynet.com:8081/test/core/shell/login.jsp?actionName=ssoLogin&u=${u}&s=${s}&target=/weixin/${url}Mobile.jsp&__WF_WORKITEM_ID=${__WF_WORKITEM_ID}&__WF_INSTANCE_ID=${__WF_INSTANCE_ID}&__WF_CODE=${__WF_CODE}&__WF_VERSION=${__WF_VERSION}&__WF_NODE=${__WF_NODE}&url=${url}`;
	}
})