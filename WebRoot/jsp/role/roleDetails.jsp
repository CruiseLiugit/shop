<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
 

$(function(){
	//关闭编辑菜单窗口
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
	// 加载权限树
	var id = $("#id").val();
	$("#role_auth").tree({
	 checkbox: true,  		  
     url: '${contextPath}/role/loadAllAuthTree.do?id='+id               	
	});
  		  					
});
  
</script>

<!--角色输入窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="roleInputForm">
                <table cellpadding=3>
                	<tr>
                        <td><input id="id" name="roleCode" type="hidden" style="width: 150px;" required="true" class="txt01" value="${role.roleCode }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>角色名称：</td>
                        <td><input id="roleName" name="roleName" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空"   value="${role.roleName }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>角色描述：</td>
                        <td><input id="description" name="description" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空"  value="${role.description }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>角色权限：</td>
	                  	<td> 
	                  	   <div style="overflow-x:hidden;overflow-y:scroll;background:#fff;height:320px;">                     
	                        <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">                                                      		                       		
	        						<ul id="role_auth"></ul>         												      
	                       	</div>	
	                       </div>	                                              				        			      					
                       </td>
                   </tr> 
            </table>  
            </form>    
         </div>    
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
            </div>
</div>
</>  