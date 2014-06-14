package com.shop.module.privilege.dao.mapper;

import com.shop.module.privilege.model.UserRole;

/**
 * 
 * @Title :  UserRoleMapper.java
 * @author:  花崴 
 * @Description:用户角色中间表关系Mapper层
 * @date  :Oct 22, 2013  5:22:28 PM
 */

public interface UserRoleMapper {

	/**
	 * 保存用户角色中间表关系
	 * @param userRole
	 */
	public void saveUserRole(UserRole userRole);

	/**
	 * 删除用户角色中间表关系
	 * @param string
	 */
	public void deleteUserRoleByUserCode(String code);
}
