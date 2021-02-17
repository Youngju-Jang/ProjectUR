package net.admin.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminOrderDAO;

public class AdminOrderList implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		
		//객체 생성
		AdminOrderDAO adao = new AdminOrderDAO();
		
		//List
		List list = adao.getAdminOrderList();
		
		request.setAttribute("list", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./admin/adminorderlist.jsp");
		return forward;
		
	}

}