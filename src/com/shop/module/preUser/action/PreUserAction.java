package com.shop.module.preUser.action;

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
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.common.Constants;
import com.shop.common.base.BaseController;
import com.shop.common.base.MD5;
import com.shop.common.util.DateUtil;
import com.shop.common.util.ResponseUtils;
import com.shop.module.preUser.model.PreUser;
import com.shop.module.preUser.service.inter.PreUserService;
import com.shop.module.privilege.model.SysUser;

@Controller
@RequestMapping("/preUser")
public class PreUserAction {
	private static final Logger logger = LoggerFactory
			.getLogger(PreUserAction.class);
	@Autowired
	private PreUserService preUserService;

	// 分页查询系统用户List方法
	@RequestMapping(value = "getAllUserList")
	public ModelAndView getAllUserList(HttpServletRequest request,
			HttpServletResponse response) {
		logger.info("---------分页查询系统用户List方法开始--------");
		int page = Integer.parseInt(request.getParameter("page"));// 当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page * rows - rows; // 分页查询开始位置
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", "1");
		int recordSize = preUserService.findPreUsersCount(map);// 获取总记录数
		map.put("startNum", startNum);
		map.put("rows", rows);
		List<PreUser> users = preUserService.findPreUsers(map);
		// 遍历用户信息组装json数据
		JSONArray json = new JSONArray();
		if (null != users && users.size() > 0) {
			for (PreUser user : users) {
				JSONObject cell = new JSONObject();
				cell.put("id", user.getPreUserCode());
				cell.put("username", user.getUsername());
				cell.put("createDate", DateUtil.getDate(user.getCreateDate()));
				json.add(cell);
			}
		}
		// 处理json数据
		BaseController.processJson(response, recordSize, json);
		logger.info("------分页查询系统用户List方法开始结束----");
		return null;

	}

	/**
	 * 查看用户名的唯一性
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "checkLoginName")
	public void checkLoginName(HttpServletRequest request,
			HttpServletResponse response) {
		String username = request.getParameter("username");
		String userCode = request.getParameter("userCode");
		boolean flag = preUserService.checkUserName(username, userCode);
		String result = "1";
		if (flag) {
			result = "0";
		}
		BaseController.processPrintStr(response, result);
	}

	/**
	 * 添加前台门户用户
	 * 
	 * @param preUser
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "savePreUser")
	public void savePreUser(PreUser preUser, HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		if (StringUtils.isEmpty(preUser.getPreUserCode())) {// 新增系统用户
			String uuid = UUID.randomUUID().toString();
			preUser.setPreUserCode(uuid);
			if (StringUtils.isNotBlank(preUser.getPassword())) {
				preUser.setPassword(new MD5().getMD5ofStr(preUser.getPassword()));
			}
			preUser.setStatus(Constants.SYSUSER_STATUS_VALID);
			try {
				preUserService.savePreUser(preUser);
				message = "新增用户成功";
			} catch (Exception e) {
				e.printStackTrace();
				message = "新增用户失败:";
			}
			ResponseUtils.renderText(response, message);
		} else {// 修改系统用户
			try {
				preUser.setStatus(Constants.SYSUSER_STATUS_VALID);
				preUserService.updatePreUser(preUser);
				message = "修改用户成功";
			} catch (Exception e) {
				e.printStackTrace();
				message = "修改用户失败:";
			}
			ResponseUtils.renderText(response, message);
		}
	}

	/**
	 * 查看用户信息
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "getPreUserByCode")
	public String getPreUserByCode(HttpServletRequest request,
			HttpServletResponse response) {
		String preUserCode = request.getParameter("preUserCode");
		PreUser preUser = preUserService.findPreUserByUserCode(preUserCode);
		request.setAttribute("preUser", preUser);
		return "preUser/editPreUser";
	}

	/**
	 * 根据用户名模糊查找用户
	 * 
	 * @param request
	 * @param response
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "getPreUserByLoginName")
	public ModelAndView getPreUserByLoginName(HttpServletRequest request,
			HttpServletResponse response, String loginName) {
		logger.info("-------根据登陆名查询系统用户方法开始,用户名:---" + loginName);
		int page = Integer.parseInt(request.getParameter("page"));// 当前页数
		int rows = Integer.parseInt(request.getParameter("rows"));// 每页多少行
		int startNum = page * rows - rows; // 分页查询开始位置
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", loginName);
		map.put("rows", rows);
		map.put("startNum", startNum);
		List<PreUser> userList = preUserService.findPreUsers(map);
		int recordSize = preUserService.findPreUsersCount(map);
		JSONArray json = new JSONArray();
		if (null != userList && userList.size() > 0) {
			for (PreUser user : userList) {
				JSONObject cell = new JSONObject();
				cell.put("id", user.getPreUserCode());
				cell.put("username", user.getUsername());
				cell.put("createDate", DateUtil.getDate(user.getCreateDate()));
				json.add(cell);
			}
		}
		// 处理json数据
		BaseController.processJson(response, recordSize, json);
		return null;
	}

	/**
	 * 根据用户code查找详细信息
	 * 
	 * @param request
	 * @param response
	 * @param loginName
	 * @return
	 */
	@RequestMapping(value = "getPreUserByUserCode")
	public String getPreUserByUserCode(HttpServletRequest request,
			HttpServletResponse response) {
		String userCode = request.getParameter("userCode");
		PreUser preUser = preUserService.findPreUserByUserCode(userCode);
		request.setAttribute("preUser", preUser);
		return "preUser/preUserDetails";
	}

	/**
	 * 删除用户
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "deletePreUsers")
	public void deletePreUsers(HttpServletRequest request,
			HttpServletResponse response) {
		String message = null;
		logger.info("---------删除系统用户方法开始,删除用户id--:"
				+ request.getParameter("userCode"));
		String ids = request.getParameter("ids");
		try {

			preUserService.deletePreUserByCode(ids);
			message = "删除系统用户成功";
		} catch (Exception e) {
			e.printStackTrace();
			message = "删除系统用户失败";
		}
		ResponseUtils.renderText(response, message);
		logger.info("---------删除系统用户方法结束--------" + message);
	}
}
