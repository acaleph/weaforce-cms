package com.weaforce.core.exception;

public class ServiceException extends RuntimeException {

	/**
	 * 统一的用于业务层的运行时异常. 继承于RuntimeException,用于触发Spring
	 * TransactionManager的rollback行为.
	 */
	private static final long serialVersionUID = 7844247980596860699L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
