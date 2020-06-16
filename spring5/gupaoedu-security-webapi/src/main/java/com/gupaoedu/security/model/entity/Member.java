package com.gupaoedu.security.model.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;



/**
 * 用户信息表
 *
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Member implements Serializable{

	public Member(){}
	public Member(String loginName,String loginPass){
		this.loginName = loginName;
		this.loginPass = loginPass;
	}
	
	private String id;			//自增ID
	private Integer mtype; 		//用户类型1：手机号，2:微信号，3：QQ号，4：新浪微博
	private String loginName;	//登录账号
	private String loginPass;	//登录密码（MD5二次加密，32位）
	private String realName;	//真实姓名
	private String nickName;	//昵称
	private String photo;		//头像
	private String tel;			//手机号
	private Integer sex;		//性别
	private Long store;			//用户积分
	private Integer level;		//用户等级
	private Long loginCount;	//登录次数
	private String lastLoginIp;	//最后登录IP
	private Long lastLoginTime;	//最后登录时间
	private String lastLoginLocation;//最后登录地址
	private Long fromSite;		//所属站点
	private Integer vipFlag;	//是否会员
	private String idCardNum;	//身份证号
	private String idCardImgs;	//身份证电子件
	private String licenseImgs;	//营业执照、组织机构代码、税务登记
	private String licenseNum;	//执照编码
	private Integer certFlag;	//证书类型(0:未提交，1：个人认证，2：企业认证）
	private Integer auditFlag;	//用户资料审核状态(0:未审核，1：审核通过，-1:审核未通过）
	private Long createTime;	//注册时间
	private Integer state;		//用户状态0：不可用，1：正常，2：已禁用，3：已删除
	private String remark;		//备注
	private String thirdInfo;	//第三方登录获取的用户信息
	private String modified;	//修改过的字段（JSON格式）
	private Long bindId;		//绑定账号
	private Integer shopCount;	//已开通店铺个数

}
