package com.shop.module.privilege.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.shop.module.privilege.dao.mapper.MenusMapper;
import com.shop.module.privilege.model.Menus;
/**
 * 菜单dao类
 * @author caryCheng
 *
 */
@Repository
public class MenusDaoImpl extends SqlSessionDaoSupport implements MenusMapper {
	
	public String nameSpace = "com.shop.module.privilege.dao.mapper.MenusMapper";
	
	public String getMybatisMapperNamesapce() {
		return nameSpace;
	}
	/**
	 * 获取当前用户拥有的菜单
	 */
	public List<Menus> findUserMenus(Map<String, Object> map) {
		return this.getSqlSessionTemplate().selectList(this.nameSpace+".findUserMenus",map);
	}
   /**
    * 插入菜单
    */
	public int insertMenus(Menus menus) {
	  return this.getSqlSessionTemplate().getMapper(MenusMapper.class).insertMenus(menus);		
	}
  /**
   * 更新菜单
   */
	public int updateMenus(Menus menus) {
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).updateMenus(menus);
	}
  /**
   * 删除菜单
   */
	public int deleteMenusById(String id) {	
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).deleteMenusById(id);
	}
  /**
   * 通过菜单Code 获取菜单
   */
	public Menus getMenusByCode(String menusCode) {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getMenusByCode(menusCode);
	}
	/**
	 * 查询所有菜单列表 -实现分页
	 */
    public List<Menus> getMenusList(int startNum, int rp) {
    	RowBounds rowBounds = new RowBounds(startNum,rp);
	return  this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getMenusList", new HashMap<String,Object>(), rowBounds);
    }
    /**
     * 统计菜单数量
     */
	public int getMenusCount() {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getMenusCount();
	}
	
	/**
	 * 通过菜单levelId获取菜单
	 */
	public Menus getMenusByLevelId(String levelId) {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getMenusByLevelId(levelId);
	}
	/**
	 * 获取所有的按钮
	 */
	public List<Map<String,Object>> getAllButtons() {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getAllButtons();
	}
	/**
	 * 获取权限编码
	 */
	public List<Map<String, Object>> getAuthorityCodeByMenuCode(Map<String,Object> map) {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getAuthorityCodeByMenuCode(map);
	}
	/**
	 * 删除角色权限表
	 */
	public void deleteRoleAuthByauthCode(Map<String,Object> map) {
		this.getSqlSessionTemplate().getMapper(MenusMapper.class).deleteRoleAuthByauthCode(map);
		
	}
	/**
	 * 删除权限表
	 */
	public void deleteAuthByMenuCode(Map<String, Object> map) {
		this.getSqlSessionTemplate().getMapper(MenusMapper.class).deleteAuthByMenuCode(map);
		
	}
	/**
	 * 插入权限表
	 */
	public void insertAuthority(Map<String, Object> map) {
		this.getSqlSessionTemplate().getMapper(MenusMapper.class).insertAuthority(map);
		
	}
	/**
	 * 通过用户编码回去角色编码
	 */
	public String getRoleCodeByUserCode(Map<String, Object> map) {	
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getRoleCodeByUserCode(map);
	}
	/**
	 * 插入角色权限表
	 */
	public void insertRoleAuth(Map<String, Object> map) {
		this.getSqlSessionTemplate().getMapper(MenusMapper.class).insertRoleAuth(map);
		
	}
	/**
	 * 获取已经拥有的按钮
	 */
	public List<Map<String, Object>> getCheckedButtonId(Map<String, Object> map) {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getCheckedButtonId(map);
	}
	/**
	 * 通过菜单名称查找菜单
	 */
	public List<Menus> getMenusByMenusName(Map<String,Object> map,int startNum, int rp) {
		RowBounds rowBounds = new RowBounds(startNum,rp);
		return this.getSqlSessionTemplate().selectList(getMybatisMapperNamesapce()+".getMenusByMenusName",map, rowBounds);
	}
	/**
	 * 通过菜单名称统计菜单数量
	 */
	public int getMenusCountByMenusName(Map<String,Object> map){		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getMenusCountByMenusName(map);
	}
	
	/**
	 *获取所有Auth menu（用于菜单按钮树）
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAllAuthMenus() {
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getAllAuthMenus();
	}
	/**
	 *获取所有Auth Button（用于菜单按钮树）
	 * @param map
	 * @return
	 */
	public List<Map<String, Object>> getAllAuthButtons() {
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getAllAuthButtons();
	}
	/**
	 * 获取用户拥有的菜单toolbar
	 */
	public List<Map<String, Object>> getUserButtons(Map<String, Object> map) {		
		return this.getSqlSessionTemplate().getMapper(MenusMapper.class).getUserButtons(map);
	}
	
	
}
