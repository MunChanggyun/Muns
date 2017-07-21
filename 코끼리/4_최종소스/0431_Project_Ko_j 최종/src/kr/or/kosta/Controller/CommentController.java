package kr.or.kosta.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Service.BoardAllListService;
import kr.or.kosta.Service.BoardCertainListService;
import kr.or.kosta.Service.BoardCommentDeleteService;
import kr.or.kosta.Service.BoardCommentWriteService;
import kr.or.kosta.Service.BoardDeleteService;
import kr.or.kosta.Service.BoardDetailContentsService;
import kr.or.kosta.Service.BoardEditPageService;
import kr.or.kosta.Service.BoardEditService;
import kr.or.kosta.Service.BoardGalleryListService;
import kr.or.kosta.Service.BoardReWriteService;
import kr.or.kosta.Service.BoardWriteService;


@WebServlet("*.comment")
public class CommentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public CommentController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    response.setContentType("text/html; charset=UTF-8");
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_command = RequestURI.substring(contextPath.length());
	    
		
		
		ActionForward forward = null;
		Action action = null;
		
		if(url_command.equals("/Write.comment")){ // 댓글 쓰기
			action = new BoardCommentWriteService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/Delete.comment")){ // 댓글 지우기
			action = new BoardCommentDeleteService();
			forward = action.execute(request, response);
		}
		
		if(forward != null){
			if(forward.isRedirect()){ // true
				response.sendRedirect(forward.getPath()); // 서버에게 페이지 재요청
			}else{	//false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}

}
