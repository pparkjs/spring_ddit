package kr.or.ddit.notice.dao;

import java.util.List;

import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

public interface INoticeDAO {

	public int noticeInsert(NoticeVO noticeVO);
	public NoticeVO selectNotice(int boNo);
	public void incrementHit(int boNo);
	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	public int noticeUpdate(NoticeVO noticeVO);
	public int noticeDelete(int boNo);

}
