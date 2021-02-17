package net.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductQnaBean;
import net.product.db.ProductQnaDAO;

public class ProductQnaReviewAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		System.out.println("ProductQnaReviewAction_execute 실행");
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		ProductQnaBean pqb = new ProductQnaBean();
		pqb.setReply(request.getParameter("reply"));
		pqb.setId(request.getParameter("id"));
		pqb.setQ_num(Integer.parseInt(request.getParameter("q_num")));
		System.out.println(pqb.toString());
		
		//p_num, reply, id, q_num 넘김
		ProductQnaDAO pqdao = new ProductQnaDAO();
		pqdao.replyQnaList(pqb, p_num);
		
		System.out.println("qna 답글 업데이트 완료!");
		
		ActionForward forward = new ActionForward();
		forward.setPath("./Product.p?p_num="+p_num);
		forward.setRedirect(true);
		return forward;
	}
}
