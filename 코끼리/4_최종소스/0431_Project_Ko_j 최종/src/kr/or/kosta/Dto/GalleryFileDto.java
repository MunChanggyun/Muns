package kr.or.kosta.Dto;

public class GalleryFileDto {
	private int gallery_id;
	private String gallery_file;
	private String gallery_file_oriname;
	
	public GalleryFileDto(){ }

	public GalleryFileDto(int gallery_id, String gallery_file,
			String gallery_file_oriname) {
		super();
		this.gallery_id = gallery_id;
		this.gallery_file = gallery_file;
		this.gallery_file_oriname = gallery_file_oriname;
	}

	public int getGallery_id() {
		return gallery_id;
	}

	public void setGallery_id(int gallery_id) {
		this.gallery_id = gallery_id;
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

	@Override
	public String toString() {
		return "GalleryFileDto [gallery_id=" + gallery_id + ", gallery_file="
				+ gallery_file + ", gallery_file_oriname="
				+ gallery_file_oriname + "]";
	}

	
	
}
