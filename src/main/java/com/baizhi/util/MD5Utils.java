package com.baizhi.util;

import org.springframework.util.DigestUtils;

import java.util.Random;

public class MD5Utils {
	//参数：输入明文密码
	public static String getMd5Code(String password){
		String md5DigestAsHex = DigestUtils.md5DigestAsHex(password.getBytes());
		return md5DigestAsHex;
	}
	//生成随机的盐
	public static String getSalt(int n){
		char[] code = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < n; i++) {
			stringBuilder.append(code[new Random().nextInt(code.length)]);
		}
		return stringBuilder.toString();
	}
}
