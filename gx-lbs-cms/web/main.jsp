<%@ page language="java" import="java.util.*,bjwxsytx.common.AuthenticationUtil" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/jsp/common/taglibs.jsp" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Complex Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/jquery-easyui/themes/icon.css">
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${ctx }/jquery-easyui/src/jquery.parser.js"></script>
	<script>
	
	if(userId==null||userId==''){
		alert('登录超时!');
		top.location.href='index.jsp';
	}
		$(function(){

			 $.post(ctx+"/menu/menu!findMenuByUserId.action", {
			 	}, function(json) {  
			 	var array = new Array();
			 	for(var i =  0 ; i < json.result.data.length ; i ++ ){
				 	for(var key in json.result.data[i]){
				 		array.push("id",json.result.data[i][key]);
				 	    
				 	}
			 	}
			 });
			$('#tt2').datagrid({
				title:'My Title',
				iconCls:'icon-save',
				width:600,
				height:350,
				nowrap: false,
				striped: true,
				fit: true,
				url:'datagrid_data.json',
				sortName: 'code',
				sortOrder: 'desc',
				idField:'code',
				frozenColumns:[[
	                {field:'ck',checkbox:true},
	                {title:'code',field:'code',width:80,sortable:true}
				]],
				columns:[[
			        {title:'Base Information',colspan:3},
					{field:'opt',title:'Operation',width:100,align:'center', rowspan:2,
						formatter:function(value,rec){
							return '<span style="color:red">Edit Delete</span>';
						}
					}
				],[
					{field:'name',title:'Name',width:120},
					{field:'addr',title:'Address',width:120,rowspan:2,sortable:true},
					{field:'col4',title:'Col41',width:150,rowspan:2}
				]],
				pagination:true,
				rownumbers:true
			});
		});
	</script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',split:true" title="North Title" style="height:100px;padding:10px;">
		<p>The north content.</p>
	</div>

	<div id="tree" data-options="region:'west',iconCls:'icon-reload',split:true" title="Tree Menu" style="width:180px;">
		<!-- <ul class="easyui-tree" data-options="url:'tree_data.json'"></ul> -->
		<ul class="easyui-tree" ></ul>
	</div>
	
	<div data-options="region:'center'" title="Main Title" style="overflow:hidden;">
		<div class="easyui-tabs" data-options="fit:true,border:false">
			<div title="Tab1" style="padding:20px;overflow:hidden;"> 
				<div style="margin-top:20px;">
					<h3>jQuery EasyUI framework help you build your web page easily.</h3>
					<ul>
						<li>easyui is a collection of user-interface plugin based on jQuery.</li> 
						<li>using easyui you don't write many javascript code, instead you defines user-interface by writing some HTML markup.</li> 
						<li>easyui is very easy but powerful.</li> 
					</ul>
				</div>
			</div>
			<div title="Tab2" data-options="closable:true" style="padding:20px;">This is Tab2 width close button.</div>
			<div title="Tab3" data-options="iconCls:'icon-reload',closable:true" style="overflow:hidden;padding:5px;">
				<table id="tt2"></table> 
			</div>
		</div>
	</div>
</body>
</html>