package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class UpdateUsers
 */
@WebServlet("/UpdateUsers")
public class UpdateUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	request.setCharacterEncoding("UTF-8");
	
	HttpSession session = request.getSession();
	
	UsersDTO info = (UsersDTO)session.getAttribute("info");
	
	// 입력폼을 통해 수정할 정보 받기
	
	String pw= request.getParameter("pw"); // 비밀번호
	String nickname=request.getParameter("nickname"); // 닉네임
	String email=request.getParameter("email"); // email
	String phone=request.getParameter("phone"); // 전화번호
	
	
	UsersDAO dao= new UsersDAO();
	// info에서 id가져오기
	
	
	String id= info.getId();
	if(pw == null) { //유저가 폼에 입력하지 않은 경우 , 단 세션에도 비밀번호는 저장되지 않으므로 세션에서 가져올 수 없음.
		pw =dao.getPw(id);
	}
	if(phone ==null) {//유저가 폼에 입력하지 않은 경우 , 단 세션에도 폰번호는 저장되지 않으므로 세션에서 가져올 수 없음.
		phone =dao.getPhone(id);
	}
	if(nickname == null) {
		nickname = info.getNickname();
	}
	if(email == null) {
		email= dao.getEmail(id);
	}
	
	UsersDTO dto = new UsersDTO(pw, nickname, email, phone, id);
	int cnt =dao.updateUsers(dto);
	
	if(cnt>0) {
		System.out.println("회원수정 성공");
		response.sendRedirect("destination.jsp");
	}
	else {
		System.out.println("회원수정 실패");
		response.sendRedirect("destination.jsp");
	}
	
	
	
	
	}

}
