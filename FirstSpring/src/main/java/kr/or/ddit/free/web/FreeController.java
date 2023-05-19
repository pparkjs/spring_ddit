package kr.or.ddit.free.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.free.service.IFreeService;
import kr.or.ddit.vo.FreeVO;
import kr.or.ddit.vo.PaginationInfoVO;

@Controller
@RequestMapping("/free")
public class FreeController {
	
	@Inject
	private IFreeService freeService;
	
	@RequestMapping(value="/list.do")
	public String freeList(
			HttpServletRequest request,
			@RequestParam(name="page", required = false, defaultValue = "1") int currentPage,
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model
			) {
		PaginationInfoVO<FreeVO> pagingVO = new PaginationInfoVO<FreeVO>();
		
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
		
		//목록 게시글 수 가져오기
		int totalRecord = freeService.selectFreeCount(pagingVO);
		pagingVO.setTotalRecord(totalRecord);
		List<FreeVO> dataList = freeService.selectFreeList(pagingVO);
		pagingVO.setDataList(dataList);
		model.addAttribute("pagingVO", pagingVO);
		return "free/list";
	}
	
	
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String freeForm() {
		return "free/form";
	}
	
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String freeInsert(FreeVO FreeVO, Model model) {
		String goPage = "";
		Map<String, String> errors = new HashMap<String, String>();
		
		if(StringUtils.isBlank(FreeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요");
		}
		if(StringUtils.isBlank(FreeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요");
		}
		
		if(errors.size() > 0) { // 에러 발생
			model.addAttribute("errors", errors);
			model.addAttribute("free", FreeVO);
			goPage = "free/form";
		}else {
			FreeVO.setBoWriter("a001");
			ServiceResult result = freeService.freeInsert(FreeVO);
			if(result.equals(ServiceResult.OK)) {
				goPage = "redirect:/free/detail.do?boNo=" + FreeVO.getBoNo();
			}else {
				errors.put("msg", "서버 에러! 다시 시도해주세요");
				model.addAttribute("errors", errors);
				goPage = "free/form";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String freeDetail(int boNo, Model model) {
		FreeVO FreeVO = freeService.selectFree(boNo);
		model.addAttribute("free", FreeVO);
		return "free/view";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String freeUpdateForm(int boNo, Model model) {
		FreeVO FreeVO = freeService.selectFree(boNo);
		model.addAttribute("free", FreeVO);
		model.addAttribute("status", "u");
		return "free/form";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String freeUpdate(FreeVO FreeVO, Model model) {
		String goPage = "";
		ServiceResult result = freeService.freeUpdate(FreeVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/detail.do?boNo=" + FreeVO.getBoNo();
		}else {
			model.addAttribute("free", FreeVO);
			model.addAttribute("status", "u");
			goPage = "free/form";
		}
		return goPage;
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.GET)
	public String freeDelete(int boNo, Model model) {
		String goPage = "";
		ServiceResult result = freeService.freeDelete(boNo);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/free/list.do";
		}else {
			goPage = "redirect:/free/detail.do?boNo=" + boNo;
		}
		
		return goPage;
	}
}
