package kr.or.kosta.Dto;

import java.sql.Date;

public class GalleryDto {
	private int row_num;
	private int gallery_id;
	private String gallery_title;
	private String member_id;
	private Date gallery_date;
	private int gallery_like;
	private String gallery_file;
	private String gallery_file_oriname;
	private String content;
	
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getGallery_file() {
		return gallery_file;
	}
	public void setGallery_file(String gallery_file) {
		this.gallery_file = gallery_file;
	}
	public String getGallery_file_oriname() {
		return gallery_file_oriname;
	}
	public void setGallery_file_oriname(String gallery_file_oriname) {
		this.gallery_file_oriname = gallery_file_oriname;
	}
	public int getRow_num() {
		return row_num;
	}
	public void setRow_num(int row_num) {
		this.row_num = row_num;
	}
	public int getGallery_id() {
		return gallery_id;
	}
	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
	}
	public String getGallery_title() {
		return gallery_title;
	}
	public void setGallery_title(String gallery_title) {
		this.gallery_title = gallery_title;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public Date getGallery_date() {
		return gallery_date;
	}
	public void setGallery_date(Date gallery_date) {
		this.gallery_date = gallery_date;
	}
	public int getGallery_like() {
		return gallery_like;
	}
	public void setGallery_like(int gallery_like) {
		this.gallery_like = gallery_like;
	}

}
