package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;




import kr.or.kosta.Dto.BoardFileDto;
import kr.or.kosta.Dto.GalleryDto;
import kr.or.kosta.Dto.GalleryFileDto;


public class GalleryDao {

   DataSource datasource =  null;
   Connection conn = null;
   Statement stmt = null;
   PreparedStatement pstmt = null;
   ResultSet rs = null;
   
   // 생성자
   public GalleryDao(){
      Context context;
      try {
         context = new InitialContext();
         datasource = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
         conn = datasource.getConnection();
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }      
   }
   
   // 전체 게시판 리스트
   public ArrayList<GalleryDto> AllGalleryList(int cpage, int pagesize){
	   ArrayList<GalleryDto> gallerylist = null;
	   
      try{
    	  
         String sql =  "select * from "
         		+ "(select ROWNUM rn, gallery_id, gallery_title, member_id, gallery_date, gallery_like, gallery_file, gallery_content from gallery)"
         		+ " where rn between ? and ?";
         
         int start = cpage * pagesize - (pagesize - 1);
         int end = cpage * pagesize;

         pstmt = conn.prepareStatement(sql);
         
         pstmt.setInt(1, start);
         pstmt.setInt(2, end);
         rs = pstmt.executeQuery();
        
         gallerylist = new ArrayList<GalleryDto>();
         while(rs.next()){
            GalleryDto gallery = new GalleryDto();
            gallery.setRow_num(rs.getInt("rn"));
            gallery.setGallery_id(rs.getInt("gallery_id")); 
            gallery.setGallery_title(rs.getString("gallery_title"));
            gallery.setMember_id(rs.getString("member_id"));
            gallery.setGallery_date(rs.getDate("gallery_date"));
            gallery.setGallery_like(rs.getInt("gallery_like"));
            gallery.setGallery_file(rs.getString("gallery_file"));
            gallery.setContent(rs.getString("gallery_content"));
            gallerylist.add(gallery);
            }
         
         rs.close();
         pstmt.close();
         conn.close();
         
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      return gallerylist;
   }
   
   // 전체 게시판 게시물 총 건수
   public int AllboardCount(){
      int totalcount = 0;
      try {
         String sql = "select count(*) cnt from gallery";
         stmt = conn.createStatement();
         rs = stmt.executeQuery(sql);
         
         if(rs.next()) {
            totalcount = rs.getInt("cnt");
         }
         
         stmt.close();
        
      }catch(Exception e){
         System.out.println(e.getMessage());
      }
      return totalcount;
   }
   
   // 게시글 상세정보
   public GalleryDto BoardgetContentByBoardId(int gallery_id){
      GalleryDto board = null;
      try{
         String sql = "select * from gallery where gallery_id=?";
         pstmt = conn.prepareStatement(sql);
         pstmt.setInt(1, gallery_id);
         rs = pstmt.executeQuery();

         if(rs.next()){
            board = new GalleryDto();
            board.setGallery_id(rs.getInt(1));
            board.setGallery_title(rs.getString(2));
            board.setMember_id(rs.getString(3));
            board.setGallery_date(rs.getDate(4));
            board.setGallery_like(rs.getInt(5));
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      }
      return board;
   }
   
   // 게시판 글 쓰기
      public int GalleryWrite(GalleryDto gallerydto){
         int rowcount = 0;
         try{
            String sql = "insert into gallery(Gallery_id, Gallery_title, Member_id,"
                     + "Gallery_date, Gallery_like, Gallery_file, Gallery_content) values(Gallery_idx.nextval,?,?,sysdate,0,?,?)";
            pstmt = conn.prepareStatement(sql);
            
            pstmt.setString(1, gallerydto.getGallery_title());
            pstmt.setString(2, gallerydto.getMember_id());
            pstmt.setString(3, gallerydto.getGallery_file());
            pstmt.setString(4, gallerydto.getContent());
            
            rowcount = pstmt.executeUpdate();          
            
            pstmt.close();
         }catch(Exception e){
            System.out.println(e.getMessage());
         }
         return rowcount;
      }
      
      // 게시판 글 삭제
       public int GalleryDelete(int gallery_id, String member_id, int session_authority) {//gallery_id
          int rowcount = 0;
          String del_gallery_sql=null;
          try {
        	  if(session_authority==2){
                  del_gallery_sql = "delete from Gallery where gallery_id=?";
                  pstmt = conn.prepareStatement(del_gallery_sql);//del_gallery_sql
                  pstmt.setInt(1, gallery_id);
                  }else if(session_authority==1){           
                  del_gallery_sql = "delete from Gallery where gallery_id=? and member_id=? ";
                  pstmt = conn.prepareStatement(del_gallery_sql);//del_gallery_sql
                  pstmt.setInt(1, gallery_id);
                  pstmt.setString(2, member_id);
                  }else{
                     rowcount= 0;
                  }            
             
             rowcount = pstmt.executeUpdate();

             if (rowcount > 0) {
                conn.commit();
             } else {
                conn.rollback();
             }

             conn.close();
          } catch (Exception e) {
             System.out.println(e.getMessage());
          }
          return rowcount;
       }
    
      // 게시물 좋아요수 증가
      public int UpBoardLike(int gallery_id) {
         int rowcount = 0;
         try {
            String sql = "update gallery set gallery_like=gallery_like+1 where gallery_id=?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, gallery_id);
            
            rowcount = pstmt.executeUpdate();

            pstmt.close();
            conn.close();
         } catch (Exception e) {
            System.out.println(e.getMessage());
         }
         return rowcount;
      }
      
      //첨부파일 input
      public int GalleryFileInput(List<GalleryFileDto> filelist){
  		int rowcount = 0;
  		try{
  			String sql = "insert into Gallery_file values(?,?)";
  			for(GalleryFileDto file : filelist){
  				pstmt = conn.prepareStatement(sql);
  				pstmt.setInt(1, file.getGallery_id());
  				pstmt.setString(2, file.getGallery_file());
  				rowcount += pstmt.executeUpdate();
  			}  			
  			pstmt.close();
  			conn.close();
  		}catch(Exception e){
  			System.out.println(e.getMessage());
  		}
  		return rowcount;
  	}
  	
    	
  	// 첨부파일 output
  	public List<GalleryFileDto> GalleryFileOutput(){
  		List<GalleryFileDto> filelist = null;
  		try{
  			filelist = new ArrayList<GalleryFileDto>();
  			String sql = "select * from gallery_file where";
  			pstmt = conn.prepareStatement(sql);
  			
  			rs = pstmt.executeQuery();
  			
  			while(rs.next()){
  				GalleryFileDto file = new GalleryFileDto();
  				file.setGallery_id(rs.getInt(1));
  				file.setGallery_file(rs.getString(2));
  				filelist.add(file);
  			}
  			
  			rs.close();
  			pstmt.close();
  			conn.close();
  		}catch(Exception e){
  			System.out.println(e.getMessage());
  		}
  		return filelist;
  	}
  	
  	// 첨부 파일 삭제
  	public int GalleryFileDelete(int galleryIdx){
  		int rowcount = 0;
  		try{
  			String sql = "delete from gallery_file where gallery_id=?";
  			pstmt = conn.prepareStatement(sql);
  			pstmt.setInt(1, galleryIdx);
  			
  			rowcount = pstmt.executeUpdate();
  			
  			pstmt.close();
  			conn.close();
  		}catch(Exception e){
  			System.out.println(e.getMessage());
  		}
  		return rowcount;
  	}
     
}