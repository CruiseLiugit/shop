package com.shop.module.privilege.dao.mapper;
import java.util.List;
import java.util.Map;

//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;

public interface SysUserMapper {

	/**
	 *新增系统用户
	 * @param sysUser
	 */
	public void saveSysUser(SysUser sysUser);
	
	/**
	 * 根据id修改用户
	 * @param sysUser
	 * @return
	 */
	public int updateSysUserById(SysUser sysUser);
	
	/**
	 * 根据id删除用户
	 * @param sysUser
	 * @return
	 */
	public int deleteSysUserById(String userId);
	
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
	public int insertLoginLog(Map<String,Object> map) throws Exception;
	
	/**
	 * 根据usercode修改用户登陆密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateLoginPwd(Map<String,Object> map) throws Exception;
	

	/**
	 * 根据id查询系统用户
	 * @param sysUserId
	 * @return
	 */
	public List<SysUser> getSysUserById(String sysUserId);

	/**
	 * 获取系统用户总记录数
	 * @return
	 */
	public int getTotalCount();

	/**
	 * 分页查询系统用户
	 * @param startNum
	 * @param rows
	 * @return
	 */
	public List<SysUser> getAllSysUserList(int startNum, int rows);

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
	 */
	public List<SysUser> checkLoginName(Map<String, Object> map);

}
