<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<!--角色输入窗口-->
<div class="easyui-layout" fit="true">
     <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
          <form method="post" id="propertyInputForm" action="${contextPath }/propertyValue/updateCategoryPropertyValue.do" method="post">
          	<table cellpadding=3>
          		<tr>
					<td>类目</td>
					<td>
					<input  type="hidden" name="categoryPropertyCode" value="${categoryPropertyCode }">
					<input type="hidden" name="categoryCode" id="categoryCode" size="40" value="${category.categoryCode}" />
					<input type="text" name="c_showName" id="c_showName"  value="${category.showName}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>属性</td>
					<td>
					<input type="hidden" name="propertyCode" id="propertyCode" size="40" value="${property.propertyCode}" />
					<input type="text" name="p_showName" id="p_showName" value="${property.showName}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>属性值</td>
					<td>
					<table width="100%"  style="border:1px #ccc solid">
						<tr>
							<td align="center" width="10%">顺序</td><td align="center" width="30%">属性值名称</td><td width="30%">属性值显示名称</td>
							<td></td>
						</tr>
					</table>
					<table width="100%"  style="border:1px #ccc solid" id="show">
						<c:choose>
							<c:when test="${fn:length(categoryPropertyValues)>0}">
								<c:forEach items="${categoryPropertyValues}" var="it" varStatus="status">
									<tr>
										<td width="10%">
										<input type="text" size="3" readonly="readonly"/>
										</td>
										<td width="30%"><input type="text" size="15" value="${it.pvName}" maxlength="50"/></td>
										<td width="30%">
										<input type="text" size="15" value="${it.showName}" maxlength="50"/>
										<input type="hidden"  value="${it.pvCode }">
										</td>
										<td><img src="${contextPath }/images/cancel.png"  name="delete" style="margin:0px 2px;cursor:pointer;">
											<img src="${contextPath }/images/up1.png" 	   name="up"  style="margin:0px 2px;cursor:pointer;">
											<img src="${contextPath }/images/dow2.png"    name="down"   style="margin:0px 2px;cursor:pointer;">
										</td>
									</tr>
								</c:forEach>
							</c:when>
						</c:choose>
					</table>
					</td>
				</tr>
          	</table>
          </form>
      </div>
      
      <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
       <a class="easyui-linkbutton" onclick="subform();" id="btn-save" icon="icon-save" href="javascript:void(0);">保存</a> 
       <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
  	</div>
</div>
<script type="text/javascript">
function order(){//排序
	$("#show tr").each(function(i){
		$(this).find(":text:eq(0)").val(i);
	});
}
order();
function validate(){//验证有没有重复项
	var ary = new Array();
	var ary1=new  Array();
	var flag=true;
	$("#show tr").each(function(i){
		ary.push($(this).find(":text:eq(1)").val());
		ary1.push($(this).find(":text:eq(2)").val());
	}); 
	var s = ary.join(",")+",";  
		for(var i=0;i<ary.length;i++) {  
		 if(s.replace(ary[i]+",","").indexOf(ary[i]+",")>-1) {  
			 $.messager.alert('提示', "数组中有重复元素：" + ary[i], 'warnings');  
		  	return false; 
		 }  
	}
	var s = ary1.join(",")+",";  
		for(var i=0;i<ary.length;i++) {  
		 if(s.replace(ary[i]+",","").indexOf(ary[i]+",")>-1) {  
		  $.messager.alert('提示', "数组中有重复元素：" + ary[i], 'warnings');
		  return false;
		 }  
	}
		return flag;
}
function isvalid(){//验证价格区间的正确性
	var flag1=true;
	if($("#p_showName").val().indexOf("价格")>=0){
		$("#show tr").each(function(i){
			var test=$(this).find(":text:eq(2)").val();
			if(test.indexOf(" ")>=0){
				$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
				flag1=false;
			}else{
				var i=test.indexOf("-");
			if(i>0){
				var first=test.substring(0,i);
				var last=test.substring(i+1,test.length);
				if(isNaN(first)||isNaN(last)||parseInt(first)>=parseInt(last)||parseInt(first)<0||parseInt(last)<0||last==''){
					$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
					flag1=false;
				}else{
					if(parseInt(first)>0&&first.substring(0,1)=='0'){
						$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
						flag1=false;
					}
				}
			}else{
					var i=test.indexOf("以上");
					var j=test.indexOf("以下");
					if((i<=0&&j<=0)||(i>0&&j>0)){
						$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
						flag1=false;
					}else if(i>0){
						var first=test.substring(0,i);
						var last=test.substring(i+2,test.length);
						if(isNaN(first)||last!=''||parseInt(first)<=0){
							$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
							flag1=false;
						}else{
						}
					}else if(j>0){
						var first=test.substring(0,j);
						var last=test.substring(j+2,test.length);
						if(isNaN(first)||last!=''||parseInt(first)<=0){
							$.messager.alert('提示', "请输入正确的价格区间", 'warnings');
							flag1=false;
						}else{
						}
					}
				}
			}
		});
		return flag1;
	}
	return true;
	
}
$("img[name=delete]").live("click",function(){
	if(confirm("确认要删除吗?")){
		if($("#show tr").length>1){
			var $img=$(this);
			$img.parent().parent().remove();
		}else{
			$.messager.alert('提示', "必须要有一个属性值", 'warnings');
		}
		order();
	}
	return false;
});
$("img[name=up]").live("click",function(){
	if($(this).parent().parent().prev().length>0){
		$(this).parent().parent().after($(this).parent().parent().prev());
	}
	order();
});
$("img[name=down]").live("click",function(){
	if($(this).parent().parent().next().length>0){
		$(this).parent().parent().before($(this).parent().parent().next());
	}
	order();
});
var allowSubmit=true;
function subform(){
	if(allowSubmit){
		var flag=true;
		$("#show :text").each(function(){	
			if($.trim($(this).val())==''){
				flag=false;
			}
		});
		if(flag){
			if(validate()&&isvalid()){
				$("#show tr").each(function(i){
					$(this).find(":text:eq(1)").attr("name","t"+i);
					$(this).find(":text:eq(2)").attr("name","r"+i);
					$(this).find(":hidden").attr("name","h"+i);
				});
				allowSubmit=false;
				$("#propertyInputForm").ajaxSubmit({
					success : function(data) {
							$.messager.alert('提示', data.message, 'info');
							if(data.id=='-1'){
								$('#grid').treegrid('reload');	
							}else{
								$('#grid').treegrid('reload',data.id);	
							}
							$('#edit-window').window('close');
					}
			});		
			}
		}else{
			$.messager.alert('提示', "属性值和显示名称不能为空", 'warnings');
		}
	}
}
$(function(){
	//关闭编辑菜单窗口
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
});
</script>