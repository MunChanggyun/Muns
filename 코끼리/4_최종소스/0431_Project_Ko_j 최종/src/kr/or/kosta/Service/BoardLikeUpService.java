package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.CommentsDto;

public class BoardLikeUpService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = new ActionForward();
		
		try{
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
		    int boardIdx = Integer.parseInt(request.getParameter("boardIdx"));
		    int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
		    		    
		    BoardDao boardDao =new BoardDao();
		    int rowcount = boardDao.UpBoardLike(boardIdx);
		    forward.setRedirect(false);
		    if(rowcount>0){
		    	forward.setPath("/DetailContents.board?boardIdx=" + boardIdx+"&categoryCode="+categoryCode); // 댓글을 썼던 글 상세보기 페이지로 넘어가기
		    }else{
		    	forward.setPath("/Write.board");
		    }
		    
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}	
}
