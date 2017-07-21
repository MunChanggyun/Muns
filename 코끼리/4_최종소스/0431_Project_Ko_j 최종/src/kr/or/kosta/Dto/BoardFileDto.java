package kr.or.kosta.Dto;

public class BoardFileDto {
	private int board_id;
	private String boardFile_name;
	private String boardFile_oriname;
	
	public BoardFileDto(){ }

	public BoardFileDto(int board_id, String boardFile_name,
			String boardFile_oriname) {
		super();
		this.board_id = board_id;
		this.boardFile_name = boardFile_name;
		this.boardFile_oriname = boardFile_oriname;
	}

	public int getBoard_id() {
		return board_id;
	}

	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}

	public String getBoardFile_name() {
		return boardFile_name;
	}

	public void setBoardFile_name(String boardFile_name) {
		this.boardFile_name = boardFile_name;
	}

	public String getBoardFile_oriname() {
		return boardFile_oriname;
	}

	public void setBoardFile_oriname(String boardFile_oriname) {
		this.boardFile_oriname = boardFile_oriname;
	}

	@Override
	public String toString() {
		return "BoardFileDto [board_id=" + board_id + ", boardFile_name="
				+ boardFile_name + ", boardFile_oriname=" + boardFile_oriname
				+ "]";
	}
	
}
