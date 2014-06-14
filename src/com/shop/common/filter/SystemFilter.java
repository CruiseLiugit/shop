package com.shop.common.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shop.common.Constants;

/**
 * Controller拦截器 判断是否登录 无需登录的需要在此配置
 */
@Repository
public class SystemFilter extends HandlerInterceptorAdapter {
	private static Logger logger = LoggerFactory.getLogger(SystemFilter.class);

	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		String uri = request.getRequestURI();
		if (uri.indexOf("/user/index") == -1
				&& uri.indexOf("/user/login.do") == -1
				&& uri.indexOf("/user/captchaimage.do") == -1
				) {
			Object obj = request.getSession().getAttribute(Constants.CURRENT_LOGIN_USER);
			if (null == obj) {
				// 未登录
				PrintWriter out = null;
				try {
					out = response.getWriter();
					String outputString = "<script type=\"text/javascript\" charset=\"UTF-8\">"
							+ "window.top.location.href='"
							+ request.getContextPath()
							+ "/login.jsp'</script>";
					out.println(outputString);
					out.flush();
					out.close();
				} catch (IOException e) {
					logger.info("过滤器报错（未登录），错误如下：" + e.getMessage());
				}
				return false;
			} else {
				// if(((SysUser)obj).getWhetheremailvalidate() != null
				// &&((SysUser)obj).getWhethermobilephonevalidate().equals("0")
				// && ((SysUser)obj).getWhethermobilephonevalidate() != null
				// &&((SysUser)obj).getWhetheremailvalidate().equals("0")&&
				// uri.indexOf("User/phoneUsermessages.do") == -1
				// && uri.indexOf("my/getTopCount.do")==-1&&
				// uri.indexOf("User/loginOut.do")==-1)
				// {
				// // 未登录
				// PrintWriter pw = null;
				// try {
				// pw = response.getWriter();
				// String outputString
				// ="<script type=\"text/javascript\" charset=\"UTF-8\">"
				// + "window.top.location.href='"
				// + request.getContextPath()
				// + "/User/phoneUsermessages.do'</script>";
				// pw.println(outputString);
				// } catch (IOException e) {
				// logger.info("过滤器报错（登录），错误如下："+e.getMessage());
				// }
				// return false;
				// }
			}
		}

		// Map paramsMap = request.getParameterMap();
		//
		// for (Iterator<Map.Entry> it = paramsMap.entrySet().iterator();
		// it.hasNext();) {
		// Map.Entry entry = it.next();
		// Object[] values = (Object[]) entry.getValue();
		// for (Object obj : values) {
		// // if (!DataUtil.isValueSuccessed(obj)) {
		// // throw new RuntimeException("有非法字符：" + obj);
		// // }
		// }
		// }

		return super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}

}
