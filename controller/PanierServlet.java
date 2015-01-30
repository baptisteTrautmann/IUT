package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.FacadeCategorie;
import ejb.FacadeImage;
import model.Image;
import model.Utilisateur;

/**
 * Servlet implementation class PanierServlet
 */
@WebServlet(name="PanierServlet",urlPatterns ="/Panier",initParams = {@WebInitParam(name= "chemin", value="C:/images/")})
public class PanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CHEMIN = "chemin";
	public static final int BUFFER = 2048;
	
	@EJB
	private FacadeCategorie facadeCategorie;
	@EJB
	private FacadeImage facadeImage;
	
	private HttpSession session;
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		session = request.getSession();
		ArrayList<String> imagesId = (ArrayList<String>)session.getAttribute("panier");
		ArrayList<Image> listeImages = new ArrayList<Image>();
		
		if(imagesId !=null)
		{
			
		for(String id : imagesId)
		{
			listeImages.add((Image)facadeImage.find((Integer.parseInt(id))));
		}
		
				
		request.setAttribute("listeImages", listeImages);
		
		this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
		
		//String chemin = this.getServletConfig().getInitParameter( CHEMIN );
		}
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String action = request.getParameter("action");

		if(action.equals("auPanier"))
		{
			String idImage = request.getParameter("idImg");
						
			session = request.getSession();
			ArrayList<String> imagesId = (ArrayList<String>)session.getAttribute("panier");
			session.setAttribute("listeImages", imagesId.add(idImage));
			
			response.sendRedirect("./accueil");
		}
		else if(action.equals("panier"))
		{
			//this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
			response.sendRedirect("./Panier");
		}
		
	}

}
