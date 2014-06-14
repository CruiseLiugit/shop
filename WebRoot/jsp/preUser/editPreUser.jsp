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
});

function validate_loginName(){
	var v_flag= $("#editSysUserForm").form('validate');
	if(v_flag){
	var loginName = $('#loginName_user').val();
	var userId = $('#user_id').val();
		$.ajax({
	          url: '${contextPath }/preUser/checkLoginName.do?username=' + loginName+'&userCode='+userId,
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
    var params =$("#editSysUserForm").serialize(); 
	$.ajax({
		url : '${contextPath }/preUser/savePreUser.do',
		type : 'post',
		data : params,
		success : function(message) {
	            if (message.match("成功")) {
	                grid.datagrid('reload');
	            	$('#editSysUserForm').form('clear');
	                $('#edit-window').window('close');
	            } else {
	                $.messager.alert('失败', message, 'error');
	            }
		}
	}); 
}


</script>

<!--修改系统用户窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="editSysUserForm">
           		 <input id="user_id" name="preUserCode" type="hidden" style="display:none" class="txt01" value="${preUser.preUserCode }" />
                <table cellpadding=3>
                    <tr>
                        <td>登录名：</td>
                        <td><input id="loginName_user" name="username" type="text" style="width: 150px;" class="easyui-validatebox" required="true" validType="length[5,12]"  value="${preUser.username }" /></td>
                    </tr>
                    <tr>
                        <td>登陆密码：</td>
                        <td><input id="logPwd" name="password" type="password" style="width: 150px;" class="easyui-validatebox" required="true"  value="${preUser.password }"/></td>
                    </tr>
                </table>
              </form>
           </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a class="easyui-linkbutton" onclick="validate_loginName()" id="btn-save" icon="icon-save" href="javascript:void(0);">确认</a> 
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
            </div>
</div>
  