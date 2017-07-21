package kr.or.kosta.Service;


import java.io.File;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.BoardFileDto;

public class BoardEditService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		try{
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
			String uploadpath = request.getRealPath("upload");

			int size = 10 * 1024 * 1024; // 10M

			// cos.jar 파일 제공하는 MultipartRequest 사용
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
		    
		    // 넘어온 값을 변수에 저장
		    int categoryCode = Integer.parseInt(multi.getParameter("categoryCode"));
		    String boardTitle= multi.getParameter("boardTitle");
		    String boardContent=multi.getParameter("smarteditor");
			int boardIdx = Integer.parseInt(multi.getParameter("boardIdx").trim());
			String [] delFileList = multi.getParameterValues("del");
			
			forward = new ActionForward();
			forward.setRedirect(false);
			
			
			BoardDto boardDto = new BoardDto();
			boardDto.setBoard_title_t(boardTitle);
			boardDto.setBoard_content(boardContent);
			boardDto.setBoard_id(boardIdx);
			
			BoardDao boardDao = new BoardDao();
			int result = boardDao.BoardEdit(boardDto);
			
			String path = "C:/Users/Administrator/Desktop/Team5/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/0427Project_Ko/upload"; // 삭제할 파일의 경로
			
			// 삭제할 파일 이름 가져오기
			String fileName = "";
			if(delFileList != null){
				for(int i=0 ; i<delFileList.length ; i++){
					fileName = delFileList[i];
					
					// 서버 폴더에 저장되어 있는 파일 삭제
					path += fileName;
					File file = new File(path);
					if(file.exists() == true){
						file.delete();
					}				
					boardDao.BoardFileDelete(fileName);
				}
			}
			
			// 새로운 첨부 파일 저장
			Enumeration<String> filenames = multi.getFileNames();
			List<BoardFileDto> filelist = new ArrayList<BoardFileDto>();
			while (filenames.hasMoreElements()) {
				BoardFileDto boardfiledto = new BoardFileDto();
				String file = (String) filenames.nextElement();
				boardfiledto.setBoard_id(boardIdx);
				boardfiledto.setBoardFile_name(multi.getFilesystemName(file));
				boardfiledto.setBoardFile_oriname(multi
						.getOriginalFileName(file));
				filelist.add(boardfiledto);
			}
			boardDao.BoardFileInput(filelist);

			forward.setRedirect(false);
			forward.setPath("/DetailContents.board?categoryCode="+ categoryCode + "&boardIdx=" + boardIdx); // 수정한 게시물로 이동

			
			
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}
}
