package com.shop.module.property.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.dao.mapper.LfyPropertyValueMapper;
import com.shop.module.property.model.LfyPropertyValue;
@Repository
public class LfyPropertyValueDaoImpl extends BaseMybatisDao<SysUser, java.lang.String> implements LfyPropertyValueMapper {
	public String nameSpace = "com.shop.module.property.dao.mapper.LfyPropertyValueMapper";

	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}
	
	@Override
	public void insertPropertyValue(LfyPropertyValue propertyValue) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).insertPropertyValue(propertyValue);
	}

	@Override
	public void updatePropertyValue(LfyPropertyValue propertyValue) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).updatePropertyValue(propertyValue);
	}

	@Override
	public void deletePropertyValue(LfyPropertyValue propertyValue) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).deletePropertyValue(propertyValue);
	}

	@Override
	public void updateValuesByCategoryPropertyId(String categoryPropertyCode) {
		this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).updateValuesByCategoryPropertyId(categoryPropertyCode);
	}

	@Override
	public LfyPropertyValue getPropertyValueByCode(String pvCode) {
		List<LfyPropertyValue> propertyValues=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getPropertyValueByCode",pvCode);
		if(propertyValues!=null&&propertyValues.size()>0){
			return propertyValues.get(0);
		}
		return null;
	}

	@Override
	public List<LfyPropertyValue> getPropertyValueList(Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).getPropertyValueList(map);
	}

	@Override
	public List<LfyPropertyValue> getAllPropertyValueListByCategoryProperty(
			String categoryPropertyCode) {
		return this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).getAllPropertyValueListByCategoryProperty(categoryPropertyCode);
	}

	@Override
	public List<LfyPropertyValue> getTicketsValueByNameAndCategoryproperty(
			Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).getTicketsValueByNameAndCategoryproperty(map);
	}

	@Override
	public int getPropertyValueList_count(Map<String, Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".getPropertyValueList_count",map);
		return count==null?0:(Integer)count;
	}

	@Override
	public List<LfyPropertyValue> getPropertyValueByCategoryCodeProperty(
			Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(LfyPropertyValueMapper.class).getPropertyValueByCategoryCodeProperty(map);
	}

}
