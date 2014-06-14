package com.shop.module.privilege.service.impl;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.shop.common.Constants;
import com.shop.common.base.Tree;
import com.shop.module.privilege.dao.mapper.MenusMapper;
import com.shop.module.privilege.model.Menus;
//import com.shop.module.privilege.model.SysUser;
import com.liufuya.core.mvc.module.privilege.model.SysUser;
import com.shop.module.privilege.service.inter.MenusService;

/**
 * 菜单service实现层
 * 
 * @author caryCheng
 * 
 */
@Service
public class MenusServiceImpl implements MenusService {
	@Autowired
	private MenusMapper menusDao;

	/**
	 * 插入菜单
	 */
	public int insertMenus(Menus menus) throws Exception {
		return menusDao.insertMenus(menus);
	}

	/**
	 * 更新菜单
	 */
	public int updateMenus(Menus menus) throws Exception {
		return menusDao.updateMenus(menus);
	}

	/**
	 * 删除菜单
	 */
	public int deleteMenusById(String id) throws Exception {
		return menusDao.deleteMenusById(id);
	}

	/**
	 * 通过菜单Code获取菜单
	 */
	public Menus getMenusByCode(String menusCode) {
		return menusDao.getMenusByCode(menusCode);
	}

	/**
	 * 查询所有菜单列表
	 */
	public List<Menus> getMenusList(int startNum, int rp) {
		return menusDao.getMenusList(startNum, rp);
	}

	/**
	 * 统计所有菜单数量
	 */
	public int getMenusCount() {
		return menusDao.getMenusCount();
	}

	/**
	 * 通过菜单levelId获取菜单
	 */
	public Menus getMenusByLevelId(String levelId) {
		return menusDao.getMenusByLevelId(levelId);
	}
    /**
     * 获取所有的按钮
     */
	public List<Map<String, Object>> getAllButtons() {
		return menusDao.getAllButtons();
	}

	/**
	 * 生成按钮树
	 */
	public String createButtonTree(List<Map<String, Object>> checkedButtonList) {
		Tree roleTreeRoot = new Tree();
		// 设置根节点标识
		roleTreeRoot.setId(Constants.ROLE_TREE_ROOT_FLAG);
		// 设置根节点名称
		roleTreeRoot.setText(Constants.BUTTON_TREE_ROOT_NAME);
		// 设置根节点ico图标
		// roleTreeRoot.setIconCls("icon-save");
		// 根节点的子节点
		List<Tree> children = new ArrayList<Tree>();
		// 获取所有角色
		List<Map<String, Object>> buttonAllList = this.getAllButtons();
		if (null != buttonAllList && buttonAllList.size() > 0
				&& !buttonAllList.isEmpty()) {
			for (Map<String, Object> buttonAllMap : buttonAllList) {
				Tree buttonTreeChild = new Tree();
				buttonTreeChild
						.setId(buttonAllMap.get("MODEL_CODE") == null ? ""
								: buttonAllMap.get("MODEL_CODE").toString());
				buttonTreeChild
						.setText(buttonAllMap.get("MODEL_NAME") == null ? ""
								: buttonAllMap.get("MODEL_NAME").toString());
				// 处理节点是否选中
				if (null != checkedButtonList && checkedButtonList.size() > 0
						&& !checkedButtonList.isEmpty()) {
					for (Map<String, Object> checkedButtonMap : checkedButtonList) {
						if (StringUtils.equals(buttonAllMap.get("MODEL_CODE")
								.toString(), checkedButtonMap.get("VALUE")
								.toString())) {
							buttonTreeChild.setChecked(true);
						}
					}
				}
				children.add(buttonTreeChild);
			}
		}
		roleTreeRoot.setChildren(children);
		return "[" + JSON.toJSONString(roleTreeRoot) + "]";

	}

	/**
	 * 保存菜单
	 */
	public void saveMenus(HttpServletRequest request, Menus menus,
			String selectButtonIds) throws Exception {
		String[] checkedButtonIds = selectButtonIds.split(","); // 将获取选中的按钮id放入
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session
				.getAttribute(Constants.CURRENT_LOGIN_USER); // 获取当前登陆的系统用户对象
		if (StringUtils.isEmpty(menus.getMenuCode())) {
			String id = UUID.randomUUID().toString();
			menus.setMenuCode(id);
			menus.setStatus("1");
			menusDao.insertMenus(menus);
		} else {
			menusDao.updateMenus(menus);
			Map<String, Object> menuCodeMap = new HashMap<String, Object>();
			menuCodeMap.put("menuCode", menus.getMenuCode());
			List<Map<String, Object>> authoritys = menusDao
					.getAuthorityCodeByMenuCode(menuCodeMap); // 获取权限编码
			if (authoritys != null) {
				for (Map<String, Object> authority : authoritys) {
					Map<String, Object> authCodeMap = new HashMap<String, Object>();
					authCodeMap.put("authCode", authority.get("AUTH_CODE")
							.toString());
					menusDao.deleteRoleAuthByauthCode(authCodeMap);// 删除角色权限
				}
			}
			Map<String, Object> authMap = new HashMap<String, Object>();
			authMap.put("menuCode", menus.getMenuCode());
			menusDao.deleteAuthByMenuCode(authMap);// 删除权限表
		}

		if (checkedButtonIds != null) {

			for (String buttonId : checkedButtonIds) {
				// -----插入权限开始----
				Map<String, Object> inAuthMap = new HashMap<String, Object>();
				String authorityId = UUID.randomUUID().toString();
				inAuthMap.put("authCode", authorityId);
				inAuthMap.put("menuCode", menus.getMenuCode());
				inAuthMap.put("modelCode", buttonId);
				if (buttonId.equals(Constants.RUN_BUTTON_CODE)) { // 运行按钮code
					inAuthMap.put("isMenu", "1");
				} else {
					inAuthMap.put("isMenu", "0");
				}
				menusDao.insertAuthority(inAuthMap); // 插入权限表

				// 插入权限结束
				// 插入角色权限开始
				Map<String, Object> userCodeMap = new HashMap<String, Object>();// 查询角色code的Map
				Map<String, Object> roleAuthMap = new HashMap<String, Object>();// 存放角色权限属性的map
				userCodeMap.put("userCode", user.getUserCode());
				String roleCode = menusDao.getRoleCodeByUserCode(userCodeMap);
				roleAuthMap.put("roleCode", roleCode);
				roleAuthMap.put("authCode", authorityId);
				menusDao.insertRoleAuth(roleAuthMap);
				// 插入角色权限结束
			}
		}

	}

	/**
	 * 获取菜单已经拥有的按钮
	 * 
	 * @param menuCode
	 * @return
	 */
	public List<Map<String, Object>> getCheckedButtonId(String id) {
		if (id != null && id != "") {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", id);
			return menusDao.getCheckedButtonId(map);
		}
		return null;
	}

	/**
	 * 删除菜单
	 * 
	 * @param ids
	 * @throws Exception
	 */
	public void deleteMenus(String ids) throws Exception {
		Map<String, Object> idMap = new HashMap<String, Object>();
		String[] idArr = ids.split(",");
		for (String id : idArr) {
			idMap.put("menuCode", id);
			menusDao.deleteMenusById(id); // 删除菜单
			List<Map<String, Object>> authoritys = menusDao
					.getAuthorityCodeByMenuCode(idMap); // 获取权限编码
			if (authoritys != null && !authoritys.isEmpty()) {
				for (Map<String, Object> authMap : authoritys) {
					String authCode = authMap.get("AUTH_CODE") == null ? ""
							: authMap.get("AUTH_CODE").toString();
					Map<String, Object> authCodeMap = new HashMap<String, Object>();
					authCodeMap.put("authCode", authCode);
					menusDao.deleteRoleAuthByauthCode(authCodeMap);// 删除角色权限
				}
			}
			menusDao.deleteAuthByMenuCode(idMap);// 删除权限表
		}

	}

	/**
	 * 同过菜单名称获取菜单
	 */
	public List<Menus> getMenusByMenusName(String menuName,int startNum, int rp) {		
			Map<String, Object> menuNameMap = new HashMap<String, Object>();
			menuNameMap.put("menuName", menuName);
			return menusDao.getMenusByMenusName(menuNameMap,startNum,rp);				
	}
	
	/**
	 * 通过菜单名称统计菜单数量
	 */
	public int getMenusCountByMenusName(String menuName){
		Map<String, Object> menuNameMap = new HashMap<String, Object>();
		menuNameMap.put("menuName", menuName);
		return menusDao.getMenusCountByMenusName(menuNameMap);
	}

	/**
	 * 生成权限树：包含Menu和Button
	 * 
	 * @param checkedAuthList
	 * @return
	 */
	public String createAuthTree(List<Map<String, Object>> checkedAuthList) {
		Tree authTreeRoot = new Tree();
		// 设置根节点标识
		authTreeRoot.setId(Constants.AUTH_TREE_ROOT_FLAG);
		// 设置根节点名称
		authTreeRoot.setText(Constants.AUTH_TREE_ROOT_NAME);
		// 获取所有Auth menu
		List<Map<String, Object>> authMenus = menusDao.getAllAuthMenus();
		// 获取所有Auth Button
		List<Map<String, Object>> authButtons = menusDao.getAllAuthButtons();
		// 处理权限树
		this.processAuthTree(authTreeRoot, authMenus, authButtons,
				checkedAuthList, Constants.FIRST_MENU_FLAG);
		String test="[" + JSON.toJSONString(authTreeRoot) + "]";
		System.out.println("test:"+test);
		return test;
	}

	/**
	 * 处理权限树
	 * 
	 * @param authTreeRoot
	 * @param authMenus
	 * @param authButtons
	 * @return
	 */
	private void processAuthTree(Tree authTreeRoot,
			List<Map<String, Object>> authMenus,
			List<Map<String, Object>> authButtons,
			List<Map<String, Object>> checkedAuthList, String fmenuFlag) {
		// authTreeRoot节点的子节点
		List<Tree> rooTchildrenList = new ArrayList<Tree>();
		for (Map<String, Object> m : authMenus) {
			if (StringUtils.equals(fmenuFlag, (String) m.get("FMENU_CODE"))) {
				Tree mchildren = new Tree();   //菜单的children
				mchildren.setId((String) m.get("AUTH_CODE"));
				mchildren.setText((String) m.get("MENU_NAME"));
				
				//处理节点是否选中
//				if(null!=checkedAuthList&&checkedAuthList.size()>0&&!checkedAuthList.isEmpty()){
//					for(Map<String, Object> mc:checkedAuthList){
//						if(StringUtils.equals((String)mc.get("AUTH_CODE"),(String)m.get("AUTH_CODE"))){
//							mchildren.setChecked(true);
//						}
//					}
//				}
				//处理Auth Button 
				List<Tree> mcchildrenList = new ArrayList<Tree>();
				for (Map<String, Object> mb : authButtons) {
					// mchildren节点的子节点
					if (StringUtils.equals((String)mb.get("MENU_CODE"), (String) m.get("MENU_CODE"))) {
						Tree bchildren = new Tree();
						bchildren.setId((String) mb.get("AUTH_CODE"));
						bchildren.setText((String) mb.get("MODEL_NAME"));
						//处理节点是否选中
						if(null!=checkedAuthList&&checkedAuthList.size()>0&&!checkedAuthList.isEmpty()){
							for(Map<String, Object> mc:checkedAuthList){
								if(StringUtils.equals((String)mc.get("AUTH_CODE"),(String)mb.get("AUTH_CODE"))){
									bchildren.setChecked(true);
								}
							}
						}
						mcchildrenList.add(bchildren);
						
					}
				}
				
				mchildren.setChildren(mcchildrenList);
				rooTchildrenList.add(mchildren);
				authTreeRoot.setChildren(rooTchildrenList);
				//递归调用
			    processAuthTree(mchildren,authMenus,authButtons,checkedAuthList,(String)m.get("MENU_CODE"));
			}

		}
		
	}
    /**
     * 获取用户拥有的菜单
     */
	public String  getUserToolbar(HttpServletRequest request,HttpServletResponse response,String menusCode) {
		HttpSession session = request.getSession();
		SysUser user = (SysUser) session.getAttribute(Constants.CURRENT_LOGIN_USER); // 获取当前登陆的系统用户对象
		System.out.println("================>当前登录用户对象 :"+user);
		
		StringBuffer toolbarSBuffer = new StringBuffer();
		toolbarSBuffer.append("[");
	
		if(user!=null && menusCode!=""){
			String userCode = user.getUserCode();
			System.out.println("================>当前用户编码 :"+userCode);
			Map<String,Object> map = new HashMap<String, Object>();
			map.put("userCode", userCode);
			map.put("menuCode", menusCode);
		    List<Map<String,Object>> butonList = menusDao.getUserButtons(map);	// 获取当前用户和菜单拥有的按钮
			for(int i=0;i<butonList.size();i++){
				Map<String,Object> buttonMap = butonList.get(i);
				String text = (String)buttonMap.get("MODEL_NAME");
				String iconCls =(String)buttonMap.get("IMGNAME");
				String handler = (String)buttonMap.get("MODEL_TITLE");
				if(i<butonList.size()-1){							
				toolbarSBuffer.append("{");
				toolbarSBuffer.append("\"text\": \""+text+"\",");  
				toolbarSBuffer.append("\"iconCls\": \""+iconCls+"\","); 
				toolbarSBuffer.append("\"handler\": \""+handler+"\""); 
				toolbarSBuffer.append("},");
				}else{
					toolbarSBuffer.append("{");
					toolbarSBuffer.append("\"text\": \""+text+"\",");  
					toolbarSBuffer.append("\"iconCls\": \""+iconCls+"\","); 
					toolbarSBuffer.append("\"handler\": \""+handler+"\""); 
					toolbarSBuffer.append("}");	
					toolbarSBuffer.append("]");	
				}
			}
					
			try {
				PrintWriter out = response.getWriter();
				String json = toolbarSBuffer.toString();
				System.out.println("======= 工具栏按钮begin =======");
				System.out.println(json);
				System.out.println("======= 工具栏按钮end =======");
				out.print(json);
				out.flush();
				out.close();
			} catch (IOException e) {				
				e.printStackTrace();
			}
		}
     return null;
	}
	
}
