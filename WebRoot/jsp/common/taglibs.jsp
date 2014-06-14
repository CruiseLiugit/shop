<%@ page import="java.text.*,java.lang.*,java.net.*,java.util.*,com.shop.common.util.StringUtil" pageEncoding="UTF-8"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%
	if (StringUtil.isEmpty((String)request.getAttribute("contextPath"))) {
		String contextPath = request.getContextPath();
		request.setAttribute("contextPath", contextPath);
		request.getSession().setAttribute("contextPath", contextPath);
	}
	//项目根路径
	if(request.getAttribute("ctx") == null || request.getAttribute("ctx").toString().trim().equals("ctx")){
		request.setAttribute("ctx", request.getContextPath());
		request.getSession().setAttribute("ctx", request.getContextPath());
	}
%>
