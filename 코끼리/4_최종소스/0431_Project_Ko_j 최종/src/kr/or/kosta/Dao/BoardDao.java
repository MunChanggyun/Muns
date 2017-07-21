package kr.or.kosta.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import kr.or.kosta.Dto.BoardCategoryDto;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.BoardFileDto;
import kr.or.kosta.Dto.CommentsDto;

public class BoardDao {
	DataSource datasource =  null;
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	// 생성자
	public BoardDao(){
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
	public List<BoardDto> AllBoardList(int cpage, int pagesize){
		List<BoardDto> baordlist = null;
		try{
			String sql = "select * from"
					+ "(select ROWNUM rn, board_id, boardcategory_code, member_id, board_title_h,"
					+ "board_title_t, board_content, board_date, board_like, board_hit, ref, depth, step from"
					+ "(select * from board order by ref DESC, step ASC)) where rn between ? and ?";

			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rs = pstmt.executeQuery();
			
			baordlist = new ArrayList<BoardDto>();
			while(rs.next()){
            	BoardDto board = new BoardDto();
            	board.setRow_num(rs.getInt("rn"));
            	board.setBoard_id(rs.getInt("board_id")); 
            	board.setBoardCategory_code(rs.getInt("boardcategory_code"));
            	board.setMember_id(rs.getString("member_id"));
            	board.setBoard_title_h(rs.getString("board_title_h"));
            	board.setBoard_title_t(rs.getString("board_title_t"));
            	board.setBoard_content(rs.getString("board_content"));
            	board.setBoard_date((java.util.Date)rs.getDate("board_date"));
            	board.setBoard_like(rs.getInt("board_like"));
            	board.setBoard_hit(rs.getInt("board_hit"));
            	board.setRef(rs.getInt("ref"));
            	board.setDepth(rs.getInt("depth"));
            	board.setStep(rs.getInt("step"));
            	
            	baordlist.add(board);
            }
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return baordlist;
	}
	
	// 전체 게시판 게시물 총 건수
	public int AllboardCount(){
		int totalcount = 0;
		try {
			String sql = "select count(*) cnt from board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return totalcount;
	}
	
	// 게시글 상세정보
	public BoardDto BoardgetContentByBoardId(int boardIdx){
		BoardDto board = null;
		try{
			String sql = "select * from board where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rs = pstmt.executeQuery();

			if(rs.next()){
				board = new BoardDto();
				board.setBoard_id(rs.getInt(1));
				board.setBoardCategory_code(rs.getInt(2));
				board.setMember_id(rs.getString(3));
				board.setBoard_title_h(rs.getString(4));
				board.setBoard_title_t(rs.getString(5));
				board.setBoard_content(rs.getString(6));
				board.setBoard_date(rs.getDate(7));
				board.setBoard_like(rs.getInt(8));
				board.setBoard_hit(rs.getInt(9));
				board.setRef(rs.getInt(10));
				board.setDepth(rs.getInt(11));
				board.setStep(rs.getInt(12));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return board;
	}

	// 특정 게시판 (공지사항/소통 게시판/갤러리) 리스트
	public List<BoardDto> CertainBoardList(int categoryCode, int cpage, int pagesize){
		List<BoardDto> certainBaordlist = null;
		try{
			String sql = "select * from"
					+ "(select ROWNUM rn, board_id, boardcategory_code, member_id, board_title_h,"
					+ "board_title_t, board_content, board_date, board_like, board_hit, ref, depth, step from"
					+ "(select * from board order by ref DESC, step ASC) where boardcategory_code=?)"
					+ "where rn between ? and ?";
			
			int start = cpage * pagesize - (pagesize - 1);
			int end = cpage * pagesize;

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			rs = pstmt.executeQuery();
			
			certainBaordlist = new ArrayList<BoardDto>();
			while(rs.next()){
            	BoardDto certainBoard = new BoardDto();
            	certainBoard.setRow_num(rs.getInt("rn"));
            	certainBoard.setBoard_id(rs.getInt("board_id")); 
            	certainBoard.setBoardCategory_code(rs.getInt("boardcategory_code"));
            	certainBoard.setMember_id(rs.getString("member_id"));
            	certainBoard.setBoard_title_h(rs.getString("board_title_h"));
            	certainBoard.setBoard_title_t(rs.getString("board_title_t"));
            	certainBoard.setBoard_content(rs.getString("board_content"));
            	certainBoard.setBoard_date(rs.getDate("board_date"));
            	certainBoard.setBoard_like(rs.getInt("board_like"));
            	certainBoard.setBoard_hit(rs.getInt("board_hit"));
            	certainBoard.setRef(rs.getInt("ref"));
            	certainBoard.setDepth(rs.getInt("depth"));
            	certainBoard.setStep(rs.getInt("step"));
            	
            	certainBaordlist.add(certainBoard);
            }
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return certainBaordlist;
	}
	
	// 특정 게시판(공지사항/소통 게시판/갤러리) 게시물 총 건수
	public int CertainboardCount(int categoryCode){
		int totalcount = 0;
		try {
			String sql = "select count(*) cnt from board where boardcategory_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return totalcount;
	}
	
	// 특정 게시판(공지사항/소통 게시판/갤러리) 정보 가져오기
	public BoardCategoryDto BoardCategoryInfoByCode(int categoryCode) {
		BoardCategoryDto category = null;
		try {
			category = new BoardCategoryDto();
			String sql = "select * from board_category where boardcategory_code=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, categoryCode);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				category.setBoardCategory_code(rs.getInt("boardcategory_code"));
				category.setBoardCategory_name(rs.getString("boardcategory_name"));
				category.setBoardCategory_description(rs.getString("boardcategory_description"));
			}
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return category;
	}
		
	// 게시판 글 쓰기
	public int BoardWrite(BoardDto board){
		int rowcount = 0;
		int boardIdx = 0;
		try{
			// 글쓰기
			String sql = "insert into board(board_id, boardcategory_code, member_id, board_title_h,"
					   + "board_title_t, board_content, board_date, board_like, board_hit,"
					   + "ref, depth, step) values(board_id.nextval,?,?,?,?,?,sysdate,0,0,?,0,0)";
			int refer = getMaxRefer(conn) + 1;
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, board.getBoardCategory_code());
			pstmt.setString(2, board.getMember_id());
			pstmt.setString(3, board.getBoard_title_h());
			pstmt.setString(4, board.getBoard_title_t());
			pstmt.setString(5, board.getBoard_content());
			
			
			pstmt.setInt(6, refer);
			rowcount = pstmt.executeUpdate();
			
			
			// 방금 쓴 글 글번호 가져오기
			String get_boardIdx_sql = "select nvl(max(board_id),0) from board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(get_boardIdx_sql);
			if(rs.next()){
				boardIdx = rs.getInt(1);
			}
			
			
			stmt.close();			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return boardIdx;
	}
	
	// 답글이 있는지 확인
	public int BoardRewriteCheck(int boardIdx){
		String sql = "select count(*) cnt from board where ref=(select ref from board board where board_id=?) and depth > (select depth from board board where board_id=?)";
		int cnt = 0;
		try{
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			pstmt.setInt(2, boardIdx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cnt = rs.getInt("cnt");
				
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return cnt;
	}
	
	// 답글 쓰기
	public int BoardReWrite(BoardDto board) {
		int rowcount = 0;
		int boardIdx = 0;
		try {
			// 1. 현재 원본글(답변처리) 글에 대한 refer , depth , step
			String refer_depth_step_sql = "select ref, depth, step from board where board_id=?";

			// 2. 여러개의 답변글이 들어오는 경우 refer 정렬되는 순서를 정의 (step ) 처리
			String step_update_sql = "update board set step=step+1 where step > ? and ref=?";

			// 3. 실 답변글 insert 처리하기
			String rewrite_sql = "insert into board(board_id, boardcategory_code, member_id,"
					    + "board_title_h, board_title_t, board_content, board_date, board_like, board_hit,"
					    + "ref, depth, step) values(board_id.nextval,?,?,?,?,?,sysdate,0,0,?,?,?)";

			// refer, depth, step 값 가져오기
			pstmt = conn.prepareStatement(refer_depth_step_sql);
			pstmt.setInt(1, board.getBoard_id());
			rs = pstmt.executeQuery();
			
			// 4. 방금 쓴 글 글번호 가져오기
			String get_boardIdx_sql = "select nvl(max(board_id),0) from board";

			if(rs.next()){
				int ref = rs.getInt("ref");
				int depth = rs.getInt("depth");
				int step = rs.getInt("step");

				// step () 값 업데이트
				pstmt = conn.prepareStatement(step_update_sql);
				pstmt.setInt(1, step);
				pstmt.setInt(2, ref);
				pstmt.executeUpdate();

				// 답글 데이터 insert
				pstmt = conn.prepareStatement(rewrite_sql);
				pstmt.setInt(1, board.getBoardCategory_code());
				pstmt.setString(2, board.getMember_id());
				pstmt.setString(3, board.getBoard_title_h());
				pstmt.setString(4, board.getBoard_title_t());
				pstmt.setString(5, board.getBoard_content());
				
				pstmt.setInt(6, ref);
				pstmt.setInt(7, depth + 1); // 답글 처리
				pstmt.setInt(8, step + 1); // 답글처리 (현재 읽은 글보다 큰 순번은 + 1)

				rowcount = pstmt.executeUpdate();
				
				if(rowcount > 0){
					
					stmt = conn.createStatement();
					rs = stmt.executeQuery(get_boardIdx_sql);
					if(rs.next()){
						boardIdx = rs.getInt(1);
					}
				}else{
					
				}				
				
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return boardIdx;
	}

	// 글 참조 번호 함수
	public int getMaxRefer(Connection conn){
		int referMax = 0;
		try {
			String sql = "select nvl(max(ref),0) from board";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				referMax = rs.getInt(1);
			}
			
			rs.close();
			stmt.close();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return referMax;
	}
	
	// 게시판 글 수정
 	public int BoardEdit(BoardDto board){
 		int rowcount = 0;
 		try{
 			String sql = "update board set board_title_t=?, board_content=? where board_id=?"; 
 			pstmt = conn.prepareStatement(sql);
 			pstmt.setString(1, board.getBoard_title_t());
 			pstmt.setString(2, board.getBoard_content());
 			pstmt.setInt(3, board.getBoard_id());
 			
 			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
 		return rowcount;
 	}
	
 	// 게시판 글 삭제
  	public int BoardDelete(int boardIdx) {
  		int rowcount = 0;
  		try {
  			// 덧글 삭제 (참조 제약 관계)
  			String del_comment_sql = "delete from comments where board_id=?";
  			// 게시글 삭제
  			String del_board_sql = "delete from Board where board_id=?";

  			conn.setAutoCommit(false); // rollback , commit 처리 강제

  			// 덧글 삭제
  			pstmt = conn.prepareStatement(del_comment_sql);
  			pstmt.setInt(1, boardIdx);
  			pstmt.executeUpdate();

  			// 게시글 삭제
  			pstmt = conn.prepareStatement(del_board_sql);
  			pstmt.setInt(1, boardIdx);
  			rowcount = pstmt.executeUpdate();

  			if (rowcount > 0) {
  				conn.commit(); // 정상처리
  			} else {
  				conn.rollback();
  			}
  			
  			pstmt.close();
  			conn.close();
  		} catch (Exception e) {
  			System.out.println(e.getMessage());
  		}
  		return rowcount;
  	}

 	//게시물 조회수 증가
	public int UpBoardHit(int boardIdx) {
		int rowcount = 0;
		try {
			String sql = "update board set board_hit=board_hit+1 where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rowcount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
 	
	// 게시물 좋아요수 증가
	public int UpBoardLike(int boardIdx) {
		int rowcount = 0;
		try {
			String sql = "update board set board_like=board_like+1 where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rowcount = pstmt.executeUpdate();

			pstmt.close();
			conn.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
 	
	// 첨부파일 input
	public int BoardFileInput(List<BoardFileDto> filelist){
		int rowcount = 0;
		try{
			String sql = "insert into board_file values(?,?,?)";
			for(BoardFileDto file : filelist){
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, file.getBoard_id());
				pstmt.setString(2, file.getBoardFile_name());
				pstmt.setString(3, file.getBoardFile_oriname());
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
	public List<BoardFileDto> BoardFileOutput(int boardIdx){
		List<BoardFileDto> filelist = null;
		try{
			filelist = new ArrayList<BoardFileDto>();
			String sql = "select * from board_file where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				BoardFileDto file = new BoardFileDto();
				file.setBoard_id(rs.getInt(1));
				file.setBoardFile_name(rs.getString(2));
				file.setBoardFile_oriname(rs.getString(3));
				filelist.add(file);
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return filelist;
	}
	
	// 글번호로 첨부 파일 삭제
	public int BoardFileDelete(int boardIdx){
		int rowcount = 0;
		try{
			String sql = "delete from board_file where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
			
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	// 이름으로 첨부 파일 삭제
	public int BoardFileDelete(String fileName) {
		int rowcount = 0;
		try {
			String sql = "delete from board_file where boardFile_name=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fileName);

			rowcount = pstmt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return rowcount;
	}

	// 댓글 쓰기
	public int CommentWrite(CommentsDto comment){
		int rowcount = 0;
		try{
			String sql = "insert into comments(comment_id, board_id, member_id, comment_date, comment_content)"
					   + "values(comment_id.nextval,?,?,sysdate,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, comment.getBoard_id());
			pstmt.setString(2, comment.getMember_id());
			pstmt.setString(3, comment.getComment_content());
		
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	// 댓글 삭제
	public int CommentDelete(int commentId){
		int rowcount = 0;
		
		try{
			String sql = "delete from Comments where comment_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, commentId);
			
			rowcount = pstmt.executeUpdate();
			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	// 댓글 삭제
	public int CommentAllDelete(int boardIdx){
		int rowcount = 0;
			
		try{
			String sql = "delete from Comments where board_id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardIdx);
				
			rowcount = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return rowcount;
	}
	
	// 댓글 리스트
	public List<CommentsDto> CommentList(int boardIdx){
		List<CommentsDto> commentList = null;
		try{
			String sql = "select * from Comments where board_id=? order by comment_id desc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,boardIdx);
			rs = pstmt.executeQuery();
			
			commentList = new ArrayList<CommentsDto>();
			while(rs.next()){
				CommentsDto comments = new CommentsDto();
				comments.setComment_id(rs.getInt("comment_id"));
				comments.setBoard_id(rs.getInt("board_id"));
				comments.setMember_id(rs.getString("member_id"));
            	comments.setComment_date(rs.getDate("comment_date"));
            	comments.setComment_content(rs.getString("comment_content"));
            	
            	commentList.add(comments);
            }
			
			rs.close();
			pstmt.close();
			conn.close();
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return commentList;
	}
}
