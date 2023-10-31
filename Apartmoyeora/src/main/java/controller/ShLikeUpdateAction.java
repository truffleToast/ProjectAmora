package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.UsersDAO;
import model.UsersDTO;

/**
 * Servlet implementation class BoardLikeUpdateAction
 */
@WebServlet("/ShLikeUpdateAction")
public class ShLikeUpdateAction extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	UsersDAO udao = new UsersDAO();
	HttpSession session = request.getSession();
	UsersDTO info = (UsersDTO) session.getAttribute("info");
	
	}

}
