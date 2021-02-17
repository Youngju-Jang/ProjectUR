package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

		// 로그인 세션처리
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		
		ActionForward forward = new ActionForward();
		if(id == null) {
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 한글처리 
		request.setCharacterEncoding("UTF-8");
		
		// 이전페이지에서 전달된 정보를 모두 저장 : 파라미터-> 자바빈객체
		MemberBean UIMB = new MemberBean();
		
		UIMB.setId(id);
		UIMB.setPass(request.getParameter("pass"));
		UIMB.setEmail(request.getParameter("email"));
		UIMB.setPostcode(Integer.parseInt(request.getParameter("postcode")));
		UIMB.setAddress1(request.getParameter("address1"));
		UIMB.setAddress2(request.getParameter("address2"));
			
		
		// 해당 자바빈객체를 가지고 DB이동 -> DAO 객체 생성 -> updateMember(mb)
		MemberDAO mdao = new MemberDAO();
		int check = mdao.updateMember(UIMB);
		
		
		// 처리결과를 리턴받아서 페이지 이동 (자바스크립트 사용 이동)
		// 수정성공 - 1
		// 비밀번호 오류 - 0
		// 아이디가 없음 - -1
		
		// 페이지응답 정보를 설정
		response.setContentType("text/html; charset=UTF-8");
		
		// 응답 정보를 출력하는 통로(IO 스트림)
		PrintWriter out = response.getWriter();
		
		if(check == 0) {
			out.print("<script>");
			out.print(" alert('비밀번호 오류!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null; 
		}else if(check == -1) {
			out.print("<script>");
			out.print(" alert('아이디가 없습니다!'); ");
			out.print(" history.back(); ");
			out.print("</script>");
			out.close();
			
			return null;
		}
		
		// check = 1
		out.print("<script>");
		out.print(" alert('수정되었습니다!'); ");
		out.print(" location.href='./MemberUpdate.me'; ");
		out.print("</script>");
		out.close();
		
		return null;
	}


}
