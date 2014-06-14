package com.shop.module.privilege.service.inter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shop.module.privilege.model.Role;

/**
 * 角色管理Service接口
 * @author Administrator
 * 
 */
public interface RoleService {
	/**
	 * 查询角色列表
	 * 
	 * @param startNum
	 * @param rp
	 * @return
	 */
	public List<Role> quryAllRoleList(int startNum, int rp);

	/**
	 * 查询角色所有数量
	 * 
	 * @return
	 */
	public int queryRoleTotalCount();
	
	/**
	 * 新增角色
	 * @param map
	 * @return
	 */
	public void saveRole(HttpServletRequest request,Role role,String ids) throws Exception;

	/**
	 * 查询所有角色
	 * @return
	 */
	public List<Role> getAllRoles();
	
	/**
	 * 生产角色树
	 */
	public String createRoleTree(List<Role> checkedRoleList);
	
	/**
	 * 根据用户id查询用户关联的角色名称信息
	 */
	public String getRoleNamesByUserId(String sysUserId);

	/**
	 * 根据用户id查询用户关联的角色id信息
	 */
	public String getRoleIdByUserId(String sysUserId);

	/**
	 * 根据roleIds查询角色列表
	 */
	public List<Role> getRoleByRoleIds(String roleIds);
	
	/**
	 * 根据用户id查询用户关联的角色信息
	 */
	public List<Role> getRoleByUserId(String userId);
	/**
	 * 根据id获取角色对象
	 * @param id
	 * @return
	 */
	public Role getRoleById(String id);
	/**
	 * 删除角色
	 * @param ids
	 * @throws Exception
	 */
	public void deleteRoles(String ids)throws Exception;
	/**
	 * 根据角色名称查询角色
	 * @param roleName
	 * @return
	 */
	public List<Role> getRoleByRoleName(String roleName,int startNum, int rp);
	/**
	 * 根据角色名称统计角色数量
	 * @param roleName
	 * @return
	 */
	public int getRolesTotalCountByRoleName(String roleName);
	/**
	 * 通过角色code获取角色拥有的权限
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCheckedAuthIds(String id);
	/**
	 * 验证角色名称是否存在
	 * @param roleName
	 * @return
	 */
	public int valiedateRoleName(String roleName);
}
