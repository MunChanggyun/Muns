package kr.or.kosta.Service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.kosta.Action.Action;
import kr.or.kosta.Action.ActionForward;
import kr.or.kosta.Dao.MemberDao;
import kr.or.kosta.Dto.MemberDto;

public class MemberListService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
			ActionForward forward =null;
			ArrayList<MemberDto> memberlist = null;
			try{
				
				MemberDao dao = new MemberDao();
				memberlist = dao.MemberAllList();
				forward = new ActionForward();
				if(memberlist!=null){
					request.setAttribute("memberlist", memberlist);
					forward.setPath("view/manager/member_list_edit.jsp");
					forward.setRedirect(false);
					
				}else{
					forward.setPath("view/homepage.jsp");
					forward.setRedirect(false);
				}				
			}catch(Exception e){			
				
			}
		return forward;
	}

}
