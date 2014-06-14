package com.liufuya.common.base;

import java.util.List;

/**
 * easyUi tree模型
 * @author caryCheng
 *
 */
public class Tree implements java.io.Serializable{
	
	private static final long serialVersionUID = 1L;
	private String id;//标识
	private String text;//显示内容
	private String state = "open";// open,closed
	private boolean checked = false;//节点是否被选中。
	private List<Tree> children;//子节点
	private Object attributes;//绑定到节点的自定义属性。
	private String iconCls="iconCls";//ico图标
	private String pid;//父节点

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

	public Object getAttributes() {
		return attributes;
	}

	public void setAttributes(Object attributes) {
		this.attributes = attributes;
	}

	public List<Tree> getChildren() {
		return children;
	}

	public void setChildren(List<Tree> children) {
		this.children = children;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
}
