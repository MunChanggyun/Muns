package kr.or.kosta.Dto;

import java.sql.Date;

public class CalendarDto {
	private int calendar_id;
	private String calendar_content;
	private Date calendar_start;
	private Date calendar_end;
	
	public CalendarDto() {
		
		
	}
	public CalendarDto(int calendar_id, String calendar_content,
			Date calendar_start, Date calendar_end) {
		
		this.calendar_id = calendar_id;
		this.calendar_content = calendar_content;
		this.calendar_start = calendar_start;
		this.calendar_end = calendar_end;
	}
	public int getCalendar_id() {
		return calendar_id;
	}
	public void setCalendar_id(int calendar_id) {
		this.calendar_id = calendar_id;
	}
	public String getCalendar_content() {
		return calendar_content;
	}
	public void setCalendar_content(String calendar_content) {
		this.calendar_content = calendar_content;
	}
	public Date getCalendar_start() {
		return calendar_start;
	}
	public void setCalendar_start(Date calendar_start) {
		this.calendar_start = calendar_start;
	}
	public Date getCalendar_end() {
		return calendar_end;
	}
	public void setCalendar_end(Date calendar_end) {
		this.calendar_end = calendar_end;
	}
	@Override
	public String toString() {
		return "CalendarDto [calendar_id=" + calendar_id
				+ ", calendar_content=" + calendar_content
				+ ", calendar_start=" + calendar_start + ", calendar_end="
				+ calendar_end + "]";
	}
	
}
