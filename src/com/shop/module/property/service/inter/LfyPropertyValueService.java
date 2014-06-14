package com.shop.module.property.service.inter;

import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.model.LfyPropertyValue;

public interface LfyPropertyValueService {
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
	
	public String to_JSON(String categoryCode);
	
	public void addCategoryPropertyValue(HttpServletRequest request,String categoryCode,String propertyCode,Enumeration<String> em,SysUser user);
}
