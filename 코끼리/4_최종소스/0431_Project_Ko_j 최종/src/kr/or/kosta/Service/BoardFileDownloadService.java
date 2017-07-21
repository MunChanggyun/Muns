package kr.or.kosta.Service;

import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.BoardDao;
import kr.or.kosta.Dto.BoardDto;
import kr.or.kosta.Dto.CommentsDto;

public class BoardFileDownloadService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("boardIdx"); //글번호
		ActionForward forward = null;
		try{
			int categoryCode = Integer.parseInt(request.getParameter("categoryCode")); // 게시판 종류
			String cpage =	request.getParameter("cp"); // 현재 페이지 번호
		    String psize = request.getParameter("ps"); // page size 정보
		    
		    // 글번호를 가지고 오지 않았을 경우 예외처리
		 	if(idx == null || idx.trim().equals("")){
		 		// 원래 있던 페이지로 넘어가기
		 		response.sendRedirect("/Certain.board?categoryCode="+categoryCode);
		 	}
		 	
		 	int boardIdx = Integer.parseInt(idx.trim());
		
		    if(cpage==null || cpage.trim().equals("")){
				cpage="1";
			}
			if(psize==null || psize.trim().equals("")){
				psize="5";
			}
			/////////////////////////////////////////////////////
			BoardDao boardDao = new BoardDao();
			
			forward = new ActionForward();
			
		    request.setAttribute("boardIdx", boardIdx);
		    request.setAttribute("cpage", cpage);
		    request.setAttribute("psize", psize);
		    
		    // 다운로드할 파일명 얻기
		    String fileName = request.getParameter("fileName");
		    
		    // 물리적 경로 얻기
		    String savepath = "upload";
		    
		    String downloadpath = request.getRealPath(savepath);
		    String FilePath = downloadpath + "\\" + fileName;

		    		    
		    // 파일을 읽어서 출력
		    byte[] b = new byte[4096]; //2kb  // 요기는 필요에 따라 조정 가능
		    FileInputStream in = new FileInputStream(FilePath); // 실 저장 경로에서
		    
		    // 실제 인지할수 있는 타입의 파일들이면 자신의 => contentType
		    // application이 인지할 수 없는 확장자 파일은 null 경우에 형식을 잡아준다
		    String sMimeType = request.getSession().getServletContext().getMimeType(FilePath); // 파일의 타입 정보
		    if(sMimeType == null){
		     // 알수 없는 형식의 파일은 
		     // application/octet-stream 기본값으로 (알수 없는 파일 형식)
		     sMimeType = "application/octet-stream";
		    }
		    
		    //2. client 전달되는 형식을 지정(Type)
		    response.setContentType(sMimeType);
		    
		    /*<% String currentDir = System.getProperty("user.dir"); %>*/
		  
		    
		    //3. 인코딩 처리(한글 : 파일이 가지고 있는 한글에 대한 처리)
		    String SEncoding = new String(fileName.getBytes("utf-8"),"utf-8");
		    
		    //4. 다운로드(기본 설정) 구현
		    //response.setCharacterEncoding("euc-kr");
		    //response.setHeader("Content-Disposition","attachment;filename="+SEncoding);
		    //파일 다운로드를 위한 헤더 정보 수정 (파일이름 문자열 다시 인코딩)
		    response.setHeader("Content-Disposition", 
		           "attachment;filename="+new String(fileName.getBytes(),"ISO8859_1"));   //filename.getBytes(),"ISO8859_1")
		    // 문서를 읽어 들여서 ISO8859_1 형식으로 변환 ......

		    // 5. 파일 출력하기
		    ServletOutputStream out2 = response.getOutputStream();
		    int numread;
		    while((numread = in.read(b,0,b.length)) != -1){
		       out2.write(b,0,numread);
		    }
		    
		    out2.flush();
		    out2.close();
		    in.close();
		    
		    forward.setRedirect(false);
		    forward.setPath("/DetailContents.board?categoryCode="+ categoryCode + "&boardIdx=" + boardIdx);
		    
		}catch(Exception e){
			System.out.println(e.getMessage());
		}		
		return forward;
	}
}
