package kr.or.kosta.Service;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.ApartmentDao;
import kr.or.kosta.Dto.ApartmentDto;

public class ApartListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward=null;
		List<ApartmentDto> apartlist=null;
		try {
			ApartmentDao dao= new ApartmentDao();
			String apartname=request.getParameter("ApartName");
			
			apartlist=dao.ApartmentList(apartname);
			
			forward=new ActionForward();			
			request.setAttribute("apartlist",apartlist);

			forward.setPath("/view/view_JSP/ApartList.jsp");
			forward.setRedirect(false);
			

		} catch (Exception e) {

		}
		
		
		return forward;
	}

}
