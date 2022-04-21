package com.inflearn.songjava.domain.Board.vo;

import java.util.List;

import com.inflearn.songjava.domain.Board.type.BoardType;

import lombok.Data;

/**
 * 게시물 검색 VO
 */
@Data
public class BoardSearchVO {
	private String keyword;
	private List<BoardType> boardTypes;
}
