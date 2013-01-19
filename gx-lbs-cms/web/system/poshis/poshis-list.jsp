<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/var.jsp" %>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>${title }</title>
	<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css">
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="${ctx }/system/poshis/poshis-list.js"></script>
	<script>
	
	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="定位查询" data-options="border:true" ></table>
</div>
<div id="tb" style="padding:5px;height:auto">    
  
 <div>    
    号码:<input id="mdn"  type="text"  style="width:110px" />
    结果码:<input id="respCode"  type="text"  style="width:40px" />
  结果:<select id="combobox"  class="easyui-combobox"  >
  <option value="-1">全部</option>
  	<option value="false">成功</option>
  	<option value="true">失败</option>
  </select>    
           开始时间: <input id="beginTime" validType="date" class="easyui-datebox" name="beginTime" readOnly="readOnly" style="width:90px" type="text">    
        结束时间: <input id="endTime" validType="date" class="easyui-datebox" name="endTime" readOnly style="width:90px" type="text">

	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>        

    </div>    
   </div>    

</body>
</html>