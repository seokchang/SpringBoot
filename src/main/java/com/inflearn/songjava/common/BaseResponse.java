package com.inflearn.songjava.common;

import lombok.Data;

/**
 * 공통으로 사용할 응답 클래스
 * 
 * @author seohong
 *
 */
@Data
public class BaseResponse<T> {
	private BaseResponseCode responseCode;
	private String message;
	private T data;

	// 성공 response
	public BaseResponse(T data) {
		this.responseCode = BaseResponseCode.SUCCESS;
		this.data = data;
	}

	// 예외 response
	public BaseResponse(BaseResponseCode responseCode, String message) {
		this.responseCode = responseCode;
		this.message = message;
	}
}
