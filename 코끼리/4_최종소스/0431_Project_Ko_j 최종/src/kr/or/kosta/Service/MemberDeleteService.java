package kr.or.kosta.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;

public class MemberDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("member_id");
		ActionForward forward = null;
		int row = 0;
		
		try {
			
			forward = new ActionForward();
			MemberDao dao = new MemberDao();
			row = dao.MemberDelete(id);			
				forward.setPath("MemberList.member");
				forward.setRedirect(false);			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forward;
	}

}
