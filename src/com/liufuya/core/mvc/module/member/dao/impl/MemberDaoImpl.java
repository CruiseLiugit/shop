package com.liufuya.core.mvc.module.member.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.nutz.dao.Chain;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.liufuya.core.mvc.module.BasicDao;
import com.liufuya.core.mvc.module.member.model.Member;
import com.liufuya.core.mvc.module.member.model.MemberAddress;
import com.liufuya.core.mvc.module.member.model.MemberBean;
import com.shop.module.privilege.dao.mapper.MenusMapper;

/**
 * 会员 dao类
 * 
 * @author caryCheng
 * 
 */
@IocBean
public class MemberDaoImpl extends BasicDao {
	private static final Log log = Logs.get();

	/**
	 * 获取当前所有的有效的会员信息
	 */
	public List<MemberBean> findAllMembers() {
		Sql sql = Sqls
				.create("select m.id as mid,m.user_code as user_code,m.user_type as user_type,m.loginName as loginName,m.realName as realName,m.sex as sex,m.memberCard_score as memberCard_score,m.create_date as create_date,m.status as status,a.city as city,a.area as area,a.address_keywords as address_keywords,a.available_shops as available_shops,a.is_available as is_available  "
						+ "from lfy_member m,lfy_member_address a  "
						+ "where m.user_code=a.user_code and m.status='1' and a.is_default='1' and a.status='1' order by m.create_date desc");

		// dao.execute(sql) 执行前，编写回调函数，解析 查询结果
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<MemberBean> list = new LinkedList<MemberBean>();
				while (rs.next()) {
					// m.FMENU_CODE,m.MENU_URL,m.LEVELID,m.SORTVALUE
					MemberBean menu = new MemberBean();
					menu.setMid(rs.getInt("mid"));
					menu.setUsercode(rs.getString("user_code"));
					menu.setUsertype(rs.getString("user_type"));
					menu.setLoginname(rs.getString("loginName")); // 电话
					menu.setRealname(rs.getString("realName"));
					menu.setUsersex(rs.getString("sex"));
					menu.setMemberCard_score(rs.getInt("memberCard_score"));
					menu.setCreatedate(rs.getDate("create_date"));
					menu.setMemberStatus(rs.getString("status"));
					String city = rs.getString("city");
					String area = rs.getString("area");
					String keyword = rs.getString("address_keywords");
					StringBuffer sb = new StringBuffer("");
					if (city != null) {
						sb.append(city);
					}
					if (area != null) {
						sb.append(area);
					}
					if (keyword != null) {
						sb.append(keyword);
					}
					menu.setDefault_address(sb.toString());
					menu.setIsavailable(rs.getString("is_available"));

					list.add(menu);
				}

				return list;
			}
		});

		dao.execute(sql);
		return sql.getList(MemberBean.class);
		// Nutz内置了大量回调, 请查看Sqls.callback的属性
	}

	
	/**
	 * 插入会员
	 */
	public void insertMember(Member Member) {
		this.save(Member);
	}

	/**
	 * 插入会员
	 */
	public void insertMemberAddress(MemberAddress member_address) {
		this.save(member_address);
	}

	/**
	 * 更新会员
	 */
	public boolean updateMember(Member Member) {
		return this.update(Member);
	}
	
	/**
	 * 更新会员地址
	 */
	public boolean updateMemberAddress(MemberAddress memberaddr) {
		return this.update(memberaddr);
	}

	/**
	 * 删除会员
	 */
	public boolean deleteMemberById(String id) {
		return super.delById(new Integer(id.trim()).intValue(), Member.class);
	}

	//-------------------修改会员-------------------
	/**
	 * 通过会员Code 获取会员
	 */
	public List<MemberBean> getMemberByCode(String memberCode) {
		Sql sql = Sqls
				.create("select m.id as mid,m.user_code as user_code,m.user_type as user_type,m.loginName as loginName,m.loginPwd as loginPwd,m.realName as realName,m.sex as sex,m.birthday as birthday,m.work_type as work_type,m.family_money as family_money,m.age_area as age_area,m.card_number as card_number,m.city as city,m.telphone as telphone,m.email as email,m.entityCardNumber as entityCardNumber,m.entityCardStatus as entityCardStatus,m.memberCard_balance as memberCard_balance,m.memberCard_score as memberCard_score,m.create_date as create_date,m.status as status,a.id as aid,a.address_code as address_code,a.area as area,a.address_keywords as address_keywords,a.gps_long as gps_long,a.gps_lat as gps_lat,a.available_shops as available_shops,a.is_available as is_available  from lfy_member m,lfy_member_address a  where m.user_code=a.user_code and a.is_default='1' and a.status='1'  and m.user_code=@memberCode");
		sql.params().set("memberCode", memberCode);

		// dao.execute(sql) 执行前，编写回调函数，解析 查询结果
		sql.setCallback(new SqlCallback() {
			public Object invoke(Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				List<MemberBean> list = new LinkedList<MemberBean>();
				while (rs.next()) {
					// m.FMENU_CODE,m.MENU_URL,m.LEVELID,m.SORTVALUE
					MemberBean menu = new MemberBean();
					menu.setMid(rs.getInt("mid"));
					menu.setUsercode(rs.getString("user_code")); // 会员编码(随机数字)
					menu.setUsertype(rs.getString("user_type")); // 会员注册类型
					menu.setLoginname(rs.getString("loginName")); // 会员手机号码，登录名
					menu.setLoginPwd(rs.getString("loginPwd"));  //密码
					menu.setRealname(rs.getString("realName"));  // 真实姓名
					menu.setUsersex(rs.getString("sex")); // 性别
					menu.setTelphone(rs.getString("telphone")); // 会员手机号码
					menu.setBirthday(rs.getDate("birthday")); // 生日
					menu.setEmail(rs.getString("email"));  // 邮箱
					menu.setCard_number(rs.getString("card_number")); // 身份证号
					menu.setCity(rs.getString("city")); //城市
					menu.setWork_type(rs.getString("work_type"));//工作类型
					menu.setFamily_money(rs.getString("family_money"));  //家庭收入
					menu.setAge_area(rs.getString("age_area")); //年龄段
					menu.setEntityCardNumber(rs.getString("entityCardNumber"));// 实体卡卡号
					menu.setEntityCardStatus(rs.getString("entityCardStatus"));// 实体卡状态 1 已开卡 2 已使用 3 已作废
					menu.setMemberCard_balance(rs.getInt("memberCard_balance"));// 会员卡余额 精确到分
					menu.setMemberCard_score(rs.getInt("memberCard_score"));// 会员积分
					menu.setCreatedate(rs.getDate("create_date"));// 注册日期
					menu.setMemberStatus(rs.getString("status"));// 会员状态 1正常 0删除 2拉黑
					//-------地址表-------
					menu.setAid(rs.getInt("aid")); //地址表 id
					menu.setAddress_code(rs.getString("address_code")); // 地址随机编码
					menu.setArea(rs.getString("area"));//区/县
					menu.setGps_long(rs.getString("gps_long")); // 默认地址经度
					menu.setGps_lat(rs.getString("gps_lat"));// 默认地址纬度
					menu.setAvailable_shops(rs.getString("available_shops"));// 周边配送符合配送条件的门店编号(Null表示无门店可以配送,有多家门店可以外送存入JSON对象{"n1":"门店编号","n2":"门店编号"})
					menu.setDefault_address(rs.getString("address_keywords"));// 默认地址,真实地址
					menu.setIsavailable(rs.getString("is_available")); // 是否可以配送  1可以 0不可以

					list.add(menu);
				}

				return list;
			}
		});

		dao.execute(sql);
		return sql.getList(MemberBean.class);
		// Nutz内置了大量回调, 请查看Sqls.callback的属性
	}

	/**
	 * 查询所有会员列表 -实现分页
	 */
	public List<Member> getMemberList(int startNum, int rp) {
		Pager pager = dao.createPager(startNum, rp);
		// 设置一共可以查询的条数
		pager.setRecordCount(dao.count(Member.class,Cnd.where("status", "=", 1)));
		List<Member> meuns = dao.query(Member.class,Cnd.where("status", "=", 1), pager);

		return meuns;
	}

	/**
	 * 通过会员Code 获取会员对象
	 */
	public Member getMemberByMemberCode(String user_code) {
		Cnd condition = Cnd.where("user_code", "=", user_code);
		return findByCondition(Member.class, condition);
	}
	
	/**
	 * 统计会员数量
	 */
	public int getMemberCount() {
		// select count(1) from SYS_Member where status='1'
		Cnd condition = Cnd.where("status", "=", 1);
		return this.searchCount(Member.class, condition);
	}

	/**
	 * 通过会员 Id获取会员
	 */
	public Member getMemberById(String id) {
		Cnd condition = Cnd.where("id", "=", id).and("status", "=", "1");
		return findByCondition(Member.class, condition);
	}

	/**
	 * 删除角色权限表
	 */
	public void deleteRoleAuthByauthCode(String authCode) {
		// delete from SYS_ROLES_AUTHORITIES where auth_code = #{authCode}

		Sql sql = Sqls
				.create("delete from SYS_ROLES_AUTHORITIES where auth_code = @authCode ");
		sql.params().set("authCode", authCode);
		dao.execute(sql);
	}

}
