package notice.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import notice.model.service.NoticeService;
import notice.model.vo.PageData;

/**
 * Servlet implementation class NoticeServlet
 */
@WebServlet(name = "Notice", urlPatterns = { "/notice" })
public class NoticeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int currentPage;
		if(request.getParameter("currentPage")==null) {  //첫페이지면 1로 셋팅 그외 페이지면 해당 페이지 값을 가져옴
			currentPage = 1;
		}
		else {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));
		}
		PageData pd = new NoticeService().noticeAll(currentPage);
		if(pd!=null) {
			RequestDispatcher view = request.getRequestDispatcher("/views/notice/notice.jsp");
			request.setAttribute("pageData", pd);
			view.forward(request, response);
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
