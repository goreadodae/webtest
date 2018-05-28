package notice.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import common.JDBCTemplate;
import common.NoticeTemplate;
import notice.model.vo.Notice;

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
		String controllName="notice";
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
		System.out.println(query);
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
		String controllName="search?sel="+sel+"&search="+search;
		return NoticeTemplate.getPageNaviHtml(recordTotalCount, recordCountPerPage, currentPage, naviCountPerPage
				, controllName);
	}

}
