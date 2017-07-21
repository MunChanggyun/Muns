package kr.or.kosta.Dto;

public class BoardCategoryDto {
	private int boardCategory_code;
	private String boardCategory_name;
	private String boardCategory_description;
	
	public BoardCategoryDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BoardCategoryDto(int boardCategory_code, String boardCategory_name,
			String boardCategory_description) {
		super();
		this.boardCategory_code = boardCategory_code;
		this.boardCategory_name = boardCategory_name;
		this.boardCategory_description = boardCategory_description;
	}
	public int getBoardCategory_code() {
		return boardCategory_code;
	}
	public void setBoardCategory_code(int boardCategory_code) {
		this.boardCategory_code = boardCategory_code;
	}
	public String getBoardCategory_name() {
		return boardCategory_name;
	}
	public void setBoardCategory_name(String boardCategory_name) {
		this.boardCategory_name = boardCategory_name;
	}
	public String getBoardCategory_description() {
		return boardCategory_description;
	}
	public void setBoardCategory_description(String boardCategory_description) {
		this.boardCategory_description = boardCategory_description;
	}
	@Override
	public String toString() {
		return "BoardCategoryDto [boardCategory_code=" + boardCategory_code
				+ ", boardCategory_name=" + boardCategory_name
				+ ", boardCategory_description=" + boardCategory_description
				+ "]";
	}
	
}
