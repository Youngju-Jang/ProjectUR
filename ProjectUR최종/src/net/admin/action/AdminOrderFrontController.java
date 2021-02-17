package net.admin.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOrderFrontController extends HttpServlet{

	protected void doProcess(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		
		Action action = null;
		ActionForward forward = null;
		if(command.equals("/AdminOrderList.ao")) {
			//AdminOrderListAction 
			action = new AdminOrderList();
			try {
				forward=action.execute(request, response);
				forward.setPath("./admin/adminorderlist.jsp");
				forward.setRedirect(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminOrderDetail.ao")){
			//  AdminOrderDetailAction
			action=new AdminOrderDetailAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if (command.equals("/AdminOrderModifyAction.ao")) {
			//AdminOrderModifyAction
			action = new AdminOrderModifyAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminOrderDeleteAction.ao")){
			action = new AdminOrderDeleteAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminMenu.ao")){
			forward = new ActionForward();
			forward.setPath("./admin/adminmenu.jsp");
			forward.setRedirect(false);
		}else if(command.equals("/AdminMember.ao")) {
			action = new AdminMemberAction();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminMemberOrder.ao")) {
			action = new AdminMemberOrder();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminMemberDelete.ao")) {
			action = new AdminMemberDelete();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/AdminQnaList.ao")) {
			action = new AdminQnaList();
			
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dis = 
						request.getRequestDispatcher(forward.getPath());
				dis.forward(request, response);
			}
		}
	}
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}