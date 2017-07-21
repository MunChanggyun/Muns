package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;

public class MemberPwdSearchService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;	
		String member_id = request.getParameter("member_id");
		String member_dong = request.getParameter("member_dong");
		String member_ho = request.getParameter("member_ho");
		String member_pw = null;
        forward = new ActionForward();
		boolean check = false;
		
		try{		
			MemberDao memberDao = new MemberDao();			
			check = memberDao.MemberPwdSearchById(member_id, member_dong, member_ho);
			member_pw = memberDao.MemberPwdSendEmail(member_id);
			
			if(!check){				
				forward.setRedirect(false);
				forward.setPath("/view/member/member_pwd_search.jsp");
			}else{
				request.setAttribute("member_id",member_id);
				request.setAttribute("member_pw",member_pw);
				forward.setRedirect(false);
				forward.setPath("MemberPwdSendEmail.member"); //수정 선화 17.04.29
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
	
}