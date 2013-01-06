<%@taglib prefix ="s" uri ="/struts-tags" %> 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="ce" uri="/WEB-INF/ce-tag.tld" %>
<%@page import="bjwxsytx.common.AuthenticationUtil" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+ path;
	request.setAttribute("path",path);
	request.setAttribute("ctx",path);
	request.setAttribute("basePath",basePath);
	String userId = String.valueOf(session.getAttribute(AuthenticationUtil.ID_SESSION_KEY));
	request.setAttribute("userId",userId);
%>

<s:set name="ctx" value="#request.path"/>
<s:set name="appPath" value="#request.basePath"/>
<s:set name="filePath" value="#request.filePath"/>
<s:set name="displayPath" value="#request.displayPath"/>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8"/>
<script>
var FLAG_ERROR = 2;
var FLAG_SUCCESS = 1;
var FLAG_FAILURE = 3;
var userId = ${userId};
var ctx = '${ctx}';
</script>
