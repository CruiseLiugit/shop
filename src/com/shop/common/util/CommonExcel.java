package com.shop.common.util;

import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author 张磊
 * Excel工具类
 */
public class CommonExcel {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonExcel.class);
	
	/**
	 * 导出excel
	 * @param list 传入要导出的excel列表信息（list里必须是map类型） 
	 * @param response
	 * @param workbook EXCEL文件
	 * @param title 导出excel的标题
	 */
	public static void exportExcel(HttpServletResponse response,HSSFWorkbook workbook,String title){
		try {
			response.reset();
			response.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;charset=UTF-8");
			String fileName = new String(title.getBytes("UTF-8"), "UTF-8");
			response.setHeader("Content-Disposition", "attachment; filename=" + java.net.URLEncoder.encode(fileName, "UTF-8")+".xls");
			OutputStream outStream = response.getOutputStream();
			workbook.write(outStream);
			outStream.close();			
		} catch (Exception e) {
			logger.error("[CommonExcel]exportExcel error !!! "+e.getMessage());
		}
	}
	
	public static String toUtf8String(String s) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			if (c >= 0 && c <= 255) {
				sb.append(c);
			} else {
				byte[] b;
				try {
					b = Character.toString(c).getBytes("utf-8");
				} catch (Exception ex) {
					b = new byte[0];
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					sb.append("%" + Integer.toHexString(k).toUpperCase());
				}
			}
		}
		return sb.toString();
	}
}
