package member.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import common.JDBCTemplate;
import member.model.vo.Member;

public class MemberDAO {

	public Member selectOneMember(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Member m = null;
		String query = "select * from member where userid = ? and userpwd = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setGender(rset.getString("gender"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setActivation(rset.getString("activation"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return m;
	}

	public ArrayList<Member> seletAll(Connection conn) {
		Statement stmt = null;
		ResultSet rset = null;
		Member m = null;
		ArrayList<Member> list = new ArrayList<Member>();
		String query = "select * from member";
		try {
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
			while(rset.next()) {
				m = new Member();
				m.setUserId(rset.getString("userid"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setGender(rset.getString("gender"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				m.setActivation(rset.getString("activation"));
				list.add(m);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(rset);
		JDBCTemplate.close(stmt);
		return list;
	}

	public int MemberActive(Connection conn, String userId, String act) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set activation=? where userid=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, act);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		return result;
	}

	public int insertMember(Connection conn, Member m) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into member values(?,?,?,?,?,?,?,?,?,sysdate,'Y',sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setInt(4, m.getAge());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getPhone());
			pstmt.setString(7, m.getAddress());
			pstmt.setString(8, m.getGender());
			pstmt.setString(9, m.getHobby());
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		return result;
	}

	public boolean idCheck(Connection conn, String checkId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from member where userid=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, checkId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				return true;
			}
			else {
				return false;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		JDBCTemplate.close(pstmt);
		JDBCTemplate.close(rset);
		return false;
	}

	public int updateMember(Connection conn, String userId, Member m) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int result = 0;
		boolean changePwd = false;
		String query = null;
		try {
			pstmt = conn.prepareStatement("select userpwd from member where userid=?");
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				if(!(m.getUserPwd().equals(rset.getString("userPwd")))) {
					changePwd = true;
				}
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(changePwd) {
			query = "update member set "
					+ "userpwd = ?, email = ?,"
					+ "phone = ?, address = ?,"
					+ "hobby = ?, last_modified=sysdate where userid = ?";
		}
		else {
			 query = "update member set "
						+ "userpwd = ?, email = ?,"
						+ "phone = ?, address = ?,"
						+ "hobby = ? where userid = ?";
		}
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, m.getUserPwd());
			pstmt.setString(2, m.getEmail());
			pstmt.setString(3, m.getPhone());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getHobby());
			pstmt.setString(6, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
						e.printStackTrace();
		}
		
		JDBCTemplate.close(pstmt);
		
		return result;
	}

	public int deleteMember(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete member where userid = ?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		return result;
	}

	public boolean changePwdCheck(Connection conn, String userId) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		boolean result = false;
		String query = "select floor(sysdate-last_modified) as change_date from member where userid=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				if(rset.getInt("change_date")>=90) {
					result = true;
				}
				else {
					result = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		JDBCTemplate.close(rset);
		JDBCTemplate.close(pstmt);
		return result;
	}

	public int changePwd(Connection conn, String userId, String userPwd) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update member set userpwd=?, last_modified=sysdate where userid=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, userPwd);
			pstmt.setString(2, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JDBCTemplate.close(pstmt);
		return result;
	}

}
