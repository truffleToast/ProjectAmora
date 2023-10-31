package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GbcDAO;
import model.ShcDAO;
import model.ShcDTO;

/**
 * Servlet implementation class DeleteComment
 */
@WebServlet("/DeleteGbcComment")
public class DeleteGbcComment extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	GbcDAO gbc =new GbcDAO();
	
//	ShcDTO dto =new ShcDTO();
	
	
	
	
	}

}
