<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>


<form action="${base}/createSysMenus" method="post" class='required-validate pageForm' onsubmit="return validateCallback(this, dialogAjaxDone);">
<input type="hidden" name="navTabId" value="A_MENUMANGE" />
<input type="hidden" name="buttonchks" value="" id="buttonchks" />


<div class="pageContent">
	    
		<div class="pageFormContent" layoutH="56">
			<div class="unit" >
				<label>菜单名称：</label>
				<input  class="required"  type="text" name="menu.menuName" size="30"  alt="请输入中文名称"/>
			</div>
			
			<div class="unit">
				<label>菜单等级：</label>
				<input class="required"  type="text" name="menu.levelId" size="30"  alt="请输入数字等级"/>
			</div>
			
			<div class="unit" >
				<label>上级菜单：</label>
				<select class="required combox" name="menu.fmenuCode">
					<option value="0">请选择</option>
					<c:forEach var="sysmenus" items="${sysmenuslist}">
						<option value="${sysmenus.menuCode }">${sysmenus.menuName }</option>
 					</c:forEach>
				</select>
			</div>
			
			<div class="unit">
				<label>菜单英文名称：</label>
				<input class="required" type="text"  name="menu.engName"  value="" size="30"  alt="请输入英文名称" />
			</div>
			<div class="unit">
				<label>菜单URL地址：</label>
				<input class="required" type="text"  name="menu.menuUrl"  value="" size="30" alt="请输入最后一级URL"/>
			</div>
			
			<div class="unit">
				<label>菜单按钮</label>
				<c:choose>
					<c:when test="${buttonlist eq null }">目前没有增删改等按钮，请增加</c:when>
					<c:otherwise>
						<ul class="tree treeFolder treeCheck expand"  oncheck="kkk">
							<li><a >框架面板</a>
							<ul id="btTree">
								<c:forEach var="buttons" items="${buttonlist}">
									<li><a tname="treename" tvalue="${buttons.modelCode }">${buttons.modelName }</a></li>			
 								</c:forEach>
							</ul>
							</li>
						</ul>
					</c:otherwise>
				</c:choose>
				
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
var result = "";
function kkk(){
	//alert("实际参数个数 length :"+arguments.length);
	
	var json = arguments[0];
	//alert("arguments[0] :"+json);
	//alert("checked :"+json.checked);
	//alert("json items :"+json.items);
	
	$(json.items).each(function(i){
		//result += "{text: "+this.text+",value:"+this.value+"}";
		result += this.value+",";
	});
	$("#buttonchks").val(result);
	//alert("result :"+result);
}
</script>
