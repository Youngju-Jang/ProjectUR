package net.member.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MemberController extends HttpServlet {
	

	protected void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String requestURI = request.getRequestURI();
		
		String contextPath = request.getContextPath();
		
		String command = requestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		
	    if(command.equals("/Join.me")) {
	    	
	    	forward = new ActionForward();
	    	forward.setPath("./member/join.jsp");
	    	forward.setRedirect(false);	    	
	    }                      
	    else if(command.equals("/MemberJoinAction.me")) {
	    	
	    	action = new MemberJoinAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(command.equals("/Login.me")) {
	    	
	    	forward = new ActionForward();
	    	forward.setPath("./member/login.jsp");
	    	forward.setRedirect(false);	    	
	    }else if(command.equals("/MemberLoginAction.me")) {
	    	
	    	action = new MemberLoginAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(command.equals("/Main.me")) {
	    	action = new MainAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(command.equals("/Logout.me")) {
	    	
	    	action = new MemberLogoutAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }else if(command.equals("/MemberInfo.me")) {
	    		    	
	    	action = new MemberInfoAction();
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(command.equals("/MemberUpdate.me")) {
	    	
	    	action = new MemberUpdate();
	    	
	    	try {
	    		forward = action.execute(request, response);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
	    	
	    }
	    else if(command.equals("/MemberUpdateAction.me")) {
	    	
	    	action = new MemberUpdateAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    else if(command.equals("/MemberDelete.me")) {
	    	
	    	forward = new ActionForward();
	    	forward.setPath("./member/memberDelete.jsp");
	    	forward.setRedirect(false);
	    	
	    }
	    else if(command.equals("/MemberDeleteAction.me")) {
	    	
	    	action =  new MemberDeleteAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	  
	    else if(command.equals("/passCheck.me")){
	    	forward = new ActionForward();
	    	forward.setPath("./member/passCheck.jsp");
	    	forward.setRedirect(false);
	    }
	    else if (command.equals("/passCheckAction.me")) {
			action = new passCheckAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		

	    else if(command.equals("/mail.me")){
	    	forward = new ActionForward();
	    	forward.setPath("./member/mail.jsp");
	    	forward.setRedirect(false);
	    }
	    
	    else if(command.equals("/MemberOrderList.me")) {
	    	action = new MemberOrderList();
	    	
	    	try {
				forward=action.execute(request, response);
				forward.setPath("./member/memberOrderList.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    else if(command.equals("/MemberQnaList.me")) {
	    	action = new MemberQnaList();
	    	
	    	try {
				forward=action.execute(request, response);
				forward.setPath("./member/memberQnaList.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}	    	
	    }
	    
	    else if(command.equals("/MemberReviewList.me")) {
	    	action = new MemberReviewList();
	    	
	    	try {
				forward=action.execute(request, response);
				forward.setPath("./member/memberReviewList.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}	 
	    }
	    
	    else if(command.equals("/MemberReviewWrite.me")) {
	    	action = new MemberReviewWrite();
	    	
	    	try {
				forward=action.execute(request, response);
				forward.setPath("./member/memberReviewWrite.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
	    }
	    
	    else if(command.equals("/MemberReviewWriteAction.me")) {
	    	action = new MemberReviewWriteAction();
	    	
	    	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	    	
	    }
	    
		else if (command.equals("/MemberOrderDetail.me")) {
			action = new MemberOrderDetailAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	    
	    
	    
		// 이동할 주소정보가 있는경우
		if(forward != null) {
			// 페이지 이동
			if(forward.isRedirect()) { // true
				response.sendRedirect(forward.getPath());
			}else {// false
				RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
				
				dis.forward(request, response);				
			}		
		}		
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);

	}

}
