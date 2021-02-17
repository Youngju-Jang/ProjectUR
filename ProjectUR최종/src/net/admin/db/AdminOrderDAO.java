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

import jdk.nashorn.internal.ir.ReturnNode;
import net.member.db.MemberBean;
import net.order.db.OrderBean;
import net.product.db.ProductBean;
import net.product.db.ProductQnaBean;

public class AdminOrderDAO {

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
	
	//getAdminOrderList()
	public List getAdminOrderList() {
		List list = new ArrayList();
		
		try {
			getCon();
			sql = "select * from p_order order by b_num desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("문제없음");
			while(rs.next()) {
				OrderBean ob = new OrderBean();
				ob.setB_count(rs.getInt("b_count"));
				ob.setB_date(rs.getDate("b_date"));
				ob.setB_num(rs.getInt("b_num"));
				ob.setD_address(rs.getString("d_address"));
				ob.setD_address2(rs.getString("d_address2"));
				ob.setD_cost(rs.getInt("d_cost"));
				ob.setD_message(rs.getString("d_message"));
				ob.setD_name(rs.getString("d_name"));
				ob.setD_phone(rs.getString("d_phone"));
				ob.setId(rs.getString("id"));
				ob.setO_email(rs.getString("o_email"));
				ob.setO_name(rs.getString("o_name"));
				ob.setO_phone(rs.getString("o_phone"));
				ob.setP_num(rs.getInt("p_num"));
				ob.setPayment(rs.getInt("payment"));
				ob.setPoint(rs.getInt("point"));
				ob.setD_result(rs.getInt("d_result"));
				list.add(ob);
			}
			System.out.println("문제없음");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return list;
	}
	//getAdminOrderList()
	
	//getAdminOrderDetail(b_num)
	public List getAdminOrderDetail(int b_num, int p_num) {
		List list = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from p_order po join product p where po.p_num = p.p_num and po.p_num=? and po.b_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, p_num);
			pstmt.setInt(2, b_num);
			
			rs = pstmt.executeQuery();
			
			System.out.println("문제 없음");
			while(rs.next()) {
				OrderBean ob = new OrderBean();
				ProductBean pb = new ProductBean();
				ob.setB_count(rs.getInt("b_count"));
				ob.setB_date(rs.getDate("b_date"));
				ob.setB_num(rs.getInt("b_num"));
				ob.setD_address(rs.getString("d_address"));
				ob.setD_address2(rs.getString("d_address2"));
				ob.setD_cost(rs.getInt("d_cost"));
				ob.setD_message(rs.getString("d_message"));
				ob.setD_name(rs.getString("d_name"));
				ob.setD_phone(rs.getString("d_phone"));
				ob.setId(rs.getString("id"));
				ob.setO_email(rs.getString("o_email"));
				ob.setO_name(rs.getString("o_name"));
				ob.setO_phone(rs.getString("o_phone"));
				ob.setP_num(rs.getInt("p_num"));
				ob.setPayment(rs.getInt("payment"));
				ob.setPoint(rs.getInt("point"));
				ob.setD_result(rs.getInt("d_result"));
				pb.setCategory(rs.getInt("category"));
				pb.setP_count(rs.getInt("p_count"));
				pb.setImg_content(rs.getString("img_content"));
				pb.setImg_main(rs.getString("img_main"));
				pb.setP_name(rs.getString("p_name"));
				pb.setP_num(rs.getInt("p_num"));
				pb.setP_price(rs.getInt("p_price"));
				pb.setP_saleprice(rs.getInt("p_saleprice"));
				pb.setPrice_count(rs.getInt("price_count"));
				list.add(ob);
				list.add(pb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}
	//getAdminOrderDetail(b_num)
	
	//updateOrder(OrderBean ob)
	public void updateOrder(OrderBean ob) {
		try {
			con = getCon();
			sql = "update p_order set d_result=? where b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, ob.getD_result());
			pstmt.setInt(2, ob.getB_num());
			
			pstmt.executeUpdate();
			System.out.println("수정 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("수정 실패");
		} finally {
			closeDB();
		}
		
	}
	//updateOrder(OrderBean ob)
	
	//deleteOrder(b_num)
	public void deleteOrder(int b_num) {
		
		try {
			con = getCon();
			sql = "delete from p_order where b_num=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, b_num);
			
			pstmt.executeUpdate();
			System.out.println("삭제 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("삭제 실패");
		} finally {
			closeDB();
		}
	}
	//deleteOrder(b_num)
	
	//adao.getMemberList()
	public List getMemberList() {
		List list = new ArrayList();
		
		try {
			con = getCon();
			sql = "select m.action,m.id, p.o_name as pass, m.email, m.address1, m.address2, grade, count(coalesce(b_num,0)) as postcode, sum(coalesce(p.point,0)) as point, sum(coalesce(p1.p_saleprice,0)*coalesce(p.b_count,0)) as totalprice from member m left join p_order p on m.id = p.id left join product p1 on p.p_num = p1.p_num group by m.id";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				MemberBean mb = new MemberBean();
				mb.setAddress1(rs.getString("address1"));
				mb.setAddress2(rs.getString("address2"));
				mb.setEmail(rs.getString("email"));
				mb.setGrade(rs.getInt("grade"));
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setPoint(rs.getInt("point"));
				mb.setPostcode(rs.getInt("postcode"));
				mb.setTotalprice(rs.getInt("totalprice"));
				mb.setAction(rs.getInt("action"));
				list.add(mb);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return list;
	}
	//adao.getMemberList()
	
	//getAdminOrderList(id)
	public List getAdminOrderList(String id) {
		List list = new ArrayList();
		
		try {
			getCon();
			sql = "select * from p_order where id=? order by b_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("문제없음");
			if(rs.next()) {
				do {
					OrderBean ob = new OrderBean();
					ob.setB_count(rs.getInt("b_count"));
					ob.setB_date(rs.getDate("b_date"));
					ob.setB_num(rs.getInt("b_num"));
					ob.setD_address(rs.getString("d_address"));
					ob.setD_address2(rs.getString("d_address2"));
					ob.setD_cost(rs.getInt("d_cost"));
					ob.setD_message(rs.getString("d_message"));
					ob.setD_name(rs.getString("d_name"));
					ob.setD_phone(rs.getString("d_phone"));
					ob.setId(rs.getString("id"));
					ob.setO_email(rs.getString("o_email"));
					ob.setO_name(rs.getString("o_name"));
					ob.setO_phone(rs.getString("o_phone"));
					ob.setP_num(rs.getInt("p_num"));
					ob.setPayment(rs.getInt("payment"));
					ob.setPoint(rs.getInt("point"));
					ob.setD_result(rs.getInt("d_result"));
					list.add(ob);
				}while(rs.next());
			}else {
				list=null;
			}
			
			System.out.println("문제없음");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return list;
	}
	//getAdminOrderList()
	
	//deleteMember(id)
	public void deleteMember(String id) {
		int result = -1;
		try {
			con = getCon();
			sql = "update member set action=1, pass=0, postcode=0, address1=null, address2=null, grade=0, totalprice=0, point=0 where id=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
	}
	//deleteMember(id)
	
	//getQnaList()
	public List getQnaList() {
		List list = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from productqna where re_result = 0 order by reg_date desc";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				do {
					ProductQnaBean qb = new ProductQnaBean();
					qb.setContent(rs.getString("content"));
					qb.setId(rs.getString("id"));
					qb.setP_num(rs.getInt("p_num"));
					qb.setQ_num(rs.getInt("q_num"));
					qb.setRe_lev(rs.getInt("re_lev"));
					qb.setRe_ref(rs.getInt("re_ref"));
					qb.setRe_reg_date(rs.getDate("re_reg_date"));
					qb.setRe_result(rs.getInt("re_result"));
					qb.setReg_date(rs.getDate("reg_date"));
					qb.setReply(rs.getString("reply"));
					qb.setSubject(rs.getString("subject"));
					
					list.add(qb);
				}while(rs.next());
			}else {
				list = null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return list;
	}
	//getQnaList()
}