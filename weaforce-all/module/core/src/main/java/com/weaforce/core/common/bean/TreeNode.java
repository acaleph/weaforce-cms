package com.weaforce.core.common.bean;

import java.util.ArrayList;
import java.util.List;

public class TreeNode implements Comparable<TreeNode> {
	private Long id;
	private Long pid;
	private String name;
	private String url;
	private String option;
	private boolean checked = false;
	private boolean nocheck = false;
	private boolean open = true;
	private boolean isParent = true; // Has child nodes
	private Long seq;
	private List<TreeNode> children = new ArrayList<TreeNode>();

	public TreeNode() {

	}

	public TreeNode(Long id, Long pid, String name) {
		this.id = id;
		this.pid = pid;
		this.name = name;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public boolean getChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isNocheck() {
		return nocheck;
	}

	public void setNocheck(boolean nocheck) {
		this.nocheck = nocheck;
	}

	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}

	public boolean getIsParent() {
		return isParent;
	}

	public void setIsParent(boolean isParent) {
		this.isParent = isParent;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}

	@Override
	public int compareTo(TreeNode o) {
		return this.getSeq().compareTo(o.getSeq());
	}

	public String getOption() {
		return option;
	}

	public void setOption(String option) {
		this.option = option;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
