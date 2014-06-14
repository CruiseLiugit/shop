package com.shop.module.category.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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

@Controller
@RequestMapping("/category")
public class CategoryAction {
	private static final Logger logger = LoggerFactory
			.getLogger(CategoryAction.class);
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping(value = "list")
	public String list(HttpServletResponse response,String id,int page,int rows,String select) throws Exception{
		StringBuffer buffer=new StringBuffer();
		int startNum = page * rows - rows; // 分页查询开始位置
		if(id==null||id.equals("-1")){//加载最上层类目
			Map<String,Object> map=new HashMap<String,Object>();
			map.put("status", "1");
			map.put("categoryPcode", "-1");
			if(select!=null&&select!=""){
				map.put("categoryCode", select);
			}
			int count=categoryService.getGoodsCategoryList_count(map);
			buffer.append("{\"total\":"+count+",\"rows\":");
			map.put("startNum", startNum);
			map.put("rows", rows);
			List<Category> categorys=categoryService.getGoodsCategoryList(map);
			buffer.append(categoryService.ListToJson(categorys));
			buffer.append("}");
		}else{//加载子目录
			Category goodsCategory=categoryService.getGoodsCategoryById(id);
			List<Category> categorys=goodsCategory.getChildGoodsgorys();
			if(categorys!=null){
				buffer.append(categoryService.ListToJson(categorys));
			}
		}
		ResponseUtils.renderJson(response, buffer.toString());
		return null;
	}
	
	@RequestMapping(value = "getCategoryByCode")
	public String getCategoryByCode(HttpServletRequest request,String categoryCode,String option){
		Category category=categoryService.getGoodsCategoryById(categoryCode);
		if("edit".equals(option)){//修改
			if(category!=null){
				Category parent=categoryService.getGoodsCategoryById(category.getCategoryPcode());
				if(parent!=null){
					request.setAttribute("categoryPname", parent.getShowName());
				}else{
					request.setAttribute("categoryPname", "这已经是最上层类目");
				}
			}
			request.setAttribute("category", category);
		}else{//添加
			if(category!=null){
				request.setAttribute("categoryPname", category.getShowName());
			}else{
				request.setAttribute("categoryPname", "这已经是最上层类目");
			}
			Category children=new Category();
			children.setCategoryPcode(categoryCode);
			if(category!=null){
				children.setCategoryRootcode(category.getCategoryRootcode());
			}
			request.setAttribute("category", children);
		}
		return "category/category-input";
		
	}
	
	@RequestMapping(value = "getCategoryById")
	public String getCategoryById(HttpServletRequest request,String categoryCode,String option){
		Category category=categoryService.getGoodsCategoryById(categoryCode);
		if(category!=null){
			Category parent=categoryService.getGoodsCategoryById(category.getCategoryPcode());
			if(parent!=null){
				request.setAttribute("categoryPname", parent.getShowName());
			}else{
				request.setAttribute("categoryPname", "这已经是最上层类目");
			}
		}
		request.setAttribute("category", category);
		return "category/categoryDetails";
		
	}
	
	@RequestMapping(value="save")
	public String save(HttpServletResponse response,HttpServletRequest request,Category category,String select)throws Exception{
		String message="";
		StringBuffer buffer=new StringBuffer();
		String categoryId="";
		if(category.getCategoryPcode()==null||category.getCategoryPcode().equals("")){
			category.setCategoryPcode("-1");
		}
		if(category.getCategoryRootcode()==null||category.getCategoryRootcode().equals("")){
			category.setCategoryRootcode("-1");
		}
		SysUser sysUser=SysUserUtil.getLoginUser(request);
		//修改目录
		if(StringUtils.isNotBlank(category.getCategoryCode())){
			category.setStatus("1");
			categoryService.updateGoodsCategory(category);
			message="修改成功";
			categoryId=category.getCategoryPcode();
		}else{//添加目录
			category.setCreateDate(new java.sql.Date(new java.util.Date().getTime()));
			category.setCreateOpid(sysUser.getUserCode());;
			String id=UUID.randomUUID().toString();
			category.setCategoryCode(id);
			category.setStatus("1");
			Category rootCategory=categoryService.findRootCategory(category.getCategoryCode());
			if(rootCategory==null){
				category.setCategoryRootcode(id);
			}else{
				category.setCategoryRootcode(rootCategory.getCategoryCode());
			}
			categoryService.insertGoodsCategory(category);
			message="添加成功";
			categoryId=category.getCategoryPcode();
			if(categoryId!=null){//如果是叶子节点时要特殊对待
				Category parent=categoryService.getGoodsCategoryById(categoryId);
				if(parent!=null&&parent.getChildGoodsgorys().size()<=1){
					categoryId="-1";
				}
			}
		}
		buffer.append("{\"id\":\"").append(categoryId).append("\",")
			  .append("\"message\":\"").append(message).append("\"}");//categoryId父节点
		ResponseUtils.renderJson(response, buffer.toString());
		return null;
	}
	
	@RequestMapping(value="delete")
	public String delete(HttpServletResponse response,HttpServletRequest request,String categoryCode)throws Exception{
		Category category=categoryService.getGoodsCategoryById(categoryCode);
		if(category!=null){
			List<Category> categorys=category.getChildGoodsgorys();
			if(categorys!=null&&categorys.size()>0){
				ResponseUtils.renderHtml(response, "0");
			}else{
				categoryService.deleteGoodsCategory(categoryCode);
				ResponseUtils.renderHtml(response, "删除成功");
			}
		}else{
			ResponseUtils.renderHtml(response, "删除失败");
		}

		return null;
	}
	
	@RequestMapping(value="allowDelte")
	public void allowDelte(HttpServletResponse response,HttpServletRequest request,String categoryCode){
		Category category=categoryService.getGoodsCategoryById(categoryCode);
		String status="0";
		if(category!=null){
			List<Category> categorys=category.getChildGoodsgorys();
			if(categorys!=null&&categorys.size()>0){
				status="1";
			}
		}
		ResponseUtils.renderHtml(response, status);
	}
}
