package kr.or.kosta.Service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;

public class BoardGalleryListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		List<BoardDto> boardlist = null;
		int pagecount = 0;
		try{
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode"));
			forward = new ActionForward();			
			
			String psStr = request.getParameter("ps");    // page size
	        String cpStr = request.getParameter("cp");    // current page
	        
	        if(psStr == null || psStr.trim().equals("")){
	            psStr = "5"; 	   // default 5건씩 
	        }
	        
	        if(cpStr == null || cpStr.trim().equals("")){
	            cpStr= "1";        // default 1 page
	        }
	      
	        int pagesize = Integer.parseInt(psStr);  //5
	        int cpage = Integer.parseInt(cpStr);     //1                      
	        
	        BoardDao boardDao = new BoardDao();
			boardlist = boardDao.CertainBoardList(categoryCode, cpage, pagesize);
			int totalboardCount = boardDao.CertainboardCount(categoryCode);
	        
	        if(totalboardCount % pagesize==0){   // 전체 건수 , page size
	            pagecount = totalboardCount/pagesize;
	        }else{
	            pagecount = (totalboardCount/pagesize) + 1;
	        }
			
			request.setAttribute("ps", pagesize);
			request.setAttribute("cp", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("boardlist", boardlist);
			request.setAttribute("totalcount", totalboardCount);
			
			forward.setRedirect(false);
			forward.setPath("/board/GalleryList.jsp"); // 갤러리 리스트 페이지로 
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
