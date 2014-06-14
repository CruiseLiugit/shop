package com.shop.module.preUser.service.inter;

import java.util.List;
import java.util.Map;

import com.shop.module.preUser.model.PreUser;

/**
 * 用户service接口
 * @author miaohanbin
 *
 */
public interface PreUserService {
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
	public void updatePreUser(PreUser preUser);
	
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
	 * 查看用户名的唯一性
	 * @param map
	 * @return
	 */
	public boolean checkUserName(String username,String preUserCode);
	
	/**
	 * 删除用户
	 * @param ids
	 */
	public void deletePreUsers(String ids);
}
