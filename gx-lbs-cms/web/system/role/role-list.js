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
			$('#role-add').dialog({
				autoOpen: false ,
				modal:true,
				//width:320,   
				closable:false,
				height:450,
				resizable:false,
				//noheader:true,
				onBeforeOpen:function(){
					 $.post(ctx+"/menu/menu!findAllMenu", {
					 	}, function(json1) {  
					 		 var json = $.parseJSON(json1);
					 		$('#tt2').tree({  
		                        //parent : node.target,  
		                        animate:true,  
		                        data : json.list,
		                        onLoadSuccess:function(){
							 		 if(optFlag=="edit"){
											$.getJSON(ctx+'/role/role!findRoleMenuByRoleId?sysRoleMenu.roleId='+row.roleId, function(json) { 
												//alert(json.listSysRoleMenu.menuId);var node = $('#tt').tree('find', 12);
												//$('#tt').tree('select', node.target);
											//	alert(json.listSysRoleMenu.length );
												for(var i = 0 ; i < json.listSysRoleMenu.length ; i++){
													var obj = json.listSysRoleMenu[i];
													var node = $('#tt2').tree('find', obj.menuId);
													$('#tt2').tree('check', node.target);
												}
												//$('#roleCombobox').combobox('setValue',json.sysUserRole.roleId);
											});
									 		 }
		                        },
		                        cascadeCheck:false,
		                        onClick: function(node){
		                        	$(this).tree('toggle', node.target);
		                        },onResize:function(){
		                        }
		                        
		                    });  

					 });
					
					if(optFlag=="add"){
						$("#role-add").dialog("setTitle","添加角色!");
						$('#roleName').val("");
					
						$('#test').datagrid('clearSelections');
					}else if(optFlag=="edit"){
						var row = $('#test').datagrid('getSelected');
						$("#role-add").dialog("setTitle","修改角色!");
						$("#roleName").val(row.roleName);
						
						/*$.getJSON(ctx+'/role/role!findRoleMenuByRoleId?sysRoleMenu.roleId='+row.roleId, function(json) { 
							//alert(json.listSysRoleMenu.menuId);var node = $('#tt').tree('find', 12);
							//$('#tt').tree('select', node.target);
							for(var i = 0 ; i < json.listSysRoleMenu.length ; i++){
								var obj = json.listSysRoleMenu[i];
								var node = $('#tt2').tree('find', obj.menuId);
								$('#tt2').tree('check', node.target);
							}
							//$('#roleCombobox').combobox('setValue',json.sysUserRole.roleId);
						});*/

					}
				},
				
				onOpen:function(){

					
				    
				},
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						validateSessionIsNull();

						
						var ids = "";
						var nodes = $('#tt2').tree('getChecked');	// get checked nodes
						for(var i = 0 ; i < nodes.length ; i++){
							var node = nodes[i];
							ids = ids + node.id+",";
						}
						
						var row = $('#test').datagrid('getSelected');
						
						var roleId ="";
						if(row!=null)
							roleId = row.roleId;
							
						//alert(nodes[0].id);
						$("#saveForm").form("submit", {
							url:ctx+"/role/role!saveRole?ids="+ids+"&sysRole.roleId="+roleId,
					        onSubmit: function(param){
					        	//param.ids = ids;
					        	return $(this).form("validate");
					        },
					        success:function(data){
					        	//alert(data);
					        	var datas = eval("("+data+")");
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','操作成功！');
					        		
					        		$("#saveForm").form("clear");
					        		$('#role-add').dialog("close");
					        		$('#test').datagrid('reload');
					        	}else{
					        		  $.messager.alert('Error','操作失败！'+data.result.msg);
					        	}
					        }
					});
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
					
						$('#loginName').val("");
						$('#pwd1').val("");
						$('#pwd2').val("");
						$('#userName').val("");
						$('#tel').val("");
						$('#email').val("");
						$('#role-add').dialog('close');
					}
				}]
			});

			
			
			function btnDisplay(rowIndex, rowData){
				
				
				var rows = $('#test').datagrid('getSelections');
				//alert(rows.length);
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
				
				onLoadError:function(){
					$.messager.alert('Error','加载数据失败!,或登录超时！请重新登录，如问题仍未解决请联系管理员!','error',function(){
						 top.location.href=ctx+"/index.jsp";  
					});
				},
				pageSize:20,
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				fit:true,
				
				url:ctx+'/role/role!list',
				onOpen:function(){
					//$('#test').datagrid('load');
				},
				sortName: 'roleId',
				sortOrder: 'desc',
				remoteSort: false,
				fitColumns:true,  
				idField:'roleId',
				onSelect:btnDisplay,
				onUnselect:btnDisplay,
				onSelectAll:btnDisplay,
				onUnselectAll:btnDisplay,
				onResize:function(){
					//alert(1);
				},
				frozenColumns:[[
	                {field:'roleId',checkbox:true},

				]],
				columns:[[
						                {field:'roleId1',title:'角色Id',						
            	formatter:function(value,rec){
					  return rec.roleId;
					},width:120},
              {title:'角色名称',field:'roleName',width:120}
				]],	
				pagination:true,
				rownumbers:true,
				toolbar:[{
					id:'addBtn',
					text:'添加角色',
					iconCls:'myicon-add',
					handler:function(){
						validateSessionIsNull();
						if(!valiMenu("role/role!saveRole")){
							
							$.messager.alert('Success','您无权执行该操作!');

							return;
						}
						
						optFlag="add";
						$('#role-add').dialog("open");
					}
				},{
					id:'editBtn',
					text:'修改角色',
					iconCls:'myicon-edit',
					disabled:true,
					handler:function(){
						validateSessionIsNull();
						if(!valiMenu("role/role!saveRole")){
							
							$.messager.alert('Success','您无权执行该操作!');

							return;
						}
						
						optFlag = "edit";

						 $('#role-add').dialog("open");
					}
				},{
					id:'delBtn',
					text:'删除角色',
					disabled:true,
					
					iconCls:'myicon-delete',
					handler:function(){
						validateSessionIsNull();
						if(!valiMenu("role/role!deleteRole")){
							
							$.messager.alert('Success','您无权执行该操作!');

							return;
						}
						
						//if (confirm("真的要删除吗？")){
							  $.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
								  
								       if (r){   
								    	   var rows = $('#test').datagrid('getSelections');
											var ids = "";
											for(var i=0;i<rows.length;i++){
												ids = ids + rows[i].roleId +",";
											}
											$.ajax({  
										          type : "post",  
										          url : ctx+"/role/role!deleteRole",  
										          data : "ids="+ids.substring(0,ids.length-1),  
										          //async : false,  
										          success : function(data1){  
										        	  var data = $.parseJSON(data1);
										          	if(data.result.flag==FLAG_SUCCESS){
										          		$('#test').datagrid('clearSelections');
										          		$.messager.alert('Success','删除成功！'+data.result.msg);

										          		$('#test').datagrid('reload');
										          		$('#delBtn').linkbutton('disable');
										          		$('#editBtn').linkbutton('disable');
										          	}else if(data.result.flag==FLAG_FAILURE){
										          		
										          		$.messager.alert('Success','删除失败！'+data.result.msg);
										          	}else{
										          	  $.messager.alert('Error','操作失败！'+data.result.msg);
										          	}
										          	
										           		
										          }  
											});  

											
								   
								        }   
								   
								    });  
							
							
						
					}
				} ]
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
					          url : ctx+"/login/other!isUserExist.action",  
					          data : "queryVO.username="+value,  
					          async : false,  
					          success : function(data1){  
					        	  var data = $.parseJSON(data1);
					           		bl = data.userExist;
					          }  
						});  
						return !bl;
					},
				    message:'该账号已存在'
				}
				
			});
		});
		
