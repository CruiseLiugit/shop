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
		url:'${contextPath }/menus/getUserToolbar.do?menusCode=ef47dca1-b953-4455-8a0a-88027bd2b617', // 获取当前用户当前菜单拥有的按钮 
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
    var url='${contextPath }/categoryProperty/queryCategoryProperty.do';;
    grid = $("#grid").treegrid({
    	title: '类目属性列表',	
		iconCls: 'icon-edit',
		toolbar: toolbar,
		loadMsg:'正在加载，请稍等...', 
		url: url,
		rownumbers: true,
		idField: 'id',
		pageSize: 5,  
		pageList:[2,5,10],
		pagination:true,
		treeField: 'showName',
		onBeforeLoad: function(row,param){  
            if (!row) { // load top level rows  
                param.id = '-1';   // set id=0, indicate to load new page rows  
            }  
        },
		columns:[[
		          {field:'showName',title:'展示名字',width:350},
		          {field:'name',title:'类目名字',width:240},
		          {field:'createDate',title:'创建时间',width:100},
		          {field:'order',title:'排序权值',width:80},
		          {field:'property',title:'属性',width:400}
		        ]]
	});
   
    $('body').layout();
});

var searchWin;
var searchForm;
var toolbar;
function getSelectedArr() {
    var ids = [];
    var rows = grid.treegrid('getSelections');
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
	 var rows = grid.treegrid('getSelections');
	 var num = rows.length;
	 var categoryCode=-1;
	 if(num==1){
		 categoryCode=rows[0].id;
	 }
    var editSysUser= $('#edit-window').dialog({
    		href:"${contextPath }/category/getCategoryByCode.do?categoryCode="+ categoryCode+"&option=add",
	        title: '新增类目',
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
    var rows = grid.treegrid('getSelections');
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行修改!', 'info');
        return;
    }else{
    	$.get('${contextPath}/category/allowDelte.do',{categoryCode:rows[0].id},function(data){
   		 if(data=='0'){
   			var editCategory= $('#edit-window').dialog({
   		        href:"${contextPath }/categoryProperty/listPropertys.do?categoryCode="+ rows[0].id+"&option=edit",
   		        title: '修改类目',
   		        width: 500,
   		        modal: true,
   		        maximizable:true,
   		        closed: true,
   		        height: 350,
   		        resizable:true
   	   		});
   	   		editCategory.window('open');
			}else{
				$.messager.alert('警告', '只能操作叶子节点!', 'warning');
			} 
   	 });
    }
}

//删除系统用户
function del() {
	var rows = grid.treegrid('getSelections');
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录进行修改!', 'info');
        return;
    }else{
    	 $.messager.confirm('提示信息', '您确认要删除吗?', function (msg) {
             if (msg) {
            	 $.get('${contextPath}/category/allowDelte.do',{categoryCode:rows[0].id},function(data){
            		 if(data=='0'){
                		 $.getJSON('${contextPath}/categoryProperty/deletePropertys.do',{categoryCode:rows[0].id},function(data){
    							if(data.flag!=0){
    								$.messager.alert('警告', '属性已经被使用不能删除!', 'warning');
    							}else{
    								$("#grid").treegrid('reload',data.categoryCode);
    								$.messager.alert('提示', '删除成功', 'info');
    							}
    						});  
         			}else{
         				$.messager.alert('警告', '只能删除叶子节点!', 'warning');
         			} 
            	 });
             }
         });
    }
}
//详情查看
function view(){
	var rows = grid.treegrid('getSelections');
    var num = rows.length;
    if (num == 0) {
        $.messager.alert('提示', '请选择一条记录进行操作!', 'info');
        return;
    }
    else if (num > 1) {
        $.messager.alert('提示', '您选择了多条记录,只能选择一条记录查看详情!', 'info');
        return;
    }else{
    	$.get('${contextPath}/category/allowDelte.do',{categoryCode:rows[0].id},function(data){
      		 if(data=='0'){
      			var editCategory= $('#edit-window').dialog({
      		        href:"${contextPath }/categoryProperty/listPropertys.do?categoryCode="+ rows[0].id+"&option=select",
      		        title: '修改类目',
      		        width: 500,
      		        modal: true,
      		        maximizable:true,
      		        closed: true,
      		        height: 350,
      		        resizable:true
      	   		});
      	   		editCategory.window('open');
   			}else{
   				$.messager.alert('警告', '只能操作叶子节点!', 'warning');
   			} 
      	 });
    }
}
function showAll() {
	grid.treegrid("reload");
}
function OpensearchWin() {
	$.messager.alert('提示', "暂不支持", 'info');
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
    	 <input type="hidden" id="select" value='<%=request.getParameter("select")%>'>
        <div id="grid"  style="width:95%px;height:550px;border:1px;"></div>
    </div>
    <div id="edit-window" title="编辑窗口" style="width: 900px; height: 800px;">
       
    </div>
</body>
</html>
