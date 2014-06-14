package com.shop.common;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * 公用静态类
 */
public class Constants {
	//读取配置文件
	private static Configuration cacheConfig;
	
	static {
		try {
			cacheConfig = new PropertiesConfiguration("ApplicationResources.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 当前登录用户Session常量
	 */
	public static final String CURRENT_LOGIN_USER="CURRENT_LOGIN_USER";
	/**
	 * 登录验证码Session常量
	 */
	public static final String KAPTCHA_SESSION_KEY="KAPTCHA_SESSION_KEY";
	
	
	/**
	 * 一级菜单标记
	 */
	public static final String FIRST_MENU_FLAG="0";
	
	/**
	 * 角色树根标识
	 */
	public static final String ROLE_TREE_ROOT_FLAG="0";

	/**
	 * 角色树根名称
	 */
	public static final String ROLE_TREE_ROOT_NAME="角色";
	/**
	 * 按钮树根名称
	 */
	public static final String BUTTON_TREE_ROOT_NAME="按钮";
	/**
	 * 运行按钮的code
	 */
	public static final String RUN_BUTTON_CODE="f14f35f8-2246-41h2-34f2-4cg56ad75ead"; 
	
	/**
	 * 权限树根名称
	 */
	public static final String AUTH_TREE_ROOT_NAME="权限";
	/**
	 * 权限树根标识
	 */
	public static final String AUTH_TREE_ROOT_FLAG="";
	
	
	/***
	 * 列表数据每页默认条数
	 */
	//public static final int DEFAULT_PAGE_SIZE=cacheConfig.getInt("DEFAULT_PAGE_SIZE");


	/**
	 * 系统用户状态：1 有效 0 无效
	 */
	public static final String SYSUSER_STATUS_VALID = "1";
	/**
	 * 系统admin 角色id
	 */
	public static final String ADMIN_ROLE_CODE ="d22f30b8-2716-41d2-84f2-4cd56bb75ecc";
	
	/**
	 * 合作商和把握公司合作状态： 1 正常合作 2 暂停 合作 3 取消合作
	 */
	public static final String PARTNER_COOPERATE_STATUS_NORMAL="1";
	public static final String PARTNER_COOPERATE_STATUS_PAUSE="2";
	public static final String PARTNER_COOPERATE_STATUS_DELETE="3";
	
	/**
	 * 合作商和铺设点合作状态： 1 正常合作 2 暂停 合作 3 取消合作
	 */
	public static final String PARTNER_PAVE_COOPERATE_STATUS_NORMAL = "1";
	
	/**
	 * 电子券的状态 :0未认证 3 冻结
	 * 
	 */
	public static final String ELECTICKET_STATUS_FREEZE="3";
	public static final String ELECTICKET_STATUS_UNAPPROVE="0";
	/**
	 * 电子券的操作类型 1 冻结  2 恢复
	 */
	public static final String ELECTICKET_OPTYPE_FREEZE="1";
	public static final String ELECTICKET_OPTYPE_RECOVER="2";
	/**
	 * pov机心跳间隔多久判断为运行故障 默认40分钟
	 */
	public static final int  POV_RUNNING_STATUS_TIME=40;
	
	/**
	 * pov机在铺设点的使用状态 1 正常 2 暂停
	 */
	public static final String POV_USE_STATUS_NORMAL = "1";
	public static final String POV_USE_STATUS_NORMAL_STR = "正常";
	public static final String POV_USE_STATUS_PAUSE_STR = "暂停";
	
	/**
	 * 铺设点状态 1 正常 2 暂停
	 */
	public static final String PAVE_STATUS_NORMAL = "1";
	public static final String PAVE_STATUS_PAUSE = "2";
	public static final String PAVE_STATUS_NORMAL_STR = "正常";
	public static final String PAVE_STATUS_PAUSE_STR = "暂停";
	/**
	 * pov机的使用状态1 正常,2 暂停
	 */
	public static final String  POV_USE_STATUS_PAUSE="2";
	public static final String  POV_USE_STATUS_RECOVER="1";
	public static final String  POV_USE_STATUS_RELEVANCE="已关联"; 
	public static final String  POV_USE_STATUS_RELIVE="暂停";
	
	/**
	 * 铺设点操作类型 3 删除, 2 暂停 
	 */
	public static final String PAVE_OPERATE_DELETE = "3";
	/**
	 * POV添加方式 1 单个添加 2 批量添加
	 */
	public static final String WAREHOUSE_POV_ADD_SATUS="1";
	public static final String WAREHOUSE_POV_ADD_LIST_SATUS="2";
	/**
	 * POV仓库管理状态 1 已入库  2 已出库 3维修中
	 */
	public static final String WAREHOUSE_POV_SATUS_IN="1";
	public static final String WAREHOUSE_POV_SATUS_OUT="2";
	public static final String WAREHOUSE_POV_SATUS_MAINTAIN="3";
	
	/**
	 * 解析Excel保存在session中的KEY
	 */
	public static final String PARSE_EXCEL_SESSION_KEY = "PaveListFromParseExcel";
}
