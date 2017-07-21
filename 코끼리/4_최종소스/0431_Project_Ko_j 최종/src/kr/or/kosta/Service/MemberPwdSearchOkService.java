package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;

public class MemberPwdSearchOkService implements Action  {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward =new ActionForward();
		int result = 0;

		
		HttpSession session= request.getSession();
		String member_id= (String) session.getAttribute("sessionid");
		String member_pw = request.getParameter("member_pw");
		
		try{
		MemberDao memberDao = new MemberDao();
		
		result = memberDao.MemberPasswordEdit(member_id, member_pw);
		
		if(result>0){
			forward.setRedirect(false);
			forward.setPath("../../index.html");
		}else{
			forward.setRedirect(false);
			forward.setPath("/view/member/member_pwd_search_ok.jsp");
		}
		
		
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
	
}