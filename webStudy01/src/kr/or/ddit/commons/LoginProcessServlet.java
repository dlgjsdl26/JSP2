package kr.or.ddit.commons;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;

@WebServlet("/login/loginProcess.do")
public class LoginProcessServlet extends HttpServlet{
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		/*1. 요청 파라미터 확보
		2. 검증
			- 통과 : 3번 처리
			- 불통 : 400 error 발생.
		3. 인증처리 (입력한 아이디와 비밀번호가 동일하면 성공)
			- 성공 : welcome page 로 이동 (Redirection)
					index.jsp 생성(authId 라는 속성으로 현재 로그인한 유저의 아이디를 출력.)
			- 실패(비밀번호 오류로 간주) : loginForm.jsp 로 이동 (동일한 아이디를 다시 입력하지 않도록.)
		*/
		String msg = "";
		String mem_id = req.getParameter("mem_id");
		String mem_pass = req.getParameter("mem_pass");
		
		int statusCode = HttpServletResponse.SC_OK;
		if(StringUtils.isBlank(mem_id) || StringUtils.isBlank(mem_pass)){
			msg = "필수 파라미터 누락";
			statusCode = HttpServletResponse.SC_BAD_REQUEST;
			resp.sendError(statusCode, msg);
			return;
		}
		
		String goPage = null;
		boolean redirect = false;
		
		HttpSession session = req.getSession(false);
		if(session == null || session.isNew()) {
			resp.sendError(statusCode, "현재 요청이 최초요청일수 없음.");
			return;
		}
		String message = null;
		if(mem_id.equals(mem_pass)) {
			goPage = "/";
			redirect = true;
			session.setAttribute("authID", mem_id);
		}else {
			goPage = "/login/loginForm.jsp";
			message = "비번 오류";
			req.setAttribute("message", message);
		}
		
		if(redirect) {
			resp.sendRedirect(req.getContextPath() + goPage);
		}else {
			req.getRequestDispatcher(goPage).forward(req, resp);
			
		}
		
	
		
		
		
	}// doPost 끝
}













