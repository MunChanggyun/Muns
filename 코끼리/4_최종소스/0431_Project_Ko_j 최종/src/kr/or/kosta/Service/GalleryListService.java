package kr.or.kosta.Service;

import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.GalleryDao;
import kr.or.kosta.Dto.GalleryDto;
import kr.or.kosta.Dto.GalleryFileDto;


public class GalleryListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		ArrayList<GalleryDto> gallerylist = null;
		
		 GalleryDao gallerydao = new GalleryDao();
		int pagecount = 0;
		try{
			forward = new ActionForward();
			
			String psStr = request.getParameter("ps");
			String cpStr = request.getParameter("cp");
			
			if(psStr == null || psStr.trim().equals("")){
	            psStr = "6"; 	   // default 5건씩 
	        }
	        
	        if(cpStr == null || cpStr.trim().equals("")){
	            cpStr= "1";        // default 1 page
	        }
			
	        int pagesize = Integer.parseInt(psStr);  //5
	        int cpage = Integer.parseInt(cpStr);     //1 
	        
	        GalleryDto gallerydto = new GalleryDto();
	        
	        int totalgallerycount = gallerydao.AllboardCount();
	        
	        if(totalgallerycount % pagesize==0){   // 전체 건수 , page size
	            pagecount = totalgallerycount/pagesize;
	        }else{
	            pagecount = (totalgallerycount/pagesize) + 1;
	        }
	        gallerylist = gallerydao.AllGalleryList(cpage, pagesize);      

	        request.setAttribute("cp", cpage);
	        request.setAttribute("pagesize", pagesize);
	        request.setAttribute("totalgallerycount", totalgallerycount);
	        request.setAttribute("pagecount", pagecount);
	        request.setAttribute("gallerylist", gallerylist);
	        
	       
	       
	        forward.setPath("/view/gallery/gallery_list.jsp");
	        forward.setRedirect(false);
	        
	        
		}catch(Exception e){
			
		}
		
		return forward;
	}

}
