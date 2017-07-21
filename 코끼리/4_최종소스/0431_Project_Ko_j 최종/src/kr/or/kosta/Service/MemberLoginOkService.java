package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;





public class MemberLoginOkService implements Action{
   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
      ActionForward forward = null;
      String memberid = request.getParameter("memberid");
      String memberpw = request.getParameter("memberpw");
      
      try{
         forward = new ActionForward();
         request.setCharacterEncoding("UTF-8");
         response.setContentType("text/html; charset=UTF-8");
         MemberDao memberDao = new MemberDao();
         
         boolean check = memberDao.MemberLogin(memberid, memberpw);
         
         if(check==true){
           HttpSession session= request.getSession();
            int authority=memberDao.getMemberAuthority(memberid);
            String zipcode=memberDao.getMemberZipcode(memberid);
            String memberurl=memberDao.getMemberUrl(zipcode);
  
           session.setAttribute("session_id", memberid);
           session.setAttribute("session_authority", authority);
           session.setAttribute("session_url", memberurl);
           
           if((String)session.getAttribute("session_url")==null){
                forward.setRedirect(false);
                  forward.setPath("/view/index_login.jsp");
             }else{
             forward.setRedirect(false);
              forward.setPath((String)session.getAttribute("session_url"));
             }
           }else{
              forward.setRedirect(false);
              forward.setPath("/view/index_login.jsp");
           }
                    
                  
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      return forward;
   }

}