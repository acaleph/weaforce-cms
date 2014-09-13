package com.weaforce.cms.util;

import java.util.HashMap;
import java.util.Map;

import com.weaforce.cms.constant.TemplateType;

public class Cms {
	public static Map<String, String> templateType = new HashMap<String, String>();
	static {
		templateType.put("CHANNEL", "频道");
		templateType.put("CATEGORY", "栏目");
		templateType.put("CELL", "单元");
		templateType.put("ALBUM", "像集");
		templateType.put("ARTICLE", "文章");
	}

	/**
	 * Get the template type
	 * 
	 * @param typeId
	 *            Type ID
	 * @return
	 */
	public static TemplateType getTemplateType(String typeId) {
		for (TemplateType o : TemplateType.values()) {
			if (typeId.equals(o.getTypeId()))
				return o;
		}
		throw new RuntimeException("No template type found for " + typeId);
	}

	public static void main(String[] args) {
		System.out.println("Type: " + getTemplateType("CHANNEL"));
	}
}
