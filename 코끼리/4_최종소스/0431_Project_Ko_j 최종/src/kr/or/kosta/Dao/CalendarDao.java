package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.kosta.Dto.CalendarDto;

public class CalendarDao {
	DataSource datasource =  null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public CalendarDao(){
		Context context;
		try {
			context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = datasource.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	//일정 추가
	public int CalendarWrite(CalendarDto calendar){
		
		int row = 0;
		
		try{
			String sql = "insert into Calendar(calendar_id,calendar_content,calendar_start,calendar_end) values(Calendar_Calendar_id.NEXTVAL,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, calendar.getCalendar_content());
			pstmt.setDate(2, calendar.getCalendar_start());
			pstmt.setDate(3, calendar.getCalendar_end());
			
			row = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return row;
	}
	//일정 리스트
	public ArrayList<CalendarDto> CalendarList(){
		
		ArrayList<CalendarDto> CalendarList = null;
		CalendarDto calendardto = null;
		try{
			String sql = "select calendar_id, calendar_content, calendar_start, calendar_end+1 from Calendar";
			pstmt = conn.prepareStatement(sql);
			
		  	rs =pstmt.executeQuery();
		  	
		  	CalendarList = new ArrayList<CalendarDto>();
		  	while(rs.next()){
		  		calendardto = new CalendarDto();
		  		
		  		calendardto.setCalendar_id(rs.getInt("calendar_id"));
		  		calendardto.setCalendar_content(rs.getString("calendar_content"));
		  		calendardto.setCalendar_start(rs.getDate("calendar_start"));
				calendardto.setCalendar_end(rs.getDate("calendar_end+1"));
								
				CalendarList.add(calendardto);
		  	}
		  	
		  	rs.close();
		  	pstmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return CalendarList;
	}
	//일정 삭제
	public int CalendarDelete(int calendar_id){
		
		int row = 0;
		
		try{
			String sql = "delete from Calendar where calendar_id=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, calendar_id);
			
			row = pstmt.executeUpdate();
			
			pstmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return row;
	}

}
