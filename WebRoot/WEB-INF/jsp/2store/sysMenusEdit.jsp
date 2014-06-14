<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>


<form action="${base}/eidtSysUser" method="post"
	class='required-validate pageForm'
	onsubmit="return validateCallback(this, dialogAjaxDone);">
	<input type="hidden" name="navTabId" value="A_USERMANAGE" /> 
	<input type="hidden" name="userid" value="${uid}" />


	<div class="pageContent">

		<c:choose>
			<c:when test="${sysUser ne null}">
				<div class="pageFormContent" layoutH="56">
					<div class="unit" id="realNameDiv">
						<label>真实姓名：</label> <input class="required" id="c_realName"
							type="text" name="user.userName" size="30" value="${sysUser.userName }" alt="请输入真实姓名" />
					</div>
					<div class="unit" id="loginNameDiv">
						<label>用户登录名：</label> <input class="required" id="c_loginName"
							type="text" name="user.loginName" size="30" value="${sysUser.loginName }" alt="请输入登录名" />
					</div>

					<div class="unit" id="oldpwdDiv">
						<label>用户原始密码：</label> <input type="text" readonly="readonly"
							id="c_loginPwd" name="user.logPwd" value="123456" size="30"
							class="textInput">
					</div>

					<div class="unit" id="mailDiv">
						<label>用户邮箱：</label> <input type="text" id="c_usermail"
							name="user.email"  class="required" size="30" value="${sysUser.email }"
							alt="请输入正确的邮箱">
					</div>
					<div class="unit" id="userphoneDiv">
						<label>用户手机：</label> <input id="c_userphone" name="user.userPhone"
							type="text" size="30" alt="请输入手机号码" value="${sysUser.userPhone }" class="textInput" />
					</div>
					<div class="unit">
						<label>账户类型：</label> <select name="user.userType"
							class="required combox" id="c_usertype">
							<option value="">请选择</option>
							<option value="1" selected>系统用户</option>
							<option value="2">商家用户</option>
							<option value="3">其他用户</option>
						</select>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<div class="pageFormContent" layoutH="56">
					<div class="unit" id="realNameDiv">
						<label>系统故障，请联系开发者！</label>
					</div>
					
				</div>
			</c:otherwise>
		</c:choose>

		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">修改</button>
						</div>
					</div>
				</li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close">取消</button>
						</div>
					</div></li>
			</ul>
		</div>

	</div>

</form>


