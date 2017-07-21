package kr.or.kosta.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;

public class BoardAllListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		BoardDao boardDao = null;
		List<BoardDto> boardList = null;
		int psize = 0;
		int cpage = 0;
		int pagecount = 0;
		try{
			boardDao = new BoardDao();
			forward = new ActionForward();
	        int totalboardCount = boardDao.AllboardCount();
	      
	        String psStr = request.getParameter("psize");    // page size
	        String cpStr = request.getParameter("cpage");    // current page
	        
	        if(psStr == null || psStr.trim().equals("")){
	            //default 값
	            psStr = "5"; // default 5건씩 
	        }
	        
	        if(cpStr == null || cpStr.trim().equals("")){
	            cpStr= "1";        // default 1 page
	        }
	      
	        psize = Integer.parseInt(psStr);  //5
	        cpage = Integer.parseInt(cpStr);     //1                      
	        
	        if(totalboardCount % psize==0){   //전체 건수 , page size
	            pagecount = totalboardCount/psize;
	        }else{
	            pagecount = (totalboardCount/psize) + 1;
	        }
	        // 페이지 갯수 : 102 건 , page size :5   page count: 21
	        
	        boardList = boardDao.AllBoardList(cpage, psize);
			
			request.setAttribute("psize", psize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("totalboardCount", totalboardCount);
			request.setAttribute("boardList", boardList);		
			
			forward.setRedirect(false);
			forward.setPath("/view/board/board_all_list.jsp?psize="+psize+"&cpage"+cpage); // 전체 게시판 리스트
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
