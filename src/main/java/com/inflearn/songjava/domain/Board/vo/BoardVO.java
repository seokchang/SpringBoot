package com.inflearn.songjava.domain.Board.vo;

import com.inflearn.songjava.domain.Board.type.BoardType;

import lombok.Data;

@Data
public class BoardVO {
	private int boardSeq;
	private BoardType boardType;
	private String title;
	private String contents;
	private String regDate;
}
