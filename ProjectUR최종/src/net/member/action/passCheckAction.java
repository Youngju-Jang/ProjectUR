package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class passCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 로그인 세션제어
		HttpSession session = request.getSession();
		String id =(String) session.getAttribute("id");
				
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.passCheck(id, pass);
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		
		if(result == 0) {
			out.print("<script>");
			out.print("  alert('비밀번호 오류!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}else if(result  == -1) {
			out.print("<script>");
			out.print("  alert('비회원 입니다!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		out.print("<script>");
		out.print("  alert('비밀번호 확인되었습니다'); ");
		out.print("  location.href='./MemberUpdate.me'; ");
		out.print("</script>");
		out.close();
		
		return null;	
	
	}
}