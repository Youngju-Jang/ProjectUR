package net.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.ProductDAO;
import net.product.db.ProductBean;

public class ProductModify implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("ProductModify");
		
		int p_num = (int)Integer.parseInt(request.getParameter("p_num"));
		System.out.println(p_num);
		
		ProductDAO pdao = new ProductDAO();
		ProductBean pb = pdao.getProduct(p_num);
		System.out.println(pb);
		
		request.setAttribute("pb", pb);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./admin/productmodify.jsp");
		forward.setRedirect(false);
		System.out.println("페이지 이동");
		
		return forward;
	}
}