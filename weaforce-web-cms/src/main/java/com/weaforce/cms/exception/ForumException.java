package com.weaforce.cms.exception;

/**
 * Forum公用的Exception.
 * <p>
 * 继承自RuntimeException,从被Spring声明式事务管理的函数中抛出时会触发事务回滚.
 * </p>
 * 
 * @author acaleph8@yahoo.com.cn
 * 
 */
public class ForumException extends RuntimeException {
	private static final long serialVersionUID = -4758389365632738084L;

	public ForumException() {
		super();
	}

	public ForumException(String message) {
		super(message);
	}

	public ForumException(Throwable cause) {
		super(cause);
	}
}
