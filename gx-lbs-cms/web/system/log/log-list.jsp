<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/var.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title }</title>
	<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/${themes }/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css">
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx }/system/log/log-list.js"></script>
	<script type="text/javascript" src="${ctx }/js/util.js"></script>
	<script>
	
	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="日志管理" data-options="border:true" ></table>
</div>
<div id="tb" style="padding:5px;height:auto">    
  
 <div>    
    账号:<input id="loginName"  type="text"  style="width:110px" />
  操作类型:<select id="combobox"   class="easyui-combobox"  >
  <option value="-1">全部</option>
  	<option value="修改密码">修改密码</option>
  	<option value="登录系统">登录系统</option>
  	<option value="退出系统">退出系统</option>
  	<option value="用户管理">用户管理</option>
  	<option value="角色管理">角色管理</option>
  	<option value="菜单管理">菜单管理</option>
  	<option value="白名单管理">白名单管理</option>
  	<option value="IP账号管理">IP账号管理</option>  	  	  	  	
  </select>    
           开始时间: <input id="beginTime" validType="date" class="easyui-datebox" name="beginTime" readOnly="readOnly" style="width:90px" type="text">    
        结束时间: <input id="endTime" validType="date" class="easyui-datebox" name="endTime" readOnly style="width:90px" type="text">

	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>        

    </div>    
   </div>    

</body>
</html>