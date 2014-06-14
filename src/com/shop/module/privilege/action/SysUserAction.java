package com.shop.module.privilege.action;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.code.kaptcha.Producer;
import com.shop.common.Constants;
import com.shop.common.base.BaseController;
import com.shop.common.base.MD5;
//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;
import com.shop.module.privilege.service.inter.SysUserService;

/**
 * 用户登录、退出、登录用户密码修改
 * 
 * @author wubin
 * 
 */
@Controller
@RequestMapping("/user")
public class SysUserAction {
	private static final Logger logger = LoggerFactory.getLogger(SysUserAction.class);
	@Autowired
	public SysUserService sysUserService;
	@Autowired
	private Producer captchaProducer;
	/**
	 * 过度进入登陆页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "index", method = { RequestMethod.POST,RequestMethod.GET })
	public String showLogin(HttpServletRequest request,HttpServletResponse response) {
		return "user/login";
	}
	
	@RequestMapping(value = "/captchaimage", method = RequestMethod.GET)
	public String captchaimage(HttpServletRequest request,HttpServletResponse response) throws Exception {
		response.setDateHeader("Expires", 0);
		// Set standard HTTP/1.1 no-cache headers.
		response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
		// Set IE extended HTTP/1.1 no-cache headers (use addHeader).
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// Set standard HTTP/1.0 no-cache header.
		response.setHeader("Pragma", "no-cache");
		// return a jpeg
		response.setContentType("image/jpeg");
		// create the text for the image
		String capText = captchaProducer.createText();
		// store the text in the session
		request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY,capText);
		// request.getSession().setAttribute("code", capText);
		// create the image with the text
		BufferedImage bi = captchaProducer.createImage(capText);
		ServletOutputStream out = response.getOutputStream();
		// write the data out
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}
		return null;
	}
	/**
	 * 登陆
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "login")
	public String login(HttpServletRequest request, HttpServletResponse response) {
		String auth_code = request.getParameter("auth_code");
		String KAPTCHA_SESSION_KEY=(String)request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
		int result = 0;//0表示成功     2表示验证码错误    1 表示登录名或密码错误
		if(StringUtils.equals(auth_code, KAPTCHA_SESSION_KEY)){//判断验证码是否相等
			Map<String, Object> map = new HashMap<String, Object>();
			String loginName = request.getParameter("loginName");
			String loginPwd = request.getParameter("logPwd");
			if (StringUtils.isNotBlank(loginName)&& StringUtils.isNotBlank(loginPwd)) {
				map.put("loginName", loginName);
				map.put("loginPwd", new MD5().getMD5ofStr(loginPwd));
				SysUser sysUser = sysUserService.findSysUser(map);
				if (sysUser != null) {// 用户存在
					HttpSession session = request.getSession();
					session.setAttribute("loginName", loginName);
					session.setAttribute(Constants.CURRENT_LOGIN_USER, sysUser);
					//登录日志
					/*String type = "1";// 登陆
					try {
						String ip = BaseController.getIpAddr(request);
						//处理登录日志
						int i = sysUserService.insertLoginLog(sysUser, ip, type);
						if (i < 1) {
							result = 1;
						}
					} catch (Exception e) {
						logger.error("登陆日记插入失败!");
					}*/
				} else {
					result = 1;
				}
			}
		}else{
			 result = 2;
		}
		try {
			PrintWriter pw = response.getWriter();
			pw.println(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("登陆失败!");
		}
		return null;
	}

	/**
	 * 异步加载头部显示
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "topLoginName", method = { RequestMethod.POST })
	public void topLoginName(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		String topLoginName = (String) session.getAttribute("loginName");
		try {
			PrintWriter pw = response.getWriter();
			pw.println(topLoginName);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			logger.error("异步加载头部显示失败!");
		}
	}

	/**
	 * 退出
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loginOut", method = { RequestMethod.GET })
	public void loginOut(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Constants.CURRENT_LOGIN_USER);
		String userPhone = sysUser.getUserPhone();
		String userEmail = sysUser.getEmail();
		if (StringUtils.isBlank(userPhone)) {
			userPhone = "";
		}
		if (StringUtils.isBlank(userEmail)) {
			userEmail = "";
		}
		String type = "2";// 退出
		session.removeAttribute("loginName");
		session.removeAttribute(Constants.CURRENT_LOGIN_USER);
		try {
			String ip = BaseController.getIpAddr(request);
			sysUserService.insertLoginLog(sysUser, ip, type);
			PrintWriter out;
			out = response.getWriter();
			out.print("success");
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error("退出日记插入失败!");
		}
	}

	/**
	 * 修改登陆密码
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "updateLoginPwd", method = { RequestMethod.POST,
			RequestMethod.GET })
	public void updateLoginPwd(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Constants.CURRENT_LOGIN_USER);
		Map<String, Object> map = new HashMap<String, Object>();
		String newLoginPwd = request.getParameter("newLoginPwd");
		if (StringUtils.isNotBlank(newLoginPwd)) {
			map.put("newLoginPwd", new MD5().getMD5ofStr(newLoginPwd));
			map.put("userCode", sysUser.getUserCode());
			try {
				sysUserService.updateLoginPwd(map);
			} catch (Exception e) {
				logger.error("修改登陆密码失败!");
			}
		}
	}

	/**
	 * 异步加载主页左边树结构
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "loadMenus", method =  RequestMethod.POST )
	public void loadMenus(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		SysUser sysUser = (SysUser) session.getAttribute(Constants.CURRENT_LOGIN_USER);
		String menuS= sysUserService.loadMenus(sysUser);
		try {
			PrintWriter out = response.getWriter();
			out.print(menuS);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("处理json数据报错：" + e.getStackTrace());
		}
	}

}
