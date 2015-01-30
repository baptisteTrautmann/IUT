package controller;
 
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ejb.FacadeImage;
import model.Image;

import java.io.* ;
import java.util.ArrayList;
import java.util.zip.* ;
     
 
@WebServlet(name="DownloadServlet",urlPatterns ="/Download",initParams = {@WebInitParam(name= "chemin", value="C:/images/")})
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String CHEMIN = "chemin";
	
	@EJB
	private FacadeImage facadeImage;
	
	
	void addFile( ZipOutputStream out, File f, String name ){
		
		FileInputStream in = null;
		
		try {
			// Ajout de l'entrée dans le Zip
			out.putNextEntry( new ZipEntry( name ) ) ;
			
			in = new FileInputStream( f ) ;
			 
			// Transfert des données du fichiers vers le Zip
			byte[] buf = new byte[ 4096 ] ;
			int len ;
			while( ( len = in.read( buf ) ) > 0 )
			{
				out.write( buf, 0, len ) ;
			}
		}
		catch( IOException ex ) { ex.printStackTrace(); }
		finally
		{
			// Complete the entry
			try{ out.closeEntry() ; } catch( IOException ex ) { }
			try{ in.close() ; } catch( IOException ex ) { }
		}
	}
 
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
				
		ArrayList<String> imagesId = (ArrayList<String>)session.getAttribute("panier");
		ArrayList<Image> listeImages = new ArrayList<Image>();
		
		for(String id : imagesId)
		{
			listeImages.add((Image)facadeImage.find((Integer.parseInt(id))));
		}
		
		//String chemin = this.getServletConfig().getInitParameter( CHEMIN );
		//System.out.println(chemin);
		   
		if (listeImages != null){
			// set the content type and the filename
			response.setContentType( "application/zip" ) ;
			response.addHeader( "Content-Disposition", "attachment; filename=jee_images.zip" ) ;
			 
			// get a ZipOutputStream, so we can zip our files together
			ZipOutputStream out = new ZipOutputStream( response.getOutputStream() );
			
			//Ajout de toutes les images du cart dans le stream du Zip
			for (Image i : listeImages){
				addFile( out, new File( i.getSource() ), i.getSource()) ;
			}
			
			session.setAttribute("panier",  new ArrayList<Image>());
			 
			// flush the stream, and close it
			out.flush() ;
			out.close() ;
		}
		else {
			session.setAttribute("erreur","Panier vide");
			response.sendRedirect("./accueil");
		}
	}
}