package com.shop.module.property.service.inter;

import java.util.List;
import java.util.Map;

import com.shop.module.property.model.LfyProperty;

public interface LfyPropertyService {
	public void insertProperty(LfyProperty property);
	public void updateProperty(LfyProperty property);
	public void deletePropertyByCode(String propertyCode);
	public LfyProperty findPropertyByCode(String propertyCode);
	public List<LfyProperty> findPropertys(Map<String,Object> map);
	public int findPropertys_count(Map<String,Object> map);
	public List<LfyProperty> findPropertysByCategoryCode(String categoryCode);
	public int findPropertyUserCount(String propertyCode);
	public int deletePropertys(String[] propertyCodes);
}
