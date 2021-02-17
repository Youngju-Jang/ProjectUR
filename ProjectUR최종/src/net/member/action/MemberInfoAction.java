package net.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;


public class MemberInfoAction implements Action {

 
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		
		// 로그인체크(세션)
		HttpSession session = request.getSession();
		
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if( id == null ) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// MemberDAO 객체 생성
		MemberDAO mdao = new MemberDAO();
		
		// 회원 정보 조회하는 메서드 getMember(id);
		MemberBean mb = mdao.getMember(id);
		
		request.setAttribute("MemberBean", mb);
		
		
		// 페이지 이동 	
		forward.setPath("./member/memberInfo.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
