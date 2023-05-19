package kr.or.ddit.free.dao;

import java.util.List;

import javax.inject.Inject;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Repository
public class FreeDAOImpl implements IFreeDAO {
	
	@Inject
	private SqlSessionTemplate sqlSession;
	
	@Override
	public int freeInsert(FreeVO freeVO) {
		return sqlSession.insert("Free.freeInsert", freeVO);
	}

	@Override
	public void incrementHit(int boNo) {
		sqlSession.update("Free.incrementHit", boNo);
	}
	
	@Override
	public FreeVO selectFree(int boNo) {
		return sqlSession.selectOne("Free.selectFree", boNo);
	}

	@Override
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectOne("Free.selectFreeCount", pagingVO);
	}

	@Override
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO) {
		return sqlSession.selectList("Free.selectFreeList", pagingVO);
	}

	@Override
	public int freeUpdate(FreeVO freeVO) {
		return sqlSession.update("Free.freeUpdate", freeVO);
	}

	@Override
	public int freeDelete(int boNo) {
		return sqlSession.delete("Free.freeDelete", boNo);
	}



}
