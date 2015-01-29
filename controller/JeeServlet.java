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

import model.Categorie;
import model.Image;
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

	/**
	 * Réponse aux requêtes de type GET
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{	
		response.setContentType("text/html");

		String action = request.getParameter("action");

		if(action.equals("upload"))
		{
			request.setAttribute("toutesImages", facadeImage.findAll());
			this.getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		else if (action.equals("tout")) 
		{
			this.getServletContext().getRequestDispatcher("/afficheToutesImages.jsp").forward(request, response);
		}
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
			request.setAttribute("toutesImages", facadeImage.findAll());
			this.getServletContext().getRequestDispatcher("/upload.jsp").forward(request, response);
		}
		else if (action.equals("tout")) 
		{
			this.getServletContext().getRequestDispatcher("/afficheToutesImages.jsp").forward(request, response);
		}
	}

	/**
	 * Méthode renvoyent une chaîne de caractères au format HTML
	 * contenant la liste des catégories disponibles dans la base
	 * @return une chaîne au format HTML
	 */
	private String getHTMLCategories() {
		String html = "<ul>";
		// ici vous utilisez évidemment votre outil de persistance
		List<Categorie> l = facadeCategorie.findAll();
		for (Categorie s : l) {
			html += "<li>" + s.getNom() + "</li>";
		}
		html += "</ul>";
		return html;
	}

	/**
	 * Méthode renvoyant une chaîne de caractères au format HTML 
	 * décrivant l'objet d'identifiant id dans la base, et la chaîne
	 * "Aucun produit ne correspond à cet identifiant." s'il n'est pas
	 * trouvé.
	 * @return une chaîne au format HTML
	 */
	private String getHTMLImages(String id) {
		String html = "";
		Image i = null;
		try {
			// ici vous utilisez évidemment votre outil de persistance
			i = (Image) facadeImage.find(Integer.parseInt(id));
		} catch (NumberFormatException ex) {
			i = null;
		}
		if (i != null) {
			/*html += "<p>" + p.getNom() + "</p>";
			html += "<ul>";
			html += "<li> Catégorie : " + p.getCategorie() + "</li>";
			html += "<li> Prix : &euro; " + p.getPrix() + " ("
					+ p.getDescription() + ")</li>";
			html += "</ul>";*/
		} else {
			//html += "<p>Aucun produit ne correspond à cet identifiant.</p>";
		}
		return html;
	}

}