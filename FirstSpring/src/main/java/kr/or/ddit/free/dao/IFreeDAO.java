package kr.or.ddit.free.dao;

import java.util.List;

import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeDAO {

	public int freeInsert(FreeVO freeVO);
	public FreeVO selectFree(int boNo);
	public void incrementHit(int boNo);
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);
	public int freeUpdate(FreeVO freeVO);
	public int freeDelete(int boNo);

}
