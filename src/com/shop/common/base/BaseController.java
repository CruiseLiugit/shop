package com.shop.common.base;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.shop.common.Constants;
import com.shop.module.privilege.model.SysUser;

/**
 *   
 * 基础控制类 ：action的辅助类
 */
public class BaseController {
	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	/**
	 * 获取登录用户信息
	 * @param request
	 * @param response
	 * @return
	 */
	public static SysUser getSysUser(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		SysUser  currentLoginUser=(SysUser) session.getAttribute(Constants.CURRENT_LOGIN_USER);
		return currentLoginUser;
	}
	/**
	 * 处理json数据
	 * @param response
	 * @param pageNum
	 * @param totalPage
	 * @param recordSize
	 * @param json
	 */
	public static void processJson(HttpServletResponse response,int recordSize,JSONArray json) {
		JSONObject jsonObj = new JSONObject();
        //jsonObj.put("page",pageNum);        // 当前页
        //jsonObj.put("total",totalPage);        // 总页数
        jsonObj.put("total",recordSize);   // 总记录数
        jsonObj.put("rows", json);
		try {
			PrintWriter out = response.getWriter();
	        out.print(jsonObj);
	        out.flush();
	        out.close();
		} catch (IOException e) {
			logger.error("处理json数据报错："+e.getStackTrace());
		}
	}
	
	/**
	 * 处理json数据
	 * @param response
	 * @param pageNum
	 * @param totalPage
	 * @param recordSize
	 * @param json
	 */
	public static void processJson(HttpServletResponse response,List list) {
		try {
			PrintWriter out = response.getWriter();
	        out.print(JSONArray.toJSON(list));
	        out.flush();
	        out.close();
		} catch (IOException e) {
			logger.error("处理json数据报错："+e.getStackTrace());
		}
	}
	
	/**
	 * 处理processPrintStr
	 * @param response
	 * @param printStr
	 */
	public static void processPrintStr(HttpServletResponse response,String printStr) {
		try {
			PrintWriter out = response.getWriter();
	        out.print(printStr);
	        out.flush();
	        out.close();
		} catch (IOException e) {
			logger.error("处理json数据报错："+e.getStackTrace());
		}
	}
	/**
	 * 获取用户IP方法
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {   
       String ip = request.getHeader("x-forwarded-for");   
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getHeader("Proxy-Client-IP");   
       } 
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getHeader("WL-Proxy-Client-IP");   
       }  
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
           ip = request.getRemoteAddr();   
       }  
       if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {   
    	   ip=request.getHeader("X-Real-IP");
       }

       return ip;   
   }
	
}
