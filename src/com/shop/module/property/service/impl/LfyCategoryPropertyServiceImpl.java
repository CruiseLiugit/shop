package com.shop.module.property.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.module.property.dao.mapper.LfyCategoryPropertyMapper;
import com.shop.module.property.model.LfyCategoryProperty;
import com.shop.module.property.service.inter.LfyCategoryPropertyService;
@Service
public class LfyCategoryPropertyServiceImpl implements
		LfyCategoryPropertyService {
	@Autowired
	private LfyCategoryPropertyMapper lfyCategoryPropertyDao;
	@Override
	public void insertCategoryProperty(LfyCategoryProperty categoryProperty) {
		lfyCategoryPropertyDao.insertCategoryProperty(categoryProperty);
	}

	@Override
	public void deleteCategoryProperty(String categoryPropertyCode) {
		lfyCategoryPropertyDao.deleteCategoryProperty(categoryPropertyCode);
	}

	@Override
	public void updateCategoryProperty(LfyCategoryProperty categoryProperty) {
		lfyCategoryPropertyDao.updateCategoryProperty(categoryProperty);
	}

	@Override
	public LfyCategoryProperty getCategoryPropertybyCode(
			String categoryPropertyCode) {
		return lfyCategoryPropertyDao.getCategoryPropertybyCode(categoryPropertyCode);
	}

	@Override
	public List<LfyCategoryProperty> getCategoryPropertyList(
			Map<String, Object> map) {
		return lfyCategoryPropertyDao.getCategoryPropertyList(map);
	}

	@Override
	public int getCategoryPropertyList_count(Map<String, Object> map) {
		return lfyCategoryPropertyDao.getCategoryPropertyList_count(map);
	}

	@Override
	public void saveCategoryProperty(String categoryCode, String[] propertys,String userCode) {
		lfyCategoryPropertyDao.updateAllPropertyByCategoryCode(categoryCode);//将指定类目所有类目属性表记录状态都置为0，无效
		if(propertys!=null&&propertys.length>0){
			for(int i=0;i<propertys.length;i++){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("categoryCode", categoryCode);
				map.put("propertyCode", propertys[i]);
				LfyCategoryProperty cp=lfyCategoryPropertyDao.getCategoryProperty(map);
				if(cp!=null){//对于类目来说这个属性在中间表存在,直接修改状态位
					cp.setStatus("1");
					lfyCategoryPropertyDao.updateCategoryProperty(cp);
				}else{//不存在，需要手动插入
					LfyCategoryProperty c=new LfyCategoryProperty();
					c.setCreateDate(new Date());
					c.setStatus("1");
					c.setCreateOpid(userCode);
					c.setCategoryCode(categoryCode);
					c.setCategoryPropertyCode(UUID.randomUUID().toString());
					c.setPropertyCode(propertys[i]);
					lfyCategoryPropertyDao.insertCategoryProperty(c);
				}
			}
		}
		
	}

	@Override
	public void updateAllPropertyByCategoryCode(String categoryCode) {
		lfyCategoryPropertyDao.updateAllPropertyByCategoryCode(categoryCode);
	}
	
}
