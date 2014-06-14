package com.liufuya.core.mvc.module.privilege.action;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.liufuya.common.Constants;
import com.liufuya.common.dwz.DWZ;
import com.liufuya.common.dwz.ReturnBean;
import com.liufuya.core.mvc.module.privilege.model.Button;
import com.liufuya.core.mvc.module.privilege.model.Menus;
import com.liufuya.core.mvc.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.service.impl.MenusServiceImpl;

/**
 * 用户权限模块，菜单管理控制器类
 * 
 * @author 刘立立
 * 
 */
@IocBean
public class MenusAction {

	private static final Log log = Logs.get();

	@Inject("refer:menusServiceImpl")
	public MenusServiceImpl menusService;

	// ************************************************************************************
	/**
	 * sysMenusList.jsp 页面，查看、修改菜单 按钮，异步请求操作
	 * 返回的数据中，包含了当前菜单的 按钮操作项目，包含已选和未选两种
	 * 这个问题困扰我一天 2014-04-21 晚 11:48
	 */
	@At("/seeMenuById")
	@Ok("json")
	public String seeMenuById(@Param("menuid") String menucode,
			HttpServletRequest req, HttpServletResponse res) {
		log.info("查看菜单项目....... menucode ="+menucode);
		// 1 根据输入值，查询当前菜单表、按钮表、菜单与按钮关系表 获取 json
		String menujson = menusService.getSeeMenusJson(req, res, menucode);
		log.info("menujson    ="+menujson);
		// 3 把结果组合成 json 返回
		if (menujson != null) {
			// 返回验证后的状态
			return menujson;
		} else {
			return "{\"status\":201,\"info\":\"查询错误\",\"data\":[]}";
		}
	}

	
	/**
	 * sysMenusList.jsp 页面，增加、修改菜单 按钮，异步请求操作
	 */
	@At("/saveMenus")
	@Ok("json")
	public String saveMenus(@Param("::menu.") Menus menus,@Param("buttonchks") String selectButtonIds,HttpServletRequest request) {
		log.info("页面传递来的 菜单项输入 ....... menucode ="+menus.getMenuName());
		log.info("按钮复选框值 buttonchks ="+selectButtonIds);
		String msg ="";
		try {
			menusService.saveMenus(request, menus, selectButtonIds);
			msg="{\"status\":200,\"info\":\"查询正确\",\"data\":[]}";
		} catch (Exception e) {	   
			msg="{\"status\":201,\"info\":\"查询错误\",\"data\":[]}";
			log.error("保存失败:"+e.getStackTrace());
		}
		
		return msg;
	}

	
	/**
	 * sysMenusList.jsp 页面，删除菜单 按钮，异步请求操作
	 */
	@At("/deleteMenus")
	@Ok("json")
	public String deleteMenus(@Param("menuid") String ids,HttpServletRequest request) {
		//log.info("删除，页面传递来的 菜单项输入 ....... menuid ="+ids);
		//log.info("按钮复选框值 buttonchks ="+selectButtonIds);
		String msg ="";
		  try {
			menusService.deleteMenus(ids); // 删除菜单
			msg="{\"status\":200,\"info\":\"正常删除\",\"data\":[]}";
		} catch (Exception e) {
			msg="{\"status\":201,\"info\":\"删除出现错误\",\"data\":[]}";  // 删除失败
		    log.error("删除失败:"+e.getStackTrace());
		}
		return msg;
	}
	
	
	// ************************************************************************************
		@At("/toCreateSysMenu")
		@Ok("jsp:jsp.sysmenus.sysMenusAdd")
		public void toCreateSysMenu(HttpServletRequest request) {
			// 简单跳转,获取菜单需要的 操作按钮，传递过去
			// 这里查询数据库，获取所有系统菜单列表
			List<Menus> menuslist = this.menusService.getMenusList(1,
					Constants.MENUS_PAGE_SIZE);
			request.setAttribute("sysmenuslist", menuslist);
			
			//取出所有的按钮项目
			List<Button> buttonslist= menusService.getAllButtons();
			request.setAttribute("buttonlist", buttonslist);
		}

		// 用户管理， 增加、查看、修改、删除用户模块
		// 2014-04-20
		/**
		 * sysUserList.jsp 页面，增加 用户功能，异步操作
		 * 
		 * @Param 适配器获取 HTTP 参数，:: 表示参数是个对象 ,user. 表示表单参数名前缀
		 */
		@At("/createSysMenus")
		public String createSysMenus(@Param("::menu.") Menus menus,@Param("buttonchks") String selectButtonIds,@Param("navTabId") String navTabId, HttpServletRequest request) {
			 log.info("增加菜单 menus :"+menus);
			 log.info("增加菜单 menus name :"+menus.getMenuName());
			 log.info("增加菜单 menus paremtcode :"+menus.getFmenuCode());
			 log.info("增加菜单 menus buttons :"+selectButtonIds);

			
			ReturnBean bean = new ReturnBean();
			// 4 插入数据库,先插入 sys_menus ，再插入 sys_authorities
			try {
				// /////////////4月20日晚，暂时没有菜单模块，角色模块，所以暂停////////////////
				menusService.saveMenus(request, menus, selectButtonIds);
				// message = "新增系统用户成功";
				bean.setStatusCode(DWZ.statusCode.ok);
				// bean.setMessage(getText("msg.operation.success",request));
				bean.setMessage("操作成功");
			} catch (Exception e) {
				e.printStackTrace();
				// message="新增系统用户失败:";
				bean.setStatusCode(DWZ.statusCode.error);
				bean.setMessage("操作失败");
			}
			
			bean.setNavTabId(navTabId);
			bean.setRel("");
			bean.setCallbackType(DWZ.CallbackType);
			bean.setForwardUrl("");
			
			// 返回验证后的状态
			String json = Json.toJson(bean);
			return json;
		}

		// 删除用户
		@At("/deleteSysMenus")
		@Ok("json")
		public String deleteSysUser(@Param("uid") String uid,
				HttpServletRequest request) {
			// 简单跳转
			log.info("user id=" + uid);
			//String msg = getText("msg.operation.success", request);
			//log.info("本地 字符串 msg =" + msg);
			//return ajaxForwardSuccess(msg);
			return "";
		}

		//
		// 跳转到修改用户页面
		@At("/toEditSysUser")
		@Ok("jsp:jsp.sysuser.sysUserEdit")
		public void toEditSysUser(@Param("uid") String uid,
				HttpServletRequest request) {
			//根据用户 id 查询用户对象
			//log.info("要修改用户的 code ="+uid);
			
			
		}

		// 修改用户
		@At("/eidtSysUser")
		//@Ok("jsp:jsp.sysuser.sysUserList")
		public String eidtSysUser(@Param("::user.") SysUser user,
				@Param("navTabId") String navTabId, @Param("userid") String uid,
				HttpServletRequest request) {
			// 跳转并刷新列表数据
			//log.info("修改用户信息 user :" + user);
			//log.info("修改用户信息 user loginname :" + user.getLoginName());
			//log.info("修改用户信息 user name :" + user.getUserName());
			//log.info("修改用户信息 user type :" + user.getUserType());

			// 1 适配器自动把页面数据，封装到 user 对象中
			

			ReturnBean bean = new ReturnBean();
			// 4 插入数据库
			try {
				// /////////////4月20日晚，暂时没有菜单模块，角色模块，所以暂停////////////////
				// sysUserService.saveSysUser(user,relativedRoles);
				// message = "新增系统用户成功";
				bean.setStatusCode(DWZ.statusCode.ok);
				// bean.setMessage(getText("msg.operation.success",request));
				bean.setMessage("操作成功");
				bean.setNavTabId(navTabId);
				bean.setRel("");
				bean.setCallbackType(DWZ.CallbackType);
				bean.setForwardUrl("");
			} catch (Exception e) {
				e.printStackTrace();
				// message="新增系统用户失败:";
			}

			// 返回验证后的状态
			return Json.toJson(bean);
		}

	
}
