<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">

var roleTree = $('#roleTree');

$(function(){
	var userId = $('#user_id').val();
	var checkedRoleId = $('#checkedRoleId').val();
	//加载角色tree
	$('#roleTree').tree({
        checkbox: true,
        url: '${contextPath }/sysUserManager/getRoleTreeData.do?checkedRoleId='+checkedRoleId+'&userCode='+userId+'&random='+Math.random()
    });
});
	
//获取roleTree选中的id
function getCheckedId(tree){
	var nodes = tree.tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
	 	s += nodes[i].id;
	}
	return s;
}

//获取roleTree选中的角色名
function getCheckedNames(tree){
	var nodes = tree.tree('getChecked');
	var s = '';
	for(var i=0; i<nodes.length; i++){
		if (s != '') s += ',';
	 	s += nodes[i].text;
	}
	return s;
}

//点击保存按钮
function saveRoleData(){
	var checkedIds= getCheckedId(roleTree);
	var checkedNames = getCheckedNames(roleTree);
	$('#userRole').attr('value',checkedNames);
	$('#checkedRoleId').val(checkedIds);
	$('#editSysUserRole-window').window('close');
	$('#userRole').focus();
	console.log("checkedIds===="+checkedIds);
	return false;
}
</script>

<!--修改系统用户窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="editSysUserRoleForm">
                <table cellpadding=3>
                	<tr><td></td>
                		<td>
                        <div region="center" style="width: 200px; height: 200px; padding: 1px;overflow-y: hidden">
        						<ul id="roleTree">
        						</ul>
    					</div>
                    	</td>
                	</tr>
                </table>
              </form>
           </div>
           
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a class="easyui-linkbutton" onclick="saveRoleData()" id="btn-Save" icon="icon-save" href="javascript:void(0);">保存</a> 
                <!--  <a id="btn-Cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>-->
            </div>
            
</div>
  