package net.product.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.product.db.ProductBean;
import net.product.db.ProductDAO;

public class ProductListAction implements Action{
	
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		//정렬
		request.setCharacterEncoding("UTF-8");
		
		String odb = request.getParameter("odb");
		if(odb == null) {
			odb = "all";
		}
		
		//검색어
		String search = request.getParameter("search");
		if(search == null) {
			search = "";
		}
		System.out.println("search : " + search);
		
		//head에 버튼별로 category 지정 다 되어있음
		int category = Integer.parseInt(request.getParameter("category"));
		
		
		ProductDAO pdao = new ProductDAO();
		
		//총 제품 수량
		int count = pdao.getProductListCount(category, search);
		System.out.println("M : 총 " + count +"개");
		
		
		//---------------------------------------------------
		//---------------------------------------------------
		//페이징처리 -------------------------------------------
		//현 페이지 위치
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) {
			pageNum = "1";
		}
		
		//한페이지에 보여줄 글 갯수
				int pageSize = 6;//6
				
				//시작행 계산
				int currentPage = Integer.parseInt(pageNum);
				int startPage = (currentPage -1) * pageSize +1;
				if(count == 0) {
					startPage = 0;
				}
				//끝행 계산
				
		
		
		ArrayList<ProductBean> productList = pdao.getProductList(odb, startPage, pageSize, category, search);
		request.setAttribute("productList", productList);
		System.out.println("productList size : " + productList.size());
		
		
		//---------------------------------------------------
		//---------------------------------------------------
		//페이징처리 -------------------------------------------
		//하단부
		
		int pageCount = count / pageSize + (count % pageSize == 0? 0:1) ;
		
		//한 화면에 보여질 페이지 블럭수
		int pageBlock = 5;//5
		
		//블럭 시작페이지번호
		int startBlock = ((currentPage -1)/pageBlock)* pageBlock + 1 ;
		//블럭 끝페이지
		int endBlock = startBlock + pageBlock -1;
		if(endBlock > pageCount) {
			endBlock = pageCount;
		}
		
		//페이징처리-------------------------
		request.setAttribute("pageNum", pageNum);
		
		request.setAttribute("startPage", startPage);
		request.setAttribute("count", count); //총 글의 수
		request.setAttribute("pageCount", pageCount); //총 페이지 수
		request.setAttribute("pageBlock", pageBlock); //페이지 블럭의 수
		request.setAttribute("startBlock", startBlock); //블럭 시작페이지
		request.setAttribute("endBlock", endBlock); //블럭 끝페이지
		request.setAttribute("odb", odb); //정렬
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("search", search);
		
		
		
		ActionForward forward = new ActionForward();
		forward.setPath("./product/ProductList.jsp");
		forward.setRedirect(false);
		
		return forward;
		
	}
}
