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
import kr.or.kosta.Service.BoardLikeUpService;
import kr.or.kosta.Service.BoardReWriteService;
import kr.or.kosta.Service.BoardWriteService;
import kr.or.kosta.Service.GalleryDeleteService;
import kr.or.kosta.Service.GalleryLikeService;
import kr.or.kosta.Service.GalleryListService;
import kr.or.kosta.Service.GalleryWriteService;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8"); 
	    response.setContentType("text/html; charset=UTF-8");
		
		String RequestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String url_command = RequestURI.substring(contextPath.length());
	    
		ActionForward forward = null;
		Action action = null;
		
		if(url_command.equals("/AllList.board")){  // 전체 게시판 리스트
			action = new BoardAllListService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/DetailContents.board")){ // 게시글 상세보기
			action = new BoardDetailContentsService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/Write.board")){ // 게시글 쓰기
			action = new BoardWriteService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/Delete.board")){ // 게시글 삭제
			action = new BoardDeleteService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/toEditPage.board")){ // 게시글 수정 페이지로
			action = new BoardEditPageService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/Edit.board")){ // 게시글 수정
			action = new BoardEditService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/ReWrite.board")){ // 답글 쓰기
			action = new BoardReWriteService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/Certain.board")){ // 공지사항,소통게시판 리스트 
			action = new BoardCertainListService();
			forward = action.execute(request, response); 
		}else if(url_command.equals("/LikeUp.board")){ // 좋아요수 증가
			action = new BoardLikeUpService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/Gallery.board")){// 최종 수정 창균 17.04.30
			action = new GalleryListService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/GalleryWrite.board")){// 최종 수정 창균 17.04.30
			action = new GalleryWriteService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/GalleryDelete.board")){// 최종 수정 창균 17.04.30	
			action = new GalleryDeleteService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/GalleryLike.board")){//최종 수정 창균 17.04.30
	         action = new GalleryLikeService();
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
