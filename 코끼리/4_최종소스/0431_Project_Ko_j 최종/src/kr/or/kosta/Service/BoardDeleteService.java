package kr.or.kosta.Service;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;

public class BoardDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDao boardDao = new BoardDao();
		ActionForward forward = null;
		
		
		try{
			PrintWriter out = response.getWriter();
			request.setCharacterEncoding("utf-8");
			
			forward = new ActionForward();
			String idx = request.getParameter("boardIdx");
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
			String cpage =	request.getParameter("cp"); // 현재 페이지 번호
		    String psize =	request.getParameter("ps"); // page size
		    
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
			
			// 게시물에 답글이 존재하는지 확인
			int cnt = boardDao.BoardRewriteCheck(boardIdx);
			
			if(cnt == 0){
				// 댓글 삭제
				boardDao.CommentAllDelete(boardIdx);
				
				// 첨부파일 삭제
				boardDao.BoardFileDelete(boardIdx);
				
				// 게시물 삭제
				int rowcnt = boardDao.BoardDelete(boardIdx);
				
				if(rowcnt > 0){
					request.setAttribute("result", "success");
				}else{
					request.setAttribute("result", "fail");
				}
			}else{
			}
			request.setAttribute("boardIdx", boardIdx);
			request.setAttribute("cpage", cpage);
			request.setAttribute("psize", psize);
			
			forward.setRedirect(false);
			forward.setPath("/Certain.board?categoryCode="+categoryCode+"&cpage="+cpage+"&psize="+psize); // 게시물이 있던 리스트 페이지?cpage=?,pasize=?로 넘어가기
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
