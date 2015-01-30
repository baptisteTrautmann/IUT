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
		
		List<Image> listeImages = facadeImage.getImagesById(imagesId);
		request.setAttribute("listeImages", listeImages);
		
		this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
		
		
		
		
		String chemin = this.getServletConfig().getInitParameter( CHEMIN );
		
		
		/*		
		File repertoire = new File(chemin);
		ArrayList<File> files = new ArrayList<File>();
		
		
		
		for(Image image : imgList)
		{
			files.add(new File(image.getSource()));
		}
		
		
		byte data[] = new byte[BUFFER];
		FileOutputStream dest= new FileOutputStream("archive.zip");
		BufferedOutputStream buff = new BufferedOutputStream(dest);
		ZipOutputStream out = new ZipOutputStream(buff);
		out.setMethod(ZipOutputStream.DEFLATED);
		out.setLevel(9);
		
		for(int i=0; i<files.size(); i++) {
		    FileInputStream fi = new FileInputStream(files.get(i));
		    BufferedInputStream buffi = new BufferedInputStream(fi, BUFFER);
		    ZipEntry entry= new ZipEntry(files.get(i).getName());
		    out.putNextEntry(entry);
		    
		    int count;
			while((count = buffi.read(data, 0, BUFFER)) != -1) {
		        out.write(data, 0, count);
		    }
			out.closeEntry();
			buffi.close();
		}
		out.close();
		*/
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
		
	}

}
