package kr.or.kosta.Service;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.ReservationDao;
import kr.or.kosta.Dto.ReservationDto;


public class ReservationListService implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
							ActionForward forward = new ActionForward();
							ReservationDao reservationdao = new ReservationDao();
							ArrayList<ReservationDto> reservationlist = new ArrayList<ReservationDto>();
						
							String member_id=request.getParameter("member_id");
							reservationlist = reservationdao.ReservationList(member_id);
						
							if(reservationlist!=null){
								request.setAttribute("reservationlist", reservationlist);
								forward.setPath("/view/reservation/ReservationList.jsp");
								forward.setRedirect(false);
							}else{
								forward.setPath("/view/reservation/Reservation.jsp");
								forward.setRedirect(false);
							}
						
				return forward;
		 }
}

