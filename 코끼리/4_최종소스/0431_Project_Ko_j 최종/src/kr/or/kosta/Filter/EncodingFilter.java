package kr.or.kosta.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class EncodingFilter implements Filter {
	
	private String charset="utf-8";
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
	
    public EncodingFilter() {
        // TODO Auto-generated constructor stub
    }


	public void destroy() {
		// TODO Auto-generated method stub
	}


	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest h_request =(HttpServletRequest)request;
		HttpServletResponse h_response=(HttpServletResponse) response;
		String uri= h_request.getRequestURI();
		
		HttpSession h_session= h_request.getSession();
		
		if(uri.indexOf("Logout.member")>0){			
			if(h_session.getAttribute("session_authority")!=null){
			h_session.invalidate();
	    	request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
			}else{
				request.setCharacterEncoding(charset);
				chain.doFilter(request, response);
		  }
		}		
		request.setCharacterEncoding(charset);
		chain.doFilter(request, response);
		
	}
	

}
