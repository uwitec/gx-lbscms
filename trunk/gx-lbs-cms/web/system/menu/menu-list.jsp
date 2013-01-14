<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Complex Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/jquery-easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="${ctx}/css/demo.css">
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx}/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="${ctx }/system/menu/menu-list.js"></script>
	<script>
	
	</script>

</head>
<body class="easyui-layout">
	<div id="text">111</div>
	<div data-options="region:'center',border:false"  >
	<table id="tt" title="菜单列表" class="easyui-treegrid" >
	</table>
	</div>
<div id="menu-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">	
<div class="easyui-panel"  fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr>  
                    <td>菜单名称:</td>  
                    <td><input class="easyui-validatebox"  type="text" name="sysMenu.menuName" ></input></td>  
                </tr>  
                <tr>  
                    <td>URL:</td>  
                    <td><input class="easyui-validatebox" type="text"  name="sysMenu.url" ></input></td>  
                </tr> 
                <tr>  
                    <td>菜单级别:</td>  
                    <td><select id="gread"  class="easyui-combobox" name="sysMenu.gread" panelHeight="100" data-options="editable:false"  >
                    		<option value="1">1</option>
                    		<option value="2">2</option>
                    		<option value="3">3</option>
                    	</select>  
                    </td>  
                </tr>                  
                <tr>  
                    <td>一级菜单:</td>  
                    <td><select id="parent1" class="easyui-combobox"  style="width: 150" disabled="disabled"></select>  </td>  
                </tr>                
                <tr>  
                    <td>二级菜单:</td>  
                    <td>
                    	<select id="parent2" class="easyui-combobox" style="width: 150"  disabled="disabled"></select>
                    	<input type="hidden" id="parentId" name="sysMenu.parentId" value="1"/>  
                    </td>  
                </tr>                       
 				
            </table>  
        </form>  
        </div>  
</div>
        </div>	
</body>
</html>