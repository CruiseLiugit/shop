<%@ page pageEncoding="UTF-8"%>
<%@include file="/jsp/common/taglibs.jsp"%>
<script type="text/javascript">
$(function(){
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
	$("#levelId").trigger("blur");
	// 加载按钮
	var id = $("#id").val();
	$("#menus_button").tree({
	 checkbox: true,   
     url: '${contextPath }/menus/loadALLButtonTree.do?id='+id  	
	});	  					
});
  
</script>

<!--菜单输入窗口-->
<div class="easyui-layout" fit="true">
          <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">
            <form method="post" id="menusInputForm">
                <table cellpadding=3>
                	<tr>
                        <td>
                        <input id ="id" name="menuCode" type="hidden" style="width: 150px;" required="true" class="txt01" value="${menus.menuCode}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        <input id="fmenuCode" name="fmenuCode" type="hidden" style="width: 150px;" required="true" class="txt01" value="${menus.fmenuCode}" readonly="readonly"/>
                        </td>
                    </tr>
                    <tr>
                        <td>菜单名称：</td>
                        <td><input id="menuName" name="menuName" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空" value="${menus.menuName }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>菜单等级：</td>
                        <td><input id="levelId" name="levelId" type="text" style="width: 150px;" required="true" class="easyui-validatebox" missingMessage="不能为空" value="${menus.levelId }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>上级菜单：</td>
                        <td >    
                        <input id="fLevelId1" name="fLevelId1" type="text" style="width: 150px;" readonly="true" required="true" class="txt01" value="" readonly="readonly"/>                  
                        </td>
                    </tr>
                    <tr>
                        <td>菜单英文名称：</td>
                        <td><input id="engName"  name="engName" type="text" style="width: 150px;"  required="true" class="txt01"  value="${menus.engName }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>菜单URL地址：</td>
                        <td><input id="menuUrl"  name="menuUrl" type="text" style="width: 150px;" required="true" class="txt01"  value="${menus.menuUrl }" readonly="readonly"/></td>
                    </tr>
                    <tr>
                        <td>菜单按钮：</td>
                    	<td>                                        
	                         <div region="center" border="false" style="padding: 10px; background: #fff; border: 1px solid #ccc;">                                                      		                       		
	        						<ul id="menus_button"></ul>         												      
	                     </div>		                                              				        			      					
                       </td>
                	</tr>
                </table>
              </form>
           </div>
           
            <div region="south" border="false" style="text-align: right; height: 30px; line-height: 30px;">
                <a id="btn-cancel" class="easyui-linkbutton" icon="icon-cancel" href="javascript:void(0);">关闭</a>
            </div>
</div>
  