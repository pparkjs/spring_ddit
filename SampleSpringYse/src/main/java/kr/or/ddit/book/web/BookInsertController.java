package kr.or.ddit.book.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/book")
public class BookInsertController {

/*
 * @Controller
	- 어노테이션에 있는 클래스는 스프링이 브라우저의 요청(request)을 받아들이는 컨트롤러라고 인지해서 자바 빈(Java Bean)으로 등록해서 관리한다.
	- 여기서 자바 빈이란 객체를 만들어서 메모리에 올리는 형태를 말한다.
 */
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	
	/*
	 * @RequestMapping

		- 요청 URL을 어떤 메소드가 처리할 지 여부를 결정
		
		method 속성은 http 요청 메소드를 의미한다.
		
		일반적인 웹 페이지 개발에서 GET 메소드는 데이터를 변경하지 않는 경우에, POST 메소드는 데이터가 변경될 경우 사용된다.
		
		ModelAndView
		- 컨트롤러가 반화노할 데이터를 담당하는 모델(Model)과 화면을 담당하는 뷰(View)의 경로를합쳐놓은 객체다.
		- 생성자에 문자열 타입 파라미터가 입력되면 뷰의 경로라고 간주한다.
		
		뷰의 경로를 ‘book/form’과 같은 형태로 전달하는 이유는 요청(request)에 해당하는 url의 mapping 되는 화면의 경로 값을 뒤에는 ‘.jsp’가 붙어 최종 위치에 해당하는 jsp 파일을 찾아준다.
	 */
	public ModelAndView bookForm() {
		//return new ModelAndView(”redirect:/book/form.do”); redirect 방식
		return new ModelAndView("book/form");//forward라고 할 수 있다.
	}
}
