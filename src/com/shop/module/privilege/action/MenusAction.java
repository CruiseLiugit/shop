package com.shop.module.privilege.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.shop.common.base.Datagrid;
import com.shop.common.util.JsonUtils;
import com.shop.common.util.ResponseUtils;
import com.shop.module.privilege.model.Menus;
import com.shop.module.privilege.service.inter.MenusService;


/**
 * 菜单action
 * @author caryCheng
 *
 */
@Controller
@RequestMapping("/menus")
public class MenusAction {
  private static final Logger logger = LoggerFactory.getLogger(MenusAction.class);
  @Autowired
  private MenusService menusService;
  /**
   * 查询所有菜单列表
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "queryAllMenusList", method = { RequestMethod.POST })
  public String queryAllMenusList(HttpServletRequest request,HttpServletResponse response) {
		int page = Integer.parseInt(request.getParameter("page"));// 当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page * rows - rows; // 分页查询开始位置
		String menusJsonStr = "";
		Datagrid datagrid = new Datagrid(); 
		int menusTotalCount = menusService.getMenusCount();// 统计菜单数量
		List<Menus> menusList = menusService.getMenusList(startNum, rows);// 查询菜单列表
		datagrid.setRows(menusList);
		datagrid.setTotal(menusTotalCount);
		try {
			// 将查询的菜单集合list转换成json格式字符串
			menusJsonStr = JsonUtils.objectToJackson(datagrid, Datagrid.class);
			PrintWriter out = response.getWriter();
			out.print(menusJsonStr);
			out.flush();
			out.close();			
		} catch (Exception e) {
			logger.error("处理json数据报错：" + e.getStackTrace());
		}
		logger.info(menusJsonStr);

		return null;
  }
  /**
   * 通过菜单等级查询上级菜单
   * @param levelId
   * @return
   */
  @RequestMapping(value = "getFmenusByLevelId", method = { RequestMethod.POST })
    public String getFmenusByLevelId(HttpServletRequest request,HttpServletResponse response,String levelId){
	  String serchId ="";
	  Menus menus = new Menus();
	  if(levelId.length()==1){
		  menus.setMenuCode("0"); // 当前菜单为一级菜单,上级菜单的code为0
		  menus.setMenuName("已经没有上级菜单了!");
	  }else{
		 serchId = levelId.substring(0, levelId.length()-1); // 获取上级菜单的levelId	
		 menus = menusService.getMenusByLevelId(serchId); //  获取到上级菜单对象
	  }	   	 
	  String menusJsonStr ="";
	  try {
		  menusJsonStr = JsonUtils.objectToJackson(menus, Menus.class);
	    logger.info(menusJsonStr);
		PrintWriter out = response.getWriter();
		out.print(menusJsonStr);
		out.flush();
		out.close();
	} catch (IOException e) {
		logger.error("处理json数据报错：" + e.getStackTrace());	
	}
    	return null;
    }
   /**
    * 加载所有的按钮树
    * @return
    */
  @RequestMapping(value = "loadALLButtonTree", method = { RequestMethod.POST })
   public String loadALLButtonTree(HttpServletRequest request,HttpServletResponse response){
	   String id = request.getParameter("id");
	   List<Map<String,Object>> checkedButtonList = menusService.getCheckedButtonId(id);
	   String buttonJsonString = menusService.createButtonTree(checkedButtonList);
	   logger.info(buttonJsonString);
	   PrintWriter out;
	try {
		out = response.getWriter();
		out.print(buttonJsonString);
		out.flush();
		out.close();
	} catch (IOException e) {	
		logger.error("处理json数据报错：" + e.getStackTrace());	
	}
		
	  return null;
  }
  /**
   * 保存菜单
   * @param request
   * @param response
   * @return
   */
  @RequestMapping(value = "saveMenus", method = { RequestMethod.POST })
  public String saveMenus(HttpServletRequest request,HttpServletResponse response,Menus menus,String selectButtonIds){
	  String msg ="";
	  try {
		menusService.saveMenus(request, menus, selectButtonIds); // 保存菜单
		msg="1";
	} catch (Exception e) {	   
		msg="0";
		logger.error("保存失败:"+e.getStackTrace());
	}
	ResponseUtils.renderText(response, msg); // 将返回的结果输出到页面
	 return null;
  }
  /**
   * 通过菜单的id获取菜单
   * @param request
   * @param id
   * @return
   */
  @RequestMapping(value = "getMenusById", method = { RequestMethod.GET })
   public String  getMenusById(HttpServletRequest request,String id){
	  Menus menus = menusService.getMenusByCode(id);
	  request.setAttribute("menus", menus);	
	  return "menus/menus-input";
   }
  /**
   * 通过菜单的code获取菜单
   * @param request
   * @param id
   * @return
   */
  @RequestMapping(value = "getMenusByCode", method = { RequestMethod.GET })
  public String  getMenusByCode(HttpServletRequest request,String id){
	  Menus menus = menusService.getMenusByCode(id);
	  request.setAttribute("menus", menus);	
	  return "menus/menusDetails";
  }
  /**
   * 删除菜单-支持批量删除
   * @param ids
   * @return
   */
  @RequestMapping(value = "deleteMenus", method = { RequestMethod.GET })
  public String deleteMenus(HttpServletResponse response,String ids){
	  String msg ="";
	  try {
		menusService.deleteMenus(ids); // 删除菜单
		msg = "1";  // 删除成功
	} catch (Exception e) {
		msg = "0";  // 删除失败
	    logger.error("删除失败:"+e.getStackTrace());
	}
	ResponseUtils.renderText(response, msg); 
	  return null;
  }
  /**
   * 根据菜单名称获取菜单
   * @param response
   * @param menuName
   * @return
   */
  @RequestMapping(value = "getMenusByMenusName",method ={RequestMethod.POST})
  public String getMenusByMenusName(HttpServletRequest request,HttpServletResponse response,String menuName){
	  int page = Integer.parseInt(request.getParameter("page"));// 当前页数
	  int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
	  int startNum = page * rows - rows; // 分页查询开始位置
	  List<Menus>  menus = menusService.getMenusByMenusName(menuName,startNum, rows); // 根据菜单名称获取菜单
	  int menusTotalCountByMenusName = menusService.getMenusCountByMenusName(menuName);// 统计菜单数量
	  Datagrid datagrid = new Datagrid(); 
	  datagrid.setRows(menus);
	  datagrid.setTotal(menusTotalCountByMenusName);
	  String  menusJsonStr="";
	  try {
		menusJsonStr = JsonUtils.objectToJackson(datagrid, Datagrid.class);// 将菜单对象转换成json字符串
		PrintWriter out = response.getWriter();
		out.print(menusJsonStr);
		out.flush();
		out.close();	
	} catch (Exception e) {
		logger.error("处理json数据报错：" + e.getStackTrace());
	} 
	 return null;
  }
  /**
   * 获取用户菜单的toolbar
   * @param request
   * @param response
   * @param menusCode
   */
  @RequestMapping(value = "getUserToolbar",method ={RequestMethod.POST})
  public void  getUserToolbar(HttpServletRequest request,HttpServletResponse response,String menusCode){
	  menusService.getUserToolbar(request, response, menusCode);	  
  }
  
}
