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
	var toolbar;
	$(function () {
	// 获取当前用户当前菜单拥有的按钮
	$.ajax({
	   url:'${contextPath }/menus/getUserToolbar.do?menusCode=bb3dcf51-2570-4f4e-8f5e-8330cbb495a3', 
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
    win = $('#edit-window').window({
        closed: true,
        modal: true,
        shadow: false
    });
    
    form = win.find('form');

    $('#btn-search,#btn-search-cancel').linkbutton();
    searchWin = $('#search-window').window({
        closed: true,
        modal: true
    });
    searchForm = searchWin.find('form');
    // 表查询 
    grid = $('#grid').datagrid({
        title: '属性列表',
        iconCls: 'icon-save',
        url: '${contextPath}/property/queryAllPropertysList.do',
        sortName: 'ID',
        sortOrder: 'desc',
        idField: 'roleCode',
        pageSize:10,
        frozenColumns: [[
                    { field: 'ck', checkbox: true }
                    //,{ title: 'ID', field: 'ID', width: 80, sortable: true }
                ]],
        columns: [[        			
                    { field: 'propertyName', title: '属性名称', width: 150 },
                    { field: 'showName', title: '显示名称', width: 150 },
                    { field: 'propertyType', title: '类型', width: 150 },
                    { field: 'chooseStatus', title: '选择类型', width: 150 },
                    { field: 'propertyOrder', title: '排序权值', width: 150 , sortable: true}
                ]],
        pagination: true,
        rownumbers: true,
        singleSelect: false,
        toolbar: []
    });
   
    $('body').layout();
});

var tree;
var grid;
var menus_button;
var win;
var form;
var searchWin;
var searchForm;


// 获取选中的行的id的数组集合
function getSelectedArr() {
    var ids = [];
    var rows = grid.datagrid('getSelections'); 
    for (var i = 0; i < rows.length; i++) {
        ids.push(rows[i].id);
    }
    return ids;
}


function getSelectedID() {
    var ids = getSelectedArr();
    return ids.join(',');
}
function arr2str(arr) { 
    return arr.join(',');
}
//详情
function view(){
	var rows = grid.datagrid('getSelections'); // 获取选中的行
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行修改!', 'info');
        return;
    }else{
   		var roleEdit= $('#edit-window').dialog({  // 打开一个编辑对话框
	        href:"${contextPath }/property/getPropertyById.do?propertyCode="+ rows[0].id, // 对话框引用的地址
	        title: '属性详情',
	        width: 600,
	        modal: true,
	        maximizable:true, // 是否可以最大化
	        closed: true,
	        height: 500,
	        resizable:true  // 大小可以调整
   		});
        roleEdit.window('open');       
    }
}
// 增加角色
function add() {
    win.window('open');
    var propertyInput= $('#edit-window').dialog({
	        href:"${contextPath }/jsp/property/property-input.jsp",
	        title: '新增属性',
	        width: 600,
	        modal: true,
	        maximizable:true,
	        closed: true,
	        height: 500,
	        resizable:true
   		});
    	propertyInput.dialog('open');    
        form.form('clear');
}
//修改菜单
function edit() {
    var rows = grid.datagrid('getSelections'); // 获取选中的行
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行修改!', 'info');
        return;
    }else{
   		var propertyInput= $('#edit-window').dialog({  // 打开一个编辑对话框
	        href:"${contextPath }/property/getPropertyByCode.do?propertyCode="+ rows[0].id, // 对话框引用的地址
	        title: '修改属性',
	        width: 600,
	        modal: true,
	        maximizable:true, // 是否可以最大化
	        closed: true,
	        height: 500,
	        resizable:true  // 大小可以调整
   		});
   		propertyInput.window('open');       
    }
}
// 删除角色
function del() {
    var arr = getSelectedArr();
    if (arr.length>0) {
        $.messager.confirm('提示信息', '您确认要删除吗?', function (data) {
            if (data) {
                $.ajax({
                    url: '${contextPath }/property/deletePropertys.do?ids=' + arr2str(arr),
                    type: 'GET',
                    timeout: 1000,
                    success: function (msg) {   
                        if (msg==1) {
                        	 $.messager.alert('提示', '该属性已经被使用不能删除!', 'info');
                        }else{
                            $.messager.alert('提示', '该属性已经被使用不能删除!', 'warnings');
                        }
                        grid.datagrid('reload');
                        grid.datagrid('clearSelections');
                    }
                });
            }
        });
    } else {
       // $.messager.show({ title: '警告',  msg: '请先选择要删除的记录。' });
        $.messager.alert('警告', '请先选择要删除的记录。', 'warning');
    }
     

}
// 查询所有角色列表
function showAll() {
    grid.datagrid({ url: '${contextPath}/property/queryAllPropertysList.do?type=all' });
    $('#grid').datagrid("addToolbarItem",toolbar);
}
function OpensearchWin() {
    searchWin.window('open');
    searchForm.form('clear');
}

function closeWindow() {
    win.window('close');
}
// 根据角色名称查询

function SearchOK() {
    var s_title = $("#s_title").val();
    searchWin.window('close');
    grid.datagrid({ url: '${contextPath }/property/queryAllPropertysList.do',pageNumber:1, queryParams: {propertyName: s_title,type:'search'} });
    $('#grid').datagrid("addToolbarItem",toolbar);
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
                    <td>名称：</td>
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

