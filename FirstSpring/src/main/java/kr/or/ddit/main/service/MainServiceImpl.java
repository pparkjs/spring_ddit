package kr.or.ddit.main.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.main.dao.IMainDAO;
import kr.or.ddit.vo.BoardVO;

@Service
public class MainServiceImpl implements IMainService {
	
	@Inject
	private IMainDAO mainDao;
	
	@Override
	public List<BoardVO> selectBoardList() {
		return mainDao.selectBoardList();
	}

	@Override
	public List<BoardVO> selectNoticeList() {
		return mainDao.selectNoticeList();
	}

	@Override
	public List<BoardVO> selectFreeList() {
		return mainDao.selectFreeList();
	}

	@Override
	public int boardCount() {
		return mainDao.boardCount();
	}

	@Override
	public int noticeCount() {
		return mainDao.noticeCount();
	}

	@Override
	public int freeCount() {
		return mainDao.freeCount();
	}

}
