package kr.or.kosta.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Service.MemberIdCheckService;
import kr.or.kosta.Service.MemberIdSearchService;
import kr.or.kosta.Service.MemberJoinOkService;
import kr.or.kosta.Service.MemberDeleteService;
import kr.or.kosta.Service.MemberListService;
import kr.or.kosta.Service.MemberLoginOkService;
import kr.or.kosta.Service.MemberPwdSearchOkService;
import kr.or.kosta.Service.MemberPwdSearchService;
import kr.or.kosta.Service.MemberPwdSendEmailService;



@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MemberController() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
		
	}
	private void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String RequestURL = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String url_command = RequestURL.substring(ContextPath.length());
		
		Action action = null;
		ActionForward forward = null;		
		if(url_command.equals("/view/member/MemberJoinOk.member")){//최종 수정 선화
			action= new MemberJoinOkService();
			forward = action.execute(request, response);	
		}else if(url_command.equals("/MemberList.member")){//마지막 수정 찬섭 17.04.28
			action= new MemberListService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/MemberDelete.member")){ //마지막 수정 찬섭 17.04.28
			action=new MemberDeleteService();
			forward = action.execute(request, response);
			/*찬섭 Controller*/
		}else if(url_command.equals("/MemberLogin.member")){//마지막 수정 진학 17.04.28 
			action= new MemberLoginOkService();
			forward = action.execute(request, response);		
		}else if(url_command.equals("/view/member/MemberIdSearch.member")){ //최종수정 진학 17.04.27
			action= new MemberIdSearchService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/view/member/MemberPwdSearch.member")){ //최종수정 진학 17.04.27
			action= new MemberPwdSearchService();
			forward = action.execute(request, response);	
		}else if(url_command.equals("/view/member/MemberPwdSearchOk.member")){//최종수정 진학 17.04.27
			action= new MemberPwdSearchOkService();
			forward = action.execute(request, response);			
		}else if(url_command.equals("/MemberIdCheck.member")){
			action= new MemberIdCheckService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/view/member/MemberPwdSendEmail.member")){//추가 선화 17.04.29
			action= new MemberPwdSendEmailService();
			forward = action.execute(request, response);
		}		
		
		if(forward !=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
				
			}else{
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}	
		}
	}
}
