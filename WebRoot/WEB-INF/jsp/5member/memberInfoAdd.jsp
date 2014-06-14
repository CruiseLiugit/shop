<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>

<script type="text/javascript">
  	
$(function(){
	  
	//生日下拉框
	$('#c_birthday').datebox({});  
	
	//关闭编辑菜单窗口
	$('#btn-cancel').click(function(){
		$('#edit-window').window('close');
	});
	// 加载上级菜单选项
	$("#levelId").blur(function(){
	var levelId = $("#levelId").val();  
	if(levelId!=null && levelId!=""){
		$.ajax({
		url : '${contextPath }/menus/getFmenusByLevelId.do?levelId='+levelId ,
		type : 'post',
		success : function(menusJsonStr) {				
		var obj = eval( "(" + menusJsonStr + ")" );// 将json格式转换成对象
			$("#fLevelId1").val(obj.menuName);
			$("#fmenuCode").val(obj.menuCode);
			}
		});
	}   
	
	});
	// 加载按钮
	var id = $("#id").val();
	$("#menus_button").tree({
	 checkbox: true,   
     url: '${contextPath }/menus/loadALLButtonTree.do?id='+id  	
	});	
	
});
  
  //  表单保存
  function 	saveMenus(){
   var selectButtonIds = getSelectedButtons();  
   if(selectButtonIds!=null && selectButtonIds!=""){
      var menusInputForm=$("#memberInputForm");
    	 menusInputForm.form('submit', {
	        url: '${contextPath }/createMemberInfo?selectButtonIds='+selectButtonIds,
	        success: function (msg) {
	            if (msg.match("1")) {	                              
	                grid.datagrid('reload');
	                win.window('close');
	            } else {
	               $.messager.alert('提示', '失败', 'info');
	            }
	        }
       });
   }else{
   $.messager.alert('提示', '请为菜单配置按钮!', 'info');  
   return ;
   }

  }
			

</script>

<!--增加会员窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="memberInputForm">
                <table cellpadding=3>
                	   <tr>
                        <td>真实姓名：</td>
                        <td><input id="c_realName" name="member.realName" type="text" style="width: 200px;" required="true" class="easyui-validatebox" missingMessage="不能为空" placeholder="请输入真实姓名" /></td>
                    </tr>
                    <tr>
                        <td>手机号码：</td>
                        <td><input id="c_telphone" name="member.telphone" type="text" style="width: 200px;" required="true" class="easyui-validatebox" validType="minLength[11]" missingMessage="手机号码为登录名"  placeholder="请输入手机号码" /></td>
                    </tr>
                    <tr>
                        <td>用户原始密码：</td>
                        <td><input id="c_loginPwd" name="member.loginPwd" type="text" style="width: 150px;"  readonly="readonly" value="123456" /></td>
                    </tr>
                    <tr>
                        <td>用户邮箱：</td>
                        <td><input id="c_email" name="member.email" type="text" style="width: 150px;" required="true" validType="email" class="easyui-validatebox" missingMessage="邮箱格式不正确" placeholder="请输入邮箱" /></td>
                    </tr>
                    <tr>
                        <td>用户性别：</td>
                        <td>
                        <input type="radio" name="member.sex" value="1"  checked="checked"/>男
					    <input type="radio" name="member.sex" value="0"  />女
				        </td>
                    </tr>
                     <tr>
                        <td>用户生日：</td>
                        <td><input id="c_birthday" name="member.birthday" type="text" style="width: 150px;" class="date" readonly="true" /></td>
                    </tr>
                     <tr>
                        <td>身份证号：</td>
                        <td><input id="c_cardNumber" name="member.card_number" type="text" style="width: 150px;" class="easyui-validatebox" validType="minLength[19]"  missingMessage="身份证号码格式不正确" placeholder="请输入身份证号码" /></td>
                    </tr>
                     <tr>
                        <td>所在城市：</td>
                        <td><input id="c_city" name="member.city" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空" placeholder="请输入所在城市，如上海市" /></td>
                    </tr>
                     <tr>
                        <td>所在县区：</td>
                        <td><input id="c_citypart" name="member.city_part" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空" placeholder="请输入所在城区，如徐汇区" /></td>
                    </tr>
                    <tr>
                        <td>详细地址：</td>
                        <td><input id="c_address" name="member.default_address" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空" placeholder="如 xx市xx区xx路2号楼203室" /></td>
                    </tr>
                    <tr>
                        <td>年龄段：</td>
                        <td><select class="required combox" name="member.age_area">
								<option value="0">请选择</option>
                    				<option value="60前">60前</option>
                    				<option value="60后">60后</option>
                    				<option value="70后">70后</option>
                    				<option value="80后" selected>80后</option>
                    				<option value="90后">90后</option>
                    				<option value="00后">00后</option>
                    				<option value="10后">10后</option>
							</select></td>
                    </tr>
                    <tr>
                        <td>职业：</td>
                        <td><select class="required combox" name="member.work_type">
								<option value="0">请选择</option>
								<option value="无职业" selected>无职业</option>
                    				<option value="科研人员">科研人员</option>
                    				<option value="律师/法务/合规">律师/法务/合规</option>
                    				<option value="教师">教师</option>
                    				<option value="医院/医疗/护理">医院/医疗/护理</option>
                    				<option value="公务员">公务员</option>
                    				<option value="在校学生">在校学生</option>
                    				<option value="翻译">翻译</option>
                    				<option value="建筑装潢/市政建设">建筑装潢/市政建设</option>
                    				<option value="行政/后勤">行政/后勤</option>
                    				<option value="互联网/电子商务/网游">互联网/电子商务/网游</option>
                    				<option value="销售人员">销售人员</option>
                    				<option value="采购">采购</option>	
                    				<option value="公关/媒介">公关/媒介</option>	
                    				<option value="酒店/旅游">酒店/旅游</option>	
                    				<option value="计算机软件">计算机软件</option>	
                    				<option value="物流/仓储">物流/仓储</option>	
                    				<option value="人力资源">人力资源</option>	
                    				<option value="艺术/设计">艺术/设计</option>
							</select></td>
                    </tr>
                    <tr>
                        <td>家庭收入：</td>
                        <td >    
                        <select class="required combox" name="member.family_money">
					<option value="0">请选择</option>
					<option value="1000以下">1000以下</option>
                    <option value="1000-3000">1000-3000</option>
                    <option value="3000-5000">3000-5000</option>
                    <option value="5000-10000" selected>5000-10000</option>
                    <option value="10000-20000">10000-20000</option>
                    <option value="20000-50000">20000-50000</option>
                    <option value="50000以上">50000以上</option>
				</select>                 
                        </td>
                    </tr>
                    <tr>
                        <td>实体卡号：</td>
                        <td><input id="c_entityCardNumber"  name="member.entityCardNumber" type="text" style="width: 150px;"  class="txt01"  placeholder="如果有实体卡，请输入实体卡号码"/></td>
                    </tr>
                    <tr>
                        <td>实体状态：</td>
                        <td><select class="required combox" name="member.entityCardStatus" id="c_usertype">
					<option value="0">请选择</option>
					<option value="1" selected>已开卡</option>
					<option value="2">已使用</option>
					<option value="3">已作废</option>
				</select></td>
                    </tr>
                    <tr>
                        <td>会员卡余额：</td>
                        <td><input id="c_cardBalance"  name="member.memberCard_balance" type="text" style="width: 150px;" class="txt01"  placeholder="请输入会员卡余额"/></td>
                    </tr>
                    <tr>
                        <td>会员卡积分：</td>
                        <td><input id="c_cardScore"  name="member.memberCard_score" type="text" style="width: 150px;"  class="txt01"  placeholder="请输入会员卡积分"/></td>
                    </tr>
                </table>
              </form>
           </div>
           
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btn-save" class="easyui-linkbutton" onclick="saveMenus();" icon="icon-save" href="javascript:void(0);">保存</a> 
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">取消</a>
            </div>
</div>

