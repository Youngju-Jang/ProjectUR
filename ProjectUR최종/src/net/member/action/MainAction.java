package net.member.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductDAO;

public class MainAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("UTF-8");
		
		ProductDAO pdao = new ProductDAO();
		int startRow = 0;
		int pageSize = 4;
		List mainList = pdao.getProductList(startRow, pageSize);
		request.setAttribute("mainList", mainList);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./main.jsp");
		forward.setRedirect(false);
		
		return forward;
	}

}
