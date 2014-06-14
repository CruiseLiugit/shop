package com.shop.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtil {
	private static final Logger logger = LoggerFactory.getLogger(StringUtil.class);

	public static boolean isEmpty(String str) {
		if (str == null || str.trim().equals("")) {
			return true;
		}
		return false;
	}

	public static String trim(String str) {
		if (isEmpty(str)) {
			return "";
		} else {
			return str.trim();
		}
	}

	public static String formatEmail(String email) {
		if (email != null) {
			String[] arr = email.split("@");
			String str = arr[0].substring(0, 2) + "****" + (arr[0].charAt(arr[0].length() - 1)) + "@" + arr[1];
			return str;
			// int i=email.indexOf("@");
			// int j=email.lastIndexOf(".");
			// if(j>2&&i>=0){
			// String str=email.substring(0, 2)+"****"+email.substring(i-1,j);
			// return str;
			// }else{
			// return email;
			// }
		}
		return null;
	}

	public static String formatEmail1(String email) {
		if (!StringUtils.isEmpty(email)) {
			int i = email.indexOf("@");
			int j = email.lastIndexOf(".");
			if (i > 0 && j > i) {
				String str = email.substring(0, i) + "****" + email.substring(j + 1, email.length());
				return str;
			} else {
				return email;
			}
		}
		return null;
	}

	public static String formatPhoneNumber(String phoneNumber) {
		if (phoneNumber != null) {
			if (phoneNumber.length() > 7) {
				int length = phoneNumber.length();
				String str = phoneNumber.substring(0, 3) + "****" + phoneNumber.substring(length - 4, length);
				return str;
			} else {
				return phoneNumber;
			}
		}
		return null;
	}

	/**
	 * 去掉字符串中的重复值
	 */
	public static String filterRepeatStr(String str) {
		Set<String> mLinkedSet = new LinkedHashSet<String>();
		String[] strArray = str.split(",");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < strArray.length; i++) {
			if (!mLinkedSet.contains(strArray[i])) {
				mLinkedSet.add(strArray[i]);
				sb.append(strArray[i] + ",");
			}
		}
		String sbStr = "";
		if (sb.length() > 0) {
			String str2 = sb.toString();
			sbStr = str2.substring(0, str2.lastIndexOf(","));
		}
		return sbStr;
	}

	/**
	 * 替换字符串。
	 * 
	 * @param input
	 *            被替换的字符串。
	 * @param search
	 *            搜寻目标
	 * @param replacement
	 *            替换的字符串
	 * @return 替换后的字符串。
	 */
	public static String replaceAll(final String input, final String search, final String replacement) {
		return StringUtil.replaceAll(input, search, replacement, "");
	}

	/**
	 * 替换字符串。<br>
	 * 用于不支持搜索替换的小型设备环境，或刻意禁用正则表达式。
	 * 
	 * @param input
	 *            被替换的字符串。
	 * @param search
	 *            搜寻目标。对目标使用编码比对。
	 * @param replacement
	 *            替换的字符串
	 * @param left
	 *            在替换值被写入之前先插入此字符串。
	 * @return 替换后的字符串。
	 */
	public static String replaceAll(String input, final String search, final String replacement, final String left) {
		int pos = input.indexOf(search);
		if (pos != -1) {
			final StringBuilder buffer = new StringBuilder();
			int lastPos = 0;
			do {
				buffer.append(input.substring(lastPos, pos));
				buffer.append(left);
				buffer.append(replacement);
				lastPos = pos + search.length();
				pos = input.indexOf(search, lastPos);
			} while (pos != -1);
			buffer.append(input.substring(lastPos));
			input = buffer.toString();
		}
		return input;
	}

	public static String fromInputStream(Reader reader) throws IOException {
		if (reader != null) {
			BufferedReader br = new BufferedReader(reader);
			String s = br.readLine();
			StringBuffer sb = new StringBuffer();
			while (s != null) {
				sb.append(s);
				s = br.readLine();
			}
			return sb.toString().trim();
		}
		return null;
	}
}
