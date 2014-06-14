package com.liufuya.core.mvc.module.storemap.action;

import javax.servlet.http.HttpServletRequest;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.liufuya.common.dwz.DWZ;
import com.liufuya.common.dwz.ReturnBean;

/**
 * 城市四级联动下拉框 action
 * 
 * @author lililiu
 * 
 */
@IocBean
public class CitysSelectAction {

	private static final Log log = Logs.get();

	// ************************************************************************************
	// 门店资料管理，已开门店列表，切换地址 链接
	@At("/toChangeCitys")
	@Ok("jsp:jsp.2store.storeCitys")
	public void toChangeCitys() {
	}

	// ************************************************************************************
	// storeCitys.jsp 页面选中一个城市名称后，返回这里
	@At("/checkCity")
	public String checkCity(@Param("selectcity") String selectcity,
			@Param("navTabId") String navTabId, HttpServletRequest request) {
		log.info("选中城市 menus :" + selectcity);

		request.setAttribute("checkedCity", selectcity);
		
		ReturnBean bean = new ReturnBean();
		bean.setStatusCode(DWZ.statusCode.ok);
		bean.setMessage("操作成功");
		bean.setNavTabId(navTabId);
		bean.setRel("");
		bean.setCallbackType(DWZ.CallbackType);
		bean.setForwardUrl("");

		// 返回验证后的状态
		String json = Json.toJson(bean);
		return json;
	}

}
