package com.shop.module.privilege.service.inter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shop.module.privilege.model.Menus;

/**
 * 菜单service接口类
 * @author caryCheng
 *
 */
public interface MenusService {

	/**
	 * 插入
	 * @param menus
	 * @return
	 */
	public int insertMenus(Menus menus) throws Exception;
	/**
	 * 更新
	 * @param menus
	 * @return
	 */
	public int updateMenus(Menus menus)  throws Exception;
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteMenusById(String id)  throws Exception;
	/**
	 * 通过菜单code获取菜单
	 * @param menusCode
	 * @return
	 */
	public Menus getMenusByCode(String menusCode);
	/**
	 * 查询所有菜单列表
	 * @param startNum
	 * @param rp
	 * @return
	 */
	public List<Menus> getMenusList(int startNum, int rp);
	/**
	 * 统计所有菜单数量
	 * @return
	 */
	public int getMenusCount();
	/**
	 * 通过菜单levelId获取菜单
	 * @param levelId
	 * @return
	 */
	public Menus getMenusByLevelId(String levelId);
	/**
	 * 获取所有的按钮
	 * @return
	 */
	public List<Map<String,Object>> getAllButtons();
	/**
	 * 生成按钮树
	 * @param checkedButtonList
	 * @return
	 */
	public String createButtonTree(List<Map<String,Object>> checkedButtonList);
	/**
	 * 保存菜单
	 * @param request
	 * @param menus
	 * @param selectButtonIds
	 * @throws Exception
	 */
	public void saveMenus(HttpServletRequest request,Menus menus,String selectButtonIds)throws Exception;
	/**
	 * 获取菜单拥有的按钮
	 * @param id
	 * @return
	 */
	public List<Map<String,Object>> getCheckedButtonId(String id);
	/**
	 * 删除菜单
	 * @param ids
	 * @throws Exception
	 */
	public void deleteMenus(String ids) throws Exception;
	/**
	 * 根据菜单名称查询菜单
	 * @param menuName
	 * @return
	 */
	public List<Menus> getMenusByMenusName(String menuName,int startNum, int rp);
	/**
	 * 根据菜单名称统计菜单数量
	 * @param menuName
	 * @return
	 */
	public int getMenusCountByMenusName(String menuName);
	/**
	 * 生成权限树：包含Menu和Button
	 * @param checkedAuthList
	 * @return
	 */
	public String createAuthTree(List<Map<String,Object>> checkedAuthList);
	/**
	 * 获取用户拥有的菜单toolbar
	 * @param userCode
	 * @param menusCode
	 * @return
	 */	
	public String getUserToolbar(HttpServletRequest request,HttpServletResponse response,String menusCode);
	
}
