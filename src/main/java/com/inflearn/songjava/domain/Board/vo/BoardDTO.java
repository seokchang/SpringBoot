package com.inflearn.songjava.domain.Board.vo;

import com.inflearn.songjava.domain.Board.type.BoardType;

import lombok.Data;

@Data
public class BoardDTO {
	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;

	public BoardDTO() {

	}

	public BoardDTO(String title, String contents) {
		this.title = title;
		this.contents = contents;
	}

}
