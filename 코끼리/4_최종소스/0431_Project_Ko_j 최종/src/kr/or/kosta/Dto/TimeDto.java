package kr.or.kosta.Dto;

import java.util.Date;

public class TimeDto {
	private Date time_date;
	private Date time_start;
	private Date time_end;
	
	public TimeDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public TimeDto(Date time_date, Date time_start, Date time_end) {
		super();
		this.time_date = time_date;
		this.time_start = time_start;
		this.time_end = time_end;
	}
	public Date getTime_date() {
		return time_date;
	}
	public void setTime_date(Date time_date) {
		this.time_date = time_date;
	}
	public Date getTime_start() {
		return time_start;
	}
	public void setTime_start(Date time_start) {
		this.time_start = time_start;
	}
	public Date getTime_end() {
		return time_end;
	}
	public void setTime_end(Date time_end) {
		this.time_end = time_end;
	}
	@Override
	public String toString() {
		return "TimeDto [time_date=" + time_date + ", time_start=" + time_start
				+ ", time_end=" + time_end + "]";
	}
	
}
