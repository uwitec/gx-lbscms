function validateSessionIsNull(){
	$.ajax({  
		type : "post",  
		url:ctx+"/login/other!isSessionNull",
	 	async : true,  
	 	success : function(data1){  
	 		var data = $.parseJSON(data1);
	 		
	 		if(data.result.flag==2){
	 			$.messager.alert('Success','登录超时，请重新登录!','error',function(){
	 				top.location.href=ctx+"/index.jsp";
	 			});
	 			return;
	 			//	
	 		}	
		}  
	});  
}
$.ajaxSetup ({
    cache: false //关闭AJAX相应的缓存
 });


function valiMenu(url){
	var allMenu = "";
	var bl = false;
	
	$.ajax({  
		type : "post",  
		url:ctx+"/menu/menu!findAllMenuByUserId.action",
	 	async : false,  
	 	success : function(data1){  
	 		//alert(data1);
	 		var data = $.parseJSON(data1);
	 		allMenu = data;
	 		for(var i = 0 ; i < allMenu.list.length ; i++){
	 			var menu = allMenu.list[i].url;
	 			
	 			if(menu==url){
	 			
	 				bl = true;
	 				break;
	 			}
	 		}
		}  
	});  
	
	
	return bl;
}