package kr.or.kosta.Dto;

public class SeoulDto {
	private String seoul_gu;
	
	public SeoulDto(){}
	public SeoulDto(String seoul_gu) {
		this.seoul_gu = seoul_gu;
	}
	public String getSeoul_go() {
		return seoul_gu;
	}
	public void setSeoul_go(String seoul_gu) {
		this.seoul_gu = seoul_gu;
	}
	@Override
	public String toString() {
		return "SeoulDto [seoul_gu=" + seoul_gu + "]";
	}
}
