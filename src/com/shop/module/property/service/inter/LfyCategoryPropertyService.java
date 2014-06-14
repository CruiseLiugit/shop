package com.shop.module.property.service.inter;

import java.util.List;
import java.util.Map;

import com.shop.module.property.model.LfyCategoryProperty;

public interface LfyCategoryPropertyService {
public void insertCategoryProperty(LfyCategoryProperty categoryProperty);
	
	public void deleteCategoryProperty(String categoryPropertyCode);
	
	public void updateCategoryProperty(LfyCategoryProperty categoryProperty);
	
	public LfyCategoryProperty getCategoryPropertybyCode(String categoryPropertyCode);
	
	public List<LfyCategoryProperty> getCategoryPropertyList(Map<String,Object> map);
	
	public int getCategoryPropertyList_count(Map<String,Object> map);
	
	public void saveCategoryProperty(String categoryCode,String[] propertys,String userCode);
	
	public void updateAllPropertyByCategoryCode(String categoryCode);
	
}
