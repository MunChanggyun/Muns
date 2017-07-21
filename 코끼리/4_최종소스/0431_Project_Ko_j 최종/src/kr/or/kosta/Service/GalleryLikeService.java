package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.GalleryDao;

public class GalleryLikeService implements Action{

   @Override
   public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
ActionForward forward = new ActionForward();
      
      try{
         request.setCharacterEncoding("UTF-8");
          response.setContentType("text/html; charset=UTF-8");
          
          int galleryIdx = Integer.parseInt(request.getParameter("gallery_id"));
          
                    
          GalleryDao boardDao =new GalleryDao();
          int rowcount = boardDao.UpBoardLike(galleryIdx);
          forward.setRedirect(false);
          if(rowcount>0){
             forward.setPath("/Gallery.board"); // 댓글을 썼던 글 상세보기 페이지로 넘어가기
          }else{
             forward.setPath("/Gallery.board");
          }
          
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      return forward;
   }

}