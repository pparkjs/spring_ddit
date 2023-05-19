package kr.or.ddit.main.service;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IMainService {

	public List<BoardVO> selectBoardList();

	public List<BoardVO> selectNoticeList();

	public List<BoardVO> selectFreeList();

	public int boardCount();

	public int noticeCount();

	public int freeCount();

}
