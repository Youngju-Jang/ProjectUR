package net.cart.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.product.db.ProductBean;


public class CartDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql = "";
	String sql2 = "";
	
	// 디비 연결
//	private Connection getCon() throws Exception {
	private void getCon() throws Exception {

		// 커넥션 풀
		//context.xml추가
		Context init = new InitialContext();
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/itwillbs14");
		con = ds.getConnection();
		System.out.println("DAO : 디비연결 완료 "+con);
	}//end of getCon()
	
	//장바구니에 담기
	public void cartAdd(CartBean cb) {
		int c_num = 0;
		try{
			getCon();
			//1. 장바구니에 해당 ID, 상품 정보가 있으면 상품 수  update 		
			sql = "select c_num from cart where id = ? and p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getId());
			pstmt.setInt(2, cb.getP_num());
			rs = pstmt.executeQuery();
			if(rs.next()){
				c_num = rs.getInt(1); //인덱스 사용 호출
				//rs.getInt("max(cno)"); // 컬럼명 사용 호출
				sql2 = "update cart set p_count = p_count + ? where c_num = ?";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, cb.getP_count());
				pstmt.setInt(2, c_num);
			// 2. 장바구니에 해당 ID, 상품 정보가  없으면 INSERT
			} else {
	
				//장바구니 번호계산		
				sql = "select max(c_num) from cart";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()){
					c_num = rs.getInt(1)+1; //인덱스 사용 호출
					//rs.getInt("max(cno)"); // 컬럼명 사용 호출
				}
				System.out.println("DAO : c_num은 "+c_num);
				
				sql2 = "insert into cart (c_num, id, p_num, p_count) values(?,?,?,?)";
				pstmt = con.prepareStatement(sql2);
				pstmt.setInt(1, c_num);
				pstmt.setString(2, cb.getId());
				pstmt.setInt(3, cb.getP_num());
				pstmt.setInt(4, cb.getP_count());
			}
			System.out.println("sql2" + sql2);
			pstmt.executeUpdate();
			System.out.println("장바구니 담기 성공");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}//end of basketAdd()

	
	//동일한 상품이 있는지 체크
	public int checkProduct(CartBean cb) {
		int result = 0;

		try{
			getCon();
			//sql : id, cno 모두 만족하는 대상 검색
			sql ="select * from cart "
					+ "where id=? and p_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getId());
			pstmt.setInt(2, cb.getP_num());
			rs = pstmt.executeQuery();
			if(rs.next()){ //중복상품인 경우
				result = 1;
				//구매수량 수정
				sql ="update cart set p_count=p_count+? "
						+ "where id=? and p_num=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, cb.getP_count());
				pstmt.setString(2, cb.getId());
				pstmt.setInt(3, cb.getP_num());
				pstmt.executeUpdate();
				System.out.println("기존의 상품에 수량 변경완료!");
			}
			System.out.println("기존의 상품 확인 결과: "+(result==1? "중복상품이 있다":"중복상품이 없다"));
			sql = "insert into team1_cart";
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}//end of checkGoods()
	
	//장바구니리스트
	@SuppressWarnings("rawtypes")
	public ArrayList<CartBean> getCartList(String id) {
		//1. 변수생성
		Vector totalData = new Vector();
		//상품정보
		List<ProductBean> productList = new ArrayList<ProductBean>();
		//장바구니정보 
		ArrayList<CartBean> cartList = new ArrayList<CartBean>();

		try{
			getCon();
			//2. 장바구니 정보 탐색 (id기준으로)
			sql ="select * from cart where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				System.out.println(">>> 바스켓 rs while시작");
				CartBean cb = new CartBean();
				cb.setP_count(rs.getInt("p_count"));
				cb.setP_num(rs.getInt("p_num"));
				cb.setId(rs.getString("id"));
				cb.setC_num(rs.getInt("c_num"));

				//장바구니에 저장된 상품정보를 불러오기
				//덮어쓰는 꼴이 되기때문에 PreparedStatement와 ResultSet 객체를 다시 생성해야한다.
				sql ="select * from product where p_num=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, cb.getP_num());
				ResultSet rs2 = pstmt2.executeQuery();
				if(rs2.next()){
					System.out.println(">>> 상품 rs if시작");
					ProductBean pb= new ProductBean();
//					pb.setImage(rs2.getString("image"));
					pb.setImg_main(rs2.getString("img_main"));
//					pb.setName(rs2.getString("name"));
					pb.setP_name(rs2.getString("p_name"));
//					pb.setPrice(rs2.getInt("price"));
					pb.setP_price(rs2.getInt("p_price"));
					pb.setP_saleprice(rs2.getInt("p_saleprice"));
					// 상품 리스트에 저장
					//productList.add(pb);	
					
					// 나머지 정보는 필요에 따라 추가 가능
					cb.setProducts(pb); //장바구니에 상품 정보 추가
				}
				
				//리스트에 저장
				cartList.add(cb);
				System.out.println(">>> 상품 rs if끝");
			}//end of while
			System.out.println(">>> 카트 rs while끝");
			System.out.println("상품 정보 : "+productList);
			System.out.println("장바구니 정보 : "+cartList);

			// 장바구니 정보, 상품정보를 모두 저장완료
			//totalData.add(cartList);
			//totalData.add(productList);
			//System.out.println("백터 정보 확인 : "+totalData);
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return cartList;
	}//end of 장바구니리스트
	
	/**
	 * 장바구니 상품 일부 삭제
	 * @param cb
	 * @return
	 */
	public int deleteCartPart(CartBean cb) {
		int result = 0;

		try{
			getCon();
			sql ="delete from cart where id=? and c_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, cb.getId());
			pstmt.setInt(2, cb.getC_num());
			pstmt.executeUpdate();
			System.out.println("DAO: 본인 장바구니 상품번호[" + cb.getP_num() + "] 삭제 완료");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
		return result;
	}//end of deleteCartPart()
	
	//장바구니 상품 전체 삭제 (오버로딩)
	public void deleteCart(String id) {
		try{
			getCon();
			sql ="delete from cart where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			System.out.println("DAO: 본인 장바구니  전체 삭제 완료");
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			closeDB();
		}
	}//end of 장바구니전체삭제
	
	// 디비 자원해제
	public void closeDB(){
		try {
			if(rs != null) {rs.close();	}
			if(pstmt != null) {pstmt.close();	}
			if(con != null) {con.close(); }
		}
		catch (SQLException e) {
			e.printStackTrace();
		} 
	}//end of closeDB()
	
}