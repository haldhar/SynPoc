package com.order.orderdemo.exception;

public class AuthenticationException extends BaseException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String errorCode, String status, String message) {
		super("AuthenticationException", errorCode, status, message);
	}
}
