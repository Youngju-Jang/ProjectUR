package net.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cart.db.CartDAO;

public class CartDeleteAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("M : CartDeleteAction의 execute() 호출");
		// 로그인체크(세션)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");

		CartDAO cdao = new CartDAO();
		cdao.deleteCart(id);
		System.out.println("M: 장바구니에서 삭제 완료");
		
		//장바구니list로 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./CartList.ba");
		forward.setRedirect(true);
		return forward;
	}
}