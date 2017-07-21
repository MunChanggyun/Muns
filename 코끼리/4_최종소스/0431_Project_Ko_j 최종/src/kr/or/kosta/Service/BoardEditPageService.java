package kr.or.kosta.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.BoardFileDto;

public class BoardEditPageService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("boardIdx"); // 글번호
		ActionForward forward = null;
		
		try{
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
			int boardIdx = Integer.parseInt(idx.trim());
			if(idx == null || idx.trim().equals("")){
				response.sendRedirect("/DetailContents.board?categoryCode="+ categoryCode + "&boardIdx=" + boardIdx); // 원래 있던 페이지로
			}
			
			BoardDao boardDao = new BoardDao();
			BoardDto boardDto = boardDao.BoardgetContentByBoardId(boardIdx);		
			
			if(boardDto == null){
				response.sendRedirect("/DetailContents.board?categoryCode="+ categoryCode + "&boardIdx=" + boardIdx); // 원래 있던 페이지로
			}
			
			// 첨부파일 가져오기
			List<BoardFileDto> filelist = boardDao.BoardFileOutput(boardIdx);
			
			forward = new ActionForward();
		    request.setAttribute("boardDto", boardDto);
		    request.setAttribute("boardIdx", boardIdx);
		    request.setAttribute("filelist", filelist);
		    
		    forward.setRedirect(false);
		    if(categoryCode==1){
		    	forward.setPath("/view/manager/board_notice_edit.jsp");
		    }else{
		    	forward.setPath("/view/board/board_community_edit.jsp");
		    }
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
		return forward;
	}
}
