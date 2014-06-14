package com.shop.module.property.dao.mapper;

import java.util.List;
import java.util.Map;

import com.shop.module.property.model.LfyPropertyValue;

public interface LfyPropertyValueMapper {
	public void insertPropertyValue(LfyPropertyValue propertyValue);
	
	public void updatePropertyValue(LfyPropertyValue propertyValue);
	
	public void deletePropertyValue(LfyPropertyValue propertyValue);
	
	public void updateValuesByCategoryPropertyId(String categoryPropertyCode);
	
	public LfyPropertyValue  getPropertyValueByCode(String pvCode);
	
	public List<LfyPropertyValue> getPropertyValueList(Map<String,Object> map);
	
	public List<LfyPropertyValue> getAllPropertyValueListByCategoryProperty(String categoryPropertyCode);
	
	public List<LfyPropertyValue> getTicketsValueByNameAndCategoryproperty(Map<String,Object> map);
	
	public int getPropertyValueList_count(Map<String,Object> map);
	
	public List<LfyPropertyValue> getPropertyValueByCategoryCodeProperty(Map<String,Object> map);

}
