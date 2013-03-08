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
	<script type="text/javascript" src="${ctx }/system/phonesection/phone-list.js"></script>
	<script type="text/javascript" src="${ctx }/js/upload/ajaxfileupload.js"></script>
		<script type="text/javascript" src="${ctx }/system/phonesection/upload.js"></script>
	<script type="text/javascript" src="${ctx }/js/util.js"></script>
	<script>
	
	</script>

</head>
<body class="easyui-layout">

<div data-options="region:'center',border:false"  >
	<table id="test" title="IP账号管理列表" data-options="border:true" ></table>
</div>
<div id="tb" style="padding:5px;height:auto">    
    <div style="margin-bottom:5px">    
       
      <a href="#" class="easyui-linkbutton"  id="addBtn" iconCls="myicon-add" plain="true">添加号段</a>
      <a href="#" class="easyui-linkbutton"  id="editBtn" iconCls="myicon-edit" disabled="disabled" plain="true">修改号段</a> 
      <a href="#" class="easyui-linkbutton" id="delBtn" iconCls="myicon-delete" disabled="disabled" plain="true">删除号段</a>
      <a href="#" class="easyui-linkbutton" id="uploadBtn" iconCls="myicon-add"  plain="true">上传号段</a>      
    

    </div>    
 <div>    
    号段:<input id="sectionValue"  type="text"  style="width:110px" />
 	
	<a id="btnSearch" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>        

    </div>    
   </div>    
<div id="ips-add" data-options="iconCls:'icon-save'" title="添加号段" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"   fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="saveForm" method="post">  
            <table>  

                <tr> 
                    <td>号段:</td>
                    <td><input class="easyui-numberbox" name="phoneSection.sectionValue"   type="text" validType="length[7,7] " data-options="required:true"></input></td>  
                </tr>
                <tr>  
                 <td>运营商:</td>  
                    <td>
                      <select id="carrierCombobox" class="easyui-combobox" name="phoneSection.carrier" data-options="required:true">

											<option value="1" >
													联通
												</option>
												<option value="2">
													移动
												</option>
												<option value="3">
													电信
												</option>
                        </select>
                    </td>  
                </tr>     
                <tr>  
                    <td>地市:</td>  
                    <td>  
                        <select id="areanameCombobox" class="easyui-combobox" name="phoneSection.areaname" data-options="required:true">

												<option value="广西南宁"  >
													广西南宁
												</option>
												<option value="广西柳州"  >
													广西柳州
												</option>
												<option value="广西玉林"  >
													广西玉林
												</option>
												<option value="广西桂林"  >
													广西桂林
												</option>
												<option value="广西百色"  >
													广西百色
												</option>
												<option value="广西河池"  >
													广西河池
												</option>
												<option value="广西北海"  >
													广西北海
												</option>
												<option value="广西梧州"  >
													广西梧州
												</option>
												<option value="广西钦州"  >
													广西钦州
												</option>
												<option value="广西防城港"  >
													广西防城港
												</option>
												<option value="广西贺州"  >
													广西贺州
												</option>
												<option value="广西贵港"  >
													广西贵港
												</option>
												<option value="广西来宾"  >
													广西来宾
												</option>
												<option value="广西崇左"  >
													广西崇左
												</option>
                        </select>
                        <input id="sectId1" type="hidden" name="phoneSection.sectionId"/>
                    </td>  
                </tr>
                    
            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
	
	<div id="upload-white" data-options="iconCls:'icon-save'" title="上传号段" closed="true" style="padding:5px;width:400px;height:200px;">
    <div class="easyui-panel"   fit="true" data-options="iconCls:'icon-save'" style="width:400px;border: 0px">  
        <div >  
        <form id="uploadForm" enctype="multipart/form-data" method="post">  
            <table>  
                <tr> 
                    <td>号码:</td>
                    <td>
                        <img src="${ctx }/js/upload/loading.gif" id="loading" style="display: none;">
        <input type="file" id="file" name="file" />
       


                    
                    </td>  
                </tr>  
                                <tr>  
                    <td>格式:</td>  
                    <td>  
                        1300730,湖南岳阳,1<br>
                        (号段,地市,运营商：1 联通 2 移动 3 电信)
                    </td>  
                </tr>                                   


            </table>  
        </form>  
        </div>  

        </div>
        
	</div>
</body>
</html>