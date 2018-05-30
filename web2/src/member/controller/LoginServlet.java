package member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 전송값에 한글이 있을 경우 처리할 수 있도록 인코딩 처리
		request.setCharacterEncoding("UTF-8");
		//2. view에서 전송한 데이터를 받아 변수에 저장
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		//3. 비즈니스 로직 처리(Controller -> service -> dao -> db 처리 후 리턴)
		Member m = new MemberService().selectOneMember(userId,userPwd);
		//4. 처리 결과에 따라 성공/실패 페이지 리턴
		System.out.println(request.getRemoteAddr());
		if(m==null) {
			response.sendRedirect("views/member/loginFail.jsp");
		}
		else {
			if(m.getActivation().equals("Y")) {
				boolean result = new MemberService().changePwdCheck(userId);
				HttpSession session = request.getSession();
				session.setAttribute("user", m);
				if(result) {
					response.sendRedirect("/views/member/passwordChange.jsp");
				}
				else {
					response.sendRedirect("views/member/loginSuccess.jsp");
				}
			}
			else {
				response.sendRedirect("views/member/loginNoAct.html");
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
