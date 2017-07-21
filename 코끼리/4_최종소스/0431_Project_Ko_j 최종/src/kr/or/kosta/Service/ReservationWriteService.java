package kr.or.kosta.Service;

import java.sql.Date;
import java.text.SimpleDateFormat;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;
import kr.or.kosta.Dao.ReservationDao;
import kr.or.kosta.Dto.FacilityDto;
import kr.or.kosta.Dto.ReservationDto;
import kr.or.kosta.Dto.TimeDto;

public class ReservationWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		ActionForward forward = null;

		try {
		    response.setContentType("text/html; charset=UTF-8");
			String facility_id = request.getParameter("facility_id");
			String reservation_time = request.getParameter("reservation_time");//date
			String time_start = request.getParameter("time_start");
			
			HttpServletRequest h_request= (HttpServletRequest) request;		
			HttpSession h_session= h_request.getSession();	
			
			String member_id = (String) h_session.getAttribute("session_id");
			int check = 0;
			
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
				
			java.util.Date date = sdf.parse(reservation_time);
		    java.sql.Date sqlDate = new Date(date.getTime());

			
			//reservation 객체 request값 넣기
			ReservationDto reservationDto = new ReservationDto();
			ReservationDao reservationDao = new ReservationDao();
			
			reservationDto.setFacility_id(facility_id);
			reservationDto.setReservation_time(sqlDate);
			reservationDto.setTime_start(time_start);
			reservationDto.setMember_id(member_id);
			
			check = reservationDao.ReservationWrite(reservationDto); //check <-resultcount	
			forward = new ActionForward();
			if(check>0){
				request.setAttribute("member_id", member_id);
				forward.setPath("/Reservationlist.etc");
				forward.setRedirect(false);
			}else{
				forward.setPath("/view/reservation/Reservation.jsp");
				forward.setRedirect(false);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
	
}