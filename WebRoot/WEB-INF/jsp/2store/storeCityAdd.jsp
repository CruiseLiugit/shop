<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>

<!-- 
<script type="text/javascript" src="${base}/selectjs/jquery.provincesCity.js" ></script>
<script type="text/javascript" src="${base}/selectjs/provincesdata.js" ></script>
 -->
<script type="text/javascript">
  //调用插件
  /*
  $(function(){
    $("#test").ProvinceCity();
  });
  */
  </script>
  
<form action="${base}/createSysMenus" method="post" class='required-validate pageForm' onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" name="navTabId" value="A_MENUMANGE" />
<input type="hidden" name="buttonchks" value="" id="buttonchks" />


<div class="pageContent">
	    
		<div class="pageFormContent" layoutH="56">
			<!-- 华北地区：北京市、天津市、河北省、山西省、内蒙古自治区；
　　东北地区：辽宁省、吉林省、黑龙江省；
　　华东地区：上海市、江苏省、浙江省、安徽省、福建省、江西省、山东省；
　　中南地区：河南省、湖北省、湖南省、广东省、海南省、广西壮族自治区；
　　西南地区：重庆市、四川省、贵州省、云南省、西藏自治区；
　　西北地区：陕西省、甘肃省、青海省、宁夏回族自治区、新疆维吾尔自治区；
　　香港特别行政区、澳门特别行政区、台湾省。 
			<div class="unit" >
				<label>大区：</label>
				<select class="required combox"  id="area_id">
					<option value="-1">请选择</option>
					
						<option value="1">华东地区</option>
						<option value="2">中南地区</option>
						<option value="3">西南地区</option>
						<option value="4">西北地区</option>
						<option value="5">华北地区</option>
						<option value="6">东北地区</option>
						<option value="7">香港澳门台湾省</option>
				</select>
			</div>
		    -->
		
			<div class="unit" >
				<label>省份：</label>
				<div id="test"></div>
			</div>
			
			<div class="unit">
				<label>菜单等级：</label>
				<input class="required"  type="text" name="menu.levelId" size="30"  alt="请输入数字等级"/>
			</div>
			
			
			
			<div class="unit">
				<label>菜单英文名称：</label>
				<input class="required" type="text"  name="menu.engName"  value="" size="30"  alt="请输入英文名称" />
			</div>
			<div class="unit">
				<label>菜单URL地址：</label>
				<input class="required" type="text"  name="menu.menuUrl"  value="" size="30" alt="请输入最后一级URL"/>
			</div>
			
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="adduserBtn">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	
</div>

</form>


<script type="text/javascript">
/*
$(documen).ready(function(){
	$("#area_id").change(function (){
		var opval =  $(".selector").val();
		var optext = $(".selector").find("option:selected").text();
		alert("vale "+opval+"  , optext ="+optext);
	});
});
*/

</script>
