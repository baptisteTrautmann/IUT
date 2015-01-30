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
import ejb.FacadeUtilisateur;

/**
 * Servlet implementation class JeeServlet
 */
@WebServlet({"/UserServlet", "/creationCompte"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ici vous utilisez évidemment votre outil de persistance
	// ces deux attributs sont mes EJB à moi qui gère ma persistance JPA
	@EJB
	private FacadeUtilisateur facadeUtilisateur;
	@EJB
	private FacadeImage facadeImage;
	
	private HttpSession session;

	/**
	 * Réponse aux requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		HttpSession session = request.getSession();
		String erreur = (String) session.getAttribute("erreur");
		request.setAttribute("erreur", erreur);
		this.getServletContext().getRequestDispatcher("/creation.jsp").forward(request, response);
	}

	/**
	 * Réponse aux requêtes de type POST, à modifier, compléter, etc.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		PrintWriter out = response.getWriter();
		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if(action.equals("creation"))
		{
			String erreur = (String) session.getAttribute("erreur");
			request.setAttribute("erreur", erreur);
			this.getServletContext().getRequestDispatcher("/creation.jsp").forward(request, response);
		}
		else if(action.equals("creerCompte"))
		{
			String login = request.getParameter("loginCreer");
			String pass = request.getParameter("passwordCreer");
			
			Utilisateur u = new Utilisateur();
			u.setLogin(login);
			u.setPassword(pass);
			Utilisateur u2 = facadeUtilisateur.findByLogin(login);
			
			if(u2 != null)
			{
				session.setAttribute("erreur", "Utilisateur déjà existant.");
				response.sendRedirect("./creationCompte");
			}
			else
			{
				facadeUtilisateur.create(u);
				response.sendRedirect("./accueil");
			}
		}
	}
}