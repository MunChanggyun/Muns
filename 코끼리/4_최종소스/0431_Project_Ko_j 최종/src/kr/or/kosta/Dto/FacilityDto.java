package kr.or.kosta.Dto;

public class FacilityDto {
	private String facility_id;
	private String facility_name;
	
	public FacilityDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FacilityDto(String facility_id, String facility_name) {
		super();
		this.facility_id = facility_id;
		this.facility_name = facility_name;
	}
	public String getFacility_id() {
		return facility_id;
	}
	public void setFacility_id(String facility_id) {
		this.facility_id = facility_id;
	}
	public String getFacility_name() {
		return facility_name;
	}
	public void setFacility_name(String facility_name) {
		this.facility_name = facility_name;
	}
	@Override
	public String toString() {
		return "FacilityDto [facility_id=" + facility_id + ", facility_name="
				+ facility_name + "]";
	}
	
}
