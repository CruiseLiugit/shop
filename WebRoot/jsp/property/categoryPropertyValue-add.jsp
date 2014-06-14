<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<!--角色输入窗口-->
<div class="easyui-layout" fit="true">
     <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
          <form method="post" id="propertyInputForm" action="${contextPath }/propertyValue/addCategoryPropertyValue.do" method="post">
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
					<input type="text" name="p_showName" id="p_showName1" value="${property.showName}" readonly="readonly"/>
					</td>
				</tr>
				<tr>
					<td>属性值</span></td>
					<td>
					<table width="100%"  style="border:1px #ccc solid" >
						<tr>
							<th align="center" width="30%">属性值名称</th>
							<th align="left" width="30%">属性值显示名称</th>
							<th align="center">操作</th>
						</tr>
					</table>
					<table width="100%" style="border:1px #ccc solid" id="oldShow">	
					<c:choose>
					<c:when test="${fn:length(categoryPropertyValues)>0}">
					<c:forEach items="${categoryPropertyValues}" var="it" varStatus="status">
					<tr>
						<td width='30%'><input type='text' size='15' value='${it.pvName}' disabled='disabled'/></td>
						<td width='30%'><input type='text' size='15' value='${it.showName}' disabled='disabled'/></td>
						<td align='center'></td>
					</tr>
					</c:forEach>
					</c:when>
					</c:choose>
					</table>
					<table width="100%" style="border:1px #ccc solid" id="myShow">	
						<tr>
							<td width="30%"><input type="text" size="15" value="" maxlength="50"/></td>
							<td width="30%"><input type="text" size="15" value="" maxlength="50"/></td>
							<td><img src="${contextPath }/images/edit_add.png" name="add" style="margin:0px 4px;cursor:pointer;">
								<img src="${contextPath }/images/cancel.png" name="delete" style="margin:0px 2px;cursor:pointer;">
								<img src="${contextPath }/images/up1.png" name="up"  style="margin:0px 1px;cursor:pointer;">
								<img src="${contextPath }/images/dow2.png"    name="down"   style="margin:0px 1px;cursor:pointer;">
							</td>
						</tr>
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
<script language="JavaScript" type="text/javascript">
	var num=1;
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
	
	$("img[name=add]").live("click",function(){
		$(this).parent().parent().after('<tr><td><input type="text" maxlength="50" size="15"/></td><td><input type="text" maxlength="50" size="15"/></td><td><img src="${contextPath }/images/edit_add.png" name="add" style="margin:0px 4px;cursor:pointer;"><img src="${contextPath }/images/cancel.png" name="delete" style="margin:0px 2px;cursor:pointer;"><img src="${contextPath }/images/up1.png" name="up"  style="margin:0px 1px;cursor:pointer;"><img src="${contextPath }/images/dow2.png"    name="down"   style="margin:0px 1px;cursor:pointer;"></td></tr>');
		return false;
	});
	$("img[name=delete]").live("click",function(){
		if($("#myShow tr").length>1){
			$(this).parent().parent().remove();
		}else{
			$.messager.alert('提示', "必须要有一个属性值", 'warnings');
		}
		return false;
	});
	$("img[name=up]").live("click",function(){
		if($(this).parent().parent().prev().length>0){
			$(this).parent().parent().after($(this).parent().parent().prev());
		}
		order();
		return false;
	});
	$("img[name=down]").live("click",function(){
		if($(this).parent().parent().next().length>0){
			$(this).parent().parent().before($(this).parent().parent().next());
		}
		order();
		return false;
	});
	function validate(){//验证有没有重复项
		var flag=true;
		var ary = new Array();
		var ary1=new  Array();
		var flag=true;
		$("#myShow tr").each(function(i){
			ary.push($(this).find(":text:eq(0)").val());
			ary1.push($(this).find(":text:eq(1)").val());
		}); 
		var s = ary.join(",")+",";  
			for(var i=0;i<ary.length;i++) {  
				var str=s.replace(ary[i]+",","")
			 if(str.indexOf(ary[i]+",")==0) {  
				 $.messager.alert('提示', "数组中有重复元素：" + ary[i], 'warnings');
			  	return false; 
			 }  
		}
		var s = ary1.join(",")+",";  
			for(var i=0;i<ary.length;i++) {  
				var str=s.replace(ary[i]+",","")
			 if(str.indexOf(ary[i]+",")==0) {  
				 $.messager.alert('提示', "数组中有重复元素：" + ary[i], 'warnings');
			  	return false;
			 }  
		}
			return flag;
	}
	function isvalid(){//验证价格区间的正确性
		var flag1=true;
		if($("#p_showName1").val().indexOf("价格")>=0){
			$("#myShow tr").each(function(i){
				var test=$(this).find(":text:eq(1)").val();
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
						}else{
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
	function order(){//排序
		$("#myShow tr").each(function(i){
			$(this).find(":text:eq(0)").val(i);
		});
	}
	
	function subform(){
		if(validate()&&isvalid()){
			$("#myShow tr").each(function(i){
				$(this).find(":text:eq(0)").attr("name","t"+(num+i));
				$(this).find(":text:eq(1)").attr("name","r"+(num+i));
			});
			$("#propertyInputForm").ajaxSubmit({success : function(data) {
					$.messager.alert('提示', data.message, 'info');
					if(data.id=='-1'){
						$('#grid').treegrid('reload');	
					}else{
						$('#grid').treegrid('reload',data.id);	
					}
					$('#edit-window').window('close');
				}
			});		
		}else{
				$.messager.alert('提示', "属性值和显示名称不能为空", 'warnings');
		}
	}
</script>