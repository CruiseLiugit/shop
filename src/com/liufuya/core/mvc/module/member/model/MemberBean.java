package com.liufuya.core.mvc.module.member.model;

import java.util.Date;

import org.nutz.dao.entity.annotation.Column;

/**
 * 用于封装 WEB-INF/jsp/5member/memberInfoList.jsp 页面中要显示的数据
 * 
 * @author lililiu
 * 
 */
public class MemberBean {
	private int mid;
	private String usercode; // 会员编码(随机数字)
	private String usertype; // 会员注册类型
	private String loginname; // 会员手机号码，登录名
	private String loginPwd;  //会员密码
	private String realname; // 真实姓名
	private String usersex; // 性别
	private Date birthday; // 生日
	private String card_number; // 身份证号
	private String city; // 所在城市
	private String telphone; // 手机号码
	private String email; // 邮箱
	private String work_type; // 工作类型
	private String age_area; // 年龄段
	private String family_money; // 家庭收入
	
	
	private String entityCardNumber; // 实体卡卡号
	private String entityCardStatus; // 实体卡状态 1 已开卡 2 已使用 3 已作废
	private int memberCard_balance;// 会员卡余额 精确到分
	private int memberCard_score; // 会员积分
	private Date createdate; // 注册日期
	private String memberStatus; // 会员状态 1正常 0删除 2拉黑

	// -------地址表-------
	private int aid ;//地址表 id
	private String address_code; // 地址随机编码
	private String area;// 所在区域
	private String gps_long;// 默认地址经度
	private String gps_lat;// 默认地址纬度
	private String default_address; // 默认地址,真实地址
	private String available_shops; // 周边配送符合配送条件的门店编号(Null表示无门店可以配送,有多家门店可以外送存入JSON对象{"n1":"门店编号","n2":"门店编号"})
	private String isavailable; // 是否可以配送 1可以 0不可以

	private String is_default;// 是否是默认(0 不是 1 是)
	
	

	public String getUsercode() {
		return usercode;
	}

	public void setUsercode(String usercode) {
		this.usercode = usercode;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getAvailable_shops() {
		return available_shops;
	}

	public void setAvailable_shops(String available_shops) {
		this.available_shops = available_shops;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getUsersex() {
		return usersex;
	}

	public void setUsersex(String usersex) {
		this.usersex = usersex;
	}

	

	public String getDefault_address() {
		return default_address;
	}

	public void setDefault_address(String default_address) {
		this.default_address = default_address;
	}

	public String getIsavailable() {
		return isavailable;
	}

	public void setIsavailable(String isavailable) {
		this.isavailable = isavailable;
	}

	

	public int getMemberCard_score() {
		return memberCard_score;
	}

	public void setMemberCard_score(int memberCard_score) {
		this.memberCard_score = memberCard_score;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEntityCardNumber() {
		return entityCardNumber;
	}

	public void setEntityCardNumber(String entityCardNumber) {
		this.entityCardNumber = entityCardNumber;
	}

	public String getEntityCardStatus() {
		return entityCardStatus;
	}

	public void setEntityCardStatus(String entityCardStatus) {
		this.entityCardStatus = entityCardStatus;
	}

	public int getMemberCard_balance() {
		return memberCard_balance;
	}

	public void setMemberCard_balance(int memberCard_balance) {
		this.memberCard_balance = memberCard_balance;
	}

	public String getAddress_code() {
		return address_code;
	}

	public void setAddress_code(String address_code) {
		this.address_code = address_code;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getGps_long() {
		return gps_long;
	}

	public void setGps_long(String gps_long) {
		this.gps_long = gps_long;
	}

	public String getGps_lat() {
		return gps_lat;
	}

	public void setGps_lat(String gps_lat) {
		this.gps_lat = gps_lat;
	}

	

	public String getIs_default() {
		return is_default;
	}

	public void setIs_default(String is_default) {
		this.is_default = is_default;
	}

	public String getLoginPwd() {
		return loginPwd;
	}

	public void setLoginPwd(String loginPwd) {
		this.loginPwd = loginPwd;
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getAge_area() {
		return age_area;
	}

	public void setAge_area(String age_area) {
		this.age_area = age_area;
	}

	public String getFamily_money() {
		return family_money;
	}

	public void setFamily_money(String family_money) {
		this.family_money = family_money;
	}
	
	
	

}
