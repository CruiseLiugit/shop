package com.shop.module.property.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.module.property.dao.mapper.LfyPropertyMapper;
import com.shop.module.property.model.LfyProperty;
import com.shop.module.property.service.inter.LfyPropertyService;
@Service
public class LfyPropertyServiceImpl implements LfyPropertyService {
	@Autowired
	private LfyPropertyMapper lfyPropertyDao;
	@Override
	public void insertProperty(LfyProperty property) {
		lfyPropertyDao.insertProperty(property);
	}

	@Override
	public void updateProperty(LfyProperty property) {
		lfyPropertyDao.updateProperty(property);
	}

	@Override
	public void deletePropertyByCode(String propertyCode) {
		lfyPropertyDao.deletePropertyByCode(propertyCode);
	}

	@Override
	public LfyProperty findPropertyByCode(String propertyCode) {
		return lfyPropertyDao.findPropertyByCode(propertyCode);
	}

	@Override
	public List<LfyProperty> findPropertys(Map<String, Object> map) {
		return lfyPropertyDao.findPropertys(map);
	}

	@Override
	public int findPropertys_count(Map<String, Object> map) {
		return lfyPropertyDao.findPropertys_count(map);
	}

	@Override
	public List<LfyProperty> findPropertysByCategoryCode(String categoryCode) {
		return lfyPropertyDao.findPropertysByCategoryCode(categoryCode);
	}

	@Override
	public int findPropertyUserCount(String propertyCode) {
		return lfyPropertyDao.findPropertyUserCount(propertyCode);
	}

	@Override
	public int deletePropertys(String[] propertyCodes) {
		int flag=0;
		if(propertyCodes!=null){
			try{
				for(int i=0;i<propertyCodes.length;i++){
					int count=lfyPropertyDao.findPropertyUserCount(propertyCodes[i]);
					if(count>0){
						throw new Exception("该属性已经被使用不能删除");
					}
					lfyPropertyDao.deletePropertyByCode(propertyCodes[i]);
				}
			}catch(Exception e){
				e.printStackTrace();
				flag=-1;
			}
		}
		return flag;
	}
	
}
