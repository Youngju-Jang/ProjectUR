package net.product.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ProductQnaDAO {
	
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
		
		//insertQna(ProductQnaBean pqb)
		public void insertQna(ProductQnaBean pqb) {
			
			int qno = 1;
			
			try {
				con = getCon();
				
				sql = "select max(q_num) from productqna ";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					qno =  rs.getInt(1) + 1;
				}
				
				System.out.println("글번호 : "+qno);
				
				sql = "insert into productqna (q_num,id,p_num,subject,content, "
						+ "re_result,reply, re_ref, re_lev ,re_seq, reg_date,    re_reg_date) "
						+ "values(?,?,?,?,?,?,null,?,?,?,now(),null)";
				pstmt = con.prepareCall(sql);
				pstmt.setInt(1, qno);
				pstmt.setString(2, pqb.getId());
				pstmt.setInt(3, pqb.getP_num());
				pstmt.setString(4, pqb.getSubject());
				pstmt.setString(5, pqb.getContent());
				pstmt.setInt(6, 0);
				pstmt.setInt(7, qno);
				pstmt.setInt(8, 0);
				pstmt.setInt(9, 0);
				
				pstmt.executeUpdate();
				System.out.println("DAO : insertQna 실행완료!");
			} catch (Exception e1) {
				System.out.println("DAO : insertQna 실행실패!");
				e1.printStackTrace();
				
			}finally {
				closeDB();
			}
		}
		
		//insertQna(ProductQnaBean pqb)
		
		
		
		
		
		
		
		//getQnaCount(int p_num);
		public int getQnaCount(int p_num, String id){
			
			int count = 0;
			
			try {
				con = getCon();
				sql = "select count(*) from productqna where p_num = ? and id = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, p_num);
				pstmt.setString(2, id);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					count = rs.getInt("count(*)");
				}
				System.out.println("DAO QNA글갯수 계산 완료 : " + count);
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
			return count;
		}
		//getQnaCount(int p_num);
		
		//getQnaList(int p_num); 
		public ArrayList<ProductQnaBean> getQnaList(int p_num, String id){
			
			ArrayList<ProductQnaBean> qnaList = new ArrayList<ProductQnaBean>();
			
			StringBuffer sql = new StringBuffer(); 
			
			try {
				con = getCon();
				
				sql.append("select * from productqna where p_num = ? ");
				
				if(id.equals("admin")) {
					sql.append("order by re_ref desc, re_seq asc ");
				}else{
					sql.append("and id = ? order by re_ref desc, re_seq asc ");
				}
				pstmt = con.prepareStatement(sql.toString());
				
				if(id.equals("admin")) {
					pstmt.setInt(1, p_num);
				}else {
					pstmt.setInt(1, p_num);
					pstmt.setString(2, id);
				}
				
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
					
					qnaList.add(qb);
				}
				System.out.println("DAO : QNA게시판 목록 저장완료! 갯수 : " + qnaList.size()); 
			} catch (Exception e) {
				e.printStackTrace();
			}finally {
				closeDB();
			}
			return qnaList;
		}
		//getQnaList(int p_num);
		
		//replyQnaList(int p_num, String id)
		public void replyQnaList(ProductQnaBean pqb, int p_num) {
			//p_num, reply, id, q_num 넘김
			
			try {
				con = getCon();
				
				sql = "update productqna set re_result=1, reply =?, re_reg_date=now() "
						+ "where p_num=? and id = ? and q_num = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, pqb.getReply());
				pstmt.setInt(2, p_num);
				pstmt.setString(3, pqb.getId());
				pstmt.setInt(4, pqb.getQ_num());
				
				pstmt.executeUpdate();
				System.out.println("DAO : QNA Reply update 완료!");
			} catch (Exception e) {
				System.out.println("DAO : QNA Reply update 실패!");
				e.printStackTrace();
			}finally {
				closeDB();
			}
			
		}
		//replyQnaList(int p_num, String id)
		
		
		
		

}
