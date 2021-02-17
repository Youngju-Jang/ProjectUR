package net.member.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;
import net.order.db.OrderBean;

public class MemberReviewList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		MemberDAO mdao = new MemberDAO();
		ArrayList<Map<String, Object>> list = mdao.getMemberOrderDetail(id);
		int count = mdao.getMemberOrderCount(id);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		ActionForward forward = new ActionForward();
		forward.setPath("./member/memberReviewList.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
