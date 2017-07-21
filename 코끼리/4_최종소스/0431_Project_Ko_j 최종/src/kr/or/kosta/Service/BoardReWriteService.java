package kr.or.kosta.Service;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.BoardFileDto;

public class BoardReWriteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response){
	 ActionForward forward =new ActionForward();
	 HttpSession session = request.getSession();
	 
	 try{
		// 한글처리
		request.setCharacterEncoding("UTF-8");
	    response.setContentType("text/html; charset=UTF-8");

		String uploadpath = request.getRealPath("upload");

		int size = 10 * 1024 * 1024; // 10M

		// cos.jar 파일 제공하는 MultipartRequest 사용
		MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
	    
		// 넘어온 값을 변수에 저장
		int categoryCode = Integer.parseInt(multi.getParameter("categoryCode"));
	    String memberId = (String)(session.getAttribute("session_id"));
	    String boardTitle= multi.getParameter("boardTitle");
	    String boardContent=multi.getParameter("smarteditor");
	    int boardIdx = Integer.parseInt(multi.getParameter("boardIdx")); // 원본글 글번호

	    BoardDao boardDao =new BoardDao();
	    
	    // 답글 띄어쓰기 처리
	    BoardDto board2 = boardDao.BoardgetContentByBoardId(boardIdx);
	    int cnt = board2.getDepth()+1;
	    
	    String title = "";
	    
	    for(int i=0 ; i < cnt ; i++){
	    	title += "&nbsp;&nbsp;&nbsp;&nbsp;";
	    }
	    title = title + "<img src='<%= request.getContextPath() %>/img/right-arrow.png'>[ 답글 ] ";
	    
	    // 변수에 저장한 값을 객체에 저장
	    BoardDto board= new BoardDto();
	    board.setBoard_id(boardIdx);
	    board.setBoardCategory_code(categoryCode);
		board.setMember_id(memberId);
		board.setBoard_title_h(title);
		board.setBoard_title_t(boardTitle);
		board.setBoard_content(boardContent);
		
		// Dao에 있는 함수로 객체보내기
	    int reboardIdx = boardDao.BoardReWrite(board);
	    
	    // 첨부 파일 처리
		Enumeration<String> filenames = multi.getFileNames();
		List<BoardFileDto> filelist = new ArrayList<BoardFileDto>();   
		while(filenames.hasMoreElements()){
			BoardFileDto boardfileDto = new BoardFileDto();
			String file = (String)filenames.nextElement();
			boardfileDto.setBoard_id(reboardIdx);
			boardfileDto.setBoardFile_name(multi.getFilesystemName(file));
			boardfileDto.setBoardFile_oriname(multi.getOriginalFileName(file));
			filelist.add(boardfileDto);
		}
		boardDao.BoardFileInput(filelist);
		
		
		// 다음 페이지 지정
	    forward.setRedirect(false);
	    if(reboardIdx>0){
	    	forward.setPath("/DetailContents.board?boardIdx="+reboardIdx+"&categoryCode="+categoryCode); // 방금 쓴 글 상세보기 페이지로 넘어가기
	    }else{
	    	forward.setPath("/Certain.board?categoryCode="+categoryCode);
	    }
	}catch(Exception e){
		System.out.println(e.getMessage());
	}
	return forward;
	}
}