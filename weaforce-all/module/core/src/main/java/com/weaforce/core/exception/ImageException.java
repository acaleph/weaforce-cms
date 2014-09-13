package com.weaforce.core.exception;

public class ImageException extends RuntimeException {
	private static final long serialVersionUID = -6064229814856131423L;

	public ImageException(String message) {
		super(message);
	}

	public ImageException(Throwable t) {
		this(t.toString(), t);
	}

	public ImageException(String message, Throwable t) {
		super(message, t);
		this.setStackTrace(t.getStackTrace());
	}
}
