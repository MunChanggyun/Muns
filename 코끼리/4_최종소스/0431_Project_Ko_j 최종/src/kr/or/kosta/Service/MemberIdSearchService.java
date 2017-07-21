package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;



public class MemberIdSearchService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request,
         HttpServletResponse response) {
      ActionForward forward = null;

      try{
         String member_zipcode = request.getParameter("zipcode");
         String member_dong = request.getParameter("dong");
         String member_ho = request.getParameter("ho");   
         forward = new ActionForward();
         
         MemberDao memberDao = new MemberDao();
         String member_id = memberDao.MemberIdSearchByZipcode(member_zipcode, member_dong, member_ho);
         if(member_id.equals("no")){
             request.setAttribute("id", "입력하신 정보에 맞는 ID 없습니다.다시입력하세요");
             forward.setPath("/view/member/member_id_search_ok.jsp");
             forward.setRedirect(false);
           }else{
               request.setAttribute("id", member_id);
               forward.setPath("/view/member/member_id_search_ok.jsp");
               forward.setRedirect(false);
           }    
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      return forward;
   }
      
}
   