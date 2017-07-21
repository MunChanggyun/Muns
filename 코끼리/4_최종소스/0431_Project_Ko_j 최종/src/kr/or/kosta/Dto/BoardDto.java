package kr.or.kosta.Dto;

import java.util.Date;

public class BoardDto {
	private int row_num;
	private int board_id;
	private int boardCategory_code;
	private String member_id;
	private String board_title_h;
	private String board_title_t;
	private String board_content;
	private Date board_date;
	private int board_like;
	private int board_hit;
	private int ref;
	private int depth;
	private int step;
	
	public BoardDto() {
		super();
	}

	public BoardDto(int row_num, int board_id, int boardCategory_code,
			String member_id, String board_title_h, String board_title_t,
			String board_content, Date board_date, int board_like,
			int board_hit, int ref, int depth, int step) {
		super();
		this.row_num = row_num;
		this.board_id = board_id;
		this.boardCategory_code = boardCategory_code;
		this.member_id = member_id;
		this.board_title_h = board_title_h;
		this.board_title_t = board_title_t;
		this.board_content = board_content;
		this.board_date = board_date;
		this.board_like = board_like;
		this.board_hit = board_hit;
		this.ref = ref;
		this.depth = depth;
		this.step = step;
	}

	public int getRow_num() {
		return row_num;
	}

	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public int getBoardCategory_code() {
		return boardCategory_code;
	}

	public void setBoardCategory_code(int boardCategory_code) {
		this.boardCategory_code = boardCategory_code;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getBoard_title_h() {
		return board_title_h;
	}

	public void setBoard_title_h(String board_title_h) {
		this.board_title_h = board_title_h;
	}

	public String getBoard_title_t() {
		return board_title_t;
	}

	public void setBoard_title_t(String board_title_t) {
		this.board_title_t = board_title_t;
	}

	public String getBoard_content() {
		return board_content;
	}

	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}

	public Date getBoard_date() {
		return board_date;
	}

	public void setBoard_date(Date board_date) {
		this.board_date = board_date;
	}

	public int getBoard_like() {
		return board_like;
	}

	public void setBoard_like(int board_like) {
		this.board_like = board_like;
	}

	public int getBoard_hit() {
		return board_hit;
	}

	public void setBoard_hit(int board_hit) {
		this.board_hit = board_hit;
	}

	public int getRef() {
		return ref;
	}

	public void setRef(int ref) {
		this.ref = ref;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}
	
}
