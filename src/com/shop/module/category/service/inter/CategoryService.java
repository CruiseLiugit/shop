package com.shop.module.category.service.inter;

import java.util.List;
import java.util.Map;

import com.shop.module.category.model.Category;

public interface CategoryService {
	public void insertGoodsCategory(Category category);
	
	public void deleteGoodsCategory(String categoryCode); 
	
	public void updateGoodsCategory(Category category);
	
	public Category getGoodsCategoryById(String categoryCode);
	
	public List<Category> getGoodsCategoryList(Map<String,Object> map);
	
	public Category findRootCategory(String categoryCode);
	
	public void deleteCategory(String categoryCode);
	
	public String ListToJson(List<Category> categorys);
	
	public int getGoodsCategoryList_count(Map<String,Object> map);
	
	public String ListToJson1(List<Category> categorys);
	
	public String ListToJson2(List<Category> categorys);
	
	
}
