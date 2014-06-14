package com.shop.module.property.action;

import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.common.util.ResponseUtils;
import com.shop.common.util.SysUserUtil;
import com.shop.module.category.model.Category;
import com.shop.module.category.service.inter.CategoryService;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.model.LfyCategoryProperty;
import com.shop.module.property.model.LfyProperty;
import com.shop.module.property.model.LfyPropertyValue;
import com.shop.module.property.service.inter.LfyCategoryPropertyService;
import com.shop.module.property.service.inter.LfyPropertyService;
import com.shop.module.property.service.inter.LfyPropertyValueService;

@Controller
@RequestMapping("/propertyValue")
public class LfyPropertyValueAction {
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private LfyPropertyService lfyPropertyService;
	@Autowired
	private LfyCategoryPropertyService categoryPropertyService;
	@Autowired
	private LfyPropertyValueService lfyPropertyValueService;
	
	@RequestMapping(value="queryCategoryProperty")
	public String queryCategoryProperty(HttpServletResponse response,String id,int page,int rows) throws Exception{
		StringBuffer buffer=new StringBuffer();
		int startNum = page * rows - rows; // 分页查询开始位置
		if(id==null||id.equals("-1")){//加载最上层类目
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("status", "1");
			map.put("categoryPcode", "-1");
			int count=categoryService.getGoodsCategoryList_count(map);
			buffer.append("{\"total\":"+count+",\"rows\":");
			map.put("startNum", startNum);
			map.put("rows", rows);
			List<Category> categorys=categoryService.getGoodsCategoryList(map);
			buffer.append(categoryService.ListToJson2(categorys));
			buffer.append("}");
		}else{//加载子目录
			Category goodsCategory=categoryService.getGoodsCategoryById(id);
			List<Category> categorys=goodsCategory.getChildGoodsgorys();
			if(categorys!=null&&categorys.size()>0){
				buffer.append(categoryService.ListToJson2(categorys));
			}else{//加载子目录的属性的属性值json信息
				buffer.append(lfyPropertyValueService.to_JSON(id));
			}
		}
		ResponseUtils.renderJson(response, buffer.toString());
		return null;
	}
	
	@RequestMapping(value="getValuesByCategoryProperty")
	public String getValuesByCategoryProperty(HttpServletResponse response,HttpServletRequest request,String categoryPropertyCode,String option){
		if(StringUtils.isNotBlank(categoryPropertyCode)){
			LfyCategoryProperty cateogryProperty=categoryPropertyService.getCategoryPropertybyCode(categoryPropertyCode);
			request.setAttribute("categoryPropertyCode", categoryPropertyCode);
			if(cateogryProperty!=null){
				Category category=categoryService.getGoodsCategoryById(cateogryProperty.getCategoryCode());
				LfyProperty property=lfyPropertyService.findPropertyByCode(cateogryProperty.getPropertyCode());
				List<LfyPropertyValue> categoryPropertyValues=lfyPropertyValueService.getAllPropertyValueListByCategoryProperty(categoryPropertyCode);
				request.setAttribute("category", category);
				request.setAttribute("property", property);
				request.setAttribute("categoryPropertyValues", categoryPropertyValues);
			}
		}
		if("edit".equals(option)){
			return "property/categoryPropertyValue-input";
		}else if("select".equals(option)){
			return "property/categoryPropertyValueDetails";
		}
		return "property/categoryPropertyValue-add";
	}
	
	@RequestMapping(value="updateCategoryPropertyValue")
	public String updateCategoryPropertyValue(String categoryCode,String propertyCode,String categoryPropertyCode,HttpServletRequest request,HttpServletResponse response) throws Exception{
		Enumeration<String> em= request.getParameterNames();
		SysUser sysUser=SysUserUtil.getLoginUser(request);
		StringBuffer buffer=new StringBuffer("");
		//先将之前的属性值置为无效
		lfyPropertyValueService.updateValuesByCategoryPropertyId(categoryPropertyCode);
		while(em.hasMoreElements()){//解析修改界面表单
			String argument=em.nextElement();
			if(argument.charAt(0)=='t'){
				String shownameStr="r"+argument.substring(1);
				String valueStr="h"+argument.substring(1);
				String valueId=request.getParameter(valueStr);
				LfyPropertyValue lcTicketsPropertyValue= lfyPropertyValueService.getPropertyValueByCode(valueId);
				if(lcTicketsPropertyValue!=null){
					lcTicketsPropertyValue.setPvName(request.getParameter(argument));
					lcTicketsPropertyValue.setPvOrder(Integer.parseInt(argument.substring(1)));;
					lcTicketsPropertyValue.setShowName(request.getParameter(shownameStr));
					lcTicketsPropertyValue.setStatus("1");
					lfyPropertyValueService.updatePropertyValue(lcTicketsPropertyValue);
				}
			}
		}
		buffer.append("{\"id\":\"").append(categoryCode).append("\",")
		  .append("\"message\":\"").append("修改属性值成功").append("\"}");
		ResponseUtils.renderJson(response, buffer.toString());
		return null;
	}
	
	@RequestMapping(value="addCategoryPropertyValue")
	public String addCategoryPropertyValue(String categoryCode,String propertyCode,HttpServletRequest request,HttpServletResponse response)throws Exception{
		Enumeration<String> em= request.getParameterNames();
		SysUser user=(SysUser) request.getSession().getAttribute("user");
		lfyPropertyValueService.addCategoryPropertyValue(request, categoryCode, propertyCode, em, user);
		StringBuffer buffer=new StringBuffer("");
		buffer.append("{\"id\":\"").append(categoryCode).append("\",")
		  .append("\"message\":\"").append("添加属性值成功").append("\"}");
		ResponseUtils.renderJson(response, buffer.toString());
		return null;
	}
}
