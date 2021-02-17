package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 세션제어
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			
			return forward;			
		}
		
		MemberDAO mdao = new MemberDAO();
		
		MemberBean mb = mdao.getMember(id);
		// 정보 저장 (request 객체)
		request.setAttribute("mb", mb);
		
	    forward.setPath("./member/memberUpdate.jsp");
	    forward.setRedirect(false);		
		
		return forward;
	}

}
