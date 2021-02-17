package net.admin.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.admin.db.ProductDAO;

public class ProductDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("ProductDeleteAction");
		
		
		int p_num = Integer.parseInt(request.getParameter("p_num"));
		
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(p_num > 0) {
			out.print("<script>");
			out.print("  alert('삭제 되었습니다'); ");
			out.print("  location.href=\'./ProductList.ap'; ");
			out.print("</script>");
			out.close();
		}
		
		System.out.println(p_num);
		ProductDAO pdao = new ProductDAO();
		pdao.deleteProduct(p_num);
		
		ActionForward forward = new ActionForward();
		forward.setPath("./ProductList.ap");
		forward.setRedirect(true);
		return forward;
		
		
		//페이지 이동
		
	}
}
