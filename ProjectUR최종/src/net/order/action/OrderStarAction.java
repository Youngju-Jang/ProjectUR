package net.order.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cart.db.CartBean;
import net.cart.db.CartDAO;
import net.member.db.MemberDAO;
import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class OrderStarAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("M : OrderStarAction의 execute() 호출");
		// 로그인 정보 (로그인 처리필요)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		int p_num = Integer.parseInt(request.getParameter("p_num")==null ? "0":request.getParameter("p_num"));
		int c_p_count = Integer.parseInt(request.getParameter("c_p_count")==null ? "0": request.getParameter("c_p_count"));
		
		ArrayList<CartBean> cartList = new ArrayList<CartBean>();
		
		if(p_num > 0) { //바로구매 상품
			CartBean cb = new CartBean();
			cb.setP_num(p_num);
			cb.setP_count(c_p_count);
			cb.setIs_direct(true);
			ProductDAO dao = new ProductDAO();
			ProductBean directPb = dao.getProduct(p_num);
			directPb.setP_num(p_num);
			directPb.setP_count(c_p_count);
			cb.setProducts(directPb);
			cartList.add(cb);
			
		} else  { //장바구니 상품
			//장바구니정보 조회
			CartDAO cdao = new CartDAO();
			cartList = cdao.getCartList(id);
		}
		//구매회원정보 저장 
		MemberDAO mdao = new MemberDAO();
		//MemberBean mb = mdao.getMember(id); 한 줄에 처리
		request.setAttribute("memberBean", mdao.getMember(id));
		request.setAttribute("cartList", cartList);
		//페이지이동
		forward.setPath("./product/product_buy.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
	
	