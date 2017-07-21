package kr.or.kosta.Controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Service.ApartListSearchService;
import kr.or.kosta.Service.ApartListService;
import kr.or.kosta.Service.BoardFileDownloadService;
import kr.or.kosta.Service.CalendarDeleteService;
import kr.or.kosta.Service.CalendarInsertOkService;
import kr.or.kosta.Service.CalendarInsertService;
import kr.or.kosta.Service.CalendarListService;
import kr.or.kosta.Service.ReservationDeleteService;
import kr.or.kosta.Service.ReservationListService;
import kr.or.kosta.Service.ReservationWriteService;


@WebServlet("*.etc")
public class EtcController extends HttpServlet {
	private static final long serialVersionUID = 1L;       
   
    public EtcController() {
        super();
        
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doprocess(request, response);
	}
	
	private void doprocess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String RequestURL = request.getRequestURI();
		String ContextPath = request.getContextPath();
		String url_command = RequestURL.substring(ContextPath.length());
		
		Action action = null;
		ActionForward forward = null;
	
		if(url_command.equals("/Apart.etc")){
			action =new ApartListService();
			forward= action.execute(request, response);
		}else if(url_command.equals("/ApartSearch.etc")){
			action =new ApartListSearchService();
			forward= action.execute(request, response);			
		}else if(url_command.equals("/CalendarList.etc")){
			action = new CalendarListService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/CalendarInsert.etc")){
			action = new CalendarInsertService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/CalendarDeleteService.etc")){
			action = new CalendarDeleteService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/CalendarInsertOk.etc")){
			action = new CalendarInsertOkService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/Reservationlist.etc")){
			action = new ReservationListService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/ReservationDelete.etc")){
			action = new ReservationDeleteService();
			forward= action.execute(request, response);
		}else if(url_command.equals("/Reservationinsert.etc")){
			action = new ReservationWriteService();
			forward = action.execute(request, response);
		}else if(url_command.equals("/FileDownload.etc")){ // 파일 다운로드
			action = new BoardFileDownloadService();
			forward = action.execute(request, response);
		}
				
		
		if(forward !=null){
			if(forward.isRedirect()){
				response.sendRedirect(forward.getPath());
			}else{
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}		
		}
	}

}
