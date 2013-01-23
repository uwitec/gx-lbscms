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
				var ip = $('#ip').val(); // 
				var clientId = $('#clientId').val(); //
				var loginName = $('#loginName').val(); // 企业账号
				var userName = $('#userName').val(); // 企业名称
				$('#test').datagrid('load',{
					"queryVO.ip":ip,
					"queryVO.clientId":clientId,
					"queryVO.loginName":loginName,
					"queryVO.userName":userName
				});
				
			 
			 }); 
			
			$("#editBtn").click(function(){
				optFlag="edit";
				var rows = $('#test').datagrid('getSelections');

				if(rows.length==1){
					$('#ips-add').dialog("open");
				}
				
			});
			$("#addBtn").click(function(){
				optFlag="add";

				$('#ips-add').dialog("open");
			
				});
			$("#delBtn").click(function(){
				var rows = $('#test').datagrid('getSelections');

				if(rows.length>0){
					$.messager.confirm('Confirm','确认要删除选中数据？',function(r){   
						  
					       if (r){
					    	   var rows = $('#test').datagrid('getSelections');
								var cellIps = "";
								var userIps = "";
								for(var i=0;i<rows.length;i++){
									cellIps = cellIps + rows[i].id +",";
								}
								for(var i=0;i<rows.length;i++){
									userIps = userIps + rows[i].reId +",";
								}
								//alert(cellIps);
								$.ajax({  
							          type : "post",   
							          url : ctx+"/ips/ips!delete?cellIpsIds="+cellIps.substring(0,cellIps.length-1)+"&userIpsIds="+userIps.substring(0,userIps.length-1),  
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
							          	}else if(data.result.flag==FLAG_FAILURE){
							          		
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
					$.getJSON(ctx+'/userWhite/findAllUserWhenAddWhiteMdn', function(json) { 
						$('#combobox').combobox({
							data :json.sysUserList, 
							width:150,
							panelHeight:150,
							valueField:'userId',
							editable:false ,
							textField:'userName',
							onLoadSuccess:function(){
								$('#combobox').combobox('setValue',json.sysUserList[0].userId);
							}
						});
						//alert(json.roleList[0].roleId);
						
					});
					
					if(optFlag=="add"){
						$("#ips-add").dialog("setTitle","添加用户!");
						$('#cellIpsId').val("");
						$('#ipsId').val();
					}else if(optFlag=="edit"){
						
						$("#ips-add").dialog("setTitle","修改用户!");
						var row = $('#test').datagrid('getSelected');
						$.getJSON(ctx+'/ips/ips!findCellIps.action?queryVO.id='+row.id+"&queryVO.reId="+row.reId, function(json) { 
							$("#saveForm").form('load', {
								"cellIps.ip": json.cellIps.ip,
			                    "cellIps.clientid": json.cellIps.clientid,
			                    "cellIps.password": json.cellIps.password,
			                    "cellIps.memo": json.cellIps.memo,
			                    "password":json.cellIps.password,
			                    "cellIps.id":json.cellIps.id
			                 });
							$('#ipsId').val(row.reId);
							$('#combobox').combobox('setValue',row.userId);
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
					        url:ctx+"/ips/ips!save",
					        onSubmit: function(){
					              return $(this).form("validate");
					        },
					        success:function(data1){
					        	var datas = $.parseJSON(data1);
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','添加成功！');
					        		$('#cellIpsId').val("");
					        		$('#ipsId').val("");
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
					
						$('#ip1').val("");
						$('#pwd1').val("");
						$('#pwd2').val("");
						$('#clientId1').val("");
						$('#cellIpsId').val("");
						$('#ipsId').val("");
						
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
				
				nowrap: true,
				autoRowHeight: false,
				striped: true,
				
				toolbar: '#tb',
				url:ctx+'/ips/ips!list',
				onOpen:function(){
					//$('#test').datagrid('load');
				},
				sortName: 'id',
				sortOrder: 'desc',
				remoteSort: false,
				idField:'id',
				onSelect:btnDisplay,
				onUnselect:btnDisplay,
				onSelectAll:btnDisplay,
				onUnselectAll:btnDisplay,
				onResize:function(){
					//alert(1);
				},
				frozenColumns:[[
	                {field:'id',checkbox:true}
	                
				]],
				columns:[[
						{field:'ip',title:'IP',width:120},
						{field:'clientId',title:'接口账号',width:120},
						{title:'企业名称',field:'userName'},
						{field:'loginName',title:'登录账号',width:120},
						{field:'memo',title:'备注'},
						{field:'reId',hidden:true},
				]],
				pagination:true,
				rownumbers:true
				
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
						
						var bl = false;
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