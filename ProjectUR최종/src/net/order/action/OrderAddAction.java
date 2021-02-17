package net.order.action;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.cart.db.CartBean;
import net.cart.db.CartDAO;
import net.order.db.OrderDAO;
import net.order.db.OrderBean;
import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class OrderAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) 
			throws Exception {
		System.out.println("M : OrderAddAction의 execute() 호출");
		// 로그인 정보 (로그인 처리필요)
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		ActionForward forward = new ActionForward();
		if(id == null){
			forward.setPath("./Login.me");
			forward.setRedirect(true);
			return forward;
		}
		
		// 전달된 정보를 저장
		// 한글처리
		// OrderBean 객체 생성 -> 정보 저장
		OrderBean ob =  new OrderBean();
		ob.setId(id);
		ob.setO_name(request.getParameter(("o_name")));
		ob.setO_phone(request.getParameter(("o_phone")));
		ob.setO_email(request.getParameter(("o_email")));
		//ob.setD_result(Integer.parseInt((String)request.getParameter(("d_result"))));
		ob.setD_name(request.getParameter(("d_name")));
		ob.setD_phone(request.getParameter(("d_phone")));
		ob.setD_postcode(Integer.parseInt((String)request.getParameter(("d_postcode"))));
		ob.setD_address(request.getParameter(("d_address")));
		ob.setD_address2(request.getParameter(("d_address2")));	
		ob.setD_message(request.getParameter(("d_message")));
		ob.setPayment(Integer.parseInt((String)request.getParameter(("payment"))));
		
		CartDAO cdao = new CartDAO();
		ArrayList<CartBean> cartList = null;
		
		// 결재 모듈(카카오,아임포트,U+) 추가영역
		System.out.println("M : 결제모듈 성공");
		int p_num = Integer.parseInt(request.getParameter("p_num")==null ? "0":request.getParameter("p_num"));
		int c_p_count = Integer.parseInt(request.getParameter("p_count")==null ? "0": request.getParameter("p_count"));
		
		if(p_num > 0) { //바로구매 상품
			cartList = new ArrayList<CartBean>();
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
		} else {
			cartList = cdao.getCartList(id);
		}
		
		// OrderDAO 객체 생성 - addOrder(주문정보,장바구니정보,상품정보)
		OrderDAO odao = new OrderDAO();
		odao.addOrder(ob, cartList);
		System.out.println("M : team1_order 테이블에 저장완료");
		
		//구매확정 메일이나 문자 , 카톡메세지 등을 유저에게 보내는 추가영역
		System.out.println("M : 구매확정 내역 전송 완료");
		
		//상품정보 수정 - 구매 수량만큼 구매갯수 차감
		for(int i=0; i<cartList.size(); i++){
			CartBean cb = cartList.get(i);
		
			ProductDAO pdao = new ProductDAO(); 
			pdao.updateProductCnt(cb.getP_num(), cb.getP_count());
		}
		
		if(p_num == 0) { //장바구니 상품
		//장바구니 비우기(삭제)
			cdao.deleteCart(id);
		}
		
		//페이지이동
		forward.setPath("./MemberOrderList.me"); //개인 정보의 주문내역 페이지로 이동
		forward.setRedirect(true);
		return forward;
	}

}