<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>LBS-CMS</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/src/jquery.parser.js"></script>
	<link rel="stylesheet" href="${ctx }/css/login/css/reset.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${ctx }/css/login/css/style.css" type="text/css" media="screen" />
	<link rel="stylesheet" href="${ctx }/css/login/css/invalid.css" type="text/css" media="screen" />
	<script>
		
	
	</script>
</head>
<body id="login">
<div id="login-wrapper" class="png_bg">
  <div id="login-top">
    <h1>Simpla Admin</h1>
    <!-- Logo (221px width) -->
    <a href="http://www.865171.cn"><img id="logo" src="${ctx }/css/login/images/logo.png" alt="Simpla Admin logo" /></a> </div>
  <!-- End #logn-top -->
  <div id="login-content">
    <form action="index.html">
      <div class="notification information png_bg">
        <div> Just click "Sign In". No password needed. </div>
      </div>
      <p>
        <label>Username</label>
        <input class="text-input" type="text" />
      </p>
      <div class="clear"></div>
      <p>
        <label>Password</label>
        <input class="text-input" type="password" />
      </p>
      <div class="clear"></div>
      <p id="remember-password">
        <input type="checkbox" />
        Remember me </p>
      <div class="clear"></div>
      <p>
        <input class="button" type="submit" value="Sign In" />
      </p>
    </form>
  </div>
  <!-- End #login-content -->
</div>
<!-- End #login-wrapper -->
</body>
</html>