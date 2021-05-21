package com.dev.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.dev.vo.MemberVO;

public class MemberDAO {
	
	Connection conn;
	PreparedStatement psmt;
	ResultSet rs;
	
	private static MemberDAO dao = new MemberDAO();
	private MemberDAO() {}
	public static MemberDAO getInstance() {
		return dao;
	}
	
	//연결처리 connection 객체
	private void connect() {
		try {
			InitialContext ic = new InitialContext();
			DataSource ds = (DataSource) ic.lookup("java:comp/env/jdbc/myoracle");
			conn = ds.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	//resource반환
	private void close() {
		if(rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(psmt != null)
			try {
				psmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		if(conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	// 한건조회
	public MemberVO searchMember(String id) {
		connect();
		String sql = "select * from member_b where id=?";
		MemberVO member = null;
		try {
			psmt = conn.prepareStatement(sql);		//psmt객체
			psmt.setString(1, id);
			rs = psmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setmail(rs.getString("mail"));
				member.setName(rs.getString("name"));
				member.setPasswd(rs.getString("passwd"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return member;
	}
	// DB처리기능
	public void insertMember(MemberVO member) {
		connect();
		String sql = "insert into member_b(id, name, passwd, mail) values(?,?,?,?)";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getId());
			psmt.setString(2, member.getName());
			psmt.setString(3, member.getPasswd());
			psmt.setString(4, member.getmail());
			int r = psmt.executeUpdate();
			System.out.println(r + " 건 입력.");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
	}
	//리스트
	public List<MemberVO> listMember(){
		connect();
		String sql = "select * from member_b order by 1";
		List<MemberVO> memberList = new ArrayList<>();
		try {
			psmt = conn.prepareStatement(sql);		//psmt객체
			rs = psmt.executeQuery();
			while(rs.next()) {
				MemberVO member = new MemberVO();
				member.setId(rs.getString("id"));
				member.setmail(rs.getString("mail"));
				member.setName(rs.getString("name"));
				member.setPasswd(rs.getString("passwd"));
				memberList.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return memberList;
	}
	
	//삭제
	public void deleteMember(String id) {
		connect();
		String sql = "delete from member_b where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, id);
			int r =psmt.executeUpdate();
			System.out.println(r + "건 삭제");
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
	
	//수정
	public void updateMember(MemberVO member) {
		connect();
		String sql = "update member_b set passwd=?,name=?,mail=?  where id=?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, member.getPasswd());
			psmt.setString(2, member.getName());
			psmt.setString(3, member.getmail());
			psmt.setString(4, member.getId());
			psmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
	}
	
}
