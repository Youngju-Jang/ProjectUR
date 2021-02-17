package net.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductDAO {

	// DB에 관련된 모든 처리를 하는 객체
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";

	// 디비연결 - 커넥션 풀을 사용해서 디비연결
	private Connection getCon() throws Exception {

		Context init = new InitialContext();// 커넥션풀 사용한거??
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/itwillbs14");
		con = ds.getConnection();

		return con;
	}

	// 디비자원해제
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

	public static void commit(Connection con) {
		try {
			con.commit();
			System.out.println("commit success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void rollback(Connection con) {
		try {
			con.rollback();
			System.out.println("rollback success");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getProductList()
	public ArrayList<ProductBean> getProductList(String odb, int startPage, int pageSize, int category, String search) {

		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try {
			con = getCon();
			if (category == 0) { // 전체일때
				// search 널일경우 처리필요 -> "" 으로
				if (odb == null || odb == "" || odb.equals("all")) {// 정렬옵션클릭전
					sql = "select * from product " + "where p_name like ? and action=0 " + "order by p_num desc limit ?,? ";
				} else if (odb.equals("num_desc")) {
					sql = "select * from product " + "where p_name like ? and action=0 " + "order by p_num desc limit ?,?";
				} else if (odb.equals("price_high")) {
					sql = "select * from product " + "where p_name like ? and action=0 " + "order by p_saleprice desc, p_num desc limit ?,?";
				} else if (odb.equals("price_low")) {
					sql = "select * from product " + "where p_name like ? and action=0 " + "order by p_saleprice asc, p_num desc limit ?,?";
				} else if (odb.equals("star_avg")) { // ***** search 구문 완성하기 -> 여기 안되는중 문의예정
					sql = "select *,  avg(r.r_star) 'star_avg' "
							+ "from review r  right outer join (select * from product where p_name like ? and action=0) p  "
							+ "on r.p_num = p.p_num group by p.p_num " + "order by avg(r.r_star) desc, p.p_num desc limit ?,?";
				} else if (odb.equals("readcount")) {
					sql = "select * from product " + "where p_name like ? and action=0 " + "order by readcount desc, p_num desc limit ?,?";
				}
				pstmt = con.prepareStatement(sql);
				if (search.equals("")) {
					pstmt.setString(1, "%%");
					pstmt.setInt(2, startPage - 1); // 시작행 -1
					pstmt.setInt(3, pageSize); // 갯수
				} else {
					pstmt.setString(1, "%" + search + "%");
					pstmt.setInt(2, startPage - 1); // 시작행 -1
					pstmt.setInt(3, pageSize); // 갯수
				}

			} else {// 다른 카테고리일 경우
				if (odb == null || odb == "" || odb.equals("all")) {
					sql = "select * from product " + "where category = ? and p_name like ? and action=0 " + "order by p_num desc limit ?,? ";
				} else if (odb.equals("num_desc")) {
					sql = "select * from product " + "where category = ?  and p_name like ? and action=0 " + "order by p_num desc "
							+ "limit ?,?";
				} else if (odb.equals("price_high")) {
					sql = "select * from product " + "where category = ?  and p_name like ? and action=0 "
							+ "order by p_saleprice desc, p_num desc " + "limit ?,?";
				} else if (odb.equals("price_low")) {
					sql = "select * from product " + "where category = ?  and p_name like ? and action=0 "
							+ "order by p_saleprice asc, p_num desc " + "limit ?,?";
				} else if (odb.equals("star_avg")) {
					sql = "select *,  avg(r.r_star) 'star_avg' " + "from review r right outer join "
							+ "(select * from product where category=? and p_name like ? and action=0) p " + "on r.p_num = p.p_num "
							+ "group by p.p_num " + "order by avg(r.r_star) desc, p.p_num desc " + "limit ?,?";
				} else if (odb.equals("readcount")) {
					sql = "select * from product " + "where category = ?  and p_name like ? and action=0 "
							+ "order by readcount desc, p_num desc " + "limit ?,?";
				}
				pstmt = con.prepareStatement(sql);
				if (search.equals("")) {
					pstmt.setInt(1, category);
					pstmt.setString(2, "%%");
					pstmt.setInt(3, startPage - 1); // 시작행 -1
					pstmt.setInt(4, pageSize); // 갯수
				} else {
					pstmt.setInt(1, category);
					pstmt.setString(2, "%" + search + "%");
					pstmt.setInt(3, startPage - 1); // 시작행 -1
					pstmt.setInt(4, pageSize); // 갯수
				}

			}

			rs = pstmt.executeQuery();

			while (rs.next()) {

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
				pb.setReadcount(rs.getInt("readcount"));
				/*
				 * pb.setStar_avg(0);
				 * 
				 * if(odb.equals("star_avg")) { pb.setStar_avg(rs.getInt("star_avg")); }
				 * 
				 * sql = "select round(avg(r.r_star),2)'star_avg', p.p_num " +
				 * "from review r right outer join " +
				 * "(select * from product where p_num = ?) p " + "on r.p_num = p.p_num " +
				 * "group by p.p_num";
				 * 
				 * pstmt = con.prepareStatement(sql); pstmt.setInt(1, rs.getInt("p_num")); rs =
				 * pstmt.executeQuery(); pb.setStar_avg(0); if(rs.next()) {//별점 있으면 평균별점 나옴
				 * pb.setStar_avg(rs.getInt("star_avg")); }
				 */
				productList.add(pb);
			}
			System.out.println("DAO : ProductList저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return productList;
	}

	// getProductList()

	// getProductListCount()
	public int getProductListCount(int category, String search) {
		int result = 0;

		try {
			con = getCon();
			if(category == 0) {
				sql = "select count(*) from product where p_name like ? and action=0";
			}else {
				sql = "select count(*) from product where p_name like ? and category = ? and action=0";
			}
			pstmt = con.prepareStatement(sql);
			if(search.equals("")) {
				pstmt.setString(1, "%%");
			}else {
				pstmt.setString(1, "%"+search+"%");
			}
			if(category != 0) {
				pstmt.setInt(2, category);
			}
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count(*)");
			}
			System.out.println("DAO : 글 개수 계산완료 : " + result + "개");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;

	}
	// getProductListCount()

	// getStarAvg(int num)
	public double getStarAvg(int num) {
		double result = 0;

		try {
			con = getCon();
			sql = "select round(avg(r.r_star),1) 'star_avg', p.p_num "
				+ "from review r right outer join "
				+ "(select * from product where p_num = ?) p "
				+ "on r.p_num = p.p_num "
				+ "group by p.p_num";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getDouble("star_avg");
			}else {
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	// getStarAvg()
	
	// getReviewCount(int num)
	public int getReviewCount(int num) {
	int result = 0;

	try {
		con = getCon();
		sql = "select count(r.r_star) 'review_count', p.p_num "
			+ "from review r right outer join "
			+ "(select * from product where p_num = ?) p "
			+ "on r.p_num = p.p_num "
			+ "group by p.p_num";
		pstmt = con.prepareStatement(sql);
		pstmt.setInt(1, num);
		rs = pstmt.executeQuery();
		if(rs.next()) {
			result = rs.getInt("review_count");
		}else {
			result = 0;
		}
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		closeDB();
	}

	return result;
}
	
	// getReviewCount(int num)
	
	//getProduct(p_num)
	public ProductBean getProduct(int p_num) {
		
		ProductBean pb = new ProductBean();
		
		try {
			con = getCon();
			sql = "select * from product where p_num = ? and action=0";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {//제품있음
				pb.setCategory(rs.getInt("category"));
				pb.setImg_content(rs.getString("img_content"));
				pb.setImg_main(rs.getString("img_main"));
				pb.setP_count(rs.getInt("p_count"));
				pb.setP_name(rs.getString("p_name"));
				pb.setP_num(rs.getInt("p_num"));
				pb.setP_price(rs.getInt("p_price"));
				pb.setP_saleprice(rs.getInt("p_saleprice"));
				pb.setPrice_count(rs.getInt("price_count"));
				pb.setReadcount(rs.getInt("readcount"));
			}
			System.out.println("DAO : ProductBean 저장완료!");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			closeDB();
		}
		
		return pb;
	}
	//getProduct(p_num)
	
	//getReviewList(p_num)
	public ArrayList<ReviewBean> getReviewList(int p_num){
		
		ArrayList<ReviewBean> reviewList = null;
		
		
			try {
				con = getCon();
				
				//조회수 1증가
				sql = "update product set readcount = readcount + 1 where p_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				pstmt.executeUpdate();
				
				sql = "select * from review where p_num = ? order by r_num desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				rs = pstmt.executeQuery();
				reviewList = new ArrayList<ReviewBean>();
				while(rs.next()) {
					ReviewBean rb = new ReviewBean();
					
					rb.setId(rs.getString("id"));
					rb.setP_num(rs.getInt("p_num"));
					rb.setR_content(rs.getString("r_content"));
					rb.setR_date(rs.getDate("r_date"));
					rb.setR_num(rs.getInt("r_num"));
					rb.setR_star(rs.getDouble("r_star"));
					
					reviewList.add(rb);
				}
				System.out.println("DAO : " + p_num + "번 제품 reviewList 저장완료");
				System.out.println("DAO : reviewList 갯수 : " + reviewList.size());
			} catch (Exception e) {
				System.out.println(p_num + "번 제품 reviewList 저장실패");
				e.printStackTrace();
			}finally {
				closeDB();
			}
		
		
		
		return reviewList;
		
	}
	
	//getReviewList(p_num)
	
	//getMyReview(p_num, id)
	public ReviewBean getMyReview(int p_num, String id) {
		
		ReviewBean rb = null;
		
		try {
			con = getCon();
			
			sql = "select * from review where p_num = ? and id = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_num);
			pstmt.setString(2, id);
			rs = pstmt.executeQuery();
			System.out.println("DAO : getMyReview 실행");
			if(rs.next()){
				rb = new ReviewBean();
				rb.setId(rs.getString("id"));
				rb.setP_num(rs.getInt("p_num"));
				rb.setR_content(rs.getString("r_content"));
				rb.setR_date(rs.getDate("r_date"));
				rb.setR_num(rs.getInt("r_num"));
				rb.setR_star(rs.getDouble("r_star"));
				
				System.out.println("DAO : 개인 reviewBean 저장완료");
			}
		} catch (Exception e) {
			System.out.println("DAO : 개인 reviewBean 저장실패");
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return rb;
	}
	//getMyReview(p_num, id)
	
	//recommendList(category, p_num)
	public ArrayList<ProductBean> recommendList(int category, int p_num){
		
		ArrayList<ProductBean> recomList = new ArrayList<ProductBean>();
		ProductBean pb = null;
		
		try {
			con = getCon();
			
			sql = "select * from product where category = ? and p_num != ? order by p_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, category);
			pstmt.setInt(2, p_num);
			rs = pstmt.executeQuery();
			int i = 0;
			while(rs.next() && (i <4)) {
				pb = new ProductBean();
				pb.setCategory(rs.getInt("category"));
				pb.setImg_content(rs.getString("img_content"));
				pb.setImg_main(rs.getString("img_main"));
				pb.setP_count(rs.getInt("p_count"));
				pb.setP_name(rs.getString("p_name"));
				pb.setP_num(rs.getInt("p_num"));
				pb.setP_price(rs.getInt("p_price"));
				pb.setP_saleprice(rs.getInt("p_saleprice"));
				pb.setPrice_count(rs.getInt("price_count"));
				pb.setReadcount(rs.getInt("readcount"));
				recomList.add(pb);
				i++;
				System.out.println("DAO : recomList 저장 완료!");
			}
			
			
		} catch (Exception e) {
			System.out.println("DAO : recomList 저장 실패!");
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		
		
		return recomList;
		
	}
	//recommendList(category, p_num)
	

	/**
	 * 주문 후 상품 수량 감소
	 * 
	 * @param p_num
	 * @param p_count
	 * @return
	 */
	public int updateProductCnt(int p_num, int p_count) {
		int result = 0;

		try{
			getCon();
			sql ="update product set p_count = p_count - ? where p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, p_count);
			pstmt.setInt(2, p_num);
			pstmt.executeUpdate();
			System.out.println("DAO: 상품테이블 [" + p_num + "] 상품 수량 변경");
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}//end of updateProductCnt()
	
	
	// getProductList(startRow, pageSize) -조회수 높은 순으로 메인 페이지에 띄워주는 메서드
	public ArrayList<ProductBean> getProductList(int startRow, int pageSize) {
		ArrayList<ProductBean> productList = new ArrayList<ProductBean>();
		try {
			con = getCon();
			sql = "select * from product where action=0 order by readcount desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while (rs.next()) {
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
				pb.setReadcount(rs.getInt("readcount"));
				productList.add(pb);
			}
			System.out.println("DAO : ProductList저장 완료!");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return productList;
	}
	// getProductList(startRow, pageSize)	
}
