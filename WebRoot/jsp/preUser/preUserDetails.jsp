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
</script>

<!--修改系统用户窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="editSysUserForm">
           		 <input id="user_id" name="preUserCode" type="hidden" style="display:none" class="txt01" value="${preUser.preUserCode }" />
                <table cellpadding=3>
                    <tr>
                        <td>登录名：</td>
                        <td><input id="loginName_user" name="username" type="text" style="width: 150px;" class="easyui-validatebox" required="true" validType="length[5,12]"  value="${preUser.username }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>创建时间：</td>
                        <td><input id="createDate" name="createDate" type="text" style="width: 150px;" class="easyui-validatebox" required="true"  value='<fmt:formatDate value="${preUser.createDate}" type="date"/>' readonly="readonly"/></td>
                    </tr>
                </table>
              </form>
           </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
            </div>
</div>
  