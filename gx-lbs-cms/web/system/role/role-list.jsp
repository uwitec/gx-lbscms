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
		<script type="text/javascript" src="${ctx }/system/role/role-list.js"></script>
		<script type="text/javascript" src="${ctx }/js/util.js"></script>
	<script>

	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="角色列表" data-options="border:true" ></table>
</div>

	<div id="role-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"  fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr>  
                    <td>角色名称:</td>  
                    <td><input class="easyui-validatebox"  type="text" id="roleName"  name="sysRole.roleName" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                    <td>菜单:</td>  
                    <td>
                    		<ul id="tt2" class="easyui-tree" data-options="checkbox:true,
			onClick: function(node){
				$(this).tree('toggle', node.target);
			},
			onContextMenu: function(e,node){
				e.preventDefault();
				$(this).tree('select',node.target);
				$('#mm').menu('show',{
					left: e.pageX,
					top: e.pageY
				});
			}"
	></ul>
                    </td>  
                </tr>  
               
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>
