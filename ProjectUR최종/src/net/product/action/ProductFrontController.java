package net.product.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductFrontController extends HttpServlet{	
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException{
		
		System.out.println("C: doProcess() 호출 - ProductFrontController");
		/////////////////////////////////////////////////////////////////////////////	
		////////////////////////1. 가상주소 계산////////////////////////////////////////
		System.out.println("\n\n 1. 가상주소 계산");
		String requestURI = request.getRequestURI();
		System.out.println("requestURI : " + requestURI);
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath : " + contextPath);
		
		String command = requestURI.substring(contextPath.length());
		System.out.println("command : " + command);
		System.out.println(" 1. 가상주소 계산\n\n");
		
		////////////////////////1. 가상주소 계산////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////	
		/////////////////////////////////////////////////////////////////////////////	
		/////////////////////////2.가상주소 처리(매핑)///////////////////////////////////
		System.out.println("\n\n 2. 가상주소 처리(매핑)");
		//모델객체 처리를위한 객체
		Action action = null;
		
		//이동정보 처리객체
		ActionForward forward = null;
		
		if(command.equals("/ProductList.p")) {
			System.out.println("C: /ProductList.p 페이지 호출!");
			System.out.println("C: DB사용(ProductList 가져오기) -> 페이지 이동 ");
			action = new ProductListAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/Product.p")) {
			System.out.println("C: /Prouct.p호출 ->p_num 넘겨 DB가져오기->페이지이동");
			
			action = new ProductDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}else if(command.equals("/ProductCartTest.p")) {
			System.out.println("C: /ProductCartTest.p 호출");
			forward = new ActionForward();
			forward.setPath("./product/carttest.jsp");
			forward.setRedirect(false);
			
			
			//제품디테일 페이지에서 QNA작성
		}else if(command.equals("/ProductQnaWriteAction.p")) {
			System.out.println("C: /ProductQnaWriteAction.p 호출");
			
			action = new ProductQnaWriteAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}else if(command.equals("/ProductQnaReview.p")) {
			System.out.println("/ProductQnaReview.p 호출");
			System.out.println("db 업데이트 리뷰로 이동");
			
			action = new ProductQnaReviewAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		
		
		
		
		
		
		
		System.out.println("2. 가상주소 처리 \n\n ");
		
		/////////////////////////2.가상주소 처리(매핑)///////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////	
		/////////////////////////////////////////////////////////////////////////////	
		//////////////////////////3.페이지 이동/////////////////////////////////////////
		System.out.println("\n\n 3. 페이지이동");
		
		if(forward != null){
			if(forward.isRedirect()) {//true. sendRedirect
				System.out.println("C: 페이지이동(sendRedirect) ");
				response.sendRedirect(forward.getPath());
				
			}else {//forward
				System.out.println("C: 페이지이동(forward) ");
				
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
		
		System.out.println("3. 페이지이동\n\n ");
		//////////////////////////3.페이지 이동/////////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////	
	
	}
	
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("C: doGet() 호출 - ProductFrontController");
		doProcess(request, response);
	
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("C: doPost() 호출 - ProductFrontController");
		doProcess(request, response);
	
	}
	
	
}
