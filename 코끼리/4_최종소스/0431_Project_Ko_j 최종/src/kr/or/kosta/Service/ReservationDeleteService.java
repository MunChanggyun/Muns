package kr.or.kosta.Service;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.ReservationDao;



public class ReservationDeleteService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		String reservation_id = request.getParameter("reservation_id");
		String member_id = request.getParameter("member_id");
		ReservationDao reservationdao = new ReservationDao();
		int row = reservationdao.ReservationDelete(member_id, Integer.parseInt(reservation_id));
		if(row>0){
			forward.setPath("Reservationlist.etc");
			forward.setRedirect(false);
		}else{
			forward.setPath("Reservationlist.etcs");
			forward.setRedirect(false);		
		}
				
		
		
		
		
		
		return forward;
	}
}