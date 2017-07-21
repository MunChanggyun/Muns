package kr.or.kosta.Service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;

public class CalendarInsertOkService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		
		ActionForward forward = new ActionForward();
		String title = request.getParameter("title");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		

		request.setAttribute("title", title);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		
		forward.setPath("/CalendarInsert.etc");
		forward.setRedirect(false);
		
		return forward;
	}

}
