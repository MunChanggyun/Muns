package kr.or.kosta.Service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardCategoryDto;
import kr.or.kosta.Dto.BoardDto;

public class BoardCertainListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		List<BoardDto> boardlist = null;
		int pagecount = 0;
		try{
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
			forward = new ActionForward();			
			
			String psStr = request.getParameter("psize");    // page size
	        String cpStr = request.getParameter("cpage");    // current page
	        
	        if(psStr == null || psStr.trim().equals("")){
	            psStr = "5"; 	   // default 5건씩 
	        }
	        
	        if(cpStr == null || cpStr.trim().equals("")){
	            cpStr= "1";        // default 1 page
	        }
	      
	        int psize = Integer.parseInt(psStr);
	        int cpage = Integer.parseInt(cpStr);

	        BoardDao boardDao = new BoardDao();
			
			int totalboardCount = boardDao.CertainboardCount(categoryCode);
	        
	        if(totalboardCount % psize==0){   // 전체 건수 , page size
	            pagecount = totalboardCount/psize;
	        }else{
	            pagecount = (totalboardCount/psize) + 1;
	        }
	        
	        // 게시판 정보 가져오기
	        BoardCategoryDto categoryInfo = boardDao.BoardCategoryInfoByCode(categoryCode);
	        boardlist = boardDao.CertainBoardList(categoryCode, cpage, psize);
	        
			request.setAttribute("psize", psize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("totalboardCount", totalboardCount);
			request.setAttribute("categoryInfo", categoryInfo);
			
			
			forward.setRedirect(false);
			if(categoryCode==1){
				forward.setPath("/view/board/board_notice_list.jsp?psize="+psize+"&cpage"+cpage); // 공지사항 리스트 페이지로 
			}else{
				forward.setPath("/view/board/board_community_list.jsp?psize="+psize+"&cpage"+cpage); // 소통게시판 리스트 페이지로 
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
