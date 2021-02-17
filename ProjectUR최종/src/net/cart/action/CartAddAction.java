package net.cart.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cart.db.CartBean;
import net.cart.db.CartDAO;

public class CartAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("M : CartAddAction의 execute() 호출");
		// 로그인 정보 (로그인 처리필요)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		// 장바구니 저장
		// CartBean / CartDAO 객체 	
		// 장바구니 객체 생성
		// 파라미터값 저장(상품번호,구매수량) + ID
		CartBean cb = new CartBean();
		cb.setP_num(Integer.parseInt(request.getParameter("p_num")));
		cb.setP_count(Integer.parseInt(request.getParameter("c_p_count")));
		cb.setId(id);
		System.out.println("장바구니 객체정보: "+cb);
		
		// CartDAO 객체 생성
		CartDAO cdao = new CartDAO();
		//해당상품(+옵션까지)이 장바구니에 동일한게 있는지 체크
		int result = cdao.checkProduct(cb);
		
		// CartAdd() - 추가
		if(result != 1){ //장바구니에 해당상품이 없다
			cdao.cartAdd(cb);
		}
		//back=0 : cartList로 이동, back =1 : 쇼핑계속하기 -> product.jsp로 다시이동
		int back = Integer.parseInt(request.getParameter("back"));
		if(back == 1) {
			
			//product.jsp로 다시이동
			forward.setPath("./Product.p?p_num="+request.getParameter("p_num"));
			forward.setRedirect(true);
			return forward;
			
		}else if(back == -1) { //구매 바로가기
			
			forward.setPath("./OrderStar.or");
			forward.setRedirect(true);
			return forward;
			
		}
			//장바구니list로 이동
			forward.setPath("./CartList.ba");
			forward.setRedirect(true);
			return forward;
			
		
		
	}
}
