package com.shop.module.preUser.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.preUser.dao.mapper.PreUserMapper;
import com.shop.module.preUser.model.PreUser;
import com.shop.module.privilege.model.SysUser;
/**
 * 用户dao实现类
 * @author miaohanbin
 *
 */
@Repository
public class PreUserDaoImpl extends BaseMybatisDao<SysUser, java.lang.String> implements PreUserMapper {
	public String nameSpace = "com.shop.module.preUser.dao.mapper.SysUserMapper";
	
	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}
	@Override
	public void savePreUser(PreUser preUser) {
		this.getSqlSessionTemplate().getMapper(PreUserMapper.class).savePreUser(preUser);
	}

	@Override
	public int updatePreUser(PreUser preUser) {
		return this.getSqlSessionTemplate().getMapper(PreUserMapper.class).updatePreUser(preUser);
	}

	@Override
	public int deletePreUserByCode(String preUserCode) {
		return this.getSqlSessionTemplate().getMapper(PreUserMapper.class).deletePreUserByCode(preUserCode);
	}
	
	@Override
	public PreUser findPreUserByUserCode(String preUserCode) {
		List<PreUser> preUsers=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".findPreUserByUserCode",preUserCode);
		if(preUsers!=null&&preUsers.size()>0){
			return preUsers.get(0);
		}
		return null;
	}
	@Override
	public List<PreUser> findPreUsers(Map<String, Object> map) {
		RowBounds rowBounds = new RowBounds((Integer)map.get("startNum"),(Integer)map.get("rows"));
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".findPreUsers", map, rowBounds);
	}

	@Override
	public int findPreUsersCount(Map<String, Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".findPreUsersCount",map);
		return count==null?0:(Integer)count;
	}
	@Override
	public int checkUserName(Map<String,Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".checkUserName",map);
		return count==null?0:(Integer)count;
	}
	
	

}
