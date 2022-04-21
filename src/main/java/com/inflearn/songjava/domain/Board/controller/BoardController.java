package com.inflearn.songjava.domain.Board.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inflearn.songjava.common.BaseResponse;
import com.inflearn.songjava.domain.Board.service.BoardService;
import com.inflearn.songjava.domain.Board.vo.BoardDTO;
import com.inflearn.songjava.domain.Board.vo.BoardSearchVO;
import com.inflearn.songjava.domain.Board.vo.BoardVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 게시판 Controller
 * 
 * @author seohong
 *
 */
@RestController
@RequestMapping("/board")
@Api(tags = "게시판 API")
public class BoardController {
	@Autowired
	private BoardService boardService;
	private final Logger logger = LoggerFactory.getLogger(BoardController.class);

	/**
	 * 게시판 목록 조회
	 * 
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "게시물 전체 조회", notes = "전체 게시물을 조회할 수 있습니다.")
	public BaseResponse<List<BoardVO>> getList(BoardSearchVO searchVO) {
		return new BaseResponse<List<BoardVO>>(boardService.getList(searchVO));
	};

	/**
	 * 상세 정보 조회
	 * 
	 * @param boardSeq
	 * @return
	 */
	@GetMapping("/{boardSeq}")
	@ApiOperation(value = "게시물 상세 조회", notes = "게시물 번호에 해당하는 상세 정보를 조회할 수 있습니다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "19") })
	public BaseResponse<BoardVO> get(@PathVariable int boardSeq) {
		return new BaseResponse<BoardVO>(boardService.get(boardSeq));
	};

	/**
	 * 게시글 등록 / 수정
	 * 
	 * @param board
	 */
	@PostMapping("/save")
	@ApiOperation(value = "등록 / 수정 처리", notes = "신규 게시물 저장 및 기존 게시물 업데이트가 가능합니다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1"),
			@ApiImplicitParam(name = "title", value = "제목", example = "spring"),
			@ApiImplicitParam(name = "contents", value = "내용", example = "spring 강좌") })
	public BaseResponse<Integer> save(BoardDTO board) {
		boardService.save(board);
		return new BaseResponse<Integer>(board.getBoardSeq());
	};

	/**
	 * List를 이용하여 대용량 등록처리
	 */
//	@ApiOperation(value = "대용량 등록처리", notes = "List를 사용하여 대용량 등록처리")
//	@PostMapping("/saveList")
//	public BaseResponse<Boolean> saveList() {
//		List<BoardDTO> list = new ArrayList<BoardDTO>();
//
//		for (int i = 0; i < 10000; i++) {
//			String title = RandomStringUtils.randomAlphabetic(10);
//			String contents = RandomStringUtils.randomAlphabetic(10);
//			list.add(new BoardDTO(title, contents));
//		}
//
//		long start = System.currentTimeMillis();
//		boardService.saveList(list);
//		long end = System.currentTimeMillis();
//		logger.info("Save List 실행 시간 : {}", (end - start) / 1000);
//		return new BaseResponse<Boolean>(true);
//	}

	/**
	 * Map을 이용하여 대용량 등록처리
	 */
	@ApiOperation(value = "대용량 등록처리", notes = "Map을 이용하여 대용량 등록처리")
	@PostMapping("/saveListInMap")
	public BaseResponse<Boolean> saveListInMap() {
		List<BoardDTO> list = new ArrayList<BoardDTO>();

		IntStream.range(0, 100).forEach(i -> {
			String title = RandomStringUtils.randomAlphabetic(10);
			String contents = RandomStringUtils.randomAlphabetic(10);
			list.add(new BoardDTO(title, contents));
		});

		long start = System.currentTimeMillis();
		boardService.saveListInMap(list);
		long end = System.currentTimeMillis();
		logger.info("Save List In Map 실행 시간 : {}", (end - start) / 1000);
		return new BaseResponse<Boolean>(true);
	}

	/**
	 * 게시글 삭제
	 * 
	 * @param boardSeq
	 */
	@DeleteMapping("/{boardSeq}")
	@ApiOperation(value = "삭제 처리", notes = "게시물 번호에 해당하는 정보를 삭제합니다.")
	@ApiImplicitParams({ @ApiImplicitParam(name = "boardSeq", value = "게시물 번호", example = "1") })
	public BaseResponse<Boolean> delete(@PathVariable int boardSeq) {
		return new BaseResponse<Boolean>(boardService.delete(boardSeq));
	};
}
