package com.weaforce.cms.exception;

/**
 * Cms公用的Exception.
 * <p>
 * 继承自RuntimeException,从被Spring声明式事务管理的函数中抛出时会触发事务回滚.
 * </p>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class CmsException extends RuntimeException {
	private static final long serialVersionUID = -1977326865255764320L;

	public CmsException() {
		super();
	}

	public CmsException(String message) {
		super(message);
	}

	public CmsException(Throwable cause) {
		super(cause);
	}
}
