package com.shop.module.category.dao.mapper;

import java.util.List;
import java.util.Map;

import com.shop.module.category.model.Category;

/**
 * 
 * @author miaohanbin
 *
 */
public interface CategoryMapper {
	public void insertGoodsCategory(Category category);
	
	public void deleteGoodsCategory(String categoryCode);
	
	public void updateGoodsCategory(Category category);
	
	public Category getGoodsCategoryById(String categoryCode);
	
	public List<Category> getGoodsCategoryList(Map<String,Object> map);
	
	public int getGoodsCategoryList_count(Map<String,Object> map);
	
	//获取下级类目
	public List<Category> getGoodsCategoryByPCode(String categoryCode); 
	
	
}
