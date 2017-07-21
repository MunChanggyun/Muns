package kr.or.kosta.Service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;

public class MemberIdCheckService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		MemberDao dao = new MemberDao();
		
		String id = request.getParameter("id");
        try{
        	
            boolean check = dao.MemberCheckId(id);
            
			//check = false > 중복
            //check = true > 중복x
            if(check){
				request.setAttribute("check", "true");
				forward.setPath("/view/member/member_id_check_ok.jsp");
				forward.setRedirect(false);
			}else{
				request.setAttribute("check", "false");
				forward.setPath("/view/member/member_id_check_ok.jsp");
				forward.setRedirect(false);
			}
		
		}catch (Exception e) {
			
			e.printStackTrace();
		}

        return forward;

	}

}
