package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberQnaList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		MemberDAO mdao = new MemberDAO();		
		List list = mdao.getMemberQnaList(id);
		int count = mdao.getMemberQnaCount(id);
		
		request.setAttribute("list", list);
		request.setAttribute("count", count);
		ActionForward forward = new ActionForward();
		forward.setPath("./member/memberQnaList.jsp");		
		forward.setRedirect(false);
		
		return forward;
	}

}
