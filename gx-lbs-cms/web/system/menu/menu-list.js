	function editNode(){
		var node = $('#tt').treegrid('getSelected');
		//alert(node.menuId);
		if (node){
			$('#tt').treegrid('beginEdit',node.menuId);
		}
	}
	function saveNode(){
		var node = $('#tt').treegrid('getSelected');
		if (node){
			$('#tt').treegrid('endEdit',node.menuId);
		}
	}
	function cancelNode(){
		var node = $('#tt').treegrid('getSelected');
		if (node){
			$('#tt').treegrid('cancelEdit',node.menuId);
		}
	}
	var lastIndex;
	function btnDisplay(rowIndex, rowData){
		//alert(rowIndex.menuId);
		if (lastIndex != rowIndex.menuId){
			$('#tt').datagrid('cancelEdit', lastIndex);
			$('#tt').datagrid('beginEdit', rowIndex.menuId);
			
		}else{
			$('#tt').datagrid('beginEdit', rowIndex.menuId);
		}
		lastIndex = rowIndex.menuId;
		var rows = $('#tt').datagrid('getSelections');	
		if(rows.length==1){
			$('#editBtn').linkbutton('enable');
			$('#cancelEditBtn').linkbutton('enable');			
		}else{
			$('#editBtn').linkbutton('disable');
			$('#cancelEditBtn').linkbutton('disable');
		}
		if(rows.length>0){
			$('#delBtn').linkbutton('enable');
		}else{
			$('#delBtn').linkbutton('disable');
		}
	}	
	$(function(){
		var optFlag = "";
		$('#tt').treegrid({  
			treeField: 'menuName',  
			idField: 'menuId',
			url: ctx+"/menu/menu!list",  
			pagination:true,  
			onLoadError:function(){
				$.messager.alert('Error','加载数据失败!,或登录超时！请重新登录，如问题仍未解决请联系管理员!','error',function(){
					 top.location.href=ctx+"/index.jsp";  
				});
			},
			pageSize:20,
			onSelect:btnDisplay,
			fitColumns:true,  
			//loadMsg:''
			striped: true,
			rownumbers: true,  
			animate:true,  
			//collapsible:true,  
			fitColumns:true,  
			showFooter:true ,			
			fit:true,
	        onBeforeLoad:function(row, param){
	    	   if(row==null){
	    		   $('#editBtn').linkbutton('disable');
	    		   $('#cancelEditBtn').linkbutton('disable');
	    		   $('#delBtn').linkbutton('disable');
	    		   $(this).treegrid('options').url = ctx+"/menu/menu!list";     		  
	    	   }
			},
	        columns:[[  
	           {title:'Id',field:'menuId'},         
	           {title:'菜单名称',field:'menuName',editor:'text',width:180},  
	           {field:'url',title:'URL',editor:'text',align:'right'},  
	           {field:'gread',title:'Gread',width:80},  
	           {field:'isMenuTree',title:'MenuTree',width:80} ,
	           {field:'parentId',title:'ParentId',width:80}
	        ]]  ,
	        onBeforeExpand: function (row) {  
	        	$(this).treegrid('options').url = ctx+"/menu/menu!list?queryVO.menuId=" + row.menuId;  
	        },
	        toolbar:[{
				id:'addBtn',
				text:'添加菜单',
				
				iconCls:'myicon-add',
				handler:function(){
					optFlag = "add";
					validateSessionIsNull();
					if(!valiMenu("menu/menu!saveMenu")){
						
						$.messager.alert('Success','您无权执行该操作!');

						return;
					}
					$('#menu-add').dialog("open");
				}
			},{
				id:'cancelEditBtn',
				text:'撤销修改',
				iconCls:'myicon-edit',
				disabled:true,
				handler:function(){
					validateSessionIsNull();
					var rows = $('#tt').treegrid('getSelected');
					if(rows!=null){
						$('#tt').treegrid('cancelEdit', rows.menuId);
						$('#tt').treegrid('clearSelections');
						$('#delBtn').linkbutton('disable');
						$('#editBtn').linkbutton('disable');
						$('#cancelEditBtn').linkbutton('disable');
					}
				}
			},{
				id:'editBtn',
				text:'保存修改',
				iconCls:'icon-save',
				disabled:true,
				handler:function(){
					validateSessionIsNull();
					if(!valiMenu("menu/menu!updateMenu")){
						
						$.messager.alert('Success','您无权执行该操作!');

						return;
					}
					var rows = $('#tt').datagrid('getSelected');
					if(rows!=null){
						$('#tt').treegrid('endEdit', rows.menuId);
						$('#tt').treegrid('clearSelections');
						$('#editBtn').linkbutton('disable');
						$('#cancelEditBtn').linkbutton('disable');
						$('#delBtn').linkbutton('disable');
					}
					$.ajax({  
				          type : "post",  
				          url : ctx+"/menu/menu!updateMenu",  
				          data : "queryVO.menuName="+rows.menuName+"&queryVO.menuId="+rows.menuId+"&queryVO.url="+rows.url,   
				          success : function(data1){  
				        	  var data = $.parseJSON(data1);
				        	  if(data.result.flag==FLAG_SUCCESS){
				        		  $.messager.alert('Success','保存成功！');
				        	  }else{
				        		  $.messager.alert('FAILURE','保存失败！'+  data.result.msg);
				        	  }
				          }  
					});
				}
			},{
				id:'delBtn',
				text:'删除菜单',
				disabled:true,
				iconCls:'myicon-delete',
				handler:function(){
					validateSessionIsNull();
					if(!valiMenu("menu/menu!deleteMenu")){
						
						$.messager.alert('Success','您无权执行该操作!');

						return;
					}
					$.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
						if (r){   
							var row = $('#tt').datagrid('getSelected');
							$.ajax({  
								type : "post",  
					         	url : ctx+"/menu/menu!deleteMenu",  
					         	data : "sysMenu.menuId="+row.menuId,  
					         	async : false,  
					         	success : function(data1){  
					         		var data = $.parseJSON(data1);
					         		if(data.result.flag==FLAG_SUCCESS){
						          		$('#tt').treegrid('clearSelections');
						          		$('#tt').treegrid('beginEdit', row.menuId);
						          		$('#tt').treegrid('remove', row.menuId);						        
						          		$.messager.alert('Success','删除成功！');
						          		$('#delBtn').linkbutton('disable');
						          		$('#editBtn').linkbutton('disable');
					         		}else {
					         			$.messager.alert('Success','删除失败！'+data.result.msg);
					         		}		
								}  
							});  
						}
					});  
				}
			} ]  
		 });
		$('#menu-add').dialog({
			autoOpen: false ,
			modal:true,
			width:320,   
			closable:false,
			height:300,
			resizable:false,
			onBeforeOpen:function(){
				$.getJSON(ctx+'/menu/menu!findMenu.action?', function(json) { 
					$('#parent1').combobox({
						data:json.listOne,
						valueField:'menuId',
						textField:'menuName',
						editable:false ,
						onLoadSuccess:function(){
							$('#parent1').combobox('setValue',json.listOne[0].menuId);
						}
					});
					
					$('#parent2').combobox({
						data:json.listTwo,
						valueField:'menuId',
						textField:'menuName',
						editable:false ,
						onLoadSuccess:function(){
							$('#parent2').combobox('setValue',json.listTwo[0].menuId);
						}
					});
					
				});
				
				if(optFlag=="add"){
					$('#loginName').val("");
				}
			},
			buttons:[{
				text:'确定',
				iconCls:'icon-ok',
				handler:function(){
					validateSessionIsNull();
					var v = $('#gread').combobox('getValue');
					
					if(v==1){
						$("#parentId").val(0);
						
					}else if(v==2){
						$('#parentId').val($("#parent1").combobox('getValue'));
						
					}else if(v==3){
						$('#parentId').val($("#parent2").combobox('getValue'));
					}
					$("#saveForm").form("submit", {
				        url:ctx+"/menu/menu!saveMenu",
				        onSubmit: function(){
				              return $(this).form("validate");
				        },
				        success:function(data){
				        	var datas = eval("("+data+")");
				        	if(datas.result.flag==FLAG_SUCCESS){
				        		$.messager.alert('Success','添加成功！');
				        		$("#saveForm").form("clear");
				        		$('#gread').combobox('setValue',1);   //\
				        		$('#menu-add').dialog("close");
				        		$('#tt').treegrid('reload');
				        	}else{
				        		$.messager.alert('Error','添加失败！');
				        	}
				        		
				        }
					});
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$("#saveForm").form("clear");
					$('#gread').combobox('setValue',1);   //\
					$('#menu-add').dialog('close');
				}
			}]
		});		
		
		$('#gread').combobox({
			onSelect:function(rec){
				var val = rec.value;
				if(val==1){
					$('#parent1').combobox('disable');   //\
					$('#parent2').combobox('disable');   //
				}else if(val==2){
					$('#parent1').combobox('enable'); 
					$('#parent2').combobox('disable');   
				}else if(val==3){
					$('#parent1').combobox('disable');  
					$('#parent2').combobox('enable');   
				}
			}
		});
		
		
	});
		