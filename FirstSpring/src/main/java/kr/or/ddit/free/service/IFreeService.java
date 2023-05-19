package kr.or.ddit.free.service;

import java.util.List;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface IFreeService {

	public ServiceResult freeInsert(FreeVO freeVO);
	public FreeVO selectFree(int boNo);
	public int selectFreeCount(PaginationInfoVO<FreeVO> pagingVO);
	public List<FreeVO> selectFreeList(PaginationInfoVO<FreeVO> pagingVO);
	public ServiceResult freeUpdate(FreeVO freeVO);
	public ServiceResult freeDelete(int boNo);

}
