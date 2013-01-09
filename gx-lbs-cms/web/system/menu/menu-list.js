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
		$('#tt').treegrid({  
			treeField: 'menuName',  
			idField: 'menuId',
			url: ctx+"/menu/menu!list",  
			pagination:true,  
			onSelect:btnDisplay,
			fitColumns:true,  
			//loadMsg:''
			height: document.body.scrollHeight,
			rownumbers: true,  
			animate:true,  
			collapsible:true,  
			fitColumns:true,  
			showFooter:true ,				
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
					
					
				}
			},'-',{
				id:'cancelEditBtn',
				text:'撤销修改',
				iconCls:'myicon-edit',
				disabled:true,
				handler:function(){
					var rows = $('#tt').treegrid('getSelected');
					if(rows!=null){
						$('#tt').treegrid('cancelEdit', rows.menuId);
						$('#tt').treegrid('clearSelections');
						$('#delBtn').linkbutton('disable');
						$('#editBtn').linkbutton('disable');
						$('#cancelEditBtn').linkbutton('disable');
					}

					//editNode();
				}
			},'-',{
				id:'editBtn',
				text:'保存修改',
				iconCls:'icon-save',
				disabled:true,
				handler:function(){
					
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
				         
				          success : function(data){  
				        	  if(data.result.flag==FLAG_SUCCESS){
				        		  $.messager.alert('Success','保存成功！');
				        	  }else{
				        		  $.messager.alert('FAILURE','保存失败！');
				        	  }
				           		//bl = data.userExist;
				          }  
					});
				}
			},'-',{
				id:'delBtn',
				text:'删除菜单',
				disabled:true,
				
				iconCls:'myicon-delete',
				handler:function(){
					//if (confirm("真的要删除吗？")){
						  $.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
							  
							       if (r){   
										var row = $('#tt').datagrid('getSelected');

							    	  
										$.ajax({  
									          type : "post",  
									          url : ctx+"/menu/menu!deleteMenu",  
									          data : "sysMenu.menuId="+row.menuId,  
									         async : false,  
									          success : function(data){  
									        	 
									          	if(data.result.flag==FLAG_SUCCESS){

									          		$('#tt').treegrid('clearSelections');
									          		$('#tt').treegrid('beginEdit', row.menuId);
									          		$('#tt').treegrid('remove', row.menuId);
									          		
									          		$.messager.alert('Success','删除成功！');
									          	  //   $('#tt').datagrid('deleteRow', index);
									          		//$('#tt').treegrid('reload');
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
		 });
	});
		