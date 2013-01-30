<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/var.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>${title }</title>
	<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/${themes }/easyui.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/icon.css"/>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/src/jquery.parser.js"></script>

	<link rel="stylesheet" type="text/css" href="${ctx}/css/css.css"/>
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
            url:ctx+"/login/login!validateCode.action", 
            data:$("#loginForm").serialize(),   //序列化               
            error:function(XMLHttpRequest, textStatus, errorThrown) {      // 设置表单提交出错                 
               alert(XMLHttpRequest);  //登录错误提示信息
            },
            success:function(data1) {
            	var data = $.parseJSON(data1);
           	 if(data.result.flag==FLAG_FAILURE){
           		alert("验证码错误!");  
           	 }else{
           		$.ajax({            
                    type:"POST",   //post提交方式默认是get
                    url:ctx+"/login/login!login.action", 
                    data:$("#loginForm").serialize(),   //序列化               
                    error:function(XMLHttpRequest, textStatus, errorThrown) {      // 设置表单提交出错                 
                    	alert(XMLHttpRequest);  //登录错误提示信息
                    },
                    success:function(data1) {
                    	var data = $.parseJSON(data1);
                   	 if(data.result.flag==FLAG_FAILURE){
                   		alert(data.result.msg);  
                   	 }else{
                   		 $("#showMsg").html("");  
                   		 top.location.href = ctx+"/main.jsp";
                   	 }
                        //document.location = "index.action";
                    } 
              }); 
           	 }
                //document.location = "index.action";
            } 
      });       

	}
 
}
</script>
</head>

<body topmargin="0" leftmargin="0">
<div class="login">
  <div class="main">
    <img src="${ctx }/images/top1.jpg" />
    <div class="xinxi">
      <div class="xinxi_l">
        <div class="xinxi_ll">
          <p>用户：</p>
          <p>密码：</p>
          <p>验证码：</p>
        </div>
        <div class="xinxi_lr">
        <form id="loginForm" method="post">
          <p><input size="" id="username" name="queryVO.username" value="" /></p>
          <p><input size="" type="password" id="password" name="queryVO.password" value="" /></p>
          <p><input type="text" id="code" maxlength="4" name="queryVO.validateCode" style="width:80px;"></input> <img  style="vertical-align:middle;cursor:pointer;" alt="点击图片更新验证码"  class="text ui-widget-content ui-corner-all" onclick="this.src='${ctx }/image.jsp?d='+new Date();" id="code" height="22" width="60" src="${ctx }/image.jsp?d=' + new Date() + '" border="0"/></p>
          </form>
        </div>
      </div>
      <div class="xinxi_r"><br /><a href="#" onclick="login()"><img src="${ctx }/images/btn_1.jpg" /></a><br /><br /><a href="#" onclick="cleardata()"><img src="${ctx }/images/btn_2.jpg" /></a></div>
    </div>
    <div class="clear"></div>
    <img src="${ctx }/images/top3.jpg" />
  </div>
</div>
</body>
</html>
