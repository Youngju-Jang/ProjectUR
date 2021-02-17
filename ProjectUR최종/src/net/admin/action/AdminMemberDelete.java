package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.admin.db.AdminOrderDAO;
import net.member.db.MemberDAO;

public class AdminMemberDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("AdminMemberDelete");

		String id = request.getParameter("id");
		
		AdminOrderDAO adao = new AdminOrderDAO();
		
		adao.deleteMember(id);
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./AdminMember.ao");
		forward.setRedirect(true);
				
		return forward;
	}

}
