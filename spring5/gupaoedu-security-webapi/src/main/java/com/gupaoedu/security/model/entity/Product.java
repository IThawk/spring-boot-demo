package com.gupaoedu.security.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 商品信息表
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product implements Serializable{

	private static final long serialVersionUID = 1L;

	public Product(){}
	public Product(String id,String name,BigDecimal price){
		this.id = id;
		this.name = name;
		this.price = price;
	}

	private String id;    	//主键id
	private String shopId;	//店铺ID
	private Integer ptype;	//商品类型
	private String name;	//商品名称
	private String pinyin;	//中文拼音
	private String intro;	//商品介绍
	private String coverImg;//封面图片
	private BigDecimal price; 	//商品价格
	private String unitName;//计量单位
	private BigDecimal sale;		//折后价格
	private Integer stock;  //库存
	private Integer sold;   //已售
	private Long createTime;//添加时间
	private Long proDate;	//生产日期
	private Long keepDate;	//保质期
	private String photos;	//商品照片（JSON 数组，详见ImageItem中定义）
	private Integer able; 	//是否有效（1：表示已删或下架，0：表示正常）
	private String brand;	//品牌
	private String barCode;	//商品条形码
	private Integer state;	//状态码
	

}
