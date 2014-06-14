package com.liufuya.core.mvc.module.storemap.action;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.liufuya.common.Constants;
import com.liufuya.common.dwz.DWZ;
import com.liufuya.common.dwz.ReturnBean;

/**
 * 门店管理模块，控制器类
 * 
 * @author 刘立立
 * 
 */
@IocBean
public class StoresAction {

	private static final Log log = Logs.get();

	//@Inject("refer:menusServiceImpl")
	//public MenusServiceImpl menusService;

	// ************************************************************************************
	// 门店资料管理，门店所在城市，增加  按钮点击后，跳转到 增加城市页面
	@At("/m2_toStoreCityAdd")
	@Ok("jsp:jsp.2store.storeCityAdd")
	public void m2_toStoreCityAdd() {
		
	}

	// ************************************************************************************
	// 门店列表，高级搜索 按钮点击后，跳转到 高级搜索页面
	@At("/m2_toStoreSearch")
	@Ok("jsp:jsp.2store.storeSearch")
	public void m2_toStoreSearch() {
	}
	
	
	
	

}
