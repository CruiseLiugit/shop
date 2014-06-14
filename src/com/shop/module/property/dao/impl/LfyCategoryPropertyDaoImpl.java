package com.shop.module.property.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.dao.mapper.LfyCategoryPropertyMapper;
import com.shop.module.property.model.LfyCategoryProperty;

@Repository
public class LfyCategoryPropertyDaoImpl extends BaseMybatisDao<SysUser, java.lang.String> implements LfyCategoryPropertyMapper{
	
	public String nameSpace = "com.shop.module.property.dao.mapper.LfyCategoryPropertyMapper";

	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}
	
	@Override
	public void insertCategoryProperty(LfyCategoryProperty categoryProperty) {
		this.getSqlSessionTemplate().getMapper(LfyCategoryPropertyMapper.class).insertCategoryProperty(categoryProperty);
	}

	@Override
	public void deleteCategoryProperty(String categoryPropertyCode) {
		this.getSqlSessionTemplate().getMapper(LfyCategoryPropertyMapper.class).deleteCategoryProperty(categoryPropertyCode);
	}

	@Override
	public void updateCategoryProperty(LfyCategoryProperty categoryProperty) {
		this.getSqlSessionTemplate().getMapper(LfyCategoryPropertyMapper.class).updateCategoryProperty(categoryProperty);
		
	}

	@Override
	public LfyCategoryProperty getCategoryPropertybyCode(
			String categoryPropertyCode) {
		List<LfyCategoryProperty> categoryPropertys=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getCategoryPropertybyCode",categoryPropertyCode);
		if(categoryPropertys!=null&&categoryPropertys.size()>0){
			return categoryPropertys.get(0);
		}
		return null;
	}
	
	

	@Override
	public void updateAllPropertyByCategoryCode(String categoryCode) {
		this.getSqlSessionTemplate().getMapper(LfyCategoryPropertyMapper.class).updateAllPropertyByCategoryCode(categoryCode);
	}

	@Override
	public List<LfyCategoryProperty> getCategoryPropertyList(Map<String, Object> map) {
		return this.getSqlSessionTemplate().getMapper(LfyCategoryPropertyMapper.class).getCategoryPropertyList(map);
	}

	@Override
	public int getCategoryPropertyList_count(Map<String, Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".getCategoryPropertyList_count",map);
		return count==null?0:(Integer)count;
	}

	@Override
	public LfyCategoryProperty getCategoryProperty(Map<String, Object> map) {
		List<LfyCategoryProperty> categoryPropertys=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getCategoryProperty",map);
		if(categoryPropertys!=null&&categoryPropertys.size()>0){
			return categoryPropertys.get(0);
		}
		return null;
	}
	
	

	
}
