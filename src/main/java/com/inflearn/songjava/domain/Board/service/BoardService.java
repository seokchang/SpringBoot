package com.inflearn.songjava.domain.Board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.inflearn.songjava.common.BaseResponseCode;
import com.inflearn.songjava.domain.Board.repository.BoardRepository;
import com.inflearn.songjava.domain.Board.vo.BoardDTO;
import com.inflearn.songjava.domain.Board.vo.BoardSearchVO;
import com.inflearn.songjava.domain.Board.vo.BoardVO;
import com.inflearn.songjava.exception.BaseException;

/**
 * 게시판 서비스
 * 
 * @author seohong
 */
@Service
public class BoardService {
	@Autowired
	private BoardRepository repository;

	/**
	 * 게시판 목록 조회
	 * 
	 * @return
	 */
	public List<BoardVO> getList(BoardSearchVO searchVO) {
		return repository.getList(searchVO);
	};

	/**
	 * 상세 정보 조회
	 * 
	 * @param boardSeq
	 * @return
	 */
	public BoardVO get(int boardSeq) {
		BoardVO board = repository.get(boardSeq);
		if (board == null) {
			throw new BaseException(BaseResponseCode.DATA_IS_NULL, new String[] { "게시글" });
		}
		return board;
	};

	/**
	 * 게시글 등록
	 * 
	 * @param board
	 */
	@SuppressWarnings("deprecation")
	public int save(BoardDTO board) {
		// 제목 입력 여부 확인
		if (StringUtils.isEmpty(board.getTitle())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "title", "제목" });
		}

		// 내용 입력 여부 확인
		if (StringUtils.isEmpty(board.getContents())) {
			throw new BaseException(BaseResponseCode.VALIDATE_REQUIRED, new String[] { "contents", "내용" });
		}

		BoardVO boardVO = repository.get(board.getBoardSeq());
		// 등록된 게시글 수정
		if (boardVO != null) {
			repository.update(board);
			return board.getBoardSeq();
		}
		repository.save(board);
		return board.getBoardSeq();
	};

	/**
	 * 반복문을 이용하여 게시글 등록 처리
	 */
	public void saveList(List<BoardDTO> list) {
		list.forEach(dto -> repository.save(dto));
	}

	/**
	 * Map을 인자로 하여 게시글 등록 처리
	 */
	public void saveListInMap(List<BoardDTO> list) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("boardList", list);
		repository.saveList(paramMap);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param boardSeq
	 */
	public boolean delete(int boardSeq) {
		BoardVO boardVO = repository.get(boardSeq);
		if (boardVO != null) {
			repository.delete(boardSeq);
			return true;
		}
		return false;
	};
}