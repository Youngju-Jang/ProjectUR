package net.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cart.db.CartBean;
import net.cart.db.CartDAO;

public class CartDeletePartAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("M : CartDeletePartAction의 execute() 호출");
		//전달정보 저장
		// 로그인체크(세션)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		int c_num = Integer.parseInt(request.getParameter("c_num"));
System.out.println("c_num:" + c_num);		
		CartBean cb = new CartBean();
		cb.setId(id);
		cb.setC_num(c_num);
		
		CartDAO cdao = new CartDAO();
		cdao.deleteCartPart(cb);
		System.out.println("M: 장바구니에서 상품 삭제 완료");
		
		//장바구니list로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./CartList.ba");
		forward.setRedirect(true);
		return forward;
	}
}