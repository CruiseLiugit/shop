<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
	//关闭编辑系统用户窗口
	$('#btn-cancel').click(function(){
		$('#editSysUserForm').form('clear');
		$('#edit-window').window('close');
	});
});
</script>

<!--修改系统用户窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="editSysUserForm">
           		 <input id="user_id" name="userCode" type="hidden" style="display:none" class="txt01" value="${user.userCode }" />
                <table cellpadding=3>
                    <tr>
                        <td>登录名：</td>
                        <td><input id="loginName_user" name="loginName" type="text" style="width: 150px;" readonly="readonly"  value="${user.loginName }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>姓名：</td>
                        <td><input id="userName" name="userName" type="text" style="width: 150px;" readonly="readonly"  value="${user.userName }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>登陆密码：</td>
                        <td><input id="logPwd" name="logPwd" type="password" style="width: 150px;" readonly="readonly"  value="${user.logPwd }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>邮箱：</td>
                        <td><input id="email"  name="email" type="email" validType="email" style="width: 150px;" readonly="readonly"  value="${user.email }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>电话：</td>
                        <td><input id="userPhone"  name="userPhone" type="text" validType="mobile" style="width: 150px;" readonly="readonly"   value="${user.userPhone }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                    <td>角色：</td><td><input id="userRole" name="userRole" readonly="true" type="text" style="width: 150px;" readonly="readonly"  value="${selectedRoleNames }"></td>
                	</tr>
                </table>
              </form>
           </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
            </div>
</div>
  