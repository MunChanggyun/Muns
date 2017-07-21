package kr.or.kosta.Dto;

import java.sql.Date;


public class ReservationDto {
   private int reservation_id;
   private Date reservation_time;
   private String time_start;
   private String facility_id;
   private String member_id;
   
   public ReservationDto() {
      super();
      // TODO Auto-generated constructor stub
   }

   public int getReservation_id() {
      return reservation_id;
   }

   public void setReservation_id(int reservation_id) {
      this.reservation_id = reservation_id;
   }

   public Date getReservation_time() {
      return reservation_time;
   }

   public void setReservation_time(Date reservation_time) {
      this.reservation_time = reservation_time;
   }

   public String getTime_start() {
      return time_start;
   }

   public void setTime_start(String time_start) {
      this.time_start = time_start;
   }

   public String getFacility_id() {
      return facility_id;
   }

   public void setFacility_id(String facility_id) {
      this.facility_id = facility_id;
   }

   public String getMember_id() {
      return member_id;
   }

   public void setMember_id(String member_id) {
      this.member_id = member_id;
   }

   public ReservationDto(int reservation_id, Date reservation_time,
         String time_start, String facility_id, String member_id) {
      super();
      this.reservation_id = reservation_id;
      this.reservation_time = reservation_time;
      this.time_start = time_start;
      this.facility_id = facility_id;
      this.member_id = member_id;
   }
   
   
}