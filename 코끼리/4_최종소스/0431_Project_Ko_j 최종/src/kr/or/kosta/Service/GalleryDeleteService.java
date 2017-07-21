package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.GalleryDao;

public class GalleryDeleteService implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//int gallery_id = Integer.parseInt(request.getParameter("gallery_id"));
		int gallery_id = Integer.parseInt(request.getParameter("gallery_id"));
		HttpSession session = request.getSession();
		
		GalleryDao galleryDao = new GalleryDao();
		ActionForward forward = null;
		
		try{
			request.setCharacterEncoding("utf-8");
			
			forward = new ActionForward();
			String cpage = request.getParameter("cp"); //현재 페이지
			String psize = request.getParameter("ps"); //페이지 사이즈 
			if(gallery_id == 0){
				response.sendRedirect("gallery_list.jsp"); // 갤러리가 있던 리스트 페이지로 넘어가기
			}
			String member_id=(String)(session.getAttribute("session_id"));
			int session_authority= (int)session.getAttribute("session_authority");
			int result = galleryDao.GalleryDelete(gallery_id, member_id,session_authority);
			if(result > 0){
				request.setAttribute("result", "success");
			}else{
				request.setAttribute("result", "fail");
			}			
			request.setAttribute("gallery_id", gallery_id);
			request.setAttribute("cpage", cpage);
			request.setAttribute("psize", psize);
			forward.setPath("/Gallery.board");
			forward.setRedirect(false);
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}