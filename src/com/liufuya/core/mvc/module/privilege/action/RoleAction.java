package com.liufuya.core.mvc.module.privilege.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.liufuya.common.base.Datagrid;
import com.liufuya.core.mvc.module.privilege.model.Authority;
import com.liufuya.core.mvc.module.privilege.model.Role;
import com.liufuya.core.mvc.module.privilege.service.impl.MenusServiceImpl;
import com.liufuya.core.mvc.module.privilege.service.impl.RoleServiceImpl;

/**
 * 用户权限模块，菜单管理控制器类
 * 
 * @author 刘立立
 * 
 */
@IocBean
public class RoleAction {

	private static final Log log = Logs.get();
	
	@Inject("refer:roleServiceImpl")
	public RoleServiceImpl roleService;
	
	@Inject("refer:menusServiceImpl")
	public MenusServiceImpl menusService;
		
	/**
	 * 获取所有角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(value = "queryAllRoleList", method = { RequestMethod.POST })
	public String queryAllRoleList(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page"));// 当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page * rows - rows; // 分页查询开始位置
		String roleJsonStr = "";
		Datagrid datagrid = new Datagrid(); 
		int roleTotalCount = roleService.queryRoleTotalCount();// 统计角色数量
		List<Role> roleList = roleService.quryAllRoleList(startNum, rows);// 查询角色列表
		datagrid.setRows(roleList);
		datagrid.setTotal(roleTotalCount);
		try {
			// 将查询的角色集合list转换成json格式字符串
			roleJsonStr = Json.toJson(datagrid);//JsonUtils.objectToJackson(datagrid, Datagrid.class);
			PrintWriter out = response.getWriter();
			out.print(roleJsonStr);
			out.flush();
			out.close();
			request.setAttribute("roleJsonStr", roleJsonStr);
		} catch (Exception e) {
			log.error("处理json数据报错：" + e.getStackTrace());
		}
		log.info(roleJsonStr);

		return null;
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(value = "saveRole", method = { RequestMethod.POST })
	public String saveRole(HttpServletRequest request,HttpServletResponse response,Role role,String selectAuthIds) {
		   String msg = "";
			try {
				roleService.saveRole(request,role,selectAuthIds); // 保存角色
				msg = "1"; // 保存角色成功
			} catch (Exception e) {
				msg = "0"; // 保存角色失败
				log.error("保存角色失败:"+e.getStackTrace());
			}
			//ResponseUtils.renderText(response, msg);
		return null;
	}
	/**
	 * 通过id获取角色
	 * @param request
	 * @param id
	 * @return
	 */
	//@RequestMapping(value = "getRoleById", method = { RequestMethod.GET })
	public String getRoleById(HttpServletRequest request,String id){
		Role role = roleService.getRoleById(id);
		 request.setAttribute("role", role);	
		 return "role/role-input";	
	}
	/**
	 * 
	 * @param request
	 * @param id
	 * @return
	 */
	//@RequestMapping(value = "getRoleByCode", method = { RequestMethod.GET })
	public String getRoleByCode(HttpServletRequest request,String id){
		Role role = roleService.getRoleById(id);
		 request.setAttribute("role", role);	
		 return "role/roleDetails";	
	}
	/**
	 * 删除角色-支持批量删除
	 * @param response
	 * @param ids
	 * @return
	 */
	//@RequestMapping(value = "deleteRoles", method = { RequestMethod.GET })
	public String deleteRoles(HttpServletResponse response,String ids){
		String msg ="";
		try {
			roleService.deleteRoles(ids);
			msg ="1";
		} catch (Exception e) {
			msg ="0";
			log.error("删除角色失败:" +e.getStackTrace());
		}
		//ResponseUtils.renderText(response, msg);
		return null;
	}
	/**
	 * 根据菜单名称获取菜单
	 * @param response
	 * @param roleName
	 * @return
	 */
	//@RequestMapping(value="getRoleByRoleName",method={RequestMethod.POST})
	public String getRoleByRoleName(HttpServletRequest request,HttpServletResponse response,String roleName){
		int page = Integer.parseInt(request.getParameter("page"));// 当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page * rows - rows; // 分页查询开始位置
		
		List<Role> roles = roleService.getRoleByRoleName(roleName,startNum, rows);
		int rolesTotalCountByRoleName = roleService.getRolesTotalCountByRoleName(roleName);
		Datagrid datagrid = new Datagrid(); 
		datagrid.setRows(roles);
		datagrid.setTotal(rolesTotalCountByRoleName);
		 String  rolesJsonStr="";
		  try {
		    rolesJsonStr = Json.toJson(datagrid); //JsonUtils.objectToJackson(datagrid, Datagrid.class);// 将对象转换成json字符串
			PrintWriter out = response.getWriter();
			out.print(rolesJsonStr);
			out.flush();
			out.close();	
		} catch (Exception e) {
			log.error("处理json数据报错：" + e.getStackTrace());
		} 
		return null;
	}
	
	/**
	 * 加载权限树
	 * @param request
	 * @param response
	 * @return
	 */
	//@RequestMapping(value = "loadAllAuthTree", method = { RequestMethod.POST })
	public String loadAllAuthTree(HttpServletRequest request,HttpServletResponse response){
	    String id = request.getParameter("id");
	    List<Authority> checkedAuthList = roleService.getCheckedAuthIds(id);
		  String authTreeJson = menusService.createAuthTree(checkedAuthList);
		   log.info(authTreeJson);
		   PrintWriter out;
		try {
			out = response.getWriter();
			out.print(authTreeJson);
			out.flush();
			out.close();
		} catch (IOException e) {	
			log.error("处理json数据报错：" + e.getStackTrace());	
		}					 
		return null;
	}
	/**
	 * 验证用户名是否存在
	 * @param roleName
	 * @return
	 */
	//@RequestMapping(value = "validataRoleName", method = { RequestMethod.POST })
	public void validataRoleName(HttpServletResponse response,String roleName){
		int i = roleService.valiedateRoleName(roleName);
		String ok=i+"";
		  try {
			PrintWriter out = response.getWriter();
			out.write(ok);
			out.flush();
			out.close();
		} catch (IOException e) {
			log.error("处理数据报错：" + e.getStackTrace());
		}
	}
	
	
	
}
