package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet(name="UploadServlet",urlPatterns ="/UploadServlet",initParams = {@WebInitParam(name= "chemin :", value="/images/")})
@MultipartConfig(location="/tmp", fileSizeThreshold=1024*1024,maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet extends HttpServlet {
	
	
	public static final String VUE = "/upload.jsp";
	public static final String CHAMP_DESCRIPTION = "description";
	public static final String CHAMP_FICHIER = "fichier";
	public static final String CHEMIN = "chemin";
	public static final int TAILLE_TAMPON = 10240; // 10 ko
	
	public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
		/* Affichage de la page d'envoi de fichiers */
		this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	}

	public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
	    /*
	     * Lecture du param�tre 'chemin' pass� � la servlet via la d�claration dans le web.xml
	     */

	    String chemin = this.getServletConfig().getInitParameter( CHEMIN );

	    /* R�cup�ration du contenu du champ de description */
	    String description = request.getParameter( CHAMP_DESCRIPTION );
	    request.setAttribute( CHAMP_DESCRIPTION, description );

	    /*
	     * Les donn�es re�ues sont multipart, on doit donc utiliser la m�thode
	     * getPart() pour traiter le champ d'envoi de fichiers.
	     */

	    Part part = request.getPart( CHAMP_FICHIER );
	    /*
	     * Il faut d�terminer s'il s'agit d'un champ classique 
	     * ou d'un champ de type fichier : on d�l�gue cette op�ration 
	     * � la m�thode utilitaire getNomFichier().
	     */

	    String nomFichier = getNomFichier( part );

	    /*
	     * Si la m�thode a renvoy� quelque chose, il s'agit donc d'un champ de type fichier (input type="file").
	     */

	    if ( nomFichier != null && !nomFichier.isEmpty() ) {

	        String nomChamp = part.getName();
	        /*
	         * Antibug pour Internet Explorer, qui transmet pour une raison
	         * mystique le chemin du fichier local � la machine du client...
	         * Ex : C:/dossier/sous-dossier/fichier.ext
	         * 
	         * On doit donc faire en sorte de ne s�lectionner que le nom et
	         * l'extension du fichier, et de se d�barrasser du superflu.
	         */

	         nomFichier = nomFichier.substring( nomFichier.lastIndexOf( '/' ) + 1 ).substring( nomFichier.lastIndexOf( '\\' ) + 1 );

	        /* �criture du fichier sur le disque */
	        ecrireFichier( part, nomFichier, chemin );

	        request.setAttribute( nomChamp, nomFichier );

	    }

	    this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );

	}
		
	/* 
	 * M�thode utilitaire qui a pour unique but d'analyser l'en-t�te "content-disposition",
	 * et de v�rifier si le param�tre "filename"  y est pr�sent. Si oui, alors le champ trait�
	 * est de type File et la m�thode retourne son nom, sinon il s'agit d'un champ de formulaire 
	 * classique et la m�thode retourne null. 
	 */
	private static String getNomFichier( Part part ) {
	    /* Boucle sur chacun des param�tres de l'en-t�te "content-disposition". */
	    for ( String contentDisposition : part.getHeader( "content-disposition" ).split( ";" ) ) {
	    	/* Recherche de l'�ventuelle pr�sence du param�tre "filename". */
	        if ( contentDisposition.trim().startsWith("filename") ) {
	            /* Si "filename" est pr�sent, alors renvoi de sa valeur, c'est-�-dire du nom de fichier. */
	            return contentDisposition.substring( contentDisposition.indexOf( '=' ) + 1 );
	        }
	    }
	    /* Et pour terminer, si rien n'a �t� trouv�... */
	    return null;
	}
	
	/*

	 * M�thode utilitaire qui a pour but d'�crire le fichier pass� en param�tre

	 * sur le disque, dans le r�pertoire donn� et avec le nom donn�.

	 */

	private void ecrireFichier( Part part, String nomFichier, String chemin ) throws IOException {

	    /* Pr�pare les flux. */
	    BufferedInputStream entree = null;
	    BufferedOutputStream sortie = null;
	    
	    try {
	        // Ouvre les flux. 
	    	
	        entree = new BufferedInputStream( part.getInputStream(), TAILLE_TAMPON );
	        sortie = new BufferedOutputStream( new FileOutputStream( new File( chemin + nomFichier)),TAILLE_TAMPON );
	        //TODO : sortie null -> new File null pourquoi ???
	        
	        //Lit le fichier re�u et �crit son contenu dans un fichier sur le disque.
	        byte[] tampon = new byte[TAILLE_TAMPON];
	        int longueur;
	        
	        while ( ( longueur = entree.read( tampon ) ) > 0 ) {
	            sortie.write( tampon, 0, longueur );
	        }
	    } finally {
	        try {
	            sortie.close();
	        } catch ( IOException ignore ) {
	        }
	        try {
	            entree.close();
	        } catch ( IOException ignore ) {
	        }
	    }
	}
}