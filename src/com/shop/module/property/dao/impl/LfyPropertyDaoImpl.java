package com.shop.module.property.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.dao.mapper.LfyPropertyMapper;
import com.shop.module.property.model.LfyProperty;
@Repository
public class LfyPropertyDaoImpl extends BaseMybatisDao<SysUser, java.lang.String> implements LfyPropertyMapper {
	public String nameSpace = "com.shop.module.property.dao.mapper.LfyPropertyMapper";

	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}

	@Override
	public void insertProperty(LfyProperty property) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyMapper.class).insertProperty(property);
	}

	@Override
	public void updateProperty(LfyProperty property) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyMapper.class).updateProperty(property);
	}

	@Override
	public void deletePropertyByCode(String propertyCode) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyMapper.class).deletePropertyByCode(propertyCode);
	}

	@Override
	public LfyProperty findPropertyByCode(String propertyCode) {
		List<LfyProperty> propertys=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".findPropertyByCode", propertyCode);
		if(propertys!=null&&propertys.size()>0){
			return propertys.get(0);
		}
		return null;
	}

	@Override
	public List<LfyProperty> findPropertys(Map<String, Object> map) {
		if(map.containsKey("startNum")&&map.containsKey("rows")){
			RowBounds rowBounds = new RowBounds((Integer)map.get("startNum"),(Integer)map.get("rows"));
			return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".findPropertys", map, rowBounds);
		}else{
			return this.getSqlSessionTemplate().getMapper(LfyPropertyMapper.class).findPropertys(map);
		}
	}

	@Override
	public int findPropertys_count(Map<String, Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".findPropertys_count",map);
		return count==null?0:(Integer)count;
	}

	@Override
	public List<LfyProperty> findPropertysByCategoryCode(String categoryCode) {
		return this.getSqlSessionTemplate().getMapper(LfyPropertyMapper.class).findPropertysByCategoryCode(categoryCode);
	}

	@Override
	public int findPropertyUserCount(String propertyCode) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".findPropertyUserCount",propertyCode);
		return count==null?0:(Integer)count;
	}
	
	
	
}
