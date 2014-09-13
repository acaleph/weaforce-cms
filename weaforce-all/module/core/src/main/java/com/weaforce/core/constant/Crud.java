package com.weaforce.core.constant;

public enum Crud {
	Create("0"), Read("1"), Update("2"), Delete("3"), List("4");
	private String flag;

	private Crud(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return this.flag;
	}
}
