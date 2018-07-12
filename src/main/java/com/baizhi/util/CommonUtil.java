package com.baizhi.util;

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
}
