<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<!--角色输入窗口-->
<div class="easyui-layout" fit="true">
     <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
          <form method="post" id="propertyInputForm">
          	<table cellpadding=3>
          	<tr>
          		<td><input id="id" name="propertyCode" type="hidden"  value="${property.propertyCode }" /></td>
          	</tr>
          	<tr>
				<td>属性名称</td>
				<td><input type="text" name="propertyName" id="propertyName" required="true" class="easyui-validatebox" missingMessage="不能为空" value="${property.propertyName }"/></td>
			</tr>
			<tr>
				<td>显示名称</td>
				<td><input type="text" name="showName" id="showName" required="true" class="easyui-validatebox" missingMessage="不能为空" value="${property.showName }" /></td>
			</tr>
			<tr>
				<td>类型</td>
				<td><select name="propertyType" id="propertyType"><option value="1">字符串</option><option value="2">价格区间</option></select></td>
			</tr>
			<tr>
				<td>选择类型</td>
				<td><select name="chooseStatus" id="chooseStatus"><option value="2">多选</option><option value="1">单选</option></select></td>
			</tr>
			<tr>
				<td>排序权值</td>
				<td>
				<input id="propertyOrder" name="propertyOrder"  value="<c:choose><c:when test='${property.propertyOrder!=null}'>${property.propertyOrder}</c:when><c:otherwise>0</c:otherwise></c:choose>" />
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
	var isDoubleSubmit=false;
	function subform(){
		if($("#propertyInputForm").form('validate')){  
			if(!isDoubleSubmit){
				isDoubleSubmit=true;
				var params =$("#propertyInputForm").serialize(); 
				$.ajax({
						url : '${contextPath }/property/saveProperty.do',
						type : 'post',
						data : params,
						success : function(data) {
								$.messager.alert('提示', data+'!', 'info');
								grid.datagrid('reload');	
								$('#edit-window').window('close');
						}
				});		
			}
		}
	}
	$(function(){
		//关闭编辑菜单窗口
		$('#btn-cancel').click(function(){
			$('#edit-window').window('close');
		});
		$("#propertyType").val("${property.propertyType}");
		$("#chooseStatus").val("${property.chooseStatus}");
		
		$('#propertyOrder').spinner({ 
			required:true, 
			onSpinUp:function(){
				var number=parseInt($('#propertyOrder').val());
				if(number<99999){
					number++;
					$('#propertyOrder').val(number);
				}
			},
			onSpinDown:function(){
				var number=parseInt($('#propertyOrder').val());
				if(number>0){
					number--;
					$('#propertyOrder').val(number);
				}
			}
		}); 
		$("#propertyOrder").blur(function(){
			var j = parseInt($("#propertyOrder").val());
			this.value = this.value.replace(/[^\d]/g, '');
			if(this.value==''){
				this.value='1';
			}
		});
	});
</script>