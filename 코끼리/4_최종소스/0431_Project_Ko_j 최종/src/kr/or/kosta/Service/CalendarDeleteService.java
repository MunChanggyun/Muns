package kr.or.kosta.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.CalendarDao;

public class CalendarDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String calendar_id = request.getParameter("idx");
		
		ActionForward forward = new ActionForward();
		CalendarDao calendardao = new CalendarDao();
		int row = calendardao.CalendarDelete(Integer.parseInt(calendar_id));
		if(row > 0){
			forward.setPath("/view/calendar/calendar_list.jsp");
			forward.setRedirect(false);
			
		}else{
			forward.setPath("/view/calendar/calendar_list.jsp");
			forward.setRedirect(false);
			
		}
		
		
		
		
		
		
		return forward;
	}

}
