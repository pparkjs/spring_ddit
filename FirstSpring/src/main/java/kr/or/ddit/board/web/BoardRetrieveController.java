package kr.or.ddit.board.web;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.board.service.IBoardService;
import kr.or.ddit.vo.BoardVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/board")
public class BoardRetrieveController {
	
	@Inject
	private IBoardService boardService;
	
	@RequestMapping(value="/list.do")
	public String boardList(
			HttpServletRequest request,
			//required = false는 필수 요소가 아니라는 것을 설정한것 
			//ex currentPage에 값이 안들어오면  값이 1로 디폴트 설정 해준것이고 name은 jsp 페이지에서 page라는 name 으로 값을 보내고 서버단에서 currentPage라는 이름으로 바꾼것 설정 
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false)
			String searchWord,
			Model model) {
		// 일반적인 게시판 목록 조회(방법1) - 페이징이 구현되어 있지 않다.
//		List<BoardVO> boardList = boardService.selectBoardList();
//		model.addAttribute("boardList", boardList);
		
		// 페이징 및 검색이 적용된 목록 조회(방법2)
		PaginationInfoVO<BoardVO> pagingVO = new PaginationInfoVO<BoardVO>();
		
		// 브라우저에서 검색한 검색 유형, 검색 키워드를 이용하여 검색 처리
		// 검색 키워드가 있으면 검색을 한거고, 키워드가 없으면 검색을 하지 않음
		HttpSession session = request.getSession();
		
		String sWord = (String)session.getAttribute("searchWord");
		String sType = (String)session.getAttribute("searchType");
		
		
		// 검색 했을 때
		if(StringUtils.isNotBlank(searchWord) || StringUtils.isNotBlank(sWord)) {
			if(sWord == null) {
				pagingVO.setSearchType(searchType);
				pagingVO.setSearchWord(searchWord);
				model.addAttribute("searchType", searchType);
				model.addAttribute("searchWord", searchWord);
			}else {
				pagingVO.setSearchType(sType);
				pagingVO.setSearchWord(sWord);
				model.addAttribute("searchType", sType);
				model.addAttribute("searchWord", sWord);
			}
		}
		
		pagingVO.setCurrentPage(currentPage);
		// 목록 총 게시글 수 가져오기
		int totalRecord = boardService.selectBoardCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<BoardVO> dataList = boardService.selectBoardBoardList2(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		return "board/list";
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String boardDetail(int boNo, Model model) {
		BoardVO boardVO = boardService.selectBoard(boNo);
		model.addAttribute("board", boardVO);
		return "board/view";
	}
	
}
