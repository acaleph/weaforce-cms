package com.weaforce.entity.admin;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value = { "userLogin", "userName" })
public class UserJson extends User {

	private static final long serialVersionUID = -6421135631536633552L;

}
