package net.admin.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.admin.db.ProductDAO;
import net.product.db.ProductBean;

public class ProductModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, 
			HttpServletResponse response) throws Exception {
		System.out.println("ProductModifyAction");
		request.setCharacterEncoding("UTF-8");
		
		ServletContext context = request.getServletContext();
		String realPath = context.getRealPath("/upload");
		int maxSize=5*1024*1024;
		
		try {
		MultipartRequest multi = new MultipartRequest(request,realPath,maxSize,"utf-8",new DefaultFileRenamePolicy());
		
		System.out.println("여기까지 이상 없음");
		ProductBean pb = new ProductBean();
		System.out.println("여기까지 이상 없음");
		System.out.println(pb);
		
		pb.setP_num(Integer.parseInt(multi.getParameter("p_num")));
		pb.setP_count(Integer.parseInt(multi.getParameter("p_count")));
		pb.setCategory(Integer.parseInt(multi.getParameter("category")));
		pb.setP_price(Integer.parseInt(multi.getParameter("p_price")));
		pb.setP_saleprice(Integer.parseInt(multi.getParameter("p_saleprice")));
		String image1 = multi.getFilesystemName("img_main");
		String image2 = multi.getFilesystemName("file1")+","
				+multi.getFilesystemName("file2")+","
				+multi.getFilesystemName("file3")+","
				+multi.getFilesystemName("file4");
		pb.setImg_main(image1);
		pb.setImg_content(image2);
		pb.setP_name(multi.getParameter("p_name"));
		
		System.out.println("여기까지 이상 없음");
		System.out.println(pb);
		System.out.println(image1);
		System.out.println(image2);
		
		ProductDAO pdao = new ProductDAO();
		pdao.productModify(pb);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//페이지 이동
		ActionForward forward = new ActionForward();
		forward.setPath("./ProductList.ap");
		forward.setRedirect(true);

		return forward;
	}
}