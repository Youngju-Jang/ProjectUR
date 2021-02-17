package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.member.db.MemberBean;
import net.member.db.MemberDAO;

public class MemberJoinAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       
        // 한글처리 
        request.setCharacterEncoding("UTF-8");
        
        MemberBean mb = new MemberBean();
        
        mb.setId(request.getParameter("id"));
        mb.setPass(request.getParameter("pass"));
        mb.setEmail(request.getParameter("email"));
        mb.setPostcode(Integer.parseInt(request.getParameter("postcode")));
        mb.setAddress1(request.getParameter("address1"));
        mb.setAddress2(request.getParameter("address2"));
          
        // DB 처리 (DAO 객체 생성)
        MemberDAO mdao = new MemberDAO();  
	    String id = request.getParameter("id");
	    String email = request.getParameter("email");
	    boolean checkID = mdao.checkID(id);
	    boolean checkEmail = mdao.checkEmail(email);
	    
	    response.setContentType("text/html; charset=UTF-8");
	    PrintWriter out = response.getWriter();

	    if(checkID) { //아이디 중복
	    	out.println("<script>");
	    	out.println(" alert('회원가입 실패! 이미 사용중인 아이디입니다');");
	    	out.println(" history.back(); ");
	    	out.println("</script>");
	    	out.close();
	    	return null;
	    }else if(checkEmail){ //이메일 중복
	    	out.println("<script>");
	    	out.println(" alert('회원가입 실패! 이미 사용중인 이메일입니다');");
	    	out.println(" history.back(); ");
	    	out.println("</script>");
	    	out.close();
	    	return null;
		}
	    
        // 회원가입 메서드 구현 
        mdao.insertMember(mb); 
        
        ActionForward forward = new ActionForward();
        forward.setPath("./Main.me");
        forward.setRedirect(true);	
        return forward;
	}
}
