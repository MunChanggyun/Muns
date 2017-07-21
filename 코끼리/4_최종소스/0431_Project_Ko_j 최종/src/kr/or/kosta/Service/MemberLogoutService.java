package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;





public class MemberLogoutService implements Action{
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
	   ActionForward forward =new ActionForward();

	   HttpSession session=request.getSession();  
       session.invalidate();   

	   forward.setRedirect(false);
       forward.setPath("/view/homepage.jsp");
      return forward;
   }

}