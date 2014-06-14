<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<div class="easyui-layout" fit="true">
     <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
          <form method="post" id="propertyInputForm">
          	<table cellpadding=3>
          	<tr>
          		<td><input type="hidden" name="categoryCode" id="categoryCode" size="40" value="${category.categoryCode}" /></td>
          	</tr>
          	<tr>
          	<td>类目</td>
          	<td><input type="text" name="categoryName" id="propertyName"  value="${category.showName }" readonly="readonly"/></td>
          	</tr>
          	<td>属性</td>
          	<td>
          		<c:forEach items="${propertys}" var="tick">
          			<c:if test="${tick.ischecked!=0 }">
          			<input type="checkbox" name="propertyCode" value="${tick.propertyCode }" checked="checked">${tick.showName }<br>
          			</c:if>
					<c:if test="${tick.ischecked==0 }">
					<input type="checkbox" name="propertyCode" value="${tick.propertyCode }">${tick.showName }<br>
					</c:if>
				</c:forEach>
          	</td>
          	</tr>
          	</table>
          </form>
     </div>
     
     <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
       <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
  </div>
</div>
<script type="text/javascript">
	$(function(){
		//关闭编辑菜单窗口
		$('#btn-cancel').click(function(){
			$('#edit-window').window('close');
		});
	});
</script>