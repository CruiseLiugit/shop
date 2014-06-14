<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>



<div class="pageContent">
	<form method="post" action="demo_page1.html" class="pageForm" onsubmit="return navTabSearch(this);">
		<div class="pageFormContent" layoutH="58">
			<div class="unit">
				<label>请输入门店名称关键字：</label>
				<input type="text" size="50" minlength="3" maxlength="10"/>
			</div>
			<div class="divider">divider</div>
			<div class="unit">
				<label>经营类型：</label>
				<label class="radioButton"><input name="name" type="radio" />直营</label>
				<label class="radioButton"><input name="name" type="radio" />加盟</label>
				<label class="radioButton"><input name="name" type="radio" />联营</label>
				<label class="radioButton"><input name="name" type="radio" />代销</label>
				<label class="radioButton"><input name="name" type="radio" />托管</label>
				<label class="radioButton"><input name="name" type="radio" />承包</label>
				<label class="radioButton"><input name="name" type="radio" />合作门店</label>
			</div>
			<div class="unit">
				<label>店铺类型：</label>
				<label class="radioButton"><input name="name" type="radio" />商社型</label>
				<label class="radioButton"><input name="name" type="radio" />社区型</label>
				<label class="radioButton"><input name="name" type="radio" />菜场型</label>
				<label class="radioButton"><input name="name" type="radio" />商圈型</label>
				<label class="radioButton"><input name="name" type="radio" />超市型</label>
				<label class="radioButton"><input name="name" type="radio" />复合型</label>
				<label class="radioButton"><input name="name" type="radio" />商社型</label>
				<label class="radioButton"><input name="name" type="radio" />校区型</label>
			</div>
			<div class="unit">
				<label>客户名称：</label>
				<input type="text" size="25" name="name"/>
				<span class="inputInfo">关键字或全称</span>
			</div>
			<div class="unit">
				<label>店铺编码：</label>
				<input type="text" size="25" name="code" class="lettersonly"/>
				<span class="inputInfo">数字编号</span>
			</div>
			<div class="unit">
				<label>区域搜索：</label>
				<select class="combox" name="province" ref="w_combox_city"
					refUrl="${base }/resources/citypart/city_{value}.html">
					<option value="all">所有省市</option>
					<option value="bj">北京</option>
					<option value="sh">上海</option>
					</select><select class="combox" name="city" id="w_combox_city"
					ref="w_combox_area" refUrl="${base }/resources/citypart/area_{value}.html">
					<option value="all">所有城市</option>
				</select><select class="combox" name="area" id="w_combox_area">
					<option value="all">所有区县</option>
				</select>
				<span class="inputInfo">完整的区域条件</span>
			</div>
			
			<div class="divider">divider</div>
			<div class="unit">
				<label>所属部门：</label>
				<select>
					<option>运营</option>
					<option>直营门店</option>
				</select>
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
