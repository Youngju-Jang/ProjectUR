package net.cart.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CartFrontController extends HttpServlet {
	// 일반 클래스를 서블릿을 상속해서 컨트롤러 역활 할수있도록 설정
	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("doProcess() 호출 (페이지 GET/POST방식 모두 사용호출)");
		// 주소 비교 (주소 매핑)
		System.out.println("--------------@ 주소 계산 @-------------");
		// 프로젝트명 + 주소
		String requestURI = request.getRequestURI();
		System.out.println(" requestURI : " + requestURI);
		// 프로젝트명
		String contextPath = request.getContextPath();
		System.out.println(" contextPath : " + contextPath);
		// 가상주소
		String command = requestURI.substring(contextPath.length());
		System.out.println(" command(가상주소) : " + command);
		
		
		System.out.println("--------------@ 주소 비교후 처리 @-------------");
		Action action = null;
		ActionForward forward = null;
		// 주소에 따른 처리 구분 (주소 매핑후 이동)
		
		// 주소에 따른 처리 구분 (주소 매핑후 이동)
		if(command.equals("/CartAdd.ba")){
			//장바구니 추가 : .jsp -> DB
			System.out.println("C: /CartAdd.ba 호출");
			//.ag -> .jsp로 이동
			//컨트롤러 -> 뷰페이지 이동
			action = new CartAddAction();
			try { forward = action.execute(request, response);
			} catch (Exception e) { e.printStackTrace(); }
		
		
		//장바구니 리스트
		}else if(command.equals("/CartList.ba")){
			System.out.println("C: /CartList.ba 호출");
			//DB - > JSP페이지출력
			action = new CartListAction();
			try { forward = action.execute(request, response);
			} catch (Exception e) { e.printStackTrace(); }
		//장바구니에서 상품 삭제
		}else if(command.equals("/CartDelete.ba")){
			System.out.println("C: /CartDelete.ba 호출");
			//.jsp -> Action_DAO_DB ->.ag로 이동
			action = new CartDeleteAction();
			try { 
				forward = action.execute(request, response);
			} catch (Exception e) { e.printStackTrace(); }
			
		}else if(command.equals("/CartDeletePart.ba")){
			System.out.println("C: /CartDeletePart.ba 호출");
			//.jsp -> Action_DAO_DB ->.ag로 이동

			action = new CartDeletePartAction();
			try { 
				forward = action.execute(request, response);
			} catch (Exception e) { e.printStackTrace(); }

		}

		
		System.out.println("-----------------@ 페이지 이동 @--------------");
		if(forward != null){ // 이동할 정보가 있다
			if(forward.isRedirect()){ // true - sendRedirect()
				// 가상주소(.bo -> .bo), 화면전환(주소변경,화면 변경)
				System.out.println("C : "+forward.getPath()+"주소로 이동(Redirect)");
				response.sendRedirect(forward.getPath());
			}else{ // false - forward()
				System.out.println("C : "+forward.getPath()+"주소로 이동(forward)");
				// 가상주소 -> 실제페이지 (.bo -> .jsp) + reqeust 객체 정보를 가지고 이동
				RequestDispatcher dis =
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);			
			}
		}//end of 페이지이동
		
	}//end of doProcess
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("doGet() 호출 (페이지 GET방식 호출)");
		doProcess(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("doPost() 호출 (페이지 POST방식 호출)");
		doProcess(req, resp);
	}
}