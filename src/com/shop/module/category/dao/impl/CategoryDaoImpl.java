package com.shop.module.category.dao.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Repository;

import com.shop.common.base.BaseMybatisDao;
import com.shop.module.category.dao.mapper.CategoryMapper;
import com.shop.module.category.model.Category;
import com.shop.module.privilege.model.SysUser;

/**
 * 
 * @author miaohanbin
 * 
 */
@Repository
public class CategoryDaoImpl extends BaseMybatisDao<SysUser, java.lang.String>
		implements CategoryMapper {

	public String nameSpace = "com.shop.module.category.model.Category.CategoryMapper";

	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}

	@Override
	public void insertGoodsCategory(Category category) {
		this.getSqlSessionTemplate().getMapper(CategoryMapper.class)
				.insertGoodsCategory(category);
	}

	@Override
	public void deleteGoodsCategory(String categoryCode) {
		this.getSqlSessionTemplate().getMapper(CategoryMapper.class).deleteGoodsCategory(categoryCode);
	}

	@Override
	public void updateGoodsCategory(Category category) {
		this.getSqlSessionTemplate().getMapper(CategoryMapper.class).updateGoodsCategory(category);
	}

	@Override
	public Category getGoodsCategoryById(String categoryCode) {
		List<Category> categorys=this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getGoodsCategoryById", categoryCode);
		if(categorys!=null&&categorys.size()>0){
			return categorys.get(0);
		}
		return null;
	}

	@Override
	public List<Category> getGoodsCategoryList(Map<String,Object> map) {
		if(map.containsKey("startNum")&&map.containsKey("rows")){
			RowBounds rowBounds = new RowBounds((Integer)map.get("startNum"),(Integer)map.get("rows"));
			return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getGoodsCategoryList",map,rowBounds);
		}else{
			return this.getSqlSessionTemplate().getMapper(CategoryMapper.class).getGoodsCategoryList(map);
		}
	}

	@Override
	public int getGoodsCategoryList_count(Map<String,Object> map) {
		Object count=this.getSqlSessionTemplate().selectOne(getMybatisMapperNamesapce()+".getGoodsCategoryList_count",map);
		return count==null?0:(Integer)count;
	}
	
	@Override
	public List<Category> getGoodsCategoryByPCode(String categoryCode) {
		return this.getSqlSessionTemplate().getMapper(CategoryMapper.class).getGoodsCategoryByPCode(categoryCode);
	}

}
