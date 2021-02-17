package net.admin.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminOrderDAO;

public class AdminOrderDetailAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		System.out.println("AdminOrderDetailAction");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		int p_num = Integer.parseInt(request.getParameter("p_num"));

		
		AdminOrderDAO adao = new AdminOrderDAO();
		
		List list = adao.getAdminOrderDetail(b_num, p_num);
		
		request.setAttribute("list", list);
		
		ActionForward forward = new ActionForward();
		
		forward.setPath("./admin/adminordermodify.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
}