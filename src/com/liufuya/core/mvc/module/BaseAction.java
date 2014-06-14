package com.liufuya.core.mvc.module;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.liufuya.common.Constants;
import com.liufuya.core.mvc.module.privilege.model.SysUser;


//@IocBean
public class BaseAction {
	private static final Log log = Logs.get();

	// ioc 注入
	// @Inject("refer:basicDao")
	protected BasicDao basicDao;

	public void setBasicDao(BasicDao basicDao) {
		this.basicDao = basicDao;
	}

	// ///////////////////////////////////////////////

	private static final long serialVersionUID = 2655416150684435458L;

	//操作完成
	protected static final String OPERATION_DONE = "operationDone";

	private int statusCode = 200;
	private String message = null;
	private String forwardUrl = null;

	private boolean skipVC = false;
	private String validationCode; // 验证码

	// search form fields
	public final static int PAGE_SHOW_COUNT = 20;
	
	protected boolean verifyValidationCode(String validationCode,HttpSession session) {
		if (validationCode == null)
			return false;

		// 随机生成验证码
		String randomCode = null;
		try {
			// 取出随机验证码
			randomCode = (String) session.getAttribute(Constants.VALIDATION_CODE);
			System.out.println("取出Session 中保存的，名为 validation_code 的验证码:"
					+ randomCode + " : " + validationCode);
		} catch (Exception e) {
			log.error(e);
		}

		if (randomCode == null)
			return false;
		else if (!randomCode.equalsIgnoreCase(validationCode.trim()))
			return false;

		return true;
	}
	
	protected boolean uploadFile(File file, String filePath) {

		boolean retCode = false;
		byte[] buffer = new byte[1024];
		FileOutputStream fos = null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);

			fos = new FileOutputStream(new File(filePath));

			int n = -1;
			while ((n = is.read(buffer, 0, buffer.length)) != -1) {
				fos.write(buffer, 0, n);
			}

			retCode = true;
			System.out.println("upload file success...");
		} catch (FileNotFoundException fnfe) {
			System.out.println("fnfe:" + fnfe);
		} catch (IOException ioe) {
			System.out.println("ioe:" + ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
					fos = null;
				} catch (IOException e) {
					log.error(e);
				}
			}
			if (is != null) {
				try {
					is.close();
					is = null;
				} catch (IOException e) {
					log.error(e);
				}

			}
		}

		return retCode;
	}

	/**
	 * 从 session 对象中，获取当前登录用户的 对象
	 * @param session
	 * @return
	 */
	public SysUser getContextUser(HttpSession session) {
		if (session != null)
			return (SysUser)session.getAttribute(Constants.CURRENT_LOGIN_USER);
		return null;
	}
	
	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode){
		this.statusCode = statusCode;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getForwardUrl() {
		return forwardUrl;
	}

	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}

	/**
	 * 修改重定向路径
	 * @param target
	 * @param replacement
	 */
	protected void margeForwardUrl(String target, String replacement) {
		if (Strings.isBlank(forwardUrl) && target != null
				&& replacement != null) {
			forwardUrl = forwardUrl.replace(target, replacement);
		}
	}


	/**
	 * 根据 key 从本地化 properties 文件中读取内容
	 * @param key
	 * @param request
	 * @return
	 */
	protected String getText(String key,HttpServletRequest request){
		return ((Map<String,String>)request.getAttribute("msg")).get(key);
	}
	
}
