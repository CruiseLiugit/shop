package com.liufuya.common.dwz;

/**
 * form提交后返回json数据结构
 * statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. 
 * statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因. 
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * 
 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent", "rel"."xxxId"}
 * {"statusCode":"300", "message":"操作失败"}
 * {"statusCode":"301", "message":"会话超时"}
 */
public class StatusCode {
	public static final String ok="200";
	public static final String error="300";
	public static final String timeout="301";
	
}
