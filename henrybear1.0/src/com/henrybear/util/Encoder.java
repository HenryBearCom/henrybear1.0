/**
 * 加密,MessageDigest 类为应用程序提供信息摘要算法的功能，如 MD5 或 SHA 算法
 */
package com.henrybear.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @author Administrator
 *
 */
public class Encoder {
	
	private final static char[] HEXDIGITS = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

	public static String enCoder(String msg ,String arg0){
		byte[] bytes = msg.getBytes();
		String encoder = null;
		try {
			MessageDigest mdTemp = MessageDigest.getInstance(arg0);
			mdTemp.update(bytes);
			byte[] md = mdTemp.digest();
			int j = md.length;
			char str[] = new char[j*2];
			int k = 0;
			for(int i=0;i<j;i++){
				byte byte0 = md[i];
				str[k++] = HEXDIGITS[byte0 >>> 4 & 0xf];
				str[k++] = HEXDIGITS[byte0 & 0xf];
			}
			encoder = new String(str);
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return encoder;
		
	}
	
}
