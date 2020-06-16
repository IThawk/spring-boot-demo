package com.gupaoedu.security.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 交易订单表
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order implements Serializable {

	public Order(){}
	public Order(String id,BigDecimal payAmount,List<Product> products){
		this.id = id;
		this.payAmount = payAmount;
		this.products = products;
	}
	public Order(String id,BigDecimal payAmount){
		this.id = id;
		this.payAmount = payAmount;
	}

	private String id;
	private String num;			//订单号共18位，生成规则前缀加数字，1位:前缀C，2位交易类型，3-10位年月日,11-18位，交易序列号，例如C12015051800000009
	private String parentNum;   //主订单号
	private String payNum;		//支付服务商生成的订单号
	private String oname;		//订单名称
	private List<Product> products;
	private	Integer otype;		//交易类型1：未知分类，2：购买商品支付，3:会员充值，4：物业费，5：快递邮费，6：充话费,7:预约服务支付
	private Integer payType;	//支付类型1：支付宝，2：微信，3:银行卡，4：现金支付
	private BigDecimal orderAmount;	//订单应该支付金额
	private BigDecimal payAmount;	//实际支付金额
	private Integer productCount;//包含商品数
	private Long fromMemberId;	//付款人
	private Long toMemberId;	//收款人
	private Integer process;	//订单状态1：已下单未付款，2：已付款待确认，3：已确认待送货，4：正在送货待收货，5：已收货，完成交易
	private Long addrId;		//收货地址
	private String cityPath;	//城市路径
	private String addrDetail;	//收货地址详细说明
	private Long estimatTime;	//预计送达时间
	private Long finishTime;	//实际完成时间
	private String remark;		//备注信息
	private String invoiceNum;	//发票号码
	private	String	sellerTel;	//商家电话
	private String sellerName;	//商家姓名
	private String customTel;	//客户电话
	private String customName;	//客户姓名
	private Long createTime;	//交易时间
	private Integer state;		//订单状态1：可用，0：禁用

	
}
