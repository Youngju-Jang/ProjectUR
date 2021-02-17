package net.member.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.order.db.OrderBean;
import net.product.db.ProductBean;
import net.product.db.ProductQnaBean;
import net.product.db.ReviewBean;


public class MemberDAO {
	// DB에 관련된 모든 처리를 하는 객체
	Connection con = null;
	PreparedStatement pstmt =null;
	ResultSet rs = null;
	String sql ="";
	
	
	// 디비 연결 - 커넥션 풀을 사용해서 디비 연결
	private Connection getCon() throws Exception{
		
		Context init = new InitialContext();
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/itwillbs14");
		con = ds.getConnection();
		
		return con;
	}
	
	// 디비 자원해제
	public void closeDB() {
		try {
			if(rs != null) { rs.close(); }
			if(pstmt != null) { pstmt.close(); }
			if(con != null) { con.close(); }			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// insertMember(mb)
	public void insertMember(MemberBean mb) {
		
		try {
			con = getCon();
		 
			sql = "insert into member(id,pass,email,postcode,address1,address2) values(?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, mb.getId());
			pstmt.setString(2, mb.getPass());
			pstmt.setString(3, mb.getEmail());
			pstmt.setInt(4, mb.getPostcode());
			pstmt.setString(5, mb.getAddress1());
			pstmt.setString(6, mb.getAddress2());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
				
	}
	
	
	// loginCheck(id,pass)
	public int loginCheck(String id,String pass) {
		int result = -1;
		
		try {
			
			con = getCon();
			
			sql = "select pass, action from member where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(rs.getInt("action")==1) { //사용 불가 계정
					result=0;
				}else if(pass.equals(rs.getString("pass"))) { //로그인 가능
					result=1;
				}else { //비밀번호 오류
					result=-1;
				}
			}else { //비회원
				result=-1;
			}
			
			
//			if(rs.next()) {
//				// 회원이다
//				if(pass.equals(rs.getString("pass"))) {
//					//본인
//					result = 1;
//					if(rs.getInt("action")==1) { //사용 불가 계정(탈퇴/강제탈퇴)
//						result=2;
//						result=0;
//					}
//				}else {
//					//본인x(비밀번호 오류)
//					result = 0;
//					result = -1;
//				}				
//			}else {
//				// 비회원이다
//				result = -1;
//			}	
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}

		return result;
	}
	
	
	
	// getMember(id)
	public MemberBean getMember(String id) {
		MemberBean mb = null;
		
		try {
			con = getCon();
			
			sql="select * from member where id=?";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) { 
				mb = new MemberBean();
				mb.setId(rs.getString("id"));
				mb.setPass(rs.getString("pass"));
				mb.setEmail(rs.getString("email"));
				mb.setPostcode(rs.getInt("postcode"));
				mb.setAddress1(rs.getString("address1"));
				mb.setAddress2(rs.getString("address2"));
				mb.setPoint(rs.getInt("point"));
				mb.setGrade(rs.getInt("grade"));
				mb.setTotalprice(rs.getInt("totalprice"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return mb;
	}
	
	
	
	// updateMember(UIMB)
	public int updateMember(MemberBean UIMB) {
	
		int result = -1;
		
		try {
			con = getCon();
			
			sql = "select pass from member where id = ? and action=0";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, UIMB.getId());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(UIMB.getPass().equals(rs.getString("pass"))) {
                   
					sql = "update member set postcode=?,address1=?,address2=? where id=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setInt(1, UIMB.getPostcode());
					pstmt.setString(2, UIMB.getAddress1());
					pstmt.setString(3, UIMB.getAddress2());
					pstmt.setString(4, UIMB.getId());
							
					result = pstmt.executeUpdate();
					
				}else {
					// 비밀번호 오류
					result = 0;
				}
			}else {
				// 비회원
				 result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
		
	}	
	
	
	
	// deleteMember(id,pass)
	public int deleteMember(String id,String pass) {
		int result =-1;
		
		try {
			con = getCon();
			
			sql = "select pass from member where id = ? and action=0";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				if(pass.equals(rs.getString("pass"))) {
					
//					sql = "delete from member where id=?";
					sql = "update member set action=1, pass=0, postcode=0, address1=null, address2=null, grade=0, totalprice=0, point=0 where id=?";
					pstmt = con.prepareStatement(sql);
					
					pstmt.setString(1, id);
							
					result = pstmt.executeUpdate();
					
				}else {
					// 비밀번호 오류
					result = 0;
				}
			}else {
				// 비회원
				result = -1;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		
		return result;
	}

	
	//passCHeck
	public int passCheck(String id, String pass) {
		int result = -1;
		// 1 - 로그인성공, 0 - 비밀번호 오류, -1 - 아이디가 없음

		try {
			con = getCon();
			
			sql = "select pass from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if (rs.next()) {// 아이디 있을때
				if (pass.equals(rs.getString("pass"))) {// 비밀번호 일치 => 로그인상태
					System.out.println(" 로그인 성공! ");
					result = 1;
				} else {// 비밀번호 불일치 => 로그인 실패
					System.out.println(" 비밀번호 오류 ! ");
					result = 0;
				}
			} else {// 아이디가 없을때
				System.out.println(" 아이디 없음 ! ");
				result = -1;
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;
	}
	
	
		//이메일 인증 메서드
		public int sendEmail(String to, String authNum) {
			String from = "rmedictest@naver.com";
			String subject = "이메일 인증";
			String content = "[" + authNum + "] 입력해주세요";
			
			Properties p = new Properties(); // 정보를 담을 객체

			p.put("mail.smtp.host", "smtp.naver.com"); // 네이버 SMTP

			p.put("mail.smtp.port", "465");
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.smtp.auth", "true");
			p.put("mail.smtp.debug", "true");
			p.put("mail.smtp.socketFactory.port", "465");
			p.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			p.put("mail.smtp.socketFactory.fallback", "false");
			// SMTP 서버에 접속하기 위한 정보들

			try {
				Authenticator auth = new SMTPAuthenticator();
				Session ses = Session.getInstance(p, auth);

				ses.setDebug(true);

				MimeMessage msg = new MimeMessage(ses); // 메일의 내용을 담을 객체
				msg.setSubject(subject); // 제목

				Address fromAddr = new InternetAddress(from);
				msg.setFrom(fromAddr); // 보내는 사람

				Address toAddr = new InternetAddress(to);
				msg.addRecipient(Message.RecipientType.TO, toAddr); // 받는 사람

				msg.setContent(content, "text/html;charset=UTF-8"); // 내용과 인코딩

				Transport.send(msg); // 전송
				
				return 1;
			} catch (Exception e) {
				System.out.println("sendEmail()메서드에서 에러 : " + e);
				return 0;
			}
		}
	
	public String randomNum() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 6; i++) {
			int n = (int)(Math.random() * 10);
			sb.append(n);
		}
		return sb.toString();
	}
	
	//getMemberOrderList(id)
	public List getMemberOrderList(String id) {
		List list = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from p_order where id=? order by b_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
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
			System.out.println("DAO : "+id+"의 주문내역 출력 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return list;
	}//getMemberOrderList(id)
	
	//getMemberOrderCount(id)
	public int getMemberOrderCount(String id) {
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(*) from p_order where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
			}
			System.out.println("DAO : "+id+" "+result+"회 주문");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return result;		
	}//getMemberOrderCount(id)
	
	
	//getMemberOrderDetail(id)-주문한 제품
	public ArrayList<Map<String, Object>> getMemberOrderDetail(String id){
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			con = getCon();
			sql = "select * from p_order po join product p on po.p_num = p.p_num "
					+ "where id=? order by po.b_num desc, p.p_num asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("b_num",rs.getInt("po.b_num"));
				map.put("id", rs.getString("po.id"));
				map.put("o_name", rs.getString("po.o_name"));
				map.put("o_phone", rs.getString("o_phone"));
				map.put("b_date", rs.getDate("po.b_date"));
				map.put("p_num", rs.getInt("po.p_num"));
				map.put("b_count", rs.getInt("b_count"));
				map.put("point", rs.getInt("point"));
				map.put("d_cost", rs.getInt("d_cost"));
				map.put("d_result", rs.getObject("po.d_result"));
				map.put("d_name", rs.getString("d_name"));
				map.put("d_phone", rs.getString("d_phone"));
				map.put("d_postcode", rs.getInt("d_postcode"));
				map.put("d_address", rs.getString("d_address"));
				map.put("d_address2", rs.getString("d_address2"));
				map.put("payment", rs.getInt("payment"));
				map.put("p_name", rs.getString("p.p_name"));
				map.put("p_price", rs.getInt("p_price"));
				map.put("p_saleprice", rs.getInt("p_saleprice"));
				map.put("price_count", rs.getInt("price_count"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return list;
	}//getMemberOrderDetail(id)
	
	
	//getMemberOrderDetail(id,b_num)-주문한 제품의 주문번호와 일치하는 제품
	public ArrayList<Map<String, Object>> getMemberOrderDetail(String id, int b_num){
		ArrayList<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		
		try {
			con = getCon();
			sql = "select * from p_order po join product p on po.p_num = p.p_num "
					+ "where id=? and b_num=?order by po.b_num desc, p.p_num asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, b_num);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("b_num",rs.getInt("po.b_num"));
				map.put("id", rs.getString("po.id"));
				map.put("o_name", rs.getString("po.o_name"));
				map.put("o_phone", rs.getString("o_phone"));
				map.put("b_date", rs.getDate("po.b_date"));
				map.put("p_num", rs.getInt("po.p_num"));
				map.put("b_count", rs.getInt("b_count"));
				map.put("point", rs.getInt("point"));
				map.put("d_cost", rs.getInt("d_cost"));
				map.put("d_result", rs.getObject("po.d_result"));
				map.put("d_name", rs.getString("d_name"));
				map.put("d_phone", rs.getString("d_phone"));
				map.put("d_postcode", rs.getInt("d_postcode"));
				map.put("d_address", rs.getString("d_address"));
				map.put("d_address2", rs.getString("d_address2"));
				map.put("payment", rs.getInt("payment"));
				map.put("p_name", rs.getString("p.p_name"));
				map.put("p_price", rs.getInt("p_price"));
				map.put("p_saleprice", rs.getInt("p_saleprice"));
				map.put("price_count", rs.getInt("price_count"));
				list.add(map);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return list;
	}//getMemberOrderDetail(id,b_num)
	
	
	//getCheckReview(id, p_num, b_num)
	public int getCheckReview(String id, int p_num, int b_num) {
		int result = 0;
		try {
			con = getCon();
			sql = "select p_num from p_order where id=? and b_num=? order by b_num desc, p_num asc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setInt(2, b_num);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = 1;
			}
			System.out.println("결과 : "+result);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}				
		return result;
	}//getCheckReview(id, b_num, p_num)
	
	
	//getMemberQnaList(id)
	public List getMemberQnaList(String id) {
		List list = new ArrayList();
		
		try {
			con = getCon();
			sql = "select * from productqna where id=? order by q_num desc";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
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
			}
			System.out.println("DAO : "+id+"의 문의내역 출력 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return list;
	}//getMemberQnaList(id, b_num, p_num)
	
	//getMemberQnaCount(id)
	public int getMemberQnaCount(String id) {
		int result = 0;
		
		try {
			con = getCon();
			sql = "select count(*) from productqna where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				result = rs.getInt("count(*)");
			}
			System.out.println("DAO : "+id+" "+result+"회 문의");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}		
		return result;
	}//getMemberQnaCount(id)
	
	
	//insertReview(rb)
	public void insertReview(ReviewBean rb) {
		int rno = 0;
		try {
			con = getCon();
			sql = "select max(r_num) from review";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				rno = rs.getInt(1)+1;
			}else {
				rno = 1;
			}
			
			sql = "insert into review(r_num, p_num, id, r_content, r_star, r_date) "
					+ "values(?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rno);
			pstmt.setInt(2, rb.getP_num());
			pstmt.setString(3, rb.getId());
			pstmt.setString(4, rb.getR_content());
			pstmt.setDouble(5, rb.getR_star());
			pstmt.executeUpdate();
			System.out.println("DAO : 리뷰 작성 성공");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
	}//insertReview(rb)
	
	//getOrderDetail(b_num)
	public Vector getOrderDetail(int b_num){
			
		Vector totalList = new Vector();
		ArrayList orderList = new ArrayList();
		ArrayList productList = new ArrayList();
		
		try {
			con = getCon();
			
			sql  = "select * from p_order where b_num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, b_num);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
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
				ob.setD_postcode(rs.getInt("d_postcode"));
				ob.setD_result(rs.getInt("d_result"));
				ob.setId(rs.getString("id"));
				ob.setO_email(rs.getString("o_email"));
				ob.setO_name(rs.getString("o_name"));
				ob.setO_phone(rs.getString("o_phone"));
				ob.setP_num(rs.getInt("p_num"));
				ob.setPayment(rs.getInt("payment"));
				ob.setPoint(rs.getInt("point"));
				
				orderList.add(ob);
				
				sql = "select * from product where p_num=?";
				PreparedStatement pstmt2 = con.prepareStatement(sql);
				pstmt2.setInt(1, ob.getP_num());
				ResultSet rs2 = pstmt2.executeQuery();
				
				if(rs2.next()) {
					ProductBean pb = new ProductBean();
					pb.setP_name(rs2.getString("p_name"));
					pb.setImg_main(rs2.getString("img_main"));
					pb.setP_price(rs2.getInt("p_price"));
					pb.setP_saleprice(rs2.getInt("p_saleprice"));
					productList.add(pb);
				}//if
			}//while
			
			totalList.add(orderList);
			totalList.add(productList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			closeDB();
		}
		return totalList;
	}//getOrderDetail(b_num)
	
	
	// checkID(id)	
	public boolean checkID(String id) {
		boolean check = false;
		System.out.println("DAO : checkID() 호출");
		try {
			con = getCon();
			sql = "select id from member where id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}else {
				check = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return check;
	}// checkID(id)	
	
	
	// checkEmail(email)
	public boolean checkEmail(String email) {
		boolean check = false;
		System.out.println("DAO : checkEmail() 호출");
		try {
			con = getCon();
			sql = "select email from member where email=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, email);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				check = true;
			}else {
				check = false;
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			closeDB();
		}
		return check;
	}// checkEmail(email)
}
