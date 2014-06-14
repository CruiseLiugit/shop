<%@ page language="java" import="java.util.*" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/taglibs.jsp"%>



<div class="pageContent">
	    
		<div class="pageFormContent" layoutH="56">
		<h2 class="contentTitle"><a href="${base }/resources/doc/mod.xlsx" target="_blank">模版下载</a></h2>
			<div class="unit" >
				<label>文件上传：</label>
				<input id="testFileInput2" type="file" name="image2" 
		uploaderOption="{
			swf:'uploadify/scripts/uploadify.swf',
			uploader:'demo/common/ajaxDone.html',
			formData:{PHPSESSID:'xxx', ajax:1},
			queueID:'fileQueue',
			buttonImage:'uploadify/img/add.jpg',
			buttonClass:'my-uploadify-button',
			width:102,
			auto:false
		}"
	/>
				<input id="testFileInput" type="file" name="image" 
		uploaderOption="{
			swf:'${base }/resources/uploadify/scripts/uploadify.swf',
			uploader:'${base }/resources/demo/common/ajaxDone.html',
			formData:{PHPSESSID:'xxx', ajax:1},
			buttonText:'请选择文件',
			fileSizeLimit:'200KB',
			fileTypeDesc:'*.xlsx;*.jpg;*.jpeg;*.gif;*.png;',
			fileTypeExts:'*.xlsx;*.jpg;*.jpeg;*.gif;*.png;',
			auto:true,
			multi:true,
			onUploadSuccess:uploadifySuccess,
			onQueueComplete:uploadifyQueueComplete
		}"
	/>
			</div>
			
			
			
		</div>
		<div class="formBar">
			<ul>
				<!--<li><a class="buttonActive" href="javascript:;"><span>保存</span></a></li>-->
				<li><div class="buttonActive"><div class="buttonContent"><button type="submit" id="adduserBtn">保存</button></div></div></li>
				<li>
					<div class="button"><div class="buttonContent"><button type="button" class="close">取消</button></div></div>
				</li>
			</ul>
		</div>
	
</div>

</form>


<script type="text/javascript">
var result = "";
function kkk(){
	//alert("实际参数个数 length :"+arguments.length);
	
	var json = arguments[0];
	//alert("arguments[0] :"+json);
	//alert("checked :"+json.checked);
	//alert("json items :"+json.items);
	
	$(json.items).each(function(i){
		//result += "{text: "+this.text+",value:"+this.value+"}";
		result += this.value+",";
	});
	$("#buttonchks").val(result);
	//alert("result :"+result);
}
</script>
