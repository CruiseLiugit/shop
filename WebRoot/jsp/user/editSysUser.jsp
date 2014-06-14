<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">


$(function(){
	$('#editSysUserRole-window').dialog({
 		closed: true
 	 });
	//关闭编辑系统用户窗口
	$('#btn-cancel').click(function(){
		$('#editSysUserForm').form('clear');
		$('#edit-window').window('close');
	});

	//点击角色输入框
	$('#userRole').click(function(){
		var editSysUserRole= $('#editSysUserRole-window').dialog({
	        href:"${contextPath }/jsp/user/editSysUserRole.jsp",
	        title: '编辑系统用户角色关系',
	        width: 300,
	        modal: true,
	        maximizable:true,
	        closed: true,
	        height: 200,
	        resizable:true
   		});
    	editSysUserRole.dialog('open');
	});
	
});

function validate_loginName(){
	var v_flag= $("#editSysUserForm").form('validate');
	if(v_flag){
	var loginName = $('#loginName_user').val();
	var userId = $('#user_id').val();
		$.ajax({
	          url: '${contextPath }/sysUserManager/checkLoginName.do?loginName=' + loginName+'&userCode='+userId,
	          success: function (message) {
	             if(message=='1'){
	                 $('#loginName_user').val('');
	                 $.messager.alert('错误', '该登录名已经被使用，请重新输入', 'error');
	              }else{
	              	saveData();
	              }
	          }
       });
    }
}
function saveData() {
	var checkedRoleId = $('#checkedRoleId').val();
    var params =$("#editSysUserForm").serialize(); 
	$.ajax({
		url : '${contextPath }/sysUserManager/saveSysUser.do?relativedRoles='+checkedRoleId,
		type : 'post',
		data : params,
		success : function(message) {
	            if (message.match("成功")) {
	                grid.datagrid('reload');
	            	$('#editSysUserForm').form('clear');
	                $('#edit-window').window('close');
	            } else {
	                $.messager.alert('错误', message, 'error');
	            }
		}
	}); 
}


</script>

<!--修改系统用户窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="editSysUserForm">
           		 <input id="user_id" name="userCode" type="hidden" style="display:none" class="txt01" value="${user.userCode }" />
                <table cellpadding=3>
                    <tr>
                        <td>登录名：</td>
                        <td><input id="loginName_user" name="loginName" type="text" style="width: 150px;" class="easyui-validatebox" required="true" validType="length[5,12]"  value="${user.loginName }" /></td>
                    </tr>
                    <tr>
                        <td>姓名：</td>
                        <td><input id="userName" name="userName" type="text" style="width: 150px;" class="easyui-validatebox" required="true"  value="${user.userName }" /></td>
                    </tr>
                    <tr>
                        <td>登陆密码：</td>
                        <td><input id="logPwd" name="logPwd" type="password" style="width: 150px;" class="easyui-validatebox" required="true"  value="${user.logPwd }"/></td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td><input id="email"  name="email" type="email" validType="email" style="width: 150px;" class="easyui-validatebox" required="true"  value="${user.email }"/></td>
                    </tr>
                    <tr>
                        <td>电话：</td>
                        <td><input id="userPhone"  name="userPhone" type="text" validType="mobile" style="width: 150px;" class="easyui-validatebox" required="true"   value="${user.userPhone }"/></td>
                    </tr>
                    <tr>
                    <td>角色：</td><td><input id="userRole" name="userRole" readonly="true" type="text" style="width: 150px;" class="easyui-validatebox" required="true"  value="${selectedRoleNames }"></td>
                	</tr>
                	<tr>
                    <td></td><td><input id="checkedRoleId" name="checkedRoleId" type="hidden" style="width: 150px;" required="true" class="txt01" value="${selectedRoleIds }"/></td>
                	</tr>
                </table>
              </form>
           </div>
          	<div id="editSysUserRole-window" title="编辑窗口" style="width: 900px; height: 800px;">
       				
    		</div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a class="easyui-linkbutton" onclick="validate_loginName()" id="btn-save" icon="icon-save" href="javascript:void(0);">确认</a> 
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
            </div>
</div>
  