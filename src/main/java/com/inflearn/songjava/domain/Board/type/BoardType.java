package com.inflearn.songjava.domain.Board.type;

public enum BoardType implements BaseCodeLabelEnum {
	NOTICE("공지 사항"), FAQ("자주묻는 질문"), INQUIRY("1:1문의"),;

	private String code;
	private String label;

	private BoardType(String label) {
		this.code = name(); // enum 값을 코드에 적용
		this.label = label;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String label() {
		return label;
	}

}
