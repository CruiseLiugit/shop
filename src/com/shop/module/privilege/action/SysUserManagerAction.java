package com.shop.module.privilege.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.common.Constants;
import com.shop.common.base.BaseController;
import com.shop.common.util.DateUtil;
import com.shop.common.util.ResponseUtils;
import com.shop.module.privilege.model.Role;
//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;
import com.shop.module.privilege.service.inter.RoleService;
import com.shop.module.privilege.service.inter.SysUserService;

/**
 * 
 * @Title :  SysUserManagerAction.java
 * @author:  花崴 
 * @Description:系统用户管理Actionc层
 * @date  :Oct 17, 2013  5:39:29 PM
 */
@Controller
@RequestMapping("/sysUserManager")
public class SysUserManagerAction {

	private static final Logger logger = LoggerFactory.getLogger(SysUserAction.class);
	@Autowired
	public SysUserService sysUserServiceImpl;
	@Autowired
	public RoleService roleServiceImpl;
	
	//分页查询系统用户List方法
	@RequestMapping(value="getAllSysUserList")
	public ModelAndView getAllSysUserList(HttpServletRequest request,HttpServletResponse response){
		logger.info("---------分页查询系统用户List方法开始--------");
		int page = Integer.parseInt(request.getParameter("page"));//当前页数
		int  rows =Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page*rows-rows; //分页查询开始位置
		int recordSize = sysUserServiceImpl.getTotalCount();//获取总记录数
		List<SysUser> users = new ArrayList<SysUser>();
		users = sysUserServiceImpl.getAllSysUserList(startNum,rows);
        //遍历用户信息组装json数据
		JSONArray json = new JSONArray();
         if(null!=users && users.size() > 0){
             for (SysUser user:users) {
               JSONObject cell = new JSONObject();
               cell.put("id",user.getUserCode());
               cell.put("loginName",user.getLoginName());
               cell.put("email",user.getEmail());
               cell.put("userName",user.getUserName());
               cell.put("userPhone",user.getUserPhone());
               cell.put("createDate",DateUtil.getDate(user.getCreateDate()));
               json.add(cell);
           }
         }
         //处理json数据
         BaseController.processJson(response,recordSize,json);
         logger.info("------分页查询系统用户List方法开始结束----");
		 return null;

	}
	
	//输入系统用户方法
	@RequestMapping(value="saveSysUser")
	public void saveSysUser(SysUser sysUser,HttpServletRequest request,String relativedRoles,HttpServletResponse response){
		String message=null;
		if(StringUtils.isEmpty(sysUser.getUserCode())){//新增系统用户
			logger.info("---------新增系统用户方法开始，绑定角色id--------"+relativedRoles);
			String uuid = UUID.randomUUID().toString();
			sysUser.setUserCode(uuid);
			sysUser.setStatus(Constants.SYSUSER_STATUS_VALID);
			try {
				sysUserServiceImpl.saveSysUser(sysUser,relativedRoles);
				message = "新增系统用户成功";
			} catch (Exception e) {
				e.printStackTrace();
				message="新增系统用户失败:";
			}
			ResponseUtils.renderText(response, message);
			logger.info("---------新增系统用户方法结束--------"+message);
		}else{//修改系统用户
			logger.info("---------修改系统用户方法开始,修改用户id--------"+sysUser.getId());
			try {
				sysUserServiceImpl.updateSysUserById(sysUser,relativedRoles);
				message = "修改系统用户成功";
			} catch (Exception e) {
				e.printStackTrace();
				message="修改系统用户失败:";
			}
			ResponseUtils.renderText(response, message);
			logger.info("---------修改系统用户方法结束--------"+message);
		}
	}
	
	//删除系统用户方法
	@RequestMapping(value="deleteSysUser")
	public void deleteSysUser(SysUser sysUser,HttpServletRequest request,HttpServletResponse response){
		String message =null;
		logger.info("---------删除系统用户方法开始,删除用户id--:"+request.getParameter("userCode"));
		String userCode = request.getParameter("userCode");
		try {
			if(userCode.equals("8532aa11-08ae-444d-bb72-801f4f9997b6")){//系统管理员id
				message="删除系统用户失败:不能删除系统管理员";
			}else{
				sysUserServiceImpl.deleteSysUserById(userCode);
				message = "删除系统用户成功";
			}
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除系统用户失败";
		}
		ResponseUtils.renderText(response, message);
		logger.info("---------删除系统用户方法结束--------"+message);
	}
	
	//根据id查询系统用户
	@RequestMapping(value="getSysUserById")
	public String getSysUserById(HttpServletRequest request,HttpServletResponse response){
		logger.info("------根据id查询系统用户方法开始----");
		String sysUserId = request.getParameter("id");
		List<SysUser> sysUserList = sysUserServiceImpl.getSysUserById(sysUserId);
		SysUser user = null;
		if(null!=sysUserList&&sysUserList.size()>0&&!sysUserList.isEmpty()){
			user = sysUserList.get(0);
		}
		String selectedRoleNames= roleServiceImpl.getRoleNamesByUserId(sysUserId);
		if(StringUtils.isNotBlank(selectedRoleNames)){
			selectedRoleNames = selectedRoleNames.substring(0,selectedRoleNames.length()-1);//样式转换，去除最后一个字符
		}else{
			selectedRoleNames="";
			logger.info("用户关联的角色信息为空");
			}
		String selectedRoleIds = roleServiceImpl.getRoleIdByUserId(sysUserId);
		if(StringUtils.isNotBlank(selectedRoleIds)){
			selectedRoleIds = selectedRoleIds.substring(0,selectedRoleIds.length()-1);//样式转换，去除最后一个字符
		}else{
			selectedRoleIds="";
			logger.info("用户关联的角色信息为空");
		}
		request.setAttribute("user", user);
		request.setAttribute("selectedRoleNames", selectedRoleNames);
		request.setAttribute("selectedRoleIds", selectedRoleIds);
		logger.info("------根据id查询系统用户方法结束----");
		return "user/editSysUser";
	}
	
	//根据id查询系统用户
		@RequestMapping(value="getSysUserByUserCode")
		public String getSysUserByUserCode(HttpServletRequest request,HttpServletResponse response){
			logger.info("------根据id查询系统用户方法开始----");
			String sysUserId = request.getParameter("userCode");
			List<SysUser> sysUserList = sysUserServiceImpl.getSysUserById(sysUserId);
			SysUser user = null;
			if(null!=sysUserList&&sysUserList.size()>0&&!sysUserList.isEmpty()){
				user = sysUserList.get(0);
			}
			String selectedRoleNames= roleServiceImpl.getRoleNamesByUserId(sysUserId);
			if(StringUtils.isNotBlank(selectedRoleNames)){
				selectedRoleNames = selectedRoleNames.substring(0,selectedRoleNames.length()-1);//样式转换，去除最后一个字符
			}else{
				selectedRoleNames="";
				logger.info("用户关联的角色信息为空");
				}
			String selectedRoleIds = roleServiceImpl.getRoleIdByUserId(sysUserId);
			if(StringUtils.isNotBlank(selectedRoleIds)){
				selectedRoleIds = selectedRoleIds.substring(0,selectedRoleIds.length()-1);//样式转换，去除最后一个字符
			}else{
				selectedRoleIds="";
				logger.info("用户关联的角色信息为空");
			}
			request.setAttribute("user", user);
			request.setAttribute("selectedRoleNames", selectedRoleNames);
			request.setAttribute("selectedRoleIds", selectedRoleIds);
			logger.info("------根据id查询系统用户方法结束----");
			return "user/sysUserDetails";
		}
	
	//根据登陆名查询系统用户
	@RequestMapping(value="getSysUserByLoginName")
	public ModelAndView getSysUserByLoginName(HttpServletRequest request,HttpServletResponse response,String loginName){
		logger.info("-------根据登陆名查询系统用户方法开始,用户名:---"+loginName);
		int page = Integer.parseInt(request.getParameter("page"));//当前页数
		int  rows =Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page*rows-rows; //分页查询开始位置
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginName", loginName);
		map.put("rows", rows);
		map.put("startNum", startNum);
		List<SysUser> userList = sysUserServiceImpl.findSysUserByLoginName(map);
		int recordSize = sysUserServiceImpl.findSysUserByLoginNameCount(map);
		JSONArray json = new JSONArray();
        if(null!=userList && userList.size() > 0){
            for (SysUser user:userList) {
              JSONObject cell = new JSONObject();
              cell.put("id",user.getId());
              cell.put("loginName",user.getLoginName());
              cell.put("email",user.getEmail());
              cell.put("userName",user.getUserName());
              cell.put("createDate",DateUtil.getDate(user.getCreateDate()));
              json.add(cell);
          }
        }
        //处理json数据
        BaseController.processJson(response,recordSize,json);
		return null;
	}
	
	//查询所有角色
	@RequestMapping(value="getAllRoles")
	public ModelAndView getAllRoles(HttpServletResponse response){
		logger.info("------查询所有角色方法开始-------");
		List<Role> roles = roleServiceImpl.getAllRoles();
		//遍历用户信息组装json数据
		JSONArray json = new JSONArray();
		int recordSize = roleServiceImpl.queryRoleTotalCount();
	     if(null!=roles && roles.size() > 0){
	         for (Role role:roles) {
	           JSONObject cell = new JSONObject();
	           cell.put("id",role.getId());
	           cell.put("roleName",role.getRoleName());
	           json.add(cell);
	       }
	     }
	     //处理json数据
	     BaseController.processJson(response,recordSize,json);
		logger.info("------查询所有角色方法结束-------"+roles);
		return null;
	}
	
	//获取角色tree
	@RequestMapping(value="getRoleTreeData")
	public void getRoleTreeData(HttpServletRequest request,HttpServletResponse response,String userCode){
		String treeData = "";
		List<Role> checkedRoleList=null;
		String checkedRoleId=request.getParameter("checkedRoleId");
		if(StringUtils.isNotBlank(checkedRoleId)){//输入框选中的角色ID
				checkedRoleList = roleServiceImpl.getRoleByRoleIds(checkedRoleId);
		}else{
			if(StringUtils.isNotBlank(userCode)){//修改用户获取roleTree
				checkedRoleList = roleServiceImpl.getRoleByUserId(userCode);
			}
		}
		//新增用户获取roleTree
		treeData = roleServiceImpl.createRoleTree(checkedRoleList);
		try {
			PrintWriter out = response.getWriter();
			out.print(treeData);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("获取角色tree时,处理json数据报错：" + e.getStackTrace());
		}
	}
	
	//登录名唯一性验证
	@RequestMapping(value="checkLoginName")
	public void checkLoginName(HttpServletRequest request,HttpServletResponse response,String loginName,String userCode){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("loginName", loginName);
		List<SysUser> userList = new ArrayList<SysUser>();
		String b = "0";
		if(StringUtils.isBlank(userCode)){//新增系统用户时验证
			userList = sysUserServiceImpl.checkLoginName(map);
			 if(null!=userList&userList.size()>0){
				 b = "1";
			 }
		}else{//修改合作商时验证
			userList = sysUserServiceImpl.checkLoginName(map);
			 if(null!=userList&userList.size()>0){
				 if(!userList.get(0).getUserCode().equals(userCode)){
					 b = "1";
				 }
			 }
		}
		BaseController.processPrintStr(response, b);
	}
	
}
