package net.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.product.db.ProductQnaBean;
import net.product.db.ProductQnaDAO;


public class ProductQnaWriteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		//id값 저장
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("id");
		
				
				
		System.out.println("id : " + id);
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		//파라미터값 저장
		ProductQnaBean pqb = new ProductQnaBean();
		//id null일경우 설정필요
		pqb.setId(id);
		pqb.setP_num(p_num);
		pqb.setSubject(request.getParameter("Qsubject"));
		pqb.setContent(request.getParameter("Qcontent"));
		
		ProductQnaDAO pqdao = new ProductQnaDAO();
		pqdao.insertQna(pqb);
		System.out.println("M: QnA insert 완료!");

		ActionForward forward = new ActionForward();
		forward.setPath("./Product.p?p_num="+p_num);
		forward.setRedirect(true);
		
		return forward;
	}
}
