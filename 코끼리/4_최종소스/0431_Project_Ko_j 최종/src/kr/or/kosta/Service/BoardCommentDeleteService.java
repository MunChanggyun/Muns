package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;

public class BoardCommentDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		int boardIdx = Integer.parseInt(request.getParameter("boardIdx"));
		int commentIdx = Integer.parseInt(request.getParameter("commentIdx"));
		BoardDao boardDao = new BoardDao();
		BoardDto boardDto = new BoardDto();
		ActionForward forward = null;
		
		try{			
			forward = new ActionForward();
			/*if(boardIdx == 0){
				response.sendRedirect("/DetailContents.board?boardIdx"+boardIdx); // 원래 있던 페이지로 넘어가기
			}*/
			
			int result = boardDao.CommentDelete(commentIdx);
			
			boardDto = boardDao.BoardgetContentByBoardId(boardIdx);
		    
		    int categoryCode = boardDto.getBoardCategory_code();
		    request.setAttribute("categoryCode", categoryCode);
			if(result > 0){
				request.setAttribute("result", "success");
			}else{
				request.setAttribute("result", "fail");
			}
			
			
			forward.setRedirect(false);
			forward.setPath("/DetailContents.board?boardIdx=" + boardIdx + "&categoryCode="+categoryCode); // 상세 게시물 보기로 넘어가기
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
