package com.liufuya.common.base;

import java.util.List;

/**
 * 表格加载类
 * @author caryCheng
 *
 */
public class Datagrid {
	private List rows; // 查询对象集合
	private int total;// 查询对象总数
	public  List getRows() {
		return rows;
	}
	public void setRows(List rows) {
		this.rows = rows;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
}
