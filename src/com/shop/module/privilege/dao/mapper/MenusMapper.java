package com.shop.module.privilege.dao.mapper;

import java.util.List;
import java.util.Map;

import com.shop.module.privilege.model.Menus;
/**
 * 菜单mapper
 * @author caryCheng
 *
 */

public interface MenusMapper {
	/**
	 * 获取当前用户拥有的菜单
	 * @param map
	 * @return
	 */
	public List<Menus> findUserMenus(Map<String,Object> map);
	/**
	 * 插入
	 * @param menus
	 * @return
	 */
	public int insertMenus(Menus menus);
	/**
	 * 更新
	 * @param menus
	 * @return
	 */
	public int updateMenus(Menus menus);
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	public int deleteMenusById(String id);
	/**
	 * 通过菜单code获取菜单
	 * @param menusCode
	 * @return
	 */
	public Menus getMenusByCode(String menusCode);
	/**
	 * 通过菜单名称查找菜单
	 * @param menuName
	 * @return
	 */
	public List<Menus>  getMenusByMenusName(Map<String,Object> map,int startNum, int rp);
	/**
	 * 查询所有菜单列表--实现分页
	 * @param startNum
	 * @param rp
	 * @return
	 */
	public List<Menus> getMenusList(int startNum, int rp);
	/**
	 * 统计菜单数量
	 * @return
	 */
	public int getMenusCount();
	/**
	 * 根据菜单名统计菜单数量
	 * @param map
	 * @return
	 */	
	public int getMenusCountByMenusName(Map<String,Object> map);
	/**
	 * 通过菜单等级id获取菜单
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
	 * 获取权限
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAuthorityCodeByMenuCode(Map<String,Object> map);
	/**
	 * 删除角色权限
	 * @param map
	 */
	public void deleteRoleAuthByauthCode(Map<String,Object> map);
	/**
	 * 删除权限表
	 * @param map
	 */
	public void deleteAuthByMenuCode(Map<String,Object> map);
	/**
	 * 插入权限表
	 * @param map
	 */
	public void insertAuthority(Map<String,Object> map);
	/**
	 * 通过用户编码回去角色编码
	 * @param map
	 * @return
	 */
	public String getRoleCodeByUserCode(Map<String,Object> map);
	/**
	 * 插入角色权限表
	 * @param map
	 */
	public void insertRoleAuth(Map<String,Object> map);
	/**
	 * 获取已经拥有的按钮
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getCheckedButtonId(Map<String,Object> map);
	
	/**
	 *获取所有Auth menu（用于菜单按钮树）
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAllAuthMenus();
	/**
	 *获取所有Auth Button（用于菜单按钮树）
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getAllAuthButtons();
	/**
	 * 获取用户拥有的菜单toolbar
	 * @param map
	 * @return
	 */
	public List<Map<String,Object>> getUserButtons(Map<String,Object> map);
}
