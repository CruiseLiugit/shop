package com.shop.module.category.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.common.util.DateUtil;
import com.shop.module.category.dao.mapper.CategoryMapper;
import com.shop.module.category.model.Category;
import com.shop.module.category.service.inter.CategoryService;
import com.shop.module.property.dao.mapper.LfyPropertyMapper;
import com.shop.module.property.model.LfyProperty;
@Service
public class CategoryServiceImpl implements CategoryService {
	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	@Autowired
	private CategoryMapper categoryDao;
	@Autowired
	private LfyPropertyMapper propertyDao;
	@Override
	public void insertGoodsCategory(Category category) {
		categoryDao.insertGoodsCategory(category);
	}
	/**
	 * 逻辑删除 只是修改状态
	 */
	@Override
	public void deleteGoodsCategory(String categoryCode) {
		Category goodsCategory=categoryDao.getGoodsCategoryById(categoryCode);
		if(goodsCategory!=null){
			if(goodsCategory.getChildGoodsgorys()==null||goodsCategory.getChildGoodsgorys().size()==0){
				goodsCategory.setStatus("0");
				categoryDao.updateGoodsCategory(goodsCategory);
			}else{
				List<Category> goodsCategorys=goodsCategory.getChildGoodsgorys();
				if(goodsCategorys!=null&&goodsCategorys.size()>0){
					for(Category gcatetory:goodsCategorys){
						gcatetory=categoryDao.getGoodsCategoryById(gcatetory.getCategoryCode());
						deleteGoodsCategory(gcatetory.getCategoryCode());//递归调用
					}
				}
			}
			goodsCategory.setStatus("0");
			categoryDao.updateGoodsCategory(goodsCategory);
		}
	}

	@Override
	public void updateGoodsCategory(Category category) {
		categoryDao.updateGoodsCategory(category);
	}

	@Override
	public Category getGoodsCategoryById(String categoryCode) {
		return categoryDao.getGoodsCategoryById(categoryCode);
	}

	@Override
	public List<Category> getGoodsCategoryList(Map<String, Object> map) {
		return categoryDao.getGoodsCategoryList(map);
	}

	
	@Override
	public int getGoodsCategoryList_count(Map<String, Object> map) {
		return categoryDao.getGoodsCategoryList_count(map);
	}

	@Override
	public Category findRootCategory(String categoryCode) {
		Category category=this.getGoodsCategoryById(categoryCode);
		if(category!=null){
			while(!category.getCategoryPcode().trim().equals("-1")){
				category=this.getGoodsCategoryById(category.getCategoryPcode());
			}
			return category;
		}else{
			return null;
		}
	}
	
	/**
	 * 彻底删除
	 */
	@Override
	public void deleteCategory(String categoryCode) {
		categoryDao.deleteGoodsCategory(categoryCode);
	}

	@Override
	public String ListToJson(List<Category> categorys) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		if(categorys!=null&&categorys.size()>0){
			for(int i=0;i<categorys.size();i++){
					buffer.append("{");
					buffer.append("\"0\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"1\":\""+categorys.get(i).getCategoryPcode()+"\",");
					buffer.append("\"id\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"option\":\"<a href='javascript:void(0);' onclick='reduce()'>还原</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='delIt()'>删除</a>\",");
					buffer.append("\"name\":\""+categorys.get(i).getCategoryName()+"\",");
					if(categorys.get(i).getCreateDate()==null){
						buffer.append("\"createDate\":\"\",");
					}else{
						buffer.append("\"createDate\":\""+DateUtil.convertDateToString(categorys.get(i).getCreateDate())+"\",");
					}
					buffer.append("\"order\":\""+categorys.get(i).getCategoryOrder()+"\",");
					buffer.append("\"showName\":\""+categorys.get(i).getShowName()+"\",");
					if(categorys.get(i).getChildGoodsgorys()!=null&&categorys.get(i).getChildGoodsgorys().size()>0){
						buffer.append("\"property\":\"\",");
						buffer.append("\"state\":\"closed\"");
					}else{//叶子节点
						String properList="";
						buffer.append("\"property\":\""+properList+"\",");
						buffer.append("\"state\":\"open\"");
					}
					buffer.append("},");	
			}
			String str=buffer.substring(0, buffer.length()-1);
			buffer=new StringBuffer(str);
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	@Override
	public String ListToJson1(List<Category> categorys){
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		if(categorys!=null&&categorys.size()>0){
			for(int i=0;i<categorys.size();i++){
					buffer.append("{");
					buffer.append("\"0\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"1\":\""+categorys.get(i).getCategoryPcode()+"\",");
					buffer.append("\"id\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"option\":\"<a href='javascript:void(0);' onclick='reduce()'>还原</a>&nbsp;&nbsp;<a href='javascript:void(0);' onclick='delIt()'>删除</a>\",");
					buffer.append("\"name\":\""+categorys.get(i).getCategoryName()+"\",");
					if(categorys.get(i).getCreateDate()==null){
						buffer.append("\"createDate\":\"\",");
					}else{
						buffer.append("\"createDate\":\""+DateUtil.convertDateToString(categorys.get(i).getCreateDate())+"\",");
					}
					buffer.append("\"order\":\""+categorys.get(i).getCategoryOrder()+"\",");
					buffer.append("\"showName\":\""+categorys.get(i).getShowName()+"\",");
					if(categorys.get(i).getChildGoodsgorys()!=null&&categorys.get(i).getChildGoodsgorys().size()>0){
						buffer.append("\"property\":\"\",");
						buffer.append("\"state\":\"closed\"");
					}else{//叶子节点
						List<LfyProperty> myPropertys=propertyDao.findPropertysByCategoryCode(categorys.get(i).getCategoryCode());
						String properList="";
						Map<String,Object> map1=new HashMap<String,Object>();
						map1.put("status", "1");
						List<LfyProperty> propertys=propertyDao.findPropertys(map1);
						if(propertys!=null){
							for(LfyProperty property:propertys){
								if(this.isExist(myPropertys, property)){
									properList+="<input type='checkbox'  disabled='disabled' checked='checked' />"+property.getShowName()+"&nbsp;&nbsp;";
								}else{
									properList+="<input type='checkbox' disabled='disabled' />"+property.getShowName()+"&nbsp;&nbsp;";
								}
							}
						}
						buffer.append("\"property\":\""+properList+"\",");
						buffer.append("\"state\":\"open\"");
					}
					buffer.append("},");	
			}
			String str=buffer.substring(0, buffer.length()-1);
			buffer=new StringBuffer(str);
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	
	@Override
	public String ListToJson2(List<Category> categorys) {
		StringBuffer buffer=new StringBuffer();
		buffer.append("[");
		if(categorys!=null&&categorys.size()>0){
			for(int i=0;i<categorys.size();i++){
					buffer.append("{");
					buffer.append("\"0\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"1\":\""+categorys.get(i).getCategoryPcode()+"\",");
					buffer.append("\"id\":\""+categorys.get(i).getCategoryCode()+"\",");
					buffer.append("\"name\":\""+categorys.get(i).getCategoryName()+"\",");
					buffer.append("\"showName\":\""+categorys.get(i).getShowName()+"\",");
					buffer.append("\"propertyValue\":\"\",");
					buffer.append("\"type\":\"\",");
					buffer.append("\"node\":\"category\",");
					buffer.append("\"state\":\"closed\"");
					buffer.append("},");			
			}
			String str=buffer.substring(0, buffer.length()-1);
			buffer=new StringBuffer(str);
		}
		buffer.append("]");
		return buffer.toString();
	}
	
	public boolean isExist(List<LfyProperty> propertys,LfyProperty property){
		if(propertys!=null&&property!=null){
			for(LfyProperty fp:propertys){
				if(fp.getPropertyCode().equals(property.getPropertyCode())){
					return true;
				}
			}
			return false;
		}
		return false;
	}

}
