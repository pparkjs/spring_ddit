package kr.or.ddit.main.dao;

import java.util.List;

import kr.or.ddit.vo.BoardVO;

public interface IMainDAO {

	public List<BoardVO> selectBoardList();

	public List<BoardVO> selectNoticeList();

	public List<BoardVO> selectFreeList();

	public int boardCount();

	public int noticeCount();

	public int freeCount();

}
