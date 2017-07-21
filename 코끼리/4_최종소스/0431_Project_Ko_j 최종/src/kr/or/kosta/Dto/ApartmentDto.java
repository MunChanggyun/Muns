package kr.or.kosta.Dto;

public class ApartmentDto {
	private String apartment_name;
	private String apartment_url;
	private String apartment_zipcode;
	private String seoul_gu;
	
	public ApartmentDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ApartmentDto(String apartment_name, String apartment_url,
			String apartment_zipcode, String seoul_gu) {
		super();
		this.apartment_name = apartment_name;
		this.apartment_url = apartment_url;
		this.apartment_zipcode = apartment_zipcode;
		this.seoul_gu = seoul_gu;
	}
	public String getApartment_name() {
		return apartment_name;
	}
	public void setApartment_name(String apartment_name) {
		this.apartment_name = apartment_name;
	}
	public String getApartment_url() {
		return apartment_url;
	}
	public void setApartment_url(String apartment_url) {
		this.apartment_url = apartment_url;
	}
	public String getApartment_zipcode() {
		return apartment_zipcode;
	}
	public void setApartment_zipcode(String apartment_zipcode) {
		this.apartment_zipcode = apartment_zipcode;
	}
	public String getSeoul_gu() {
		return seoul_gu;
	}
	public void setSeoul_gu(String seoul_gu) {
		this.seoul_gu = seoul_gu;
	}
	@Override
	public String toString() {
		return "ApartmentDto [apartment_name=" + apartment_name
				+ ", apartment_url=" + apartment_url + ", apartment_zipcode="
				+ apartment_zipcode + ", seoul_gu=" + seoul_gu + "]";
	}
	
}
