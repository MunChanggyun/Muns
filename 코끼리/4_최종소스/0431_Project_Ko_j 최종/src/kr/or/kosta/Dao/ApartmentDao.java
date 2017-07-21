package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.kosta.Dto.ApartmentDto;
import kr.or.kosta.Dto.SeoulDto;


public class ApartmentDao {
	DataSource datasource =  null;
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ApartmentDao(){
		Context context;
		try {
			context = new InitialContext();
			datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
			conn = datasource.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
	}
	
	//서울시 구 리스트를 배열 객체에 담아서 리턴
	public List<SeoulDto> SeoulList(){
		
		List<SeoulDto> SeoulList = null;
		
		try{
			String sql = "select Seoul_gu from Seoul";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			SeoulList = new ArrayList<SeoulDto>();
			while(rs.next()){
				String Seoul_gu = rs.getString("seoul_gu");
			
				SeoulDto seouldata = new SeoulDto(Seoul_gu);
				SeoulList.add(seouldata);
			}
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return SeoulList;
	}

	//변수 값에 구를 넣어주면 아파트 리스트 리턴
	public List<ApartmentDto> ApartmentList(String apartment_gu){
		
		List<ApartmentDto> ApartmentList = null;
		
		try{
			String sql = "select * from Apartment where Seoul_gu=?";
			pstmt = conn.prepareStatement(sql);
		  	pstmt.setString(1, apartment_gu);
			
		  	rs =pstmt.executeQuery();
		  	
		  	ApartmentList = new ArrayList<ApartmentDto>();
		  	while(rs.next()){
		  		ApartmentDto a= new ApartmentDto();
		  		a.setApartment_name(rs.getString("apartment_name"));
		  		a.setApartment_url(rs.getString("apartment_url"));
		  		a.setApartment_zipcode(rs.getString("apartment_zipcode"));
		  		a.setSeoul_gu(rs.getString("seoul_gu"));			
				ApartmentList.add(a);
		  	}
		  	rs.close();
		  	pstmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ApartmentList;
	}
	
	//변수 값에 입력해오는 String을 넣어주면 아파트 리스트 리턴
	public List<ApartmentDto> ApartmentSearchList(String searchstring, String apartname){
		
		List<ApartmentDto> ApartmentList = null;
		
		try{
			String sql = "select * from Apartment where Seoul_gu=? and Apartment_name like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, apartname);
		  	pstmt.setString(2, "%"+searchstring+"%");
			
		  	rs =pstmt.executeQuery();
		  	
		  	ApartmentList = new ArrayList<ApartmentDto>();
		  	while(rs.next()){
		  		ApartmentDto a= new ApartmentDto();
		  		a.setApartment_name(rs.getString("apartment_name"));
		  		a.setApartment_url(rs.getString("apartment_url"));
		  		a.setApartment_zipcode(rs.getString("apartment_zipcode"));
		  		a.setSeoul_gu(rs.getString("seoul_gu"));			
				ApartmentList.add(a);
		  	}
		  	rs.close();
		  	pstmt.close();
			conn.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return ApartmentList;
	}
}
