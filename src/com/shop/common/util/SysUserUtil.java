package com.shop.common.util;

import javax.servlet.http.HttpServletRequest;

import com.shop.common.Constants;
import com.shop.module.privilege.model.SysUser;

public class SysUserUtil {
	public static SysUser getLoginUser(HttpServletRequest request){
		Object userObject=request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
		if(userObject!=null){
			return (SysUser)userObject;
		}
		return null;
	}
}
