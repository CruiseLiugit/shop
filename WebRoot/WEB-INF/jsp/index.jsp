<%@ page pageEncoding="utf-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- 避免制作出的页面在IE8下面出现错误，建议直接将IE8使用IE7进行渲染 -->
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<!-- 使页面使用手机屏幕尺寸 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
    <title>留夫鸭CRM管理系统</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="description" content="留夫鸭,订餐系统,CRM,会员管理,积分管理系统.">
	<meta name="author" content="刘立立,836131325@qq.com">
	
     <link href="${base }/css/default126.css" rel="stylesheet" type="text/css" />
     
     <!-- Yahoo ui -->
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/pure-min.css">
	<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/base-min.css">
   	<!--[if lte IE 8]>
    		<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/grids-responsive-old-ie-min.css">
  	<![endif]-->
	<!--[if gt IE 8]><!-->
    		<link rel="stylesheet" href="http://yui.yahooapis.com/pure/0.5.0/grids-responsive-min.css">
  	<!--<![endif]-->
  	<style scoped>
        .button-success,
        .button-error,
        .button-warning,
        .button-secondary {
            color: white;
            border-radius: 4px;
            text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);
        }

        .button-success {
            background: rgb(28, 184, 65); /* this is a green */
        }

        .button-error {
            background: rgb(202, 60, 60); /* this is a maroon */
        }

        .button-warning {
            background: rgb(223, 117, 20); /* this is an orange */
        }

        .button-secondary {
            background: rgb(66, 184, 221); /* this is a light blue */
        }

    </style>
  	 
    <link rel="stylesheet" type="text/css" href="${base}/js/themes/default/easyui.css" />
    <link rel="stylesheet" type="text/css" href="${base}/js/themes/icon.css" />
    <script type="text/javascript" src="${base}/js/jquery.js"></script>
    <script type="text/javascript" src="${base}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src='${base}/js/locale/easyui-lang-zh_CN.js'> </script>
	<script type="text/javascript" src='${base}/js/XiuCai.index.js'> </script>
	<link rel="shortcut icon" href="${base}/images/bawo.ico"/>
<script type="text/javascript">
var _menus;
$(function(){
	$.post('${base}/user/loadMenus.do',function(data){
		_menus=data;
		InitLeftMenu('${base}');
	    tabClose();
	    tabCloseEven();
	}, "json");
});

  
//var _menus ={"menus":[{"menuid": "A_SYSTEM","icon": "icon-sys","menuname": "系统功能","menus":[{"menuid": "A_PRIVILEGE","icon": "icon-sys","menuname": "权限管理","child":[{"menuid": "A_USERMANAGE","menuname": "用户管理","url": "${base}/jsp/user/sysUserList.jsp"},{"menuid": "A_ROLE_MANAGE","menuname": "角色管理","url": "${base}/jsp/role/role.jsp"},{"menuid": "A_MENU_MANAGE","menuname": "菜单管理","url": "${base}/jsp/user/sysUserList.jsp"}]}]}]};

$(function(){
	$.ajax({
		url : '${base}/user/topLoginName.do',
		type : 'post',
		success : function(result) {
			$("#topLoginName").html(result);
		}
	});
});

 $(function() {
    $('#w').dialog({
 	closed: true
  });

  $('#editpass').click(function() {
        var eidtPassword= $('#w').dialog({
	        href:'${base}/jsp/user/eidtPassword.jsp',
	        title: '修改密码',
	        width: 300,
	        modal: true,
	        maximizable:true,
	        closed: true,
	        height: 160,
	        resizable:true
   		});
    	eidtPassword.dialog('open');
  });

$('#loginOut').click(function() {
         $.messager.confirm('系统提示', '您确定要退出本次登录吗?', function(r) {
             if (r) {
            	 $.ajax({
     		        type: "get",
     		        url: "${base}/user/loginOut.do?t="+new Date().getTime(),
     		        success: function (result) {
     		            if(result=="success"){
     		            	var topUrl="${base}/login.jsp";
     		           		parent.top.location.href=topUrl;
     		            }
     		        }
     		    });
             }
         });
     })
 });
</script>
</head>
<body class="easyui-layout" style="overflow-y: hidden"  fit="true"   scroll="no">
<noscript>
<div style=" position:absolute; z-index:100000; height:2046px;top:0px;left:0px; width:100%; background:white; text-align:center;">
    <img src="${base}/images/noscript.gif" alt='抱歉，请开启脚本支持！' />
</div></noscript>

<div id="loading-mask" style="position:absolute;top:0px; left:0px; width:100%; height:100%; background:#D2E0F2; z-index:20000">
<div id="pageloading" style="position:absolute; top:50%; left:50%; margin:-120px 0px 0px -120px; text-align:center;  border:2px solid #8DB2E3; width:200px; height:40px;  font-size:14px;padding:10px; font-weight:bold; background:#fff; color:#15428B;"> 
    <img src="${base}/images/loading.gif" align="absmiddle" /> 正在加载中,请稍候...</div>
</div>

    <!-- 头部 --> 
    <div region="north" split="true" border="false" style="overflow: hidden; height: 55px;
        background: url(${base}/images/header_bg.jpg) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">欢迎 <span id="topLoginName"></span><a href="javascript:void(0)" id="editpass">修改密码</a> <a href="javascript:void(0)" id="loginOut">安全退出</a></span>
        <span style="padding-left:10px; font-size: 16px; "><img src="${base}/images/logo.png" width="150" height="50" align="absmiddle" />留夫鸭品牌CRM管理系统</span>
    </div>
  
    <!-- 左侧导航菜单 -->
    <div region="west" split="true"  title="导航菜单" style="width:180px;" id="west">
			<div id="nav"></div>
    </div>
    
    <!-- 中间主题部分 -->
    <div id="mainPanle" region="center" style="background: #eee; overflow-y:hidden">
        <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
			<div title="欢迎使用" style="padding:20px;overflow:hidden;" >
				<ul class="blocks-2" style="background-color:#F7FBFF;border:1px solid;border-color: #B2CEF1;">
        				<li><h1 style="font-size:14px;">留夫鸭品牌电商管理系统</h1>
						<h1 style="font-size:11px;">该管理系统，为留夫鸭品牌官网、在线订餐系统、会员积分管理系统、微信会员子系统、App移动订餐系统，提供全面的数据管理服务。</h1>
						<button class="pure-button" style="background: rgb(28, 184, 65);color: white;border-radius: 4px;text-shadow: 0 1px 1px rgba(0, 0, 0, 0.2);">Success Button</button>
        				</li>
        				<li><h2><a href="#" target="_blank">留夫鸭CRM核心管理系统使用手册(PDF)</a></h2>
						<a href="#" target="_blank">留夫鸭CRM核心管理系统演示视频</a></li>
    				</ul>
				
			</div>
		</div>
    </div>
    
    
    <!--修改密码窗口-->
    <div id="w" class="easyui-window" style="background:#fafafa;"></div>

	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="close">关闭</div>
		<div id="closeall">全部关闭</div>
		<div id="closeother">关闭其他</div>
		<div class="menu-sep"></div>
		<div id="closeright">关闭右侧全部</div>
		<div id="closeleft">关闭左侧全部</div>
	</div>

	<!-- 底部 footer  -->
    <div region="south" split="true" style="height:30px;background:#D2E0F2;">
        <div class="footer">Copyright &copy; 2014 <a href="#" target="dialog">上海释伟科技有限公司</a> 沪ICP备05019125号-10</div>
    </div>
</body>
</html>