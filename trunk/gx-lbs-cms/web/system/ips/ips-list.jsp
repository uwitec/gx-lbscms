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
	<script type="text/javascript" src="${ctx }/system/ips/ips-list.js"></script>
	<script>
	
	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="IP账号管理列表" data-options="border:true" ></table>
</div>
<div id="tb" style="padding:5px;height:auto">    
    <div style="margin-bottom:5px">    
       
      <a href="#" class="easyui-linkbutton"  id="addBtn" iconCls="myicon-add" plain="true">添加账号</a>
      <a href="#" class="easyui-linkbutton"  id="editBtn" iconCls="myicon-edit" disabled="disabled" plain="true">修改账号</a> 
      <a href="#" class="easyui-linkbutton" id="delBtn" iconCls="myicon-delete" disabled="disabled" plain="true">删除账号</a>
            
    

    </div>    
 <div>    
    IP:<input id="ip"  type="text"  style="width:110px" />
 	接口账号:<input id="clientId"  type="text" style="width:110px"/>
	企业名称:<input id="userName"  type="text" style="width:110px" />
	登录账号:<input id="loginName"  type="text" style="width:110px" />
	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>        

    </div>    
   </div>    
<div id="ips-add" data-options="iconCls:'icon-save'" title="添加用户" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"   fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  
                <tr> 
                    <td>IP:</td>
                    <td><input class="easyui-validatebox"  type="text" id="ip1" name="cellIps.ip" data-options="required:true"></input></td>  
                </tr>  
                <tr> 
                    <td>接口账号:</td>
                    <td><input class="easyui-validatebox"  type="text" id="cliendId1"  name="cellIps.clientid" data-options="required:true"></input></td>  
                </tr>
                                                                 <tr>  
                 <td>密码:</td>  
                    <td><input class="easyui-validatebox" type="password" id="pwd1"   name="cellIps.password" data-options="required:true"></input></td>  
                </tr>  
                <tr>  
                 <td>密码确认:</td>  
                    <td><input class="easyui-validatebox" id="pwd2" validType="equalTo['pwd1']" type="password" name="password"  data-options="required:true"></input></td>  
                </tr>     
                <tr>  
                    <td>所属EC/SI:</td>  
                    <td>  
                        <select id="combobox" class="easyui-combobox" name="userIps.userId"></select>
                    </td>  
                </tr>
                <tr> 
                    <td>备注:</td>
                    <td><input class="easyui-validatebox"  type="text" id="memo"  name="cellIps.memo" ></input>
                    	<input type="hidden" id="cellIpsId" name="cellIps.id">
                    	<input type="hidden" id="ipsId" name="userIps.id">
                    </td>   
                </tr>                  
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>