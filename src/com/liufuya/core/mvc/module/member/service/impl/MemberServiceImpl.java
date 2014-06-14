package com.liufuya.core.mvc.module.member.service.impl;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;

import com.liufuya.common.Constants;
import com.liufuya.common.base.ButtonTree;
import com.liufuya.common.base.Tree;
import com.liufuya.core.mvc.module.member.dao.impl.MemberDaoImpl;
import com.liufuya.core.mvc.module.member.model.Member;
import com.liufuya.core.mvc.module.member.model.MemberAddress;
import com.liufuya.core.mvc.module.member.model.MemberBean;
import com.shop.module.privilege.model.SysUser;

/**
 * 会员 service实现层
 * 
 * @author 刘立立
 * 
 */
@IocBean
public class MemberServiceImpl {
	private static final Log log = Logs.get();

	@Inject("refer:memberDaoImpl")
	private MemberDaoImpl memberDao;

	public List<MemberBean> findAllMembers() {
		return memberDao.findAllMembers();
	}
	
	/**
	 * 1 插入会员
	 */
	public void insertMember(Member member) throws Exception {
		memberDao.insertMember(member);
	}
	
	/**
	 * 1 插入会员默认地址，与插入会员同步操作
	 */
	public void insertMemberAddress(MemberAddress member_address) throws Exception {
		memberDao.insertMemberAddress(member_address);
	}
	
	/**
	 * 更新会员
	 */
	public boolean updateMember(Member member) throws Exception {
		return memberDao.updateMember(member);
	}
	
	/**
	 * 更新会员地址
	 */
	public boolean updateMemberAddress(MemberAddress memberaddr) throws Exception {
		return memberDao.updateMemberAddress(memberaddr);
	}

	
	/**
	 * 删除会员
	 */
	public boolean deleteMemberById(String id) throws Exception {
		return memberDao.deleteMemberById(id);
	}

	/**
	 * 通过会员Code获取会员
	 */
	public List<MemberBean> getMemberByCode(String memberCode) {
		return memberDao.getMemberByCode(memberCode);
	}
	
	
	/**
	 * 通过会员Code获取会员
	 */
	public Member getMemberByMemberCode(String user_code){
		return memberDao.getMemberByMemberCode(user_code);
	}
	
	/**
	 * 通过会员 id 获取会员
	 */
	public Member getMemberById(String MemberId) {
		return memberDao.getMemberById(MemberId);
	}

	/**
	 * 查询所有会员列表
	 */
	public List<Member> getMemberList(int startNum, int rp) {
		return memberDao.getMemberList(startNum, rp);
	}

	/**
	 * 统计所有会员数量
	 */
	public int getMemberCount() {
		return memberDao.getMemberCount();
	}

}
