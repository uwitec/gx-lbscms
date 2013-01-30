$(function() {
	$('#upload-white').dialog({
		autoOpen : false,
		modal : true,
		width : 320,

		closable : false,
		height : 200,
		resizable : false,
		// noheader:true,
		onBeforeOpen : function() {
			$.getJSON(ctx+ '/userWhite/findAllUserWhenAddWhiteMdn',
				function(json) {
					$('#roleCombobox1').combobox({
						data : json.sysUserList,
						width : 150,
						panelHeight : 150,
						valueField : 'userId',
						editable : false,
						textField : 'userName',
						onLoadSuccess : function() {
							$('#roleCombobox1').combobox(
								'setValue',
								json.sysUserList[0].userId);
						}
					});
					// alert(json.roleList[0].roleId);

				});

		},

		onOpen : function() {

		},
		buttons : [
		{
			text : '上传',
			iconCls : 'icon-ok',
			handler : function() {
				if($("#file").val().length<1){
					$.messager.alert('Error',"请选择要上传的文件!");
				}else{
					if(!/.(txt|TXT)$/.test($("#file").val())){ 
						$.messager.alert('Error',"文件类型错误！必须是txt文本!");
						return ;
					}
					ajaxFileUpload();
				}
				
			}
		}, {
			text : '取消',
			iconCls : 'icon-cancel',
			handler : function() {

				$("#file").val("");
				$('#upload-white').dialog('close');
			}
		} ]
	});
	$("#uploadBtn").click(function() {
		if(!valiMenu("userWhite/uploadWhiteMdn")){
			
			$.messager.alert('Success','您无权执行该操作!');

			return;
		}
		//alert( ctx+'/js/uploadify/uploadify.swf');
		$('#upload-white').dialog('open');
	});

	

});

function ajaxFileUpload()
{
    
    $("#loading")
    .ajaxStart(function(){
        $(this).show();
    })//开始上传文件时显示一个图片
    .ajaxComplete(function(){
        $(this).hide();
    });//文件上传完成将图片隐藏起来
   // alert($('#roleCombobox1').combobox('getValue'));
    $.ajaxFileUpload({
            url:ctx+ '/userWhite/uploadWhiteMdn',//用于文件上传的服务器端请求地址
            secureuri:false,//一般设置为false
            fileElementId:'file',//文件上传空间的id属性  <input type="file" id="file" name="file" />
            dataType: 'json',//返回值类型 一般设置为json
            data:{"sysUserWhite.userId":$('#roleCombobox1').combobox('getValue')},
            success: function (data, status)  {
            	var obj = eval("("+data+")");
            	//alert(obj.result.flag);
            	//eval( "data = " + data );
            	if(obj.result.flag = 1){
            		$.messager.alert('Success','上传成功！'+obj.result.msg);
            		$('#test').datagrid('reload');
            	}else{
            		$.messager.alert('Success','上传失败！');
            	}
            },
            error: function (data, status, e){
                alert(e);
            }
        }
    );


}
