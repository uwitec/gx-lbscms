function qq(value,name){
		
		//重新刷新datagrid，并增加两个参数key、type，这里是POST传值  1111
		if(name=='queryVO.loginName'){
		$('#test').datagrid('load',{"queryVO.loginName":value});
		}else if(name=='queryVO.username'){
			$('#test').datagrid('load',{"queryVO.username":value});	
		}
	}
		$(function(){
			$('#btnSearch').bind('click', function(){
				var sectionValue = $('#sectionValue').val(); // 
				$('#test').datagrid('load',{
					"queryVO.sectionValue":sectionValue
				});
				
			 
			 }); 
			
			$("#editBtn").click(function(){
				if(!valiMenu("ips/ips!save")){
					
					$.messager.alert('Success','您无权执行该操作!');

					return;
				}
				//$('#combobox').combobox('setValue',"");
				optFlag="edit";
				var rows = $('#test').datagrid('getSelections');

				if(rows.length==1){
					$('#ips-add').dialog("open");
				}
				
			});
			$("#addBtn").click(function(){
				optFlag="add";
				if(!valiMenu("phonesection/phonesection!save")){
					
					$.messager.alert('Success','您无权执行该操作!');

					return;
				}

				$('#ips-add').dialog("open");
			
				});
			$("#delBtn").click(function(){
				var rows = $('#test').datagrid('getSelections');
				if(!valiMenu("phonesection/phonesection!save")){
					
					$.messager.alert('Success','您无权执行该操作!');

					return;
				}
				if(rows.length>0){
					$.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
						  
					       if (r){
					    	   var rows = $('#test').datagrid('getSelections');
								var ids = "";
								for(var i=0;i<rows.length;i++){
									ids = ids + rows[i].sectionId +",";
								}

								//alert(cellIps);
								$.ajax({  
							          type : "post",   
							          url : ctx+"/phonesection/phonesection!delete?ids="+ids.substring(0,ids.length-1),  
							         // data : "cellIps="+cellIps.substring(0,cellIps.length-1)+"&userIps="+userIps.substring(0,userIps.length-1),  
							          //async : false,  
							          success : function(data1){
							        	  var data = $.parseJSON(data1);
							          	if(data.result.flag==FLAG_SUCCESS){
							          		$('#test').datagrid('clearSelections');
							          		$.messager.alert('Success','删除成功！');

							          		$('#test').datagrid('reload');
							          		$('#delBtn').linkbutton('disable');
							          		$('#editBtn').linkbutton('disable');
							          		ids="";
							          	}else {
							          		
							          		$.messager.alert('Success','删除失败！'+data.result.msg);
							          	}
							          	
							           		
							          }  
								});  

								
					   
					        }   
					   
					    });  
				}

				
				
			});			
			document.onkeydown = function(e){   
				  e = e || event;
				  if(e.keyCode==27){
				      return false;
				    }
				 }
			var data = [{   
				 
				      "id":1,   
				 
				     "text":"联通"  
				 
				 },{   
				 
				      "id":2,   
				 
				     "text":"移动"  
				 
				 },{   
				 
				      "id":3,   
				 
				      "text":"电信"
				 
				 }]  ;

			var optFlag="";
			$('#ips-add').dialog({
				autoOpen: false ,
				modal:true,
				width:320,   
				
				closable:false,
				height:300,
				resizable:false,
				//noheader:true,
				onBeforeOpen:function(){
					
						$('#areanameCombobox').combobox({
							width:150,
							panelHeight:150,
							valueField:'areaname',
							editable:false
						});
						//alert(json.roleList[0].roleId);
						$('#carrierCombobox').combobox({
							width:150,
							panelHeight:150,
							data:data,
							valueField:'id',
							textField:'text',
							editable:false
						});
				
					
					if(optFlag=="add"){
						$("#ips-add").dialog("setTitle","添加号段!");
						$('#sectId1').val("");
						$('#areanameCombobox').combobox('setValue',"");
						$('#carrierCombobox').combobox('setValue',"");
					}else if(optFlag=="edit"){
						
						$("#ips-add").dialog("setTitle","修改号段!");
						var row = $('#test').datagrid('getSelected');
						
						$.getJSON(ctx+'/phonesection/phonesection!findPhoneSectionById.action?phoneSection.sectionId='+row.sectionId, function(json) {
							//alert(json.phoneSection.sectionValue);
							$("#saveForm").form('load', {
								"phoneSection.sectionId": json.phoneSection.sectionId,
			                    "phoneSection.sectionValue": json.phoneSection.sectionValue
			                 });
							$('#areanameCombobox').combobox('setValue',json.phoneSection.areaname);
							$('#carrierCombobox').combobox('setValue',json.phoneSection.carrier);
						});

					}
				},
				
				onOpen:function(){

					
				    
				},
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						
						$("#saveForm").form("submit", {
					        url:ctx+"/phonesection/phonesection!save",
					        onSubmit: function(){
					              return $(this).form("validate");
					        },
					        success:function(data1){
					        	var datas = $.parseJSON(data1);
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','添加成功！');

					        		$("#saveForm").form("clear");
					        		$('#ips-add').dialog("close");
					        		$('#test').datagrid('reload');
					        		
					        	}else{
					        		$.messager.alert('Success','添加失败'+datas.result.msg);
					        	}
					        }
					});
					}
				},{
					
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
					
						$('#sectId1').val("");
						
						$('#ips-add').dialog('close');
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
				
				fit:true,
				pageSize:20,
				fitColumns:true,  
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				
				onLoadError:function(){
					$.messager.alert('Error','加载数据失败!,或登录超时！请重新登录，如问题仍未解决请联系管理员!','error',function(){
						 top.location.href=ctx+"/index.jsp";  
					});
				},
				toolbar: '#tb',
				url:ctx+'/phonesection/phonesection!list',
				onOpen:function(){
					//$('#test').datagrid('load');
				},
				sortName: 'sectionId',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'sectionId',
				onSelect:btnDisplay,
				onUnselect:btnDisplay,
				onSelectAll:btnDisplay,
				onUnselectAll:btnDisplay,
				onResize:function(){
					//alert(1);
				},
				frozenColumns:[[
	                {field:'sectionId',checkbox:true}
				]],
				columns:[[
						{field:'sectionValue',title:'号段',width:120},
						{field:'areaname',title:'属地',width:120},
						{field:'sectId',hidden:true},
				]],
				pagination:true,
				rownumbers:true
				
			}  
			
			);
			
			


			
		});
