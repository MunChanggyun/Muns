package kr.or.kosta.Service;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import kr.or.kosta.Dao.ReservationDao;
import kr.or.kosta.Dto.ReservationDto;


@WebServlet("/ReservationTimeList")
public class ReservationTimeList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ReservationTimeList() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}
	private void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String reservation_time = request.getParameter("reservationdate");
			String facility_id = request.getParameter("facilityname");
			ReservationDao reservationdao = new ReservationDao();
			ArrayList<ReservationDto> reservationlist  = null;
			JSONArray  jsonarray = new JSONArray();
			ReservationDto reservationdto = new ReservationDto();
			HttpSession session = request.getSession();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
			 
			try {
				java.util.Date date = sdf.parse(reservation_time);
				 java.sql.Date sqlDate = new Date(date.getTime());
				 String memberId = (String)(session.getAttribute("session_id"));
				 reservationlist = reservationdao.ReservationListByFnameDate(facility_id, sqlDate,memberId);
				 
				 for(int i =0 ; i<reservationlist.size();i++){
					 reservationdto = reservationlist.get(i);
					 JSONObject jsonobject = new JSONObject();
					 jsonobject.put("time_start", reservationdto.getTime_start());
					 jsonarray.add(jsonobject);
				 }
				 response.getWriter().print(jsonarray);
			} catch (Exception e) {
				
			}
		   
	}

}

    
    
    
    
