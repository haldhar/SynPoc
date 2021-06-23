package com.order.orderdemo.exception;

public class BaseException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public final String errorCode;
	public final String status;
	public final String message;

	public BaseException(String exception, String errorCode, String status, String message) {
		super(exception);
		this.errorCode = errorCode;
		this.status = status;
		this.message = message;
	}

	/**
	 * @return errorCode
	 *
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * @return status
	 *
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @return message
	 *
	 */
	public String getMessage() {
		return message;
	}

}
