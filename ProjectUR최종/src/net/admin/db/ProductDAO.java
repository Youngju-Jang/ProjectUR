package net.admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.product.db.ProductBean;

public class ProductDAO {

	// DB에 관련된 모든 처리를 하는 객체
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// 디비 연결 - 커넥션 풀을 사용해서 디비 연결
		private Connection getCon() throws Exception {

			Context init = new InitialContext();
			DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/itwillbs14");
			con = ds.getConnection();

			return con;
		}

		// 디비 자원해제
		public void closeDB() {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		//insertProduct(pb)
		public void insertProduct(ProductBean pb) {
			
			int bno = 0;
			
			try {
				//p_num 처리
				con = getCon();
				sql = "select max(p_num) from product";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if (rs.next()) {
					bno = rs.getInt(1) + 1;
				}else {
					bno = 1;
				}
				
				System.out.println(bno);
				
				//insert처리
				sql = "insert into product(p_num,category,p_name,p_count,p_price,p_saleprice,img_main,img_content,price_count)"
						+ "values(?,?,?,?,?,?,?,?,?)";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, bno);
				pstmt.setInt(2, pb.getCategory());
				pstmt.setString(3, pb.getP_name());
				pstmt.setInt(4, pb.getP_count());
				pstmt.setInt(5, pb.getP_price());
				pstmt.setInt(6, pb.getP_saleprice());
				pstmt.setString(7, pb.getImg_main());
				pstmt.setString(8, pb.getImg_content());
				pstmt.setInt(9, pb.getP_count()*pb.getP_saleprice()); // => 결제시 동작?
				
				pstmt.executeUpdate();
				System.out.println("insert완료");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("inset실패");
			} finally {
				closeDB();
			}
			
		}
		//insertProduct(pb)
		
		//getProductList()
		public List getProductList()  {
			List productList = new ArrayList();
			
			try {
				con = getCon();
				
				sql = "select p1.category, p1.img_content, p1.img_main, p1.p_count-sum(coalesce(p2.b_count,0)) as p_count, p1.p_name, p1.p_price, p1.p_saleprice, p1.price_count, p1.readcount, p1.p_num, p1.action from product p1 left join p_order p2 on p1.p_num = p2.p_num group by p1.p_num order by p1.p_num desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					ProductBean pb = new ProductBean();
					pb.setCategory(rs.getInt("category"));
					pb.setImg_content(rs.getString("img_content"));
					pb.setImg_main(rs.getString("img_main"));
					pb.setP_count(rs.getInt("p_count"));
					pb.setP_name(rs.getString("p_name"));
					pb.setP_num(rs.getInt("p_num"));
					pb.setP_price(rs.getInt("p_price"));
					pb.setP_saleprice(rs.getInt("p_saleprice"));
					pb.setPrice_count(rs.getInt("price_count"));
					pb.setAction(rs.getInt("action"));
					//productList 안에 담기
					productList.add(pb);
				}
				System.out.println("getList 완료");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return productList;
		}
		//getProductList()
		
		//productModify(pb)
		public void productModify(ProductBean pb) {
			
			try {
				con = getCon();
				sql = "update product set category=?, p_name=?, p_price=?, p_saleprice=?, p_count=?, img_main=?, img_content=? where p_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, pb.getCategory());
				pstmt.setString(2, pb.getP_name());
				pstmt.setInt(3, pb.getP_price());
				pstmt.setInt(4, pb.getP_saleprice());
				pstmt.setInt(5, pb.getP_count());
				pstmt.setString(6, pb.getImg_main());
				pstmt.setString(7, pb.getImg_content());
				pstmt.setInt(8, pb.getP_num());
				
				pstmt.executeUpdate();
				System.out.println("수정성공");
			} catch (Exception e) {
				System.out.println("수정실패");
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		//productModify(pb)
		
		//deleteProduct(p_num)
		public void deleteProduct(int p_num) {
			
			try {
				con = getCon();
				sql = "update product set action=1 where p_num=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, p_num);
				pstmt.executeUpdate();
				System.out.println("삭제 성공");
			} catch (Exception e) {
				System.out.println("삭제 실패");
				e.printStackTrace();
			} finally {
				closeDB();
			}
		}
		//deleteProduct(p_num)
		
		
		//getProduct(p_num)
		public ProductBean getProduct(int p_num) {
			ProductBean pb = null;
			try {
				con = getCon();
				sql = "select * from product where p_num=?";
				pstmt = con.prepareStatement(sql);
				
				pstmt.setInt(1, p_num);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					pb = new ProductBean();
					pb.setCategory(rs.getInt("category"));
					pb.setP_count(rs.getInt("p_count"));
					pb.setImg_content(rs.getString("img_content"));
					pb.setImg_main(rs.getString("img_main"));
					pb.setP_name(rs.getString("p_name"));
					pb.setP_num(rs.getInt("p_num"));
					pb.setP_price(rs.getInt("p_price"));
					pb.setP_saleprice(rs.getInt("p_saleprice"));
					pb.setPrice_count(rs.getInt("price_count"));
				}
				System.out.println("getProduct(num)성공");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				closeDB();
			}
			
			return pb;
		}
		//getProduct(p_num)
}
