package com.shop.module.privilege.dao.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.shop.module.privilege.dao.mapper.RoleMapper;
import com.shop.module.privilege.model.Role;
/**
 * 角色dao
 * @author caryCheng
 *
 */
@Repository
public class RoleDaoImpl extends SqlSessionDaoSupport implements RoleMapper {
	
	String nameSpace = "com.shop.module.privilege.dao.mapper.RoleMapper";
	/**
	 * 获取nameSpace
	 * @return
	 */
	public String getMybatisMapperNamesapce() {
		return this.nameSpace;
	}
	/**
	 * 新增角色
	 * @param map
	 * @return
	 */
	public int addRole(Map<String,Object> map){
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).addRole(map);
	}
	
	/**
	 * 插入角色
	 */
	public int insertRole(Role role) {
		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).insertRole(role);
	}
	/**
	 * 更新角色
	 */
	public void updateRole(Role role) {
		this.getSqlSessionTemplate().getMapper(RoleMapper.class).updateRole(role);
		
	}
	/**
	 * 删除角色
	 */
	public void deleteRole(String id) {
		this.getSqlSessionTemplate().getMapper(RoleMapper.class).deleteRole(id);
		
	}
	/**
	 * 统计角色数量
	 */
	public int getRoleTotalCount() {
		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getRoleTotalCount();
	}
	/**
	 * 查询所有角色列表
	 */
	public List<Role> queryAllRoleList(int startNum, int rp) {	
		RowBounds rowBounds = new RowBounds(startNum,rp);
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".queryAllRoleList", new HashMap<String,Object>(), rowBounds);
	}
    /**
     * 通过角色名称获取角色
     */
	public List<Role> getRoleByRoleName(Map<String,Object> map,int startNum, int rp){
		RowBounds rowBounds = new RowBounds(startNum,rp);
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getRoleByRoleName", map, rowBounds);
	}
	/**
	 * 根据角色名称统计角色数量
	 */
	public int getRolesTotalCountByRoleName(Map<String,Object> map){		
		return  this.getSqlSessionTemplate().getMapper(RoleMapper.class).getRolesTotalCountByRoleName(map);
	}
	
	/**
	 * 根据id查询角色
	 */
	public Role getRoleById(String roleId) {		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getRoleById(roleId);
	}
	
	/**
	 * 查询所有角色
	 */
	public List<Role> getAllRoles() {
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getAllRoles();
	}
	
	/**
	 * 根据用户id查询用户关联的角色信息
	 */
	public List<Role> getRolesByUserId(String sysUserId) {
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getRolesByUserId(sysUserId);
	}
	/**
	 * 删除角色权限
	 */
	public int deleteRoleAuthByRoleCode(Map<String, Object> map) {		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).deleteRoleAuthByRoleCode(map);
	}
	/**
	 * 根据角色code查询角色拥有的权限
	 */
	public List<Map<String, Object>> getCheckedAuthIds(Map<String, Object> map) {		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getCheckedAuthIds(map);
	}
	/**
	 * 验证用户名是否存在
	 */
	public int validateRoleName(Map<String, Object> map) {		
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).validateRoleName(map);
	}
	
	public List<Role> getRoleByRoleIds(Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(RoleMapper.class).getRoleByRoleIds(map);
	}

}
