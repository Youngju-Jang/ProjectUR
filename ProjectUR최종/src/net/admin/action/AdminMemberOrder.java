package net.admin.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.AdminOrderDAO;

public class AdminMemberOrder implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		
		System.out.println("AdminMemberOrder");
		
		String id = request.getParameter("id");
		
		//객체 생성
		AdminOrderDAO adao = new AdminOrderDAO();
		
		//List
		List list = adao.getAdminOrderList(id);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(list==null) {
			out.print("<script>");
			out.print("  alert('주문 내역이 없습니다!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		request.setAttribute("list", list);
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./admin/adminmemberorder.jsp");
		return forward;
	}
}