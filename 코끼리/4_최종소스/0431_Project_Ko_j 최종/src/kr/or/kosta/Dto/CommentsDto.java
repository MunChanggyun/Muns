package kr.or.kosta.Dto;

import java.util.Date;

public class CommentsDto {
	private int comment_id;
	private int board_id;
	private String member_id;
	private Date comment_date;
	private String comment_content;

	public CommentsDto() {
		super();
	}
	
	public CommentsDto(int comment_id, int board_id, String member_id,
			Date comment_date, String comment_content) {
		super();
		this.comment_id = comment_id;
		this.board_id = board_id;
		this.member_id = member_id;
		this.comment_date = comment_date;
		this.comment_content = comment_content;
	}

	public int getComment_id() {
		return comment_id;
	}
	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getComment_date() {
		return comment_date;
	}
	public void setComment_date(Date comment_date) {
		this.comment_date = comment_date;
	}

	public String getComment_content() {
		return comment_content;
	}

	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}

	@Override
	public String toString() {
		return "CommentsDto [comment_id=" + comment_id + ", board_id="
				+ board_id + ", member_id=" + member_id + ", comment_date="
				+ comment_date + ", comment_content=" + comment_content + "]";
	}
}
