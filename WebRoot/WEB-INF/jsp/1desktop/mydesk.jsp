<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<div class="accountInfo">
	<div class="alertInfo">
		
		<h2>最新消息：</h2>
		<a href="#">通知：端午节放假时间 5月31日-6月2日，6月3日正常上班，各个部门安排人员轮流值班!</a>
	</div>
	
	<div class="right">
		<p>待办工作32项，消息212条</p>
		<p>05月31日，星期六</p>
	</div>
	<p><span>留夫鸭CRM管理系统 </span></p>
	<p>欢迎你:<a href="${base}/toEditPwd" target="dialog">刘立立</a></p>
</div>

<style type="text/css">
	ul.rightTools {float:right; display:block;}
	ul.rightTools li{float:left; display:block; margin-left:5px}
</style>

<div class="pageContent" style="padding:5px">
	<div class="panel" defH="40">
		<h1>日常工作查询</h1>
		<div>
			查询关键字：<input type="text" name="patientNo" />
			<ul class="rightTools">
				<li><a class="button" target="dialog" href="#" mask="true"><span>按门店名称查询</span></a></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>按产品名称查询</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>按订单编号查询</button></div></div></li>
				<li><div class="buttonDisabled"><div class="buttonContent"><button>按会员名称查询</button></div></div></li>
			</ul>
		</div>
	</div>
	<div class="divider"></div>
	<div class="tabs">
		<div class="tabsHeader">
			<div class="tabsHeaderContent">
				<ul>
					<li><a href="javascript:;"><span>门店概览</span></a></li>
					<li><a href="javascript:;"><span>产品概览</span></a></li>
					<li><a href="javascript:;"><span>订单概览</span></a></li>
					<li><a href="javascript:;"><span>会员概览</span></a></li>
					<li><a href="javascript:;"><span>积分概览</span></a></li>
				</ul>
			</div>
		</div>
		<div class="tabsContent">
			<div>
	
				<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
						<li><a href="javascript">门店概览</a>
							<ul>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">新开门店列表</a></li>
								<li><a href="demo/pagination/list1.html" target="ajax" rel="jbsxBox">门店订单查询</a></li>
							</ul>
						</li>
						
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
					<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="120">客户号</th>
				<th>客户名称</th>
				<th width="100">客户类型</th>
				<th width="150">证件号码</th>
				<th width="80" align="center">信用等级</th>
				<th width="80">所属行业</th>
				<th width="80">建档日期</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="1">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			<tr target="sid_user" rel="2">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			<tr target="sid_user" rel="3">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
		</tbody>
	</table>
				</div>
	
			</div>
			
			<div>
				<div layoutH="146" style="float:left; display:block; overflow:auto; width:240px; border:solid 1px #CCC; line-height:21px; background:#fff">
				    <ul class="tree treeFolder">
						<li><a href="javascript">产品概览</a>
							<ul>
								<li><a href="#" target="ajax" rel="jbsxBox">最新上架产品</a></li>
								<li><a href="#" target="ajax" rel="jbsxBox">最新促销产品</a></li>
							</ul>
						</li>
						
				     </ul>
				</div>
				
				<div id="jbsxBox" class="unitBox" style="margin-left:246px;">
					<!--#include virtual="list1.html" -->
					<table class="table" width="100%" layoutH="138">
		<thead>
			<tr>
				<th width="80"></th>
				<th width="120">客户号</th>
				<th>客户名称</th>
				<th width="100">客户类型</th>
				<th width="150">证件号码</th>
				<th width="80" align="center">信用等级</th>
				<th width="80">所属行业</th>
				<th width="80">建档日期</th>
			</tr>
		</thead>
		<tbody>
			<tr target="sid_user" rel="1">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			<tr target="sid_user" rel="2">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
			<tr target="sid_user" rel="3">
				<td>天津农信社</td>
				<td>A120113196309052434</td>
				<td>天津市华建装饰工程有限公司</td>
				<td>联社营业部</td>
				<td>29385739203816293</td>
				<td>5级</td>
				<td>政府机构</td>
				<td>2009-05-21</td>
			</tr>
		</tbody>
	</table>
				</div>
			</div>
			
			<div>订单概览
				
<script type="text/javascript">
    var options = {
    	stacked: false,
    	gutter:20,
		axis: "0 0 1 1", // Where to put the labels (trbl)
		axisystep: 10 // How many x interval labels to render (axisystep does the same for the y axis)
	};
	
	$(function() {
        // Creates canvas
        var r = Raphael("chartHolder");
        var data = [[10,20,30,50],[15,25,35,50]]
        
        // stacked: false
		var chart1 = r.barchart(40, 10, 320, 220, data, options).hover(function() {
            this.flag = r.popup(this.bar.x, this.bar.y, this.bar.value).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 500, ">", function () {this.remove();});
        });
		chart1.label([["A1",  "A2", "A3", "A4"],["B1",  "B2", "B3", "B4"]],true);
		
		
		// stacked: true
		options.stacked=true;
		
		var chart2 = r.barchart(400, 10, 320, 220, data, options).hoverColumn(function() {
    		var y = [], res = [];
            for (var i = this.bars.length; i--;) {
                y.push(this.bars[i].y);
                res.push(this.bars[i].value || "0");
            }
            this.flag = r.popup(this.bars[0].x, Math.min.apply(Math, y), res.join(", ")).insertBefore(this);
        }, function() {
            this.flag.animate({opacity: 0}, 500, ">", function () {this.remove();});
        });
		chart2.label([["A"],["B"],["C"],["D"]],true);
	});
</script>

				<div id="chartHolder"></div>
			</div>
			
			<div>会员概览
			
			</div>
			
			<div>积分概览</div>
		</div>
		<div class="tabsFooter">
			<div class="tabsFooterContent"></div>
		</div>
	</div>
	
</div>


