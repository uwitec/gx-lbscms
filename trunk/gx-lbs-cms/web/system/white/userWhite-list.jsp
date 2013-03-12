<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Complex Layout - jQuery EasyUI Demo</title>
<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/${themes }/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css">

	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx }/js/upload/ajaxfileupload.js"></script>
	<script type="text/javascript" src="${ctx }/system/white/upload.js"></script>
<script type="text/javascript" src="${ctx }/js/util.js"></script>			
		
	<script>

		$(function(){


			$('#areanameCombobox').combobox({
				data :null, 
				width:150,
				panelHeight:150,
				valueField:'value',
				editable:false ,
				textField:'value'
			});
			$('#areanameCombobox').combobox('setValue','全部');
			
			
			// searchAction:
				 $('#btnSearch').bind('click', function(){
					var beginTime = $('#beginTime').datebox('getValue'); // 开始时间
					var endTime = $('#endTime').datebox('getValue'); // 结束时间
					var mdn = $('#mdn').val(); // 号码
					var loginName = $('#loginName').val(); // 企业账号
					var userName = $('#userName').val(); // 企业名称
					var areaname =  $('#areanameCombobox').combobox('getValue');
					$('#test').datagrid('load',{
						"queryVO.beginTime":beginTime,
						"queryVO.endTime":endTime,
						"queryVO.mdn":mdn,
						"queryVO.loginName":loginName,
						"queryVO.areaname":areaname,
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
				height:200,
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

						
						$('#loginName').val("");
					}else if(optFlag=="edit"){
						
					

					}
				},
				
				onOpen:function(){

					
				    
				},
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						
						$("#saveForm").form("submit", {
					        url:ctx+"/userWhite/saveWhiteMdn",
					        onSubmit: function(){
					              return $(this).form("validate");
					        },
					        success:function(data1){
					        	 var datas = $.parseJSON(data1);
					        	//var datas = eval("("+data+")");
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','添加成功！');
					        		
					        		$("#saveForm").form("clear");
					        		$('#user-add').dialog("close");
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
		
			

			$("#addBtn").click(function(){
				optFlag="add";
				if(!valiMenu("userWhite/saveWhiteMdn")){
					
					$.messager.alert('Success','您无权执行该操作!');

					return;
				}
				$('#user-add').dialog("open");
			
				});
			$("#delBtn").click(function(){
				var rows = $('#test').datagrid('getSelections');
				if(!valiMenu("userWhite/userWhite!deleteWhiteMdn.action")){
					
					$.messager.alert('Success','您无权执行该操作!');

					return;
				}
				if(rows.length>0){
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
							          success : function(data1){
							        	  var data = $.parseJSON(data1);
							          	if(data.result.flag==FLAG_SUCCESS){
							          		$('#test').datagrid('clearSelections');
							          		$.messager.alert('Success','删除成功！');

							          		$('#test').datagrid('reload');
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

				
				
			});
			
			//alert(parent.document.getElementById("frameId").offsetHeight);
			$('#test').datagrid({
				//title:'My DataGrid',
				pageList:[10,20,30,40,50],
				
				width:'auto',
				onLoadError:function(){
					$.messager.alert('Error','加载数据失败!,或登录超时！请重新登录，如问题仍未解决请联系管理员!','error',function(){
						 top.location.href=ctx+"/index.jsp";  
					});
				},
			
				nowrap: true,
				autoRowHeight: false,
				toolbar: '#tb',
				fitColumns:true,  
				striped: true,
				fit:true,
				pageSize:20,
				url:ctx+'/userWhite/list',
				// huhuadd
				onLoadSuccess:function () {  
	
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
					//{field:'memo',title:'描述',width:120},
					
					{field:'createTime',title:'创建时间',width:180,sortable:true,
						formatter:function(value,rec){
						  var ctime =rec.createTime;
						  ctime = ctime.replace(new RegExp("T","gm")," ");
						  return ctime;
						}
					}
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
						
						var bl =false;
						$.ajax({  
					          type : "post",  
					          url : ctx+"/userWhite/userWhite!isWhiteMdnExist.action",  
					          data : "queryVO.mdn="+value,  
					          async : false,  
					          success : function(data1){
					        	  var data = $.parseJSON(data1);
					           		bl = data.whiteMdnExist;
					          }  
						});
						var rules = $.fn.validatebox.defaults.rules; 
						if(!(/^1[3|4|5|8][0-9]\d{8}$/.test(value))){
						     rules.exist.message = "手机号码不正确！"; 
                             return false;
						} 
						if(bl){
						     rules.exist.message = "该号码已存在！"; 
                             return false;
						}
						return true;
					},
				    message:''
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
	<table id="test" title="白名单列表" data-options="border:true" ></table>
</div>
<!-- huhuadd -->
<div id="tb" style="padding:5px;height:auto">    
    <div style="margin-bottom:5px">    
       
       <a href="#" class="easyui-linkbutton"  id="addBtn" iconCls="myicon-add" plain="true">添加白名单</a> 
        <a href="#" class="easyui-linkbutton" id="delBtn" iconCls="myicon-delete" disabled="disabled" plain="true">删除白名单</a>
       
        <a href="#" class="easyui-linkbutton" id="uploadBtn" iconCls="myicon-add"  plain="true">上传白名单</a>

    </div>   
     
    <div>    
     
       
       开始时间: <input id="beginTime" class="easyui-datebox" name="beginTime" readOnly="readOnly" style="width:90px" type="text">    
        结束时间: <input id="endTime" class="easyui-datebox" name="endTime" readOnly style="width:90px" type="text">
       号码:<input id="mdn" name="mdn" type="text"  style="width:110px" />
       企业账号:<input id="loginName" name="ecsiId"  type="text" style="width:110px"/>
      企业名称:<input id="userName" name = "ecsiName" type="text" style="width:110px" />
  地市:<select id="areanameCombobox" class="easyui-combobox" name="phoneSection.areaname" data-options="required:true">
												<option value="全部" >
													全部
												</option>
												<option value="广西南宁"  >
													广西南宁
												</option>
												<option value="广西柳州"  >
													广西柳州
												</option>
												<option value="广西玉林"  >
													广西玉林
												</option>
												<option value="广西桂林"  >
													广西桂林
												</option>
												<option value="广西百色"  >
													广西百色
												</option>
												<option value="广西河池"  >
													广西河池
												</option>
												<option value="广西北海"  >
													广西北海
												</option>
												<option value="广西梧州"  >
													广西梧州
												</option>
												<option value="广西钦州"  >
													广西钦州
												</option>
												<option value="广西防城港"  >
													广西防城港
												</option>
												<option value="广西贺州"  >
													广西贺州
												</option>
												<option value="广西贵港"  >
													广西贵港
												</option>
												<option value="广西来宾"  >
													广西来宾
												</option>
												<option value="广西崇左"  >
													广西崇左
												</option>
                        </select>      
	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>        
         
    </div>    
</div>    

<!-- <div id="selfSearchBox">
	开始时间:<input id="beginTime" class="easyui-datebox"  name="beginTime" type="text" />	
	结束时间:<input id="endTime" class="easyui-datebox" name="endTime"  type="text">
	<div id="separator" class="datagrid-btn-separator"></div>
	号码:<input id="mdn" name="mdn" type="text"  style="width:110px" />
	</br>
	企业账号:<input id="loginName" name="ecsiId"  type="text" style="width:110px"/>
	企业名称:<input id="userName" name = "ecsiName" type="text" style="width:200px" />
	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a> 
</div> -->
<div id="separator" class="datagrid-btn-separator"></div>
<!-- huhuadd -->


	<div id="user-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"   fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
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
                        <select id="roleCombobox" class="easyui-combobox" name="sysUserWhite.userId" data-options="required:true"></select>
                    </td>  
                </tr>  
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
	<div id="upload-white" data-options="iconCls:'icon-save'" title="上传白名单" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"   fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="uploadForm" enctype="multipart/form-data" method="post">  
            <table>  
                <tr> 
                    <td>号码:</td>
                    <td>
                        <img src="${ctx }/js/upload/loading.gif" id="loading" style="display: none;">
        <input type="file" id="file" name="file" />
       


                    
                    </td>  
                </tr>  
                                <tr>  
                    <td>格式:</td>  
                    <td>  
                        txt文本，每行一个号码。
                    </td>  
                </tr>                                   
                <tr>  
                    <td>所属EC/SI:</td>  
                    <td>  
                        <select id="roleCombobox1" class="easyui-combobox" name="sysUserWhite.userId" data-options="required:true"></select>
                    </td>  
                </tr>  

            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
	
	
</body>
</html>