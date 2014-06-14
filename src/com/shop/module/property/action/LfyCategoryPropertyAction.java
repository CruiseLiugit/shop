package com.shop.module.property.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.common.util.ResponseUtils;
import com.shop.common.util.SysUserUtil;
import com.shop.module.category.model.Category;
import com.shop.module.category.service.inter.CategoryService;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.model.LfyProperty;
import com.shop.module.property.service.inter.LfyCategoryPropertyService;
import com.shop.module.property.service.inter.LfyPropertyService;

@Controller
@RequestMapping("/categoryProperty")
public class LfyCategoryPropertyAction {
	@Autowired
	private LfyCategoryPropertyService lfyCategoryPropertyService;
	@Autowired
	private LfyPropertyService lfyPropertyService;
	
	@Autowired 
	private CategoryService categoryService;
	private static final Logger logger = LoggerFactory.getLogger(LfyCategoryPropertyAction.class);
	
	//查找出所有在类目属性表中出现的类目,并且以json形式返回
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
				buffer.append(categoryService.ListToJson1(categorys));
				buffer.append("}");
			}else{//加载子目录
				Category goodsCategory=categoryService.getGoodsCategoryById(id);
				List<Category> categorys=goodsCategory.getChildGoodsgorys();
				if(categorys!=null){
					buffer.append(categoryService.ListToJson1(categorys));
				}
			}
			ResponseUtils.renderJson(response, buffer.toString());
			return null;
	}
	
	@RequestMapping(value="listPropertys")
	public String listPropertys(HttpServletResponse response,HttpServletRequest request){
		String categoryCode=request.getParameter("categoryCode");
		String option=request.getParameter("option");
		if(StringUtils.isNotBlank(categoryCode)){
			List<LfyProperty> myPropertys=lfyPropertyService.findPropertysByCategoryCode(categoryCode);
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("status", "1");
			List<LfyProperty> propertys=lfyPropertyService.findPropertys(map);
			if(propertys!=null){
				for(LfyProperty lp:propertys){
					if(isExist(myPropertys, lp)){
						lp.setIschecked("1");
					}else{
						lp.setIschecked("0");
					}
				}
			}
			Category category=categoryService.getGoodsCategoryById(categoryCode);
			request.setAttribute("category", category);
			request.setAttribute("propertys", propertys);
		}
		if("select".equals(option)){
			return "property/categoryPropertyDetails";
		}
		return "property/categoryProperty-input";
	}
	
	@RequestMapping(value="editCategoryProperty")
	public void editCategoryProperty(HttpServletResponse response,HttpServletRequest request,String categoryCode){
		String msg="操作成功";
		try{
			String propertyList=request.getParameter("propertyList");
			String[] propertys=null;
			if(StringUtils.isNotEmpty(propertyList)){
				propertys=propertyList.split(",");
			}
			SysUser user=SysUserUtil.getLoginUser(request);
			lfyCategoryPropertyService.saveCategoryProperty(categoryCode, propertys,user.getUserCode());
		}catch(Exception e){
			e.printStackTrace();
			msg="操作失败";
		}
		ResponseUtils.renderHtml(response, msg);
	}
	
	@RequestMapping(value="deletePropertys")
	public void deletePropertys(HttpServletResponse response,HttpServletRequest request,String categoryCode){
		int flag=0;
		String pcode="";
		if(StringUtils.isNotBlank(categoryCode)){
			try{
				lfyCategoryPropertyService.updateAllPropertyByCategoryCode(categoryCode);
				Category category=categoryService.getGoodsCategoryById(categoryCode);
				if(category!=null){
					pcode=category.getCategoryPcode();
				}
			}catch(Exception e){
				flag=-1;
				e.printStackTrace();
			}
		}
		String json="{\"flag\":"+flag+",\"categoryCode\":\""+pcode+"\"}";
		ResponseUtils.renderJson(response, json);
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
