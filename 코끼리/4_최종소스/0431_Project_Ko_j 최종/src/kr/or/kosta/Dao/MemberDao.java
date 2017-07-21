package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.kosta.Dto.MemberDto;

public class MemberDao {
	DataSource datasource = null;
	Connection conn = null;

	// DB와 처음 연결시키는 Constructor
	public MemberDao() {
		Context context;
		try {
			context = new InitialContext();
			datasource = (DataSource) context
					.lookup("java:comp/env/jdbc/oracle");
			conn = datasource.getConnection();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// MemberDao 함수 1
	/*가입된 회원의 모든 정보를 ArrayList에 담아 ArrayList로 반환하는 함수*/
	public ArrayList<MemberDto> MemberAllList() {
	      ArrayList<MemberDto> memberlist = null;
	      try {
	         

	         PreparedStatement pstmt = null;
	         String sql = "select ROWNUM rn, member_authority, member_id, member_pw, member_zipcode, member_dong, member_ho from MEMBER";
	         pstmt = conn.prepareStatement(sql);
	         ResultSet rs = pstmt.executeQuery();
	         memberlist = new ArrayList<MemberDto>();	         

	         while (rs.next()) {
	         
	            MemberDto m = new MemberDto();
	            m.setRn(rs.getInt("rn"));
	            m.setMember_id(rs.getString("MEMBER_ID"));
	            m.setMember_pw(rs.getString("MEMBER_PW"));
	            m.setMember_authority(rs.getInt("MEMBER_AUTHORITY"));
	            m.setMember_zipcode(rs.getString("MEMBER_ZIPCODE"));
	            m.setMember_dong(rs.getString("MEMBER_DONG"));
	            m.setMember_ho(rs.getString("MEMBER_HO"));
	            memberlist.add(m);
	         }
	         rs.close();
	         pstmt.close();
	         conn.close();
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return memberlist;
	   }
	// MemberDao 함수 2
	/* 사용자가 입력한 비밀번호와 id를 확인하여 pw를 바꾸는 함수*/
	public int MemberPasswordEdit(String member_id, String member_pw) {
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			
			String sql = "update MEMBER set MEMBER_PW=? where MEMBER_ID=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, member_pw);
			pstmt.setString(2, member_id);

			row = pstmt.executeUpdate();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return row;
	}

	// MemberDao 함수 3
    /*입력한 id에 맞게 삭제시킨후, Commit 시키는 함수*/
	   public int MemberDelete(String id) {
		      int board = 0;
		      int gallery = 0;
		      int reservation = 0;
		      int row = 0;
		      try {		         
		         PreparedStatement psmt = null;
		         
		         String spl4="delete from reservation where member_id=?";
		         psmt = conn.prepareStatement(spl4);
		         psmt.setString(1, id);
		         reservation = psmt.executeUpdate();
		         
		            
		         
		         
		         String spl3="delete from gallery where member_id=?";
		         psmt = conn.prepareStatement(spl3);
		         psmt.setString(1, id);
		         gallery = psmt.executeUpdate();
		      
		            
		         
		         
		         String spl2 ="delete from board where member_id=?";
		         psmt = conn.prepareStatement(spl2);
		         psmt.setString(1, id);
		         board = psmt.executeUpdate();
		         
		            String sql = "delete from member where member_id=?";
		            psmt = conn.prepareStatement(sql);
		            psmt.setString(1, id);
		            row = psmt.executeUpdate();

		            if (row > 0) {
		               conn.commit();
		            } else {
		               conn.rollback();
		            }
		         
		         conn.close();
		      } catch (Exception e) {
		         System.out.println(e.getMessage());
		      }
		      return row;
		   }

	// MemberDao 함수 4
	/*회원 가입 시키는 함수, 모든 입력되는 값을 DB에 insert*/
	public int MemberJoin(String member_id, String member_pw,
	          String member_zipcode, String member_dong,
	         String member_ho) {
	      int row = 0;
	      try {
	         PreparedStatement psmt = null;
	         String sql = "insert into MEMBER values(?,?,1,?,?,?)";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, member_id);
	         psmt.setString(2, member_pw);
	         psmt.setString(3, member_zipcode);
	         psmt.setString(4, member_dong);
	         psmt.setString(5, member_ho);
	         row = psmt.executeUpdate();

	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      } finally {
	      }
	      return row;
	   }
	// MemberDao 함수 5
	/* 로그인시 id에 맞는 id와pw를 DB에서 가져와 일치여부 검사 */
	public boolean MemberLogin(String member_id, String member_pw) {
	      PreparedStatement psmt = null;
	      ResultSet rs = null;
	      boolean check = false;
	      try {
	         String sql = "select MEMBER_ID,MEMBER_PW from MEMBER where MEMBER_ID=?";
	         psmt = conn.prepareStatement(sql);
	         psmt.setString(1, member_id);
	         int row = psmt.executeUpdate();
	         rs = psmt.executeQuery();
	         
	         rs.next();
	         if (row > 0) {
	            if (rs.getString(2).equals(member_pw)) {
	               check = true;
	            } else {
	               check = false;
	            }
	         }
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return check;
	   }

	// MemberDao 함수 6 (완료 건들지말기.)
	/* 우편번호에 맞게 Return member_id */
	public String MemberIdSearchByZipcode(String zipcode, String dong, String ho) {
        PreparedStatement psmt = null;
        ResultSet rs = null;
        String member_id = null;
        String member_dong = null;
        String member_ho = null;
        try {             
           String sql0= "select MEMBER_ZIPCODE from MEMBER where MEMBER_ZIPCODE=? ";           
           String sql = "select MEMBER_ID, Member_dong, Member_ho from MEMBER where MEMBER_ZIPCODE=?";
           
           psmt=conn.prepareStatement(sql0);
           psmt.setString(1, zipcode);
           rs=psmt.executeQuery();
           
           if(rs.next()){           
           
           psmt = conn.prepareStatement(sql);
           psmt.setString(1, zipcode);
           rs = psmt.executeQuery(); 

	           while(rs.next()){        	   
	              member_id = rs.getString(1);
	              member_dong = rs.getString(2);
	              member_ho = rs.getString(3);    
	              
	              if(member_dong.equals(dong)&&member_ho.equals(ho)){
	                 member_id = rs.getString(1);
	                 break;
	              }else{
	            	 member_id="no";
	            	 break;
	              }
	              
	           }
         }else{
        	 member_id="no";
         }
           psmt.close();
           rs.close();
           conn.close();
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }
        return member_id;
     }

	// MemberDao 함수 7
	/* 동,호,id를 가져와 DB에서 id가 일치하면 그에 따른 동,호수, 비교하는 함수 */
	/* 비밀번호를 찾을때 필요함 */
	public boolean MemberPwdSearchById(String member_id, String member_dong,
			String member_ho) {
		PreparedStatement psmt = null;
		ResultSet rs = null;
		boolean check = false;
		try {
			String sql = "select MEMBER_DONG,MEMBER_HO from MEMBER where MEMBER_ID=?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member_id);
			rs = psmt.executeQuery();
			
			if (rs.next()) {				
				if (rs.getString(1).equals(member_dong)) {
					if (rs.getString(2).equals(member_ho)) {
						check = true;
					}
				} else {
					check = false;
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return check;
	}

	    // MemberDao 함수 8
		//id 중복체크함수  있으면 false 없으면 ture 반환
		public boolean MemberCheckId(String member_id) {
			PreparedStatement psmt = null;
			boolean check = false;
			try {
				String sql = "select MEMBER_ID from MEMBER where MEMBER_ID=?";
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, member_id);
				int row = psmt.executeUpdate();
				if (row > 0) {
					check = false;
				} else {
					check = true;
				}
			} catch (Exception e) {

			}
			return check;
		}

	// MemberDao 함수 9
	// id를 넣으면 id에 맞는 member_authority 반환(session 필요)// 마지막 수정 진학
	public int getMemberAuthority(String memberid) {

		int  authority = 0;
	      try {
	         PreparedStatement pstmt = null;
	         String sql = "select member_authority from MEMBER where MEMBER_ID=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memberid);
	         ResultSet rs = pstmt.executeQuery();

	         if(rs.next()){
	         authority=rs.getInt("member_authority");      
	         }
	         rs.close();
	         pstmt.close();
	      
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return authority;
	}

	// MemberDao 함수 10
	//id를 넣으면 id에 맞는 zipcode 반환(session 필요) // 마지막 수정 진학
	public String getMemberZipcode(String memberid) {

		String  zipcode = null;
	      try {
	         PreparedStatement pstmt = null;
	         String sql = "select member_zipcode from MEMBER where MEMBER_ID=?";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, memberid);
	         ResultSet rs = pstmt.executeQuery();

	         if(rs.next()){	         
	         zipcode=rs.getString("member_zipcode");  
	         }
	         rs.close();
	         pstmt.close();	        
	      } catch (Exception e) {
	         System.out.println(e.getMessage());
	      }
	      return zipcode;
	}

	
	// MemberDao 함수 11
	   //zipcode 넣으면 zipcode에 맞는 URL주소 반환.(session 필요) 마지막 수정 진학
	   public String getMemberUrl(String zipcode) {
	      String apartmenturl = null;
	         try {
	            
	            PreparedStatement pstmt = null;
	            String sql = "select apartment_url from APARTMENT where apartment_zipcode=?";
	            pstmt = conn.prepareStatement(sql);
	            pstmt.setString(1, zipcode);
	            ResultSet rs = pstmt.executeQuery();        
	            if(rs.next()){
	               apartmenturl=rs.getString("apartment_url");
	            }            
	            rs.close();
	            pstmt.close();
	            conn.close();
	         } catch (Exception e) {
	            System.out.println(e.getMessage());
	         }
	         return apartmenturl;

	   }
	
	// MemberDao 함수 12 마지막 수정 선화
			/* id를 가져와 DB에서 id가 일치하면 그에 따른 비밀번호를 불러오는 함수 */
			/* 비밀번호를 이메일로 보내줄 때 필요함 */
			public String MemberPwdSendEmail(String member_id) {
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String member_pw=null;
				
				try {
					String sql = "select MEMBER_PW from MEMBER where MEMBER_ID=?";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1, member_id);
					rs = pstmt.executeQuery();					
					if(rs.next()){
						member_pw = rs.getString("MEMBER_PW");
					}				
					rs.close();
			        pstmt.close();
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
				return member_pw;
			}
	
	
}
