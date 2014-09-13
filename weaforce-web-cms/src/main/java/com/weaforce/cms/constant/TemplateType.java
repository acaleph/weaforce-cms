package com.weaforce.cms.constant;

public enum TemplateType {
	CHANNEL("CHANNEL"), CATEGORY("CATEGORY"), ARTICLE("ARTICLE"), ALBUM("ALBUM"), CELL(
			"CELL");
	private final String typeId;

	private TemplateType(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeId() {
		return this.typeId;
	}
}
