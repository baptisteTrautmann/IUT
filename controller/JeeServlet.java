package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Categorie;
import model.Image;
import model.Utilisateur;
import ejb.FacadeCategorie;
import ejb.FacadeImage;

/**
 * Servlet implementation class JeeServlet
 */
@WebServlet({"/JeeServlet", "/accueil"})
public class JeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ici vous utilisez évidemment votre outil de persistance
	// ces deux attributs sont mes EJB à moi qui gère ma persistance JPA
	@EJB
	private FacadeCategorie facadeCategorie;
	@EJB
	private FacadeImage facadeImage;
	
	private HttpSession session;

	/**
	 * Réponse aux requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		session = request.getSession();
		Utilisateur u = (Utilisateur) session.getAttribute("utilisateur");
		request.setAttribute("utilisateur", u);
		this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * Réponse aux requêtes de type POST, à modifier, compléter, etc.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");

		String action = request.getParameter("action");

		if(action.equals("upload"))
		{
			request.setAttribute("ListeCategorie", facadeCategorie.findAll());
			this.getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		else if (action.equals("tout")) 
		{
			request.setAttribute("toutesImages", facadeImage.findAll());
			this.getServletContext().getRequestDispatcher("/afficheToutesImages.jsp").forward(request, response);
		}
	}
}