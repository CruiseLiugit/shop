package com.shop.module.privilege.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.common.base.Datagrid;
import com.shop.common.util.JsonUtils;
import com.shop.common.util.ResponseUtils;
import com.shop.module.privilege.model.Role;
import com.shop.module.privilege.service.inter.MenusService;
import com.shop.module.privilege.service.inter.RoleService;

/**
 * 角色Action
 * 
 * @author caryCheng
 * 
 */
@Controller
@RequestMapping("/role")
public class RoleAction {
	private static final Logger logger = LoggerFactory.getLogger(RoleAction.class);
	@Autowired
	private RoleService roleService;
	@Autowired
    private MenusService menusService;
	/**
	 * 获取所有角色信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "queryAllRoleList", method = { RequestMethod.POST })
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
			roleJsonStr = JsonUtils.objectToJackson(datagrid, Datagrid.class);
			PrintWriter out = response.getWriter();
			out.print(roleJsonStr);
			out.flush();
			out.close();
			request.setAttribute("roleJsonStr", roleJsonStr);
		} catch (Exception e) {
			logger.error("处理json数据报错：" + e.getStackTrace());
		}
		logger.info(roleJsonStr);

		return null;
	}
	
	/**
	 * 新增角色
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "saveRole", method = { RequestMethod.POST })
	public String saveRole(HttpServletRequest request,HttpServletResponse response,Role role,String selectAuthIds) {
		   String msg = "";
			try {
				roleService.saveRole(request,role,selectAuthIds); // 保存角色
				msg = "1"; // 保存角色成功
			} catch (Exception e) {
				msg = "0"; // 保存角色失败
				logger.error("保存角色失败:"+e.getStackTrace());
			}
			ResponseUtils.renderText(response, msg);
		return null;
	}
	/**
	 * 通过id获取角色
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "getRoleById", method = { RequestMethod.GET })
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
	@RequestMapping(value = "getRoleByCode", method = { RequestMethod.GET })
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
	@RequestMapping(value = "deleteRoles", method = { RequestMethod.GET })
	public String deleteRoles(HttpServletResponse response,String ids){
		String msg ="";
		try {
			roleService.deleteRoles(ids);
			msg ="1";
		} catch (Exception e) {
			msg ="0";
			logger.error("删除角色失败:" +e.getStackTrace());
		}
		ResponseUtils.renderText(response, msg);
		return null;
	}
	/**
	 * 根据菜单名称获取菜单
	 * @param response
	 * @param roleName
	 * @return
	 */
	@RequestMapping(value="getRoleByRoleName",method={RequestMethod.POST})
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
		    rolesJsonStr = JsonUtils.objectToJackson(datagrid, Datagrid.class);// 将对象转换成json字符串
			PrintWriter out = response.getWriter();
			out.print(rolesJsonStr);
			out.flush();
			out.close();	
		} catch (Exception e) {
			logger.error("处理json数据报错：" + e.getStackTrace());
		} 
		return null;
	}
	
	/**
	 * 加载权限树
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "loadAllAuthTree", method = { RequestMethod.POST })
	public String loadAllAuthTree(HttpServletRequest request,HttpServletResponse response){
	    String id = request.getParameter("id");
		List<Map<String,Object>> checkedAuthList = roleService.getCheckedAuthIds(id);
		  String authTreeJson = menusService.createAuthTree(checkedAuthList);
		   logger.info(authTreeJson);
		   PrintWriter out;
		try {
			out = response.getWriter();
			out.print(authTreeJson);
			out.flush();
			out.close();
		} catch (IOException e) {	
			logger.error("处理json数据报错：" + e.getStackTrace());	
		}					 
		return null;
	}
	/**
	 * 验证用户名是否存在
	 * @param roleName
	 * @return
	 */
	@RequestMapping(value = "validataRoleName", method = { RequestMethod.POST })
	public void validataRoleName(HttpServletResponse response,String roleName){
		int i = roleService.valiedateRoleName(roleName);
		String ok=i+"";
		  try {
			PrintWriter out = response.getWriter();
			out.write(ok);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("处理数据报错：" + e.getStackTrace());
		}
	}
	
}
