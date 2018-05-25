package member.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import jdk.nashorn.internal.scripts.JD;
import member.model.dao.MemberDAO;
import member.model.vo.Member;

public class MemberService {
	public MemberService() {}
	public Member selectOneMember(String userId, String userPwd) {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		Member m = new MemberDAO().selectOneMember(conn, userId, userPwd);
		JDBCTemplate.close(conn);
		return m;
	}
	public ArrayList<Member> selectAll() {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		ArrayList<Member> list = new MemberDAO().seletAll(conn);
		JDBCTemplate.close(conn);
		return list;
	}
	public int MemberActive(String userId, String act) {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		int result = new MemberDAO().MemberActive(conn, userId, act);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public int insertMember(Member m) {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		int result = new MemberDAO().insertMember(conn, m);
		if(result>0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		JDBCTemplate.close(conn);
		return result;
	}
	public boolean idCheck(String checkId) {
		Connection conn = null;
		conn = JDBCTemplate.getConnection();
		boolean result = new MemberDAO().idCheck(conn, checkId);
		JDBCTemplate.close(conn);
		return result;
	}
}
