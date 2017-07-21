package kr.or.kosta.Service;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.GalleryDao;
import kr.or.kosta.Dto.GalleryDto;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class GalleryWriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		try{
			request.setCharacterEncoding("UTF-8");
		    response.setContentType("text/html; charset=UTF-8");
		    
			String uploadpath = request.getRealPath("upload");
			int size = 10 * 1024 * 1024; // 10M

			// cos.jar 파일 제공하는 MultipartRequest 사용 -> 첨부파일 받아오려면 사용해야함
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8", new DefaultFileRenamePolicy());
		    
		    String member_id = (String)(session.getAttribute("session_id"));
			String galleryTitle= multi.getParameter("galleryTitle");
		    String content = multi.getParameter("gallerycontent");
		    
		    // 변수에 저장한 값을 객체에 저장
		    GalleryDto gallery= new GalleryDto();
		    gallery.setMember_id(member_id);
		    gallery.setGallery_title(galleryTitle);
			gallery.setContent(content);
			
			// Dao에 있는 함수로 객체보내기
		    GalleryDao galleryDao =new GalleryDao();		   
		    
		    // 첨부 파일 처리
			Enumeration<String> filenames = multi.getFileNames();			
			
			String file = (String)filenames.nextElement();
			gallery.setGallery_file(multi.getFilesystemName(file).replaceAll("\\s,!,@,#,$,%,^,&,*,;,-",""));
		    gallery.setGallery_file_oriname(multi.getOriginalFileName(file));
			
		    int galleryIdx = galleryDao.GalleryWrite(gallery);			   		
							
		    forward.setRedirect(false);
		    if(galleryIdx>0){
		    	forward.setPath("/Gallery.board"); // 리스트 페이지로 넘어가기
		    }else{
		    	forward.setPath("/view/gallery/GalleryWrite.jsp");
		    }		    
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		return forward;
	}	
}