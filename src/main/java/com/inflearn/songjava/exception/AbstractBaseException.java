package com.inflearn.songjava.exception;

import com.inflearn.songjava.common.BaseResponseCode;

public abstract class AbstractBaseException extends RuntimeException {
	private static final long serialVersionUID = 2053463624545219443L;

	protected BaseResponseCode responseCode;
	protected Object[] args;

	public AbstractBaseException() {
	}

	public AbstractBaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public BaseResponseCode getResponseCode() {
		return responseCode;
	}

	public Object[] getArgs() {
		return args;
	}

}
