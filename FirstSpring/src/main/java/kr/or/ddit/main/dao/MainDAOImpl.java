package kr.or.ddit.main.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.BoardVO;

@Repository
public class MainDAOImpl implements IMainDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<BoardVO> selectBoardList() {
		return sqlSession.selectList("Main.selectBoardList");
	}

	@Override
	public List<BoardVO> selectNoticeList() {
		return sqlSession.selectList("Main.selectNoticeList");
	}

	@Override
	public List<BoardVO> selectFreeList() {
		return sqlSession.selectList("Main.selectFreeList");
	}

	@Override
	public int boardCount() {
		return sqlSession.selectOne("Main.boardCount");
	}

	@Override
	public int noticeCount() {
		return sqlSession.selectOne("Main.noticeCount");
	}

	@Override
	public int freeCount() {
		return sqlSession.selectOne("Main.freeCount");
	}

}
