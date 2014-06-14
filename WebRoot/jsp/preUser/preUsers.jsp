<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- 避免制作出的页面在IE8下面出现错误，建议直接将IE8使用IE7进行渲染 -->
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<!-- 使页面使用手机屏幕尺寸 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	    
	<title>后台框架</title>
	<link href="${contextPath }/css/default126.css" rel="stylesheet" type="text/css" /> 
	<link rel="stylesheet" type="text/css" href="${contextPath }/js/themes/default/easyui.css" /> 
	<link rel="stylesheet" type="text/css" href="${contextPath }/js/themes/icon.css" />
	<script type="text/javascript" src="${contextPath }/js/jquery.js"></script>
	<script type="text/javascript" src="${contextPath }/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${contextPath }/js/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
$(function () {

	$.ajax({ 
		url:'${contextPath }/menus/getUserToolbar.do?menusCode=c7693b2e-dea6-4aab-801f-6bc24903d801', // 获取当前用户当前菜单拥有的按钮 
		type:'post', 
		success: function(data){ 
			toolbar=eval("("+data+")"); 
			$('#grid').datagrid("addToolbarItem",toolbar); // 为toolbar 赋值 
		} 
	});
	
	$('#edit-window').dialog({
 		closed: true
 	 });

    $('#btn-save,#btn-cancel').linkbutton();

    $('#btn-search,#btn-search-cancel').linkbutton();
    searchWin = $('#search-window').window({
        closed: true,
        modal: true
    });
    searchForm = searchWin.find('form');

    grid = $('#grid').datagrid({
        title: '系统用户列表',
        iconCls: 'icon-save',
        url: '${contextPath }/preUser/getAllUserList.do',
        sortName: 'ID',
        sortOrder: 'desc',
        idField: 'id',
        pageSize:10,
        frozenColumns: [[
                    { field: 'ck', checkbox: true }
                ]],
        columns: [[
                    { field: 'username', title: '登录名', width: 150 },
                    { field: 'createDate', title: '创建时间', width: 150, sortable: true }
                ]],
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        toolbar: []
    });
   
    $('body').layout();
});

var grid;
var searchWin;
var searchForm;
var toolbar;
function getSelectedArr() {
    var ids = [];
    var rows = grid.datagrid('getSelections');
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    return ids;
}


function arr2str(arr) {
    return arr.join(',');
}

//新增系统用户方法
function add() {
    var editSysUser= $('#edit-window').dialog({
	        href:"${contextPath }/jsp/preUser/editPreUser.jsp",
	        title: '新增用户',
	        width: 350,
	        modal: true,
	        maximizable:true,
	        closed: true,
	        height: 300,
	        resizable:true
   		});
    	editSysUser.dialog('open');
}

//修改系统用户方法
function edit() {
    var rows = grid.datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行修改!', 'info');
        return;
    }else{
   		var editSysUser= $('#edit-window').dialog({
	        href:"${contextPath }/preUser/getPreUserByCode.do?preUserCode="+ rows[0].id,
	        title: '修改用户',
	        width: 500,
	        modal: true,
	        maximizable:true,
	        closed: true,
	        height: 350,
	        resizable:true
   		});
        editSysUser.window('open');
    }
}

//删除系统用户
function del() {
    var arr = getSelectedArr();
    if (arr.length>0) {
        $.messager.confirm('提示信息', '您确认要删除吗?', function (data) {
            if (data) {
                $.ajax({
                    url: '${contextPath }/preUser/deletePreUsers.do?ids=' + arr2str(arr),
                    error: function () {
                        $.messager.alert('错误', '删除失败!', 'error');
                    },
                    success: function (message) {
                        if (message.match("成功")) {
                            grid.datagrid('reload');
                            grid.datagrid('clearSelections');
                        } else {
                            $.messager.alert('错误', message, 'error');
                        }
                    }
                });
            }
        });
    } else {
       // $.messager.show({ title: '警告',  msg: '请先选择要删除的记录。' });
        $.messager.alert('警告', '请先选择要删除的记录。', 'warning');
    }
}
//详情查看
function view(){
	var rows = grid.datagrid('getSelections');
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行查看!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行查看!', 'info');
        return;
    }else{
   		var viewPave= $('#edit-window').dialog({
	        href:"${contextPath }/preUser/getPreUserByUserCode.do?userCode="+ rows[0].id,
	        title: '查看铺设点详情',
	        width: 750,
	        modal: true,
	        closed: true,
	        height: 420,
	        resizable:true
   		});
        viewPave.window('open');
    }
}
function showAll() {
    grid.datagrid({ url: '${contextPath }/preUser/getAllUserList.do' });
    $('#grid').datagrid("addToolbarItem",toolbar); // 为toolbar 赋值
}
function OpensearchWin() {
    searchWin.window('open');
    searchForm.form('clear');
}

function SearchOK() {
    var s_title = $("#s_title").val();
    searchWin.window('close');
    grid.datagrid({ url: '${contextPath }/preUser/getPreUserByLoginName.do',
    				pageNumber:1, 
    				queryParams: {loginName: s_title} 
    			});
    $('#grid').datagrid("addToolbarItem",toolbar); // 为toolbar 赋值
}
function closeSearchWindow() {
    searchWin.window('close');
}
 
	</script>
    <style type="text/css">
       
    </style>
</head>
<body class="easyui-layout" style="overflow-y: hidden" scroll="no">
    
    <div region="center" style="width: 500px; height: 300px; padding: 1px;overflow-y: hidden">
        <div id="grid" fit="true">
        </div>
    </div>
    <div id="edit-window" title="编辑窗口" style="width: 900px; height: 800px;">
       
    </div>
    <div id="search-window" title="查询窗口" style="width: 350px; height: 200px;">
        <div style="padding: 20px 20px 40px 80px;">
        <form method="post">
            <table>
                <tr>
                    <td>登录名：</td>
                    <td>
                        <input name="s_title" id="s_title" style="width: 150px;" />
                    </td>
                </tr>
            </table>
            </form>
        </div>
        <div style="text-align: center; padding: 5px;">
        	<a href="javascript:void(0)" onclick="SearchOK()" id="btn-search" icon="icon-ok">确定</a>
        	<a href="javascript:void(0)" onclick="closeSearchWindow()" id="btn-search-cancel" icon="icon-cancel">取消</a>
        </div>
    </div>
</body>
</html>
