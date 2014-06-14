package com.shop.module.property.action;

import java.util.ArrayList;
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

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.common.base.BaseController;
import com.shop.common.util.ResponseUtils;
import com.shop.common.util.SysUserUtil;
import com.shop.module.privilege.model.SysUser;
import com.shop.module.property.model.LfyProperty;
import com.shop.module.property.service.inter.LfyPropertyService;

@Controller
@RequestMapping("/property")
public class LfyPropertyAction {
	private static final Logger logger = LoggerFactory.getLogger(LfyPropertyAction.class);
	@Autowired
	private LfyPropertyService lfyPropertyService;
	@RequestMapping(value = "queryAllPropertysList")
	public String queryAllPropertysList(HttpServletRequest request,HttpServletResponse response,String propertyName) {
		logger.info("---------分页查询系统用户List方法开始--------");
		String search=request.getParameter("type");
		int page = Integer.parseInt(request.getParameter("page"));//当前页数
		int  rows =Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page*rows-rows; //分页查询开始位置
		Map<String,Object> map=new HashMap<String,Object>();
		if("search".equals(search)){
			map.put("propertyName", propertyName);
		}
		int recordSize = lfyPropertyService.findPropertys_count(map);//获取总记录数
		List<LfyProperty> propertys = new ArrayList<LfyProperty>();
		map.put("startNum", startNum);
		map.put("rows", rows);
		propertys = lfyPropertyService.findPropertys(map);
        //遍历用户信息组装json数据
		JSONArray json = new JSONArray();
         if(null!=propertys && propertys.size() > 0){
             for (LfyProperty property:propertys) {
               JSONObject cell = new JSONObject();
               cell.put("id",property.getPropertyCode());
               cell.put("propertyName",property.getPropertyName());
               cell.put("showName",property.getShowName());
               if("1".equals(property.getPropertyType())){
                   cell.put("propertyType","字符串");
               }else{
                   cell.put("propertyType","价格区间");
               }
               cell.put("propertyOrder",property.getPropertyOrder());
               if("1".equals(property.getChooseStatus())){
            	   cell.put("chooseStatus","单选");
               }else{
            	   cell.put("chooseStatus","多选");
               }
               
               json.add(cell);
           }
         }
         //处理json数据
         BaseController.processJson(response,recordSize,json);
         logger.info("------分页查询系统用户List方法开始结束----");
		return null;
	}
	
	@RequestMapping(value = "saveProperty")
	public String saveProperty(HttpServletRequest request,HttpServletResponse response,LfyProperty property){
		  String msg ="";
		  try {
			  if(StringUtils.isEmpty(property.getPropertyCode())){//新增属性
				  String uuid = UUID.randomUUID().toString();
				  property.setPropertyCode(uuid);
				  property.setStatus("1");
				  SysUser sysUser=SysUserUtil.getLoginUser(request);
				  if(sysUser!=null){
					  property.setCreateOpid(sysUser.getUserCode());
				  }
				  lfyPropertyService.insertProperty(property);
				  msg ="新增属性成功";
			  }else{//修改属性
				  property.setStatus("1");
				  lfyPropertyService.updateProperty(property);
				  msg ="修改属性成功";
			  }
		} catch (Exception e) {	   
			msg="操作属性失败";
			e.printStackTrace();
			logger.error("失败:"+e.getStackTrace());
		}
		ResponseUtils.renderText(response, msg); // 将返回的结果输出到页面
		 return null;
	 }
	
	@RequestMapping(value = "getPropertyByCode")
	public String getPropertyByCode(HttpServletRequest request,HttpServletResponse response,String propertyCode){
		LfyProperty property=lfyPropertyService.findPropertyByCode(propertyCode);
		if(property!=null){
			request.setAttribute("property", property);
		}
		return "property/property-input";
	}
	
	@RequestMapping(value = "deleteProperty")
	public String deleteProperty(HttpServletRequest request,HttpServletResponse response,String propertyCode){
		String msg ="该属性已经不存在";
		try{
			LfyProperty property=lfyPropertyService.findPropertyByCode(propertyCode);
			if(property!=null){
				property.setStatus("0");
				lfyPropertyService.updateProperty(property);
				msg="删除属性成功";
			}
		}catch(Exception exception){
			msg="删除属性失败";
		}
		ResponseUtils.renderText(response, msg); // 将返回的结果输出到页面
		return null;
	}
	
	@RequestMapping(value = "getPropertyById")
	public String getPropertyById(HttpServletRequest request,HttpServletResponse response,String propertyCode){
		LfyProperty property=lfyPropertyService.findPropertyByCode(propertyCode);
		if(property!=null){
			request.setAttribute("property", property);
		}
		return "property/propertyDetails";
	}
	
	@RequestMapping(value = "deletePropertys")
	public void deletePropertys(HttpServletRequest request,HttpServletResponse response){
		String ids=request.getParameter("ids");
		int flag=0;
		if(StringUtils.isNotBlank(ids)){
			String[] list=ids.split(",");
			flag=lfyPropertyService.deletePropertys(list);
		}
		ResponseUtils.renderHtml(response,String.valueOf(flag));
	}
}
