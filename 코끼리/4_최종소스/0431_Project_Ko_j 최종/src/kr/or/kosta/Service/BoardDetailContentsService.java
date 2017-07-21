package kr.or.kosta.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.BoardFileDto;
import kr.or.kosta.Dto.CommentsDto;

public class BoardDetailContentsService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("boardIdx"); //글번호
		ActionForward forward = null;
		try{		
			// list 다시 넘어갈때 현재 페이지 * 페이지 사이즈////////////
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode")); // 게시판 종류
			String cpage =	request.getParameter("cpage"); // 현재 페이지 번호
		    String psize = request.getParameter("psize"); // page size 정보
		    
		    // 글번호를 가지고 오지 않았을 경우 예외처리
		 	if(idx == null || idx.trim().equals("")){
		 		// 원래 있던 페이지로 넘어가기
		 		response.sendRedirect("/Certain.board?categoryCode="+categoryCode);
		 	}
		 	
		 	int boardIdx = Integer.parseInt(idx.trim());
		
		    if(cpage==null || cpage.trim().equals("")){
				cpage="1";
			}
			if(psize==null || psize.trim().equals("")){
				psize="5";
			}
			/////////////////////////////////////////////////////
			BoardDao boardDao = new BoardDao();
			// 조회수 증가
			int result = boardDao.UpBoardHit(boardIdx);
			
			// 게시물 정보 가져오기
			BoardDto boardDto = boardDao.BoardgetContentByBoardId(boardIdx);
			
			// 첨부파일 가져오기
			List<BoardFileDto> filelist = boardDao.BoardFileOutput(boardIdx);
			
			// 댓글 가져오기
			List<CommentsDto> commentlist = boardDao.CommentList(boardIdx);
			
			forward = new ActionForward();
			
			// 넘겨줄 값들
			
		    request.setAttribute("boardDto", boardDto);
		    request.setAttribute("filelist", filelist);
		    request.setAttribute("commentlist", commentlist);
		    request.setAttribute("boardIdx", boardIdx);
		    request.setAttribute("cpage", cpage);
		    request.setAttribute("psize", psize);
		    
		    forward.setRedirect(false);
		    
		    if(categoryCode==1){
		    	forward.setPath("/view/board/board_notice_content.jsp?cpage="+cpage+"&psize="+psize); // 공지사항 게시물 상세 페이지로 넘어가기
		    }else if(categoryCode==2){
		    	forward.setPath("/view/board/board_community_content.jsp?cpage="+cpage+"&psize="+psize); // 소통 게시판 게시물 상세 페이지로 넘어가기
		    }
		    
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
		return forward;
	}
}
