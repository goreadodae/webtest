package notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.vo.Member;
import notice.model.service.NoticeService;

/**
 * Servlet implementation class DeleteNoticeServlet
 */
@WebServlet(name = "DeleteNotice", urlPatterns = { "/deleteNotice" })
public class DeleteNoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteNoticeServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session.getAttribute("user")!=null&&
				((Member)session.getAttribute("user")).getUserId().equals("admin")){
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			int result = new NoticeService().deleteNotice(noticeNo);
			if(result>0) {
				response.sendRedirect("/notice?currentPage=1");
			}
			else {
				response.sendRedirect("/views/notice/error.html");
			}
		}
		else {
			response.sendRedirect("/views/notice/adminError.html");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
