<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Complex Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css">
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script>
	function qq(value,name){
		
		//重新刷新datagrid，并增加两个参数key、type，这里是POST传值
		if(name=='queryVO.loginName'){
		$('#test').datagrid('load',{"queryVO.loginName":value});
		}else if(name=='queryVO.username'){
			$('#test').datagrid('load',{"queryVO.username":value});	
		}
	}
		$(function(){

		
			var optFlag="";
			$('#user-add').dialog({
				autoOpen: false ,
				modal:true,
				width:320,   
				closable:false,
				height:300,
				resizable:false,
				//noheader:true,
				onBeforeOpen:function(){
					$.getJSON(ctx+'/role/findRole', function(json) { 
						$('#roleCombobox').combobox({
							data : json.roleList, 
							width:150,
							panelHeight:150,
							valueField:'roleId',
							editable:false ,
							textField:'roleName',
							onLoadSuccess:function(){
								$('#roleCombobox').combobox('setValue',json.roleList[0].roleId);
							}
						});
						//alert(json.roleList[0].roleId);
						
					});
					
					if(optFlag=="add"){
						$("#user-add").dialog("setTitle","添加用户!");
						$('#loginName').removeAttr("readOnly");
						$('#loginName').validatebox({   
						    required: true,   
						    validType: "exist['loginName']"  
						});  
						
						$('#loginName').val("");
					}else if(optFlag=="edit"){
						
						$("#user-add").dialog("setTitle","修改用户!");
					
						$('#loginName').validatebox({   
						    required: false,   
						    validType: ''  
						});  
						$('#loginName').attr("readOnly","true");

						var row = $('#test').datagrid('getSelected');
						$.getJSON(ctx+'/user/user!findUserById.action?queryVO.id='+row.userId, function(json) { 
							$("#saveForm").form('load', {
								"sysUser.loginName": json.sysUser.loginName,
			                    "sysUser.loginPass": json.sysUser.loginPass,
			                    "queryVO.password": json.sysUser.loginPass,
			                    "sysUser.userName": json.sysUser.userName,
			                    "sysUser.tel": json.sysUser.tel,
			                    "sysUser.email": json.sysUser.email,
			                    "sysUser.userId":json.sysUser.userId,
			                    "sysUserRole.id":json.sysUserRole.id
			                 });
							$('#roleCombobox').combobox('setValue',json.sysUserRole.roleId);
						});

					}
				},
				
				onOpen:function(){

					
				    
				},
				buttons:[{
					text:'Ok',
					iconCls:'icon-ok',
					handler:function(){
						
						$("#saveForm").form("submit", {
					        url:ctx+"/user/saveUser",
					        onSubmit: function(){
					              return $(this).form("validate");
					        },
					        success:function(data){
					        	var datas = eval("("+data+")");
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','添加成功！');
					        		
					        		$("#saveForm").form("clear");
					        		$('#user-add').dialog("close");
					        		$('#test').datagrid('reload');
					        	}
					        }
					});
					}
				},{
					text:'Cancel',
					handler:function(){
					
						$('#loginName').val("");
						$('#pwd1').val("");
						$('#pwd2').val("");
						$('#userName').val("");
						$('#tel').val("");
						$('#email').val("");
						$('#user-add').dialog('close');
					}
				}]
			});

			
			
			function btnDisplay(rowIndex, rowData){
				
				
				var rows = $('#test').datagrid('getSelections');
				
				if(rows.length==1){
					$('#editBtn').linkbutton('enable');
				}else{
					$('#editBtn').linkbutton('disable');
				}
				if(rows.length>0){
					$('#delBtn').linkbutton('enable');
				}else{
					$('#delBtn').linkbutton('disable');
				}
			}
		
			

			//alert(parent.document.getElementById("frameId").offsetHeight);
			$('#test').datagrid({
				//title:'My DataGrid',
				
				iconCls:'icon-save',
				width:"auto",
				pageSize:20,
				height: document.body.scrollHeight,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				url:ctx+'/user/list',
				onOpen:function(){
					//$('#test').datagrid('load');
				},
				sortName: 'userId',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'userId',
				onSelect:btnDisplay,
				onUnselect:btnDisplay,
				onSelectAll:btnDisplay,
				onUnselectAll:btnDisplay,
				onResize:function(){
					//alert(1);
				},
				frozenColumns:[[
	                {field:'userId',checkbox:true},
	                {title:'姓名',field:'userName',width:80,sortable:true}
				]],
				columns:[[
					{field:'loginName',title:'登录账号',width:120},
					{field:'tel',title:'联系电话',width:120},
					{field:'email',title:'电子邮件',width:120},
					{field:'createTime',title:'创建时间',width:220,sortable:true,
						sorter:function(a,b){
							return (a>b?1:-1);
						}
					},
					{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						}
					}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'addBtn',
					text:'添加用户',
					iconCls:'myicon-add',
					handler:function(){
						
						optFlag="add";

						$('#user-add').dialog("open");
					}
				},'-',{
					id:'editBtn',
					text:'修改用户',
					iconCls:'myicon-edit',
					disabled:true,
					handler:function(){
						
						optFlag = "edit";

						 $('#user-add').dialog("open");
					}
				},'-',{
					id:'delBtn',
					text:'删除用户',
					disabled:true,
					
					iconCls:'myicon-delete',
					handler:function(){
						//if (confirm("真的要删除吗？")){
							  $.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
								  
								       if (r){   
								    	   var rows = $('#test').datagrid('getSelections');
											var ids = "";
											for(var i=0;i<rows.length;i++){
												ids = ids + rows[i].userId +",";
											}
											$.ajax({  
										          type : "post",  
										          url : ctx+"/user/user!deleteUser.action",  
										          data : "ids="+ids.substring(0,ids.length-1),  
										          //async : false,  
										          success : function(data){  
										        	  
										          	if(data.result.flag==FLAG_SUCCESS){
										          		$('#test').datagrid('clearSelections');
										          		$.messager.alert('Success','删除成功！');

										          		$('#test').datagrid('reload');
										          		$('#delBtn').linkbutton('disable');
										          		$('#editBtn').linkbutton('disable');
										          	}else if(data.result.flag==FLAG_FAILURE){
										          		
										          		$.messager.alert('Success','删除失败！'+data.result.msg);
										          	}
										          	
										           		
										          }  
											});  

											
								   
								        }   
								   
								    });  
							
							
						
					}
				},'-' ]
			}  
			
			);
			
			$('#ss').appendTo('.datagrid-toolbar');
			$('#ss').searchbox({  
				menu:'#mm'
			});

			var p = $('#test').datagrid('getPager');
			$(p).pagination({
				onBeforeRefresh:function(){
					//$('#test').datagrid('load');
				}
			});
			$.extend($.fn.validatebox.defaults.rules,{
				equalTo:{
					 validator:function(value,param){   
					     var s = $("#"+param).val();
					     //因为日期是统一格式的所以可以直接比较字符串 否则需要Date.parse(_date)转换
					     return value==s;
					    },
				    message:'两次输入的密码不一至!'
				  },
				exist:{
					validator:function(value,param){  
						$.ajax({  
					          type : "post",  
					          url : ctx+"/login/other!isUserExist.action",  
					          data : "queryVO.username="+value,  
					          async : false,  
					          success : function(data){  
					           		bl = data.userExist;
					          }  
						});  
						return !bl;
					},
				    message:'该账号已存在'
				}
				
			});
		});
		function resize(){
			$('#test').datagrid('resize', {
				width:700,
				height:400
			});
		}
		function getSelected(){
			var selected = $('#test').datagrid('getSelected');
			if (selected){
				alert(selected.code+":"+selected.name+":"+selected.addr+":"+selected.col4);
			}
		}
		function getSelections(){
			var ids = [];
			var rows = $('#test').datagrid('getSelections');
			for(var i=0;i<rows.length;i++){
				ids.push(rows[i].code);
			}
			alert(ids.join(':'));
		}
		function clearSelections(){
			$('#test').datagrid('clearSelections');
		}
		function selectRow(){
			$('#test').datagrid('selectRow',2);
		}
		function selectRecord(){
			$('#test').datagrid('selectRecord','002');
		}
		function unselectRow(){
			$('#test').datagrid('unselectRow',2);
		}
		function mergeCells(){
			$('#test').datagrid('mergeCells',{
				index:2,
				field:'addr',
				rowspan:2,
				colspan:2
			});
		}
	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="用户列表" data-options="border:true" ></table>
</div>
<input id="ss" class="easyui-searchbox"
		searcher="qq"
		prompt="Please Input Value" menu="#mm" ></input>
		<div id="mm" style="width:120px">
	<div name="queryVO.loginName" iconCls="icon-ok">账号</div>
	<div name="queryVO.username" iconCls="icon-ok">姓名</div>
</div>
	<div id="user-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"  fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr>  
                    <td>登录账号:</td>  
                    <td><input class="easyui-validatebox" id="loginName" type="text" validType="exist['loginName']" name="sysUser.loginName" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>登录密码:</td>  
                    <td><input class="easyui-validatebox" type="password" id="pwd1"   name="sysUser.loginPass" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>密码确认:</td>  
                    <td><input class="easyui-validatebox" id="pwd2" validType="equalTo['pwd1']" type="password" name="queryVO.password" data-options="required:true"></input></td>  
                </tr>                  
                <tr>  
                    <td>用户姓名:</td>  
                    <td><input class="easyui-validatebox" id="userName" type="text" name="sysUser.userName" data-options=""></input></td>  
                </tr>  
                <tr>  
                    <td>电话号码:</td>  
                    <td><input class="easyui-validatebox easyui-numberbox" id="tel" type="text" name="sysUser.tel" data-options=""></input></td>  
                </tr>  
                <tr>  
                    <td>电子邮件:</td>  
                    <td><input class="easyui-validatebox" type="text" id="email" name="sysUser.email" data-options="validType:'email'"></input></td>  
                </tr>                  
                <tr>  
                    <td>角色:</td>  
                    <td>  
                        <select id="roleCombobox" class="easyui-combobox" name="sysUserRole.roleId"></select>  
                        <input type="hidden"  name="sysUser.userId">
                        <input type="hidden"  name="sysUserRole.id">
                    </td>  
                </tr>  
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>