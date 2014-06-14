package com.liufuya.common.dwz;

/**
 * form提交后返回json数据结构
 * statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. 
 * statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因. 
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * 
 * 
 */
public class DWZ {
	public static StatusCode statusCode;
	public static String CallbackType = "closeCurrent";
}
