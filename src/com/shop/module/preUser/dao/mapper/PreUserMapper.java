package com.shop.module.preUser.dao.mapper;

import java.util.List;
import java.util.Map;

import com.shop.module.preUser.model.PreUser;

public interface PreUserMapper {
	/**
	 * 新增用户
	 * @param preUser
	 */
	public void savePreUser(PreUser preUser);
	
	/**
	 * 修改用户
	 * @param preUser
	 * @return
	 */
	public int updatePreUser(PreUser preUser);
	
	/**
	 * 删除用户
	 * @param preUserCode
	 * @return
	 */
	public int deletePreUserByCode(String preUserCode);
	
	/**
	 * 根绝用户code查询用户
	 * @param preUserCode
	 * @return
	 */
	public PreUser findPreUserByUserCode(String preUserCode);
	
	/**
	 * 查询一定条件下的用户(包含分页)
	 * @param map
	 * @return
	 */
	public List<PreUser> findPreUsers(Map<String,Object> map);
	
	/**
	 * 查询一定条件下的用户数量(包含分页)
	 * @param map
	 * @return
	 */
	public int findPreUsersCount(Map<String,Object> map);
	
	/**
	 * 检查用户名唯一性
	 * @param username
	 * @return
	 */
	public int checkUserName(Map<String,Object> map);
}
