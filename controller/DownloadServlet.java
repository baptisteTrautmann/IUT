package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Utilisateur;

/**
 * Servlet implementation class Download
 */
@WebServlet(name="DownloadServlet",urlPatterns ="/Download")
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    private HttpSession session;
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");

		session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		String action = request.getParameter("action");

		if(action.equals("download"))
		{
			this.getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
		}
	}

}
