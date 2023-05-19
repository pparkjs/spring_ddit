package kr.or.ddit.notice;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SearchFilter implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		String type = request.getParameter("searchType");
		String word = request.getParameter("searchWord");
		
		if(word != null) {
			HttpServletRequest req = (HttpServletRequest)request;
			
			HttpSession session = req.getSession();
			
			session.setAttribute("searchType", type);
			session.setAttribute("searchWord", word);
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}

}
