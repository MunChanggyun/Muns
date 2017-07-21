package kr.or.kosta.Service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.CalendarDao;
import kr.or.kosta.Dto.CalendarDto;

public class CalendarListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		response.setContentType("text/html; charset=utf-8");
		
		
		CalendarDto calerdardto = new CalendarDto();
		CalendarDao calendardao = new CalendarDao();
		ArrayList<CalendarDto> calendarlist =null;
		ActionForward forward = null;
		
		JSONArray jsonarray = new JSONArray();
		try{
        	calendarlist = calendardao.CalendarList();
			calerdardto = new CalendarDto();
			forward = new ActionForward();			
			
			for(int i =0; i<calendarlist.size();i++){
				calerdardto = calendarlist.get(i);
				JSONObject jsonobject = new JSONObject();
				jsonobject.put("idx", calerdardto.getCalendar_id());
				jsonobject.put("title", calerdardto.getCalendar_content());
				jsonobject.put("start", calerdardto.getCalendar_start().toString());
				jsonobject.put("end", calerdardto.getCalendar_end().toString());
				jsonarray.add(jsonobject);
			}
			response.getWriter().print(jsonarray);
			return null;

		}catch(Exception e){
			
		}		

		return forward;
	}

}
