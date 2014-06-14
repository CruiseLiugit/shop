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
				<li><label>搜索城市名称：</label><input type="text" />
				</li>
				<!--
				<li><select class="combox" name="province" ref="w_combox_city"
					refUrl="${base }/resources/citypart/city_{value}.html">
						<option value="all">所有省市</option>
						<option value="bj">北京</option>
						<option value="sh">上海</option>
				</select><select class="combox" name="city" id="w_combox_city"
					ref="w_combox_area"
					refUrl="${base }/resources/citypart/area_{value}.html">
						<option value="all">所有城市</option>
				</select><select class="combox" name="area" id="w_combox_area">
						<option value="all">所有区县</option>
				</select></li>
				-->
				<li></li>
			</ul>

			<div class="subBar">
				<ul>
					<li><div class="buttonActive">
							<div class="buttonContent">
								<button type="submit">检索</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="${base }/m2_toStoreCityAdd"
				rel="usersNav" target="dialog" width="600" height="500"><span>添加城市</span>
			</a>
			</li>
			<li><a class="delete"
				href="${base }/deleteSysMenus?uid={slt_userid}" target="ajaxTodo"
				title="确定要删除吗?"><span>删除</span> </a>
			</li>
			<li><a class="edit"
				href="${base }/toEditSysUser?uid={slt_userid}" rel="usersNav"
				target="dialog" width="600"><span>修改</span> </a>
			</li>
			<li class="line">line</li>
			<li><a class="icon" href="demo/common/dwz-team.xls"
				target="dwzExport" targetType="navTab" title="实要导出这些记录吗?"><span>导出EXCEL</span>
			</a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="60" align="center">名称</th>
				<th width="40">上级城市</th>
				<th width="40">大区</th>
				<th width="80">描述</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>上海市</td>
				<td>上海市</td>
				<td>华东</td>
				<td>总部</td>
			</tr>
			<tr>
				<td>浦东新区</td>
				<td>上海市</td>
				<td>华东</td>
				<td>无</td>
			</tr>
			<tr>
				<td>七宝镇</td>
				<td>浦东新区</td>
				<td>华东</td>
				<td>直营片区</td>
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
