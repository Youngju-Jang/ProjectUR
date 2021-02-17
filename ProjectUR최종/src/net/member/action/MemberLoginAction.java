package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberDAO;

public class MemberLoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception {
		
	    String id = request.getParameter("id");
	    String pass = request.getParameter("pass");
	    
		// DAO 객체 생성 - 로그인 체크 메서드()
	    MemberDAO mdao = new MemberDAO();
	    int check = mdao.loginCheck(id,pass);
	    
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();
	    
//	    if(check == 0) { //비밀번호 오류
//	    	out.println("<script>");
//	    	out.println(" alert('비밀번호 오류!');");
//	    	out.println(" history.back(); ");
//	    	out.println("</script>");
//	    	out.close();
//	    	
//	    	return null; // 메서드 종료 -> 컨트롤러 이동X
	    if(check == 0) { //사용 불가 계정
	    	out.println("<script>");
	    	out.println(" alert('사용할 수 없는 계정입니다');");
	    	out.println(" history.back(); ");
	    	out.println("</script>");
	    	out.close();
	    		
	    	return null; // 메서드 종료 -> 컨트롤러 이동X
	    }else if(check == -1){
	    	out.println("<script>");
//	    	out.println(" alert('비회원 입니다!');");
	    	out.println(" alert('아이디와 비밀번호가 일치하지 않습니다');");
	    	out.println(" history.back(); ");
	    	out.println("</script>");
	    	out.close();
	    	
	    	return null; // 메서드 종료 -> 컨트롤러 이동X
		}
//	    else if(check == 2){
//			out.println("<script>");
//			out.println(" alert('현재 삭제 진행중인 계정입니다!');");
//			out.println(" history.back(); ");
//			out.println("</script>");
//			out.close();
//			
//			return null; // 메서드 종료 -> 컨트롤러 이동X
//		}
	    
	    // check == 1 (로그인성공)
	    
	    HttpSession session = request.getSession();
	    session.setAttribute("id", id);
	    
	    // 메인 페이지로 이동
	    // 페이지 이동 - ActionForward 객체 
//	    ActionForward forward = new ActionForward();
//	    forward.setPath("./Main.me");
//	    forward.setRedirect(true);

    	out.println("<script>");
    	out.println("alert('로그인 되었습니다');");
    	out.println(" history.go(-2); ");
//    	out.println(" location.href=document.referrer; ");
    	out.println("location.reload();");
    	out.println("</script>");
    	out.close();
    	return null; // 메서드 종료 -> 컨트롤러 이동X
	}
}
