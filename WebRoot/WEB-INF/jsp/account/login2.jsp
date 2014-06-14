<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<!-- 避免制作出的页面在IE8下面出现错误，建议直接将IE8使用IE7进行渲染 -->
	<meta http-equiv="X-UA-Compatible" content="IE=7" />
	<!-- 使页面使用手机屏幕尺寸 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
    
    <title>登录</title>
    <!-- Bootstrap 3-->
    <link href="${contextPath }/dist/css/bootstrap.min.css" rel="stylesheet" />
    
    <style>
/* http://css-tricks.com/perfect-full-page-background-image/ */
        html {
            background: url(images/136246642448.jpg) no-repeat center center fixed; 
            -webkit-background-size: cover;
            -moz-background-size: cover;
            -o-background-size: cover;
            background-size: cover;
        }

        body {
            padding-top: 20px;
            font-size: 16px;
            font-family: "Open Sans",serif;
            background: transparent;
        }

        h1 {
            font-family: "Abel", Arial, sans-serif;
            font-weight: 400;
            font-size: 40px;
        }

        /* Override B3 .panel adding a subtly transparent background */
        .panel {
            background-color: rgba(255, 255, 255, 0.9);
        }

        .margin-base-vertical {
            margin: 40px 0;
        }

    </style>
</head>


<body>
 
   <div class="container">
        <div class="row">
            <div class="col-md-6 col-md-offset-3 panel panel-default">

                <h1 class="margin-base-vertical">留夫鸭后台管理系统</h1>

                <p>
                    留夫鸭品牌，核心管理后台。
                </p>
                
                <form id="ff" class="margin-base-vertical">
                    <p class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-user"></span></span>
                        <input type="text" class="form-control input-lg" name="username" placeholder="账户名" />
                    </p>
                    <p class="input-group">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-lock"></span></span>
                        <input type="text" class="form-control input-lg" name="password" placeholder="密码" />
                    </p>
                    <p class="help-block text-center"><small>请输入管理员分配的账户.</small></p>
                    <p class="text-center">
                        <button type="submit" class="btn btn-success btn-lg">登 录</button>
                    </p>
                    </span>
                </form>

                <div class="margin-base-vertical">
                    <small class="text-muted">
                        <a href="#">开发公司</a>. 上海释伟科技有限公司</small>
                </div>

            </div><!-- //main content -->
        </div><!-- //row -->
    </div> <!-- //container -->


<script type="text/javascript" src="${contextPath }/js/jquery.js"></script>    
<script type="text/javascript">
$(function () {
	$("#logbtn").submit(function(){
		$.ajax({
			url : '${contextPath }/user/login.do',
			type : 'post',
			data : {loginName:'admin',logPwd:'admin'},
			success : function(result) {
				if(result==1){		
					$('#signName').html("登录名或密码错误！");
					$("#loginName").focus();
				}else if(result==2){
					document.getElementById("auth_code_img").src = "${contextPath }/user/captchaimage.do?time=" + new Date().getTime();	
					$('#signAuthCode').html("验证码错误！");
					$("#auth_code").focus();
				}else{
					parent.top.location.href="${contextPath }/index.jsp";
				}
			}
		}); 	
	});
	
});
			
</script>
</body>
</html>