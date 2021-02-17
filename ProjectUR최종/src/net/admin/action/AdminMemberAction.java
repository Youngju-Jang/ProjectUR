package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminOrderDAO;

public class AdminMemberAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		AdminOrderDAO adao = new AdminOrderDAO();
		
		List list = adao.getMemberList();
		
		request.setAttribute("list", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./admin/adminmemberlist.jsp");
		return forward;
	}
}