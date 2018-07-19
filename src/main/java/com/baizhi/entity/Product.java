package com.baizhi.entity;

import lombok.Data;

@Data
public class Product {
	// 商品编号
	private String pid;
	// 商品名称
	private String name;
	// 商品分类名称
	private String catalog_name;
	// 价格
	private float price;
	// 商品描述
	private String description;
	// 图片名称
	private String picture;
	

}
