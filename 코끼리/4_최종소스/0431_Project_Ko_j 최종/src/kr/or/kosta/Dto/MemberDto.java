package kr.or.kosta.Dto;

public class MemberDto {
	private int rn;
	private String member_id;
	private String member_pw;
	private int member_authority;
	private String member_zipcode;
	private String member_dong;
	private String member_ho;
	
	public MemberDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MemberDto(String member_id, String member_pw, int member_authority,
			String member_zipcode, String member_dong, String member_ho) {
		super();
		this.member_id = member_id;
		this.member_pw = member_pw;
		this.member_authority = member_authority;
		this.member_zipcode = member_zipcode;
		this.member_dong = member_dong;
		this.member_ho = member_ho;
	}
	
	
	public int getRn() {
		return rn;
	}
	public void setRn(int rn) {
		this.rn = rn;
	}
	public String getMember_id() {
		return member_id;
	}
	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}
	public String getMember_pw() {
		return member_pw;
	}
	public void setMember_pw(String member_pw) {
		this.member_pw = member_pw;
	}
	public int getMember_authority() {
		return member_authority;
	}
	public void setMember_authority(int member_authority) {
		this.member_authority = member_authority;
	}
	public String getMember_zipcode() {
		return member_zipcode;
	}
	public void setMember_zipcode(String member_zipcode) {
		this.member_zipcode = member_zipcode;
	}
	public String getMember_dong() {
		return member_dong;
	}
	public void setMember_dong(String member_dong) {
		this.member_dong = member_dong;
	}
	public String getMember_ho() {
		return member_ho;
	}
	public void setMember_ho(String member_ho) {
		this.member_ho = member_ho;
	}
	@Override
	public String toString() {
		return "MemberDto [member_id=" + member_id + ", member_pw=" + member_pw
				+ ", member_authority=" + member_authority
				+ ", member_zipcode=" + member_zipcode + ", member_dong="
				+ member_dong + ", member_ho=" + member_ho + "]";
	}
	
}
