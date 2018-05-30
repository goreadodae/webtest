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
 * Servlet implementation class WriteCommentServlet
 */
@WebServlet(name = "WriteComment", urlPatterns = { "/writeComment" })
public class WriteCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WriteCommentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session!=null) {
			String comment = request.getParameter("comment");
			int noticeNo = Integer.parseInt(request.getParameter("noticeNo"));
			String userId = ((Member)session.getAttribute("user")).getUserId();
			int result = new NoticeService().writeComment(noticeNo, userId, comment);
			if(result>0) {
				response.sendRedirect("/noticeSelect?noticeNo="+noticeNo);
			}
			else {
				response.sendRedirect("/views/notice/error.html");
			}
		}
		else {
			response.sendRedirect("/views/notice/error.html");
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
