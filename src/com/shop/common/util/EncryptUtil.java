package com.shop.common.util;

/**
 * 
 * MD5加密算法
 * @author moyuqing
 * 
 */

import java.io.IOException;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class EncryptUtil  {
	public static String md5Encrypt(String s) {
		if ("".equals(s) || s == null) {
			return "";
		}
		try {
			MessageDigest md = 
				MessageDigest.getInstance("MD5");
		
			byte[] bys = md.digest(s.getBytes());
		
			return Base64Encrypt(bys);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static String Base64Encrypt(byte[] bys) {
		BASE64Encoder base = new BASE64Encoder();
		String s = base.encode(bys);
		return s;
	}

	public static byte[] Base64Decrypt(String s) throws IOException {
		BASE64Decoder base = new BASE64Decoder();
		byte[] bys = base.decodeBuffer(s);
		return bys;
	}
	
	public static void main(String[] args){
		String s = EncryptUtil.md5Encrypt("admin1");
		System.out.println(s);
		s = EncryptUtil.md5Encrypt("111111111111");
		System.out.println(s);
		s = EncryptUtil.md5Encrypt("abc1111111111111111111");
		System.out.println(s);
	}
	
	
}
