package notice.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import common.JDBCTemplate;
import notice.model.dao.NoticeDao;
import notice.model.vo.Notice;
import notice.model.vo.PageData;

public class NoticeService {

	public PageData noticeAll(int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		//Service에서는 2가지 값을 정해야함
		//1. 한 페이지당 보이는 리스트의 개수 (게시물의 개수)
		//2. 현재 위치를 중심으로 시작 navi에서부터 끝 navi 개수
		
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		//Service에서는 DAO에 2가지 요청을 진행해야 함
		//1. 현재 페이지 리스트
		//2. 현재 중심으로 만들어지는 navi리스트
		ArrayList<Notice> list = new NoticeDao().getCurrentPage(conn, currentPage, recordCountPerPage);
		String pageNavi = new NoticeDao().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);
		JDBCTemplate.close(conn);
		
		PageData pd = null;
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData();
			pd.setNoticeList(list);
			pd.setPageNavi(pageNavi);
		}
		return pd;
	}

	public PageData searchNotice(String search, int currentPage) {
		Connection conn = JDBCTemplate.getConnection();
		int recordCountPerPage = 10;
		int naviCountPerPage = 5;
		
		ArrayList<Notice> list = new NoticeDao().getCurrentPage(conn, currentPage, recordCountPerPage);
		String pageNavi = new NoticeDao().getPageNavi(conn, currentPage, recordCountPerPage, naviCountPerPage);
		JDBCTemplate.close(conn);
		
		PageData pd = null;
		if(!list.isEmpty() && !pageNavi.isEmpty()) {
			pd = new PageData();
			pd.setNoticeList(list);
			pd.setPageNavi(pageNavi);
		}
		return pd;
		
	}

}
