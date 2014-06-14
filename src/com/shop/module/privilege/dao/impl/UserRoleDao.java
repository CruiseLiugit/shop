package com.shop.module.privilege.dao.impl;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.shop.module.privilege.dao.mapper.UserRoleMapper;
import com.shop.module.privilege.model.UserRole;



@Repository
public class UserRoleDao extends SqlSessionDaoSupport implements UserRoleMapper {
	String namespace="com.shop.module.privilege.dao.mapper.UserRoleMapper";
	
	public void deleteUserRoleByUserid(String string) {
		this.getSqlSessionTemplate().getMapper(UserRoleMapper.class).deleteUserRoleByUserCode(string);
	}
	
	public void saveUserRole(UserRole userRole) {
		this.getSqlSessionTemplate().getMapper(UserRoleMapper.class).saveUserRole(userRole);
	}

//	public List<Map> queryUsersByRoleName(String roleName) {
//		return this.getSqlSessionTemplate().getMapper(sysUserRoleMapper.class).queryUsersByRoleName(roleName);
//	}

	public void deleteUserRoleByUserCode(String code) {
		this.getSqlSessionTemplate().getMapper(UserRoleMapper.class).deleteUserRoleByUserCode(code);
	}

//	public String queryRoleCodeByuserName(String userName) {
//		return this.getSqlSessionTemplate().getMapper(sysUserRoleMapper.class).queryRoleCodeByuserName(userName);
//	}



	
	
}
