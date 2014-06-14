package com.shop.module.privilege.dao.mapper;

import java.util.List;
import java.util.Map;

import com.shop.module.privilege.model.Role;

/**
 * 角色Mapper
 * 
 * @author caryCheng
 * 
 */

public interface RoleMapper {
	/**
	 * 新增角色
	 * @param map
	 * @return
	 */
	public int addRole(Map<String,Object> map);
	
	/**
	 * 插入角色
	 * 
	 * @param role
	 * @return
	 */
	public int insertRole(Role role);

	/**
	 * 更新角色
	 * 
	 * @param role
	 */
	public void updateRole(Role role);

	/**
	 * 删除角色
	 * 
	 * @param role
	 */
	public void deleteRole(String id);

	/**
	 * 统计所有角色数量
	 * 
	 * @return
	 */
	public int getRoleTotalCount();
	/**
	 * 通过角色名称获取角色
	 * @param map
	 * @return
	 */
	public List<Role> getRoleByRoleName(Map<String,Object> map,int startNum,int rp);
	/**
	 * 根据角色名称统计角色数量
	 * @param map
	 * @return
	 */
	public int getRolesTotalCountByRoleName(Map<String,Object> map);

	/**
	 * 分页查询获取所有角色
	 * 
	 * @param startNum
	 * @param rp
	 * @return
	 */
	public List<Role> queryAllRoleList(int startNum, int rp);

	/**
	 * 通过id获取角色对象
	 * 
	 * @param roleId
	 * @return
	 */
	public Role getRoleById(String id);

	/**
	 * 查询所有角色
	 * @return
	 */
	List<Role> getAllRoles();

	/**
	 * 根据用户id查询用户关联的角色信息
	 */
	public List<Role> getRolesByUserId(String sysUserId);
	
	/**
	 * 删除角色权限
	 * @param map
	 * @return
	 */
    public int deleteRoleAuthByRoleCode(Map<String,Object> map);
    /**
     * 根据角色code查询角色拥有的权限
     * @param map
     * @return
     */
    public List<Map<String,Object>> getCheckedAuthIds(Map<String,Object> map);
    /**
     * 验证角色名称是否存在
     * @param map
     * @return
     */
    public int validateRoleName(Map<String,Object> map);
    /**
	 * 根据map查询角色列表
	 */
	public List<Role> getRoleByRoleIds(Map<String, Object> map);
}
