package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.CommentsDto;

public class BoardCommentWriteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		try{
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
			// 넘어온 값을 변수에 저장
		    int boardIdx = Integer.parseInt(request.getParameter("boardIdx"));
		    String memberId = (String)(session.getAttribute("session_id"));
		    String commentContent=request.getParameter("commentContent");
		    
		    // 변수에 저장한 값을 객체에 저장
		    CommentsDto comment = new CommentsDto();
		    comment.setBoard_id(boardIdx);
		    comment.setMember_id(memberId);
		    comment.setComment_content(commentContent);
		    
			// Dao에 있는 함수로 객체보내기
		    BoardDao boardDao =new BoardDao();
		    boardDao.CommentWrite(comment);
		    
		    BoardDto boardDto = boardDao.BoardgetContentByBoardId(boardIdx);
		    
		    int categoryCode = boardDto.getBoardCategory_code();
		    request.setAttribute("categoryCode", categoryCode);
			
			// 다음 페이지 지정
		    forward.setRedirect(false);
		    forward.setPath("/DetailContents.board?boardIdx=" + boardIdx+"&categoryCode="+categoryCode); // 댓글을 썼던 글 상세보기 페이지로 넘어가기
		    
		    
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}	
}
