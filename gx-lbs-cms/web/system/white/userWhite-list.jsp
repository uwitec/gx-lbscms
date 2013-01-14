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

		$(function(){
			// searchAction:
				 $('#btnSearch').bind('click', function(){
					var beginTime = $('#beginTime').datebox('getValue'); // 开始时间
					var endTime = $('#endTime').datebox('getValue'); // 结束时间
					var mdn = $('#mdn').val(); // 号码
					var loginName = $('#loginName').val(); // 企业账号
					var userName = $('#userName').val(); // 企业名称

					$('#test').datagrid('load',{
						"queryVO.beginTime":beginTime,
						"queryVO.endTime":endTime,
						"queryVO.mdn":mdn,
						"queryVO.loginName":loginName,
						"queryVO.userName":userName
					});
					
				 
				 }); 
			//  end search 
			
			
			
		
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
					$.getJSON(ctx+'/userWhite/findAllUserWhenAddWhiteMdn', function(json) { 
						$('#roleCombobox').combobox({
							data :json.sysUserList, 
							width:150,
							panelHeight:150,
							valueField:'userId',
							editable:false ,
							textField:'userName',
							onLoadSuccess:function(){
								$('#roleCombobox').combobox('setValue',json.sysUserList[0].userId);
							}
						});
						//alert(json.roleList[0].roleId);
						
					});
					
					if(optFlag=="add"){
						$("#user-add").dialog("setTitle","添加白名单!");
						$('#loginName').removeAttr("readOnly");
						$('#loginName').validatebox({   
						    required: true,   
						    validType: "exist['loginName']"  
						});  
						
						$('#loginName').val("");
					}else if(optFlag=="edit"){
						
						$("#user-add").dialog("setTitle","修改白名单!");
					
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
					        url:ctx+"/userWhite/saveWhiteMdn",
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
				pageList:[1,5,10],
				iconCls:'icon-save',
				width:'auto',
				height:'auto',
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				collapsible:true,
				url:ctx+'/userWhite/list',
				// huhuadd
				onLoadSuccess:function () {  
				     var separator = $("#separator"); //toolbar上的竖线   
				      var grid = $(".datagrid-toolbar"); //datagrid   
				     var date = $("#selfSearchBox");
				      
				     //alert(grid.attr("style"));
				     grid.append(separator);  
				     grid.append(date);
				},
				// huhuadd
				onOpen:function(){
					//$('#test').datagrid('load');
				},
				sortName: 'msisdn',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'whiteId',
				onSelect:btnDisplay,
				onUnselect:btnDisplay,
				onSelectAll:btnDisplay,
				onUnselectAll:btnDisplay,
				onResize:function(){
					//alert(1);
				},
				frozenColumns:[[
	                {field:'whiteId',checkbox:true}
				]],
				columns:[[
						{field:'msisdn',title:'号码',width:150,sortable:true},
					{field:'userName',title:'所属EC/SI',width:220},
					{field:'memo',title:'描述',width:120},
					
					{field:'createTime',title:'创建时间',width:180,sortable:true,
						formatter:function(value,rec){
						  var ctime =rec.createTime;
						  ctime = ctime.replace(new RegExp("T","gm")," ");
						  return ctime;
						}
					},
					{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">删除</span>';
						}
					}
				]],
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'addBtn',
					text:'添加白名单',
					iconCls:'myicon-add',
					handler:function(){
						
						optFlag="add";

						$('#user-add').dialog("open");
					}
				},
				/**
				'-',{
					id:'editBtn',
					text:'修改白名单',
					iconCls:'myicon-edit',
					disabled:true,
					handler:function(){
						
						optFlag = "edit";

						 $('#user-add').dialog("open");
					}
				},
				*/
				'-',{
					id:'delBtn',
					text:'删除白名单',
					disabled:true,
					iconCls:'myicon-delete',
					handler:function(){
						//if (confirm("真的要删除吗？")){
							  $.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
								  
								       if (r){
								    	   var rows = $('#test').datagrid('getSelections');
											var ids = "";
											for(var i=0;i<rows.length;i++){
												ids = ids + rows[i].whiteId +",";
											}
											$.ajax({  
										          type : "post",  
										          url : ctx+"/userWhite/userWhite!deleteWhiteMdn.action",  
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
					          url : ctx+"/userWhite/userWhite!isWhiteMdnExist.action",  
					          data : "queryVO.mdn="+value,  
					          async : false,  
					          success : function(data){
					           		bl = data.whiteMdnExist;
					          }  
						});  
						return !bl;
					},
				    message:'该号码已存在'
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
			alert("rows:"+rows);
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
	<table id="test" data-options="border:true" ></table>
</div>
<!-- huhuadd -->
<div id="selfSearchBox">
	开始时间:<input id="beginTime" class="easyui-datebox"  name="beginTime" type="text" />	
	结束时间:<input id="endTime" class="easyui-datebox" name="endTime"  type="text">
	<div id="separator" class="datagrid-btn-separator"></div>
	号码:<input id="mdn" name="mdn" type="text"  style="width:110px" />
	</br>
	企业账号:<input id="loginName" name="ecsiId"  type="text" style="width:110px"/>
	企业名称:<input id="userName" name = "ecsiName" type="text" style="width:200px" />
	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
</div>
<div id="separator" class="datagrid-btn-separator"></div>
<!-- huhuadd -->


	<div id="user-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"  fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr> 
                    <td>号码:</td>
                    <td><input class="easyui-validatebox" id="whiteMdn" type="text" validType="exist['whiteMdn']" name="cellWhite.msisdn" data-options="required:true"></input></td>  
                </tr>  
                                 
                <tr>  
                    <td>所属EC/SI:</td>  
                    <td>  
                        <select id="roleCombobox" class="easyui-combobox" name="sysUserWhite.userId"></select>
                    </td>  
                </tr>  
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>