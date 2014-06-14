package com.shop.module.privilege.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.privilege.dao.mapper.SysUserMapper;
//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;

@Repository
public class SysUserDaoImpl extends BaseMybatisDao<SysUser, java.lang.String> implements SysUserMapper{
	
	public String nameSpace = "com.shop.module.privilege.dao.mapper.SysUserMapper";
	
	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}
	
	/**
	 * 根据id删除用户
	 * @param sysUser
	 * @return
	 */
	public int deleteSysUserById(String sysUserId) {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).deleteSysUserById(sysUserId);
	}

	/**
	 * 根据登陆名和密码查当前用户
	 * @param map
	 * @return
	 */
	public SysUser findSysUser(Map<String, Object> map) {
		return (SysUser) this.getSqlSessionTemplate().selectOne(this.nameSpace + ".findSysUser", map);
	}
	
	/**
	 * 添加登陆日记
	 * @param map
	 * @return
	 */
	public int insertLoginLog(Map<String,Object> map) throws Exception{
		return this.getSqlSessionTemplate().insert(this.nameSpace + ".insertLoginLog", map);
	}
	
	/**
	 * 根据usercode修改用户登陆密码
	 * @param map
	 * @return
	 * @throws Exception
	 */
	public int updateLoginPwd(Map<String,Object> map) throws Exception{
		return this.getSqlSessionTemplate().update(this.nameSpace + ".updateLoginPwd", map);
	}
	


	/**
	 *新增系统用户
	 * @param sysUser
	 */
	public void saveSysUser(SysUser sysUser) {
		this.getSqlSessionTemplate().getMapper(SysUserMapper.class).saveSysUser(sysUser);
	}

	/**
	 * 根据id修改用户
	 * @param sysUser
	 * @return
	 */
	public int updateSysUserById(SysUser sysUser) {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).updateSysUserById(sysUser);
	}
	
	
	/**
	 * 根据id查询用户
	 * @param sysUserId
	 * @return
	 */
	public List<SysUser> getSysUserById(String sysUserId) {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).getSysUserById(sysUserId);
	}

	public int getTotalCount() {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).getTotalCount();
	}

	/**
	 * 分页查询系统用户
	 */
	public List<SysUser> getAllSysUserList(int startNum, int rows) {
		RowBounds rowBounds = new RowBounds(startNum,rows);
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getAllSysUserList", new HashMap(), rowBounds);
	}

	/**
	 * 根据loginName查询系统用户
	 */
	public List<SysUser> findSysUserByLoginName(Map<String, Object> map) {
		RowBounds rowBounds = new RowBounds((Integer)map.get("startNum"),(Integer)map.get("rows"));
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".findSysUserByLoginName", map, rowBounds);
	}

	/**
	 * 根据loginName查询系统用户的总记录数
	 */
	public int findSysUserByLoginNameCount(Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).findSysUserByLoginNameCount(map);
	}

	
	/**
	 * 根据loginName验证登录名是否唯一
	 */
	public List<SysUser> checkLoginName(Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(SysUserMapper.class).checkLoginName(map);
	}

}
