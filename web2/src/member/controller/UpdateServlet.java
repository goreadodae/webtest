package member.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet(name = "Update", urlPatterns = { "/update" })
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession(false);
		if(session==null) {
			response.sendRedirect("/views/member/error.html");
		}
		else {
			String userId = ((Member)session.getAttribute("user")).getUserId();
			Member m = new Member();
			m.setUserPwd(request.getParameter("userPwd"));
			m.setEmail(request.getParameter("email"));
			m.setPhone(request.getParameter("phone"));
			m.setAddress(request.getParameter("addr"));
			m.setHobby(request.getParameter("hobby"));
			int result = new MemberService().updateMember(userId,m);
			if(result>0) {
				m = new MemberService().selectOneMember(userId, m.getUserPwd());
				if(m!=null) {
					session.setAttribute("user", m);
					response.sendRedirect("/views/member/updateSuccess.jsp");
				}
				else {
					response.sendRedirect("/views/member/updateFail.jsp");
				}
			} else {
				response.sendRedirect("/views/member/updateFail.jsp");
			}
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
