package com.shop.module.privilege.service.inter;

import java.util.List;
import java.util.Map;

//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;

/**
 * @Title :  UserService.java
 * @author:  花崴 
 * @Description:系统用户管理模块Service接口层
 * @date  :Oct 17, 2013  12:36:24 PM
 */
public interface SysUserService {
	
	/**
	 *新增系统用户
	 * @param sysUser,
	 * @param relativedRoles 
	 * @throws Exception 
	 */
	public void saveSysUser(SysUser sysUser, String relativedRoles) throws Exception;
	
	/**
	 * 根据id修改用户
	 * @param sysUser
	 * @param relativedRoles 
	 * @return
	 * @throws Exception 
	 */
	public void updateSysUserById(SysUser sysUser, String relativedRoles) throws Exception;
	
	/**
	 * 根据id删除用户
	 * @param sysUser
	 * @return
	 * @throws Exception 
	 */
	public void deleteSysUserById(String sysUserId) throws Exception;
	
	/**
	 * 查询所有系统用户
	 * @param rows 
	 * @param startNum 
	 * @return
	 * @throws Exception 
	 */
	public List<SysUser> getAllSysUserList(int startNum, int rows);
	
	/**
	 * 根据登陆名和密码查当前用户
	 * @param map
	 * @return
	 */
	public SysUser findSysUser(Map<String,Object> map);
	
	/**
	 * 添加登陆日记
	 * @param map
	 * @return
	 */
	public int insertLoginLog(SysUser sysUser,String ip,String type) throws Exception;
	
	/**
	 * 根据usercode修改用户登陆密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateLoginPwd(Map<String,Object> map) throws Exception;
	
	
	/**
	 *加载当前用户的菜单
	 * @return
	 */
	public String loadMenus(SysUser sysUser);
	
	/**
	 * 根据id查询系统用户
	 * @param sysUserId
	 * @return
	 * @throws Exception 
	 */
	public List<SysUser> getSysUserById(String sysUserId);

	/**
	 * 获取用户列表总记录数
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 根据loginName查询系统用户
	 * @param map
	 * @return
	 */
	public List<SysUser> findSysUserByLoginName(Map<String, Object> map);
	
	/**
	 * 根据loginName查询系统用户的总记录数
	 * @param map
	 * @return
	 */
	public int findSysUserByLoginNameCount(Map<String, Object> map);

	/**
	 * 根据loginName验证登录名是否唯一
	 * @param map
	 * @return
	 */
	public List<SysUser> checkLoginName(Map<String, Object> map);
}
