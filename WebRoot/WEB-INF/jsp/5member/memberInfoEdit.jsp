<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>


<form action="${base}/editMemberInfo" method="post" class='required-validate pageForm' onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" name="navTabId" value="A_USERMANAGE" />
<!-- 修改信息需要带回的隐藏字段 -->
<input type="hidden" name="member_code" value="${member_code}" />

<input type="hidden" name="member.mid" value="${member.mid }" />
<input type="hidden" name="member.usercode" value="${member.usercode }" />
<input type="hidden"  name="member.usertype" value="${member.usertype}" />
<input type="hidden" name="member.loginname" value="${member.loginname }" />
<input type="hidden" name="member.loginPwd" value="${member.loginPwd }" />
<input type="hidden" name="member.memberStatus" value="${member.memberStatus }" />

<input type="hidden"  name="member.aid" value="${member.aid}" />
<input type="hidden"  name="member.address_code" value="${member.address_code}" />
<!-- 下面三个暂时不做 -->
<input type="hidden"  name="member.gps_long" value="${member.gps_long}" />
<input type="hidden"  name="member.gps_lat" value="${member.gps_lat}" />
<input type="hidden"  name="member.available_shops" value="${member.available_shops}" />


<div class="pageContent">
	
	<c:choose>
			<c:when test="${member ne null}">
				<div class="pageFormContent" layoutH="56">
			<div class="unit">
				<label>真实姓名：</label>
				<input  class="required"  type="text" name="member.realName"  value="${member.realname }" size="30"  alt="请输入真实姓名"/>
			</div>
			
			<div class="unit">
				<label>手机号码：</label>
				<input class="required"  type="text" name="member.telphone" size="30" value="${member.telphone }" alt="手机号码就是登录名"/>
			</div>
			
			<div class="unit">
				<label>用户注册类型：</label>
				<!-- 1.实体卡 2.网站注册 3.微信注册 4.后台注册 -->
				<c:choose>
					<c:when test="${member.usertype == 1}">
						<input type="text" readonly="readonly"  value="实体卡注册"  size="30" class="textInput" />
					</c:when>
					<c:when test="${member.usertype == 2}">
						<input type="text" readonly="readonly"  value="网站注册"  size="30" class="textInput" />
					</c:when>
					<c:when test="${member.usertype == 3}">
						<input type="text" readonly="readonly"  value="微信注册"  size="30" class="textInput" />
					</c:when>
					<c:when test="${member.usertype == 4}">
						<input type="text" readonly="readonly"  value="后台注册"  size="30" class="textInput" />
					</c:when>
					<c:otherwise>
						<input type="text" readonly="readonly"  value="未知注册"  size="30" class="textInput" />
					</c:otherwise>
				</c:choose>	
			</div>
			
			<div class="unit">
				<label>用户邮箱：</label>
				<input class="textInput email" type="text"  name="member.email"  value="${member.email }"  size="30"  alt="请输入正确的邮箱" />
			</div>
			<div class="unit">
				<label>用户性别：</label>
				<c:if test="${member.usersex eq null }">
					<input type="radio" name="member.usersex" value="1"  checked="checked"/>男
					<input type="radio" name="member.usersex" value="0"  />女
				</c:if>
				<c:if test="${member.usersex == 1 }">
					<input type="radio" name="member.usersex" value="1"  checked="checked"/>男
					<input type="radio" name="member.usersex" value="0"  />女
				</c:if>
				<c:if test="${member.usersex == 0 }">
					<input type="radio" name="member.usersex" value="1"  />男
					<input type="radio" name="member.usersex" value="0" checked="checked" />女
				</c:if>
			</div>
			<div class="unit">
				<label>用户生日：</label>
				<!-- <fmt:formatDate value="${sysuser.createDate }" pattern="yyyy-MM-dd" /> -->
				<input type="text" name="member.birthday" value="<fmt:formatDate value="${member.birthday }" pattern="yyyy-MM-dd" />" class="date" readonly="true"/>
				<a class="inputDateButton" href="javascript:;">选择</a>
			</div>
			
			<div class="unit">
				<label>身份证号：</label>
				<input  name="member.card_number" value="${member.card_number }" type="text" size="30" alt="请输入身份证号码" class="textInput"/>
			</div>
			<div class="unit">
				<label>所在城市：</label>
				<input class="required" name="member.city"  value="${member.city }" type="text" size="30" alt="请输入所在城市" />
			</div>
			<div class="unit">
				<label>所在区/县：</label>
				<input class="required"  name="member.area"  value="${member.area }" type="text" size="30" alt="请输入所在区县" />
			</div>
			<div class="unit">
				<label>详细地址：</label>
				<input class="required"  name="member.default_address" value="${member.default_address }" type="text" size="30" alt="如 xx市xx区xx路2号楼203室" />
			</div>
			<div class="unit">
				<label>是否可配送：</label>
				<c:if test="${member.isavailable eq null }">
					<input type="radio" name="member.isavailable" value="1"  checked="checked"/>可配送
					<input type="radio" name="member.isavailable" value="0"  />不可配送
				</c:if>
				<c:if test="${member.isavailable == 1 }">
					<input type="radio" name="member.isavailable" value="1"  checked="checked"/>可配送
					<input type="radio" name="member.isavailable" value="0"  />不可配送
				</c:if>
				<c:if test="${member.isavailable == 0 }">
					<input type="radio" name="member.isavailable" value="1"  />可配送
					<input type="radio" name="member.isavailable" value="0" checked="checked" />不可配送
				</c:if>
			</div>
			
			<div class="unit">
				<label>年龄段：</label>
				<select class="required combox" name="member.age_area">
					<option value="0">请选择</option>
					<c:forEach var="age" items="${ageList}">
						<c:choose>
							<c:when test="${member.age_area == age }">
								<option value="${age }" selected>${age }</option>
							</c:when>
							<c:otherwise>
								<option value="${age }">${age }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="unit">
				<label>职业：</label>
				<select class="required combox" name="member.work_type">
					<option value="0">请选择</option>
					<c:forEach var="type" items="${worktypeList}">
						<c:choose>
							<c:when test="${member.work_type == type }">
								<option value="${type }" selected>${type }</option>
							</c:when>
							<c:otherwise>
								<option value="${type }">${type }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			<div class="unit">
				<label>家庭收入：</label>
				<select class="required combox" name="member.family_money">
					<option value="0">请选择</option>
					<c:forEach var="fmoney" items="${fmoney_list}">
						<c:choose>
							<c:when test="${member.family_money == fmoney }">
								<option value="${fmoney }" selected>${fmoney }</option>
							</c:when>
							<c:otherwise>
								<option value="${fmoney }">${fmoney }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
			</div>
			
			
			<div class="unit">
				<label>实体卡号：</label>
				<input  name="member.entityCardNumber" value="${member.entityCardNumber }" type="text" size="30" alt="请输入实体卡号码" class="textInput"/>
			</div>
			<div class="unit">
				<label>实体状态：</label>
				<select class="required combox" name="member.entityCardStatus">
					<option value="0">请选择</option>
						<c:choose>
							<c:when test="${member.entityCardStatus == 1 }">
								<option value="1" selected>已开卡</option>
								<option value="2">已使用</option>
								<option value="3">已作废</option>
							</c:when>
							<c:when test="${member.entityCardStatus == 2 }">
								<option value="1" >已开卡</option>
								<option value="2" selected>已使用</option>
								<option value="3">已作废</option>
							</c:when>
							<c:when test="${member.entityCardStatus == 3 }">
								<option value="1" >已开卡</option>
								<option value="2">已使用</option>
								<option value="3" selected>已作废</option>
							</c:when>
							<c:otherwise>
								<option value="1" selected>已开卡</option>
								<option value="2">已使用</option>
								<option value="3">已作废</option>
							</c:otherwise>
						</c:choose>
				</select>
			</div>
			<div class="unit">
				<label>会员卡余额：</label>
				<input name="member.memberCard_balance" value="${member.memberCard_balance }" type="text" size="30" alt="请输入会员卡余额" class="textInput"/>
			</div>
			<div class="unit">
				<label>会员卡积分：</label>
				<input name="member.memberCard_score" value="${member.memberCard_score }" type="text" size="30" alt="请输入会员卡积分" class="textInput"/>
			</div>
			
			<div class="unit">
				<label>注册日期：</label>
				<input type="text" name="member.createdate" value="<fmt:formatDate value="${member.createdate }" pattern="yyyy-MM-dd" />" class="textInput" readonly="true"/>
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
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="adduserBtn">修改</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	
</div>

</form>

<script type="text/javascript">
	
</script>



