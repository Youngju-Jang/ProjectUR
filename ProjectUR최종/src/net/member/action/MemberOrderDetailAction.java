package net.member.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;
import net.order.db.OrderBean;
import net.order.db.OrderDAO;

public class MemberOrderDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {

		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		MemberDAO mdao = new MemberDAO();
		Vector totalList = mdao.getOrderDetail(b_num);
		
		request.setAttribute("orderList", totalList.get(0));
		request.setAttribute("productList", totalList.get(1));
		
		ActionForward forward = new ActionForward();
		forward.setPath("./member/memberOrderDetail.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
