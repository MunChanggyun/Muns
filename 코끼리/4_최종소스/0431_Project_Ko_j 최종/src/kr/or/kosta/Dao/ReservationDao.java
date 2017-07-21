package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.kosta.Dto.ReservationDto;


public class ReservationDao {
	DataSource datasource =  null;
	Connection conn = null;
	
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	
	
	public ReservationDao(){
		Context context;
		try {
			context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	//예약 추가
	public int ReservationWrite(ReservationDto reservationdto){
			
		int result = 0;
		
		try{
			conn = datasource.getConnection();
			String  sql = "insert into Reservation(Reservation_id, Reservation_time, Time_start, Facility_id, Member_id ) "
						+ "values(reservation_idx.nextval,?,?,?,?)";	
			pstmt = conn.prepareStatement(sql);
			
	
			pstmt.setDate(1, reservationdto.getReservation_time());
			pstmt.setString(2, reservationdto.getTime_start());
			pstmt.setString(3, reservationdto.getFacility_id());
			pstmt.setString(4, reservationdto.getMember_id());
			
			result = pstmt.executeUpdate();
				
			
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	
	//id는 자신의 아이디 세션  r_id는 레저베이션 프라이머리키
	//예약 삭제
	public int ReservationDelete(String member_id, int reservation_id){
		
		int result = 0;		
		try{
			conn = datasource.getConnection();
			String sql = "delete  from reservation where member_id = ? and reservation_id = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_id);
			pstmt.setInt(2, reservation_id);
			result=pstmt.executeUpdate();
			
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return result;
	}
	//예약 리스트
	public ArrayList<ReservationDto> ReservationList(String memberid){
		
		
		ArrayList<ReservationDto> reservationlist =null;
		ReservationDto dto = null;
		try{
			conn = datasource.getConnection();
			String sql = "select * from Reservation where member_id=?";
			pstmt=conn.prepareStatement(sql);
			pstmt.setString(1, memberid);
			rs = pstmt.executeQuery();
			reservationlist =  new ArrayList<ReservationDto>();
			while(rs.next()){
				dto = new ReservationDto();
				dto.setMember_id(rs.getString("member_id"));
				dto.setFacility_id(rs.getString("facility_id"));
				dto.setReservation_id(rs.getInt("reservation_id"));
				dto.setReservation_time(rs.getDate("reservation_time"));
				dto.setTime_start(rs.getString("time_start"));
				reservationlist.add(dto);

			}
			rs.close();
			pstmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		
		return reservationlist;
	}
	//예약 중복검사
	public ArrayList<ReservationDto> ReservationListByFnameDate(String facility_id, Date reservation_time, String memberid){
		ReservationDto reservationdto = null;
		ArrayList<ReservationDto> reservationlist = null;
		try{
			conn= datasource.getConnection();
			String sql ="select time_start from Reservation where facility_id=? and reservation_time=? and member_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, facility_id);
			pstmt.setDate(2, reservation_time);
			pstmt.setString(3, memberid);
			rs=pstmt.executeQuery();
			reservationlist = new ArrayList<ReservationDto>();
			while(rs.next()){
				reservationdto = new ReservationDto();
				reservationdto.setTime_start(rs.getString("time_start"));
				
				reservationlist.add(reservationdto);
			}
			
		}catch(Exception e ){
			
		}finally{
			try {
				rs.close();
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
			
			}
			
		}
		
		
		
		return reservationlist;
		
	}
	

}
