package com.liufuya.core.mvc.module;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;

import com.liufuya.core.mvc.module.member.model.MemberBean;
import com.liufuya.core.mvc.module.member.service.impl.MemberServiceImpl;

/**
 * 用户所有菜单链接的跳转
 * 
 * @author 刘立立
 * 
 */
@IocBean
public class LeftMenuAction {

	private static final Log log = Logs.get();

	@Inject("refer:memberServiceImpl")
	public MemberServiceImpl memberServiceImpl;

	/**
	 * 项目启动时的验证，搭建项目框架使用，返回当前系统时间
	 * 
	 * @return
	 */
	@At("/ping")
	public Object ping() {
		return new Date();
	}

	/**
	 * 进入系统，根路径 / 进入登陆页面， 可以使用 View 对象控制返回到哪个视图路径
	 */
	@At("/")
	@Ok("jsp:jsp.account.login")
	public void index() {

	}

	// ---------------------左侧菜单-----------------------
	/**
	 * 1 工作台模块 跳转到 WEB-INF/jsp/desktop/mydesk.jsp
	 * 
	 * @param request
	 */
	@At("/m1_tomyDesk")
	@Ok("jsp:jsp.1desktop.mydesk")
	public void m1_tomyDesk() {

	}

	// ---------------------门店菜单-----------------------
	/**
	 * 2 门店所在城市
	 * 
	 * @param request
	 */
	@At("/m2_toCityList")
	@Ok("jsp:jsp.2store.storeCityList")
	public void m2_toCityList() {

	}

	/**
	 * 2 已开门店列表 跳转到 WEB-INF/jsp/desktop/mydesk.jsp
	 * 
	 * @param request
	 */
	@At("/m2_toStoreList")
	@Ok("jsp:jsp.2store.storeList")
	public void m2_toStoreList() {

	}

	// ---------------------会员管理菜单-----------------------
	/**
	 * 5 会员资料
	 * 
	 * @param request
	 */
	@At("/m5_memberInfoList")
	@Ok("jsp:jsp.5member.memberInfoList")
	public void m5_memberInfoList(HttpServletRequest request) {
		// 这里查询所有会员信息,lfy_member, lfy_member_address
		List<MemberBean> list = memberServiceImpl.findAllMembers();
		log.info("所有会员 list size = " + list.size());
		request.setAttribute("memberslist", list);
	}

}
