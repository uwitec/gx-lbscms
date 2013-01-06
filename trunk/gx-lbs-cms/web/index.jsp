<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>广西LBS后台管理系统</title>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/src/jquery.parser.js"></script>
</head>

<body style="background-color: #22468b;">
<div id="loginWin" class="easyui-window" title="LBS后台管理系统登录" style="width:300px;height:240px;padding:5px;"
   minimizable="false" maximizable="false" resizable="false"  closable="false" collapsible="false">
    <div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding:5px;background:#fff;border:0px solid #ccc;">
        <form id="loginForm" method="post">
            <div style="padding:5px 0;">
                <label for="login">帐　号:</label>
                <input type="text" id="username" name="queryVO.username" style="width:160px;"></input>
            </div>
            <div style="padding:5px 0;">
                <label for="password">密　码:</label>
                <input type="password" id="password" name="queryVO.password" style="width:160px;"></input>
            </div>
            <div style="padding:5px 0;">
                <label for="password">验证码:</label>
                <input type="text" id="code" name="code" style="width:80px;"></input>
            </div>            
             <div style="padding:5px 0;text-align: center;color: red;" id="showMsg"></div>
        </form>
            </div>
            <div region="south" border="false" style="text-align:right;padding:5px 0;">
                <a class="easyui-linkbutton" iconCls="icon-ok" href="javascript:void(0)" onclick="login()">登录</a>
                <a class="easyui-linkbutton" iconCls="icon-cancel" href="javascript:void(0)" onclick="cleardata()">重置</a>
            </div>
    </div>
</div>
</body>

<script type="text/javascript">
document.onkeydown = function(e){
    var event = e || window.event;  
    var code = event.keyCode || event.which || event.charCode;
    if (code == 13) {
        login();
    }
}
$(function(){
    $("input[name='login']").focus();
});
function cleardata(){
    $('#loginForm').form('clear');
}
function login(){
	var username = $("#username");
	var password = $("#password");
	var code = $("#code");
	if(username.val().length<1){
		$("#showMsg").html("请输入用户名!");
		username.focus();
	}else if(password.val().length<1){
		$("#showMsg").html("请输入密码!");
		password.focus();
	}else if(code.val().length<1){
		$("#showMsg").html("请输入验证码!");
		code.focus();
	}else{
		 $.ajax({            
             type:"POST",   //post提交方式默认是get
             url:"login/login!login.action", 
             data:$("#loginForm").serialize(),   //序列化               
             error:function(XMLHttpRequest, textStatus, errorThrown) {      // 设置表单提交出错                 
                 $("#showMsg").html(XMLHttpRequest);  //登录错误提示信息
             },
             success:function(data) {
            	 if(data.result.flag==FLAG_FAILURE){
            		 $("#showMsg").html(data.result.msg);  
            	 }else{
            		 $("#showMsg").html("");  
            		 top.location.href = "main.jsp";
            	 }
                 //document.location = "index.action";
             } 
       });       
	}
    /* if($("input[name='login']").val()=="" || $("input[name='password']").val()==""){
         $("#showMsg").html("用户名或密码为空，请输入");
         $("input[name='login']").focus();
    }else{
            //ajax异步提交  
           $.ajax({            
                  type:"POST",   //post提交方式默认是get
                  url:"login.action", 
                  data:$("#loginForm").serialize(),   //序列化               
                  error:function(request) {      // 设置表单提交出错                 
                      $("#showMsg").html(request);  //登录错误提示信息
                  },
                  success:function(data) {
                      document.location = "index.action";
                  }            
            });       
        } */
}
</script>
</html>