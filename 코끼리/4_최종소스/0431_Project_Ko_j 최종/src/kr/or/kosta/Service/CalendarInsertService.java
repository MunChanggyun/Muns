package kr.or.kosta.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.CalendarDao;
import kr.or.kosta.Dto.CalendarDto;

public class CalendarInsertService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		CalendarDao calendardao = new CalendarDao();
		CalendarDto calendardto = new CalendarDto();
		int result = 0;
	
		String calendar_content = request.getParameter("title");
		String calendar_start = request.getParameter("start");
		String calendar_end = request.getParameter("end");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		try{
			java.util.Date date = sdf.parse(calendar_start);
	        java.util.Date date2 = sdf.parse(calendar_end);
	        java.sql.Date sqlDate = new Date(date.getTime());
	        java.sql.Date sqlDate2 = new Date(date2.getTime());
			
			
	        calendardto.setCalendar_content(calendar_content);
	        calendardto.setCalendar_start(sqlDate);
			calendardto.setCalendar_end(sqlDate2);
			
			result = calendardao.CalendarWrite(calendardto);
			if(result>0){
				forward.setPath("/view/calendar/calendar_list.jsp");
				forward.setRedirect(false);
			}else{
				forward.setPath("/view/calendar/calendar_list.jsp");
				forward.setRedirect(false);
			}
			
			
			
		}catch(Exception e){
			
		}
        
		
		
		
		
		
		return forward;
	}

}
