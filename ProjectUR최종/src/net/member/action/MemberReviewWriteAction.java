package net.member.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.member.db.MemberDAO;
import net.product.db.ReviewBean;

public class MemberReviewWriteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		ReviewBean rb = new ReviewBean();
		rb.setId(request.getParameter("id"));
		rb.setP_num(Integer.parseInt(request.getParameter("p_num")));
		rb.setR_content(request.getParameter("r_content"));
		rb.setR_star(Double.parseDouble(request.getParameter("r_star")));
		
		if(Double.parseDouble(request.getParameter("r_star"))==0){
		    response.setContentType("text/html; charset=UTF-8");
		    PrintWriter out = response.getWriter();
	    	out.println("<script>");
	    	out.println(" alert('별점을 입력해 주세요');");
	    	out.println(" history.go(-2); ");
	    	out.println("</script>");
	    	out.close();
	    	return null;
		}

		MemberDAO mdao = new MemberDAO();
		mdao.insertReview(rb);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./MemberReviewList.me");
		forward.setRedirect(true);
		
		return forward;
	}

}
