<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>


<form action="${base}/checkCity" method="post" class='required-validate pageForm' onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" name="navTabId" value="module2_store" />

<div class="pageContent">
	    
		<div class="pageFormContent" layoutH="56">
			<!-- 华北地区：北京市、天津市、河北省、山西省、内蒙古自治区；
　　东北地区：辽宁省、吉林省、黑龙江省；
　　华东地区：上海市、江苏省、浙江省、安徽省、福建省、江西省、山东省；
　　中南地区：河南省、湖北省、湖南省、广东省、海南省、广西壮族自治区；
　　西南地区：重庆市、四川省、贵州省、云南省、西藏自治区；
　　西北地区：陕西省、甘肃省、青海省、宁夏回族自治区、新疆维吾尔自治区；
　　香港特别行政区、澳门特别行政区、台湾省。 
		    -->
		
			<div class="unit">
				<label>华东地区：</label>
				<div class="unit">
				<label><input type="radio" name="selectcity" value="上海"  />上海</label>
				<label><input type="radio" name="selectcity" value="杭州"  />杭州</label>
				<label><input type="radio" name="selectcity" value=""  />宁波</label>
				<label><input type="radio" name="selectcity" value=""  />南京</label>
				<label><input type="radio" name="selectcity" value=""  />无锡</label>
				<label><input type="radio" name="selectcity" value=""  />苏州</label>
				<label><input type="radio" name="selectcity" value=""  />常州</label>
				</div>
			</div>
			
			<div class="unit">
				<label>华南-华中地区：</label>
				<div class="unit">
				<label><input type="radio" name="selectcity" value=""  />广州</label>
				<label><input type="radio" name="selectcity" value=""  />深圳</label>
				<label><input type="radio" name="selectcity" value=""  />东莞</label>
				<label><input type="radio" name="selectcity" value=""  />武汉</label>
				<label><input type="radio" name="selectcity" value=""  />长沙</label>
				<label><input type="radio" name="selectcity" value=""  />郑州</label>
				</div>
			</div>
			
			<div class="unit">
				<label>西北-西南地区：</label>
				<div class="unit">
				<label><input type="radio" name="selectcity" value=""  />西安</label>
				<label><input type="radio" name="selectcity" value=""  />成都</label>
				<label><input type="radio" name="selectcity" value=""  />重庆</label>
				<label><input type="radio" name="selectcity" value=""  />昆明</label>
				</div>
			</div>
						
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="adduserBtn">选中</button></div></div></li>
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
