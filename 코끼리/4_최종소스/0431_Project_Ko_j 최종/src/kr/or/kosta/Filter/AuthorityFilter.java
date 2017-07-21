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
import javax.servlet.http.HttpSession;


@WebFilter("/MemberList.member")
public class AuthorityFilter implements Filter {
	
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}    
    public AuthorityFilter() {
        // TODO Auto-generated constructor stub
    }
	
	public void destroy() {
		// TODO Auto-generated method stub
	}	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {	
		HttpServletRequest h_request= (HttpServletRequest) request;
		
		HttpSession h_session= h_request.getSession();
		
	    if(h_session.getAttribute("session_authority") == null){
	    	request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);
		}else if((int)h_session.getAttribute("session_authority")==2){
			chain.doFilter(request, response);			
		}else {			
			request.getRequestDispatcher("/view/homepage.jsp").forward(request, response);	
		}		
	}

}
