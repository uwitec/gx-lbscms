<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/var.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
	<title>${title}</title>
	<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/${themes }/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/src/jquery.parser.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/css/css.css"/>
	<script type="text/javascript" src="${ctx }/js/util.js"></script>
	<script type="text/javascript" src="${ctx }/js/jgxLoader.js"></script>
	<script>
	
	if(userId==null||userId==''){
		alert('登录超时!');
		top.location.href='index.jsp';
	}
		$(function(){
			$('#pwd-update').dialog({
				autoOpen: false ,
				modal:true,
				width:320,   
				closable:false,
				height:200,
				resizable:false,
	
				buttons:[{
					text:'确定',
					iconCls:'icon-ok',
					handler:function(){
						
						$("#saveForm").form("submit", {
					        url:ctx+"/login/other!updatePwd",
					        onSubmit: function(){
					              return $(this).form("validate");
					        },
					        success:function(data1){
					        	var datas = $.parseJSON(data1);
					        	//var datas = eval("("+data+")");
					        	if(datas.result.flag==FLAG_SUCCESS){
					        		$.messager.alert('Success','修改成功！');
					        		
					        		$("#saveForm").form("clear");
					        		$('#pwd-update').dialog("close");
					        	}else{
					        		$.messager.alert('Error','修改失败！'+datas.result.msg);
					        	}
					        }
					});
					}
				},{
					
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
					
						$('#oldPwd').val("");
						$('#pwd1').val("");
						$('#pwd2').val("");
						$('#pwd-update').dialog('close');
					}
				}]
			});
			function addTab(title, url){

				if ($('#tabs').tabs('exists', title)){
					 $('#tabs').tabs('select', title);
			    } else {
					var content = '<iframe scrolling="auto" name="frameName"  frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
				     $('#tabs').tabs('add',{
			            title:title,
			            content:content,
			            closable:true
			        });
			    }
			}
			 $.post(ctx+"/menu/menu!findMenuByUserId.action", {
			 	}, function(data1) {  
			 		
			 		var json = $.parseJSON(data1);
			 		if(json.result!=null){
			 			if(json.result.flag==4){
			 				$.messager.alert('Success',json.result.msg,'error',function(){
		         				top.location.href=ctx+"/index.jsp";
		         			});
			 			}
			 		}
			 		$('#tree').tree({  
                        //parent : node.target,  
                        //dataType:"json",
                        animate:true,  
                        data : json.treeList,
                       	
                        onClick: function(node){
                        	$.ajax({  
								type : "post",  
								  url:ctx+"/login/other!isSessionNull",
					         	async : false,  
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
                        	
                        	
                        	
                        	$(this).tree('toggle', node.target);
                        	if(node.attributes){
                        		addTab(node.text,ctx+"/"+node.attributes);
                        	}
                            //$(this).tree('toggle', node.target);
                           //alert('you dbclick '+node.text);
                        }
                        
                    });  
			 });

			

			$("#updatePwdBtn").click(function(){
				$('#pwd-update').dialog("open");
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
				validatePwd:{
					validator:function(value,param){  
						
						var bl = false;
						$.ajax({  
					          type : "post",  
					          url : ctx+"/login/other!validatePwd.action",  
					          data : "queryVO.oldPwd="+value,  
					          async : false,  
					          success : function(data1){  
					        	  var data = $.parseJSON(data1);
					        	  if(data.result.flag==4){
					        		  $.messager.alert('Success','登录超时，请重新登录！','error',function(){
					        			  top.location.href=ctx+"/index.jsp";  
					        		  });
					        		  
					        	  }else if(data.result.flag==2){
					        		  $.messager.alert('Error',data.result.msg);
					        	  }else if(data.result.flag==3){
					        		  bl = false;
					        	  }else{
					        		  bl= true;
					        	  }
					           		
					          }  
						});  
						return bl;
					},
				    message:'源密码错误!'
				}
				
			});
			//$("body").attr({style:""});
		});
	</script>
</head>
<body class="easyui-layout" >
	<div data-options="region:'north',split:true" class="top" style="height: 100px" >
		    <div class="header">
      <p class="name"><img src="${ctx }/images/name.png" /><p>
      <p class="info"><img src="${ctx }/images/ico1.png"><a href="${ctx }/login/login!logout.action">退出</a><img src="images/ico2.png" /><a href="#" id="updatePwdBtn">修改密码</a></p> 
    </div>

	</div>

	<div id="tree" data-options="region:'west',iconCls:'icon-reload',split:true" title="导航栏" style="width:180px;background-color: #e9f2ff">
		<!-- <ul class="easyui-tree" data-options="url:'tree_data.json'"></ul> -->
		<ul class="easyui-tree" ></ul>
	</div>
	<div data-options="region:'south',split:true" style="height:30px;background:#fafafa;margin-left: auto;margin-right: auto;text-align: center;">Copyright © 中国联合网络通信有限公司广西分公司 All Rights Reserved</div>
	<div id="center" data-options="region:'center',border:false"  style="overflow:hidden;">
		<div class="easyui-tabs" id="tabs" data-options="fit:true,border:false">
			<div title="欢迎登录" style="padding:20px;overflow:hidden;background-color: #e9f2ff"> 
				<div style="margin-top:20px;background-color: #e9f2ff">
					<h3>尊敬的<font color="red"><%=session.getAttribute(AuthenticationUtil.ACCOUNT_SESSION_KEY) %></font>欢迎登录LBS后台管理系统!</h3>
					<!-- <ul>
						<li>easyui is a collection of user-interface plugin based on jQuery.</li> 
						<li>using easyui you don't write many javascript code, instead you defines user-interface by writing some HTML markup.</li> 
						<li>easyui is very easy but powerful.</li> 
					</ul>
					 -->
				</div>
			</div>

		</div>
	</div>
	
	
		<div id="pwd-update" data-options="iconCls:'icon-save'" title="修改密码" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"  fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr>  
                    <td>原密码:</td>  
                    <td><input class="easyui-validatebox" type="password" id="oldPwd" validType="validatePwd"   data-options="required:true"></input></td>  
                </tr>                
                <tr>  
                    <td>新密码:</td>  
                    <td><input class="easyui-validatebox" type="password" id="pwd1"    data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>新密码确认:</td>  
                    <td><input class="easyui-validatebox" id="pwd2" validType="equalTo['pwd1']" type="password" name="queryVO.password" data-options="required:true"></input></td>  
                </tr>                  
             
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>