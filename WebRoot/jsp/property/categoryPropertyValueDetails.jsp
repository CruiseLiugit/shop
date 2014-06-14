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
							<th align="center" width="50%">属性值名称</th>
							<th align="left" width="50%">属性值显示名称</th>
						</tr>
					</table>
					<table width="100%" style="border:1px #ccc solid" id="oldShow">	
					<c:choose>
					<c:when test="${fn:length(categoryPropertyValues)>0}">
					<c:forEach items="${categoryPropertyValues}" var="it" varStatus="status">
					<tr>
						<td width='50%'><input type='text' size='15' value='${it.pvName}' disabled='disabled'/></td>
						<td width='50%'><input type='text' size='15' value='${it.showName}' disabled='disabled'/></td>
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
       <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
  	</div>
</div>
<script language="JavaScript" type="text/javascript">
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
</script>