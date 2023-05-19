package kr.or.ddit.notice.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.notice.service.INoticeService;
import kr.or.ddit.vo.NoticeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/notice")
public class NoticeController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/list.do")
	public String noticeList(
			HttpServletRequest request,
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			Model model
			) {
		PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
		
		HttpSession session = request.getSession();
		
		String sWord = (String)session.getAttribute("searchWord");
		String sType = (String)session.getAttribute("searchType");
		
		// 검색 했을 때
		if(StringUtils.isNotBlank(sWord)) {
			pagingVO.setSearchType(sType);
			pagingVO.setSearchWord(sWord);
			model.addAttribute("searchType", sType);
			model.addAttribute("searchWord", sWord);
		}
		
		pagingVO.setCurrentPage(currentPage);
		
		//목록 게시글 수 가져오기
		int totalRecord = noticeService.selectNoticeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		return "notice/list";
	}
	
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String noticeForm() {
		return "notice/form";
	}
	
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String noticeInsert(NoticeVO noticeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) { // 에러 발생
			model.addAttribute("errors", errors);
			model.addAttribute("notice", noticeVO);
			goPage = "notice/form";
		}else {
			noticeVO.setBoWriter("관리자");
			ServiceResult result = noticeService.noticeInsert(noticeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
			}else {
				errors.put("msg", "서버 에러! 다시 시도해주세요");
				model.addAttribute("errors", errors);
				goPage = "notice/form";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String noticeDetail(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		model.addAttribute("notice", noticeVO);
		return "notice/view";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String noticeUpdateForm(int boNo, Model model) {
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "u");
		return "notice/form";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String noticeUpdate(NoticeVO noticeVO, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.noticeUpdate(noticeVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/detail.do?boNo=" + noticeVO.getBoNo();
		}else {
			model.addAttribute("notice", noticeVO);
			model.addAttribute("status", "u");
			goPage = "notice/form";
		}
		return goPage;
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET)
	public String noticeDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = noticeService.noticeDelete(boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/notice/list.do";
		}else {
			goPage = "redirect:/notice/detail.do?boNo=" + boNo;
		}
		
		return goPage;
	}
}
