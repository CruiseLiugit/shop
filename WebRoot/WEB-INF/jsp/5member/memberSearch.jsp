<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<div class="pageContent">
	<form method="post" action="demo_page1.html" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>会员账户名称：</label>
				<input type="text" size="25" name="name"/>
				<span class="inputInfo">电话号码或实体卡号</span>
			</div>
			<div class="unit">
				<label>会员性别：</label>
				<label class="radioButton"><input name="name" type="radio" />男</label>
				<label class="radioButton"><input name="name" type="radio" />女</label>
			</div>
			<div class="unit">
				<label>会员类型：</label>
				<label class="radioButton"><input name="name" type="radio" />实体卡会员</label>
				<label class="radioButton"><input name="name" type="radio" />网站注册</label>
				<label class="radioButton"><input name="name" type="radio" />微信注册</label>
				<label class="radioButton"><input name="name" type="radio" />app注册</label>
			</div>
			
			<div class="unit">
				<label>实体卡号：</label>
				<input type="text" size="25" name="name"/>
				<span class="inputInfo">数字编号</span>
			</div>
			<div class="unit">
				<label>注册日期：</label>
				<input type="text" size="25" name="date1" class="date"/>
				<span class="inputInfo">大于等于，小于等于</span>
			</div>
			
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit">开始检索</button></div></div></li>
				<li><div class="button"><div class="buttonContent"><button type="reset">清空重输</button></div></div></li>
			</ul>
		</div>
	</form>
</div>
