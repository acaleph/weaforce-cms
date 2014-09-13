package com.weaforce.entity.app;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "menuDescription", "menuParent" })
public class MenuJson extends Menu {
	private static final long serialVersionUID = 8863039499201930203L;

}
