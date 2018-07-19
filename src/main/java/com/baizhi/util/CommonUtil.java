package com.baizhi.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CommonUtil {
	public static List<String> getGoodsFields(){
		String[] subs = {"序号","商品名称","规格","价格","销量","销售商","上架时间","所属主题","简介"};
		List<String> list = new ArrayList<String>();
		for (String s : subs) {
			list.add(s);
		}
		return list;
	}
	public static List<String> getUserFields(){
		String[] subs = {"序号", "手机号", "密码", "随机盐", "昵称", "头像地址", "注册时间", "状态"};
		List<String> list = new ArrayList<String>();
		for (String s : subs) {
			list.add(s);
		}
		return list;
	}
	public static String SMS(String postData, String postUrl) {
		try {
			//发送POST请求
			URL url = new URL(postUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			conn.setRequestProperty("Connection", "Keep-Alive");
			conn.setUseCaches(false);
			conn.setDoOutput(true);

			conn.setRequestProperty("Content-Length", "" + postData.length());
			OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
			out.write(postData);
			out.flush();
			out.close();

			//获取响应状态
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				System.out.println("connect failed!");
				return "";
			}
			//获取响应内容体
			String line, result = "";
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
			while ((line = in.readLine()) != null) {
				result += line + "\n";
			}
			in.close();
			return result;
		} catch (IOException e) {
			e.printStackTrace(System.out);
		}
		return "";
	}
}
