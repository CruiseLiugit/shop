package com.liufuya.core.mvc.module.member.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.json.Json;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.liufuya.common.Constants;
import com.liufuya.common.base.MD5;
import com.liufuya.common.dwz.DWZ;
import com.liufuya.common.dwz.ReturnBean;
import com.liufuya.core.filter.LoginSessionFilter;
import com.liufuya.core.map.BaiduMapBiz;
import com.liufuya.core.map.jsonbean.Geocoding;
import com.liufuya.core.mvc.module.member.model.Member;
import com.liufuya.core.mvc.module.member.model.MemberAddress;
import com.liufuya.core.mvc.module.member.model.MemberBean;
import com.liufuya.core.mvc.module.member.service.impl.MemberServiceImpl;
import com.shop.common.base.Datagrid;
import com.shop.common.util.JsonUtils;
import com.shop.module.privilege.model.Menus;

/**
 * 会员管理模块，控制器类
 * 
 * @author 刘立立
 * 
 */
@IocBean
//全局的字符过滤器
//@Filters({@By(type=LoginSessionFilter.class)})
public class MemberInfoAction {

	private static final Log log = Logs.get();

	@Inject("refer:memberServiceImpl")
	public MemberServiceImpl memberService;

	// ioc 注入，百度工具类
	@Inject("refer:baiduMapBiz")
	private BaiduMapBiz baidu;

	// ************************************************************************************
	/**
	 * 1 进入页面，查询页面列表数据，才有页面 ajax 请求方式获取列表数据，
	 * 大量使用异步效果
	 * 
	 * @param request
	 */
	@At("/m5_memberGetInfoList")
	public void m5_memberGetInfoList(@Param("page") String page,
			@Param("rows") String rows, HttpServletResponse response) {
		//分页
		int pageInt = Integer.parseInt(page);// 当前页数
		int rowsInt = Integer.parseInt(rows);// 每页多少行
		int startNum = pageInt * rowsInt - rowsInt; // 分页查询开始位置
		
		log.info("分页 当前页数 ="+pageInt+" ,每页行数 row ="+rowsInt+" , 分页开始位置 startNum ="+startNum);
		
		String menusJsonStr = "";
		Datagrid datagrid = new Datagrid(); 
		int menusTotalCount = memberService.getMemberCount();// 统计会员数量
		log.info("会员数据表总数为 :"+menusTotalCount);
		
		//这里查询所有会员信息,lfy_member, lfy_member_address
		List<Member> list = memberService.getMemberList(startNum,rowsInt); //查询会员列表
		log.info("所有会员 list size = " + list.size());
		
		datagrid.setRows(list);
		datagrid.setTotal(menusTotalCount);
		PrintWriter out = null;
		try {
			menusJsonStr = Json.toJson(datagrid);//JsonUtils.objectToJackson(datagrid, Datagrid.class);
			//log.info("查询出来的 json ="+menusJsonStr);
			out = response.getWriter();
			out.print(menusJsonStr);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("处理json数据报错：" + e.getStackTrace());
		} finally {
			if (out != null) {
				out.close();
			}
		}
	}
	
	

	// ************************************************************************************
	// 搜索栏
	// memberInfoList.jsp 页面，点击按钮 <高级检索>，跳转到 高级搜索页面
	@At("/m5_toMemberSearch")
	@Ok("jsp:jsp.5member.memberSearch")
	public void m5_toMemberSearch() {

	}

	// ************************************************************************************
	// CRUD 工具栏
	// ----------------批量导入会员信息-----------------
	// memberInfoList.jsp 页面，点击按钮 <批量导入> , 跳转到新增会员界面
	@At("/m5_toImportMember")
	@Ok("jsp:jsp.5member.memberInfoImport")
	public void m5_toImportMember(HttpServletRequest request) {
		// 简单跳转,获取菜单需要的 操作按钮，传递过去

	}

	// ----------------添加会员信息-----------------
	// memberInfoList.jsp 页面，点击按钮 <添加> , 跳转到新增会员界面
	@At("/m5_toCreateMember")
	@Ok("jsp:jsp.5member.memberInfoAdd")
	public void m5_toCreateMember(HttpServletRequest request) {
		// 简单跳转,获取菜单需要的 操作按钮，传递过去

	}

	// memberInfoAdd.jsp 页面，点击 <保持> 按钮。增加会员数据，并刷新列表
	@At("/createMemberInfo")
	public String createMemberInfo(@Param("::member.") Member member,
			@Param("navTabId") String navTabId, HttpServletRequest request) {
		// log.info("增加会员信息 member :"+member);
		// log.info("增加会员信息 member realName :"+member.getRealName());
		// log.info("增加会员信息 member sex :"+member.getSex());
		// log.info("增加会员信息 member.birthday :"+member.getBirthday());

		// 1 适配器自动把页面数据，封装到 member 对象中
		// 2 对比 界面 sysmemberList.jsp 传递过来的密码，要进行 MD5加密
		// 3 封装默认到参数
		String uuid = UUID.randomUUID().toString();
		member.setUser_code(uuid);
		member.setUser_type("4");
		member.setLoginName(member.getTelphone());
		member.setLoginPwd(new MD5().getMD5ofStr(member.getLoginPwd().intern()));
		// 工作类型，家庭收入，年龄区间
		member.setStatus("1");
		member.setCreate_date(new Date());

		// 准备同时插入会员默认地址
		MemberAddress address = new MemberAddress();
		String uuid2 = UUID.randomUUID().toString();
		address.setAddress_code(uuid2);
		address.setUser_code(uuid); // 用户 code
		address.setCity(member.getCity()); // 城市
		address.setArea(member.getCity_part());// 城市区域
		address.setAddress_keywords(member.getDefault_address());
		// 调用百度地图查询 GPS 周围店铺 code 编号
		Geocoding ge = null;
		try {
			// log.info("百度地图查询1");
			ge = baidu.getMapByGet(member.getDefault_address(),
					member.getCity());
			// log.info("百度地图查询2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("百度地图查询异常");
		}
		if (ge != null) {
			if (ge.getResult() != null) {
				// log.info("百度地图查询成功,纬度 lat = "+ge.getResult().getLocation().getLat());
				address.setGps_lat("" + ge.getResult().getLocation().getLat());
				address.setGps_long("" + ge.getResult().getLocation().getLng());
			} else {
				log.info("地址不正确");
			}
		} else {
			log.info("百度地图查询失败");
		}
		address.setCreate_date(new Date());
		address.setIs_default("1"); // 是否是默认地址
		address.setStatus("1");
		address.setIs_available("0"); // 是否可配送，暂时不做
		// available_shops
		// 周边配送符合配送条件的门店编号(Null表示无门店可以配送,有多家门店可以外送存入JSON对象{"n1":"门店编号","n2":"门店编号"})

		ReturnBean bean = new ReturnBean();
		// 4 插入数据库
		try {
			memberService.insertMember(member);
			memberService.insertMemberAddress(address);
			// message = "新增系统会员成功";
			bean.setStatusCode(DWZ.statusCode.ok);
			bean.setMessage("新增会员成功"); // 成功后，弹出提示文字
		} catch (Exception e) {
			e.printStackTrace();
			bean.setStatusCode(DWZ.statusCode.error);
			bean.setMessage("操作失败");
		}

		bean.setNavTabId(navTabId);
		bean.setRel("");
		bean.setCallbackType(DWZ.CallbackType);
		bean.setForwardUrl("");

		// 返回验证后的状态
		String json = Json.toJson(bean);
		// log.info(json);
		return json;
	}

	// ----------------修改会员信息-----------------
	// memberInfoList.jsp 页面，点击按钮 <修改> , 跳转到修改会员界面
	@At("/m5_toEditMember")
	@Ok("jsp:jsp.5member.memberInfoEdit")
	public void toEditSysmb(@Param("mid") String member_code,
			HttpServletRequest request) {
		// 根据用户 id 查询用户对象
		// log.info("要修改会员的 code ="+uid);
		List<MemberBean> beans = this.memberService
				.getMemberByCode(member_code);
		if (beans != null && beans.size() > 0) {
			request.setAttribute("member", beans.get(0));
		}

		request.setAttribute("member_code", member_code);
		// 页面下来框中需要的数据。通过 request 对象传递过去
		// 年龄段
		request.setAttribute("ageList", Constants.age_area_list);
		request.setAttribute("worktypeList", Constants.work_type_list);
		request.setAttribute("fmoney_list", Constants.family_money_list);
	}

	// 修改会员页面点击，修改按钮
	@At("/editMemberInfo")
	public String editMemberInfo(@Param("::member.") MemberBean mb,
			@Param("navTabId") String navTabId,
			@Param("member_code") String member_code, HttpServletRequest request) {
		// 跳转并刷新列表数据
		// log.info("修改会员信息 mb :" + mb);
		// log.info("修改会员信息 mb loginname :" + mb.getLoginname());
		// log.info("修改会员信息 mb name :" + mb.getAddress_code());
		// log.info("修改会员信息 mb type :" + mb.getmbType());
		// 把一个 MemberBean 中的数据拆分到两个数据库表对象中
		Member member = new Member();
		member.setId(mb.getMid());
		member.setUser_code(mb.getUsercode());
		member.setUser_type(mb.getUsertype());
		member.setLoginName(mb.getLoginname());
		member.setLoginPwd(mb.getLoginPwd());
		member.setRealName(mb.getRealname());
		member.setSex(mb.getUsersex());
		member.setBirthday(mb.getBirthday());
		member.setCard_number(mb.getCard_number());
		member.setCity(mb.getCity());
		member.setTelphone(mb.getTelphone());
		member.setEmail(mb.getEmail());
		member.setAge_area(mb.getAge_area());
		member.setWork_type(mb.getWork_type());
		member.setFamily_money(mb.getFamily_money());
		member.setEntityCardNumber(mb.getEntityCardNumber());
		member.setEntityCardStatus(mb.getEntityCardStatus());
		member.setMemberCard_balance(mb.getMemberCard_balance());
		member.setMemberCard_score(mb.getMemberCard_score());
		member.setCreate_date(mb.getCreatedate());
		member.setStatus(mb.getMemberStatus()); // 在会员订单管理里面，拉黑会员

		MemberAddress mbaddr = new MemberAddress();
		mbaddr.setId(mb.getAid()); // 地址表 id
		mbaddr.setAddress_code(mb.getAddress_code()); // 地址随机编码
		mbaddr.setUser_code(mb.getUsercode());
		mbaddr.setCity(mb.getCity());
		mbaddr.setArea(mb.getArea()); // 所在区域
		mbaddr.setCreate_date(new Date()); // 修改后的日期
		mbaddr.setIs_default("1");
		mbaddr.setStatus("1");
		mbaddr.setIs_available(mb.getIsavailable());
		mbaddr.setAddress_keywords(mb.getDefault_address());
		// 根据地址重新获取 经纬度值
		mbaddr.setAvailable_shops(mb.getAvailable_shops()); // 配送门店编号
		// 调用百度地图查询 GPS 周围店铺 code 编号
		Geocoding ge = null;
		try {
			// log.info("百度地图查询1");
			ge = baidu.getMapByGet(mb.getDefault_address(), mb.getCity());
			// log.info("百度地图查询2");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("百度地图查询异常");
		}
		if (ge != null) {
			if (ge.getResult() != null) {
				// log.info("百度地图查询成功,纬度 lat = "+ge.getResult().getLocation().getLat());
				mbaddr.setGps_lat("" + ge.getResult().getLocation().getLat());
				mbaddr.setGps_long("" + ge.getResult().getLocation().getLng());
			} else {
				log.info("地址不正确");
			}
		} else {
			log.info("百度地图查询失败");
		}

		ReturnBean bean = new ReturnBean();
		try {
			boolean flag1 = this.memberService.updateMember(member);
			boolean flag2 = this.memberService.updateMemberAddress(mbaddr);

			if (flag1 && flag2) {
				bean.setStatusCode(DWZ.statusCode.ok);
				bean.setMessage("修改会员信息，操作成功");
			} else {
				bean.setStatusCode(DWZ.statusCode.error);
				bean.setMessage("修改会员信息，操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setStatusCode(DWZ.statusCode.error);
			bean.setMessage("修改会员信息，操作失败");
		}
		bean.setNavTabId(navTabId);
		bean.setRel("");
		bean.setCallbackType(DWZ.CallbackType);
		bean.setForwardUrl("");

		// 返回验证后的状态
		return Json.toJson(bean);
	}

	// ----------------删除会员信息-----------------
	// 删除会员
	@At("/m5_deleteMember")
	@Ok("json")
	public String m5_deleteMember(@Param("mid") String mid,
			HttpServletRequest request) {
		// 简单跳转
		log.info("mb id=" + mid);
		// 查询。会员对象
		Member member = this.memberService.getMemberByMemberCode(mid);
		member.setStatus("0");

		ReturnBean bean = new ReturnBean();
		try {
			boolean flag = this.memberService.updateMember(member);
			if (flag) {
				bean.setStatusCode(DWZ.statusCode.ok);
				bean.setMessage("删除会员信息，操作成功");
			} else {
				bean.setStatusCode(DWZ.statusCode.error);
				bean.setMessage("删除会员信息，操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setStatusCode(DWZ.statusCode.error);
			bean.setMessage("删除会员信息，操作失败");
		}
		bean.setNavTabId("module2_memberinfo"); // 这里也重要
		bean.setRel("");
		bean.setCallbackType(""); // 这里回调不能是关闭当前页面
		bean.setForwardUrl("");

		// 返回验证后的状态
		return Json.toJson(bean);
	}

	// ----------------拉黑会员信息-----------------
	// 拉黑会员
	@At("/m5_disableMember")
	@Ok("json")
	public String m5_disableMember(@Param("mid") String mid,
			HttpServletRequest request) {
		// 简单跳转
		log.info("mb id=" + mid);
		// 查询。会员对象
		Member member = this.memberService.getMemberByMemberCode(mid);
		member.setStatus("2");

		ReturnBean bean = new ReturnBean();
		try {
			boolean flag = this.memberService.updateMember(member);
			if (flag) {
				bean.setStatusCode(DWZ.statusCode.ok);
				bean.setMessage("拉黑会员信息，操作成功");
			} else {
				bean.setStatusCode(DWZ.statusCode.error);
				bean.setMessage("拉黑会员信息，操作失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			bean.setStatusCode(DWZ.statusCode.error);
			bean.setMessage("拉黑会员信息，操作失败");
		}
		bean.setNavTabId("module2_memberinfo"); // 这里是当前 navTab ID
		bean.setRel("");
		bean.setCallbackType(""); // 这里回调不能是关闭当前页面
		bean.setForwardUrl("");

		// 返回验证后的状态
		return Json.toJson(bean);
	}

}
