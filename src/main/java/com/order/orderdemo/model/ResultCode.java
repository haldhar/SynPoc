package com.order.orderdemo.model;

public enum ResultCode {

	SUCCESS("0000", "success", "Success"), DATA_NOT_FOUND("E0001", "success", "Data not found"),
	AUTHENTICATION_FAILURE("E0002", "Failure", "Authetication Failed."),
	UNAUTHORIZED("E0003", "Failure", "No token provided.");

	private String code;
	private String status;
	private String message;

	/**
	 * @param code
	 * @param status
	 * @param message
	 */
	private ResultCode(String code, String status, String message) {
		this.code = code;
		this.status = status;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	public static ResultCode getResponseStatusByCode(final String value) {
		ResultCode responseStatusToReturn = null;
		for (ResultCode resultCode : ResultCode.values()) {
			if (value != null && resultCode.getCode().equals(value)) {
				responseStatusToReturn = resultCode;
				break;
			}
		}
		return responseStatusToReturn;
	}
}
