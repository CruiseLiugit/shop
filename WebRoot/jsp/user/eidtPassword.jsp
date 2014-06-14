<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">

$(function(){
	//关闭修改密码窗口
	$('#btnCancel').click(function(){
		$('#w').window('close');
	})
	
	$('#btnEp').click(function(){
    	var $newpass = $('#txtNewPass');
        var $rePass = $('#txtRePass');

        if ($newpass.val() == '') {
            msgShow('系统提示', '请输入密码！', 'warning');
            return false;
        }
        if ($rePass.val() == '') {
            msgShow('系统提示', '请在一次输入密码！', 'warning');
            return false;
        }
        if ($newpass.val() != $rePass.val()) {
            msgShow('系统提示', '两次密码不一至！请重新输入', 'warning');
            return false;
        }
        $.post('${contextPath }/user/updateLoginPwd.do?newLoginPwd='+ $newpass.val(), function(msg) {
            msgShow('系统提示', '恭喜，密码修改成功！','info');
            $newpass.val('');
            $rePass.val('');
            $('#w').window('close');
        });
    })
});
</script>

<!--修改密码窗口-->
<div class="easyui-layout" fit="true">
            <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
                <table cellpadding=3>
                    <tr>
                        <td>新密码：</td>
                        <td><input id="txtNewPass" type="password" class="txt01" /></td>
                        <td></td>
                    </tr>
                    <tr>
                        <td>确认密码：</td>
                        <td><input id="txtRePass" type="password" class="txt01" /></td>
                    </tr>
                </table>
            </div>
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btnEp" class="easyui-linkbutton" icon="icon-ok" href="javascript:void(0);">确定</a> 
                <a id="btnCancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
            </div>
</div>
  