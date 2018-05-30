package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import common.NoticeTemplate;
import notice.model.vo.Notice;
import notice.model.vo.NoticeComment;

public class NoticeDao {

	public ArrayList<Notice> getCurrentPage(Connection conn, int currentPage, int recordCountPerPage) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		//시작 게시물 계산
		int start = (currentPage - 1)*recordCountPerPage + 1;
		//		currentPage*recordCountPerPage - (recordCountPerPage-1);

		//끝 게시물 계산
		int end = currentPage*recordCountPerPage;

		String query = "select * from "
				+ "(select notice.*, row_number() over(order by noticeno desc) as num from notice)"
				+ "where num between ? and ?";
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
				list.add(n);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public String getPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage) {
		//게시물의 토탈 개수를 구해야 함
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0; //총 게시물 개수 저장 변수
		String query = "select count(*) as totalcount from notice";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		String controllName="notice?";
		return NoticeTemplate.getPageNaviHtml(recordTotalCount, recordCountPerPage, currentPage, naviCountPerPage,
				controllName);
	}

	public ArrayList<Notice> getCurrentSearchPage(Connection conn, int currentPage, int recordCountPerPage, String sel,
			String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;

		int start = (currentPage - 1)*recordCountPerPage + 1;
		int end = currentPage*recordCountPerPage;
		String query = "select * from (" + 
				"select notice.*, row_number() over(order by noticeno desc) as num from notice " + 
				"where "+sel+" like " + "'%" + search + "%')" + 
				"where num between ? and ?";
		ArrayList<Notice> list = new ArrayList<Notice>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("noticeno"));
				n.setSubject(rset.getString("subject"));
				n.setContents(rset.getString("contents"));
				n.setUserId(rset.getString("userid"));
				n.setRegDate(rset.getDate("regdate"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public String getSearchPageNavi(Connection conn, int currentPage, int recordCountPerPage, int naviCountPerPage,
			String sel, String search) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		int recordTotalCount = 0; //총 게시물 개수 저장 변수
		String query = "select count(*) as totalcount from notice where "+sel+" like '%"+search+"%'";
		try {
			pstmt = conn.prepareStatement(query);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				recordTotalCount = rset.getInt("totalcount");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		String controllName="search?sel="+sel+"&search="+search+"&";
		return NoticeTemplate.getPageNaviHtml(recordTotalCount, recordCountPerPage, currentPage, naviCountPerPage
				, controllName);
	}

	public Notice noticeSelect(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		Notice notice = null;
		String query = "select * from notice where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				notice = new Notice();
				notice.setNoticeNo(rset.getInt("noticeno"));
				notice.setSubject(rset.getString("subject"));
				notice.setContents(rset.getString("contents"));
				notice.setUserId(rset.getString("userid"));
				notice.setRegDate(rset.getDate("regdate"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return notice;
	}

	public int noticeUpdate(Connection conn, String subject, String contents, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update notice set subject=?, contents=? where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			pstmt.setInt(3, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int insertNotice(Connection conn, String subject, String contents) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into notice values(SEQ_NOTICE.NEXTVAL,?,?,'admin',sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, subject);
			pstmt.setString(2, contents);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public int deleteNotice(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from notice where noticeno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<NoticeComment> noticeComment(Connection conn, int noticeNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String query = "select * from noticecomment where noticeno=?";
		ArrayList<NoticeComment> list = new ArrayList<NoticeComment>();
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				NoticeComment nc = new NoticeComment();
				nc.setCommentNo(rset.getInt("commentno"));
				nc.setNoticeNo(rset.getInt("noticeno"));
				nc.setContent(rset.getString("content"));
				nc.setUserId(rset.getString("userid"));
				nc.setRegDate(rset.getDate("regdate"));
				list.add(nc);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return list;
	}

	public int writeComment(Connection conn, int noticeNo, String userId, String comment) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "insert into noticecomment values(SEQ_noticecomment.NEXTVAL,?,?,?,sysdate)";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, noticeNo);
			pstmt.setString(2, comment);
			pstmt.setString(3, userId);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int updateComment(Connection conn, String content, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "update noticecomment set content=? where commentno=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, content);
			pstmt.setInt(2, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}

	public int deleteComment(Connection conn, int commentNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String query = "delete from noticecomment where commentNo=?";
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, commentNo);
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
