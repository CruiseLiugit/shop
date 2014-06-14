<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
  	// 获取选中的按钮id
   function getSelectedAuths() {
    var ids = '';
      $('#role_auth').find(".tree-checkbox2").each(function(){ 
      var fid=$($(this).parent()[0]).attr("node-id"); //获取半选中状态的id 
      	if(fid!='')
 		ids +=fid+',';
		});
    var nodes = $('#role_auth').tree('getChecked');
    for (var i = 0; i < nodes.length; i++) {
       if (i<nodes.length-1){
       ids += nodes[i].id+',';
       }   else{    
       ids += nodes[i].id;
       }
    }
    return ids;
  }
 

  
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
	// 验证角色名是否存在
	$("#roleName").blur(function(){
	  var roleName = $(this).val();	 
	  if(roleName==''||roleName==null){
		  $.ajax({
			  url:'${contextPath }/role/validataRoleName.do?roleName='+roleName,
			  type:'post',
			  success: function(i){	 
			  if(i=="1"){
			   $.messager.alert('提示', '该角色名已经存在请重新输入!', 'info'); 
			   $("#roleName").attr('value',"");
			  }	  
			 }	  
		    });	  	  
	  }	 	  	  		
  });
  		  					
});
  
  //  表单保存
  function 	saveRole(){
   var selectAuthIds = getSelectedAuths();  
   if(selectAuthIds!="" && selectAuthIds!=null){
      var roleInputForm=$("#roleInputForm");    
    	 roleInputForm.form('submit', {
	        url: '${contextPath }/role/saveRole.do?selectAuthIds='+selectAuthIds,
	        success: function (msg) {
	            if (msg.match("1")) {	                              
                	grid.datagrid('reload');
	                win.window('close');
	            } else {
	               $.messager.alert('提示', "失败", 'warnings');
	            }
	        }
       });
   }else{
      $.messager.alert('提示', '请为菜单配置按钮!', 'info');  
      return ;
   
   }   
    
  }
</script>

<!--角色输入窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="roleInputForm">
                <table cellpadding=3>
                	<tr>
                        <td><input id="id" name="roleCode" type="hidden" style="width: 150px;" required="true" class="txt01" value="${role.roleCode }" /></td>
                    </tr>
                    <tr>
                        <td>角色名称：</td>
                        <td><input id="roleName" name="roleName" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空"   value="${role.roleName }" /></td>
                    </tr>
                    <tr>
                        <td>角色描述：</td>
                        <td><input id="description" name="description" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空"  value="${role.description }" /></td>
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
                <a class="easyui-linkbutton" onclick="saveRole()" id="btn-save" icon="icon-save" href="javascript:void(0);">确认</a> 
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
            </div>
</div>
</>  