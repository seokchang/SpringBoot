package com.inflearn.songjava.domain.Board.repository;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.inflearn.songjava.domain.Board.vo.BoardDTO;
import com.inflearn.songjava.domain.Board.vo.BoardSearchVO;
import com.inflearn.songjava.domain.Board.vo.BoardVO;

/**
 * 게시판 Repository
 * 
 * @author seohong
 *
 */
@Repository
public interface BoardRepository {
	List<BoardVO> getList(BoardSearchVO searchVO);

	BoardVO get(int boardSeq);

	int save(BoardDTO board);

	void saveList(Map<String, Object> paramMap);

	void delete(int boardSeq);

	void update(BoardDTO board);

}
