package kr.or.kosta.Service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;

public class MemberJoinOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;

		try {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String pwdcheck = request.getParameter("pwdcheck");
			String zipcode = request.getParameter("zipcode");
			String dong = request.getParameter("dong");
			String ho = request.getParameter("ho");
			forward = new ActionForward();
			if(pwd.equals(pwdcheck)){
				MemberDao dao = new MemberDao();
				
				int row = dao.MemberJoin(id, pwd, zipcode, dong, ho);
				if(row>0){
					forward.setRedirect(false);
					forward.setPath("/view/index_login.jsp");
				}else{
					forward.setRedirect(false);
					forward.setPath("/view/member/join.jsp");
				}
				
			}else{
				forward.setRedirect(false);
				forward.setPath("member/MemberJoinOk.jsp");
			}

		} catch (Exception e) {
			
	
		}	
		return forward;
	}

}
