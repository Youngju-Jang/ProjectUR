package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberDeleteAction implements Action {

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
		
		// 전달정보 저장 (id,pass)
		String pass = request.getParameter("pass");
		
		MemberDAO mdao = new MemberDAO();
		int check = mdao.deleteMember(id,pass);
		
		// 리턴 결과에 따른 페이지 이동(자바 스크립트)
		// check =1(삭제성공), check=0(비밀번호 오류), check =-1(비회원)
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			out.print("<script>");
			out.print("  alert('비밀번호 오류!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}else if(check  == -1) {
			out.print("<script>");
			out.print("  alert('비회원 입니다!'); ");
			out.print("  history.back(); ");
			out.print("</script>");
			out.close();
			return null;
		}
		
		// 회원의 로그인정보 제거
		session.invalidate();
		
		// 삭제 성공
		out.print("<script>");
		out.print("  alert('회원 탈퇴되었습니다'); ");
		out.print("  location.href='./Main.me'; ");
		out.print("</script>");
		out.close();
		
		return null;
	}

}
