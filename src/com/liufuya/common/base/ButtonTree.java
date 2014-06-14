package com.liufuya.common.base;

import java.util.List;

/**
 * 创建菜单时，按钮的树
 * 
 * @author lililiu
 * 
 */
public class ButtonTree implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private String id;// 标识
	private String text;// 显示内容
	private String state = "open";  // open,closed
	private boolean checked = false; // 节点是否被选中。
	private String iconCls = "iconCls";// ico图标

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

}
