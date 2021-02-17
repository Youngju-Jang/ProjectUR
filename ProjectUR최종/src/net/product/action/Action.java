package net.product.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Action {
	//인터페이스임
	
	//추상메서드 -> 인터페이스 구현시 반드시 사용해야 한다.(오버라이딩)
	//-> 메서드 강제사용하게 한다. (모든 동작 동일하게 처리)
	
	//메서드 실행후 페이지 이동정보를 리턴하는 메서드
	//->페이지 요청정보(request), 페이지응답정보(response)를 가지고 있는 메서드
	public ActionForward execute(HttpServletRequest request, 
					HttpServletResponse response) throws Exception;
	
}
