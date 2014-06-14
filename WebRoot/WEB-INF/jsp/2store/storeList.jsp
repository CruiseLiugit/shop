<%@ page language="java" import="java.util.*"
	contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/taglibs.jsp"%>


<form id="pagerForm" method="post" action="demo_page1.html">
	<input type="hidden" name="status" value="${param.status}"> <input
		type="hidden" name="keywords" value="${param.keywords}" /> <input
		type="hidden" name="pageNum" value="1" /> <input type="hidden"
		name="numPerPage" value="${model.numPerPage}" /> <input type="hidden"
		name="orderField" value="${param.orderField}" />
</form>



<div class="pageHeader">
	<form onsubmit="return navTabSearch(this);" action="demo_page1.html"
		method="post">
		<div class="searchBar">
			<ul class="searchContent">
				<li><label>搜索门店名称：</label><input type="text"/></li>
				
				<li>地点:&nbsp; &nbsp;<b><span id="city">
				<c:choose>
					<c:when test="${checkedCity ne null }">
						<c:out value="${checkedCity }"></c:out>
					</c:when>
					<c:otherwise>上海</c:otherwise>
				</c:choose>
				&nbsp; &nbsp;<a style="color:#2a70b8;" href="${base }/toChangeCitys" rel="changeCityes" target="dialog" width="600" height="450">[切换城市]</a>	</span></b></li>
				<li>中心位置:&nbsp; &nbsp;<b><span id="city"><a style="color:#2a70b8;" href="${base }/toChangeCitys" rel="changeCityes" target="dialog" width="600" height="450">[地标]</a></span></b></li>
				
				<!-- 
				<li>
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
				</li>
				 -->
				<li></li>
			</ul>

			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div></li>
					<li><a class="button" href="${base }/m2_toStoreSearch" target="dialog" width="720" height="400" mask="true" title="高级检索" ><span>高级检索</span></a></li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${base }/toCreateSysMenu"
				rel="usersNav" target="dialog" width="600" height="500"><span>添加</span>
			</a></li>
			<li><a class="delete"
				href="${base }/deleteSysMenus?uid={slt_userid}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span> </a></li>
			<li><a class="edit"
				href="${base }/toEditSysUser?uid={slt_userid}" rel="usersNav"
				target="dialog" width="600"><span>修改</span> </a></li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
			</a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="20">店铺编码</th>
				<th width="60">店铺名称</th>
				<th width="30">经营类型</th>
				<th width="30">店铺类型</th>
				<th width="120">地址</th>
				<th width="30">大区</th>
				<th width="30">省份</th>
				<th width="30">城市</th>
				<th width="30">区(县)</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>010120</td>
				<td>闸北区闻喜路店</td>
				<td>直营</td>
				<td>商社型</td>
				<td>上海闸北区闻喜路954号</td>
				<td>华东</td>
				<td>上海市</td>
				<td>上海市</td>
				<td>闸北区</td>
			</tr>
			<tr>
				<td>101415</td>
				<td>常州万达一号街店</td>
				<td>直营</td>
				<td>商圈型</td>
				<td>江苏省常州市万达广场一号街路213号</td>
				<td>华东</td>
				<td>江苏省</td>
				<td>常州市</td>
				<td>城区</td>
			</tr>
			<tr>
				<td>010416</td>
				<td>昆山震川西路二店</td>
				<td>直营</td>
				<td>商社型</td>
				<td>江苏省昆山市震川西路234号</td>
				<td>华东</td>
				<td>江苏省</td>
				<td>苏州市</td>
				<td>昆山市</td>
			</tr>
		</tbody>
		<!--
		<tbody> 
			<c:forEach var="sysmenus" items="${sysmenuslist}">
				<c:if test="${sysmenus.menuUrl != '#'}">
					<tr>
						<td>${sysmenus.menuName }</td>
						<td>${sysmenus.levelId }</td>
						<td>${sysmenus.engName }</td>
					</tr>
				</c:if>
			</c:forEach>
		</tbody>
		 -->
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span> <select class="combox" name="numPerPage"
				onchange="navTabPageBreak({numPerPage:this.value})">
				<option value="20">20</option>
				<option value="50">50</option>
				<option value="100">100</option>
				<option value="200">200</option>
			</select> <span>条，共${totalCount}条</span>
		</div>

		<div class="pagination" targetType="navTab" totalCount="200"
			numPerPage="20" pageNumShown="10" currentPage="1"></div>

	</div>
</div>
