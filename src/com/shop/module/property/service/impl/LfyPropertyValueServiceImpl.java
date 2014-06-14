package com.shop.module.property.service.impl;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.dao.mapper.LfyCategoryPropertyMapper;
import com.shop.module.property.dao.mapper.LfyPropertyMapper;
import com.shop.module.property.dao.mapper.LfyPropertyValueMapper;
import com.shop.module.property.model.LfyCategoryProperty;
import com.shop.module.property.model.LfyProperty;
import com.shop.module.property.model.LfyPropertyValue;
import com.shop.module.property.service.inter.LfyPropertyValueService;
@Service
public class LfyPropertyValueServiceImpl implements LfyPropertyValueService {
	@Autowired
	private LfyPropertyValueMapper lfyPropertyValueDao;
	@Autowired
	private LfyPropertyMapper lfyPropertyDao;
	@Autowired
	private LfyCategoryPropertyMapper lfyCategoryPropertyDao;
	
	@Override
	public void insertPropertyValue(LfyPropertyValue propertyValue) {
		lfyPropertyValueDao.insertPropertyValue(propertyValue);
	}

	@Override
	public void updatePropertyValue(LfyPropertyValue propertyValue) {
		lfyPropertyValueDao.updatePropertyValue(propertyValue);
	}

	@Override
	public void deletePropertyValue(LfyPropertyValue propertyValue) {
		lfyPropertyValueDao.deletePropertyValue(propertyValue);
	}

	@Override
	public void updateValuesByCategoryPropertyId(String categoryPropertyCode) {
		lfyPropertyValueDao.updateValuesByCategoryPropertyId(categoryPropertyCode);
	}

	@Override
	public LfyPropertyValue getPropertyValueByCode(String pvCode) {
		return lfyPropertyValueDao.getPropertyValueByCode(pvCode);
	}

	@Override
	public List<LfyPropertyValue> getPropertyValueList(Map<String, Object> map) {
		return lfyPropertyValueDao.getPropertyValueList(map);
	}

	@Override
	public List<LfyPropertyValue> getAllPropertyValueListByCategoryProperty(
			String categoryPropertyCode) {
		return lfyPropertyValueDao.getAllPropertyValueListByCategoryProperty(categoryPropertyCode);
	}

	@Override
	public List<LfyPropertyValue> getTicketsValueByNameAndCategoryproperty(
			Map<String, Object> map) {
		return lfyPropertyValueDao.getTicketsValueByNameAndCategoryproperty(map);
	}

	@Override
	public int getPropertyValueList_count(Map<String, Object> map) {
		return lfyPropertyValueDao.getPropertyValueList_count(map);
	}

	@Override
	public List<LfyPropertyValue> getPropertyValueByCategoryCodeProperty(
			Map<String, Object> map) {
		return lfyPropertyValueDao.getPropertyValueByCategoryCodeProperty(map);
	}

	@Override
	public String to_JSON(String categoryCode) {
		StringBuffer buffer=new StringBuffer("[");
		List<LfyProperty> ticketsPropertys=lfyPropertyDao.findPropertysByCategoryCode(categoryCode);
		if(ticketsPropertys!=null&&ticketsPropertys.size()>0){
			for(LfyProperty property:ticketsPropertys){
				Map<String,Object> map=new HashMap<String,Object>();
				map.put("status", "1");
				map.put("propertyCode", property.getPropertyCode());
				map.put("categoryCode", categoryCode);
				LfyCategoryProperty categoryProperty=lfyCategoryPropertyDao.getCategoryProperty(map);
				if(categoryProperty==null){
					continue;
				}
				String categoryPropertyCode=categoryProperty.getCategoryPropertyCode();
				List<LfyPropertyValue> values=lfyPropertyValueDao.getAllPropertyValueListByCategoryProperty(categoryPropertyCode);
				StringBuffer builder=new StringBuffer("");
				if(values!=null&&values.size()>0){
					for(LfyPropertyValue value:values){
						builder.append(value.getShowName()+",");
					}
					String str=builder.substring(0, builder.length()-1);
					builder=new StringBuffer(str);
				}
				buffer.append("{");
				buffer.append("\"0\":\""+property.getPropertyCode()+"\",");
				buffer.append("\"1\":\""+categoryCode+"\",");
				buffer.append("\"id\":\""+categoryPropertyCode+"\",");
				buffer.append("\"name\":\""+property.getShowName()+"\",");
				String type="";
				if(property.getPropertyType().equals("2")){
					type="价格区间";
				}else{
					type="字符串";
				}
				buffer.append("\"type\":\""+type+"\",");
				buffer.append("\"categoryId\":\""+categoryCode+"\",");
				buffer.append("\"showName\":\""+property.getShowName()+"\",");
				buffer.append("\"propertyValue\":\""+builder.toString()+"\",");
				buffer.append("\"node\":\"property\",");
				buffer.append("\"state\":\"open\"");
				buffer.append("},");
			}
			String str=buffer.substring(0, buffer.length()-1);
			buffer=new StringBuffer(str);
		}
		buffer.append("]");
		return buffer.toString();
	}

	@Override
	public void addCategoryPropertyValue(HttpServletRequest request,
			String categoryCode, String propertyCode, Enumeration<String> em,
			SysUser user) {
		if(categoryCode!=null&&propertyCode!=null){
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("categoryCode", categoryCode);
			map.put("propertyCode", propertyCode);
			LfyCategoryProperty cp=lfyCategoryPropertyDao.getCategoryProperty(map);

				String categoryPropertyCode=cp.getCategoryPropertyCode();
				while(em.hasMoreElements()){//解析修改界面表单
					String argument=em.nextElement();
					if(argument.charAt(0)=='t'){
						String shownameStr="r"+argument.substring(1);
						String pvName=request.getParameter(argument);
						Map<String,Object> map1=new HashMap<String,Object>();
						map1.put("categoryPropertyCode", categoryPropertyCode);
						map1.put("pvName", pvName);
						List<LfyPropertyValue> values=lfyPropertyValueDao.getTicketsValueByNameAndCategoryproperty(map1);
						if(values==null||values.size()==0){//插入的数据数据库不存在
							LfyPropertyValue lcTicketsPropertyValue=new LfyPropertyValue();
							lcTicketsPropertyValue.setPvName(pvName);
							lcTicketsPropertyValue.setPvOrder(Integer.parseInt(argument.substring(1)));
							lcTicketsPropertyValue.setShowName(request.getParameter(shownameStr));
							lcTicketsPropertyValue.setStatus("1");
							String id=UUID.randomUUID().toString();
							lcTicketsPropertyValue.setPvCode(id);
							lcTicketsPropertyValue.setCategoryPropertyCode(categoryPropertyCode);
							lcTicketsPropertyValue.setPvtype(lfyPropertyDao.findPropertyByCode(propertyCode).getPropertyType());
							lfyPropertyValueDao.insertPropertyValue(lcTicketsPropertyValue);
						}else{
							if(!values.get(0).getStatus().equals("1")){
								values.get(0).setStatus("1");
								lfyPropertyValueDao.updatePropertyValue(values.get(0));;
							}
						}
						
				}
			}
		}
		
	}
	
	
}
