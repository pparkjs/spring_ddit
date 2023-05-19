package kr.or.ddit.main.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.main.service.IMainService;
import kr.or.ddit.vo.BoardVO;

@Controller
public class MainController {
	
	@Inject
	private IMainService mainService;
	
	@RequestMapping(value={"/","/main.do"}, method = RequestMethod.GET)
	public String main(Model model, HttpServletRequest request) {
		
		
		HttpSession session = request.getSession();
		// 검색 데이터 세션 삭제 
		session.invalidate();
		
		// 일반게시판 게시글 총 5개의 정보를 가져와서 메인화면에 뿌릴거임
		List<BoardVO> boardList = mainService.selectBoardList();
		List<BoardVO> noticeList = mainService.selectNoticeList();
		List<BoardVO> freeList = mainService.selectFreeList();
		
		int bTotalCount = mainService.boardCount();
		int nTotalCount = mainService.noticeCount();
		int fTotalCount = mainService.freeCount();
		
		model.addAttribute("bCnt", bTotalCount);
		model.addAttribute("nCnt", nTotalCount);
		model.addAttribute("fCnt", fTotalCount);
		
		model.addAttribute("boardList", boardList);
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("freeList", freeList);
		return "main";
	}
	
}