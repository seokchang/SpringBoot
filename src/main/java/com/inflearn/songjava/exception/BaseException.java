package com.inflearn.songjava.exception;

import com.inflearn.songjava.common.BaseResponseCode;

public class BaseException extends AbstractBaseException {
	private static final long serialVersionUID = -1629002351393329287L;

	public BaseException() {
	}

	public BaseException(BaseResponseCode responseCode) {
		this.responseCode = responseCode;
	}

	public BaseException(BaseResponseCode responseCode, String[] args) {
		this.responseCode = responseCode;
		this.args = args;
	}

}
