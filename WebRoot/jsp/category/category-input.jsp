<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
  <form id="inputForm" action="${pageContext.request.contextPath }/category/save.do" method="post">
	<input  type="hidden" name="select" value="${select }">
	<input	type="hidden" name="categoryCode" value="${category.categoryCode}" />	
	<input	type="hidden" name="categoryPcode" value="${category.categoryPcode}" />		
	<input type="hidden"  name="categoryRootcode" value="${category.categoryRootcode}" />
    <table cellpadding=3>
	<tr>
		<td>类目名称<span class="required">(*)</span></td>
		<td><input type="text" name="categoryName" size="40" maxlength="20"
			value="${category.categoryName}" id="categoryName" class="easyui-validatebox" required="true" validType="length[2,20]" /></td>
	</tr>
	<tr>
		<td>展示名称<span class="required">(*)</span></td>
		<td><input type="text" name="showName" size="40" maxlength="20"
			value="${category.showName}" class="easyui-validatebox" required="true" validType="length[2,20]"/></td>
	</tr>
	<tr>
		<td>上级类目<span class="required">(*)</span></td>
		<td><input type="text"  size="40"
			value="${categoryPname}" disabled="true" id="help"/></td>
	</tr>
	<tr>
		<td>排序权值<span class="required">(*)</span></td>
		<td>
		<input  id="categoryOrder" name="categoryOrder"  value="<c:choose><c:when test='${category.categoryOrder!=null}'>${category.categoryOrder}</c:when><c:otherwise>0</c:otherwise></c:choose>" />
		</td>
	</tr> 	
		<input type="hidden" name="isShow" value="1" /> 
	</table>
	</form>
	</div>	
           
  <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
       <a class="easyui-linkbutton" onclick="subform();" id="btn-save" icon="icon-save" href="javascript:void(0);">保存</a> 
       <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
  </div>
</div>
<script language="javascript">
	//关闭编辑菜单窗口
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
	
	var isDoubleSubmit=false;
	$('#categoryOrder').spinner({ 
		required:true, 
		onSpinUp:function(){
			var number=parseInt($('#categoryOrder').val());
			if(number<99999){
				number++;
				$('#categoryOrder').val(number);
			}
		},
		onSpinDown:function(){
			var number=parseInt($('#categoryOrder').val());
			if(number>0){
				number--;
				$('#categoryOrder').val(number);
			}
		}
	}); 
	
	function subform(){
		if($("#inputForm").form('validate')){  
			if(!isDoubleSubmit){
				isDoubleSubmit=true;
				var params =$("#inputForm").serialize(); 
				$.ajax({
						url : '${contextPath }/category/save.do',
						type : 'post',
						data : params,
						success : function(data) {
								$.messager.alert('提示', data.message+'!', 'info');
								if(data.id=='-1'){
									$('#grid').treegrid('reload');	
								}else{
									$('#grid').treegrid('reload',data.id);	
								}
								$('#edit-window').window('close');
						}
				});		
			}
		}
	}
	$(function(){
		$(".minus").click(function(){
			if($("#categoryOrder").val()>0){
				$("#categoryOrder").val(parseInt($("#categoryOrder").val())-1);
			}
		});
		$(".plus").click(function(){
			$("#categoryOrder").val(parseInt($("#categoryOrder").val())+1);
		});
		$("#categoryOrder").keyup(function(){
			if(isNaN($(this).val())||parseInt($(this).val())<1||$(this).val()==''){
				$(this).val(0);
			}
		});
	});
</script>
