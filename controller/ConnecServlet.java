package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet({"/ConnecServlet", "/connexion"})
public class ConnecServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// ici vous utilisez évidemment votre outil de persistance
	// ces deux attributs sont mes EJB à moi qui gère ma persistance JPA
	@EJB
	private FacadeUtilisateur facadeUtilisateur;

	/**
	 * Réponse aux requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		response.setContentType("text/html");

		String action = request.getParameter("action");

		if(action.equals("connexion"))
		{

		}
	}

	/**
	 * Réponse aux requêtes de type POST, à modifier, compléter, etc.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		response.setContentType("text/html");

		String action = request.getParameter("action");
		HttpSession session = request.getSession();

		if(action.equals("connexion"))
		{
			PrintWriter out = response.getWriter();
			
			String login = request.getParameter("login");
			String pass = request.getParameter("password");
			Utilisateur u = facadeUtilisateur.findByLogin(login);
			
			if(u != null && pass.equals(u.getPassword()))
			{
				session.setAttribute("utilisateur", u);
				session.setAttribute("panier", new ArrayList<String>());
			}
			else
			{
				session.setAttribute("erreur", "Login ou mot de passe erroné.");
			}
		}
		else if(action.equals("deconnexion"))
		{
			session.removeAttribute("utilisateur");
		}
		response.sendRedirect("./accueil");
	}
}