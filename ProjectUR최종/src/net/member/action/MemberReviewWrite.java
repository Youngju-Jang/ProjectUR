package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement.ParseInfo;

import net.member.db.MemberDAO;

public class MemberReviewWrite implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");		
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		int b_num = Integer.parseInt(request.getParameter("b_num"));
		request.setCharacterEncoding("utf-8");
		
		MemberDAO mdao = new MemberDAO();
		int result = mdao.getCheckReview(id, b_num, p_num);
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		/*
		if(result == 0) {
			out.println("<script>");
			out.println("alert('리뷰를 작성할 수 없는 상품입니다!');");
			out.println("history.back();");
			out.println("</script>");
			out.close();
			
			return null;
		}
		*/
		request.setAttribute("p_num", p_num);
		request.setAttribute("id", id);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./memberReviewWrite.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
