package com.liufuya.core.filter;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.JspView;

import com.liufuya.common.Constants;
import com.liufuya.core.mvc.module.privilege.model.SysUser;

/**
 * Nutz 自定义过滤器,在 主模块 MainMoudle  上设置 
 * 用户登录状态过滤器
 * @author lililiu
 *
 */
public class LoginSessionFilter implements ActionFilter {

	private static final Log log = Logs.get();
	
	public View match(ActionContext actionContext) {
		//获取请求响应对象
		HttpServletRequest req = actionContext.getRequest();
		HttpSession session = req.getSession();
		
		log.debug("--------->登录过滤<---------");
		//设置请求响应对象的字符集
		try {
			SysUser sysUser = (SysUser)session.getAttribute("Constants.CURRENT_LOGIN_USER");
			if (sysUser == null) {
				req.setAttribute("error", "用户名或者密码错误！");
				return new JspView("jsp.account.login");
			}
		} catch (NullPointerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Session 取用户数据为空");
		}catch(Exception e){
			log.info("Session 取用户数据异常");
		}
		
		return null;
	}

}
